package com.robotllama.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.robotllama.Handlers.FontHandler;
import com.robotllama.Main;

import Utils.HexColors;

public class GameSettings {
	
	/*================================================================================================================================
	//---- READ ME ----//
	==================================================================================================================================
	*
	* This is the only file you will need to edit. 
	* This file contains every editable aspect of the game. There are a large number of settings but dont worry everything is fully
	* explained and there is a video tutorial in the documents section of the downloaded zip from CodeCanyon.
	* 
	* 
	* QUICK START GUIDE
	* =================
	* 
	* There is a quick start guide in the help documentation.
	* 
	* 
	* HELP WITH COLOURS
	* =================
	* 
	* All game colours are handled with hex values and must use this format #XXXXXX where X is an alphanumerical value. For help with
	* hex colours please refer to the help document or:
	* visit: http://www.color-hex.com/
	* 
	* 
	* What Can I Edit?
	* ================
	* 
	* To make your life easier I have tagged everything that you can edit as follows:
	* ================
	*     EDIT ME
	* ================
	* That should help you out :)
	* 
	==================================================================================================================================
	//---- READ ME DONE----//
	=================================================================================================================================*/



	/*================================================================================================================================
	//---- Debug Mode ----//
	==================================================================================================================================
	*/
	/* === Toggle Debug ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// true / false to turn on / off debug mode
	public static boolean debug = false;
	
	
	
	/*================================================================================================================================
	//---- QUICK START SETTINGS ----//
	==================================================================================================================================
	*/
	
	/* === Font Titles ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// game title
	// => see android > res > values > strings > app_name
	// title for the main menu
	public static String subTitle = "Fingers Ready?";
	// title for the best score title
	public static String bestScoreTitle = "Best: ";		
	// title for the score title
	public static String scoreTitle = "Score: ";	
	// title for the high Score title
	public static String highScoresTitle = "High Score";
	// title for the game over screen
	public static String gameOverTitle = "Game Over";
	// start value for countdown
	public static String countdownStart = "Ready";
	// end value for countdown
	public static String countdownEnd = "GO";	
	// song list title
	public static String songListTitle = "Your Music Folder";


	// - song list lable style
	public static LabelStyle style;
	// - libgdx default skin
    public static Skin skin;
    // - music folder default name on android devices is /Music
    public static String musicFolderDefaultName = "Music";

	/* === Font File ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// colour for title font - use hex colours
	public static String mainFontFile = "main.ttf";
	// colour for title font - use hex colours
	public static String subFontFile = "sub.ttf";
	
	
	/* === Font Colour ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// colour for title font - use hex colours
	public static Color mainFontColour = HexColors.hex2Rgb("#ffffff");
	// colour for title font - use hex colours
	public static Color subFontColour = HexColors.hex2Rgb("#000000");
	
	
	/* === Global Background ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - USE 100% COLOUR
	public static boolean useGlobalBackgroundColour = true;
	
	// - SET BACKGROUND COLOUR
	// colour of the background - use hex colours
	public static Color globalBackgroundColour = HexColors.hex2Rgb("#5c899a");
	
	// - SET BACKGROUND IMAGE
	public static String globalBackgroundName = "bg.png";
	
	
	/* === Splash Screen Background ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - USE 100% COLOUR
	public static boolean useBackgroundColour = false;
	
	// - SET BACKGROUND COLOUR
	// colour of the background - use hex colours
	public static Color backgroundColour = HexColors.hex2Rgb("#d6d6d6");
	
	// - SET BACKGROUND IMAGE
	public static String backgroundName = "bg.png";
	
	
	/* === Splash Screen Logo ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - LOGO NAME
	public static String logoName = "logo.png";
	
	
	
	/*================================================================================================================================
	//---- Font Settings ----//
	==================================================================================================================================
	*/
	// font used for all titles
	public static BitmapFont mainFont;
	// font used for scores
	public static BitmapFont subFont;



