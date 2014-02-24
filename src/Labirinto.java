import java.util.*;

public class Labirinto {
	public static int N;
	private static char[][] maze;
	private char[][] visitedCell;
	private boolean dragon = true;
	private boolean heroi = true;
	private Stack<int[]> st;

	public static void main(String[] args) {
		Labirinto lab = new Labirinto();
		lab.Game();
	}


	public void Game() {

		boolean exit = true;
		getNumero();
		maze = new char[N][N];
		createLab();
		Espada.pos();
		Heroi.pos();
		Dragon.pos();
		Saida.pos();
		printLab();
		while(heroi&&(dragon || exit))
		{
			Scanner sc = new Scanner(System.in);
			String walk = sc.nextLine(); 
			Heroi.move(walk);
			Dragon.moveRandom();
			System.out.println();
			printLab();
			if(Dragon.eat())
			{ 
				if(Heroi.getSword()) 
					dragon =false;
				else 
					heroi = false;
			} 
			if(Heroi.getCoord()[0] == Saida.getCood()[0] &&
					Heroi.getCoord()[1] == Saida.getCood()[1] )
				exit = false;
			else 
				exit = true; 
		}
		if(!heroi) System.out.println("\n\n\n\n\n\n\n\n\nMorrrrrrrrrrrreu!");
		else System.out.println("\n\n\n\n\n\n\n\n\nVitória!");
	}

	public static char getSpace(int x, int y) {
		if (x > N - 1 || x < 0 || y > N - 1 || y < 0)
			return 'I';
		return maze[y][x];
	}

	public static void setSpace(int x, int y, char C) {
		maze[y][x] = C;
	}

	public void printLab() {
		int coodH[] = Heroi.getCoord(); int coodE[] =
				Espada.getCood(); int coodD[] = Dragon.getCood();
				int coodS[] = Saida.getCood();
				maze[coodS[1]][coodS[0]] = 'S'; 
				if(dragon)
					maze[coodD[1]][coodD[0]] = 'D'; 
				else
					maze[coodD[1]][coodD[0]] = ' ';
				if(Heroi.getSword()) 
					maze[coodH[1]][coodH[0]] ='A'; 
				else
				{
					maze[coodH[1]][coodH[0]] = 'H';
					if(coodD[0] == coodE[0] && coodD[1] == coodE[1])
						maze[coodD[1]][coodD[0]] = 'F'; 
					else 
						maze[coodE[1]][coodE[0]] = 'E';
				}

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						System.out.print(maze[i][j] + " ");
					}
					System.out.println();
				}

	}

	public void createLab() {
		st = new Stack();
		Random r = new Random();

		int size = (N - 1) / 2;
		int x = r.nextInt(size);
		int y = r.nextInt(size);

		visitedCell = new char[size][size];

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
		addToStack(x ,y);

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
				if (!st.isEmpty()){
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
		/*
		// printing maze
		printLab();

		System.out.println();
		// printing visited cells
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(visitedCell[i][j] + " ");
			}
			System.out.println();
		}

		// printing stack
		Stack<int[]> aux = (Stack<int[]>) st.clone();
		while (!aux.isEmpty()) {
			System.out.println(aux.peek()[0] + "," + aux.peek()[1]);
			aux.pop();
		}
		System.out.print("\n-----\n");
		 */

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

	public void getNumero() {
		System.out.println("Insira o tamanho 'N' do puzzle. (NxN)");
		boolean rep;
		int aux;
		do {
			rep = false;
			try {
				Scanner sc = new Scanner(System.in);
				String detect = sc.nextLine();
				aux = Integer.parseInt(detect);
				N = aux;
			} catch (Exception e) {
				System.out.println("Erro, insira o nº de novo.");
				rep = true;
			}
		} while (rep);
	}

	public void addToStack(int x, int y) {
		int[] temp = new int[2];
		temp[0] = x;
		temp[1] = y;
		st.push(temp);
	}

}