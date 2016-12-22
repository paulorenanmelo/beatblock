package com.robotllama.CustomActors;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CompositeImage extends CustomActor{

	private static float size;
	List<Sprite> componentSprites = new ArrayList<Sprite>();
	Rectangle boundingBox;
	int currentIndex = 0;
	Image image;
	float x, y, x2, y2, height;
	float offCameraX, offCameraY;
	static float offScreenY;


	public CompositeImage(List<Sprite> list, float size) {

		this.size = size;
		for(Sprite sprite : list){
			sprite.setSize(size, size);
			componentSprites.add(sprite);
		}

		setWidth(getSprite().getWidth());
		setHeight(getSprite().getHeight());
		setBounds(0, 0, getWidth(), getHeight());
		
		setCameraVectors();
	}
	
	public CompositeImage(CompositeImage ci){
		this.componentSprites = ci.getComponentSprites();
		this.size = ci.getSize();
		
		setWidth(ci.getSprite().getWidth());
		setHeight(ci.getSprite().getHeight());
		setBounds(0, 0, getWidth(), getHeight());
	}
	
	public float getSize(){
		return size;
	}
	
	
	public List<Sprite> getComponentSprites(){
		return componentSprites;
	}

	public void position(float x, float y){
		setX(x);
		setY(y);
		setCameraVectors();
	}
	
	public void setPosition(float x, float y){
		super.setPosition(x, y);
		setX(x);
		setY(y);
		for(Sprite sprite : componentSprites){
			sprite.setPosition(x, y);
		}
	}
	
	public Sprite getSprite(){
		return componentSprites.get(currentIndex);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		getSprite().draw(batch, parentAlpha);
	}

	public void isTouched(){
		int spriteCount = componentSprites.size()-1;
		currentIndex++;
		if(currentIndex > spriteCount)currentIndex = 0;
	}
}
