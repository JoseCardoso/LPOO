package maze.cli;
import java.util.Scanner;

import maze.game.MazeGame;


public class MazeCli {
	static MazeCli cli = new MazeCli();
	static public MazeGame game = new MazeGame();
	
	public static MazeGame getGame() {
		return game;
	}

	public static void setGame(MazeGame game) {
		MazeCli.game = game;
	}

	public static void main(String[] args) 
	{
		String walk= "";
		boolean choice = cli.choseMaze();

		if (choice)
		cli.getNumeroLab();
		else
			game.setN(10);
		
		cli.getNumeroDragoes();
		game.generate(choice);
		cli.setDifficulty();
		while(game.start(walk))
		{
			Scanner sc = new Scanner(System.in);
			walk = sc.nextLine(); 
		}

		if(!game.getLiveHero())
			System.out.println("\n\n\nMorrrrrrrrrrrreu!");
		else
			System.out.println("\n\n\nVitória!");
	}
	
	public void getNumeroLab() {
		System.out.println("Insira o tamanho 'N' do puzzle (Deve ser ímpar). (NxN)");
		boolean rep;
		int N = 0;
		
		do {
			rep = false;
			try {
				Scanner sc = new Scanner(System.in);
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
		game.setN(N);
	}
	
	public static void printLab(char[][] maze, int N)
	{
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	

	public boolean choseMaze()
	{
		System.out.println("Insira 1 para o Standard Maze ou 2 para gerar um aleatorio");
		boolean rep;
		int N = 0;
		
		do {
			rep = false;
			try {
				Scanner sc = new Scanner(System.in);
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
	
	public void getNumeroDragoes() {
		System.out.println("Insira o numero de dragoes: \n");
		boolean rep;
		int N = 0;
		
		do {
			rep = false;
			try {
				Scanner sc = new Scanner(System.in);
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
		
		
		game.setNdragon(N);
	}

	public void setDifficulty(){
		
		System.out.println("Insira a dificuldade de jogo:\n1-Dragoes parados\n2-Dragoes com movimento aleatorio\n3-Dragoes com movimento aleatorio intercalado com dormir");
		boolean rep;
		int N = 0;
		
		do {
			rep = false;
			try {
				Scanner sc = new Scanner(System.in);
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
		
		
		game.setDiff(N);
		
	}
	
	
}
