package com.robotllama.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.robotllama.CustomActors.CompositeImage;
import com.robotllama.CustomActors.CustomImage;
import com.robotllama.CustomActors.CustomTableActor;
import com.robotllama.CustomActors.CustomTextActor;
import com.robotllama.Entities.Explosion;
import com.robotllama.Entities.Hero;
import com.robotllama.Entities.HeroTrail;
import com.robotllama.Entities.ObjectDropHandler;
import com.robotllama.Handlers.ImageHandler;
import com.robotllama.Main;
import com.robotllama.Settings.GameSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Utils.Animations;
import Utils.EntityStates.MoveTo;
import Utils.Musics;
import Utils.RenderGDXSettings;
import Utils.Score;

public class Play implements Screen, InputProcessor{

	List<String> actionKeys = new ArrayList<String>();
	List<Actor> emptyActorList = new ArrayList<Actor>();

	// =========================================
	// Screen variables
	// =========================================
	private Screen newScreen;
	private Stage stage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Actor actorHolder;
	private Array<Actor> actorList = new Array<Actor>();
	private Vector2 vector = new Vector2();
	private int adCount = 1;
	private Sprite bgSprite = ImageHandler.bgSprite;


	// =========================================
	// Final variables
	// =========================================
	private static final float screenWidth = GameSettings.screenWidth;
	private static final float screenHeight = GameSettings.screenHeight;
	private static final float screenMargin = GameSettings.getScreenMargin();
	private static final float menuButtonSize = GameSettings.getMenuButtonSize();
	private static final int adFrequency = GameSettings.interAdFrequency;


	// =========================================
	// Game variables
	// =========================================
	private Hero hero;
	private int seconds = 0;
	private Gamestate GAMESTATE;
	Boolean COUNTDOWNTRIGGERED = false;
	InputMultiplexer inputMultiplexer  = new InputMultiplexer();
	boolean gameOver = false;
	private Main game;
	private CustomImage groundImageRight, groundImageLeft;
	private ObjectDropHandler objectDropHandler;
	private Explosion explosion;
	private HeroTrail heroTrail;


    // =========================================
    // Audio variables
    // =========================================
    private boolean canPlay = true;
    private int peakCounter = 0;


	// =========================================
	// Lists and maps
	// =========================================
	private List<Actor>buttonList = new ArrayList<Actor>();
	private List<Actor>GameOverActors = new ArrayList<Actor>();
	private List<Actor>CountdownActors = new ArrayList<Actor>();
	private List<Actor>GameActors = new ArrayList<Actor>();
	private List<Actor>GameSideActors = new ArrayList<Actor>();
	private List<List<Actor>> actionShow = new ArrayList<List<Actor>>();
	private List<List<Actor>> actionHide = new ArrayList<List<Actor>>();


	// =========================================
	// Button variables
	// =========================================
	private CompositeImage scoresBtn, replayBtn, homeBtn, googleBtn, rateBtn, shareBtn;


	// =========================================
	// Game state enums
	// =========================================
	enum Gamestate{
		COUNTDOWN, RUNNING, GAMEOVER, PAUSED;
	}


	private void reset(){
		seconds = 0;
		COUNTDOWNTRIGGERED = false;
		gameOver = false;
		//hero.reset();
		Score.reset();
		Main.imageHandler.currentScoreText.setString(String.valueOf(Score.getScore()));
		Main.imageHandler.countdownText.setString(GameSettings.countdownStart);
		setStage(Gamestate.COUNTDOWN);
	}


	private void clearActors(){
		actionShow.clear();
		actionHide.clear();
		actionHide.add(GameOverActors);
		actionHide.add(GameActors);
		actionShow.add(GameSideActors);
		actionShow.add(CountdownActors);
	}


