package maze.test;

import java.util.ArrayList;

import maze.cli.*;
import maze.game.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Segundo {
	
	Labirinto lab = new Labirinto();
	Heroi hero = new Heroi();
	Espada espada = new Espada();
	MazeCli cli = new MazeCli();
	MazeGame maze = new MazeGame();
	Saida saida = new Saida();
	private ArrayList<Dragon> dragons =new ArrayList<Dragon>();


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
		hero.pos(0, 0);
		dragons = maze.getDragonList();
		Dragon d = dragons.listIterator(0).next();
		
		MockedRandomWrapper teste1 = new MockedRandomWrapper(1);//irá fazer com que o aleatório saia com este valor

		d.setCood(1, 1);
		d.setRandomWrapper(teste1);
		d.moveRandom();
		lab.printLab();
		assertEquals(2,d.getCood()[1]);
		assertEquals(1,d.getCood()[0]);
		d.setCood(1, 1);

		MockedRandomWrapper teste2 = new MockedRandomWrapper(2);
		d.setCood(1, 1);
		d.setRandomWrapper(teste2);
		d.moveRandom();
		lab.printLab();
		assertEquals(1,d.getCood()[1]);
		assertEquals(2,d.getCood()[0]);
	}
	
	
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
		hero.pos(0, 0);
		dragons = maze.getDragonList();
		Dragon d = dragons.listIterator(0).next();
		
		MockedRandomWrapper teste1 = new MockedRandomWrapper(5);//irá fazer com que o aleatório saia com este valor

		d.setCood(1, 1);
		d.setRandomWrapper(teste1);
		d.moveRandom();//dormir
		assertEquals(true,d.getSleep());
		d.moveRandom();	//acordar
		assertEquals(false,d.getSleep());

	}
	
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
		hero.pos(0, 0);
		dragons = maze.getDragonList();
		Dragon d1 = dragons.listIterator(0).next();
		Dragon d2 = dragons.listIterator(1).next();
		
		MockedRandomWrapper teste1 = new MockedRandomWrapper(5);//irá fazer com que o aleatório saia com este valor

		d1.setCood(9, 9);
		d1.setRandomWrapper(teste1);
		d1.moveRandom();//dormir
		assertEquals(true,d1.getSleep());
		d1.moveRandom();	//acordar
		assertEquals(false,d1.getSleep());
		
		MockedRandomWrapper teste2 = new MockedRandomWrapper(2);
		d2.setCood(1, 1);
		d2.setRandomWrapper(teste2);
		d2.moveRandom();
		lab.printLab();
		assertEquals(1,d2.getCood()[1]);
		assertEquals(2,d2.getCood()[0]);
	}
	
	
}
