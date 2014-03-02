package mazeGame;
import java.util.Random;

import mazeCli.MazeCli;

public class Dragon  extends MazeObject {
	Heroi heroi;
	private boolean sleep = false;

	Dragon(Heroi hero)
	{
		heroi = hero;
	}
	
	public void pos() 
	{
		Random r = new Random(); 
		int N = MazeCli.game.getN();
		do
		{
			x = r.nextInt(N-2)+1;
			y = r.nextInt(N-2)+1;	
		}
		while(MazeCli.game.getSpace(x,y) != ' ' || eat() );
		MazeCli.game.setSpace(x, y, 'D');
	}

	public boolean eat()
	{
		int[] coordH = heroi.getCood();
		if((Math.abs(coordH[0]-x) <= 1 && Math.abs(coordH[1]-y) == 0) 
				||  (Math.abs(coordH[0]-x) == 0 && Math.abs(coordH[1]-y) <= 1))
			return true;
		else 
			return false;
	}

	public void moveRandom()
	{
		Random r = new Random();
		int x = r.nextInt(6);
		switch(x)
		{
		case 1:
			if(!sleep)
				move(0, 1);
			break;
		case 2:
			if(!sleep)
				move(1,0);
			break;
		case 3:
			if(!sleep)
				move(0, -1);
			break;
		case 4:
			if(!sleep)
				move(-1,0);
			break;
		case 5:
			sleep = !sleep;
			break;
		default:
			break;
		}
	}

	public boolean getSleep()
	{
		return sleep;
	}

	public void move(int dx, int dy)
	{
		char Valid;
		Valid =MazeCli.game.getSpace(x+dx, y+dy);
		if (Valid == 'X')
			moveRandom();
		else if (Valid == ' ' || Valid == 'S' || Valid == 'E')
		{
			MazeCli.game.setSpace(x, y,' ');
			y += dy;
			x += dx;
		}
	}
}