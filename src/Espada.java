import java.util.Random;


public class Espada {
	static private int x = 5, y = 5;

	static public void pos() 
	{
		Random r = new Random(); 

		do
		{
			x = r.nextInt(Labirinto.N-1)+1;
			y = r.nextInt(Labirinto.N-1)+1;	

		}
		while(Labirinto.getSpace(x,y) != ' ');

	}
	
	static public int[] getCood()
	{
		int cood[] = new int[2];
		cood[0] = x;
		cood[1] = y;
		return cood;
	}
}