	/* === Font Size ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// title font size. Size is percentage of screen size. So 20 = 20% of screen height.
	static float mainFontSize = 18;
	// score font size. Size is percentage of screen size. So 20 = 20% of screen height.
	static float subFontSize = 5;
	
	
	
	/*================================================================================================================================
	//---- Button Settings ----//
	==================================================================================================================================
	*/
	/* === Button Sizes ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - MENU BUTTON SIZE
	// Percentage of screen height. So 14 is 14%.
	public static float menuButtonSize;
	public static float menuButtonSizeInput = 10;
	
	// - BIG BUTTON SIZE
	// Percentage of screen height. So 20 is 20%.
	public static float bigButtonSize;
	public static float bigButtonSizeInput = 20;
	
	// - BUTTON POSITIONS
	// Positions of and button list
	// Percentage of screen height. So 30 is 30%.	
	public static float buttonPosition;
	public static float buttonPositionInput = 30;
	
	
	
	/*================================================================================================================================
	//---- READ ME : Global Variables ----//
	==================================================================================================================================
	*
	* These are settings that carry across all screens ( see SCREEN section ).
	* 
	* 
	* SCREEN MARGIN
	* =============
	* 
	* To keep all used spacing equal you can set the value of this spacing.
	* The value set is a percentage of the screen width.
	* This value is used to space out things like buttons.
	* 
	* 
	* ANIMATIONS
	* ==========
	* 
	* The game uses a number pre-defined animation sequences to give elements a bounce or slideIn / slideOut effect. You can not edit
	* the actual animation sequences but you can edit their interpolation settings. This alone can give an animation sequence a
	* completely new feeling.
	* 
	* To change the interpolation, simply select one of the listed settings ( see below ).
	* 
	* 
	* ANIMATION SPEED
	* ===============
	* 
	* This controls how fast all the animations take place. The setting is a float with a range of 0 - 1, eg; 0.7 which stands for
	* 0.7 of a second. It can also be set higher than 1 but each integer is the equivalent of 1 second., eg; 2.7 is 2.7 seconds.
	* 
	* Settings this value too high will create very slow animations. Too low are your animations will be too fast.
	* 
	* 
	* OTHER SETTINGS
	* ==============
	* 
	* These are all explained below
	* 
	==================================================================================================================================
	//---- READ ME DONE----//
	=================================================================================================================================*/

	
	/* === Screen Sizes === */
	// - Screen Height
	public static float screenHeight = Gdx.graphics.getHeight();
	// - Screen Width
	public final static float screenWidth = Gdx.graphics.getWidth();
    // - PIXELS PER INCH
    public final static float PPI = Gdx.graphics.getPpiX() + Gdx.graphics.getPpiY() / 2;

    /* === Scroll menu Event === */
	// - Amount of dragging ignored by the touching event.
    public final static float dragEnergyIgnore = ((screenWidth*screenHeight)/PPI)/200f;


	/* === Screen Margin ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - Margin
	// Percentage of screen width. So 5 is 5%.
	public static float screenMargin;
	public static float screenMarginInput = 3;
	
	// - Game Sound
	// True or false = on or off
	public static boolean sound = true;
	public static final float timeOffset = 0.53f;//Time the block spend to get to the right point and match the actual beat

	
	/* === Hero ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - HERO SPEED
	// High value = faster game + harder
	// Slower value = slower  + easier
	public static float heroSpeed;
	public static float heroSpeedInput = Main.getActionResolver().getGameSpeed() * 1.5f;

	// - HERO SIZE
	// This value is a % of the screen width. A nice is size 5 - 7.
	public static float heroSize;
	public static float heroSizeInput = Main.getActionResolver().getHeroSize();
	
	
	/* === Object ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - OBJECT SIZE
	// This value is a % of the screen width. A nice is size 5 - 7.
	public static float objectSize;
	public static float objectSizeInput = Main.getActionResolver().getObjectSize();
	
	// - GRAVITY
	// This determines the gravity applied to the jumping objects
	// The value represents a portion of the object's height.
	// Higher value = less gravity
	public static float gravity;
	public static float gravityInput = Main.getActionResolver().getGameSpeed();
	
	/* === Animations ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - ANIMATION TYPE
	// => Interpolation.bounce 
	// => Interpolation.bounceIn 
	// => Interpolation.bounceOut 
	// => Interpolation.circle 
	// => Interpolation.circleIn 
	// => Interpolation.circleOut 
	// => Interpolation.elastic 
	// => Interpolation.elasticIn 
	// => Interpolation.elasticOut 
	// => Interpolation.exp10 
	// => Interpolation.exp10In 
	// => Interpolation.exp10Out 
	// => Interpolation.exp5 
	// => Interpolation.exp5In 
	// => Interpolation.exp5Out 
	// => Interpolation.fade 
	// => Interpolation.linear 
	// => Interpolation.pow2 
	// => Interpolation.pow2In 
	// => Interpolation.pow2Out 
	// => Interpolation.pow3 
	// => Interpolation.pow3In 
	// => Interpolation.pow3Out 
	// => Interpolation.pow4 
	// => Interpolation.pow4In 
	// => Interpolation.pow4Out 
	// => Interpolation.pow5 
	// => Interpolation.pow5In 
	// => Interpolation.pow5Out 
	// => Interpolation.sine 
	// => Interpolation.sineIn 
	// => Interpolation.sineOut 
	// => Interpolation.swing 
	// => Interpolation.swingIn 
	public static Interpolation animationType = Interpolation.circleOut;
	
	public static Interpolation buttonOut = Interpolation.bounceOut;
	
	// - ANIMATION SPEED
	public static float animationSpeed = 0.7f;
	
	
	
	/*================================================================================================================================
	//---- READ ME : Screen Settings ----//
	==================================================================================================================================
	*
	*
	* SCREENS EXPLAINED
	* =================
	* 
	* This section is designed to give you complete control of various aspects on each screen. Screens are as follow:
	* 		
	* 		- Splash Screen; the screen the player sees when the game first starts
	* 		- Menu Screen; the screen with the game title, sub title and buttons
	* 		- Score Screen; shows the player's highest score
	* 		- Game Screen; runs the game
	* 
	*  Each of these screens display some unique content and depending on the fonts you chose to use in your game, you may need to
	*  slightly adjust some positions for clean centering. Don't worry it is really simple and you can see results instantly by
	*  adjusting the following setting sections
	* 
	==================================================================================================================================
	//---- READ ME DONE----//
	=================================================================================================================================*/
	
	
	
