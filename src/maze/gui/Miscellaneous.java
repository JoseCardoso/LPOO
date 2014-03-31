package maze.gui;

import java.awt.event.KeyEvent;

public class Miscellaneous {

		public static String getStringOfKey(int key) {//Devolve string correspondente à tecla premida 
			switch (key) {
			case KeyEvent.VK_UP: return "w";
			case KeyEvent.VK_DOWN: return "s";
			case KeyEvent.VK_LEFT: return "a";
			case KeyEvent.VK_RIGHT: return "d";
			case KeyEvent.VK_SPACE: return "e";
						}

			return "";
		}
 
		
		public static int getKeyFromString(String str) { //Devolve tecla correspondente à string usada na logica de jogo
			str = str.toUpperCase();

			switch (str) {
			case "A": return KeyEvent.VK_LEFT;
			case "S": return KeyEvent.VK_DOWN;
			case "D": return KeyEvent.VK_RIGHT;
			case "W": return KeyEvent.VK_UP;
			case "E": return KeyEvent.VK_SPACE;
			
			}

			return 0;
		}

}
