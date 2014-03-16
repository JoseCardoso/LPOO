package maze.test;


import java.util.ArrayList;

import maze.cli.*;
import maze.game.*;
import static org.junit.Assert.*;

import org.junit.Test;



public class Primeiro {


	Labirinto lab = new Labirinto();
	Heroi hero = new Heroi();
	Espada espada = new Espada();
	MazeCli cli = new MazeCli();
	MazeGame maze = new MazeGame();
	private ArrayList<Dragon> dragons =new ArrayList<Dragon>();
	
	
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
	
	@Test
	public void testMoveFree()
	{
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(0);
		maze.generate(false);
		lab = maze.getLab();
		hero = maze.getHero();
		hero.pos(1, 1);
		lab.printLab();
		hero.move("s");
		assertEquals(2,hero.getCood()[1]);
		hero.pos(1, 1);
		lab.printLab();
		hero.move("d");
		assertEquals(2,hero.getCood()[0]);
	}
	
	
	
	@Test
	public void testMoveWall()
	{
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(0);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		hero.pos(1, 1);
		lab.printLab();
		hero.move("w");
		assertEquals(1,hero.getCood()[1]);
		hero.pos(1, 1);
		lab.printLab();
		hero.move("a");
		assertEquals(1,hero.getCood()[0]);	
		
	}
	
	@Test 
	public void testPickSword()
	{
		
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(1);
		maze.generate(false);
		hero = maze.getHero();
		espada = maze.getEspada();
		lab = maze.getLab();
		dragons = maze.getDragonList();
		dragons.listIterator(0).next().setCood(0, 0);//colocar o dragão em 0|0 para não conseguir matar o herói
		espada.setCood(2, 1);
		hero.pos(1, 1);
		lab.printLab();
		hero.move("d");
		assertEquals(true,hero.getSword());		
	}
	
	
	
	@Test 
	public void testEat()
	{
		
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(1);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		dragons = maze.getDragonList();
		dragons.listIterator(0).next().setCood(3, 1);//colocar o dragão em 0|0 para não conseguir matar o herói
	
		hero.pos(1, 1);
		hero.move("d");
		lab.printLab();
		maze.bigEat();
		assertEquals(false,maze.getLiveHero());		
	}
	
	@Test 
	public void testEatDragon()
	{
		
		maze = cli.getGame();
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(1);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		dragons = maze.getDragonList();
		dragons.listIterator(0).next().setCood(3, 1);//colocar o dragão em 0|0 para não conseguir matar o herói
		hero.pos(1, 1);
		hero.move("d");
		lab.printLab();
		maze.bigEat();
		assertEquals(false,maze.getLiveHero());		
	}
	
}
