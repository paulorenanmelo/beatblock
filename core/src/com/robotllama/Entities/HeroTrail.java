package com.robotllama.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.robotllama.Handlers.ImageHandler;
import com.robotllama.Settings.GameSettings;

public class HeroTrail implements Disposable{
	
	private static final Color groundColour = GameSettings.heroColor;
	private static final float leftX = GameSettings.getGroundSize();
	private static final float rightX = GameSettings.screenWidth - leftX;
	final static float size = GameSettings.getHeroSize();
	
	enum TrailPosition{
		LEFT,
		RIGHT
	}
	
	static ParticleEffect trail;
	static ParticleEmitter trailFirst;
	float[] particleColors = new float[]{0, 0, 0};
	public static boolean trailDraw = false;
	static Hero hero;
	Color color = GameSettings.groundParticleColor;
	
	public HeroTrail(Hero hero){
		this.hero = hero;
		trail = new ParticleEffect();
		trail.load(Gdx.files.internal("heroTrail"),Gdx.files.internal("images"));
		trailFirst = trail.getEmitters().first();
		trailFirst.setBehind(true);
		trailFirst.setSprite(new Sprite(ImageHandler.particleSprite));
		trailFirst.getScale().setHigh(size/15);
		trailFirst.getVelocity().setHighMin(size/2);
		trailFirst.getVelocity().setHighMax(size*5);
		trail.start(); 
		
		particleColors[0] = color.r;
		particleColors[1] = color.g;
		particleColors[2] = color.b;
		trailFirst.getTint().setColors(particleColors);
	}
	
	public static void setPosition(TrailPosition position){
		switch (position){
		case LEFT:
			trailFirst.setPosition(leftX, hero.getY());
			break;
		case RIGHT:
			trailFirst.setPosition(rightX, hero.getY());
			break;
		}
		trailDraw = true;
	}
	
	public static void reset(){
		trail.reset();
	}
	
	public void update(SpriteBatch sb, float delta){
		
		if(trailDraw && !Hero.isDead){
			trail.draw(sb, delta);
		}else{
			trail.allowCompletion();
			trail.reset();
		}
	}


	@Override
	public void dispose() {
		trail.dispose();
	}
}
