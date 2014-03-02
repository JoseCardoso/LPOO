package mazeGame;
import java.util.Random;
import mazeCli.MazeCli;



public class Heroi extends MazeObject{
	private boolean Sword = false;	
	
	public void pos() 
	{
		Random r = new Random(); 
		int N = MazeCli.game.getN();
		
		do
		{
			x = r.nextInt(N-1)+1;
			y = r.nextInt(N-1)+1;	
		}
		while(MazeCli.game.getSpace(x,y) != ' ' );
		MazeCli.game.setSpace(x, y, 'H');
	}
	
	public void move(String walk)
	{
		char Valid;
		if ( walk.equals("a") || walk.equals("A"))
			trueMove(0,-1);
		else if ( walk.equals("D") || walk.equals("d"))
			trueMove(0,1);
		else if (  walk.equals("W") || walk.equals("w"))
			trueMove(-1,0);
		else if ( walk.equals("S") || walk.equals("s"))
			trueMove(1,0);
	}
	
	public void trueMove(int dy, int dx)
	{
		char Valid;
		Valid =MazeCli.game.getSpace(x+dx, y+dy);
		if (Valid == ' '  || Valid == 'S')
		{
			MazeCli.game.setSpace(x, y,' ');
			y += dy;
			x += dx;
		}
		if( Valid == 'E')
		{
			Sword = true;
			MazeCli.game.setSpace(x, y,' ');
			y += dy;
			x += dx;
		}
	}
	
	public boolean getSword()
	{
		return Sword;		
	}
}
