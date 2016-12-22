package Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.robotllama.Main;
import com.robotllama.Entities.Hero;
import com.robotllama.Entities.Objects;
import com.robotllama.Settings.GameSettings;

public class Score {

	static int score = 0;
	static Preferences prefs = Gdx.app.getPreferences("Game Scores");
	static String scoreString;
	
	public static int getScore(){
		return score;
	}

	public static void tick(){
		score ++;
		scoreString = Integer.toString(score);
		Main.imageHandler.currentScoreText.setString(scoreString , true, false);
	}

	public static void add(){
		score ++;
		scoreString = Integer.toString(score);
		Main.imageHandler.currentScoreText.setString(scoreString , true, false);
		Sounds.point();
		
		if(score % GameSettings.gameSpeedIncrement == 0){
			GameSettings.increaseGravity();
			GameSettings.increaseHeroSpeed();
			Objects.gravity = GameSettings.getGravity();
			Hero.speed = GameSettings.getHeroSpeed();
		}
	}
	
	public static void reset(){
		score = 0;
		GameSettings.resetGravity();
		GameSettings.resetHeroSpeed();
		Objects.gravity = GameSettings.getGravity();
		Hero.speed = GameSettings.getHeroSpeed();
	}
	
	public static void saveHighScore(){
		if(getScore() > getHighScore()){
			prefs.putInteger("score", getScore());
			flush();
		}
	}
	
	public static int getHighScore(){
		return prefs.getInteger("score", 0);
	}
	
	public static void flush(){
		prefs.flush();
	}
}
