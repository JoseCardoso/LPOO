package maze.game;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Dragon  extends MazeObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Heroi heroi;
	private Aguia aguia;
	private boolean sleep = false;

	transient private IRandomWrapper random = new RandomWrapper(); // default implementation

	public void setRandomWrapper(IRandomWrapper random) {
		this.random = random;
	}
	

	Dragon(MazeGame mazeGame)
	{
		super(mazeGame);
		heroi = game.getHero();
		aguia = game.getAguia();
	}

	public void pos() 
	{
		Random r = new Random(); 
		int N = game.getN();
		do
		{
			x = r.nextInt(N-2)+1;
			y = r.nextInt(N-2)+1;	
		}
		while(game.getSpace(x,y) != ' ' || eat() );
	}

	public boolean eat()
	{
		int[] coordH = heroi.getCoord();
		if((Math.abs(coordH[0]-x) <= 1 && Math.abs(coordH[1]-y) == 0) 
				||  (Math.abs(coordH[0]-x) == 0 && Math.abs(coordH[1]-y) <= 1))
			return true;
		else 
			return false;
	}

	public boolean eatEagle()
	{
		int[] coordA = aguia.getCoord();
		if(!aguia.isFlying() && aguia.isAlive() && ((Math.abs(coordA[0]-x) <= 1 && Math.abs(coordA[1]-y) == 0) 
				||  (Math.abs(coordA[0]-x) == 0 && Math.abs(coordA[1]-y) <= 1)))
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
			if(game.getDiff() == 3)
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
		Valid =game.getSpace(x+dx, y+dy);
		if (Valid == 'X')
			moveRandom();
		else if (Valid == ' ' || Valid == 'S' || Valid == 'E')
		{
			y += dy;
			x += dx;
		}
	}
	
}