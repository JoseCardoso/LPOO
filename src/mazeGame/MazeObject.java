package mazeGame;


public abstract class MazeObject {
	protected int x, y;
	
	public abstract void pos(); 
	
	public int[] getCood()
	{
		int cood[] = new int[2];
		cood[0] = x;
		cood[1] = y;
		return cood;
	}
}
