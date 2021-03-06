package maze.test;

import java.util.ArrayList;

import maze.cli.*;
import maze.game.*;
import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class DragonTest.
 * 
 * This class checks if the behavior of the dragons are correct
 */
public class DragonTest {
	
	MazeCli cli = new MazeCli();
	MazeGame maze = new MazeGame();
	Labyrinth lab = new Labyrinth(maze);
	Hero hero = new Hero(maze);
	Sword espada = new Sword(maze);
	Exit saida = new Exit(maze);
	private ArrayList<Dragon> dragons =new ArrayList<Dragon>();


	/**
	 * Test move free.
	 * 
	 * Test passes if dragon can move
	 */
	@Test
	public void testMoveFree()
	{
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(1);
		maze.generate(false);
		lab = maze.getLab();
		hero = maze.getHero();
		hero.setCoord(0, 0);
		dragons = maze.getDragonList();
		Dragon d = dragons.listIterator(0).next();
		
		MockedRandomWrapper teste1 = new MockedRandomWrapper(1);//ir� fazer com que o aleat�rio saia com este valor

		d.setCoord(1, 1);
		d.setRandomWrapper(teste1);
		d.moveRandom();
		lab.updateLab();
		assertEquals(2,d.getCoord()[1]);
		assertEquals(1,d.getCoord()[0]);
		d.setCoord(1, 1);

		MockedRandomWrapper teste2 = new MockedRandomWrapper(2);
		d.setCoord(1, 1);
		d.setRandomWrapper(teste2);
		d.moveRandom();
		lab.updateLab();
		assertEquals(1,d.getCoord()[1]);
		assertEquals(2,d.getCoord()[0]);
	}
	
	
	/**
	 * Test sleep.
	 * 
	 * Test passes if dragon goes to sleep
	 */
	@Test
	public void testSleep()
	{
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(3);
		maze.setNdragon(1);
		maze.generate(false);
		lab = maze.getLab();
		hero = maze.getHero();
		hero.setCoord(0, 0);
		dragons = maze.getDragonList();
		Dragon d = dragons.listIterator(0).next();
		
		MockedRandomWrapper teste1 = new MockedRandomWrapper(5);//ir� fazer com que o aleat�rio saia com este valor

		d.setCoord(1, 1);
		d.setRandomWrapper(teste1);
		d.moveRandom();//dormir
		assertEquals(true,d.isSleeping());
		d.moveRandom();	//acordar
		assertEquals(false,d.isSleeping());

	}
	
	/**
	 * Multiple dragons.
	 * 
	 * Test passes if various dragons can move and sleep
	 */
	@Test
	public void MultipleDragons()
	{
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(3);
		maze.setNdragon(2);
		maze.generate(false);
		lab = maze.getLab();
		hero = maze.getHero();
		hero.setCoord(0, 0);
		dragons = maze.getDragonList();
		Dragon d1 = dragons.listIterator(0).next();
		Dragon d2 = dragons.listIterator(1).next();
		
		MockedRandomWrapper teste1 = new MockedRandomWrapper(5);//ir� fazer com que o aleat�rio saia com este valor

		d1.setCoord(9, 9);
		d1.setRandomWrapper(teste1);
		d1.moveRandom();//dormir
		assertEquals(true,d1.isSleeping());
		d1.moveRandom();	//acordar
		assertEquals(false,d1.isSleeping());
		
		MockedRandomWrapper teste2 = new MockedRandomWrapper(2);
		d2.setCoord(1, 1);
		d2.setRandomWrapper(teste2);
		d2.moveRandom();
		lab.updateLab();
		assertEquals(1,d2.getCoord()[1]);
		assertEquals(2,d2.getCoord()[0]);
	}
	
	
}
