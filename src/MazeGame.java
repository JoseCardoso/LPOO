import java.awt.List;
import java.util.ArrayList;



public class MazeGame {
	
	private int N;
	private boolean LiveDragon = true, LiveHero = true,exit = false;
	private Espada espada = new Espada();
	private Heroi hero = new Heroi();
	private Dragon dragon = new Dragon(hero);
	private Saida saida = new Saida();
	private Labirinto lab = new Labirinto(espada,dragon,hero,saida);
	private ArrayList<Dragon> DragonList = new ArrayList<Dragon>();
	private int NumeroD;
	
	
	public void generate() {

		boolean exit = true;
		//maze = new char[N][N];
		lab.setN(N);
		lab.createLab();
		espada.pos();
		hero.pos();
		saida.pos();
		lab.printLab();
		for(int i = 0; i < NumeroD; i++)
		{
			DragonList.listIterator(NumeroD).next().pos();
		}
	}
	
	public void setN(int n)
	{
		N = n;
	}
	
	public void setNdragon()
	{
		NumeroD = N/2-3;
		if(NumeroD < 1)
			NumeroD = 1;		
		for(int i = 0; i < NumeroD; i++)
		{
			DragonList.add(new Dragon(hero));
		}
		
	}
	
	public boolean start(String walk )
	{
		
		hero.move(walk);
		dragon.moveRandom();
		System.out.println();
		lab.printLab();
		if(dragon.eat())
		{ 
			if(hero.getSword()) 
				LiveDragon =false;
			else if(!dragon.getSleep())
				LiveHero = false;
		} 
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


