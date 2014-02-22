import java.util.Random;


public class Dragon {
	static private int x = 7, y = 8;

	static public boolean eat()
	{
		int[] coordH = Heroi.getCoord();
		if((Math.abs(coordH[0]-x) <= 1 && Math.abs(coordH[1]-y) == 0) 
				||  (Math.abs(coordH[0]-x) == 0 && Math.abs(coordH[1]-y) <= 1))
			return true;
		else 
			return false;
	}

	static public int[] getCood()
	{
		int cood[] = new int[2];
		cood[0] = x;
		cood[1] = y;
		return cood;
	}

	static public void moveRandom()
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

	static public void move(int dx, int dy)
	{
		char Valid;
		Valid =Labirinto.getSpace(x+dx, y+dy);
		if (Valid == ' ' || Valid == 'S' || Valid == 'E')
		{
			Labirinto.setSpace(x, y,' ');
			y += dy;
			x += dx;
		}
	}
}