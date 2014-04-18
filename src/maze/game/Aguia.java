package maze.game;

import java.io.IOException;
import java.io.ObjectOutputStream;


public class Aguia extends MazeObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Heroi heroi;
	private Espada espada;
	private int xDeparture, yDeparture, xObjective, yObjective;
	private boolean sword = false, alive = true, flying = false, arrived = false;

	
	public boolean getArrived() {
		return arrived;
	}

	public void setArrived(boolean arrived) {
		this.arrived = arrived;
	}



	public Aguia(MazeGame mazeGame) {
		super(mazeGame);
		heroi = game.getHero();
		espada = game.getEspada();
	}

	public void pos()
	{
		int[] coodH = heroi.getCoord();
		x = coodH[0];
		y = coodH[1];
		xObjective = espada.getCoord()[0];
		yObjective = espada.getCoord()[1];
	}

	public void launch()
	{
		arrived = false;
		flying = true;
		xDeparture = heroi.getCoord()[0];
		yDeparture = heroi.getCoord()[1];
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
			x = heroi.getCoord()[0];
			y = heroi.getCoord()[1];
		}
		else
		{
			if(x == espada.getCoord()[0] &&  y== espada.getCoord()[1] && dx == 0 && dy == 0)
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
			xDeparture = heroi.getCoord()[0];
			yDeparture = heroi.getCoord()[1];
			return true;
		}
		else
			return false;
	}

	public boolean foundSword()
	{
		if(espada.getCoord()[0] == x && espada.getCoord()[1]  == y)
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
			espada.setCoord(x, y);
		}

		alive = false;


	}

	public boolean isFlying()
	{
		return flying;
	}


	public void flight(int dx, int dy){
		if (alive)
		{
			y += dy;
			x += dx;
		}

	}
	

}
