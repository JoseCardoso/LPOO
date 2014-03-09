package mazeGame;


import java.awt.List;
import java.util.ArrayList;



public class MazeGame {

	private int N;
	private boolean LiveDragon = true, LiveHero = true,exit = false;
	private Espada espada = new Espada();
	private Heroi hero = new Heroi();
	private Aguia aguia = new Aguia(hero, espada);
	private Saida saida = new Saida();
	private ArrayList<Dragon> DragonList = new ArrayList<Dragon>();
	private Labirinto lab = new Labirinto(espada,DragonList,hero,saida,aguia);
	private int NumeroD;

	public void generate() {

		boolean exit = true;
		lab.setN(N);
		setNdragon();
		lab.createLab();
		espada.pos();
		hero.pos();
		aguia.pos();
		hero.setAguia(aguia);
		saida.pos();
		for(int i = 0; i <= NumeroD; i++)//contruie a lista, menos ou igual
		{
			Dragon temp = new Dragon(hero,aguia);
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

	public void setNdragon()
	{
		NumeroD = N/2-3;
		if(NumeroD < 1)
			NumeroD = 1;	
	}

	public boolean start(String walk )
	{

		hero.move(walk);
		aguia.move();
		for(int i = 0; i <= NumeroD; i++)
		{
			DragonList.listIterator(i).next().moveRandom();
		}
		System.out.println();
		lab.printLab();
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
		for(int i = 0; i <= NumeroD;i++)
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
		for(int i = 0; i <= NumeroD;i++)
		{
			DragonList.listIterator(i).next();
			DragonList.listIterator(i).next().eatEagle();
		}
	}
	
	public boolean getHero()
	{
		return LiveHero;
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


