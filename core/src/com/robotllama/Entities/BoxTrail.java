package com.robotllama.Entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.robotllama.Handlers.ImageHandler;
import com.robotllama.Settings.GameSettings;

public class BoxTrail implements Disposable{
	
	ParticleEffect trail;
	ParticleEmitter trailFirst;
	float[] particleColors = new float[]{0, 0, 0};
	public static boolean trailDraw = false;
	Sprite hero;
	Random rand = new Random();
	float rx, ry, size;
	boolean drift;
	Color color = GameSettings.heroParticleColor;
	
	public BoxTrail(Sprite hero, float size, Boolean drift){
		this.drift = drift;
		this.hero = hero;
		this.size = size;
		trail = new ParticleEffect();
		trail.load(Gdx.files.internal("boxTrail"),Gdx.files.internal("images"));
		trailFirst = trail.getEmitters().first();
		trailFirst.setSprite(new Sprite(ImageHandler.particleSprite));
		trailFirst.getEmission().setHighMin(20);
		trailFirst.getEmission().setHighMax(40);
		trailFirst.getScale().setHigh(size/2);
		trailFirst.getVelocity().setHighMin(size/10);
		trailFirst.getVelocity().setHighMax(size/2);
		
		particleColors[0] = color.r;
		particleColors[1] = color.g;
		particleColors[2] = color.b;
		trailFirst.getTint().setColors(particleColors);
	}
	
	public void reset(){
		trail.reset();
	}
	
	public void pause(){
		trailFirst.allowCompletion();
	}
	
	public void resume(){
		reset();
	}
	
	public void update(SpriteBatch sb, float delta){
		if(drift){
			rx = hero.getX() + (rand.nextFloat() * hero.getWidth());
			ry = hero.getY() + (rand.nextFloat() * hero.getHeight());
			trailFirst.setPosition(rx, ry);
		}else{
			trailFirst.setPosition(middleX(), middleY());
		}
		
		trail.draw(sb, delta);
	}
	
	private float middleX(){
		return hero.getX() + size/2;
	}
	
	private float middleY(){
		return hero.getY() + size/2;
	}

	@Override
	public void dispose() {
		trail.dispose();
	}
}
