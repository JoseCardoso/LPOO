package mazeGame;

import mazeCli.MazeCli;

public class Aguia extends MazeObject{

	private Heroi heroi;
	private Espada espada;
	private char lastPos;
	private int x,y, xDeparture, yDeparture, xObjective, yObjective;
	private boolean sword = false, alive = true, flying = false, withHero = true;

	public boolean isWithHero() {
		return withHero;
	}

	public void setWithHero(boolean withHero) {
		this.withHero = withHero;
	}

	Aguia(Heroi hero, Espada espada)
	{
		heroi = hero;
		this.espada = espada;
		xObjective = espada.getCood()[0];
		yObjective = espada.getCood()[1];
	}

	public void pos()
	{
		int[] coodH = heroi.getCood();
		x = coodH[0];
		y = coodH[1];
	}


	public void move()
	{
		int dx = xObjective -x;
		int dy = yObjective -y;
		boolean timeToChange;
		
		if(withHero)
		{
			xDeparture = heroi.getCood()[0];
			yDeparture = heroi.getCood()[1];
			withHero = false;
		}
		if(dx == 0 && dy == 0)
		{
			flying = false;
			return;
		}
		else if (Math.abs(dx) > Math.abs(dy))
		{
			timeToChange = flight(dx/Math.abs(dx),0);
		}
		else
		{
			timeToChange = flight(0,dy/Math.abs(dy));
		}
		if(timeToChange)
		{
			xObjective = xDeparture;
			yObjective = yDeparture;
		}
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

	public boolean flight(int dx, int dy)
	{
		flying= true;
		boolean atSword = false;;
		if ( !(x == heroi.getCood()[0] && y == heroi.getCood()[1]))
		{

			if (x == 'E')
			{
				sword = true;
				flying = false;
				lastPos = ' ';
				atSword = true;
			}
			

			MazeCli.game.setSpace(x , y , lastPos);

		}

		y += dy;
		x += dx;
		lastPos = MazeCli.game.getSpace( x ,y);
		MazeCli.game.setSpace(x, y, 'a');
		return atSword;
	}

}