	/*================================================================================================================================
	//---- READ ME : Splash Screen ----//
	==================================================================================================================================
	*
	* The logo size is a scaling system based around the width of the logo vs the width of the screen. The resulting size will keep
	* the logo height to width ratio.
	* 
	==================================================================================================================================
	//---- READ ME DONE----//
	=================================================================================================================================*/

	
	/* === Logo ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */

	// - LOGO SIZE
	// Percentage of screen width, so 15 = 15%
	public static float logoSize;
	public static float logoSizeInput = 40;
	
	// - POSITIONX
	// The horizontal position of the logo, 50 = 50% which is the center
	public static float logoXPosition;
	public static float logoXPositionInput = 50;
	
	// - POSITIONY
	// The vertical position of the logo, 50 = 50% which is the center
	public static float logoYPosition;
	public static float logoYPositionInput = 50;
	
	
	
	/*================================================================================================================================
	//---- READ ME : Menu Screen ----//
	==================================================================================================================================
	*
	* This screen has four main elements; title, subtitle background and the buttons.
	* All elements are pre-loaded at the splash screen game stage, so you dont need to worry too much about that. You will want to edit
	* title, subtitle and button positions if you change the overall game fonts.
	* 
	* 
	* TITLE HANDLING
	* ==============
	* 
	* At most you will want to adjust the vertical position of this title. I doubt you will want to adjust the horizontal position,
	* although this is still an option
	* 
	* 
	* SUBTITLE HANDLING
	* =================
	* 
	* The default is set to position the subtitle equally between the title and the buttons. However, this setting is heavily
	* dependent upon the various fonts used. If you change the fonts then it is likely you will need to change this value to keep the
	* subtitle nicely positioned.
	* 
	* 
	* BUTTON HANDLING
	* ===============
	* 
	* Button icons are handled in the BUTTONS section of the settings.
	* 
	* The button positions are controlled by a few factors;
	* 
	* - BUTTON SIZE ( see BUTTONS section)
	* - SCREENMARGIN ( see GAME SETTINGS section)
	* - HORIZONTAL POSITION ( handling by code, there is no setting for this value to ensure buttons are always displayed nicely )
	* - VERTICAL POSITION ( handled per screen section as it may need to be adjusted to make space for other elements )
	* 
	* 
	* BACKGROUND HANDLING
	* ===================
	* 
	* This is handled globally for all post-splash screens ( see GAME SETTINGS )
	* 
	* 
	==================================================================================================================================
	//---- READ ME DONE----//
	=================================================================================================================================*/
	
	
	/* === Title Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONX
	// The horizontal position of the logo, 50 = 50% which is the center
	public static float menuTitleXPosition;
	public static float menuTitleXPositionInput = 50;
	
	// - POSITIONY
	// The vertical position of the logo, 75 = 75% which is the top
	public static float menuTitleYPosition;
	public static float menuTitleYPositionInput = 75;
	
	
	/* === Sub Title Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONX
	// The horizontal position of the subtitle, 50 = 50% which is the center
	public static float menuSubTitleXPosition;
	public static float menuSubTitleXPositionInput = 50;
	
	// - POSITIONY
	// The vertical position of the subtitle, 65 = 65% which is the top
	public static float menuSubTitleYPosition;
	public static float menuSubTitleYPositionInput = 65;


    /* === Song List Position ===
     * ===================
     *      EDIT ME
     * ===================
     */
    // - POSITIONX
    // The horizontal position of the song list, 50 = 50% which is the center
    public static float songListTitleXPosition;//unused
    public static float songListTitleXPositionInput = 50;//unused

