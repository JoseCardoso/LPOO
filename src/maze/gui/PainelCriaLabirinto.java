package maze.gui;

import java.awt.Color;
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


public class PainelCriaLabirinto extends JPanel   {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	char[][] maze;
	private LabirintoPersonalizado lP;
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
	private boolean availableExit;
	private boolean availableHero;
	private boolean availableSword;



	public PainelCriaLabirinto(int size, LabirintoPersonalizado lP) throws IOException {

		this.size = size;
		this.lP = lP;
		maze = new char[size][size];
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
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g2d = (Graphics2D) g;

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

				g2d.drawImage(tile, dx1,dy1,dx2,dy2, Color.BLACK, null);
			}
		}

	}


	public void printLab() {

		repaint();
	}

	public void setSize(int size) {
		this.size = size;
	}


	public class MouseListener extends MouseAdapter {


		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int x=e.getX();
			int y=e.getY();
			int dx = x/(getWidth() / size);
			int dy = y/(getHeight() / size) ;


			maze[dy][dx] = getChar((String)lP.objects.getSelectedItem(),maze[dy][dx],dx,dy);
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
		if(availableHero)
			if(previous != 'X' && previous != 'S')
			{
				availableHero = false;
				return 'H';
			}
			else if(previous == 'H')
				return ' ';
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
				return 'X';
			else
				return ' ';
		}					
	}

	private char dragonRequirements(char previous)
	{
		if(previous != 'X' && previous != 'S')
		{
			return 'D';
		}
		else if(previous == 'D')
			return ' ';
		else
		{
			JOptionPane.showMessageDialog(null, "Your dragon must be on path!");
			return previous;
		}
	}

	private char swordRequirements(char previous)
	{
		if(availableSword)
			if(previous != 'X' && previous != 'S')
			{
				availableSword = false;
				return 'E';
			}
			else if (previous == 'E')
				return ' ';
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

		if(previous == 'X' && 
				!( (dx == 0 || dx == size-1)
						||
						(dy == 0 || dy == size-1) ))
			return ' ';
		else
			return 'X';
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


}
