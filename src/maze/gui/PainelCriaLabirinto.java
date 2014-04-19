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
import javax.swing.JPanel;


public class PainelCriaLabirinto extends JPanel   {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char[][] maze;
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


	public PainelCriaLabirinto(int size, LabirintoPersonalizado lP) throws IOException {

		this.size = size;
		this.lP = lP;
		maze = new char[size][size];
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

			maze[dy][dx] = getChar((String)lP.objects.getSelectedItem());
			repaint();
		}



	}

	private char getChar(String tile)
	{
		char ret = ' ';
		if(tile.equals("Dragon"))
			ret = 'D';
		else if(tile.equals("Hero"))
			ret = 'H';
		else if(tile.equals("Sword"))
			ret = 'E';
		else if(tile.equals("Exit"))
			ret = 'S';
		else if(tile.equals("Path"))
			ret = ' ';
		else if(tile.equals("Wall"))
			ret = 'X';

		return ret;
	}


}
