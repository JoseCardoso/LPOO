
public class MazeGame {
	
	private int N;
	private boolean LiveDragon = true, LiveHero = true,exit = false;
	private Espada espada = new Espada();
	private Heroi hero = new Heroi();
	private Dragon dragon = new Dragon(hero);
	private Saida saida = new Saida();
	private Labirinto lab = new Labirinto(espada,dragon,hero,saida);
	
	public void generate() {

		boolean exit = true;
		//maze = new char[N][N];
		lab.setN(N);
		lab.createLab();
		espada.pos();
		hero.pos();
		dragon.pos();
		saida.pos();
		lab.printLab();
		
	}
	
	public void setN(int n)
	{
		N = n;
	}
	
	public boolean start(String walk)
	{
		
		hero.move(walk);
		dragon.moveRandom();
		System.out.println();
		lab.printLab();
		if(dragon.eat())
		{ 
			if(hero.getSword()) 
				LiveDragon =false;
			else 
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
}


