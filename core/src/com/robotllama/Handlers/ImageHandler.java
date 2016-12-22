package com.robotllama.Handlers;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.robotllama.CustomActors.CompositeImage;
import com.robotllama.CustomActors.CustomTableActor;
import com.robotllama.CustomActors.CustomTextActor;
import com.robotllama.Main;
import com.robotllama.Settings.GameSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.ImageScaling;
import Utils.ImageScaling.ClockWise;

public class ImageHandler implements Disposable{

	// =========================================
	// Holder for the pulses
	// =========================================
	public List<Sprite> pulseList = new ArrayList<Sprite>();


	// =========================================
	// Set texture variables
	// =========================================
	public static Texture hero, pulse, object, bg, ground, background, leftWall, wallTexture, title, particle;
	public static Sprite sound, mute, replay, home, score, google, rate, jump, play, share, buttonBase, groundSpriteLeft, groundSpriteRight;

	
	// =========================================
	// Set CompositeImage variables
	// =========================================
	public CompositeImage
	scoresButton, playButton, homeButton, replayButton, googleButton, rateButton, soundButtonImage, shareButton;


	// =========================================
	// Set CustomActor variables
	// =========================================
	public CustomTextActor gameTitleText, subTitleText, scoreTitleText, bestScoreTitleText, highScoreTitleText, countdownText, gameOverText, currentScoreText;


    // =========================================
    // Set Song List variables
    // =========================================
    public CustomTableActor songListTable;
    public LabelStyle style;
    public Label labelHolder;
	public FileHandle selectedSong, currentPlayingFile;
    public AsyncExecutor songAnalyzer;
    public Music currentPlaying;
	public List<Float> peaksList;

    // =========================================
	// Set Image variables
	// =========================================
	public Image bgImage, jumpLeftButtonImage, jumpRightButtonImage;


	// =========================================
	// Set Sprite variables
	// =========================================
	public static Sprite heroSprite;
	public static Sprite objectSpriteLeft, objectSpriteRight;
	public static Sprite trailSprite;
	public static Sprite bgSprite;
	public static Sprite particleSprite;
	public static Sprite leftWallSprite, rightWallSprite, wallSprite, groundSprite;
	private Texture groundTexture;


	// =========================================
	// Set TextureAtlas variables
	// =========================================
	private static TextureAtlas buttonAtlas;


	// =========================================
	// Set Image variables
	// =========================================
	private AssetManager assets;
	private Main game;


	// =========================================
	// Button textures from android
	// =========================================
	Map<String, Texture> buttonTextures = new HashMap<String, Texture>();
	
	
	public ImageHandler(Main main){
		this.game = main;
		this.assets = Assets.manager;
		

		// =========================================
		// Load button textures
		// =========================================
		buttonTextures = Main.actionResolver.getButtonTextures();

		
		// =========================================
		// Load game entities
		// =========================================
		loadTextures();
		loadTextActors();

	}


	private void loadTextActors(){

		// =========================================
		// Create all Custom Text Actors
		// =========================================
		gameTitleText 		= new CustomTextActor(GameSettings.getMainFont(), Main.actionResolver.getAppName());
		subTitleText 		= new CustomTextActor(GameSettings.getSubFont(), GameSettings.subTitleText);
		countdownText 		= new CustomTextActor(GameSettings.getMainFont(), GameSettings.countdownStart);
		currentScoreText 	= new CustomTextActor(GameSettings.getSubFont(), "0");
		scoreTitleText 		= new CustomTextActor(GameSettings.getSubFont(), GameSettings.scoreTitle);
		bestScoreTitleText 	= new CustomTextActor(GameSettings.getSubFont(), GameSettings.bestScoreTitle);
		highScoreTitleText 	= new CustomTextActor(GameSettings.getMainFont(), GameSettings.highScoresTitle);
		gameOverText 		= new CustomTextActor(GameSettings.getMainFont(), GameSettings.gameOverTitle);
	}


