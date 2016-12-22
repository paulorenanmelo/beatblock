package com.robotllama.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontHandler {

	// =========================================
	// Generate font
	// =========================================
	public static BitmapFont createFont(String fontName, float size, float screenHeight)
	{
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/" + fontName));
		
		FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter(); 
		param.size = (int) (screenHeight * (size / 100));
		param.minFilter = TextureFilter.Linear;
		param.magFilter = TextureFilter.MipMapLinearNearest;
		
		BitmapFont theFont = generator.generateFont(param);
		generator.dispose();

	    return theFont;
	}
}
