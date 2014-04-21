package maze.game;


import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class MazeGame.
 * 
 * this class is responsible for gameplay
 */
public class MazeGame implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The number d. */
	private int N, difficulty, numberD;
	
	/** The can exit. */
	private boolean LiveDragon = true, LiveHero = true, canExit = false;
	
	/** The sword. */
	private Sword sword = new Sword(this);
	
	/** The hero. */
	private Hero hero = new Hero(this);
	
	/** The eagle. */
	private Eagle eagle = new Eagle(this);
	
	/** The exit. */
	private Exit exit = new Exit(this);
	
	/** The Dragon list. */
	private ArrayList<Dragon> DragonList = new ArrayList<Dragon>();
	
	/** The lab. */
	private Labyrinth lab = new Labyrinth(this);



	/**
	 * Full generate.
	 *
	 * @param sword the sword
	 * @param hero the hero
	 * @param exit the exit
	 * @param DragonList the dragon list
	 * @param emptyMaze the empty maze
	 * @param maze the maze
	 * 
	 * This function generates the maze with all the objects specified
	 */
	public void fullGenerate(Sword sword,Hero hero, Exit exit,ArrayList<Dragon> DragonList, char[][] emptyMaze, char[][] maze)
	{
		this.sword = sword;
		this.hero = hero;
		eagle = new Eagle(this);
		eagle.pos();
		this.exit = exit;
		this.DragonList  = new ArrayList<Dragon>(DragonList);
		lab = new Labyrinth(this);
		lab.createLab(emptyMaze,maze);
		numberD = DragonList.size();
		lab.updateLab();
	}
	
	/**
	 * Creates the maze.
	 *
	 * @param maze the maze array
	 * @param diff the difficulty
	 */
	public void create(char[][] maze, int diff) {
	
		N = maze.length;
		char[][] emptyMaze = new char[N][N];
		for(int x = 0; x < N; x++)
			for(int y = 0; y < N; y++)
			{
				if(maze[y][x] == 'H')
				{
					hero.setCoord(x,y);
					emptyMaze[y][x] = ' ';
				}
				else if(maze[y][x] == 'E')
				{
					sword.setCoord(x, y);
					emptyMaze[y][x] = ' ';
				}
				else if(maze[y][x] == 'S')
				{
					exit.setCoord(x, y);
					emptyMaze[y][x] = 'X';
				}
				else if(maze[y][x] == 'D')
				{
					numberD++;
					Dragon dragon = new Dragon(this);
					dragon.setCoord(x, y);
					DragonList.add(dragon);
					emptyMaze[y][x] = ' ';
				}					
				else
					emptyMaze[y][x] = maze[y][x];
			}
		
		difficulty = diff;
		eagle.pos();
		hero.setEagle(eagle);
		lab.createLab(emptyMaze, maze);
		lab.setN(N);
		lab.setEagle(eagle);
		lab.updateLab();
		
	}

	/**
	 * Auto generation.
	 *
	 * @param N the size
	 * @param NDragons the number of dragons
	 * @param diff the difficulty
	 * @param choice the choice defines if maze is random or standard
	 *
	 *	this generates a maze with some options defined
	 */
	public void autoGen(int N, int NDragons, int diff, boolean choice) {
		if (!choice)
			this.N = 10;
		else
			this.N = N;
	
		this.numberD = NDragons;
		this.difficulty = diff;
		generate(choice);
	
	}

	/**
	 * Generates the actual maze.
	 *
	 * @param choice the choice
	 */
	public void generate(boolean choice) {
		// choice -false = standard
		lab.setN(N);
		if (choice)
			lab.createLab();
		else
			lab.createLab(lab.getStandardMaze());
		hero.pos();
		sword.pos();
		eagle.pos();
		hero.setEagle(eagle);
		exit.pos();
		lab.setEagle(eagle);
		for (int i = 0; i < numberD; i++)// contruie a lista, menos ou igual
		{
			Dragon temp = new Dragon(this);
			temp.pos();
			DragonList.add(temp);
		}
		lab.updateLab();
	}

	/**
	 * Update.
	 * 
	 * this function updates the game state
	 *
	 * @param dir the direction in which hero will move
	 * @return true, if successful
	 */
	public boolean update(String dir) {
	
		hero.move(dir);
		eagle.move();
		if (difficulty != 1)
			for (int i = 0; i < numberD; i++)
				DragonList.listIterator(i).next().moveRandom();
		searchHeroToKill();
		searchEagleToKill();
		hero.pickUpEagle();
		if (hero.getCoord()[0] == exit.getCoord()[0]
				&& hero.getCoord()[1] == exit.getCoord()[1])
			canExit = true;
		else
			canExit = false;
		if (!LiveHero || (!LiveDragon && canExit))
			return false;
		else
			return true;
	}

	/**
	 * Search hero to kill
	 * 
	 * goes through the arrayList of dragons to see if a dragon kills the hero
	 */
	public void searchHeroToKill() {
		for (int i = 0; i < numberD; i++) {
			DragonList.listIterator(i).next();
			if (DragonList.listIterator(i).next().killHero()) {
				if (hero.hasSword()) {
					DragonList.remove(i);
					numberD--;
				} else if (!DragonList.listIterator(i).next().isSleeping())
					LiveHero = false;
			}
		}
		if (numberD == 0)
			LiveDragon = false;
	}

	/**
	 * Search Eagle to kill.
	 * 
	 * goes through the arrayList of dragons to see if a dragon kill the eagle
	 */
	public void searchEagleToKill() {
		for (int i = 0; i < numberD; i++)
			DragonList.listIterator(i).next().killEagle();
	}
	
	/**
	 * creates random Wrapper for each dragon.
	 */
	public void createRandomWrapper() 
	{
		for (int i = 0; i < numberD; i++)
			DragonList.listIterator(i).next().createRandomWrapper();
	}

	/** 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String res = "";

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				res += lab.getMaze()[i][j] + " ";
			}

			res += "\n";
		}

		return res;
	}

	/**
	 * Gets the difficulty.
	 *
	 * @return the diff
	 */
	public int getDiff() {
		return difficulty;
	}

	/**
	 * Gets the eagle.
	 *
	 * @return the eagle
	 */
	public Eagle getEagle() {
		return eagle;
	}

	/**
	 * Gets the lab.
	 *
	 * @return the lab
	 */
	public Labyrinth getLab() {
		return lab;
	}

	/**
	 * Gets the sword.
	 *
	 * @return the sword
	 */
	public Sword getSword() {
		return sword;
	}

	/**
	 * Gets the dragon list.
	 *
	 * @return the dragon list
	 */
	public ArrayList<Dragon> getDragonList() {
		return DragonList;
	}

	/**
	 * Gets the hero.
	 *
	 * @return the hero
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * Gets the dragon.
	 *
	 * @return the dragon
	 */
	public boolean getDragon() {
		return LiveDragon;
	}

	/**
	 * Gets the numberD.
	 *
	 * @return the number of Dragons
	 */
	public int getNumberD() {
		return numberD;
	}

	/**
	 * Gets the n.
	 *
	 * @return the n , n is the maze size
	 */
	public int getN() {
		return N;
	}

	/**
	 * Gets the space.
	 *
	 * @param x the x coord of specific space
	 * @param y the y coord of specific space
	 * @return the space
	 */
	public char getSpace(int x, int y) {
		return lab.getSpace(x, y);
	}

	/**
	 * Gets the sword pos.
	 *
	 * @return the sword pos
	 */
	public int[] getSwordPos() {
		return sword.getCoord();
	}

	/**
	 * Gets the hero pos.
	 *
	 * @return the hero pos
	 */
	public int[] getHeroPos() {
		return hero.getCoord();
	}

	/**
	 * Gets the exit.
	 *
	 * @return the exit
	 */
	public Exit getExit() {
		return exit;
	}

	/**
	 * Hero is alive.
	 *
	 * @return true, if successful
	 */
	public boolean heroIsAlive() {
		return LiveHero;
	}

	/**
	 * Checks if is live dragon.
	 *
	 * @return true, if is live dragon
	 */
	public boolean isLiveDragon() {
		return LiveDragon;
	}

	/**
	 * Sets the space.
	 *
	 * @param x the x coord of specific space
	 * @param y the y coord of specific space
	 * @param C the char to fill specific space
	 */
	public void setSpace(int x, int y,char C) {
		lab.setSpace(x, y,C);
	}

	/**
	 * Sets the sword.
	 *
	 * @param sword the new sword
	 */
	public void setSword(Sword sword) {
		this.sword = sword;
	}

	/**
	 * Sets the dragon list.
	 *
	 * @param dragonList the new dragon list
	 */
	public void setDragonList(ArrayList<Dragon> dragonList) {
		DragonList = dragonList;
	}

	/**
	 * Sets the live dragon.
	 *
	 * @param liveDragon the new live dragon
	 */
	public void setLiveDragon(boolean liveDragon) {
		LiveDragon = liveDragon;
	}

	/**
	 * Sets the n.
	 *
	 * @param n the new 
	 */
	public void setN(int n) {
		N = n;
	}

	/**
	 * Sets the ndragon.
	 *
	 * @param num the new number of Dragons
	 */
	public void setNdragon(int num) {
		numberD = num;
	}

	/**
	 * Sets the hero.
	 *
	 * @param hero the new hero
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
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
	 * Sets the lab.
	 *
	 * @param lab the new lab
	 */
	public void setLab(Labyrinth lab) {
		this.lab = lab;
	}

	/**
	 * Sets the diff.
	 *
	 * @param diff the new difficulty
	 */
	public void setDiff(int diff) {
		this.difficulty = diff;
	}

	/**
	 * Sets the exit.
	 *
	 * @param exit the new exit
	 */
	public void setExit(Exit exit) {
		this.exit = exit;
	}

}


