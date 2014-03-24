package maze.game;
import java.util.Random;

public class Saida {
	private int x , y;
	private MazeGame game;
	
	public Saida(MazeGame mazeGame) {
		game = mazeGame;
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

	public void pos(int x, int y)//posição definida
	{
		this.x = x;
		this.y = y;
	}
	
	public int[] getCood() {
		int cood[] = new int[2];
		cood[0] = x;
		cood[1] = y;
		return cood;
	}

	public void setCood(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
}
