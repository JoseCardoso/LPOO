package maze.game;

// TODO: Auto-generated Javadoc
/**
 * The Class MockedRandomWrapper.
 * 
 * this class creates a determinate "random" number that is used in the tests
 */
public class MockedRandomWrapper implements IRandomWrapper {

	private int theInt;    

	/**
	 * Instantiates a new mocked random wrapper.
	 *
	 * @param theInt
	 */
	public MockedRandomWrapper(int theInt) {
		this.theInt = theInt;
	}

	/**
	 * @see maze.game.IRandomWrapper#getInt()
	 */
	public int getInt() { 
		return theInt;
	}

}