    // - POSITIONY
    // The vertical position of the song list, 20 = 20% which is at the bottom
    public static float songListTitleYPosition;//unused
    public static float songListTitleYPositionInput = 20;//unused

//    // - WIDTH OF SECOND COL
//    // The width in pixels of the second column in the list (A++)
	public static float songListWidthSecondCol;
	public static float songListWidthSecondColInput = Main.getActionResolver().getSongListWidthSecondCol();;

	/* === Button Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONY
	// The vertical position of the logo, 55 = 55% which is the center-top
	public static float menuButtonYPosition;
	public static float menuButtonYPositionInput = 55;
	
	
	
	/*================================================================================================================================
	//---- READ ME : Score Screen ----//
	==================================================================================================================================
	*
	* This screen has four main elements; title, best score background and the buttons.
	* All elements are pre-loaded at the splash screen game stage, so you dont need to worry too much about that. You will want to edit
	* title, subtitle and button positions if you change the overall game fonts.
	* 
	* 
	* TITLE HANDLING
	* ==============
	* 
	* At most you will want to adjust the vertical position of this title. I doubt you will want to adjust the horizontal position,
	* although this is still an option
	* 
	* 
	* BEST SCORE HANDLING
	* ===================
	* 
	* The default is set to position the best score equally between the title and the buttons. However, this setting is heavily
	* dependent upon the various fonts used. If you change the fonts then it is likely you will need to change this value to keep the
	* best score nicely positioned.
	* 
	* 
	* BUTTON HANDLING
	* ===============
	* 
	* Button icons are handled in the BUTTONS section of the settings.
	* 
	* The button positions are controlled by a few factors;
	* 
	* - BUTTON SIZE ( see BUTTONS section)
	* - SCREENMARGIN ( see GAME SETTINGS section)
	* - HORIZONTAL POSITION ( handling by code, there is no setting for this value to ensure buttons are always displayed nicely )
	* - VERTICAL POSITION ( handled per screen section as it may need to be adjusted to make space for other elements )
	* 
	* 
	* BACKGROUND HANDLING
	* ===================
	* 
	* This is handled globally for all post-splash screens ( see QUICK START SETTINGS )
	* 
	* 
	==================================================================================================================================
	//---- READ ME DONE----//
	=================================================================================================================================*/
	
	
	/* === Score Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONX
	// The horizontal position of the logo, 50 = 50% which is the center
	public static float scoresTitleXPosition;
	public static float scoresTitleXPositionInput = 50;
	
	// - POSITIONY
	// The vertical position of the logo, 70 = 70% which is the center
	public static float scoresTitleYPosition;
	public static float scoresTitleYPositionInput = 60;
	
	
	/* === Sub Title / Best Scores Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONX
	// The horizontal position of the logo, 50 = 50% which is the center
	public static float scoresSubTitleXPosition;
	public static float scoresSubTitleXPositionInput = 50;
	
	// - POSITIONY
	// The vertical position of the logo, 52.5 = 52.5% which is the center
	public static float scoresSubTitleYPosition;
	public static float scoresSubTitleYPositionInput = 50;
	
	
	/* === Scores Button Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONY
	// The vertical position of the logo, 30 = 30% which is the center
	public static float scoresButtonYPosition;
	public static float scoresButtonYPositionInput = 40;
	
	
	
	/*================================================================================================================================
	//---- READ ME : Game Screen ----//
	==================================================================================================================================
	*
	* This screen has many elements.
	* All elements are pre-loaded at the splash screen game stage, so you dont need to worry too much about that. You will want to edit
	* title, subtitle and button positions if you change the overall game fonts.
	* 
	* 
	* TITLE HANDLING
	* ==============
	* 
	* This screen has numerous texts that act as titles. For sake of control all have been given X and Y position settings.
	* 
	* 
	* SUBTITLE HANDLING
	* =================
	* 
	* As above.
	* 
	* 
	* BUTTON HANDLING
	* ===============
	* 
	* Button icons are handled in the BUTTONS section of the settings.
	* 
	* The button positions are controlled by a few factors;
	* 
	* - BUTTON SIZE ( see BUTTONS section)
	* - SCREENMARGIN ( see GAME SETTINGS section)
	* - HORIZONTAL POSITION ( handling by code, there is no setting for this value to ensure buttons are always displayed nicely )
	* - VERTICAL POSITION ( handled per screen section as it may need to be adjusted to make space for other elements )
	* 
	* 
	* BACKGROUND HANDLING
	* ===================
	* 
	* This is handled globally for all post-splash screens ( see QUICK START SETTINGS )
	* 
	* 
	==================================================================================================================================
	//---- READ ME DONE----//
	=================================================================================================================================*/
	
	
	/* === Game Over Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONX
	// The horizontal position of the logo, 50 = 50% which is the center
	public static float playGameOverXPosition;
	public static float playGameOverXPositionInput = 50;
	
	// - POSITIONY
	// The vertical position of the logo, 70 = 70% which is the center
	public static float playGameOverYPosition;
	public static float playGameOverYPositionInput = 75;
	
	
	/* === Countdown Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONX
	// The horizontal position of the logo, 50 = 50% which is the center
	public static float playCountdownXPosition;
	public static float playCountdownXPositionInput = 50;
	
	// - POSITIONY
	// The vertical position of the logo, 50 = 50% which is the center
	public static float playCountdownYPosition;
	public static float playCountdownYPositionInput = 50;
	
	
	/* === Score Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONX
	// The horizontal position of the logo, 50 = 50% which is the center
	public static float playScoreXPosition;
	public static float playScoreXPositionInput = 50;
	
	// - POSITIONY
	// The vertical position of the logo, 50 = 50% which is the center
	public static float playScoreYPosition;
	public static float playScoreYPositionInput = 75;
	
	/* === Sub Title / Best Scores Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONX
	// The horizontal position of the logo, 50 = 50% which is the center
	public static float playBestScoreSubTitleXPosition;
	public static float playBestScoreSubTitleXPositionInput = 50;
	
	// - POSITIONY
	// The vertical position of the logo, 52.5 = 52.5% which is the center
	public static float playBestScoreSubTitleYPosition;
	public static float playBestScoreSubTitleYPositionInput = 65;
	
	
	/* === Sub Title / Current Scores Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONX
	// The horizontal position of the logo, 50 = 50% which is the center
	public static float playCurrentScoreSubTitleXPosition;
	public static float playCurrentScoreSubTitleXPositionInput = 50;
	
	// - POSITIONY
	// The vertical position of the logo, 52.5 = 52.5% which is the center
	public static float playCurrentScoreSubTitleYPosition;
	public static float playCurrentScoreSubTitleYPositionInput = 60;
	
	
	/* === Navigation Button Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONY
	// The vertical position of the logo, 30 = 30% which is the center
	public static float playButtonYPosition;
	public static float playButtonYPositionInput = 40;
	
	
	/* === Game Control Button Position ===
	 * ===================
	 *      EDIT ME
	 * ===================
	 */
	// - POSITIONY
	// The vertical position of the logo, 30 = 30% which is the center
	public static float playGameButtonYPosition;
	public static float playGameButtonYPositionInput = 50;
	
	
	
