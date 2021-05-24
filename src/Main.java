import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

/**
 * Main class that runs the program
 * @author alex_zheng
 * @version 5.23.21
 */
public class Main {
	
	/**
	 * The main method which shows the window and plays music.
	 * @param args A user input array of Strings
	 */
	public static void main(String args[])  {
		
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(800, 500);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		
		
		canvas.requestFocus();
		
		try
        {
            MusicPlayer startMusic = new MusicPlayer("music\\StartScreenMusic.wav");
            MusicPlayer normalMusic = new MusicPlayer("music\\NormalTheme.wav");
            MusicPlayer enemyMusic = new MusicPlayer("music\\EnemyMusic.wav");
            MusicPlayer endMusic = new MusicPlayer("music\\EndScreenMusic.wav");
            
            
            while(true) {
            	Screen cGameScreen = drawing.getActiveScreen();
            	if(cGameScreen instanceof GameScreen) {	// if the active screen is game screen
            		if (startMusic.isPlaying())
            			startMusic.pause();
            		ArrayList<Enemy> enemies=((GameScreen) cGameScreen).getEnemies();
            		Hero hero=((GameScreen) cGameScreen).getHero();
            		boolean enemyMusicShouldPlay = false;
            		for(int i=0; i < enemies.size(); i++) {
            			if(enemies.get(i)!=null && enemies.get(i).heroInRange(hero)) {
            				enemyMusicShouldPlay = true;
            			}
            		}
            		
        			if (enemyMusicShouldPlay) {
        				if (normalMusic.isPlaying())
        					normalMusic.pause();
        				
        				enemyMusic.play();
        			} else {
        				if (enemyMusic.isPlaying())
        					enemyMusic.pause();
        				
        				normalMusic.play();
        			}


            	} else if (cGameScreen instanceof VictoryScreen){	// if active screen is not game screen
            		if (normalMusic.isPlaying())
            			normalMusic.pause();
            		
            		if (enemyMusic.isPlaying())
            			enemyMusic.pause();
            		
            		if (startMusic.isPlaying()) 
            			startMusic.pause();
            		
            		endMusic.play();
            		
            	} else {
            		if (normalMusic.isPlaying())
            			normalMusic.pause();
            		
            		if (enemyMusic.isPlaying())
            			enemyMusic.pause();
            		
            		startMusic.play();
            	}
            }
            
        } 
          
        catch (Exception ex) 
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
          
        }
	}

}
