package com.robotllama.Screens;

import java.util.ArrayList;
import java.util.List;

import Utils.Animations;
import Utils.RenderGDXSettings;
import Utils.Score;
import Utils.EntityStates.MoveTo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.robotllama.Main;
import com.robotllama.CustomActors.CompositeImage;
import com.robotllama.CustomActors.CustomTextActor;
import com.robotllama.Handlers.ImageHandler;
import com.robotllama.Settings.GameSettings;

public class Scores implements Screen{
	

	// =========================================
	// Button click checkers
	// =========================================
	public boolean playButton = false;
	public boolean homeButton = false;
	public boolean googleButton = false;
	public boolean shareButton = false;
	public boolean rateButton = false;
	public boolean changeScreen = false;
	
	
	// =========================================
	// Screen holder
	// =========================================
	private Screen newScreen;

	
	// =========================================
	// Screen variables
	// =========================================
	private Stage stage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Main game;
	private List<CompositeImage>buttonList = new ArrayList<CompositeImage>();
	private CompositeImage ciHolder;
	private Actor actorHolder;
	private Array<Actor> actorList = new Array<Actor>();
	private Sprite bgSprite = ImageHandler.bgSprite;
	
	
	// =========================================
	// Button variables
	// =========================================
	private CompositeImage homeBtn, playBtn, googleBtn, rateBtn, shareBtn;
	

