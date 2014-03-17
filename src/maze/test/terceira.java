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
	
	Labirinto lab = new Labirinto();
	Heroi hero = new Heroi();
	Espada espada = new Espada();
	MazeCli cli = new MazeCli();
	MazeGame maze = new MazeGame();
	Saida saida = new Saida();
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
		espada = maze.getEspada();
		aguia = maze.getAguia();
		hero.pos(1, 1);
		aguia.pos();
		espada.setCood(1, 5);
		assertEquals(1,aguia.getCood()[1]);
		assertEquals(1,aguia.getCood()[0]);
		lab.printLab();
		hero.move("e");
		lab.printLab();
		aguia.move();
		lab.printLab();
		assertEquals(2,aguia.getCood()[0]);
		lab.printLab();
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
		espada = maze.getEspada();
		aguia = maze.getAguia();
		hero.pos(1, 1);
		aguia.pos();
		espada.setCood(1, 3);
		lab.printLab();
		hero.move("e");
		lab.printLab();
		aguia.move();
		aguia.move();
		lab.printLab();
		aguia.foundSword();
		assertEquals(true,aguia.getSword());
		lab.printLab();
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
		espada = maze.getEspada();
		aguia = maze.getAguia();
		hero.pos(1, 1);
		aguia.pos();
		espada.setCood(1, 3);
		lab.printLab();
		hero.move("e");
		lab.printLab();
		aguia.move();
		aguia.move();
		lab.printLab();
		aguia.foundSword();
		aguia.move();
		aguia.move();
		lab.printLab();
		hero.pickUpEagle();
		assertEquals(true,hero.getSword());
		assertEquals(true,hero.isComAguia());

	}
	
	

}
