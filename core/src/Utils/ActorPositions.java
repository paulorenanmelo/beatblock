package Utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.robotllama.Settings.GameSettings;

public class ActorPositions {
	
	private static final float screenWidth = GameSettings.screenWidth;
	private static final float screenHeight = GameSettings.screenHeight;
	private static final float screenMargin = GameSettings.getScreenMargin();
	
	public static float getMidY(float y, Actor actor){
		return GameSettings.getScreenHeightPos(.5f) - (actor.getHeight() * .5f);
	}
	
	public static float getOffScreenYUp(Actor actor){
		
		return 0 - (actor.getHeight() + screenMargin);
	}
	
	public static float getOffScreenX(Actor actor){
		return (actor.getX() == 0)? 0 - actor.getWidth() : screenWidth + actor.getWidth();
	}
	
	public static float getOffScreenYDown(Actor actor){
		return screenHeight + screenMargin;
	}
}
