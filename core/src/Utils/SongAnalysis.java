package Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.robotllama.Main;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulo on 08-Oct-15.
 */
public class SongAnalysis implements AsyncTask<List<Float>> {

    @Override
    public List<Float> call() throws Exception{
        // ANALYZE and extract song beats
        Main.imageHandler.peaksList = extractBeats(Main.imageHandler.selectedSong);
        Main.actionResolver.Toast("SONG ANALYSIS COMPLETE", 0);

        // WRITE peaks read from the wave file into a txt file
        Main.actionResolver.writePeaks(Main.imageHandler.peaksList, Main.imageHandler.selectedSong.nameWithoutExtension() + ".txt");
        Main.actionResolver.Toast("PEAKS WRITTEN", 0);

        return Main.imageHandler.peaksList;
    }

    public static boolean hasAnalyzed(FileHandle selectedSong){
        return Gdx.files.local(selectedSong.nameWithoutExtension() + ".txt").exists();
    }

    public static void dispose(){
        if (Main.imageHandler.currentPlaying != null){
            Main.imageHandler.currentPlaying.setLooping(false);
            Main.imageHandler.currentPlaying.stop();
            Main.imageHandler.currentPlaying.dispose();
        }
        if (Main.imageHandler.songAnalyzer != null){
            Main.imageHandler.songAnalyzer.dispose();
        }
    }

    private List<Float> extractBeats(FileHandle fileHandle){
        List<Float> peaks;// = new ArrayList<Double>();

        // Open the wav file specified as the first argument
        File file = fileHandle.file();

        switch (fileHandle.extension()){
            case "wav":
                try
                {
                    Wave.WavFile wavFile = Wave.WavFile.openWavFile(file);

                    // Display information about the wav file
                    wavFile.display();

                    // Get the number of audio channels in the wav file
                    final int NUM_CHANNELS = wavFile.getNumChannels();

                    // Create a buffer of 1000 frames
                    final int NUM_FRAMES = 1000;
                    final long NUM_BLOCKS = (wavFile.getNumFrames()/NUM_FRAMES)+5;
                    double[] buffer = new double[NUM_FRAMES * NUM_CHANNELS];

                    int framesToRead = NUM_FRAMES;
                    double min = Double.MAX_VALUE;
                    double max = Double.MIN_VALUE;

                    double[] mins = new double[(int)NUM_BLOCKS];
                    double[] maxs = new double[(int)NUM_BLOCKS];

                    // =========================================
                    // Read file and find out MIN and MAX values
                    // =========================================
                    int maxIterator = 0;
                    do
                    {
                        if (maxIterator>3600){
                            System.out.println();
                        }

                        maxs[maxIterator] = -10;
                        // Read frames into buffer
                        framesToRead = wavFile.readFrames(buffer, framesToRead);

                        // Loop through frames and look for minimum and maximum value
                        for (int s=0 ; s<framesToRead * NUM_CHANNELS ; s++)
                        {

                            if (buffer[s] > maxs[maxIterator]) maxs[maxIterator] = buffer[s];
                            if (buffer[s] < mins[maxIterator]) mins[maxIterator] = buffer[s];
                        }
                        maxIterator++;
//                System.out.println("Buffer_read : " + max + " and min " + min);
                    }
                    while (framesToRead != 0);

                    // Close the wavFile
                    wavFile.close();

                    System.out.println("Wave File reading is successfully finished");
                    System.out.printf("Min: %f, Max: %f\n", min, max);


                    // ====================================================
                    // Read file and extract beats into a list of "seconds"
                    // ====================================================

                    //The RMS value of a sine wave is approximately 0.707 of max peak
                    double rms = max * 0.707;// = 0.216600762;//result of 0.707 * max peak found in the song
                    double[] rmss = new double[maxs.length];
                    for (int i = 0; i<rmss.length; i++){
                        rmss[i] = maxs[i] * 0.707;
                    }
                    peaks = new ArrayList<>();
                    framesToRead = NUM_FRAMES;
                    wavFile = Wave.WavFile.openWavFile(file);
                    buffer = new double[NUM_FRAMES * NUM_CHANNELS];
                    double peakValue = 0;
                    double peakTime = 0;
                    DecimalFormat formatter = new DecimalFormat("#.00");
                    int rmsIterator = 0;
                    do
                    {
                        // Read frames into buffer
                        framesToRead = wavFile.readFrames(buffer, framesToRead);

                        // Loop through frames and look for peaks
                        for (int s=0 ; s < framesToRead * NUM_CHANNELS ; s++)
                        {
                            if (buffer[s] > rmss[rmsIterator]) {
//                                if (buffer[s] > peakValue){
//                                    peakValue = buffer[s];
                                    float p = wavFile.getPosition(s);
                                    if (p-peakTime > 0.333f){
                                        //ADD the peak to list
                                        peaks.add(p);

                                        //reset 'last peak' found
//                                        peakValue = 0;
                                        peakTime = p;
//                                System.out.println("Peak Found (seconds): " + p);
                                    }
//                                }
                            }
                        }
                        rmsIterator++;
                    }
                    while (framesToRead != 0);

                    // Close the wavFile
                    wavFile.close();

                    System.out.println("File wave analyzing is successfully finished!");

                    // Output the analysis
                    System.out.printf("Min: %f, Max: %f\n", min, max);
                    System.out.printf("RMS value: %f \n", rms);
                    System.out.println("Number of peaks found: " + peaks.size());
                    System.out.println("List of peaks (seconds): " + peaks);
                    return peaks;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    //System.err.println(e);
                }
                break;

            case "mp3":
                break;

            case "ogg":
                break;
        }

        return null;
    }
}
