import java.io.File;
import java.io.IOException;
  
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class MusicPlayer{
    private Clip clip;
    private AudioInputStream audioInputStream;
    private static String filePath;
    private boolean isPlaying;
    
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
	
	public void play() {
        clip.start();
        isPlaying = true;
    }
	
	public void pause()
    {
        clip.stop();
        isPlaying = false;
    }
	
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
