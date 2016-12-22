package com.robotllama.Entities;

import java.util.List;
import java.util.Random;

import Utils.ImageScaling;
import Utils.ImageScaling.ClockWise;
import Utils.Sounds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.robotllama.Main;
import com.robotllama.Entities.HeroTrail.TrailPosition;
import com.robotllama.Settings.GameSettings;

public class Hero{
	

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
	public static float speed = GameSettings.getHeroSpeed();
	public static final Color heroColor = GameSettings.heroColor;
	private static float leftEdge = groundSize;
	private static float rightEdge = screenWidth - (groundSize + size);

	
	// =========================================
	// Hero State
	// =========================================
	enum Herostate{
		LEFT( groundSize ),
		RIGHT( getRightEdge());
		
		float value;
		
		Herostate(float value){
			this.value = value;
		}
		
		public float getValue(){
			return value;
		}
	}
	


	// =========================================
	// Hero Variables
	// =========================================
	Texture texture, pulse;
	public float x, y;
	boolean JUMP = false;
	int dir = 0;
	double radians;
	float jumpY;
	float deadcount = 1;
	float distanceToTravel = rightEdge - leftEdge;
	float currentTravel;
	float perc1;
	float perc2;
	float floorDistance;
	float percentage;
	int num;
	int dirC = 0;
	Side side;
	boolean FLIPPED = false;
	public static boolean isDead = false;
	boolean offScreen = false;
	boolean SPUN = false;
	Sprite sprite, flipSprite;
	static float width, height;
	Herostate heroState = Herostate.RIGHT;
	float rotationOffSet = 0;
	
	
	// =========================================
	// Point Variables
	// =========================================
	List<Float> pointPosX;
	int pointMax;


	// =========================================
	// Game Variables
	// =========================================
	Main game;
	Random rand = new Random();
 
	
	// =========================================
	// Hero game side
	// =========================================
	private enum Side{
		LEFT, RIGHT;
	}

	
	// =========================================
	// Initiate Hero
	// =========================================
	public Hero(Sprite sprite, Main game){
		this.sprite = new Sprite(sprite);
		this.sprite = ImageScaling.rotateSprite(this.sprite, ClockWise.ANTICLOCKWISE, 90);
		width = this.sprite.getWidth();
		height = this.sprite.getHeight();
		
		rightEdge = screenWidth - (groundSize + width);
		heroPosition = pointPosition - height;
		this.heroState = Herostate.RIGHT;
		this.x = rightEdge;
		this.y = heroPosition;
		this.sprite.setPosition(x, y);
		this.sprite.setOriginCenter();
		this.game = game;
		
		if(!GameSettings.useHeroImage)setColor();
	}
	
	private static float getRightEdge(){
		return screenWidth - (groundSize + width);
	}
	
	private void setColor(){
		sprite.setColor(heroColor);
	}

	
	// =========================================
	// Kill Hero
	// =========================================
	public void dead(Herostate heroState){
		this.heroState = heroState;
		if(!isDead){
			isDead = true;
			Sounds.hit();
		}
	}
	
	// =========================================
	// Reset Hero
	// =========================================
	public void reset(){
		isDead = false;

		if(heroState == Herostate.LEFT)flip(true);
		FLIPPED = false;
		
		deadcount = 1;
		offScreen = false;
		heroState = Herostate.RIGHT;
		SPUN = false;
		JUMP = false;
		
		x = rightEdge;
		y = pointPosition - height;
		sprite.setPosition(x, y);
		sprite.setRotation(0);
		
		Explosion.reset();
	}
	
	
	// =========================================
	// Get hero bounds for collisions
	// =========================================
	public Rectangle getRect(){
		return this.sprite.getBoundingRectangle();
	}

	public Texture getTexture(){
		return this.texture;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public int getDirection(){
		return this.dir;
	}
	
	
	// =========================================
	// Get height
	// =========================================
	public float getHeight(){
		return height;
	}
	
	
	// =========================================
	// Get width
	// =========================================
	public float getWidth(){
		return width;
	}
	
	// =========================================
	// Get left or right
	// =========================================
	public boolean onLeft(){
		if(side == Side.LEFT)return true;
		return false;
	}
	
	
	// =========================================
	// Draw the hero with updates
	// =========================================
	public boolean update(SpriteBatch sb, float delta){

		//handle death
		if(isDead){
			
			y -= getHeight()/4;
			
			deadcount += .05;
			switch(heroState){
			case LEFT:
				x += speed/deadcount;
				break;
			case RIGHT:
				x -= speed/deadcount;
				break;
			}
			
			sprite.setRotation(sprite.getRotation() + 15);
			
			if(y > screenHeight || y < 0 - height){
				if(Explosion.explodeDone){
					offScreen = true;
				}
			}
		}else{		    
			if(JUMP){
				HeroTrail.trailDraw = false;
				percentage = getPercentage();
				y = heroPosition + (jumpHeight * (float)Math.sin(Math.toRadians(180 * percentage)));

				if(percentage > 0.5){
					if(!FLIPPED){
						flip(true);
						FLIPPED = true;
					}
				}else{
					flip(false);
					FLIPPED = false;
				}
				
				switch(heroState){
				case LEFT:
					sprite.setRotation(180 - (180 * getPercentage()) + rotationOffSet);
					if(x >= rightEdge){
						x = rightEdge;
						heroState = Herostate.RIGHT;
						JUMP = false;
						y = heroPosition;
						sprite.setRotation(0 + rotationOffSet);
					}
					break;
				case RIGHT:
					sprite.setRotation(180 * getPercentage() + rotationOffSet);
					if(x <= leftEdge){
						x = leftEdge;
						heroState = Herostate.LEFT;
						JUMP = false;
						y = heroPosition;
						sprite.setRotation(180 + rotationOffSet);
					}
					break;
				}
			}else{
				switch(heroState){
				case LEFT:
					HeroTrail.setPosition(TrailPosition.LEFT);
					break;
				case RIGHT:
					HeroTrail.setPosition(TrailPosition.RIGHT);
					break;
				}
			}
		}
		
		//draw hero on stage
		sprite.setPosition(x, y);
		sprite.draw(sb);
		return false;
	}
	
	public boolean getoffScreen(){
		return offScreen;
	}
	
	
	// =========================================
	// Calculate distance to floor
	// =========================================
	public void jump(){

		if(!JUMP){
			JUMP = true;
			Sounds.jump();
		}
	}
	
	private void flip(boolean b){
		if(b){
			sprite.flip(true, false);
			return;
		}else{
			sprite.flip(false, false);
			return;
		}
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
}
