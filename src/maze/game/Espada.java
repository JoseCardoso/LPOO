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

	public void pos() //gera a espada num espaço e pelo menos a uma casa do heroi
	{
		Random r = new Random(); 

		do
		{
			x = r.nextInt(game.getN()-1)+1;
			y = r.nextInt(game.getN()-1)+1;	
		}
		while(game.getSpace(x,y) != ' ' 
				&& (game.getHero().getCoord()[0] != x && game.getHero().getCoord()[1] != y)
				&& ((Math.abs(game.getHero().getCoord()[0]-x) <= 1 && Math.abs(game.getHero().getCoord()[1]-y) == 0) 
				||  (Math.abs(game.getHero().getCoord()[0]-x) == 0 && Math.abs(game.getHero().getCoord()[1]-y) <= 1))
				);
		//MazeCli.game.setSpace(x, y, 'E');
	}

	public void setCoord(int x , int y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	
}
