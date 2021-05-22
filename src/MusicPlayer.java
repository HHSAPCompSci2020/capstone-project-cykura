import java.io.File;
import java.io.IOException;
import java.util.Scanner;
  
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class MusicPlayer{
    private Clip clip;
    private AudioInputStream audioInputStream;
    private static String filePath="music\\NormalTheme.wav";
	public MusicPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	        // create AudioInputStream object
	        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
	          
	        // create clip reference
	        clip = AudioSystem.getClip();
	          
	        // open audioInputStream to the clip
	        clip.open(audioInputStream);
	          
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public static void main(String[] args) 
    {
        try
        {
            MusicPlayer audioPlayer = new MusicPlayer();
              
            audioPlayer.play();
        } 
          
        catch (Exception ex) 
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
          
          }
    }
	public void play() {
        clip.start();
    }    
	public void pause() {
        clip.stop();
    }
	public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException 
	{
		clip.stop();
		clip.close();
		resetAudioStream();
		clip.setMicrosecondPosition(0);
		this.play();
	}
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        clip.close();
    }
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(
				new File(filePath).getAbsoluteFile());
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