	/*================================================================================================================================
	//---- READ ME ----//
	==================================================================================================================================
	*
	* You are now done editing all the settings, feel free to delete all the READ MEs
	* 
	==================================================================================================================================
	//---- READ ME DONE----//
	=================================================================================================================================*/
	
	
	
	/*================================================================================================================================
	//---- NO MORE EDITING ----//
	==================================================================================================================================
	*/
	private static Boolean isNull(Object field){
		if(field instanceof Float){
			if((Float)field == 0){return true;}
		}else{
			if(field == null){return true;}
		}
		return false;
	}
	
	public static float screenBigDimension(){
		if(screenWidth > screenHeight){
			return screenWidth;
		}else{
			return screenHeight;
		}
	}
	
	public static float screenSmallDimension(){
		if(screenWidth < screenHeight){
			return screenWidth;
		}else{
			return screenHeight;
		}
	}
	

	public static float getScreenWidthPos(float f){
		return screenWidth * f;
	}
	
	
	public static float getScreenHeightPos(float f){
		return screenHeight * f;
	}
	
	
	public static float getScreenMargin(){
		if(isNull(screenMargin)){
			screenMargin = screenWidth * (screenMarginInput / 100);
		}
		return screenMargin;
	}
	
	
	public static BitmapFont getMainFont(){
		if(isNull(mainFont)){
			mainFont = FontHandler.createFont(mainTitleFile, mainTitleSize, screenSmallDimension());
			mainFont.setColor(mainTitleColor);
		}
		return mainFont;
	}

	
	public static BitmapFont getSubFont(){
		if(isNull(subFont)){
			subFont = FontHandler.createFont(subTitleFile, subTitleSize, screenSmallDimension());
			subFont.setColor(subTitleColor);
		}
		return subFont;
	}

