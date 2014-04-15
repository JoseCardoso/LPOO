package maze.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.game.MazeGame;

public class GPanel extends JPanel   {
	private static final long serialVersionUID = 1L;
	private MazeGame game = new MazeGame();
	private char[][] maze = new char[game.getN()][game.getN()];
	//private GFrame window;
	private Graphics2D g2d;
	private BufferedImage wallIMG;
	private BufferedImage pathIMG;
	private BufferedImage exitIMG;
	private BufferedImage dragonIMG;
	private BufferedImage heroIMG;
	private BufferedImage swordIMG;
	private int upKey = KeyEvent.VK_UP;
	private int leftKey = KeyEvent.VK_LEFT;
	private int rightKey = KeyEvent.VK_RIGHT;
	private int downKey = KeyEvent.VK_DOWN;
	private int sendEagleKey = KeyEvent.VK_SPACE;

	public GPanel() throws IOException {
		//this.window = window;
		addKeyListener(new MyKeyboardAdapter());
		setFocusable(true);
		requestFocus();
		loadImage();
	
	}
public void startGame()
{
	
	game.autoGen(13, 1, 2, true);
	setVisible(true);
	game.update("");
	repaint();
	
	
}
	public void loadImage() throws IOException {
		wallIMG = ImageIO.read(new File("res/wall.png"));
		pathIMG = ImageIO.read(new File("res/path.png"));
		exitIMG = ImageIO.read(new File("res/exit.png"));
		heroIMG = ImageIO.read(new File("res/hero.png"));
		swordIMG = ImageIO.read(new File("res/sword.png"));
		dragonIMG = ImageIO.read(new File("res/dragon.png"));
	}

	public class MyKeyboardAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {


			String dir = "";
			int key = e.getKeyCode();

			if (key == upKey)
				dir = "W";
			if (key == leftKey)
				dir = "A";
			if (key == downKey)
				dir = "S";
			if (key == rightKey)
				dir = "D";
			if (key == sendEagleKey)
				dir = "E";

			
			if  (game.update(dir) == false)
			{
				repaint();
				if (game.heroIsAlive())
				{
					String msg = "You win!";
					JOptionPane.showMessageDialog(getRootPane(), msg);
				}
				else
				{

					String msg = "Game Over!";
					JOptionPane.showMessageDialog(getRootPane(), msg);


				}

				setVisible(false);
			}


			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g2d = (Graphics2D) g;
		maze = game.getLab().updateLab();

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				BufferedImage tile = wallIMG;
				if (maze[i][j] == ' ')
					tile = pathIMG;
				else if (maze[i][j] == 'S')
					tile = exitIMG;
				else if (maze[i][j] == 'H')
					tile = heroIMG;
				else if (maze[i][j] == 'D')
					tile = dragonIMG;
				else if (maze[i][j] == 'E')
					tile = swordIMG;

				int dx1 = j * tile.getWidth();
				int dx2 = dx1 + tile.getWidth();

				int dy1 = i * tile.getHeight();
				int dy2 = dy1 + tile.getHeight();

				g2d.drawImage(tile, dx1, dy1, dx2, dy2, 0, 0, tile.getWidth(),
						tile.getHeight(), null);
			}
		}

	}

	public void printLab() {
		maze = game.getLab().getMaze();
		repaint();
	}

	public int getUpKey() {
		return upKey;
	}

	public void setUpKey(int upKey) {
		this.upKey = upKey;
	}

	public int getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(int leftKey) {
		this.leftKey = leftKey;
	}

	public int getRightKey() {
		return rightKey;
	}

	public void setRightKey(int rightKey) {
		this.rightKey = rightKey;
	}

	public int getDownKey() {
		return downKey;
	}

	public void setDownKey(int downKey) {
		this.downKey = downKey;
	}
}
