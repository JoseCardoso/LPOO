package maze.game;

import maze.cli.MazeCli;

public class Aguia extends MazeObject{

	private Heroi heroi;
	private Espada espada;
	private char lastPos;
	private int xDeparture, yDeparture, xObjective, yObjective;
	private boolean sword = false, alive = true, flying = false, arrived = false;

	public boolean getArrived() {
		return arrived;
	}

	public void setArrived(boolean arrived) {
		this.arrived = arrived;
	}



	Aguia(Heroi hero, Espada espada)
	{
		heroi = hero;
		this.espada = espada;
		x = heroi.getCood()[0];
		y = heroi.getCood()[1];
	}

	public void pos()
	{
		int[] coodH = heroi.getCood();
		x = coodH[0];
		y = coodH[1];
		xObjective = espada.getCood()[0];
		yObjective = espada.getCood()[1];
	}

	public void launch()
	{
		arrived = false;
		flying = true;
		xDeparture = heroi.getCood()[0];
		yDeparture = heroi.getCood()[1];
	}

	public void move()
	{
		int dx = xObjective - x;
		int dy = yObjective - y;
		boolean timeToChange = false;
		if(x == xDeparture && xDeparture == xObjective && y == yDeparture && y == yObjective)
			arrived = true;
		if(heroi.isComAguia())
		{
			x = heroi.getCood()[0];
			y = heroi.getCood()[1];
		}
		else
		{
			if(x == espada.getCood()[0] &&  y== espada.getCood()[1] && dx == 0 && dy == 0)
			{
				flying = false;
				timeToChange = true;
				foundSword();

			}

			else if (Math.abs(dx) > Math.abs(dy))
			{
				flight(dx/Math.abs(dx),0);
			}
			else if(dy !=0)
			{
				flight(0,dy/Math.abs(dy));
			}

			if(timeToChange)
			{
				xObjective = xDeparture;
				yObjective = yDeparture;
			}
		}
	}

	public boolean setDeparture()
	{
		if (!heroi.getSword())
		{
			xDeparture = heroi.getCood()[0];
			yDeparture = heroi.getCood()[1];
			return true;
		}
		else
			return false;
	}

	public boolean foundSword()
	{
		if(espada.getCood()[0] == x && espada.getCood()[1]  == y)
			sword = true;
		return sword;
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
			MazeCli.game.setSpace(x , y, 'E');
		}

		alive = false;


	}

	public boolean isFlying()
	{
		return flying;
	}


	public void flight(int dx, int dy)
	{	
		if (alive)
		{
			if (lastPos == 'E')
			{
				lastPos = ' ';
			}

			MazeCli.game.setSpace(x , y , lastPos);

			y += dy;
			x += dx;
			lastPos = MazeCli.game.getSpace( x ,y);
			MazeCli.game.setSpace(x, y, 'a');
		}

	}

	public void erase() {
		MazeCli.game.setSpace(x, y, lastPos);		
	}
}
