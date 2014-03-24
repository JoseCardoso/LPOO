package maze.game;


import java.util.ArrayList;
import maze.cli.MazeCli;


public class MazeGame {

	private int N , diff;
	private boolean LiveDragon = true, LiveHero = true,exit = false;
	private Espada espada = new Espada(this);
	private Heroi hero = new Heroi(this);
	private Aguia aguia = new Aguia(this);
	private Saida saida = new Saida(this);
	private ArrayList<Dragon> DragonList = new ArrayList<Dragon>();
	private Labirinto lab = new Labirinto(this);
	private MazeCli cli;
	
	

	public MazeGame(MazeCli mazeCli) {
		cli = mazeCli;
	}

	public Saida getSaida() {
		return saida;
	}

	public void setSaida(Saida saida) {
		this.saida = saida;
	}

	public boolean isLiveDragon() {
		return LiveDragon;
	}

	public void setLiveDragon(boolean liveDragon) {
		LiveDragon = liveDragon;
	}

	public ArrayList<Dragon> getDragonList() {
		return DragonList;
	}

	public void setDragonList(ArrayList<Dragon> dragonList) {
		DragonList = dragonList;
	}


	public Espada getEspada() {
		return espada;
	}

	public void setEspada(Espada espada) {
		this.espada = espada;
	}
	
	public Labirinto getLab() {
		return lab;
	}

	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}


	public void setLab(Labirinto lab) {
		this.lab = lab;
	}

	public Aguia getAguia() {
		return aguia;
	}

	public void setAguia(Aguia aguia) {
		this.aguia = aguia;
	}

	public void setHero(Heroi hero) {
		this.hero = hero;
	}

	private int NumeroD;

	public void generate(boolean choice) {

		//choice -false = standard
		lab.setN(N);
		if (choice)
			lab.createLab();
		else
			lab.setMaze(lab.getStandardMaze());
		espada.pos();
		hero.pos();
		aguia.pos();
		hero.setAguia(aguia);
		saida.pos();
		lab.setAguia(aguia);
		for(int i = 0; i < NumeroD; i++)//contruie a lista, menos ou igual
		{
			Dragon temp = new Dragon(this);
			temp.pos();
			DragonList.add(temp);
		}
		lab.printLab();
	}

	public void setN(int n)
	{
		N = n;
	}

	public int getNdragon()
	{
		return NumeroD;
	}

	public void setNdragon(int num)
	{
		NumeroD = num;
	}

	public boolean start(String walk )
	{
		char[][] maze;

		hero.move(walk);
		aguia.move();
		if (diff != 1)
			for(int i = 0; i < NumeroD; i++)
			{
				DragonList.listIterator(i).next().moveRandom();
			}
		System.out.println();
		maze=lab.printLab();
		cli.printLab(maze, N);
		bigEat();
		bigEatEagle();
		hero.pickUpEagle();
		if(hero.getCood()[0] == saida.getCood()[0] &&
				hero.getCood()[1] == saida.getCood()[1] )
			exit = true;
		else 
			exit = false;
		if(!LiveHero ||  (!LiveDragon && exit))
			return false;
		else
			return true;
	}


	public void bigEat()
	{
		for(int i = 0; i < NumeroD;i++)
		{
			DragonList.listIterator(i).next();
			if(DragonList.listIterator(i).next().eat())
			{ 
				if(hero.getSword()) 
				{
					setSpace(DragonList.listIterator(i).next().getCood()[0],
							DragonList.listIterator(i).next().getCood()[1],' ');
					DragonList.remove(i);
					NumeroD--;
				}
				else if(!DragonList.listIterator(i).next().getSleep())
					LiveHero = false;
			} 
		}
		if(NumeroD == 0)
			LiveDragon = false;
	}

	public void bigEatEagle()
	{
		for(int i = 0; i < NumeroD;i++)
		{
			DragonList.listIterator(i).next();
			DragonList.listIterator(i).next().eatEagle();
		}
	}

	public boolean getLiveHero()
	{
		return LiveHero;
	}

	public Heroi getHero()
	{
		return hero;
	}


	public boolean getDragon()
	{
		return LiveDragon;
	}

	public int getN()
	{
		return N;
	}

	public char getSpace(int x, int y)
	{
		return lab.getSpace(x, y);
	}

	public void setSpace(int x, int y, char C)
	{
		lab.setSpace(x, y, C);
	}

	public int[] getEspadaPos()
	{
		return espada.getCood();
	}

	public int[] getHeroPos()
	{
		return hero.getCood();
	}

}


