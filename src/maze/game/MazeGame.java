package maze.game;

import java.util.ArrayList;

public class MazeGame {
	private int N, dificuldade, NumeroD;
	private boolean LiveDragon = true, LiveHero = true, exit = false;
	private Espada espada = new Espada(this);
	private Heroi hero = new Heroi(this);
	private Aguia aguia = new Aguia(this);
	private Saida saida = new Saida(this);
	private ArrayList<Dragon> DragonList = new ArrayList<Dragon>();
	private Labirinto lab = new Labirinto(this);

	
	public String toString() {
		String res = "";

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				res += lab.getMaze()[i][j] + " ";
			}

			res += "\n";
		}

		return res;
	}

	public MazeGame() {

	}

	public Saida getSaida() {
		return saida;
	}

	public void setSaida(Saida saida) {
		this.saida = saida;
	}

	public boolean isLiveDragon() {
		return LiveDragon;
	}

	public void setLiveDragon(boolean liveDragon) {
		LiveDragon = liveDragon;
	}

	public ArrayList<Dragon> getDragonList() {
		return DragonList;
	}

	public void setDragonList(ArrayList<Dragon> dragonList) {
		DragonList = dragonList;
	}

	public Espada getEspada() {
		return espada;
	}

	public void setEspada(Espada espada) {
		this.espada = espada;
	}

	public Labirinto getLab() {
		return lab;
	}

	public int getDiff() {
		return dificuldade;
	}

	public void setDiff(int diff) {
		this.dificuldade = diff;
	}

	public void setLab(Labirinto lab) {
		this.lab = lab;
	}

	public Aguia getAguia() {
		return aguia;
	}

	public void setAguia(Aguia aguia) {
		this.aguia = aguia;
	}

	public void setHero(Heroi hero) {
		this.hero = hero;
	}

	public void autoGen(int N, int NDragons, int diff, boolean choice) {
		if (!choice)
			this.N = 10;
		else
			this.N = N;

		this.NumeroD = NDragons;
		this.dificuldade = diff;
		generate(choice);

	}

	public void generate(boolean choice) {
		// choice -false = standard
		lab.setN(N);
		if (choice)
			lab.createLab();
		else
			lab.createLab(lab.getStandardMaze());
		espada.pos();
		hero.pos();
		aguia.pos();
		hero.setAguia(aguia);
		saida.pos();
		lab.setAguia(aguia);
		for (int i = 0; i < NumeroD; i++)// contruie a lista, menos ou igual
		{
			Dragon temp = new Dragon(this);
			temp.pos();
			DragonList.add(temp);
		}
		lab.updateLab();
	}

	public void setN(int n) {
		N = n;
	}

	public int getNdragon() {
		return NumeroD;
	}

	public void setNdragon(int num) {
		NumeroD = num;
	}

	public boolean update(String dir) {

		hero.move(dir);
		aguia.move();
		if (dificuldade != 1)
			for (int i = 0; i < NumeroD; i++)
				DragonList.listIterator(i).next().moveRandom();
		bigEat();
		bigEatEagle();
		hero.pickUpEagle();
		if (hero.getCoord()[0] == saida.getCoord()[0]
				&& hero.getCoord()[1] == saida.getCoord()[1])
			exit = true;
		else
			exit = false;
		if (!LiveHero || (!LiveDragon && exit))
			return false;
		else
			return true;
	}

	public void bigEat() {
		for (int i = 0; i < NumeroD; i++) {
			DragonList.listIterator(i).next();
			if (DragonList.listIterator(i).next().eat()) {
				if (hero.getSword()) {
					DragonList.remove(i);
					NumeroD--;
				} else if (!DragonList.listIterator(i).next().getSleep())
					LiveHero = false;
			}
		}
		if (NumeroD == 0)
			LiveDragon = false;
	}

	public void bigEatEagle() {
		for (int i = 0; i < NumeroD; i++) {
			DragonList.listIterator(i).next();
			DragonList.listIterator(i).next().eatEagle();
		}
	}

	public boolean heroIsAlive() {
		return LiveHero;
	}

	public Heroi getHero() {
		return hero;
	}

	public boolean getDragon() {
		return LiveDragon;
	}

	public int getN() {
		return N;
	}

	public char getSpace(int x, int y) {
		return lab.getSpace(x, y);
	}
	
	public void setSpace(int x, int y,char C) {
		 lab.setSpace(x, y,C);
	}

	public int[] getEspadaPos() {
		return espada.getCoord();
	}

	public int[] getHeroPos() {
		return hero.getCoord();
	}

}
