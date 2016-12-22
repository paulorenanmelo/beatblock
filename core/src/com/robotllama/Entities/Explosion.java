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

public class Explosion implements Disposable{
	
	static ParticleEffect explode;
	ParticleEmitter explodeFirst;
	float[] particleColors = new float[]{0, 0, 0};
	public static boolean explodeDone = false;
	Hero hero;
	final static float size = GameSettings.getHeroSize();
	Color color = GameSettings.heroParticleColor;
	
	public Explosion(Hero hero){
		this.hero = hero;
		explode = new ParticleEffect();
		explode.load(Gdx.files.internal("explode2"),Gdx.files.internal("images"));
		explodeFirst = explode.getEmitters().first();
		explodeFirst.getRotation().setHighMin(0);
		explodeFirst.getRotation().setHighMax(180);
		explodeFirst.setSprite(new Sprite(ImageHandler.particleSprite));
		explodeFirst.getScale().setHigh(size/2);
		explodeFirst.getVelocity().setHighMin(size/2);
		explodeFirst.getVelocity().setHighMax(size*10);
		explode.start(); 
		
		particleColors[0] = color.r;
		particleColors[1] = color.g;
		particleColors[2] = color.b;
		explodeFirst.getTint().setColors(particleColors);
	}
	
	public static void reset(){
		explode.reset();
	}
	
	public void update(SpriteBatch sb, float delta){
		
		if(explode.isComplete()){
			explode.allowCompletion();
			explodeDone = true;
		}else if(Hero.isDead){
			explodeFirst.setPosition(hero.x + hero.width/2, hero.y);
		    explode.draw(sb, delta);
		    explodeDone = false;
		}
		
	}


	@Override
	public void dispose() {
		explode.dispose();
	}
}
