package Utils;

import com.badlogic.gdx.audio.Sound;
import com.robotllama.Handlers.Assets;
import com.robotllama.Settings.GameSettings;


public class Sounds {

    private static Sound click = Assets.manager.get("sounds/click.wav", Sound.class);
    private static Sound hit = Assets.manager.get("sounds/hit.wav", Sound.class);
    private static Sound jump = Assets.manager.get("sounds/jump.wav", Sound.class);
    private static Sound point = Assets.manager.get("sounds/point.wav", Sound.class);

    public static void click(){
    	if(GameSettings.getSound())click.play();
	}
	
	public static void jump(){
		if(GameSettings.getSound())jump.play();
	}
	
	public static void point(){
		if(GameSettings.getSound())point.play();
	}
	
	public static void hit(){
		if(GameSettings.getSound())hit.play();
	}
}
