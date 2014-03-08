package mazeGame;

import mazeCli.MazeCli;

public class Aguia extends MazeObject{

	private Heroi heroi;
	private Espada espada;
	private char lastPos;
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


	public void move()
	{
		if ()
		

	}
	
	public boolean setDeparture()
	{
		if (!heroi.getSword())
		{
			x = heroi.getCood()[0];
			y = heroi.getCood()[1];
			return true;
		}
		else
			return false;
	}

	public void foundSword()
	{
		if(espada.getCood()[0] == x && espada.getCood()[1]  == y)
			sword = true;
	}


	public boolean getSword()
	{
		return sword;
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void kill()
	{
		if (sword)
		{
			espada.setCood(x, y);
		}
		alive = false;

	}

	public boolean isFlying()
	{
		return flying;
	}

	public boolean withHero()
	{
		return withHero;
	}

	public void flight(int dx, int dy)
	{
		flying= true;

		if ( !(x == heroi.getCood()[0] && y == heroi.getCood()[1]))
		{

			if (lastPos == 'E')
			{
				sword = true;
				flying = false;
				lastPos = ' ';
			}


			MazeCli.game.setSpace(x , y , lastPos);

		}

		y += dy;
		x += dx;
		lastPos = MazeCli.game.getSpace( x ,y);
		MazeCli.game.setSpace(x, y, 'a');
	}

}
