package maze.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Launch the application.
 * 
 * Start graphic layout
 */
public class Start {
	public static void main(String[] args) throws IOException {
		GFrame window = new GFrame(1,1,7,true, KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_SPACE);
		window.start();
	}
}
