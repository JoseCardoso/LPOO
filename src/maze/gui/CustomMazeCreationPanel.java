package maze.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


// TODO: Auto-generated Javadoc
/**
 * The Class CustomMazeCreationPanel.
 * 
 * this class is responsible for maze customization graphical representation
 */
public class CustomMazeCreationPanel extends JPanel   {

	private static final long serialVersionUID = 1L;
	private char[][] maze;
	private char[][] emptyMaze;
	private CustomMaze cM;
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
	private int size;
	private int nDragons;
	private boolean availableExit;
	private boolean availableHero;
	private boolean availableSword;




	/**
	 * Instantiates a new custom maze creation panel.
	 *
	 * @param size the size
	 * @param cM the custom maze
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public CustomMazeCreationPanel(int size, CustomMaze cM) throws IOException {

		this.size = size;
		this.cM = cM;
		nDragons = 0;
		maze = new char[size][size];
		emptyMaze = new char[size][size];
		availableExit = true;
		availableHero = true;
		availableSword = true;
		for(int i = 0; i < size;i++)
			for(int j = 0; j < size;j++)
				maze[i][j] = 'X';
		addMouseListener(new MouseListener());
		setFocusable(true);
		requestFocus();
		loadImage();

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
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g2d = (Graphics2D) g;

		BufferedImage backtile = pathIMG;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
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


				int dx1 = j * (this.getWidth() / size) ;
				int dx2 = this.getWidth() / size;

				int dy1 = i * (this.getHeight()/ size);
				int dy2 =this.getHeight() / size;

				if (tile != pathIMG && tile != wallIMG)
					g2d.drawImage(backtile, dx1,dy1,dx2,dy2, null);
				g2d.drawImage(tile, dx1,dy1,dx2,dy2, null);


			}
		}

	}

	/**
	 * Prints the lab state.
	 */
	public void printLab() {

		repaint();
	}

	/**
	 * The listener interface for receiving mouse events.
	 * The class that is interested in processing a mouse
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addMouseListener<code> method. When
	 * the mouse event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * this class is responsible for receiving the mouse inputs for custom maze creation
	 *
	 * @see MouseEvent
	 */
	public class MouseListener extends MouseAdapter {


		/**
		 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			int x=e.getX();
			int y=e.getY();
			int dx = x/(getWidth() / size);
			int dy = y/(getHeight() / size) ;


			maze[dy][dx] = getChar((String)cM.getObjects().getSelectedItem(),maze[dy][dx],dx,dy);
			repaint();
		}
	}

	private char exitRequirements(char previous,int dx, int dy)
	{
		if(!availableExit)
		{
			JOptionPane.showMessageDialog(null, "You can Only have 1 exit!");	
			return previous;
		}
		else
		{
			if(previous == 'X')
			{
				if( (dx == 0 || dx == size-1)
						||
						(dy == 0 || dy == size-1) )
				{
					if(dy != dx) 
					{
						availableExit = false;
						return  'S';
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Your exit can't be on a corner!");
						return previous;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Your exit must be on a border!");
					return previous;
				}					
			}
			else if(previous == 'S')
				return  'X';
			else
			{
				JOptionPane.showMessageDialog(null, "Your exit must be on a wall!");
				return previous;
			}
		}

	}

	private char heroRequirements(char previous)
	{
		if(previous == 'H')
			return ' ';
		if(availableHero)
			if(previous != 'X' && previous != 'S')
			{
				availableHero = false;
				return 'H';
			}
			else if(previous == 'D')
			{
				nDragons--;
				return 'H';
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Your hero must be on path!");
				return previous;
			}
		else
		{
			JOptionPane.showMessageDialog(null, "You can only have 1 hero!");
			return previous;
		}
	}

	private char pathRequirements( char previous, int dx, int dy)
	{
		if( (dx == 0 || dx == size-1)
				||
				(dy == 0 || dy == size-1) )
		{

			JOptionPane.showMessageDialog(null, "Your path cannot be on a border!");
			return previous;
		}
		else
		{
			if(previous == ' ')
			{	
				emptyMaze[dx][dy] = 'X';
				return 'X';
			}
			else if(previous == 'D')
			{
				nDragons--;
				return ' ';
			}
			else
			{
				emptyMaze[dx][dy] = ' ';
				return ' ';
			}
		}					
	}

	private char dragonRequirements(char previous)
	{
		if(previous == 'D')
		{
			nDragons--;
			return ' ';
		}
		if(previous != 'X' && previous != 'S')
		{
			nDragons++;
			return 'D';
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Your dragon must be on path!");
			return previous;
		}
	}

	private char swordRequirements(char previous)
	{
		if (previous == 'E')
		{
			availableSword = true;
			return ' ';
		}
		if(availableSword)
			if(previous == 'D')
			{
				nDragons--;
				return 'E';
			}
			else if(previous != 'X' && previous != 'S')
			{
				availableSword = false;
				return 'E';
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Your sword must be on path!");
				return previous;
			}
		else
		{
			JOptionPane.showMessageDialog(null, "You can only have 1 sowrd!");
			return previous;
		}
	}

	private char wallRequirements(char previous,int dx, int dy)
	{
		if(previous == 'D')
		{
			nDragons--;
			return 'X';
		}
		else if(previous == 'X' && 
				!( (dx == 0 || dx == size-1)
						||
						(dy == 0 || dy == size-1) ))
		{
			emptyMaze[dx][dy] = ' ';
			return ' ';
		}
		else
		{
			emptyMaze[dx][dy] = ' ';
			return 'X';
		}
	}

	private char getChar(String tile,char previous,int dx, int dy)
	{
		if(previous == 'S')
			availableExit = true;
		if(previous == 'H')
			availableHero = true;
		if(previous == 'E')
			availableSword = false;

		if(tile.equals("Dragon"))
			return dragonRequirements(previous);
		else if(tile.equals("Hero"))
			return  heroRequirements(previous);
		else if(tile.equals("Sword"))
			return swordRequirements(previous);
		else if(tile.equals("Exit"))
		{
			return exitRequirements(previous,dx,dy);
		}
		else if(tile.equals("Path"))
			return pathRequirements(previous, dx, dy);
		else
			return wallRequirements(previous, dx, dy);
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
		maze = new char[size][size];
		emptyMaze = new char[size][size];
		availableExit = true;
		availableHero = true;
		availableSword = true;
		nDragons = 0;
		for(int i = 0; i < size;i++)
			for(int j = 0; j < size;j++)
				maze[i][j] = 'X';
		repaint();
	}

	/**
	 * Gets the maze size.
	 *
	 * @return the maze size
	 */
	public int getMazeSize() {
		return size;
	}

	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public char[][] getMaze() {
		return maze;
	}

	/**
	 * Gets the empty maze.
	 *
	 * @return the empty maze
	 */
	public char[][] getEmptyMaze() {
		return emptyMaze;
	}

	/**
	 * Ready to save.
	 *
	 * @return true, if successful
	 */
	public boolean readyToSave() {
		// TODO Auto-generated method stub
		return (!availableExit && !availableHero && !availableSword && (nDragons > 0));
	}


}
