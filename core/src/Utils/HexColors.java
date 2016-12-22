package Utils;

import com.badlogic.gdx.graphics.Color;

public class HexColors {

	public static Color hex2Rgb(String colorStr) {
		
		int r = Integer.valueOf( colorStr.substring( 1, 3 ), 16 );
		int g = Integer.valueOf( colorStr.substring( 3, 5 ), 16 );
		int b = Integer.valueOf( colorStr.substring( 5, 7 ), 16 );
		
		Color c = new Color(r/255f, g/255f, b/255f, 1);
		return c;
	}
}
