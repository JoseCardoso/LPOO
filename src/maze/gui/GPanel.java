package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.game.MazeGame;

public class GPanel extends JPanel   {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MazeGame game = new MazeGame();
	private char[][] maze = new char[game.getN()][game.getN()];
	//private GFrame window;
	private Graphics2D g2d;
	private BufferedImage wallIMG;
	private BufferedImage pathIMG;
	private BufferedImage exitIMG;
	private BufferedImage dragonIMG;
	private BufferedImage eagleIMG;
	private BufferedImage heroIMG;
	private BufferedImage swordIMG;
	private BufferedImage heroSwordIMG;
	private BufferedImage sleepDragonIMG;
	private BufferedImage DragonSwordIMG;
	
	private GFrame gF;
	
	public GPanel(GFrame gF) throws IOException {
		this.gF = gF;
		addKeyListener(new MyKeyboardAdapter());
		setFocusable(true);
		requestFocus();
		loadImage();

	}


	public void startGame()
	{		
		game.autoGen(gF.nM, gF.nD, gF.nDf, gF.mRandom);
		setVisible(true);
		game.update("");
		repaint();
		requestFocus();


	}
	
	public void startGame(MazeGame game)
	{

		
		this.game = game;
		setVisible(true);
		game.update("");
		repaint();
		requestFocus();


	}
	
	
	
	public void startGame(char[][] obj) {
		// TODO Auto-generated method stub
		game.create(obj);
	}


	public void loadImage() throws IOException {
		wallIMG = ImageIO.read(new File("res/wall.png"));
		pathIMG = ImageIO.read(new File("res/path.png"));
		exitIMG = ImageIO.read(new File("res/exit.png"));
		heroIMG = ImageIO.read(new File("res/hero.png"));
		swordIMG = ImageIO.read(new File("res/sword.png"));
		dragonIMG = ImageIO.read(new File("res/dragon.png"));
		DragonSwordIMG = ImageIO.read(new File("res/dragonSword.png"));
		heroSwordIMG = ImageIO.read(new File("res/HeroSword.png"));
		sleepDragonIMG = ImageIO.read(new File("res/SleepDragon.png"));
		eagleIMG = ImageIO.read(new File("res/eagle.png"));

	}

	public class MyKeyboardAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {


			String dir = "";
			int key = e.getKeyCode();

			if (key == gF.savedUpKey)
				dir = "W";
			if (key == gF.savedLeftKey)
				dir = "A";
			if (key == gF.savedDownKey)
				dir = "S";
			if (key == gF.savedRightKey)
				dir = "D";
			if (key == gF.savedSendEagleKey)
				dir = "E";


			if  (game.update(dir) == false)
			{
				repaint();
				if (game.heroIsAlive())
				{
					String msg = "You win!";
					JOptionPane.showMessageDialog(getRootPane(), msg);
					gF.frmFairyTailSclass.dispose();
					try {
						gF = new GFrame(gF.nD ,gF.nDf,gF.nM,gF.mRandom, gF.upKey, gF.rightKey, gF.leftKey, gF.downKey, gF.sendEagleKey);
						gF.start();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{

					String msg = "Game Over!";
					JOptionPane.showMessageDialog(getRootPane(), msg);
					gF.frmFairyTailSclass.dispose();
					try {
						gF = new GFrame(gF.nD ,gF.nDf,gF.nM,gF.mRandom, gF.upKey, gF.rightKey, gF.leftKey, gF.downKey, gF.sendEagleKey);
						gF.start();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
				else if (maze[i][j] == 'a')
					tile = eagleIMG;
				else if (maze[i][j] == 'd')
					tile = sleepDragonIMG;
				else if (maze[i][j] == 'F')
					tile = DragonSwordIMG;
				else if (maze[i][j] == 'A')
					tile = heroSwordIMG;

				int dx1 = j * (this.getWidth() / maze.length) ;
			    int dx2 = this.getWidth() / maze.length;

			    int dy1 = i * (this.getHeight()/ maze.length);
			    int dy2 =this.getHeight() / maze.length;

			    g2d.drawImage(tile, dx1,dy1,dx2,dy2, Color.BLACK, null);
			}
		}

	}

	public void printLab() {
		maze = game.getLab().getMaze();
		repaint();
	}

	
}
