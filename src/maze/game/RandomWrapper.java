package maze.game;

import java.util.Random;


// TODO: Auto-generated Javadoc
/**
 * The Class RandomWrapper.
 * 
 * this class is responsible for generating the random numbers for
 */
public class RandomWrapper implements IRandomWrapper {

	private Random random;

	/**
	 * Instantiates a new random wrapper.
	 */
	public RandomWrapper() {
		random = new Random();
	}

	/**
	 * @see maze.game.IRandomWrapper#getInt()
	 */
	public int getInt() {
		return random.nextInt(5) + 1;
	}

}