	public Play(final Main game){
		this.game = game;
		GAMESTATE = Gamestate.COUNTDOWN;

		// =========================================
		// Set up the camera
		// =========================================
		camera = new OrthographicCamera(screenWidth, screenHeight);
		camera.position.set(GameSettings.getScreenWidthPos(.5f), GameSettings.getScreenHeightPos(.5f), 0);


		// =========================================
		// initiate batch and stage
		// =========================================
		batch = new SpriteBatch();
		stage = new Stage();


		// =========================================
		// Initiate object handler
		// =========================================
		objectDropHandler = new ObjectDropHandler();


		// =========================================
		// Get ground sprite
		// =========================================
		groundImageRight = new CustomImage(
				Main.imageHandler.groundSpriteRight,
				new Vector2(screenWidth - Main.imageHandler.groundSpriteRight.getWidth() , 0));

		groundImageLeft = new CustomImage(
				Main.imageHandler.groundSpriteLeft,
				new Vector2(0, 0));

		groundImageRight.setAnimationAction(MoveTo.RIGHT);
		groundImageLeft.setAnimationAction(MoveTo.LEFT);

		stage.addActor(groundImageRight);
		stage.addActor(groundImageLeft);


		// =========================================
		// Create the image clones
		// =========================================
		replayBtn = new CompositeImage(Main.imageHandler.replayButton);
		homeBtn = new CompositeImage(Main.imageHandler.homeButton);
		googleBtn = new CompositeImage(Main.imageHandler.googleButton);
		rateBtn = new CompositeImage(Main.imageHandler.rateButton);
		scoresBtn = new CompositeImage(Main.imageHandler.scoresButton);
		shareBtn = new CompositeImage(Main.imageHandler.shareButton);


		// =========================================
		// Prepare text for stage
		// =========================================	
		Main.imageHandler.countdownText.settingsRelative(GameSettings.getPlayCountdownTitlePosX(), GameSettings.getPlayCountdownTitlePosY());
		Main.imageHandler.currentScoreText.settingsRelative(GameSettings.getPlayScoreTitlePosX(), GameSettings.getPlayScoreTitlePosY());
		Main.imageHandler.gameOverText.settingsRelative(GameSettings.getPlayGameOverTitlePosX(), GameSettings.getPlayGameOverTitlePosY());
		Main.imageHandler.scoreTitleText.settingsRelative(GameSettings.getPlayCurrentScoreSubTitlePosX(), GameSettings.getPlayCurrentScoreSubTitlePosY());
		Main.imageHandler.bestScoreTitleText.settingsRelative(GameSettings.getPlayBestScoreSubTitlePosX(), GameSettings.getPlayBestScoreSubTitlePosY());


		// =========================================
		// Set text offcamera
		// =========================================
		Main.imageHandler.countdownText.setAnimationAction(MoveTo.TOP);
		Main.imageHandler.currentScoreText.setAnimationAction(MoveTo.TOP);
		Main.imageHandler.gameOverText.setAnimationAction(MoveTo.TOP);
		Main.imageHandler.scoreTitleText.setAnimationAction(MoveTo.TOP);
		Main.imageHandler.bestScoreTitleText.setAnimationAction(MoveTo.TOP);


		// =========================================
		// Organise elements into game / game over
		// =========================================
		handleActor(GameSideActors, groundImageLeft);
		handleActor(GameSideActors, groundImageRight);
		handleActor(GameActors, Main.imageHandler.currentScoreText);
		handleActor(CountdownActors, Main.imageHandler.countdownText);
		handleActor(GameOverActors, Main.imageHandler.gameOverText);
		handleActor(GameOverActors, Main.imageHandler.scoreTitleText);
		handleActor(GameOverActors, Main.imageHandler.bestScoreTitleText); 


		// =========================================
		// Add buttons to list
		// =========================================
		buttonList.add(replayBtn);
		buttonList.add(homeBtn);
		buttonList.add(scoresBtn);
		buttonList.add(googleBtn);
		buttonList.add(rateBtn);
		buttonList.add(shareBtn);


		// =========================================
		// Prepare game buttons
		// =========================================
		float listWidth = (buttonList.size() * menuButtonSize) + ((buttonList.size()-1) * screenMargin);
		float startPoint = ((screenWidth - listWidth)/2);

		for(int i = 0; i < buttonList.size(); i++){
			CompositeImage ci = (CompositeImage) buttonList.get(i);
			float posX = startPoint + (menuButtonSize * i) + ( i * screenMargin);
			float posY = GameSettings.getMenuButtonPosY() - (menuButtonSize/2);
			ci.position((int)posX, (int)posY);
			ci.setAnimationAction(MoveTo.BOTTOM);

			// - put buttons in gameover list
			handleActor(GameOverActors, ci);
			stage.addActor(ci);
		}


		// =========================================
		// Button listeners
		// =========================================
		// - Replay Button
		replayBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				Animations.bounce(replayBtn);
				clearActors();
				reset();
				return true;
			}
		});

		// - HomeButton
		homeBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				Animations.bounce(homeBtn);

				actionShow.clear();
				actionHide.clear();
				actionHide.add(GameOverActors);
