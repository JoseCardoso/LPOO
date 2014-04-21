package maze.gui;

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

// TODO: Auto-generated Javadoc
/**
 * The Class GPanel.
 * 
 * this class is responsible to represent graphic interface
 * 
 * 
 */
public class GPanel extends JPanel   {

	
	private static final long serialVersionUID = 1L;

	/** The game. */
	MazeGame game = new MazeGame();
	private char[][] maze = new char[game.getN()][game.getN()];
	private char[][] emptyMaze = new char[game.getN()][game.getN()];
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

	/**
	 * Instantiates a new g panel.
	 *
	 * @param gF the game frame
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public GPanel(GFrame gF) throws IOException {
		this.gF = gF;
		
		addKeyListener(new MyKeyboardAdapter());
		setFocusable(true);
		requestFocus();
		loadImage();

	}


	/**
	 * Start game.
	 */
	public void startGame()
	{		
		game.autoGen(gF.nM, gF.nD, gF.nDf, gF.mRandom);
		gF.frmDbzBattle.setResizable(true);
		setVisible(true);
		game.update("");
		repaint();
		requestFocus();


	}

	/**
	 * Start game with a specific game
	 *
	 * @param game the game
	 */
	public void startGame(MazeGame game)
	{


		this.game = game;
		setVisible(true);
		gF.frmDbzBattle.setResizable(true);
		game.update("");
		repaint();
		requestFocus();


	}



	/**
	 * Start game with a maze and difficulty
	 *
	 * @param obj the maze
	 * @param diff the difficulty
	 */
	public void startGame(char[][] obj,int diff) {
		// TODO Auto-generated method stub
		game.create(obj,diff);
		gF.frmDbzBattle.setResizable(true);
	}


	/**
	 * Loads sprites.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	/**
	 * The Class MyKeyboardAdapter.
	 * 
	 * this class is responsible to receive user inputs
	 */
	public class MyKeyboardAdapter extends KeyAdapter {
		
		/**
		 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
		 */
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
					gF.frmDbzBattle.dispose();
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
					gF.frmDbzBattle.dispose();
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

	/**
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g2d = (Graphics2D) g;
		maze = game.getLab().updateLab();
		emptyMaze = game.getLab().getEmptyMaze();
		BufferedImage tile = wallIMG;



		for (int i = 0; i < emptyMaze.length; i++) {
			for (int j = 0; j < emptyMaze.length; j++) {
				if (emptyMaze[i][j] == 'X')
					tile = wallIMG;
				else
					tile = pathIMG;


				int dx1 = j * (this.getWidth() / maze.length) ;
				int dx2 = this.getWidth() / maze.length;

				int dy1 = i * (this.getHeight()/ maze.length);
				int dy2 =this.getHeight() / maze.length;

				g2d.drawImage(tile, dx1,dy1,dx2,dy2, null);
			}
		}

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				tile = pathIMG;
				if (maze[i][j] == 'S')
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

				if (tile != pathIMG)
				{

					int dx1 = j * (this.getWidth() / maze.length) ;
					int dx2 = this.getWidth() / maze.length;

					int dy1 = i * (this.getHeight()/ maze.length);
					int dy2 =this.getHeight() / maze.length;
					
					

					
					if (game.getExit().getCoord()[0] == game.getHero().getCoord()[0] && game.getExit().getCoord()[1] == game.getHero().getCoord()[1])
						g2d.drawImage(exitIMG, dx1,dy1,dx2,dy2, null);
					
					g2d.drawImage(tile, dx1,dy1,dx2,dy2, null);
					
					
						

				}

			}

		}
	}



	/**
	 * Prints the labyrinth.
	 * 
	 */
	public void printLab() {
		maze = game.getLab().getMaze();
		repaint();
	}





}
