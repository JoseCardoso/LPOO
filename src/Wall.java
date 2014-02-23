
public class Wall {
	private int x,y;
	private boolean maze;
	public Wall(int a, int b){x = a;
	y = b; }
	public int[] getCood()
	{
		int cood[] = new int[2];
		cood[0] = x;
		cood[1] = y;
		return cood;
	}
	
	public void setCood(int xN, int yN)
	{
		x = xN;
		y = yN;
	}
	
	public boolean equals(Object o2)
	{
		Wall w = (Wall) o2;
		if((this.x == w.getCood()[0]) && (this.y == w.getCood()[1]))
			return true;
		else
			return false;
	}
}
