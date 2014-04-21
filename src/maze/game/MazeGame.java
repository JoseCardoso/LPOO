package maze.game;


import java.io.Serializable;
import java.util.ArrayList;

public class MazeGame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int N, dificuldade, NumeroD;
	private boolean LiveDragon = true, LiveHero = true, exit = false;
	private Sword espada = new Sword(this);
	private Hero hero = new Hero(this);
	private Eagle aguia = new Eagle(this);
	private Exit saida = new Exit(this);
	private ArrayList<Dragon> DragonList = new ArrayList<Dragon>();
	private Labyrinth lab = new Labyrinth(this);


	public void fullGenerate(Sword espada,Hero hero, Exit saida,ArrayList<Dragon> DragonList, char[][] emptyMaze, char[][] maze)
	{
		this.espada = espada;
		this.hero = hero;
		aguia = new Eagle(this);
		aguia.pos();
		this.saida = saida;
		this.DragonList  = new ArrayList<Dragon>(DragonList);
		lab = new Labyrinth(this);
		lab.createLab(emptyMaze,maze);
		NumeroD = DragonList.size();
		lab.updateLab();
	}
	
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
					espada.setCoord(x, y);
					emptyMaze[y][x] = ' ';
				}
				else if(maze[y][x] == 'S')
				{
					saida.setCoord(x, y);
					emptyMaze[y][x] = 'X';
				}
				else if(maze[y][x] == 'D')
				{
					NumeroD++;
					Dragon dragon = new Dragon(this);
					dragon.setCoord(x, y);
					DragonList.add(dragon);
					emptyMaze[y][x] = ' ';
				}					
				else
					emptyMaze[y][x] = maze[y][x];
			}
		
		dificuldade = diff;
		aguia.pos();
		hero.setEagle(aguia);
		lab.createLab(emptyMaze, maze);
		lab.setN(N);
		lab.setEagle(aguia);
		lab.updateLab();
		
	}

	public void autoGen(int N, int NDragons, int diff, boolean choice) {
		if (!choice)
			this.N = 10;
		else
			this.N = N;
	
		this.NumeroD = NDragons;
		this.dificuldade = diff;
		generate(choice);
	
	}

	public void generate(boolean choice) {
		// choice -false = standard
		lab.setN(N);
		if (choice)
			lab.createLab();
		else
			lab.createLab(lab.getStandardMaze());
		hero.pos();
		espada.pos();
		aguia.pos();
		hero.setEagle(aguia);
		saida.pos();
		lab.setEagle(aguia);
		for (int i = 0; i < NumeroD; i++)// contruie a lista, menos ou igual
		{
			Dragon temp = new Dragon(this);
			temp.pos();
			DragonList.add(temp);
		}
		lab.updateLab();
	}

	public boolean update(String dir) {
	
		hero.move(dir);
		aguia.move();
		if (dificuldade != 1)
			for (int i = 0; i < NumeroD; i++)
				DragonList.listIterator(i).next().moveRandom();
		bigEat();
		bigEatEagle();
		hero.pickUpEagle();
		if (hero.getCoord()[0] == saida.getCoord()[0]
				&& hero.getCoord()[1] == saida.getCoord()[1])
			exit = true;
		else
			exit = false;
		if (!LiveHero || (!LiveDragon && exit))
			return false;
		else
			return true;
	}

	public void bigEat() {
		for (int i = 0; i < NumeroD; i++) {
			DragonList.listIterator(i).next();
			if (DragonList.listIterator(i).next().killHero()) {
				if (hero.hasSword()) {
					DragonList.remove(i);
					NumeroD--;
				} else if (!DragonList.listIterator(i).next().isSleeping())
					LiveHero = false;
			}
		}
		if (NumeroD == 0)
			LiveDragon = false;
	}

	public void bigEatEagle() {
		for (int i = 0; i < NumeroD; i++)
			DragonList.listIterator(i).next().killEagle();
	}
	
	/**
	 * creates random Wrapper for each dragon
	 */
	public void createRandomWrapper() 
	{
		for (int i = 0; i < NumeroD; i++)
			DragonList.listIterator(i).next().createRandomWrapper();
	}

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

	public int getDiff() {
		return dificuldade;
	}

	public Eagle getAguia() {
		return aguia;
	}

	public Labyrinth getLab() {
		return lab;
	}

	public Sword getEspada() {
		return espada;
	}

	public ArrayList<Dragon> getDragonList() {
		return DragonList;
	}

	public Hero getHero() {
		return hero;
	}

	public boolean getDragon() {
		return LiveDragon;
	}

	public int getNdragon() {
		return NumeroD;
	}

	public int getN() {
		return N;
	}

	public char getSpace(int x, int y) {
		return lab.getSpace(x, y);
	}

	public int[] getEspadaPos() {
		return espada.getCoord();
	}

	public int[] getHeroPos() {
		return hero.getCoord();
	}

	public Exit getSaida() {
		return saida;
	}

	public boolean heroIsAlive() {
		return LiveHero;
	}

	public boolean isLiveDragon() {
		return LiveDragon;
	}

	public void setSpace(int x, int y,char C) {
		lab.setSpace(x, y,C);
	}

	public void setEspada(Sword espada) {
		this.espada = espada;
	}

	public void setDragonList(ArrayList<Dragon> dragonList) {
		DragonList = dragonList;
	}

	public void setLiveDragon(boolean liveDragon) {
		LiveDragon = liveDragon;
	}

	public void setN(int n) {
		N = n;
	}

	public void setNdragon(int num) {
		NumeroD = num;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public void setAguia(Eagle aguia) {
		this.aguia = aguia;
	}

	public void setLab(Labyrinth lab) {
		this.lab = lab;
	}

	public void setDiff(int diff) {
		this.dificuldade = diff;
	}

	public void setSaida(Exit saida) {
		this.saida = saida;
	}

}


