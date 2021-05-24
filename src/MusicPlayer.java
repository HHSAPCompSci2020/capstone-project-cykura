import java.io.File;

import java.io.IOException;
  
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The MusicPlayer class represents a music player which can play, pause, and loop clips.
 * @author geeks_for_geeks
 * @version 5.23.21
 *
 */
public class MusicPlayer{
    private Clip clip;
    private AudioInputStream audioInputStream;
    private static String filePath;
    private boolean isPlaying;
    
    /**
     * Creates a Music Player
     * 
     * @param file The music file
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
	public MusicPlayer(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		filePath=file;
	    // create AudioInputStream object
	    audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
	        
	    // create clip reference
	    clip = AudioSystem.getClip();
	       
	    // open audioInputStream to the clip
	    clip.open(audioInputStream);
	         
	    clip.loop(Clip.LOOP_CONTINUOUSLY);
	    clip.stop();
	    isPlaying = false;
	}
	
	/**
	 * Plays the music
	 */
	public void play() {
        clip.start();
        isPlaying = true;
    }
	
	/**
	 * Pauses the music
	 */
	public void pause()
    {
        clip.stop();
        isPlaying = false;
    }
	
	/**
	 * 
	 * @return true if the music is being played
	 */
	public boolean isPlaying() {
		return isPlaying;
	}
	
//	public void setFilePath(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//		filePath=file;
//	    // create AudioInputStream object
//	    audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
//	        
//	    // create clip reference
//	    clip = AudioSystem.getClip();
//	       
//	    // open audioInputStream to the clip
//	    clip.open(audioInputStream);
//	         
//	    clip.loop(Clip.LOOP_CONTINUOUSLY);		
//	}
	
}
