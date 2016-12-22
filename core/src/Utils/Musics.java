package Utils;

/**
 * Created by Paulo on 25-Aug-15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.robotllama.Main;
import com.robotllama.Settings.GameSettings;

public class Musics {

    // selectedMusic = Gdx.audio.newMusic(
    //                                    Gdx.files.external(System.getProperty("user.home")
    //                                    + "music/AviciiWakeMeUp.wav"));
    private static Music selectedMusic;

    private static boolean played = false;

    public static void playSelected(){
        selectedMusic =  Gdx.audio.newMusic(Main.imageHandler.selectedSong);

        selectedMusic.setVolume(0f);
        if(GameSettings.getSound()) selectedMusic.setVolume(1f);

        selectedMusic.play();
        played = true;
    }

    public static void stopSelected(){
        selectedMusic.stop();
        selectedMusic.dispose();
        played = false;
    }

    public static void music01toggleMute(){
        if (selectedMusic.getVolume() > 0){
            selectedMusic.setVolume(0);
        }
        else {
            selectedMusic.setVolume(1f);
        }
    }

    public static void selectedFadeOut(int steps){
        //libGDX implementation with Sound class for a music
//        if(music01Volume > 0){
//            music01Volume -= 1f/steps;//0.01f
//            music01Pitch -= 1f/(steps*2);//0.005f
//            play.setVolume(music01ID, music01Volume);
//            if (music01Pitch > 0.5) play.setPitch(music01ID, music01Pitch);
//        }

        //libGDX implementation with Music class for a music
        if(selectedMusic.getVolume() > 0){
            selectedMusic.setVolume(selectedMusic.getVolume() - (1f/steps));//0.01f
        }
    }

    public static void selectedFadeOut(){
        //libGDX implementation with Sound class for a music
//        if(music01Volume > 0){
//            music01Volume -= 0.01f;
//            music01Pitch -= 0.005;
//            play.setVolume(music01ID, music01Volume);
//            if (music01Pitch > 0.5) play.setPitch(music01ID, music01Pitch);
//        }

        //libGDX implementation with Music class for a music
        if(selectedMusic.getVolume() > 0){
            selectedMusic.setVolume(selectedMusic.getVolume() - (1f/200f));//0.005f
        }
    }

    public static float selectedSongPosition() {
        return selectedMusic.getPosition();
    }

    public static boolean finishedPlaying() {
        if (selectedMusic == null && !played){
            return false;
        }
        return !selectedMusic.isPlaying() && played;
    }
}
