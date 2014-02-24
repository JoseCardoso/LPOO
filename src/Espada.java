import java.util.Random;

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
	}
	
	
}