	// - song list lable style
	public static LabelStyle getDefaultStyle(){
		if(isNull(style)){
			style = new LabelStyle(getSubFont(), subTitleColor);
		}
		return style;
	}

    // - song list lable style
    public static Skin getDefaultSkin(){
        if(isNull(skin)){
            skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        }
        return skin;
    }

	public static int getMenuButtonSize(float size){
		return (int)(size * (menuButtonSizeInput / 100));
	}

	public static int getMenuButtonSize(){
		if(isNull(menuButtonSize)){
			menuButtonSize = screenSmallDimension() * (menuButtonSizeInput / 100);
		}
		return (int)menuButtonSize;
	}
	
	
	public static int getBigButtonSize(){
		if(isNull(bigButtonSize)){
			bigButtonSize = screenSmallDimension() * (bigButtonSizeInput / 100);
		}
		return (int)bigButtonSize;
	}
	
	public static float getButtonPosition(){
		if(isNull(buttonPosition)){
			buttonPosition = screenHeight * (buttonPositionInput / 100);
		}
		return buttonPosition;
	}
	
	
	public static float getHeroSpeed(){
		if(isNull(heroSpeed)){
			heroSpeed = screenSmallDimension() * (heroSpeedInput / 1000);
		}
		return heroSpeed;
	}
	
	public static void increaseHeroSpeed(){
		heroSpeed = heroSpeed * (1f + Main.getActionResolver().getGameSpeedIncrementIncrease()/100f);
	}
	
	public static void resetHeroSpeed(){
		heroSpeed = screenSmallDimension() * (heroSpeedInput / 1000);
	}
	
	
	public static float getHeroSize(){
		if(isNull(heroSize)){
			heroSize = screenSmallDimension() * (heroSizeInput / 100);
		}
		return heroSize;
	}
	
	
	public static float getObjectSize(){
		if(isNull(objectSize)){
			objectSize = screenSmallDimension() * (objectSizeInput / 100);
		}
		return objectSize;
	}
	
	
	public static float getGravity(){
		if(isNull(gravity)){
			gravity = screenHeight * ( gravityInput / 1000 );
		}
		return gravity;
	}
	
	public static void increaseGravity(){
		gravity = gravity * (1f + Main.getActionResolver().getGameSpeedIncrementIncrease()/100f);
	}
	
	public static void resetGravity(){
		gravity = screenHeight * ( gravityInput / 1000 );
	}
	
	
	public static boolean getSound(){
		return sound;
	}
	
	public static void toggleSound(){
		sound = (sound) ? false : true;
	}
	
	
	// =========================================
	// Logo methods
	// =========================================
	// - Logo Size
	public static float getLogoSize(){
		if(isNull(logoSize)){
			logoSize = screenWidth * (logoSizeInput / 100);
		}
		return logoSize;
	}
	
	// - Logo X
	public static float getLogoPosX(){
		if(isNull(logoXPosition)){
			logoXPosition = screenWidth * (logoXPositionInput / 100);
		}
		return logoXPosition;
	}
	
	// - Logo Y
	public static float getLogoPosY(){
		if(isNull(logoYPosition)){
			logoYPosition = screenHeight * (logoYPositionInput / 100);
		}
		return logoYPosition;
	}
	
	
	// =========================================
	// Menu methods
	// =========================================
	// - Menu Title X
	public static float getMenuTitlePosX(){
		if(isNull(menuTitleXPosition)){
			menuTitleXPosition = (menuTitleXPositionInput / 100);
		}
		return menuTitleXPosition;
	}
	
	// - Menu Title Y
	public static float getMenuTitlePosY(){
		if(isNull(menuTitleYPosition)){
			menuTitleYPosition = (menuTitleYPositionInput / 100);
		}
		return menuTitleYPosition;
	}
	
	// - Menu Sub Title X
	public static float getMenuSubTitlePosX(){
		if(isNull(menuSubTitleXPosition)){
			menuSubTitleXPosition = (menuSubTitleXPositionInput / 100);
		}
		return menuSubTitleXPosition;
	}
	
