package mazeGame;

import mazeCli.MazeCli;

public class Aguia extends MazeObject{

	private Heroi heroi;
	private Espada espada;
	private char lastPos;
	private int x ,y, xDeparture, yDeparture, xObjective, yObjective;
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
		withHero = false;
		xDeparture = heroi.getCood()[0];
		yDeparture = heroi.getCood()[1];
	}

	public void move()
	{
		int dx = xObjective -x;
		int dy = yObjective -y;
		boolean timeToChange = false;
		System.out.println("x : " + x);
		System.out.println("y : " + y);
		System.out.println(xObjective);
		System.out.println(yObjective);
		System.out.println(sword);
		System.out.println(flying);
		System.out.println(timeToChange);
		if(withHero)
		{
			x = heroi.getCood()[0];
			y = heroi.getCood()[1];
		}
		else
		{
			if(dx == 0 && dy == 0)
			{
				flying = false;
				timeToChange = true;
				
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
				flying = true;
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
		System.out.println("encontrou a espada" + sword);
		flying= true;
		boolean atSword = false;;
		if ( !(x == heroi.getCood()[0] && y == heroi.getCood()[1]))
		{

			if (dx==0 && dy==0)
			{
				System.out.println("chegou");
				foundSword();
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
		System.out.println("flight = " + atSword);
		return atSword;
	}

}
