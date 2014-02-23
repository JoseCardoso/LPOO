import java.util.*;

public class Labirinto {
	private static char[][] maze;
	private char[][] visitedCell;
	private boolean dragon = true;
	private boolean heroi = true;
	private static int N;
	private Stack <int []> st;

	public static void main(String[] args)
	{
		Labirinto lab = new Labirinto();
		lab.Game();
	}

	public void Game()
	{

		boolean exit = true;
		getNumero();
		maze = new char[N][N];
		createLab();
	/*	while(heroi&&(dragon || exit))
		{
			Scanner sc = new Scanner(System.in);
			String walk = sc.nextLine();
			printLab();
			Heroi.move(walk);
			Dragon.moveRandom();
			if(Dragon.eat())
			{
				if(Heroi.getSword())
					dragon = false;
				else
					heroi = false;
			}	
			if(Heroi.getCoord()[0] == Saida.getCood()[0] 
					&& Heroi.getCoord()[1] == Saida.getCood()[1] )
				exit = false;
			else
				exit = true;
		}*/
		printLab();
		/*
		if(!heroi)
			System.out.println("\n\n\n\n\n\n\n\n\nMorrrrrrrrrrrreu!");
		else
			System.out.println("\n\n\n\n\n\n\n\n\nVitória!");
	*/}

	public static char getSpace(int x, int y)
	{
		if(x > N-1 || x < 0 || y > N-1 || y < 0)
			return 'I';
		return maze[x][y];		
	}

	public static void setSpace(int x, int y, char C)
	{
		maze[x][y] = C;
	}

	public void printLab()
	{/*
		int coodH[] = Heroi.getCoord();
		int coodE[] = Espada.getCood();
		int coodD[] = Dragon.getCood();
		int coodS[] = Saida.getCood();
		maze[coodS[0]][coodS[1]] = 'S';
		if(dragon)
			maze[coodD[0]][coodD[1]] = 'D';
		else
			maze[coodD[0]][coodD[1]] = ' ';
		if(Heroi.getSword())
			maze[coodH[0]][coodH[1]] = 'A';
		else
		{
			maze[coodH[0]][coodH[1]] = 'H';
			if(coodD[0] == coodE[0] && coodD[1] == coodE[1])
				maze[coodD[0]][coodD[1]] = 'F';
			else
				maze[coodE[0]][coodE[1]] = 'E';
		}
*/
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}

	}


	public void createLab()
	{
		st = new Stack();
		Random r = new Random();

		int size= (N-1)/2;
		int x = r.nextInt(size);
		int y = r.nextInt(size);

		visitedCell = new char[size][size];

		for (int i = 0 ; i < N; i++)
		{
			for ( int j = 0 ; j < N ;j++)
			{
				if ( i%2 != 0  && j %2 != 0)
					maze [i][j]= ' ';
				else
					maze [i][j]= 'X';
			}
		}

		for (int i=0 ; i<size ; i++)
		{
			for ( int j = 0 ; j < size ; j++)

				visitedCell[i][j] = '.';
		}

		visitedCell[x][y] = '+';
		maze[2*x +1][2*y +1] = '+';
		int[] aux = {x , y};
		st.push(aux);

		while (!st.isEmpty())
		{ 
			if ( hasMove(x , y , size))
			{
			
			int dir = r.nextInt(4);

			switch ( dir)
			{
			case 0: //up
				if(getValidMaze(0 ,-1 , size))
				{
					y--;
					visitedCell[x][y] = '+';
					fillMaze(2*x + 1 , 2*y + 1 , 0 , -1);
				}
				break;
			case 1: // left
				if(getValidMaze(-1,0 , size))
				{
					x--;
					visitedCell[x][y] = '+';
					fillMaze(2*x + 1 , 2*y + 1 , -1 , 0);
				}

				break;

			case 2: // right
				if(getValidMaze(1 ,0 , size))
				{
					x++;
					visitedCell[x][y] = '+';
					fillMaze(2*x + 1 , 2*y + 1 , 1 , 0);
				}
				break;
			case 3: // down
				if(getValidMaze(0 ,1 , size))
				{
					y++;
					visitedCell[x][y] = '+';
					fillMaze(2*x + 1 , 2*y + 1 , 0 , 1);
				}
				break;
			}

			aux[0]= x;
			aux[1]= y;

			st.push(aux);


			}
			else
			{
				x = st.peek()[0];
				y = st.peek()[1];
				st.pop();
			}
			
		}



	}



	public void fillMaze(int x , int y ,int dx , int dy)
	{
		for  ( int i = 0 ; i < 3 ; i ++)
		{
			x += dx;
			y += dy;

			if ((x == 1 && dx < 0)|| ( x == N-2 && dx >0)
					||(y == 1 && dy < 0)|| ( y == N-2 && dy >0))
				break;
			
				
			if ( x <0 || y < 0 || x > N-1 || y > N-1)
				break;
			if (x==0 || y==0 || x== N-1 || y== N-1)
				maze[x][y] = 'X';
			else
				maze[x][y] = ' ';
		}
	}

	public boolean hasMove(int x , int y , int size)
	{
		if(x == 0)
		{
			if (visitedCell [x +1][y] == '.' )

				return true;

		}
		else if(x == size-1)
		{
			if (visitedCell [x -1][y] == '.' )

				return true;

		}
		else
		{
			if (visitedCell [x +1][y] == '.' || visitedCell [x-1][y] == '.')

				return true;

		}

		if(y == 0)
		{
			if (visitedCell [x][y+1] == '.' )
				return true;
		}
		else if(y == size -1)
		{
			if (visitedCell [x][y-1] == '.')

				return true;

		}
		else
		{
			if (visitedCell [x ][y+1] == '.' || visitedCell [x][y-1] == '.')

				return true;
		}

		return false;

	}


	public boolean getValidMaze(int dx , int dy , int size)
	{	
		int x= st.peek()[0];
		int y= st.peek()[1];

		if ((x+dx >=0 && y + dy >=0 && x+dx < size && y + dy <size) && visitedCell [x + dx][y+dy] == '.')

			return true;
		else 
			return false;
	}


	public void getNumero()
	{
		System.out.println("Insira o tamanho 'N' do puzzle. (NxN)");
		boolean rep;
		int aux;
		do
		{
			rep = false;
			try { 
				Scanner sc = new Scanner(System.in);
				String detect = sc.nextLine();
				aux = Integer.parseInt(detect);
				sc.close();
				N = aux;
			} catch(Exception e) { 
				System.out.println("Erro, insira o nº de novo.");
				rep = true;
			}
		}while(rep);	
	}
}

