import java.util.*;


public class Labirinto {
	private static char[][] maze;
	private boolean dragon = true;
	private boolean heroi = true;
	private int N;

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
		while(heroi&&(dragon || exit))
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
		}
		printLab();
		if(!heroi)
			System.out.println("\n\n\n\n\n\n\n\n\nMorrrrrrrrrrrreu!");
		else
			System.out.println("\n\n\n\n\n\n\n\n\nVitória!");
	}

	public static char getSpace(int x, int y)
	{
		if(x > 9 || x < 0 || y > 9 || y < 0)
			return 'I';
		return maze[x][y];		
	}

	public static void setSpace(int x, int y, char C)
	{
		maze[x][y] = C;
	}

	public void printLab()
	{
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

		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}

	}

	
	public void createLab()
	{
		
		
		
	}
	
	
/*	
	public void createLab()//Randomized Prim's algorithm
	{
		int f = 0, t =0;
		for (int j = 0 ; j < N;j++)
		{
			for (int i = 0 ; i < N ; i++)
			{
				maze[j][i] = 'X';
			}			
		}

		Random r = new Random();
		int x = r.nextInt(N-1) + 1;//maior que zero e menor que N -1, não será uma parede
		int y = r.nextInt(N-1) + 1;
		int aux = -1;//ajuda para ir buscar um elemento aleatório à lista
		List<Wall> lista = new ArrayList();
		do
		{
			if(aux != -1)//evitar a 1ª iteração
			{
				ListIterator<Wall> it = lista.listIterator(aux);
				Wall c = (Wall) it.next();
				x = c.getCood()[0];
				y = c.getCood()[1];
				it.remove();
			}
			if(getValidMaze(x,y))
			{
				t++;
				maze[x][y] = ' ';
				if(x == 1)
				{
					if(maze[x+1][y] == 'X')
					{
						Wall a = new Wall(x+1,y);
						lista.add(a);
					}
				}
				else if(x >= N-2)
				{
					if(maze[x-1][y] == 'X')
					{
						Wall a = new Wall(x-1,y);
						lista.add(a);
					}
				}
				else
				{
					if(maze[x+1][y] == 'X')
					{
						Wall b = new Wall(x+1,y);
						lista.add( b);
					}
					if(maze[x-1][y] == 'X')
					{
						Wall a = new Wall(x-1,y);
						lista.add(a);
					}
				}
				if(y == 1)
				{
					if(maze[x][y+1] == 'X')
					{
						Wall a = new Wall(x,y+1);
						lista.add( a);
					}
				}
				else if(y >= N-2)
				{
					if(maze[x][y-1] == 'X')
					{
						Wall a =new Wall(x,y-1);
						lista.add(a);
					}
				}
				else
				{
					if(maze[x][y+1] == 'X')
					{
						Wall a = new Wall(x,y+1);
						lista.add( a);
					}
					if(maze[x][y-1] == 'X')
					{
						Wall b =new Wall(x,y-1);
						lista.add(b);
					}
				}
			}
			else
				f++;
			if(lista.size() > 0)
				aux = r.nextInt(lista.size());
		}while(!lista.isEmpty());
		System.out.println(f + "\n" + t );
	}
*/
	public boolean getValidMaze(int x, int y)
	{			
		if(((maze[x][y-1] == ' ' )&& (maze[x][y+1] == ' ')) || ((maze[x-1][y] == ' ') &&( maze[x+1][y] == ' ') ))
		{
			return false;
		}
		else
		{
			return true;
		}
		
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

