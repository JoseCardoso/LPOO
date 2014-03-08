package mazeGame;
import java.util.Random;
import mazeCli.MazeCli;

public class Espada extends MazeObject {

	public void pos() 
	{
		Random r = new Random(); 

		do
		{
			x = r.nextInt(MazeCli.game.getN()-1)+1;
			y = r.nextInt(MazeCli.game.getN()-1)+1;	
		}
		while(MazeCli.game.getSpace(x,y) != ' ');
		//MazeCli.game.setSpace(x, y, 'E');
	}

	public void setCood(int x , int y)
	{
		this.x = x;
		this.y = y;
	}
	
}
