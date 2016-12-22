package com.robotllama.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Utils.Score;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.robotllama.Handlers.ImageHandler;
import com.robotllama.Settings.GameSettings;

public class Objects {
	
	// =========================================
	// Object Variables
	// =========================================
	float x, y;
	Sprite sprite;
	int pulseCount = 0;
	boolean pulse = true;
	boolean REMOVE = false;
	boolean CHILD = false;
	boolean BIRTHED = false;
	boolean POINT = false;
	boolean POINTGIVEN = false;
	float travelled;
	final float height, width;
	static final float screenHeight = GameSettings.screenHeight;
	public static float gravity = GameSettings.getGravity();
	static final float pointPosition = GameSettings.pointPosition - GameSettings.getHeroSize();
	static final float leftPosition = GameSettings.getGroundSize();
	static final float objectSize = GameSettings.getObjectSize();
	static final float rightPosition = GameSettings.screenWidth - (GameSettings.getGroundSize() + GameSettings.getObjectSize());
	List<Sprite> sprites = new ArrayList<Sprite>();
	Random rand = new Random();
	Sprite spriteHolder;
	Rectangle rec = new Rectangle();
	Side side;
	
	// =========================================
	// Object posX enum
	// =========================================
	enum Side{
		LEFT ( leftPosition ),
		RIGHT ( rightPosition );
		
		private float posX;
		
		Side(float posX){
			this.posX = posX;
		}
		
		public float getPosX(){
			return posX;
		}
	}	

	
	// =========================================
	// Initiate object
	// =========================================
	public Objects(Side side, float offSet){
		
		this.x = side.getPosX();
		this.y = screenHeight + offSet;
		this.side = side;
		
		
		if(this.side == Side.LEFT){
			this.sprite = new Sprite(ImageHandler.objectSpriteLeft);
		}else{
			this.sprite = new Sprite(ImageHandler.objectSpriteRight);
		}

		/* Future Update 
		for(int i = 0; i < rand.nextInt(4-2)+2; i++){
			Sprite spr = new Sprite(ImageHandler.objectSprite);
			float rSize = rand.nextFloat() * (objectSize - objectSize/3) + objectSize/3;
			spr.setSize(rSize, rSize);
			spr.setOriginCenter();
			spr.setU(objectSize * rand.nextFloat());
			spr.setU2(objectSize * rand.nextFloat());
			spr.setPosition(x + spr.getU(),  y + spr.getU());
			spr.setRotation(randomRotation());
			sprites.add(spr);
		}
		*/
		this.travelled = 0;
		this.height = objectSize;
		this.width = objectSize;
		if(!GameSettings.useObjectImage)sprite.setColor(GameSettings.objectColor);
	}

	public void reset(){
		travelled = 0;
		POINT = false;
		POINTGIVEN = false;
		CHILD = false;
		BIRTHED = false;
	}
	
	// =========================================
	// Get rectangle for collisions
	// =========================================
	public Rectangle getRect(){
		return rec.set(x, y, objectSize, objectSize);
	}
	
	
	public void setPoint(){
		POINT = true;
	}


	public float getWidth(){
		return width;
	}
	
	
	public float getHeight(){
		return height;
	}
	
	
	public float getX(){
		return this.x;
	}
	
	
	public float getY(){
		return this.y;
	}
	
	
	public void setOffSet(float offSet){
		y = screenHeight + offSet;
	}
	
	
	public boolean getChild(){
		if(CHILD && !BIRTHED){
			BIRTHED = true;
			return true;
		}
		return false;
	}
	
	private float randomRotation(){
		return rand.nextFloat() * ((this.side == Side.LEFT) ? -5f : 5f);
	}

	
	// =========================================
	// Handle jumps
	// =========================================
	public boolean update(SpriteBatch sb){
		y -= gravity;
		if(y <= screenHeight )travelled += gravity;
		
		/* Future Update 
		for(int i = 0; i < sprites.size(); i++){
			spriteHolder = sprites.get(i);
			spriteHolder.setPosition(x + spriteHolder.getU(),  y + spriteHolder.getU());
			spriteHolder.rotate(randomRotation());
			spriteHolder.draw(sb);
		}
		*/
		
		sprite.setPosition(x, y);
		sprite.draw(sb);
		
		

		if((travelled + gravity) >= height){
			CHILD = true;
		}

		
		if(y < 0 - height){
			return false;
		}

		if(!POINTGIVEN && !Hero.isDead) {
			Score.tick();
		}
		/*if(POINT && y <= pointPosition && !POINTGIVEN && !Hero.isDead){
			Score.add();
			POINTGIVEN = true;
		}*/
		return true;
	}
}
