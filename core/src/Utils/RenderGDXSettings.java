package Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.robotllama.Settings.GameSettings;

public class RenderGDXSettings {

	private static final Color backgroundColor = GameSettings.gameBackgroundColor;
	private static final float red = backgroundColor.r;
	private static final float blue = backgroundColor.b;
	private static final float green = backgroundColor.g;
	private static final float alpha = backgroundColor.a;
	
	public static void settings(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
		Gdx.gl.glClearColor(red, green, blue, alpha);
	}
}