	// - Menu Sub Title Y
	public static float getMenuSubTitlePosY(){
		if(isNull(menuSubTitleYPosition)){
			menuSubTitleYPosition = (menuSubTitleYPositionInput / 100);
		}
		return menuSubTitleYPosition;
	}

    // - Song List Title X
    public static float getSongListTitlePosX(){
        if(isNull(songListTitleXPosition)){
            songListTitleXPosition = (songListTitleXPositionInput / 100);
        }
        return songListTitleXPosition;
    }

    // - Song List Title Y
    public static float getSongListTitlePosY(){
        if(isNull(songListTitleYPosition)){
            songListTitleYPosition = (songListTitleYPositionInput / 100);
        }
        return songListTitleYPosition;
    }

    // - Song List Width Second Col
    public static float getSongListWidthSecondCol(){
        if(isNull(songListWidthSecondCol)){
            songListWidthSecondCol = (screenWidth /100) * songListWidthSecondColInput;
        }
        return songListWidthSecondCol;
    }
	
	// - Menu Button Y
	public static float getMenuButtonPosY(){
		if(isNull(menuButtonYPosition)){
			menuButtonYPosition = screenHeight * (menuButtonYPositionInput / 100);
		}
		return menuButtonYPosition;
	}
	
	
	// =========================================
	// Scores methods
	// =========================================
	// - Menu Title X
	public static float getScoresTitlePosX(){
		if(isNull(scoresTitleXPosition)){
			scoresTitleXPosition = (scoresTitleXPositionInput / 100);
		}
		return scoresTitleXPosition;
	}
	
	// - Scores Title Y
	public static float getScoresTitlePosY(){
		if(isNull(scoresTitleYPosition)){
			scoresTitleYPosition = (scoresTitleYPositionInput / 100);
		}
		return scoresTitleYPosition;
	}
	
	// - Scores Sub Title X
	public static float getScoresSubTitlePosX(){
		if(isNull(scoresSubTitleXPosition)){
			scoresSubTitleXPosition = (scoresSubTitleXPositionInput / 100);
		}
		return scoresSubTitleXPosition;
	}
	
	// - Scores Sub Title Y
	public static float getScoresSubTitlePosY(){
		if(isNull(scoresSubTitleYPosition)){
			scoresSubTitleYPosition = (scoresSubTitleYPositionInput / 100);
		}
		return scoresSubTitleYPosition;
	}
	
	// - Scores Button Y
	public static float getScoresButtonPosY(){
		if(isNull(scoresButtonYPosition)){
			scoresButtonYPosition = screenHeight * (scoresButtonYPositionInput / 100);
		}
		return scoresButtonYPosition;
	}
	
	
	// =========================================
	// Play methods
	// =========================================
	// - GameOver Title X
	public static float getPlayGameOverTitlePosX(){
		if(isNull(playGameOverXPosition)){
			playGameOverXPosition = (playGameOverXPositionInput / 100);
		}
		return playGameOverXPosition;
	}
	
	// - GameOver Title Y
	public static float getPlayGameOverTitlePosY(){
		if(isNull(playGameOverYPosition)){
			playGameOverYPosition = (playGameOverYPositionInput / 100);
		}
		return playGameOverYPosition;
	}
	
	// - Countdown Title X
	public static float getPlayCountdownTitlePosX(){
		if(isNull(playCountdownXPosition)){
			playCountdownXPosition = (playCountdownXPositionInput / 100);
		}
		return playCountdownXPosition;
	}
	
	// - Countdown Title Y
	public static float getPlayCountdownTitlePosY(){
		if(isNull(playCountdownYPosition)){
			playCountdownYPosition = (playCountdownYPositionInput / 100);
		}
		return playCountdownYPosition;
	}
	
	// - Score Title X
	public static float getPlayScoreTitlePosX(){
		if(isNull(playScoreXPosition)){
			playScoreXPosition = (playScoreXPositionInput / 140);
		}
		return playScoreXPosition;
	}
	
	// - Score Title Y
	public static float getPlayScoreTitlePosY(){
		if(isNull(playScoreYPosition)){
			playScoreYPosition = (playScoreYPositionInput / 85);
		}
		return playScoreYPosition;
	}
	
	// - BestScore Sub Title X
	public static float getPlayBestScoreSubTitlePosX(){
		if(isNull(playBestScoreSubTitleXPosition)){
			playBestScoreSubTitleXPosition = (playBestScoreSubTitleXPositionInput / 100);
		}
		return playBestScoreSubTitleXPosition;
	}
	
