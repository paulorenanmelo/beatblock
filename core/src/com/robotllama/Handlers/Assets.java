package com.robotllama.Handlers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.robotllama.Main;
import com.robotllama.Settings.GameSettings;

//import ddf.minim.AudioPlayer;
//import ddf.minim.Minim;

public class Assets{
	
	public static AssetManager manager = new AssetManager();

	
	// =========================================
	// Queue and start loading
	// =========================================
    public static void queueLoading() {
    	
    	TextureParameter param = new TextureParameter();
    	param.minFilter = TextureFilter.Linear;
    	param.magFilter = TextureFilter.Linear;
    	
    	//load sounds
        manager.load("sounds/click.wav", Sound.class);
        manager.load("sounds/hit.wav", Sound.class);
        manager.load("sounds/jump.wav", Sound.class);
        manager.load("sounds/point.wav", Sound.class);

        //load musics
        manager.load("musics/AviciiWakeMeUp.mp3", Music.class);
//        manager.load("musics/AviciiWakeMeUp.wav", Music.class);
        manager.load("musics/groove.mp3", Music.class);
//        try {
//            manager.load("musics/AviciiWakeMeUp.wav", File.class);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        //manager.load("musics/AviciiWakeMeUp.mp3", Sound.class);
        //minim = new Minim(Musics.class);
        //song = minim.loadFile("musics/AviciiWakeMeUp.mp3", 5048);

        if(GameSettings.useBackgroundImage){
        	manager.load("images/bg.png", Texture.class, param);
        }
        
        if(GameSettings.useWallImage){
        	manager.load("images/wall.png", Texture.class, param);
        }

        if(GameSettings.useObjectImage){
        	manager.load("images/object.png", Texture.class, param);
        }
        
        if(GameSettings.useHeroImage){
        	manager.load("images/hero.png", Texture.class, param);
        }

        manager.load("images/square.png", Texture.class, param);
        
        Main.getActionResolver().loadButtons(GameSettings.getMenuButtonSize());
    }

    
	// =========================================
	// Smooth game loading
	// =========================================
    public static boolean update() {
    	return manager.update();
    }
}
