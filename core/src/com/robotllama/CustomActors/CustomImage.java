package com.robotllama.CustomActors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class CustomImage extends CustomActor {
	private Sprite sprite;

	public CustomImage(Sprite sprite, Vector2 vector) {
		this.sprite = new Sprite(sprite);
		setPosition(vector.x, vector.y);
		setWidth(sprite.getWidth());
		setCameraVectors();
	}
	
	public void setPosition(float x, float y){
		super.setPosition(x, y);
		sprite.setPosition(x, y);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch, parentAlpha);
	}
}