	public void loadTextures(){


		// =========================================
		// Get button textures
		// =========================================
		sound 	= makeSprite(buttonTextures.get("sound"));
		mute 	= makeSprite(buttonTextures.get("mute"));
		replay 	= makeSprite(buttonTextures.get("replay"));
		home 	= makeSprite(buttonTextures.get("home"));
		score 	= makeSprite(buttonTextures.get("score"));
		google 	= makeSprite(buttonTextures.get("google"));
		rate 	= makeSprite(buttonTextures.get("star"));
		play 	= makeSprite(buttonTextures.get("play"));
		share 	= makeSprite(buttonTextures.get("share"));
		
		// =========================================
		// Create buttons
		// =========================================
		soundButtonImage = new CompositeImage(Arrays.asList(sound, mute), GameSettings.getMenuButtonSize());
		playButton 		= new CompositeImage(Arrays.asList(play), GameSettings.getMenuButtonSize());
		replayButton 	= new CompositeImage(Arrays.asList(replay), GameSettings.getMenuButtonSize());
		homeButton 		= new CompositeImage(Arrays.asList(home), GameSettings.getMenuButtonSize());
		googleButton 	= new CompositeImage(Arrays.asList(google), GameSettings.getMenuButtonSize());
		rateButton 		= new CompositeImage(Arrays.asList(rate), GameSettings.getMenuButtonSize());
		scoresButton 	= new CompositeImage(Arrays.asList(score), GameSettings.getMenuButtonSize());
		shareButton 	= new CompositeImage(Arrays.asList(share), GameSettings.getMenuButtonSize());



		// =========================================
		// Create hero sprite
		// =========================================
		if(GameSettings.useHeroImage){
			heroSprite = new Sprite(assets.get("images/hero.png", Texture.class));
		}else{
			heroSprite = new Sprite(assets.get("images/square.png", Texture.class));
		}
		ImageScaling.scale(heroSprite, GameSettings.getHeroSize());
			

		// =========================================
		// Create objects
		// =========================================
		if(GameSettings.useObjectImage){
			objectSpriteRight = new Sprite(assets.get("images/object.png", Texture.class));
			objectSpriteRight = ImageScaling.rotateSprite(objectSpriteRight, ClockWise.ANTICLOCKWISE, 90);
			objectSpriteLeft = new Sprite(objectSpriteRight);
			objectSpriteLeft = ImageScaling.rotateSprite(objectSpriteLeft, ClockWise.ANTICLOCKWISE, 180); 
		}else{
			objectSpriteRight = new Sprite(assets.get("images/square.png", Texture.class));
			objectSpriteLeft = new Sprite(objectSpriteRight);
			objectSpriteLeft.flip(true, false);
		}
		ImageScaling.scale(objectSpriteRight, GameSettings.getObjectSize());
		ImageScaling.scale(objectSpriteLeft, GameSettings.getObjectSize());
		

		// =========================================
		// Create background image sprite
		// =========================================
		if(GameSettings.useBackgroundImage){
			bg = assets.get("images/bg.png", Texture.class);
			bgSprite = new Sprite(bg);
			bgSprite.setSize(GameSettings.screenWidth, GameSettings.screenHeight);
		}


		// =========================================
		// Create Ground
		// =========================================
		if(!GameSettings.useWallImage){
			Texture groundTex = createCube((int)GameSettings.getGroundSize(), (int)(Math.ceil(GameSettings.screenHeight)), GameSettings.groundColor);

			groundSpriteLeft = new Sprite(groundTex);
			groundSpriteLeft.setOriginCenter();
			groundSpriteLeft.setRotation(180);

			groundSpriteRight = new Sprite(groundSpriteLeft);
			groundSpriteRight.setOriginCenter();
			groundSpriteRight.setRotation(0);
		}else{
			groundTexture = assets.get("images/wall.png", Texture.class);
			groundTexture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
			int count = (int) (GameSettings.screenHeight / GameSettings.getGroundSize()) + 1;
			groundSprite = new Sprite(
					groundTexture, 
					0, 0, 
					groundTexture.getHeight() * count, 
					groundTexture.getHeight());
			groundSprite.setSize(
					GameSettings.screenHeight,
					GameSettings.getGroundSize());
			groundSprite = ImageScaling.rotateSprite(groundSprite, ClockWise.ANTICLOCKWISE, 90);
			groundSpriteRight = new Sprite(groundSprite);
			groundSpriteLeft = new Sprite(groundSprite);
			groundSpriteLeft = ImageScaling.rotateSprite(groundSpriteLeft, ClockWise.CLOCKWISE, 180);
		}
		

		// =========================================
		// Create particle sprite
		// =========================================
		particleSprite = new Sprite(assets.get("images/square.png", Texture.class));
	}


	// =========================================
	// Get texture based on button handling type
	// =========================================
	Sprite makeSprite(Object obj){
		if(obj instanceof String){
			return buttonAtlas.createSprite((String)obj, buttonAtlas.findRegion((String)obj).index);
		}else{
			return new Sprite((Texture)obj);
		}
	}


	// =========================================
	// Create button
	// =========================================
	Image makeButtonImage(Sprite sprite, float size, boolean rotate){
		Image image = new Image(sprite);
		image.setSize(size, size);
		if(rotate){
			image.setOrigin(image.getWidth()/2, image.getHeight()/2);
			image.rotateBy(180);
		}
		return image;
	}


	// =========================================
	// Scale sprite
	// =========================================
	public static void scaleSprite(Sprite sprite, float size){
		sprite.setSize(size, ImageScaling.scaleMe(size, sprite));
	}


	// =========================================
	// Object abd hero
	// =========================================
	public Texture createCube(int size, Color color){
		return createCube(size, size, color);
	}
	
	public Texture createCube(int width, int height, Color color){
		//create pulse
		Pixmap cube = new Pixmap(width, height, Format.RGBA8888);
		cube.setColor(color);

		cube.fillRectangle(0, 0, width, height);
		Texture tex = new Texture(cube);
		cube.dispose();
		return tex;
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		if(hero != null)hero.dispose();
		if(pulse != null)pulse.dispose();
		if(object != null)object.dispose(); 
		if(bg != null)bg.dispose();
		if(ground != null)ground.dispose();
		if(background != null)background.dispose(); 
		if(title != null)title.dispose();
	}
}
