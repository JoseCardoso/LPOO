package mazeGame;

import mazeCli.MazeCli;

public class Aguia extends MazeObject{

	private Heroi heroi;
	private Espada espada;
	private int x,y, xDeparture, yDeparture, xObjective, yObjective;
	private boolean sword = false, alive = true, flying = false, withHero = true;

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


	public void move(int dx, int dy)
	{
		
		y += dy;
		x += dx;
		MazeCli.game.setSpace(x, y, 'a');
	}

	public void foundSword()
	{
		if(espada.getCood()[0] == x && espada.getCood()[1]  == y)
			sword = true;
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

	public boolean isFlying()
	{
		return flying;
	}

	public boolean withHero()
	{
		return withHero;
	}

}
