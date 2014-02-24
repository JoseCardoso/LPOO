import java.util.Random;

public class Saida {
	private int x , y;
	public void pos()
	{
		Random r = new Random();
		int N = MazeCli.game.getN();
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
		} while (MazeCli.game.getSpace(x + 1, y) != ' ' && MazeCli.game.getSpace(x - 1, y) != ' '
				&& MazeCli.game.getSpace(x, y + 1) != ' ' && MazeCli.game.getSpace(x + 1, y) != ' ');
	}

	public int[] getCood() {
		int cood[] = new int[2];
		cood[0] = x;
		cood[1] = y;
		return cood;
	}
}