	// =========================================
	// Text variables
	// =========================================
	private CustomTextActor scoreTitle, bestScore;
	
	
	public Scores(Main game){
		this.game = game;

		
		// =========================================
		// Set up camera, batch and stage
		// =========================================
		camera = new OrthographicCamera(GameSettings.screenWidth, GameSettings.screenHeight);
		camera.position.set(GameSettings.screenWidth / 2, GameSettings.screenHeight/ 2, 0);
		stage = new Stage();
		batch = new SpriteBatch();
		
		
    	// =========================================
		// Create the image clones
		// =========================================
		playBtn = new CompositeImage(Main.imageHandler.playButton);
		googleBtn = new CompositeImage(Main.imageHandler.googleButton);
		rateBtn = new CompositeImage(Main.imageHandler.rateButton);
		homeBtn = new CompositeImage(Main.imageHandler.homeButton);
		shareBtn = new CompositeImage(Main.imageHandler.shareButton);
		
		
		// =========================================
		// Create the text clones
		// =========================================
		scoreTitle = new CustomTextActor(Main.imageHandler.highScoreTitleText);
		bestScore = new CustomTextActor(Main.imageHandler.bestScoreTitleText);
		

		// =========================================
		// Add buttons to list
		// =========================================
		buttonList.add(homeBtn);
		buttonList.add(playBtn);
		buttonList.add(googleBtn);
		buttonList.add(rateBtn);
		buttonList.add(shareBtn);
		
		
		
		// =========================================
		// Set button positions
		// =========================================
		float listWidth = (buttonList.size() * buttonList.get(0).getWidth()) + ((buttonList.size()-1) * GameSettings.getScreenMargin());
		float startPoint = ((GameSettings.screenWidth - listWidth)/2);

		for(int i = 0; i < buttonList.size(); i++){
			float posX = startPoint + (buttonList.get(0).getWidth() * i) + ( i * GameSettings.getScreenMargin());
			float posY = GameSettings.getMenuButtonPosY() - (buttonList.get(i).getHeight()/2);

			CompositeImage ci = buttonList.get(i);
			//stage.addActor(ci);
			ci.position((int)posX, (int)posY);
			ci.setAnimationAction(MoveTo.BOTTOM);
		}
		
		// =========================================
		// Set text positions
		// =========================================
		scoreTitle.settingsRelative(GameSettings.getScoresTitlePosX(), GameSettings.getScoresTitlePosY());
		bestScore.settingsRelative(GameSettings.getScoresSubTitlePosX(), GameSettings.getScoresSubTitlePosY());
		

		// =========================================
		// Add in the elements
		// =========================================
		// - Title
		//stage.addActor(scoreTitle);
		//stage.addActor(bestScore);
		

		// =========================================
		// Handle button listeners
		// =========================================
		// - Play Button
		playBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				playButton = true;
				Animations.bounce(playBtn);
				return true;
			}
		});
		
		// - Home Button
		homeBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				homeButton = true;
				Animations.bounce(homeBtn);
				return true;
			}
		});

		// - Google Button
		googleBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				googleButton = true;
				Animations.bounce(googleBtn);
				return true;
			}
		});
		
		// - Rate Button
		rateBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				rateButton = true;
				Animations.bounce(rateBtn);
				return true;
			}
		});
		
		// - share Button
		shareBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				Main.actionResolver.share();
				return true;
			}
		});
	}
	
	
	@Override
	public void render(float delta) {
		// =========================================
		// Manager the screen colours and background
		// =========================================
		RenderGDXSettings.settings();

		
		// =========================================
		// Manager the camera
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
		// end batch and start stage handling
		// =========================================
		batch.end();
		stage.act();
		stage.draw();

		
		// =========================================
		// Handle button clicks and screen changes
		// =========================================
		// - Play Button
		if(Animations.done(playBtn) && playButton){ // when the animation is finished, go to MainMenu()

			playButton = false;
			newScreen = game.playScreen;
			changeScreen = true;

			clear();
		}

		// - Home Button
		if(Animations.done(homeBtn) && homeButton){ // when the animation is finished, go to MainMenu()

			homeButton = false;
			newScreen = game.menuScreen;
			changeScreen = true;

			clear();
		}
		
		// - Google Button
		if(Animations.done(googleBtn) && googleButton){ // when the animation is finished, go to MainMenu()
			Main.actionResolver.connect(true);
			googleButton = false;
		}
		
		// - rate Button
		if(Animations.done(rateBtn) && rateButton){
			Main.actionResolver.rate();
			rateButton = false;
		}

		
		// =========================================
		// Listen for animations and trigger screen change
		// =========================================
		if(changeScreen)
			moveScreen(newScreen);

	}

	
	// =========================================
	// Trigger screen change when anims done
	// =========================================
	private void moveScreen(Screen screen){

		boolean allDone = true;

		actorList = stage.getActors();
		for(int i = 0; i < actorList.size; i++){
			actorHolder = actorList.get(i);
			if(!Animations.done(actorHolder))allDone = false;
		}

		if(allDone){
			changeScreen = false;
			game.setScreen(newScreen);
		}
	}
	
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Main.actionResolver.showAds(true);
		
		
		// =========================================
		// Set title position
		// =========================================
		scoreTitle.reposition();
		bestScore.setString(GameSettings.bestScoreTitle + Score.getHighScore(), true, true);
		//bestScore.reposition();

		
		// =========================================
		// Handle the screen animations
		// =========================================
		Animations.slideInFromTop(stage, scoreTitle, scoreTitle.getShowVector());
		Animations.slideInFromTop(stage, bestScore, bestScore.getShowVector());

		//anim buttons from bottom
		for(int i = 0; i < buttonList.size(); i++){
			ciHolder = buttonList.get(i);
			Animations.slideInFromBottom(stage, ciHolder);
		}

		
		// =========================================
		// Set stage as input processor
		// =========================================
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		stage.clear();
	}
	
	private void clear(){
		for(Actor actor : stage.getActors()){
			if(actor instanceof CustomTextActor){
				Animations.slideOutViaTop(actor);
			}else{
				Animations.slideOutViaBottom(actor);
			}
		}
	}

	@Override
	public void pause() {
		Score.flush();
	}

	@Override
	public void resume() {
	}
	
	
	// =========================================
	// Clean up
	// =========================================
	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
	}
}
