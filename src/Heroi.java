import java.util.Random;



public class Heroi {
	static private int x = 1, y = 1;
	static private boolean Sword = false;	
	
	static public void pos() 
	{
		Random r = new Random(); 

		do
		{
			x = r.nextInt(Labirinto.N-1)+1;
			y = r.nextInt(Labirinto.N-1)+1;	

		}
		while(Labirinto.getSpace(x,y) != ' ' &&  Labirinto.getSpace(x,y) != 'E');

	}
	
	public static void move(String walk)
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
	
	static public void trueMove(int dy, int dx)
	{
		char Valid;
		Valid =Labirinto.getSpace(x+dx, y+dy);
		if (Valid == ' '  || Valid == 'S')
		{
			Labirinto.setSpace(x, y,' ');
			y += dy;
			x += dx;
		}
		if( Valid == 'E')
		{
			Sword = true;
			Labirinto.setSpace(x, y,' ');
			y += dy;
			x += dx;
		}
	}
	
	static public int[] getCoord()
	{
		int coord[] = new int[2];
		coord[0] = x;
		coord[1] = y;		
		return coord;
	}
	
	static public boolean getSword()
	{
		return Sword;		
	}

}
