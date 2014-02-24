import java.util.Random;

public class Dragon  extends MazeObject {
	Heroi heroi;
	
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
		while(MazeCli.game.getSpace(x,y) != ' ' || eat());
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
		int x = r.nextInt(5);
		switch(x)
		{
		case 1:
			move(0, 1);
			break;
		case 2:
			move(1,0);
			break;
		case 3:
			move(0, -1);
			break;
		case 4:
			move(-1,0);
			break;
		default:
			break;
		}
	}

	public void move(int dx, int dy)
	{
		char Valid;
		Valid =MazeCli.game.getSpace(x+dx, y+dy);
		if (Valid == ' ' || Valid == 'S' || Valid == 'E')
		{
			MazeCli.game.setSpace(x, y,' ');
			y += dy;
			x += dx;
		}
	}
}