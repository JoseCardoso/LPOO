package maze.cli;
import java.util.Scanner;

import maze.game.MazeGame;


// TODO: Auto-generated Javadoc
/**
 * The Class MazeCli.
 * 
 * this class is responsible for console interface
 */
public class MazeCli {
	private MazeGame game = new MazeGame();


	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public MazeGame getGame() {
		return game;
	}

	/**
	 * Sets the game.
	 *
	 * @param game the new game
	 */
	public void setGame(MazeGame game) {

		this.game = game;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		new MazeCli().run();
	}

	/**
	 * Run.
	 * 
	 * Runs the app
	 */
	public  void run() 
	{
		int N,  NDragons,  diff;
		boolean choice;
		Scanner sc = new Scanner(System.in);
		String walk= "";
		
		
		choice = chooseMaze(sc);
		if (choice)
			N = getNumberLab(sc);
		else
			N = 10;
		NDragons = getNumberDragons(sc, N);
		diff = setDifficulty(sc);


		game.autoGen( N,  NDragons,  diff,  choice);
		
		while(game.update(walk))
		{
			printLab();
			walk = sc.nextLine(); 
		}
		
		sc.close();
		
		if(!game.heroIsAlive())
			System.out.println("\n\n\nDerrota...");
		else
			System.out.println("\n\n\nVitória!");
	}

	/**
	 * Prints the lab.
	 */
	public void printLab()
	{
		char[][] maze = game.getLab().updateLab();
		int N = game.getN();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	
	}

	/**
	 * Gets the lab size from user.
	 *
	 * @param sc the sc
	 * @return the numberLab, numberLab is the maze size
	 */
	public int getNumberLab(Scanner sc) {
		boolean rep;
		int N = 0;


		System.out.println("Insira o tamanho 'N' do puzzle (Deve ser ímpar). (NxN)");
		do {
			rep = false;
			try {
				String detect = sc.nextLine();
				N = Integer.parseInt(detect);
				if ( N %2 ==0 )
				{
					rep=true;
					System.out.println("Número par, por favor insira o número de novo.");
				}
				else
					rep = false;
			} catch (Exception e) {
				System.out.println("Erro, insira o número de novo.");
				rep = true;
			}


		} while (rep);
		return N;
	}

	/**
	 * Chose maze.
	 * 
	 * chooses the maze type
	 *
	 * @param sc the sc
	 * @return true, if random, false if standard
	 */
	public boolean chooseMaze(Scanner sc)
	{
		System.out.println("Insira 1 para o Standard Maze ou 2 para gerar um aleatorio");
		boolean rep;
		int N = 0;

		do {
			rep = false;
			try {
				String detect = sc.nextLine();
				N = Integer.parseInt(detect);
				if ( N != 1 && N != 2)
				{
					rep=true;
					System.out.println("Número inválido");
				}
				else{
					rep = false;
					if (N==1)
						return false;//standard
					else 
						return true;//random
				}
			} catch (Exception e) {
				System.out.println("Erro, insira o número de novo.");
				rep = true;
			}


		} while (rep);
		return true;
	}

	/**
	 * Gets the numero dragoes.
	 *
	 * @param sc the sc
	 * @param size the maze size
	 * @return the number of dragons
	 */
	public int getNumberDragons(Scanner sc , int size) {
		System.out.println("Insira o numero de dragoes: \n");
		boolean rep;
		int N = 0;

		do {
			rep = false;
			try {
				String detect = sc.nextLine();
				N = Integer.parseInt(detect);
				if ( N <=0 || N > size/2)
				{
					rep=true;
					System.out.println("Número inválido. insira o número de novo");
				}
				else
					rep = false;
			} catch (Exception e) {
				System.out.println("Erro, insira o número de novo.");
				rep = true;
			}


		} while (rep);


		return N;
	}

	/**
	 * Sets the difficulty.
	 *
	 * @param sc the sc
	 * @return the difficulty
	 */
	public int setDifficulty(Scanner sc){

		System.out.println("Insira a dificuldade de jogo:\n1-Dragoes parados\n2-Dragoes com movimento aleatorio\n3-Dragoes com movimento aleatorio intercalado com dormir");
		boolean rep;
		int N = 0;

		do {
			rep = false;
			try {
				String detect = sc.nextLine();
				N = Integer.parseInt(detect);
				if ( N !=1 && N != 2 && N!= 3)
				{
					rep=true;
					System.out.println("Dificuldade inválida. insira o número de novo");
				}
				else
					rep = false;
			} catch (Exception e) {
				System.out.println("Erro, insira o número de novo.");
				rep = true;
			}


		} while (rep);


		return N;

	}


}
