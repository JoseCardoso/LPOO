package maze.cli;
import java.util.Scanner;

import maze.game.MazeGame;


public class MazeCli {
	private MazeGame game = new MazeGame();


	public MazeGame getGame() {
		return game;
	}

	public void setGame(MazeGame game) {

		this.game = game;
	}

	public static void main(String[] args)
	{
		new MazeCli().run();
	}

	public  void run() 
	{
		int N,  NDragons,  diff;
		boolean choice;
		Scanner sc = new Scanner(System.in);
		String walk= "";
		
		
		choice = choseMaze(sc);
		if (choice)
			N = getNumeroLab(sc);
		else
			N = 10;
		NDragons = getNumeroDragoes(sc);
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

	public int getNumeroLab(Scanner sc) {
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

	public boolean choseMaze(Scanner sc)
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
						return true;//aleatorio
				}
			} catch (Exception e) {
				System.out.println("Erro, insira o número de novo.");
				rep = true;
			}


		} while (rep);
		return true;
	}

	public int getNumeroDragoes(Scanner sc) {
		System.out.println("Insira o numero de dragoes: \n");
		boolean rep;
		int N = 0;

		do {
			rep = false;
			try {
				String detect = sc.nextLine();
				N = Integer.parseInt(detect);
				if ( N <=0 || N > game.getN()/2)
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
