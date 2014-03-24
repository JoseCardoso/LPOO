package maze.game;

public class MockedRandomWrapper implements IRandomWrapper {

	private int theInt;    

	public MockedRandomWrapper(int theInt) {
		this.theInt = theInt;
	}

	public int getInt() { 
		return theInt;
	}

}
