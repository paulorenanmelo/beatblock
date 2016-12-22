package com.robotllama;


import com.badlogic.gdx.Game;
import com.robotllama.Handlers.Assets;
import com.robotllama.Handlers.ImageHandler;
import com.robotllama.Screens.Menu;
import com.robotllama.Screens.Play;
import com.robotllama.Screens.Scores;
import com.robotllama.Screens.Splash;

public class Main extends Game{

	public static ImageHandler imageHandler;
	//public static TextHandler textHandler;

	//Game screens
	public Menu menuScreen;
	public Play playScreen;
	public Splash splashScreen;
	public Scores scoresScreen;

	public static ActionResolver actionResolver;


	public Main(ActionResolver actionResolver){
		Main.actionResolver = actionResolver;
	}

	@Override
	public void create() {
		splashScreen = new Splash(this);
		actionResolver.showAds(true);
		setScreen(splashScreen);

		/*registerStuff();
		Main.actionResolver.connect(false);
		setScreen(menuScreen);*/
	}

	//Triggered once all assets are loaded
	// - see the splash screen for trigger event
	public void registerStuff(){
		imageHandler = new ImageHandler(this);

		menuScreen = new Menu(this);
		playScreen = new Play(this);
		scoresScreen = new Scores(this);    	
	}
	
	public static ActionResolver getActionResolver(){
		return actionResolver;
	}


	@Override
	public void dispose() {
		super.dispose();
		Assets.manager.dispose();
		imageHandler.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
		Assets.manager.finishLoading();
	}
}
