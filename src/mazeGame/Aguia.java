package mazeGame;

public class Aguia extends MazeObject{

	private Heroi heroi;
	private Espada espada;
	private int x,y, xDeparture, yDeparture, xObjective, yObjective;
	private boolean sword, alive;
	
	Aguia(Heroi hero, Espada espada)
	{
		heroi = hero;
		this.espada = espada;
	}
	
	public void pos()
	{
		int[] coodH = heroi.getCood();
		x = coodH[0];
		y = coodH[1];
	}
	
	public void foundSword()
	{
		  
	}
	
	
	
	public int[] getCood()
	{
		int cood[] = {x,y};
		return cood;
	}
	
	public boolean getSword()
	{
		return sword;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
}
