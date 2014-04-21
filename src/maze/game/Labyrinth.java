package maze.game;

import java.io.Serializable;
import java.util.*;

// TODO: Auto-generated Javadoc
//MazeBuilder
/**
 * The Class Labyrinth.
 * 
 * This class is responsable for generating the mazes
 */
public class Labyrinth implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public int N;
	private int NumeroD;
	private char[][] emptyMaze;
	private char[][] maze;
	private char[][] visitedCell;
	transient private Stack<int[]> st;
	private Sword sword;
	private Hero hero;
	private Exit exit;
	private Eagle eagle;
	private MazeGame game;
	private ArrayList<Dragon> DragonList;

	/**
	 * Instantiates a new labyrinth.
	 *
	 * @param mazeGame the maze game
	 */
	public Labyrinth(MazeGame mazeGame) {
		game = mazeGame;
		sword = game.getEspada();
		DragonList = game.getDragonList();
		hero = game.getHero();
		exit = game.getSaida();
		eagle = game.getAguia();
	}

	/**
	 * Creates the labyrinth based on a specific maze
	 *
	 * @param emptyMaze the empty maze with only walls and path
	 * @param maze the maze with objects, pat
	 */
	public void createLab(char[][] emptyMaze, char[][] maze) {
		// TODO Auto-generated method stub
			
		this.emptyMaze = emptyMaze;
		this.maze = maze;
	}
	

	/**
	 * Creates the lab.
	 *
	 * @param maze the maze
	 */
	public void createLab(char[][] maze) // laboratório pre-definido
	{
		this.emptyMaze = maze;
		this.maze = new char[N][N];
	}

	/**
	 * Creates the lab.
	 */
	public void createLab() {
		st = new Stack<int[]>();
		Random r = new Random();
		int size = (N - 1) / 2;
		int x = r.nextInt(size);
		int y = r.nextInt(size);
		visitedCell = new char[size][size];
		emptyMaze = new char[N][N];
		maze = new char[N][N];
	
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i % 2 != 0 && j % 2 != 0)
					emptyMaze[i][j] = ' ';
				else
					emptyMaze[i][j] = 'X';
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				visitedCell[i][j] = '.';
		}
	
		visitedCell[y][x] = '+';
		addToStack(x, y);
	
		// começar a gerar algoritmo
		while (!st.isEmpty()) {
			if (hasMove(x, y, size)) {
				int dir = r.nextInt(4);
				switch (dir) {
				case 0: // up
					if (getValidMaze(0, -1, size)) {
						fillMaze(2 * x + 1, 2 * y + 1, 0, -1);
						y--;
						visitedCell[y][x] = '+';
						addToStack(x, y);
					}
					break;
				case 1: // left
					if (getValidMaze(-1, 0, size)) {
	
						fillMaze(2 * x + 1, 2 * y + 1, -1, 0);
						x--;
						visitedCell[y][x] = '+';
						addToStack(x, y);
					}
					break;
				case 2: // right
					if (getValidMaze(1, 0, size)) {
						fillMaze(2 * x + 1, 2 * y + 1, 1, 0);
						x++;
						visitedCell[y][x] = '+';
						addToStack(x, y);
					}
					break;
				case 3: // down
					if (getValidMaze(0, 1, size)) {
						fillMaze(2 * x + 1, 2 * y + 1, 0, 1);
						y++;
						visitedCell[y][x] = '+';
						addToStack(x, y);
					}
					break;
				}
			} else {
				st.pop();
				if (!st.isEmpty()) {
					x = st.peek()[0];
					y = st.peek()[1];
				}
			}
		}
	}

	/**
	 * Adds the to stack.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void addToStack(int x, int y) {
		int[] temp = new int[2];
		temp[0] = x;
		temp[1] = y;
		st.push(temp);
	}

	/**
	 * Fill maze.
	 *
	 * @param x the x
	 * @param y the y
	 * @param dx the dx
	 * @param dy the dy
	 */
	public void fillMaze(int x, int y, int dx, int dy) {
		emptyMaze[y + dy][x + dx] = ' ';
	}

	/**
	 * Checks for move.
	 *
	 * @param x the x
	 * @param y the y
	 * @param size the size
	 * @return true, if successful
	 */
	public boolean hasMove(int x, int y, int size) {
	
		if (y - 1 >= 0)
			if (visitedCell[y - 1][x] == '.')
				return true;
	
		if (y + 1 < size)
			if (visitedCell[y + 1][x] == '.')
				return true;
	
		if (x - 1 >= 0)
			if (visitedCell[y][x - 1] == '.')
				return true;
	
		if (x + 1 < size)
			if (visitedCell[y][x + 1] == '.')
				return true;
		return false;
	}

	/**
	 * Gets the valid maze.
	 *
	 * @param dx the dx
	 * @param dy the dy
	 * @param size the size
	 * @return the valid maze
	 */
	public boolean getValidMaze(int dx, int dy, int size) {
		int x = st.peek()[0];
		int y = st.peek()[1];
	
		if ((x + dx >= 0 && y + dy >= 0 && x + dx < size && y + dy < size)
				&& visitedCell[y + dy][x + dx] == '.')
			return true;
		else
			return false;
	}

	/**
	 * Update lab.
	 *
	 * @return the char[][]
	 */
	public char[][] updateLab() {
		
		int coordH[] = hero.getCoord();
		int coordE[] = sword.getCoord();
		int coordS[] = exit.getCoord();
		int coordA[] = eagle.getCoord();
		emptyMaze[coordS[1]][coordS[0]] = 'S';
		
		for(int i = 0; i < N;i++)
			for(int j = 0; j <N ; j++)
				maze[i][j] = emptyMaze[i][j];
		
		if ((coordH[0] != coordA[0] || coordH[1] != coordA[1]) && eagle.isAlive())
			maze[coordA[1]][coordA[0]] = 'a';
		
		
		drawDragon(coordE);
		
		if (hero.hasSword())
		{
			maze[coordE[1]][coordE[0]] = ' ';
			maze[coordH[1]][coordH[0]] = 'A';
			
		}
		else
			maze[hero.getCoord()[1]][hero.getCoord()[0]] = 'H';
	
		return maze;
	
	}

	/**
	 * Draw dragon.
	 *
	 * @param coodE the cood e
	 */
	public void drawDragon(int[] coodE) {
		NumeroD = game.getNdragon();
		boolean dragonHasSword = false; // verifica se um dragao está na mesma
		// posição que a espada
		for (int i = 0; i < NumeroD; i++) {
			int coodD[] = DragonList.listIterator(i).next().getCoord();
			if (DragonList.listIterator(i).next().isSleeping())
				maze[coodD[1]][coodD[0]] = 'd';
			else
				maze[coodD[1]][coodD[0]] = 'D';
			if (!hero.hasSword() && (!eagle.isWithSword()|| !eagle.isAlive() )) {
				if (coodD[0] == coodE[0] && coodD[1] == coodE[1]) {
					dragonHasSword = true;
					maze[coodD[1]][coodD[0]] = 'F';
				} else if (!dragonHasSword)
					maze[coodE[1]][coodE[0]] = 'E';
			} else if (coodD[0] != coodE[0] || coodD[1] != coodE[1])// desenhar
				// a espada
				// em branco
				// se o
				// heroi ou
				// a aguia
				// tiverem
				// espada
				maze[coodE[1]][coodE[0]] = ' ';
		}
	
	}

	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public char[][] getMaze() {
		return maze;
	}

	/**
	 * Gets the empty maze.
	 *
	 * @return the empty maze
	 */
	public char[][] getEmptyMaze() {
		return emptyMaze;
	}

	/**
	 * Gets the standard maze.
	 *
	 * @return the standard maze
	 */
	public char[][] getStandardMaze() {
		char[][] temp = { 
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }};
		
		return temp;
	
	}

	/**
	 * Gets the space.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the space
	 */
	public char getSpace(int x, int y) {
		if (x > N - 1 || x < 0 || y > N - 1 || y < 0)
			return 'I'; //caso nao seja uma coordenada válida
		return emptyMaze[y][x];
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
	 * Sets the eagle.
	 *
	 * @param eagle the new eagle
	 */
	public void setEagle(Eagle eagle) {
		this.eagle = eagle;
	}

	/**
	 * Sets the n.
	 *
	 * @param n the new n
	 */
	public void setN(int n) {
		N = n;
	}

	/**
	 * Sets the space.
	 *
	 * @param x the x
	 * @param y the y
	 * @param C the c
	 */
	public void setSpace(int x, int y, char C) {
		
		maze[y][x] = C;
	}
	
}