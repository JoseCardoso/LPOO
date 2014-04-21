package maze.test;


import java.util.ArrayList;

import maze.cli.*;
import maze.game.*;
import static org.junit.Assert.*;

import org.junit.Test;



// TODO: Auto-generated Javadoc
/**
 * The Class HeroTest.
 * 
 * This class checks if the behavior of the hero is correct
 */
public class HeroTest {


	MazeCli cli = new MazeCli();
	MazeGame maze = new MazeGame();
	Labyrinth lab = new Labyrinth(maze);
	Hero hero = new Hero(maze);
	Sword espada = new Sword(maze);
	Exit saida = new Exit(maze);
	ArrayList<Dragon> dragons =new ArrayList<Dragon>();
	
	/**
	 * Test move free.
	 * 
	 * Test passes if hero can move
	 */
	@Test
	public void testMoveFree()
	{
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(0);
		maze.generate(false);
		lab = maze.getLab();
		hero = maze.getHero();
		hero.setCoord(1, 1);
		lab.updateLab();
		hero.move("s");
		assertEquals(2,hero.getCoord()[1]);
		hero.setCoord(1, 1);
		lab.updateLab();
		hero.move("d");
		assertEquals(2,hero.getCoord()[0]);
	}
	
	
	
	/**
	 * Test move wall.
	 * 
	 * Test passes if hero moves against wall
	 */
	@Test
	public void testMoveWall()
	{
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(0);
		maze.generate(false);
		hero = maze.getHero();
		saida = maze.getSaida();
		lab = maze.getLab();
		hero.setCoord(1, 1);
		saida.setCoord(0, 0);
		lab.updateLab();
		hero.move("w");
		assertEquals(1,hero.getCoord()[1]);
		hero.setCoord(1, 1);
		lab.updateLab();
		hero.move("a");
		assertEquals(1,hero.getCoord()[0]);	
		
	}
	
	/**
	 * Test pick sword.
	 * 
	 * Test passes if hero finds sword and keeps it
	 */
	@Test 
	public void testPickSword()
	{
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(1);
		maze.generate(false);
		hero = maze.getHero();
		espada = maze.getEspada();
		lab = maze.getLab();
		dragons = maze.getDragonList();
		dragons.listIterator(0).next().setCoord(0, 0);//colocar o dragão em 0|0 para não conseguir matar o herói
		espada.setCoord(3, 1);
		hero.setCoord(2, 1);
		lab.updateLab();
		hero.move("d");
		assertEquals(true,hero.hasSword());		
	}
	
	
	
	/**
	 * Test hero gets killed.
	 * 
	 * Test passes if the hero gets killed by a dragon
	 */
	@Test 
	public void testHeroGetsKilled()
	{
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(1);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		dragons = maze.getDragonList();
		dragons.listIterator(0).next().setCoord(3, 1);//colocar o dragão em 0|0 para não conseguir matar o herói
	
		hero.setCoord(1, 1);
		hero.move("d");
		lab.updateLab();
		maze.bigEat();
		assertEquals(false,maze.heroIsAlive());		
	}
	
	/**
	 * Test kill dragon.
	 * 
	 * Test passes if hero kills dragon
	 */
	@Test 
	public void testKillDragon()
	{
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(1);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		espada = maze.getEspada();
		espada.setCoord(2, 1);
		dragons = maze.getDragonList();
		dragons.listIterator(0).next().setCoord(3, 1);//colocar o dragão em 0|0 para não conseguir matar o herói
		hero.setCoord(1, 1);
		lab.updateLab();
		hero.move("d");
		lab.updateLab();
		maze.bigEat();
		assertEquals(true,maze.heroIsAlive());		
	}
	
	/**
	 * Test win game.
	 * 
	 * Test passes if Hero gets to exit and wins, meaning he has killed all the dragons
	 */
	@Test 
	public void testWinGame()
	{
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(0);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		saida = maze.getSaida();
		int x = saida.getCoord()[0], y = saida.getCoord()[1];
		maze.setSpace(x, y, 'X');
		saida.setCoord(0, 1);
		hero.setSword(true);
		lab.updateLab();
		x = hero.getCoord()[0];
		y = hero.getCoord()[1];
		maze.setSpace(x, y, ' ');
		hero.setCoord(1, 1);
		assertEquals(false,maze.update("a"));		
		assertEquals(false,maze.isLiveDragon());
	}

	/**
	 * Test cannot win. 
	 * 
	 * Test passes if Hero gets to exit and didn't win, meaning he didn't kill all the dragons
	 */
	@Test 
	public void testCannotWin()
	{
		int x, y;
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(1);
		maze.generate(false);
		lab = maze.getLab();
		hero = maze.getHero();
		saida = maze.getSaida();
		x = hero.getCoord()[0];
		y = hero.getCoord()[1];
		lab.setSpace(x, y, ' ');
		hero.setCoord(1, 1);
		x = saida.getCoord()[0];
		y = saida.getCoord()[1];
		lab.setSpace(x, y, 'X');
		saida.setCoord(0, 1);
		hero.setCoord(1, 1);
		lab.updateLab();
		
		assertEquals(true,maze.update("a"));
		assertEquals(false,hero.hasSword());
	}
	
	
}