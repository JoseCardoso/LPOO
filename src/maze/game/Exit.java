package maze.game;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Exit.
 * 
 * A maze object representing the exit
 */
public class Exit extends MazeObject{
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new saida.
	 *
	 * @param mazeGame the maze game
	 */
	public Exit(MazeGame mazeGame) {
		super(mazeGame);
	}

	/**
	 * @see maze.game.MazeObject#pos()
	 */
	public void pos()
	{
		Random r = new Random();
		int N = game.getN();
		do {
			int tmp = r.nextInt(2);
			switch (tmp) {
			case 0:
				x = r.nextInt(2) * (N - 1);
				y = r.nextInt(N);
				break;
			case 1:
				y = r.nextInt(2) * (N - 1);
				x = r.nextInt(N);
				break;
			}
		} while (game.getSpace(x + 1, y) != ' ' && game.getSpace(x - 1, y) != ' '
				&& game.getSpace(x, y + 1) != ' ' && game.getSpace(x + 1, y) != ' ');
	}

}
