package maze.cli;
import java.util.Scanner;

import maze.game.MazeGame;


public class MazeCli {
	static MazeCli cli = new MazeCli();
	static public MazeGame game = new MazeGame();
	
	public static void main(String[] args) 
	{
		String walk;

		cli.getNumeroLab();
		game.generate();
		do
		{
			Scanner sc = new Scanner(System.in);
			walk = sc.nextLine(); 
		}while(game.start(walk));

		if(!game.getLiveHero())
			System.out.println("\n\n\nMorrrrrrrrrrrreu!");
		else
			System.out.println("\n\n\nVit�ria!");
	}
	
	public void getNumeroLab() {
		System.out.println("Insira o tamanho 'N' do puzzle (Deve ser �mpar). (NxN)");
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
					System.out.println("N�mero par, por favor insira o n�mero de novo.");
				}
				else
					rep = false;
			} catch (Exception e) {
				System.out.println("Erro, insira o n�mero de novo.");
				rep = true;
			}
			
			
		} while (rep);
		game.setN(N);
	}
	
}
