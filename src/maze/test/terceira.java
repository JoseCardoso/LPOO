package maze.test;


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
	MazeGame maze = new MazeGame();
	Labirinto lab = new Labirinto(maze);
	Heroi hero = new Heroi(maze);
	Espada espada = new Espada(maze);
	Saida saida = new Saida(maze);
	Aguia aguia;
	
	
	
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
		hero.setCoord(1, 1);
		aguia = maze.getAguia();
		espada.setCoord(1, 5);
		hero.setCoord(1, 1);
		aguia.pos();
		assertEquals(1,aguia.getCoord()[1]);
		assertEquals(1,aguia.getCoord()[0]);
		lab.updateLab();
		hero.move("e");
		assertEquals(false,hero.isComAguia());	
		lab.updateLab();
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
		hero.setCoord(1, 1);
		maze.getEspada().setCoord(1, 5);
		aguia.pos();
		assertEquals(1,aguia.getCoord()[1]);
		assertEquals(1,aguia.getCoord()[0]);
		lab.updateLab();
		hero.move("e");
		lab.updateLab();
		aguia.move();
		lab.updateLab();
		assertEquals(2,aguia.getCoord()[1]);
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
		hero.setCoord(1, 1);
		int x = maze.getEspada().getCoord()[0];
		int y = maze.getEspada().getCoord()[1];
		maze.setSpace(x, y, ' ');
		maze.getEspada().setCoord(1, 4);
		aguia.pos();
		lab.updateLab();
		hero.move("e");
		lab.updateLab();
		aguia.move();
		lab.updateLab();
		aguia.move();
		lab.updateLab();
		aguia.move();
		lab.updateLab();
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
		hero.setCoord(1, 1);
		int x = maze.getEspada().getCoord()[0];
		int y = maze.getEspada().getCoord()[1];
		maze.setSpace(x, y, ' ');
		maze.getEspada().setCoord(1, 4);
		aguia.pos();
		lab.updateLab();
		hero.move("e");
		lab.updateLab();
		aguia.move();
		lab.updateLab();
		aguia.move();
		lab.updateLab();
		aguia.move();
		lab.updateLab();
		aguia.move();
		lab.updateLab();
		aguia.move();
		lab.updateLab();
		aguia.move();
		lab.updateLab();
		assertEquals(true, hero.pickUpEagle());
		

	}
	
	

}
