package Utils;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.robotllama.CustomActors.CompositeImage;
import com.robotllama.CustomActors.CustomTableActor;
import com.robotllama.Settings.GameSettings;

public class Animations {
	
	private final static float animationSpeed = GameSettings.animationSpeed;
	private final static float screenHeight = GameSettings.screenHeight;
	private final static float screenMargin = GameSettings.getScreenMargin();
	private final static Interpolation animationType = GameSettings.animationType;
	private final static Interpolation bounce = GameSettings.buttonOut;
	private final static Interpolation swingOut = Interpolation.swingOut;
	private final static Interpolation swingIn = Interpolation.swingIn;
	private static Array<Actor> actorList = new Array<Actor>();
	
	static Action killActor = new Action(){
	    public boolean act( float delta ) {
	        if(getActor() != null){
	        	getActor().remove();
	        }
	        return true;
	    }
	};
	
	private static boolean isOnStage(Stage stage, Actor actor){
		actorList = stage.getActors();
		if(actorList.contains(actor, true)) return true;
		return false;
	}
	
	public static void slideInFromTop(Stage stage, Actor actor, Vector2 vector){
		if(!isOnStage(stage, actor))stage.addActor(actor);
		float offScreenY = screenHeight + actor.getHeight();
		
		actor.addAction(Actions.sequence(
				Actions.moveTo(vector.x, offScreenY),
        		Actions.moveTo(vector.x, vector.y, animationSpeed, animationType)));
	}
	
	public static void slideInFromBottom(Stage stage, CompositeImage ci){
		if(!isOnStage(stage, (Actor)ci))stage.addActor(ci);
		Vector2 vector = ci.getShowVector();
		ci.addAction(Actions.sequence(
				Actions.moveTo(vector.x, vector.y, animationSpeed, animationType)));
	}

	public static void slideInFromBottom(Stage stage, CustomTableActor ci){
		if(!isOnStage(stage, (Actor)ci))stage.addActor(ci);
		Vector2 vector = ci.getShowVector();
		ci.addAction(Actions.sequence(
				Actions.moveTo(vector.x, vector.y, animationSpeed, animationType)));
	}
	
	public static void quickBounce(Actor actor){
		if(done(actor)){
			Sounds.click();
			float currentX = actor.getX();
			float currentY = actor.getY();
			float newY = actor.getY() + (screenHeight * 0.08f);
			actor.addAction(Actions.sequence(
					Actions.moveTo(currentX, newY, 0.05f, swingOut),
					Actions.moveTo(currentX, currentY, 0.05f)));
		}
	}
	
	public static void bounce(Actor actor){
		if(done(actor)){
			Sounds.click();
			float currentX = actor.getX();
			float currentY = actor.getY();
			float newY = actor.getY() + (screenHeight * 0.08f);
			actor.addAction(Actions.sequence(
					Actions.moveTo(currentX, newY, 0.25f, swingOut),
					Actions.moveTo(currentX, currentY, 0.5f, bounce)));
		}
	}
	
	public static void slideOutViaTop(Actor actor){
		float offScreenY = GameSettings.screenHeight + actor.getHeight() + screenMargin;
		actor.addAction(Actions.sequence(
				Actions.moveTo(actor.getX(), offScreenY, animationSpeed, swingIn),
				Actions.delay(0.5f),
				killActor));
	}
	
	public static void slideOutViaBottom(Actor actor){
		actor.addAction(Actions.sequence(
				Actions.moveTo(actor.getX(), 0 - actor.getHeight(), animationSpeed, swingIn),
				Actions.delay(0.5f),
				killActor));
	}
	
	public static void fadeOut(Actor actor){
		actor.addAction(Actions.fadeOut(0.5f));
	}
	
	public static void splashLogo(Actor actor){
		float offScreenY = screenHeight + actor.getHeight() + screenMargin;
		actor.addAction(Actions.sequence(
				Actions.alpha(0),
				Actions.delay(1f),
				Actions.fadeIn(0.5f),
				Actions.delay(0.2f),
				Actions.moveBy(0, screenHeight * 0.08f, 0.25f, swingOut),
				Actions.moveBy(0, (screenHeight * -0.08f), 0.5f, bounce),
				Actions.delay(0.2f),
				Actions.moveTo(actor.getX(), offScreenY, animationSpeed, swingIn),
				Actions.delay(0.3f)));
	}
	
	public static void show(Actor actor){
		actor.addAction(Actions.sequence(
				Actions.alpha(1)));
	}

	public static void showAnimation(Stage stage, Actor actor, float x, float y){
		if(!isOnStage(stage, actor))stage.addActor(actor);
		actor.addAction(Actions.sequence(
				Actions.moveTo(x, y, animationSpeed, animationType)));
	}	
	
	public static void removeAnimation(Object image){
		float movY = screenHeight + ((Actor) image).getHeight() + screenMargin;
		((Actor) image).addAction(Actions.sequence(
				Actions.moveTo(((Actor) image).getX(), movY, animationSpeed, swingIn),
				Actions.delay(0.3f),
				killActor));
	}
	
	public static void removeAnimation(Object image, float movY){
		((Actor) image).addAction(Actions.sequence(
				Actions.moveTo(((Actor) image).getX(), movY, animationSpeed, swingIn),
				Actions.delay(0.3f),
				killActor));
	}
	
	public static void removeAnimation(Object image, float movX, float movY){
		((Actor) image).addAction(Actions.sequence(
				Actions.moveTo(movX, movY, animationSpeed, swingIn),
				Actions.delay(0.3f),
				killActor));
	}
	
	public static boolean done(Actor a){
		if(a.getActions().size == 0)return true;
		return false;
	}
}
