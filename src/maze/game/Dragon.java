package maze.game;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Dragon.
 * 
 * a maze object representing a dragon
 */
public class Dragon  extends MazeObject {
	
	private static final long serialVersionUID = 1L;
	private Hero hero;
	private Eagle eagle;
	private boolean sleep = false;
	transient private IRandomWrapper random = new RandomWrapper(); // default implementation

	/**
	 * Instantiates a new dragon.
	 *
	 * @param mazeGame the maze game
	 */
	public Dragon(MazeGame mazeGame)
	{
		super(mazeGame);
		hero = game.getHero();
		eagle = game.getEagle();
	}


	/**
	 * Sets the random wrapper.
	 *
	 * @param random the new random wrapper to be used in dragon movement
	 */
	public void setRandomWrapper(IRandomWrapper random) {
		this.random = random;
	}
	
	/**
	 * Creates the random wrapper.
	 */
	public void createRandomWrapper()
	{
		random = new RandomWrapper();
	}
	

	/**
	 * @see maze.game.MazeObject#pos()
	 */
	public void pos() 
	{
		Random r = new Random(); 
		int N = game.getN();
		do
		{
			x = r.nextInt(N-2)+1;
			y = r.nextInt(N-2)+1;	
		}
		while(game.getSpace(x,y) != ' ' || killHero());
	}

	/**
	 * Kill Hero.
	 *
	 * @return true, if dragon is beside the hero thus killing him
	 */
	public boolean killHero()
	{
		int[] coordH = hero.getCoord();
		if((Math.abs(coordH[0]-x) <= 1 && Math.abs(coordH[1]-y) == 0) 
				||  (Math.abs(coordH[0]-x) == 0 && Math.abs(coordH[1]-y) <= 1))
			return true;
		else 
			return false;
	}

	/**
	 * Kill Eagle.
	 *
	 * @return true, if dragon is beside a landed eagle thus killing it
	 */
	public boolean killEagle()
	{
		int[] coordA = eagle.getCoord();
		if(!eagle.isFlying() && eagle.isAlive() && ((Math.abs(coordA[0]-x) <= 1 && Math.abs(coordA[1]-y) == 0) 
				||  (Math.abs(coordA[0]-x) == 0 && Math.abs(coordA[1]-y) <= 1)))
		{
			eagle.kill();
			return true;
		}
		else 
			return false;
	}

	/**
	 * Move random.
	 * 
	 * Makes the dragon move in a random direction or puts dragon to sleep
	 */
	public void moveRandom()
	{
		int x = random.getInt();

		switch(x)
		{
		case 1:
			if(!sleep)
				move(0, 1);
			break;
		case 2:
			if(!sleep)
				move(1,0);
			break;
		case 3:
			if(!sleep)
				move(0, -1);
			break;
		case 4:
			if(!sleep)
				move(-1,0);
			break;
		case 5:
			if(game.getDiff() == 3)
				sleep = !sleep;
			break;
		default:
			break;
		}
	}

	private void move(int dx, int dy)
	{
		char Valid;
		Valid =game.getSpace(x+dx, y+dy);
		if (Valid == 'X')
			moveRandom();
		else if (Valid == ' ' || Valid == 'S' || Valid == 'E')
		{
			y += dy;
			x += dx;
		}
	}


	/**
	 * Checks if is sleeping.
	 *
	 * @return the sleep
	 * 				true if is sleeping, false if awake
	 */
	public boolean isSleeping()
	{
		return sleep;
	}
	
}