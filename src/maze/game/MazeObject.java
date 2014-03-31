package maze.game;


public abstract class MazeObject {
	protected int x, y;
	protected MazeGame game;
	
	public abstract void pos(); 
	
	public MazeObject(MazeGame mazeGame)
	{
		game = mazeGame;
	}
	
	public int[] getCoord()
	{
		int cood[] = new int[2];
		cood[0] = x;
		cood[1] = y;
		return cood;
	}
	
	public void setCood(int x, int y)
	{
		this.x = x;
		this.y = y;		
	}
}
