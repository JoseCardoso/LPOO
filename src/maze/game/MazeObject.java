package maze.game;

import java.io.ObjectOutputStream;
import java.io.Serializable;



public abstract class MazeObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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

	public void setCoord(int x, int y)
	{
		this.x = x;
		this.y = y;	
	}

	void writeObject(ObjectOutputStream os)
	{
		try { 
			os.writeObject(this); 
		} 
		catch (Exception e) {  } 
		finally {
		}
	}
}
