
public class Saida {
	static private int x = 5, y = 9;

	Saida(int xN, int yN)
	{
		x = xN;
		y = yN;
	}
	
	static public int[] getCood()
	{
		int cood[] = new int[2];
		cood[0] = x;
		cood[1] = y;
		return cood;
	}
}
