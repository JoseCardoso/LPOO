package maze.game;


import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Heroi.
 * 
 * A maze object representing the hero
 * 
 */
public class Hero extends MazeObject {
	
	private static final long serialVersionUID = 1L;
	private boolean sword = false;
	private Eagle eagle;
	private boolean withEagle = true;
	
	/**
	 * Instantiates a new hero.
	 *
	 * @param mazeGame the maze game
	 */
	public Hero(MazeGame mazeGame) {
		super(mazeGame);
		eagle = game.getAguia();
	}

	/**
	 * @see maze.game.MazeObject#pos()
	 */
	public void pos()
	{
		Random r = new Random();
		int N = game.getN();

		do {
			x = r.nextInt(N - 1) + 1;
			y = r.nextInt(N - 1) + 1;
		} while (game.getSpace(x, y) != ' ');
	}

	/**
	 * Move.
	 *
	 * @param walk the walk is a string that gives the direction in which the  hero is going to move
	 */
	public void move(String walk) {
		if (walk.equals("a") || walk.equals("A"))
			trueMove(0, -1);
		else if (walk.equals("D") || walk.equals("d"))
			trueMove(0, 1);
		else if (walk.equals("W") || walk.equals("w"))
			trueMove(-1, 0);
		else if (walk.equals("S") || walk.equals("s"))
			trueMove(1, 0);
		else if ((walk.equals("e") || walk.equals("E")) && withEagle) {
			if (withEagle && !sword) {
				withEagle = false;
				eagle.launch();
			}
		}
	}

	private void trueMove(int dy, int dx) {
		char Valid;
		Valid = game.getSpace(x + dx, y + dy);
		if (Valid == ' ' || 
				(x+dx == game.getSaida().getCoord()[0] &&
						y+dy == game.getSaida().getCoord()[1] )
						||(
						x+dx == game.getEspada().getCoord()[0] &&
						y+dy == game.getEspada().getCoord()[1] )) 
		{
			y += dy;
			x += dx;
		}
		if (x == game.getEspada().getCoord()[0] &&
				y == game.getEspada().getCoord()[1])//testa caso o heroi esteja em cima da espada e nao a tenha apanhado
		{
			sword = true;
		}
	}

	/**
	 * Pick up eagle.
	 *
	 * @return true, if hero picks eagle
	 * 	 
	 */
	public boolean pickUpEagle() {

		if (((Math.abs(eagle.getCoord()[0] - x) <= 1 && Math
				.abs(eagle.getCoord()[1] - y) == 0) || (Math
				.abs(eagle.getCoord()[0] - x) == 0 && Math
				.abs(eagle.getCoord()[1] - y) <= 1))
				&& eagle.isAlive() && !eagle.isFlying()) 
		{
			withEagle = true;
			if (eagle.isWithSword())
				sword = true;
		
			return true;
		} else
			return false;

	}


	/**
	 * Checks if Hero is with eagle.
	 *
	 * @return true, if is with eagle
	 */
	public boolean isWithEagle() {
		return withEagle;
	}

	/**
	 * checks if hero has sword.
	 *
	 * @return true, if hero has sword
	 */
	public boolean hasSword() {
		return sword;
	}

	/**
	 * Sets the eagle.
	 *
	 * @param eagle the new eagle
	 */
	public void setEagle(Eagle eagle) {
		this.eagle = eagle;
	}

	/**
	 * Sets if the hero has eagle.
	 *
	 * @param withEagle true if hero has eagle, false if hero doens't has eagle
	 */
	public void setWithEagle(boolean withEagle) {
		this.withEagle = withEagle;
	}

	/**
	 * Sets the sword.
	 *
	 * @param Sword true if hero has sword, false if he doesn't
	 */
	public void setSword(boolean Sword) {
		this.sword = Sword;
	}
	
}
