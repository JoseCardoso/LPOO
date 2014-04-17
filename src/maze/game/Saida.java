package maze.game;
import java.util.Random;

public class Saida extends MazeObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Saida(MazeGame mazeGame) {
		super(mazeGame);
	}

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