//                Main.actionResolver.selectSong(stage);//THIS SHOULD BE SOMEWHERE ELSE MOTHEFUCKA
				newScreen = game.menuScreen;
				return true;
			}
		});

		// - Sound Button
		scoresBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				Animations.bounce(scoresBtn);

				actionShow.clear();
				actionHide.clear();
				actionHide.add(GameOverActors);
				newScreen = game.scoresScreen;
				return true;
			}
		});

		// - Rate Button
		rateBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				Animations.bounce(rateBtn);

				Main.actionResolver.rate();
				return true;
			}
		});

		// - Google Button
		googleBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				Animations.bounce(googleBtn);

				Main.actionResolver.connect(true);
				return true;
			}
		});

		// - Share Button
		shareBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				Main.actionResolver.share();
				return true;
			}
		});


		// =========================================
		// Add entities; objects and define hero
		// =========================================
		hero = new Hero(ImageHandler.heroSprite, game);
		explosion = new Explosion(hero);
		heroTrail = new HeroTrail(hero);

	}


	private void handleActor(List list, Actor actor){
		list.add(actor);
	}


	@Override
	public void render(float delta) { 

		// =========================================
		// Set screen bg and colors
		// =========================================
		RenderGDXSettings.settings();


		// =========================================
		// Handle camera
		// =========================================
		camera.update();
		batch.setProjectionMatrix(camera.combined);



		// =========================================
		// Initiate batch
		// =========================================
		batch.begin();
		

		// =========================================
		// BackgroundImage
		// =========================================
		if(GameSettings.useBackgroundImage){
			bgSprite.draw(batch);
		}


		// =========================================
		// Handle animation queue
		// =========================================
		if(queueComplete() && actionsEmpty()) {
            switch (GAMESTATE) {


                // =========================================
                // Run Countdown
                // =========================================
                case COUNTDOWN:

                    // kill ads
                    if (!COUNTDOWNTRIGGERED) {
                        countDown();
                    }
                    break;


                // =========================================
                // Show the game
                // =========================================
                case RUNNING:

                    //move the objects
                    if (!objectDropHandler.hasStarted()) {
                        objectDropHandler.start();
                    }

                    //handle hero
                    hero.update(batch, delta);
                    heroTrail.update(batch, delta);

					// ================================================
					// If the music playing reached the next peak time
					// drop an object in the left side
					// ================================================
					float playbackPos = Musics.selectedSongPosition();
//                    System.out.println("Player Position (seconds): " + playbackPos);
                    // Is there a next peak?
                    if (peakCounter < Main.imageHandler.peaksList.size()) {
//                        float nextPeak = (float)(Main.imageHandler.peaksList.get(peakCounter) - GameSettings.timeOffset);
                        double nextPeak = Main.imageHandler.peaksList.get(peakCounter);
                        // Did the playback reach the next beat timing?
                        if (playbackPos >= nextPeak) {
                            // Drop an object
                            // Did the current playing position pass the next peak?
                            // if it has just passed, then drop one
                            if (playbackPos - nextPeak < 0.1f) objectDropHandler.dropOne(0f);
							peakCounter++;
						}
					}
					//handle obstacles objects
					objectDropHandler.update(batch, hero);
					break;

                // =========================================
                // Pause game
                // =========================================
                case PAUSED:

                    break;

                // =========================================
                // Show game over
                // =========================================
                case GAMEOVER:

                    break;
            }
        }


		if(Hero.isDead){
            Musics.selectedFadeOut(100);
			explosion.update(batch, delta);
		}

		if((hero.getoffScreen() || Musics.finishedPlaying()) && GAMESTATE != Gamestate.GAMEOVER){
            Musics.stopSelected();
            hero.reset();
			objectDropHandler.reset();
			setStage(Gamestate.GAMEOVER);
			Main.actionResolver.googlePlaySubmit(Score.getScore());
			peakCounter = 0;
		}


		// =========================================
		// Close batch, handle actors and stage
		// =========================================
		batch.end();
		stage.act();
		stage.draw();
	}


	@Override
	public void resize(int width, int height) {
	}


	@Override
	public void show() {		

		// =========================================
		// Set all the elements
		// =========================================
		setStage(Gamestate.COUNTDOWN);


		// =========================================
		// Set all the elements to hide
		// =========================================
		actorList = stage.getActors();
		for(int i = 0; i < actorList.size; i++){
			actorHolder = actorList.get(i);
			instantHide(actorHolder);
		}

		// =========================================
		// Set multiple inputprocessors
		// =========================================
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}


	// =========================================
	// Handle stage actors and animations
	// =========================================
	private void setStage(Gamestate gameState){

		actionHide.clear();
		actionShow.clear();

		switch(gameState){
		case COUNTDOWN:
			actionHide.add(GameOverActors);
			actionShow.add(GameSideActors);
			actionShow.add(CountdownActors);
			Main.actionResolver.showAds(false);
			handleInterAd();
			break;
		case RUNNING:
			actionHide.add(CountdownActors);
			actionShow.add(GameActors);
			break;
		case GAMEOVER:
			Score.saveHighScore();
			Main.imageHandler.scoreTitleText.setString(GameSettings.scoreTitle + Score.getScore(), true, false);
			Main.imageHandler.bestScoreTitleText.setString(GameSettings.bestScoreTitle + Score.getHighScore(), true, false);
			actionHide.add(GameActors);
			actionHide.add(GameSideActors);
			actionShow.add(GameOverActors);
			if(!Main.actionResolver.wasInterClosed())Main.actionResolver.showInterAds();
			Main.actionResolver.showAds(true);
			break;
		}

		this.GAMESTATE = gameState;
	}

	private boolean actionsEmpty(){
		if(actionHide.isEmpty() && actionShow.isEmpty())return true;
		return false;
	}

	private boolean queueComplete(){
		if(!actionHide.isEmpty() || !actionShow.isEmpty() || newScreen != null){
			if(!allAnimationsDone())return false;

			if(!actionHide.isEmpty()){
				for (int i = 0; i < actionHide.size(); i++ ) {
					emptyActorList = actionHide.get(i);
					for (int c = 0; c < emptyActorList.size(); c++ ) {
						actorHolder = emptyActorList.get(c);
						hideActors(actorHolder);
					}
					actionHide.remove(i);
				}
			}else{
				for (int i = 0; i < actionShow.size(); i++ ) {
					emptyActorList = actionShow.get(i);
					for (int c = 0; c < emptyActorList.size(); c++ ) {
						actorHolder = emptyActorList.get(c);
						showActors(actorHolder);
					}
					actionShow.remove(i);
				}
			}

			if(actionsEmpty() && allAnimationsDone()){
				game.setScreen(newScreen);
				newScreen = null;
				return false;
			}

			return true;
		}else{
			return true;
		}
	}


	// =========================================
	// Checks actors for anims, ignore game buttons
	// =========================================
	private boolean allAnimationsDone(){
		boolean allDone = true;

		actorList = stage.getActors();
		for(int i = 0; i < actorList.size; i++){
			actorHolder = actorList.get(i);
			if(!Animations.done(actorHolder)){
				allDone = false;
				break;
			}
		}
		return allDone;
	}


	// =========================================
	// Loop through actors and show as needed
	// =========================================
	private void showActors(Actor actor){

		if(actor instanceof CustomImage){
			vector = ((CustomImage)actor).getShowVector();
		}

		if(actor instanceof CompositeImage){
			vector = ((CompositeImage)actor).getShowVector();
		}

		if(actor instanceof CustomTextActor){
			vector = ((CustomTextActor)actor).getShowVector();
		}

        if (actor instanceof CustomTableActor){
            vector = ((CustomTableActor) actor).getShowVector();
        }

		Animations.showAnimation(stage, actor, vector.x, vector.y);
	}


	// =========================================
	// Loop through actors and hide as needed
	// =========================================
	private void hideActors(Actor actor){
		if(actor instanceof CustomImage){
			vector = ((CustomImage)actor).getHideVector();
		}

		if(actor instanceof CompositeImage){
			vector = ((CompositeImage)actor).getHideVector();
		}

		if(actor instanceof CustomTextActor){
			vector = ((CustomTextActor)actor).getHideVector();
		}

        if(actor instanceof CustomTableActor){
            vector = ((CustomTableActor)actor).getHideVector();
        }

		Animations.removeAnimation(actor, vector.x, vector.y);
	}



	// =========================================
	// Loop through actors and hide as needed
	// =========================================
	private void instantHide(Actor actor){
		if(actor instanceof CustomImage){
			vector = ((CustomImage)actor).getHideVector();
			((CustomImage)actor).setPosition(vector.x, vector.y);
		}

		if(actor instanceof CompositeImage){
			vector = ((CompositeImage)actor).getHideVector();
			((CompositeImage)actor).setPosition(vector.x, vector.y);
		}

		if(actor instanceof CustomTextActor){
			vector = ((CustomTextActor)actor).getHideVector();
			((CustomTextActor)actor).setPosition(vector.x, vector.y);
		}

        if(actor instanceof CustomTableActor){
            vector = ((CustomTableActor)actor).getHideVector();
            ((CustomTableActor)actor).setPosition(vector.x, vector.y);
        }
	}

	// =========================================
	// Manager countdown timer
	// =========================================
	private void countDown(){
		if(!COUNTDOWNTRIGGERED && actionsEmpty()){
			if(queueComplete()){
				COUNTDOWNTRIGGERED = true;
				final Timer timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {

                        if (seconds > 0){
                            seconds--;
                        }
                        else if (seconds == 0){
                            Main.imageHandler.countdownText.setString(GameSettings.countdownEnd);
                            seconds--;
                        }
                        //COUNTDOWN COMPLETED - RUN THE GAME!
                        else{
                            Musics.playSelected();
                            Main.imageHandler.countdownText.setString(GameSettings.countdownEnd);
                            setStage(Gamestate.RUNNING);
                            seconds = 0;
                            timer.purge();
                            timer.cancel();
                        }
					}
				}, 500, 500);
			}
		}
	}
	
	private void handleInterAd(){
		
		if(adFrequency == 1 || adFrequency == 0){
			Main.actionResolver.resetInterClosed();
			return;
		}
		
		if(adCount == 1){
			Main.actionResolver.resetInterClosed();
			adCount ++;
		}else if(adCount == adFrequency){
			adCount = 1;
		}else{
			adCount ++;
		}
	}


	@Override
	public void hide() {
		reset();
	}

	@Override
	public void pause() {
		Score.flush();
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
		explosion.dispose();
		heroTrail.dispose();

	}


	@Override
	public boolean keyDown(int keycode) {
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(GAMESTATE == Gamestate.RUNNING)hero.jump(); return false;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}


	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
