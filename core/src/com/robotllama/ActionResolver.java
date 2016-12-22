package com.robotllama;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ActionResolver {
	
	public void googlePlaySubmit(int score);

	public void googlePlayView();

	public void connect(boolean seeScore);

	public void showAds(boolean show);

	public void showInterAds();

	public boolean wasInterClosed();

	public void resetInterClosed();

	public int getInterAdFrequency();

	public void share();

	public void selectSong(Stage stage);

    public int displaySongList(Stage stage);

    public float getSongListWidthSecondCol();

    public String getSongDisplay(FileHandle file);

	public String getHighScore(FileHandle file);

	public void writePeaks(List<Float> peaksListToWrite, String fileName) throws IOException;

	public List<Float> readPeaks(String fileName) throws IOException, FileNotFoundException;

	public void Toast(String string, int duration);

	public void rate();

	// =========================================
	// Grabbing Title and Sub
	// =========================================

	// hero speed
	public String getAppName();

	// pulse count
	public String getSubTitle();

	
	
	// =========================================
	// Grabbing Difficulty Settings
	// =========================================

	// hero speed
	public int getGameSpeed();

	//increment
	public int getGameSpeedIncrement();

	//increment Increase value
	public int getGameSpeedIncrementIncrease();

	
	
	// =========================================
	// Grabbing title files
	// =========================================

	// main font size
	public String getMainTitleFontFile();

	// main font size
	public String getSubTitleFontFile();

	
	
	// =========================================
	// Grabbing title colors
	// >> colors
	// =========================================

	// main font color
	public String getMainTitleColor();

	// sub font color
	public String getSubTitleColor();

	
	
	// =========================================
	// Grabbing title size
	// >> colors
	// =========================================

	// main font size
	public int getMainTitleSize();

	// main font size
	public int getSubTitleSize();

	
	
	// =========================================
	// Grabbing Entity Info
	// >> colors
	// =========================================

	// hero color
	public String getHeroColor();

	// Object color
	public int getHeroSize();

	// hero image boolean
	public boolean useHeroImage();

	// Object color
	public String getObjectColor();

	// Object color
	public int getObjectSize();

	// wall image boolean
	public boolean useObjectImage();

	// ground color
	public String getGroundColor();

	// wall image boolean
	public boolean useWallImage();

	// hero particle color
	public String getHeroParticleColor();

	// ground particle color
	public String getGroundParticleColor();

	
	// =========================================
	// Grabbing game settings
	// >> colors
	// =========================================

	//splash background color
	public String getSplashBackgroundColor();

	//splash background color
	public String getGameBackgroundColor();

	//use background image
	public boolean useBackgroundImage();

	
	
	// =========================================
	// Grabbing game buttons
	// =========================================

	public void loadButtons(int size);

	public Map<String, Texture> getButtonTextures();

    public void songSelected(FileHandle file, Label label, Stage stage);
}
