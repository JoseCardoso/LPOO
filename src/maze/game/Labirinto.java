package maze.game;

import java.util.*;

//MazeBuilder
public class Labirinto {
	public int N;
	private int NumeroD;
	private char[][] maze;
	private char[][] visitedCell;
	private boolean dragon = true;
	private Stack<int[]> st;
	private Espada espada;
	private Heroi hero;
	private Saida saida;
	private Aguia aguia;
	private MazeGame game;

	public Aguia getAguia() {
		return aguia;
	}

	public void setAguia(Aguia aguia) {
		this.aguia = aguia;
	}

	private ArrayList<Dragon> DragonList;

	public Labirinto(MazeGame mazeGame) {
		game = mazeGame;
		espada = game.getEspada();
		DragonList = game.getDragonList();
		hero = game.getHero();
		saida = game.getSaida();
		aguia = game.getAguia();
	}

	public char[][] getMaze() {
		return maze;
	}

	public void setMaze(char[][] m) {
		this.maze = m;
	}

	public char[][] getStandardMaze() {
		char[][] temp = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		
		return temp;

	}

	public char getSpace(int x, int y) {
		if (x > N - 1 || x < 0 || y > N - 1 || y < 0)
			return 'I';
		return maze[y][x];
	}

	public void setSpace(int x, int y, char C) {
		
		maze[y][x] = C;
	}

	public void drawDragon(int[] coodE) {
		NumeroD = game.getNdragon();
		boolean dragonHasSword = false; // verifica se um dragao está na mesma
		// posição que a espada
		for (int i = 0; i < NumeroD; i++) {
			int coodD[] = DragonList.listIterator(i).next().getCoord();
			if (DragonList.listIterator(i).next().getSleep())
				maze[coodD[1]][coodD[0]] = 'd';
			else
				maze[coodD[1]][coodD[0]] = 'D';
			if (!hero.getSword() && !aguia.getSword()) {
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

	public char[][] updateLab() {
		dragon = game.getDragon();
		int coordH[] = hero.getCoord();
		int coordE[] = espada.getCoord();
		int coordS[] = saida.getCood();
		int coordA[] = aguia.getCoord();
		
		if (coordH[0] != coordA[0] || coordH[1] != coordA[1])
			maze[coordA[1]][coordA[0]] = 'a';
		
		maze[coordS[1]][coordS[0]] = 'S';
		if (dragon) {
			drawDragon(coordE);
		}
		if (hero.getSword())
			maze[coordH[1]][coordH[0]] = 'A';
		else
			maze[hero.getCoord()[1]][hero.getCoord()[0]] = 'H';

		return maze;

	}

	public void createLab(char[][] maze) // laboratório pre-definido
	{
		this.maze = maze;
	}

	public void createLab() {
		st = new Stack<int[]>();
		Random r = new Random();
		int size = (N - 1) / 2;
		int x = r.nextInt(size);
		int y = r.nextInt(size);
		visitedCell = new char[size][size];
		maze = new char[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i % 2 != 0 && j % 2 != 0)
					maze[i][j] = ' ';
				else
					maze[i][j] = 'X';
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

	public void fillMaze(int x, int y, int dx, int dy) {
		maze[y + dy][x + dx] = ' ';
	}

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

	public boolean getValidMaze(int dx, int dy, int size) {
		int x = st.peek()[0];
		int y = st.peek()[1];

		if ((x + dx >= 0 && y + dy >= 0 && x + dx < size && y + dy < size)
				&& visitedCell[y + dy][x + dx] == '.')
			return true;
		else
			return false;
	}

	public void setN(int n) {
		N = n;
	}

	public int getN() {
		return N;
	}

	public void addToStack(int x, int y) {
		int[] temp = new int[2];
		temp[0] = x;
		temp[1] = y;
		st.push(temp);
	}

	public Heroi getHeroi() {
		return hero;
	}

	public Espada getEspada() {
		return espada;
	}

	public Saida getSaida() {
		return saida;
	}
}