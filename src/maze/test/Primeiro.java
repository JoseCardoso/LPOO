package maze.test;


import maze.cli.*;
import maze.game.*;
import static org.junit.Assert.*;
import org.junit.Test;



public class Primeiro {

	char tabuleiro[][] = 
		{ 
			{'X','X','X','X','X','X','X','X','X','X'}, 
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ','X','X',' ','X',' ','X',' ','X'}, 
			{'X',' ','X','X',' ','X',' ','X',' ','X'}, 
			{'X',' ','X','X',' ','X',' ','X',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ','X',' ','X'}, 
			{'X',' ','X','X',' ','X',' ','X',' ','X'}, 
			{'X',' ','X','X',' ','X',' ','X',' ','X'}, 
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'}, 
			{'X','X','X','X','X','X','X','X','X','X'} 
			};
	
	Heroi hero = new Heroi();
	Labirinto lab = new Labirinto();
	
	MazeGame maze = new MazeGame();
	
	@Test
	public void testMoveFree()
	{
		lab.createLab(tabuleiro);
		hero.pos(1, 1);
		hero.move("s");
		assertEquals(2,hero.getCood()[1]);
		hero.pos(1, 1);
		hero.move("d");
		assertEquals(2,hero.getCood()[0]);
	}
	
}
