package maze.game;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Sword.
 * 
 * A maze object representing the sword
 */
public class Sword extends MazeObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new sword.
	 *
	 * @param mazeGame the maze game
	 */
	public Sword(MazeGame mazeGame) {
		super(mazeGame);
	}

	/**
	 * @see maze.game.MazeObject#pos()
	 */
	public void pos() //gera a espada num espaço e pelo menos a uma casa do heroi
	{
		Random r = new Random(); 

		do
		{
			x = r.nextInt(game.getN()-1)+1;
			y = r.nextInt(game.getN()-1)+1;	
		}
		while(game.getSpace(x,y) != ' ' 
				|| ((Math.abs(game.getHero().getCoord()[0]-x) <= 1 && Math.abs(game.getHero().getCoord()[1]-y) == 0) 
						||  (Math.abs(game.getHero().getCoord()[0]-x) == 0 && Math.abs(game.getHero().getCoord()[1]-y) <= 1))
				);
	}

	/**
	 * @see maze.game.MazeObject#setCoord(int, int)
	 */
	public void setCoord(int x , int y)
	{
		this.x = x;
		this.y = y;
	}

}
