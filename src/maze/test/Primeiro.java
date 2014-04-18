package maze.test;


import java.util.ArrayList;

import maze.cli.*;
import maze.game.*;
import static org.junit.Assert.*;

import org.junit.Test;



public class Primeiro {


	MazeCli cli = new MazeCli();
	MazeGame maze = new MazeGame();
	Labirinto lab = new Labirinto(maze);
	Heroi hero = new Heroi(maze);
	Espada espada = new Espada(maze);
	Saida saida = new Saida(maze);
	ArrayList<Dragon> dragons =new ArrayList<Dragon>();
	
	
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
		assertEquals(false,hero.getSword());
	}
	
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
	
	
	
	@Test
	public void testMoveWall()
	{
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(0);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		hero.setCoord(1, 1);
		lab.updateLab();
		hero.move("w");
		assertEquals(1,hero.getCoord()[1]);
		hero.setCoord(1, 1);
		lab.updateLab();
		hero.move("a");
		assertEquals(1,hero.getCoord()[0]);	
		
	}
	
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
		hero.setCoord(1, 1);
		lab.updateLab();
		hero.move("d");
		assertEquals(true,hero.getSword());		
	}
	
	
	
	@Test 
	public void testEat()
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
	
	@Test 
	public void testEatDragon()
	{
		maze.setN(10);
		maze.setDiff(1);
		maze.setNdragon(1);
		maze.generate(false);
		hero = maze.getHero();
		lab = maze.getLab();
		dragons = maze.getDragonList();
		dragons.listIterator(0).next().setCoord(3, 1);//colocar o dragão em 0|0 para não conseguir matar o herói
		hero.setCoord(2, 1);
		lab.updateLab();
		maze.bigEat();
		assertEquals(false,maze.heroIsAlive());		
	}
	
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
	
	
}