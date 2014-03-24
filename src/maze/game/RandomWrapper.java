package maze.game;

import java.util.Random;


public class RandomWrapper implements IRandomWrapper {

	private Random random;

	public RandomWrapper() {
		random = new Random();
	}

	public int getInt() {
		return random.nextInt(5) + 1;
	}

}
