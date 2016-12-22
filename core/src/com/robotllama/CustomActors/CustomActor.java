package com.robotllama.CustomActors;

import Utils.EntityStates.MoveState;
import Utils.EntityStates.MoveTo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.robotllama.Settings.GameSettings;

public class CustomActor extends Actor {

	protected static final float screenWidth = GameSettings.screenWidth;
	protected static final float screenHeight = GameSettings.screenHeight;
	protected static final float screenMargin = GameSettings.getScreenMargin();
	private Vector2 offCameraTop, offCameraBottom, offCameraLeft, offCameraRight;
	protected Vector2 onCamera;
	private MoveTo action;
	private MoveState move;
	private Vector2 vector = new Vector2();
	
	public void setCameraVectors(){
		move = MoveState.SHOW;
		offCameraTop = new Vector2(getX(), screenHeight + getWidth() + screenMargin);
		offCameraBottom = new Vector2(getX(), 0 - getHeight());
		offCameraLeft = new Vector2(0 - getWidth(), getY());
		offCameraRight = new Vector2(screenWidth + getWidth(), getY());
		onCamera = new Vector2(getX(), getY());
	}
	
	public void setAnimationAction(MoveTo action){
		this.action = action;
		setOffCamera();
	}
	
	public void updateOnCamera(float x, float y){
		if(x > 0)onCamera.x = x;
		if(y > 0)onCamera.y = y;
	}
	
	public void setOffCamera(){
		vector = getHideVector();
		setX(vector.x);
		setY(vector.y); 
	}
	
	public Vector2 getShowVector(){
		move = MoveState.SHOW;
		return getMoveVector();
	}
	
	public Vector2 getHideVector(){
		move = MoveState.HIDE;
		return getMoveVector();
	}
	
	private Vector2 getMoveVector(){
		switch(move){
		case SHOW:
			return onCamera;
		case HIDE:
			switch(action){
			case TOP:
				return offCameraTop;
			case BOTTOM:
				return offCameraBottom;
			case LEFT:
				return offCameraLeft;
			case RIGHT:
				return offCameraRight;
			}
			break;
		}
		return onCamera;
	}
}
