package maze.game;

// TODO: Auto-generated Javadoc
/**
 * The Class Eagle.
 *
 * A maze object representing the eagle
 */
public class Eagle extends MazeObject{

	private static final long serialVersionUID = 1L;
	private Hero hero;
	private Sword sword;
	private int xDeparture, yDeparture, xObjective, yObjective;
	private boolean withSword = false, alive = true, flying = false, arrived = false;

	
	/**
	 * Instantiates a new eagle.
	 *
	 * @param mazeGame the maze game
	 */
	public Eagle(MazeGame mazeGame) {
		super(mazeGame);
		hero = game.getHero();
		sword = game.getSword();
	}

	/**
	 * @see maze.game.MazeObject#pos()
	 */
	public void pos()
	{
		int[] coodH = hero.getCoord();
		x = coodH[0];
		y = coodH[1];
		xObjective = sword.getCoord()[0];
		yObjective = sword.getCoord()[1];
	}

	/**
	 * Launch.
	 * 
	 * Starts eagle movement
	 */
	public void launch()
	{
		arrived = false;
		flying = true;
		xDeparture = hero.getCoord()[0];
		yDeparture = hero.getCoord()[1];
	}

	/**
	 * Move.
	 * 
	 * makes the eagle move, pick sword, land depending on the game state
	 * 
	 */
	public void move()
	{
		int dx = xObjective - x;
		int dy = yObjective - y;
		boolean timeToChange = false;
		if(x == xDeparture && xDeparture == xObjective && y == yDeparture && y == yObjective)
		{
			arrived = true;
			flying = false;
		}
		else
			flying = true;
		if(hero.isWithEagle())
		{
			x = hero.getCoord()[0];
			y = hero.getCoord()[1];
		}
		else
		{
			if(x == sword.getCoord()[0] &&  y== sword.getCoord()[1] && dx == 0 && dy == 0)
			{
				flying = false;
				timeToChange = true;
				foundSword();

			}

			else if (Math.abs(dx) > Math.abs(dy))
			{
				flight(dx/Math.abs(dx),0);
			}
			else if(dy !=0)
			{
				flight(0,dy/Math.abs(dy));
			}

			if(timeToChange)
			{
				xObjective = xDeparture;
				yObjective = yDeparture;
			}
		}
	}

	/**
	 * Sets the departure coords.
	 *
	 * @return true, if hero doesn't have the sword , false if he already has
	 */
	public boolean setDeparture()
	{
		if (!hero.hasSword())
		{
			xDeparture = hero.getCoord()[0];
			yDeparture = hero.getCoord()[1];
			return true;
		}
		else
			return false;
	}

	/**
	 * Found sword.
	 *
	 * @return true, if founds sword and picks it, false if not
	 */
	public boolean foundSword()
	{
		if(sword.getCoord()[0] == x && sword.getCoord()[1]  == y)
			withSword = true;
		return withSword;
	}

	/**
	 * Checks if is with sword.
	 *
	 * @return withSword, true if is with sword , false if not
	 */
	public boolean isWithSword()
	{
		return withSword;
	}

	/**
	 * Checks if is alive.
	 *
	 * @return true, if is alive , false, if not
	 */
	public boolean isAlive()
	{
		return alive;
	}

	/**
	 * Checks if has arrived.
	 *
	 * @return arrived, true if has arrived to hero, false if not
	 */
	public boolean hasArrived() {
		return arrived;
	}

	/**
	 * Sets the arrived.
	 *
	 * @param arrived true if has arrived to hero, false if not
	 */
	public void setArrived(boolean arrived) {
		this.arrived = arrived;
	}

	/**
	 * Kill.
	 * 
	 * kills the eagle
	 */
	public void kill()
	{
		if (withSword)
		{
			sword.setCoord(x, y);
		}

		alive = false;


	}

	/**
	 * Checks if is flying.
	 *
	 * @return true, if is flying
	 */
	public boolean isFlying()
	{
		return flying;
	}

	private void flight(int dx, int dy){
		if (alive)
		{
			y += dy;
			x += dx;
		}

	}
	
}
