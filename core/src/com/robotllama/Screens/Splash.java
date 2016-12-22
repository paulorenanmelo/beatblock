package com.robotllama.Screens;

import Utils.Animations;
import Utils.ImageScaling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.robotllama.Main;
import com.robotllama.Handlers.Assets;
import com.robotllama.Settings.GameSettings;

public class Splash implements Screen{
	
	// =========================================
	// Screen variable
	// =========================================
    private Texture texture;
   // private Sprite logo = new Sprite(new Texture(Gdx.files.internal("images/" + GameSettings.logoName)));
   // private Image logoImage;
    private Stage stage = new Stage();
    private Main game;
    private Color backgroundColor;
    private boolean LOADED = false;
    
    
    public Splash(Main game){
    	this.game = game;
    	
    	
    	// =========================================
    	// Handle the game logo
    	// =========================================
    	//logo.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	//logoImage = new Image(logo);
    	/*
    	float logoWidth = GameSettings.getLogoSize();
    	logoImage.setSize(logoWidth, ImageScaling.scaleMe(logoWidth, logo));
    	logoImage.setPosition(
    			(GameSettings.getLogoPosX()) - (logoImage.getWidth() / 2), 
    			(GameSettings.getLogoPosY()) - (logoImage.getHeight() / 2));*/
    	//stage.addActor(logoImage);
    	backgroundColor = GameSettings.splashBackgroundColor;
    }
    
	@Override
	public void render(float delta) {
    	
    	// =========================================
    	// Handle background colour
    	// =========================================
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        

    	// =========================================
    	// Handle stage
    	// =========================================
        stage.act();
        stage.draw();
        // check all files are loaded
        if(Assets.update()){
        	// check all animations are done
			if(true){//if(Animations.done(logoImage)){
            	if(!LOADED){
            		game.registerStuff();
            		// backup to stop repeats
            		LOADED = true;
            	}else{
            		Main.actionResolver.connect(false);
            		game.setScreen(game.menuScreen);
            	}
            }
        }
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		
		Main.actionResolver.showAds(false);
		
    	// =========================================
    	// Animation splash screen
    	// =========================================
        //Animations.splashLogo(logoImage);
        
		
    	// =========================================
    	// Handle asset loading
    	// =========================================
        Assets.manager.clear(); 
        Assets.queueLoading(); 
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
    
	
	// =========================================
	// Cleaning up
	// =========================================
	@Override
	public void dispose() {
        texture.dispose();
        stage.dispose();
	}
}
