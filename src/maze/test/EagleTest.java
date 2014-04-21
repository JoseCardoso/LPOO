package maze.test;


import maze.cli.*;
import maze.game.*;
import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Auto-generated Javadoc
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


/**
 * The Class EagleTest.
 * 
 * This class checks if the behavior of the eagle is correct
 */
public class EagleTest {

	MazeCli cli = new MazeCli();
	MazeGame maze = new MazeGame();
	Labyrinth lab = new Labyrinth(maze);
	Hero hero = new Hero(maze);
	Sword espada = new Sword(maze);
	Exit saida = new Exit(maze);
	Eagle aguia;
	
	
	
	/**
	 * Test launch eagle.
	 * 
	 * 
	 * Test passes is eagle leaves hero to go get the sword
	 */
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
		assertEquals(false,hero.isWithEagle());	
		lab.updateLab();
	}
	
	/**
	 * Test move eagle.
	 * 
	 * Test passes if eagle moves towards the sword
	 */
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
	
	/**
	 * Test find sword.
	 * 
	 * Test passes if eagle finds sword and picks it
	 */
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
	
	/**
	 * Test pick eagle.
	 * 
	 * Test passes if eagle returns to the hero and gives him the sword
	 */
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
		aguia.move();
		lab.updateLab();
		aguia.move();
		lab.updateLab();

		assertEquals(true, hero.pickUpEagle());
		

	}
	
	

}
