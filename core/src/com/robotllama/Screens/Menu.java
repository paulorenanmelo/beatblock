package com.robotllama.Screens;

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
import com.robotllama.CustomActors.CompositeImage;
import com.robotllama.CustomActors.CustomTableActor;
import com.robotllama.CustomActors.CustomTextActor;
import com.robotllama.Entities.DemoHero;
import com.robotllama.Handlers.ImageHandler;
import com.robotllama.Main;
import com.robotllama.Settings.GameSettings;

import java.util.ArrayList;
import java.util.List;

import Utils.Animations;
import Utils.EntityStates.MoveTo;
import Utils.RenderGDXSettings;
import Utils.SongAnalysis;

public class Menu implements Screen{
	
	
	// =========================================
	// Button click checkers
	// =========================================
	public boolean playButton 	= false;
	public boolean scoresButton = false;
	public boolean googleButton = false;
	public boolean shareButton 	= false;
	public boolean rateButton 	= false;
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
	private DemoHero demoHero;
	private Sprite bgSprite = ImageHandler.bgSprite;

	
	// =========================================
	// Button variables
	// =========================================
	private CompositeImage soundBtn, playBtn, scoresBtn, googleBtn, rateBtn;

    // Song List
    private CustomTextActor[] songList;


    public Menu(Main game){
		this.game = game;

		
		// =========================================
		// Set up camera, batch and stage
		// =========================================
		camera = new OrthographicCamera(GameSettings.screenWidth, GameSettings.screenHeight);
		camera.position.set(GameSettings.screenWidth / 2, GameSettings.screenHeight/ 2, 0);
		batch = new SpriteBatch();
		stage = new Stage();
		
		
    	// =========================================
		// Create the image clones
		// =========================================
		playBtn 	= new CompositeImage(Main.imageHandler.playButton);
		scoresBtn 	= new CompositeImage(Main.imageHandler.scoresButton);
		googleBtn 	= new CompositeImage(Main.imageHandler.googleButton);
		rateBtn 	= new CompositeImage(Main.imageHandler.rateButton);
		soundBtn 	= new CompositeImage(Main.imageHandler.soundButtonImage);


		// =========================================
		// Add buttons to list
		// =========================================
		buttonList.add(playBtn);
		buttonList.add(scoresBtn);
		buttonList.add(googleBtn);
		buttonList.add(rateBtn);
		buttonList.add(soundBtn);


		// =========================================
		// Set title positions
		// =========================================
		Main.imageHandler.gameTitleText.settingsRelative(GameSettings.getMenuTitlePosX(), GameSettings.getMenuTitlePosY());
		Main.imageHandler.subTitleText.settingsRelative(GameSettings.getMenuSubTitlePosX(), GameSettings.getMenuSubTitlePosY());

//		Main.imageHandler.songListTable = new CustomTableActor(GameSettings.getDefaultSkin());
//		Main.imageHandler.songListTable.setPosition(GameSettings.getSongListTitlePosX(), GameSettings.getSongListTitlePosY());
//		Main.imageHandler.songListTable.settingsRelative(GameSettings.getSongListTitlePosX(), GameSettings.getSongListTitlePosY());

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
			ci.position(posX, posY);
			ci.setAnimationAction(MoveTo.BOTTOM);
		}

		// =========================================
		// Song list
		// =========================================

//        Main.actionResolver.selectSong(stage);

        // =========================================
        // Song list listeners
        // =========================================



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
		
		// - Sound Button
		soundBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				Animations.bounce(soundBtn);
				soundBtn.isTouched();
				GameSettings.toggleSound();
				return true;
			}
		});

		// - Scores Button
		scoresBtn.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
				scoresButton = true;
				Animations.bounce(scoresBtn);
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

		
		// =========================================
		// Create demo hero
		// =========================================
		float startX = Main.imageHandler.gameTitleText.getOnCamera().x;
		float startY = Main.imageHandler.gameTitleText.getOnCamera().y;
		float endX = startX + Main.imageHandler.gameTitleText.getWidth();
		demoHero = new DemoHero(Main.imageHandler.heroSprite, startX, startY, endX);
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
		
		
		if(Animations.done(Main.imageHandler.gameTitleText) || !demoHero.OFFSCREEN){
			demoHero.update(batch, delta);
		}
		// =========================================
		// end batch and start stage handling
		// =========================================
		batch.end();
		stage.act();
        //Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();

		
		// =========================================
		// Handle button clicks and screen changes
		// =========================================
		// - Play Button
		if(Animations.done(playBtn) && playButton){

			demoHero.pause();
			playButton = false;
			newScreen = game.playScreen;
			changeScreen = true;

			clear();
		}
		
		// - Scores Button
		if(Animations.done(scoresBtn) && scoresButton){

			demoHero.pause();
			scoresButton = false;
			newScreen = game.scoresScreen;
			changeScreen = true;

			clear();
		}

		
		// - Google Button
		if(Animations.done(googleBtn) && googleButton){
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
		
		if(!demoHero.OFFSCREEN)allDone = false;

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
		demoHero.start();
		
		// =========================================
		// Set title position
		// =========================================
		Main.imageHandler.gameTitleText.reposition();
		Main.imageHandler.subTitleText.reposition();


		// =========================================
		// Set song list position
		// =========================================
        Main.imageHandler.songListTable = new CustomTableActor(GameSettings.getDefaultSkin());
        Main.imageHandler.songListTable.setPosition(GameSettings.getSongListTitlePosX(), GameSettings.getSongListTitlePosY());
        Main.imageHandler.songListTable.settingsRelative(GameSettings.getSongListTitlePosX(), GameSettings.getSongListTitlePosY());
        Main.actionResolver.selectSong(stage);
//        Main.imageHandler.songListTable.reposition();


        // =========================================
        // Handle the screen animations
        // =========================================
        Animations.slideInFromTop(stage, Main.imageHandler.gameTitleText, Main.imageHandler.gameTitleText.getShowVector());
        Animations.slideInFromTop(stage, Main.imageHandler.subTitleText, Main.imageHandler.subTitleText.getShowVector());
        Animations.slideInFromTop(stage, Main.imageHandler.songListTable, Main.imageHandler.songListTable.getShowVector());

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
        SongAnalysis.dispose();
        stage.clear();
	}
	
	private void clear(){
		demoHero.hide();
		actorList = stage.getActors();
		for(int i = 0; i < actorList.size; i++){
			actorHolder = actorList.get(i);
			if(actorHolder instanceof CustomTextActor){
				Animations.slideOutViaTop(actorHolder);
			}else{
				Animations.slideOutViaBottom(actorHolder);
			}
		}
	}

	
	@Override
	public void pause() {
		demoHero.pause();
	}

	
	@Override
	public void resume() {
		demoHero.start();
	}

	//It's only called when the application is closed (probably)
	@Override
	public void dispose() {
        batch.dispose();
        stage.dispose();
        demoHero.dispose();
    }
}
