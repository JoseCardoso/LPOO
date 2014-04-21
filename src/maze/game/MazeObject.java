package maze.game;

import java.io.Serializable;



// TODO: Auto-generated Javadoc
/**
 * The Class MazeObject.
 * 
 * This is mother class for all objects in the game
 *
 * This class is generic for all objects
 */
public abstract class MazeObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected int x, y;
	protected MazeGame game;

	/** 
	 * Pos
	 * 
	 * Sets a random position for the object
	 */
	public abstract void pos(); 

	/**
	 * Instantiates a new maze object.
	 *
	 * @param mazeGame the maze game
	 */
	public MazeObject(MazeGame mazeGame)
	{
		game = mazeGame;
	}

	/**
	 * Gets the coords of the MazeObject.
	 *
	 * @return this maze Object coords  The coordinates are given in an array of int's
	 */
	public int[] getCoord()
	{
		int cood[] = new int[2];
		cood[0] = x;
		cood[1] = y;
		return cood;
	}

	/**
	 * Sets the coords of the maze object.
	 *
	 * @param x the new x coord
	 * @param y the new y coord
	 */
	public void setCoord(int x, int y)
	{
		this.x = x;
		this.y = y;	
	}
}
