package maze.game;
import java.util.Random;

public class Espada extends MazeObject {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Espada(MazeGame mazeGame) {
		super(mazeGame);
	}

	public void pos() 
	{
		Random r = new Random(); 

		do
		{
			x = r.nextInt(game.getN()-1)+1;
			y = r.nextInt(game.getN()-1)+1;	
		}
		while(game.getSpace(x,y) != ' ');
		//MazeCli.game.setSpace(x, y, 'E');
	}

	public void setCoord(int x , int y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	
}
