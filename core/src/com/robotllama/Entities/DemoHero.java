package com.robotllama.Entities;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.robotllama.Settings.GameSettings;

public class DemoHero implements Disposable{


	// =========================================
	// final values
	// =========================================
	static final float groundSize = GameSettings.getGroundSize();
	static final float screenWidth = GameSettings.screenWidth;
	static final float screenHeight = GameSettings.screenHeight;
	static final float jumpHeight = GameSettings.getScreenHeightPos(.1f);
	static final float pointPosition = GameSettings.pointPosition;
	static float heroPosition;
	static final float size = GameSettings.getHeroSize();
	static final float yHandler = size * 2f;
	public static float speed = GameSettings.getHeroSpeed()/2;
	public static final Color heroColor = GameSettings.heroColor;
	private static float leftEdge, rightEdge;



	// =========================================
	// Hero State
	// =========================================
	enum Herostate{
		LEFT,
		RIGHT
	}

	enum Movestate{
		SHOW,
		HIDE,
		NORMAL
	}


	// =========================================
	// Hero Variables
	// =========================================
	Texture texture, pulse;
	public float x, y, startY, startX, endX;
	boolean JUMP = false;
	float jumpY;
	float deadcount = 1;
	float distanceToTravel;
	float currentTravel;
	float perc1;
	float perc2;
	float floorDistance;
	float percentage;
	Sprite sprite;
	float width, height;
	Herostate heroState = Herostate.LEFT;
	float jumpForce = speed;
	float offScreenY = screenHeight;
	Timer timer;
	boolean PAUSE = false;
	public static boolean OFFSCREEN = true, HIDE = true;
	BoxTrail bigTrail, smallTrail1, smallTrail2;
	Movestate moveState = Movestate.HIDE;
	float rotationOffSet = 0;


	// =========================================
	// Initiate Hero
	// =========================================
	public DemoHero(Sprite sprite, float x, float y, float endX){
		this.sprite = new Sprite(sprite);
		
		//this.sprite.setSize(size, size);
		width = sprite.getWidth();
		height = sprite.getHeight();		
		
		this.sprite.rotate(rotationOffSet);
		this.x = x;
		this.startX = x;
		this.endX = endX + width;
		this.y = screenHeight;
		this.startY = y;
		if(!GameSettings.useHeroImage)this.sprite.setColor(heroColor);
		this.sprite.setOriginCenter();
		leftEdge = x;
		rightEdge = endX - width;
		bigTrail = new BoxTrail(this.sprite, size, false);
		smallTrail1 = new BoxTrail(this.sprite, size/2, true);
		smallTrail2 = new BoxTrail(this.sprite, size/2, true);

		distanceToTravel = rightEdge - leftEdge;
		timer = new Timer();
		jump();
	}
	
	private void reset(){
		y = screenHeight;
		x = startX;
		sprite.setRotation(0);
		OFFSCREEN = true;
	}

	public void pause(){
		PAUSE = true;
	}

	public void start(){
		moveState = Movestate.SHOW;
		PAUSE = false;
	}
	
	public void hide(){
		moveState = Movestate.HIDE;
		clear();
	}
	
	public void clear(){
		bigTrail.pause();
		smallTrail1.pause();
		smallTrail2.pause();
	}
	
	private void jump(){
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if(!JUMP && moveState == Movestate.NORMAL && !PAUSE){
					JUMP = true;
					jumpForce = speed;
					bigTrail.resume();
					smallTrail1.resume();
					smallTrail2.resume();
				}
			}
		}, 500, 700); 
	}

	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}

	public void update(SpriteBatch batch, float delta){

		switch(moveState){
		case HIDE:
			y += height/5;
			if(y >= screenHeight){
				reset();
			}
			break;
		case SHOW:
			y -= height/5;
			if(y <= startY){
				y = startY;
				OFFSCREEN = false;
				moveState = Movestate.NORMAL;
			}
			break;
		case NORMAL:
			if(JUMP){
				y = startY + (jumpHeight * (float)Math.sin(Math.toRadians(180 * getPercentage())));
				switch(heroState){
				case LEFT:
					sprite.setRotation(360 - (360 * getPercentage()) + rotationOffSet);
					if(x >= rightEdge){
						x = rightEdge;
						heroState = Herostate.RIGHT;
						JUMP = false;
						sprite.setRotation(rotationOffSet);
					}
					break;
				case RIGHT:
					sprite.setRotation(360 * getPercentage() + rotationOffSet);
					if(x <= leftEdge){
						x = leftEdge;
						heroState = Herostate.LEFT;
						JUMP = false;
						sprite.setRotation(360 + rotationOffSet);
					}
					break;	
				}
			}else{
				bigTrail.pause();
				smallTrail1.pause();
				smallTrail2.pause();
			}
			break;
		}
		
		bigTrail.update(batch, delta);
		smallTrail1.update(batch, delta);
		smallTrail2.update(batch, delta);
		
		sprite.setPosition(x, y);
		sprite.draw(batch);
	}

	private float getPercentage(){
		switch(heroState){
		case LEFT:
			x += speed;
			currentTravel = x - leftEdge;
			break;
		case RIGHT:
			x -= speed;
			currentTravel = rightEdge - x;
			break;
		}
		return (currentTravel/distanceToTravel < 0)? 0 : currentTravel/distanceToTravel;
	}

	@Override
	public void dispose() {
		bigTrail.dispose();
		smallTrail1.dispose();
		smallTrail2.dispose();
		timer.cancel();
		timer.purge();
	}
}