	// - BestScore Sub Title Y
	public static float getPlayBestScoreSubTitlePosY(){
		if(isNull(playBestScoreSubTitleYPosition)){
			playBestScoreSubTitleYPosition = (playBestScoreSubTitleYPositionInput / 100);
		}
		return playBestScoreSubTitleYPosition;
	}
	
	// - CurrentScore Sub Title X
	public static float getPlayCurrentScoreSubTitlePosX(){
		if(isNull(playCurrentScoreSubTitleXPosition)){
			playCurrentScoreSubTitleXPosition = (playCurrentScoreSubTitleXPositionInput / 100);
		}
		return playCurrentScoreSubTitleXPosition;
	}
	
	// - CurrentScore Sub Title Y
	public static float getPlayCurrentScoreSubTitlePosY(){
		if(isNull(playCurrentScoreSubTitleYPosition)){
			playCurrentScoreSubTitleYPosition = (playCurrentScoreSubTitleYPositionInput / 100);
		}
		return playCurrentScoreSubTitleYPosition;
	}
	
	// - play Button Y
	public static float getPlayButtonPosY(){
		if(isNull(playButtonYPosition)){
			playButtonYPosition = screenHeight * (playButtonYPositionInput / 100);
		}
		return playButtonYPosition;
	}
	
	// - play Button Y
	public static float getPlayGameButtonPosY(){
		if(isNull(playGameButtonYPosition)){
			playGameButtonYPosition = (playGameButtonYPositionInput / 100);
		}
		return playGameButtonYPosition;
	}

	
	// - main font file
	public final static String mainTitleFile = Main.getActionResolver().getMainTitleFontFile();

	
	// - main font color
	public final static Color mainTitleColor = HexColors.hex2Rgb(Main.getActionResolver().getMainTitleColor());

	
	// - main font size
	public final static float mainTitleSize = Main.getActionResolver().getMainTitleSize();


	// - sub font file
	public final static String subTitleFile = Main.getActionResolver().getSubTitleFontFile();

	
	// - sub font color
	public final static Color subTitleColor = HexColors.hex2Rgb(Main.getActionResolver().getSubTitleColor());
	
	
	// - sub font size
	public final static float subTitleSize = Main.getActionResolver().getSubTitleSize();


	// - hero color
	public final static Color heroColor = HexColors.hex2Rgb(Main.getActionResolver().getHeroColor());
	
	
	// - object color
	public final static Color objectColor = HexColors.hex2Rgb(Main.getActionResolver().getObjectColor());
	
	
	// - splash background color
	public final static Color splashBackgroundColor = HexColors.hex2Rgb(Main.getActionResolver().getSplashBackgroundColor());
	
	
	// - splash background color
	public final static Color gameBackgroundColor = HexColors.hex2Rgb(Main.getActionResolver().getGameBackgroundColor());
	
	// - hero particle color
	public final static Color heroParticleColor = HexColors.hex2Rgb(Main.getActionResolver().getHeroParticleColor());
	
	// - ground particle color
	public final static Color groundParticleColor = HexColors.hex2Rgb(Main.getActionResolver().getGroundParticleColor());
	
	// - subtitle text
	public final static String subTitleText = Main.getActionResolver().getSubTitle();

	
	// - get ground size
	public static float getGroundSize(){
		return screenWidth * .1f;
	}
	
	
	// - get ground color
	public final static Color groundColor = HexColors.hex2Rgb(Main.getActionResolver().getGroundColor());

	
	// - get ground count
	public final static int froundCount = (int)Math.ceil(screenHeight / getGroundSize());

	
	// - hero default y and point position
	public final static float pointPosition = getScreenHeightPos(.25f);
	
	//Inter Ad Frequency
	public final static int interAdFrequency = Main.getActionResolver().getInterAdFrequency();
	
	// - use background image
	public final static boolean useBackgroundImage = Main.getActionResolver().useBackgroundImage();

	// - use wall image
	public final static boolean useWallImage = Main.getActionResolver().useWallImage();

	// - use object image
	public final static boolean useObjectImage = Main.getActionResolver().useObjectImage();
	
	// - use Hero image
	public final static boolean useHeroImage = Main.getActionResolver().useHeroImage();
	
	// - GameSpeedIncrement
	public final static int gameSpeedIncrement = Main.getActionResolver().getGameSpeedIncrement();
}
