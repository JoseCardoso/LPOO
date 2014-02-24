import java.util.Random;

public class Saida {
	static private int x = 5, y = 9;

	Saida()
	{}

	static public void pos() 
	{
		Random r = new Random(); 

		do
		{
		int tmp= r.nextInt(2);
		switch (tmp)
		{
		case 0:
			x = r.nextInt(2)*(Labirinto.N -1);
			y = r.nextInt(Labirinto.N);	
			break;
		case 1:
			y = r.nextInt(2)*(Labirinto.N -1);
			x = r.nextInt(Labirinto.N);	 
			break;
		}
		}
		while(Labirinto.getSpace(x+1,y) != ' ' && Labirinto.getSpace(x-1,y)!= ' '
				&& Labirinto.getSpace(x,y+1)!= ' ' &&  Labirinto.getSpace(x+1,y) != ' ');
		
	}

	static public int[] getCood()
	{
		int cood[] = new int[2];
		cood[0] = x;
		cood[1] = y;
		return cood;
	}
}
