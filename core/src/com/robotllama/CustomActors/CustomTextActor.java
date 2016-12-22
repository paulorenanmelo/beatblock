package com.robotllama.CustomActors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.robotllama.Settings.GameSettings;

import Utils.OffScreenY;

public class CustomTextActor extends CustomActor{

	BitmapFont font;
	String string;
	Image image;
	float height, width;
	float relativeX, relativeY;
	float relativeXPos, relativeYPos;
	String oldString, newString;
	
	
    public CustomTextActor(BitmapFont font, Object string){
    	this.font = font;
    	this.string = String.valueOf(string);
    	this.newString = this.oldString = this.string;
    }
    
    public CustomTextActor(Image image){
    	this.image = image;
    }
    
    public CustomTextActor(CustomTextActor ca){
    	this.image = ca.getImage();
    	this.font = ca.getFont();
    	this.string = ca.getString();
    	this.relativeX = ca.getRelativeX();
    	this.relativeY = ca.getRelativeY();
    	this.newString = this.oldString = this.string;
    }
    
    public void settingsRelative(float x, float y){
    	setRelativeX(x);
    	setRelativeY(y);
    	reposition();
    	setCameraVectors();
    }
    
    public void settingsFixed(float x, float y, OffScreenY offY){
    	setX(x);
    	setY(y);
    }
    
	public float getRelativeX(){
    	return relativeX;
    }
    
    public float getRelativeY(){
    	return relativeY;
    }
    
    public BitmapFont getFont(){
    	return font;
    }
    
    public Image getImage(){
    	return image;
    }

    public void setString(String s){
    	string = s;
    	newString = s;
    }
    
    public void setString(String s, boolean x, boolean y){
    	string = s;
    	if(s != newString){
    		if(x){repositionX(); onCamera.x = getX();}
    		if(y){repositionY(); onCamera.y = getY();}
    	}
    	newString = s;
    }
    
    public void setRelativeY(float f){
    	relativeY = f;
    	relativeYPos = GameSettings.getScreenHeightPos(relativeY);
    }
    
    public void setRelativeX(float f){
    	relativeX = f;
    	relativeXPos = GameSettings.getScreenWidthPos(relativeX);
    }
    
    public String getString(){
    	return string;
    }
    
    @Override
    public float getHeight(){
    	height = font.getBounds(string).height;
    	return height;
    }
    
    @Override
    public float getWidth(){
    	width = font.getBounds(string).width;
    	return width;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
    	repositionX();
    	font.draw(batch, String.valueOf(string), getX(), getY());
    }
    
    public void reposition(){
    	repositionX();
    	repositionY();
    }
    
    public void repositionX(){
    	float newX = relativeXPos - (getWidth() * .5f);
    	setX(newX);
    }
    
    public void repositionY(){
    	float newY = relativeYPos + (getHeight() * .5f);
    	setY(newY);
    }
    
    public Vector2 getOnCamera(){
    	return onCamera;
    }
}