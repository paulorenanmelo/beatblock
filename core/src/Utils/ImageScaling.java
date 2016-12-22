package Utils;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class ImageScaling {

	public enum ClockWise{
		CLOCKWISE(true),
		ANTICLOCKWISE(false);
		
		private boolean b;
		
		ClockWise(boolean b){
			this.b = b;
		}
		
		public boolean getValue(){
			return b;
		}
	}
	
	public static float scaleMe(float newWidth, Sprite sprite){
		float ratio = sprite.getWidth() / sprite.getHeight();
		float newHeight = newWidth / ratio;
		return newHeight;
	}
	
	public static void scale(Sprite sprite, float maxSize){
		float w = sprite.getWidth();
		float h = sprite.getHeight();
		float newW, newH;
		
		float ratio = w/h;
		if(w > h){
			newW = maxSize;
			newH = newW / ratio;
		}else{
			newH = maxSize;
			newW = newH * ratio;
		}
		
		sprite.setSize(newW, newH);
	}
	
	public static Sprite rotateSprite(Sprite sprite, ClockWise clockwise, int degrees){
		
		float w;
		float h;
		
		for(int i = 0; i < degrees/90; i++){
			w = sprite.getWidth();
			h = sprite.getHeight();
			
			sprite.rotate90(clockwise.getValue());
			sprite.setSize(h, w);
		}

		return sprite;
	}
}
