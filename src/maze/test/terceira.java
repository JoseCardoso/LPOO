package maze.test;

import java.util.ArrayList;

import maze.cli.*;
import maze.game.*;
import static org.junit.Assert.*;

import org.junit.Test;

/*			{'X','X','X','X','X','X','X','X','X','X'},           isto é para ver o labirinto estático
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'X',' ','X','X',' ','X',' ','X',' ','X'}, 
			{'X',' ','X','X',' ','X',' ','X',' ','X'}, 
			{'X',' ','X','X',' ','X',' ','X',' ','X'}, 
			{'X',' ',' ',' ',' ',' ',' ','X',' ','X'}, 
			{'X',' ','X','X',' ','X',' ','X',' ','X'}, 
			{'X',' ','X','X',' ','X',' ','X',' ','X'}, 
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'}, 
			{'X','X','X','X','X','X','X','X','X','X'} 
*/


public class terceira {

	MazeCli cli = new MazeCli();
	MazeGame maze = new MazeGame(cli);
	Labirinto lab = new Labirinto(maze);
	Heroi hero = new Heroi(maze);
	Espada espada = new Espada(maze);
	Saida saida = new Saida(maze);
	Aguia aguia;
	private ArrayList<Dragon> dragons =new ArrayList<Dragon>();
	
	
	
	@Test
	public void testLaunchEagle()
	{
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(0);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		hero.pos(1, 1);
		aguia = maze.getAguia();
		espada.setCood(1, 5);
		hero.pos(1, 1);
		aguia.pos();
		assertEquals(1,aguia.getCood()[1]);
		assertEquals(1,aguia.getCood()[0]);
		lab.printLab();
		hero.move("e");
		assertEquals(false,hero.isComAguia());	
		lab.printLab();
	}
	
	@Test
	public void testMoveEagle()
	{
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(0);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		aguia = maze.getAguia();
		hero.pos(1, 1);
		int x = maze.getEspada().getCood()[0];
		int y = maze.getEspada().getCood()[1];
		maze.getEspada().setCood(1, 5);
		aguia.pos();
		assertEquals(1,aguia.getCood()[1]);
		assertEquals(1,aguia.getCood()[0]);
		lab.printLab();
		hero.move("e");
		lab.printLab();
		aguia.move();
		lab.printLab();
		assertEquals(2,aguia.getCood()[1]);
	}
	
	@Test
	public void testFindSword(){
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(0);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		aguia = maze.getAguia();
		hero.pos(1, 1);
		int x = maze.getEspada().getCood()[0];
		int y = maze.getEspada().getCood()[1];
		maze.setSpace(x, y, ' ');
		maze.getEspada().setCood(1, 4);
		aguia.pos();
		lab.printLab();
		hero.move("e");
		lab.printLab();
		aguia.move();
		lab.printLab();
		aguia.move();
		lab.printLab();
		aguia.move();
		lab.printLab();
		assertEquals(true,aguia.foundSword());
	}
	
	@Test
	public void testPickEagle(){
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(0);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		aguia = maze.getAguia();
		hero.pos(1, 1);
		int x = maze.getEspada().getCood()[0];
		int y = maze.getEspada().getCood()[1];
		maze.setSpace(x, y, ' ');
		maze.getEspada().setCood(1, 4);
		aguia.pos();
		lab.printLab();
		hero.move("e");
		lab.printLab();
		aguia.move();
		lab.printLab();
		aguia.move();
		lab.printLab();
		aguia.move();
		lab.printLab();
		aguia.move();
		lab.printLab();
		aguia.move();
		lab.printLab();
		aguia.move();
		lab.printLab();
		assertEquals(true, hero.pickUpEagle());
		

	}
	
	

}
