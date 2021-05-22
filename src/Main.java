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
 *
 */
public class Main {
	
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
		
//		JOptionPane.showMessageDialog(window,
//				"Space: Punch (Get Close to enemy to hit)" + "\n" + "D to Dash" + "\n"
//						+ "Arrow Keys to Move." + "\n" + "If you can't move the player, click the window" +"\n" +"Good Luck!");
		
		canvas.requestFocus();
		try
        {
            MusicPlayer startMusic = new MusicPlayer("music\\StartScreenMusic.wav");
            MusicPlayer normalMusic = new MusicPlayer("music\\NormalTheme.wav");
            MusicPlayer enemyMusic = new MusicPlayer("music\\EnemyMusic.wav");
            while(!drawing.gameScreenActive()) {
            	startMusic.play();
            }
            startMusic.pause();
            normalMusic.play();
            while(true) {
            	Screen cGameScreen = drawing.getActiveScreen();
            	if(cGameScreen instanceof GameScreen) {
            		ArrayList<Enemy> enemies=((GameScreen) cGameScreen).getEnemies();
            		Hero hero=((GameScreen) cGameScreen).getHero();
            		for(int i=0;i<enemies.size();i++) {
            			if(enemies.get(i).heroInRange(hero)) {
            				normalMusic.pause();
            				enemyMusic.play();
            				break;
            			}
            			else {
            				enemyMusic.pause();
            				normalMusic.play();
            			}
            		}
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
