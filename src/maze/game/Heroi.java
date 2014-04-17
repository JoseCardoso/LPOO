package maze.game;

import java.util.Random;

public class Heroi extends MazeObject {
	private boolean Sword = false;
	private boolean comAguia = true;
	private Aguia aguia;

	public Heroi(MazeGame mazeGame) {
		super(mazeGame);
		aguia = game.getAguia();
	}

	public Aguia getAguia() {
		return aguia;
	}

	public boolean isComAguia() {
		return comAguia;
	}

	public void setComAguia(boolean comAguia) {
		this.comAguia = comAguia;
	}

	public void setAguia(Aguia aguia) {
		this.aguia = aguia;
	}

	public void pos(int x, int y)// posição definida
	{
		this.x = x;
		this.y = y;
	}

	public void pos() // posição aleatória
	{
		Random r = new Random();
		int N = game.getN();

		do {
			x = r.nextInt(N - 1) + 1;
			y = r.nextInt(N - 1) + 1;
		} while (game.getSpace(x, y) != ' ');
	}

	public void move(String walk) {
		if (walk.equals("a") || walk.equals("A"))
			trueMove(0, -1);
		else if (walk.equals("D") || walk.equals("d"))
			trueMove(0, 1);
		else if (walk.equals("W") || walk.equals("w"))
			trueMove(-1, 0);
		else if (walk.equals("S") || walk.equals("s"))
			trueMove(1, 0);
		else if ((walk.equals("e") || walk.equals("E")) && comAguia) {
			if (comAguia && !Sword) {
				comAguia = false;
				aguia.launch();
			}
		}
	}

	public boolean pickUpEagle() {

		if (((Math.abs(aguia.getCoord()[0] - x) <= 1 && Math
				.abs(aguia.getCoord()[1] - y) == 0) || (Math
				.abs(aguia.getCoord()[0] - x) == 0 && Math
				.abs(aguia.getCoord()[1] - y) <= 1))
				&& aguia.isAlive() && !aguia.isFlying()) 
		{
			comAguia = true;
			if (aguia.getSword())
				Sword = true;
		
			return true;
		} else
			return false;

	}

	public void trueMove(int dy, int dx) {
		char Valid;
		Valid = game.getSpace(x + dx, y + dy);
		if (Valid == ' ' || 
				(x+dx == game.getSaida().getCood()[0] &&
						y+dy == game.getSaida().getCood()[1] )) 
		{
			y += dy;
			x += dx;
		}
		if (dx == game.getEspada().getCoord()[0] &&
				dy == game.getEspada().getCoord()[1])//testa caso o heroi esteja em cima da espada e nao a tenha apanhado
		{
			Sword = true;
		}
		if (x+dx == game.getEspada().getCoord()[0] &&
				y+dy == game.getEspada().getCoord()[1]) 
		{
			Sword = true;
			y += dy;
			x += dx;
		}
	}

	public boolean getSword() {
		return Sword;
	}

	public void setSword(boolean Sword) {
		this.Sword = Sword;
	}
}
