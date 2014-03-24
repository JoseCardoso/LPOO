package maze.game;
import java.util.Random;

import maze.cli.MazeCli;

public class Dragon  extends MazeObject {
	Heroi heroi;
	Aguia aguia;
	private boolean sleep = false;

	private IRandomWrapper random = new RandomWrapper(); // default implementation

	public void setRandomWrapper(IRandomWrapper random) {
		this.random = random;
	}
	

	Dragon(Heroi hero, Aguia aguia)
	{
		heroi = hero;
		this.aguia = aguia;
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

	public boolean eatEagle()
	{
		int[] coordA = aguia.getCood();
		if(!aguia.isFlying() && aguia.isAlive() && (Math.abs(coordA[0]-x) <= 1 && Math.abs(coordA[1]-y) == 0) 
				||  (Math.abs(coordA[0]-x) == 0 && Math.abs(coordA[1]-y) <= 1))
		{
			aguia.kill();
			return true;
		}
		else 
			return false;
	}

	public void moveRandom()
	{
		int x = random.getInt();

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
			if(MazeCli.game.getDiff() == 3)
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
		MazeCli.game.setSpace(x, y, 'D');
	}
}