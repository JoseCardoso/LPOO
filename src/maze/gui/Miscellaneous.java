package maze.gui;

import java.awt.event.KeyEvent;

public class Miscellaneous {

	public static String getStringOfKey(int key) {
		switch (key) {
		case KeyEvent.VK_A: return "A";
		case KeyEvent.VK_B: return "B";
		case KeyEvent.VK_C: return "C";
		case KeyEvent.VK_D: return "D";
		case KeyEvent.VK_E: return "E";
		case KeyEvent.VK_F: return "F";
		case KeyEvent.VK_G: return "G";
		case KeyEvent.VK_H: return "H";
		case KeyEvent.VK_I: return "I";
		case KeyEvent.VK_J: return "J";
		case KeyEvent.VK_K: return "K";
		case KeyEvent.VK_L: return "L";
		case KeyEvent.VK_M: return "M";
		case KeyEvent.VK_N: return "N";
		case KeyEvent.VK_O: return "O";
		case KeyEvent.VK_P: return "P";
		case KeyEvent.VK_Q: return "Q";
		case KeyEvent.VK_R: return "R";
		case KeyEvent.VK_S: return "S";
		case KeyEvent.VK_T: return "T";
		case KeyEvent.VK_U: return "U";
		case KeyEvent.VK_V: return "V";
		case KeyEvent.VK_W: return "W";
		case KeyEvent.VK_X: return "X";
		case KeyEvent.VK_Y: return "Y";
		case KeyEvent.VK_Z: return "Z";
		case KeyEvent.VK_UP: return "UP";
		case KeyEvent.VK_DOWN: return "DOWN";
		case KeyEvent.VK_LEFT: return "LEFT";
		case KeyEvent.VK_RIGHT: return "RIGHT";
		case KeyEvent.VK_SPACE: return "SPACE";
		}

		return "";
	}

	/**
	 * Gets the key corresponding to the letter specified.
	 * 
	 * @param str
	 *            letter to get key from
	 * @return the matching key
	 */
	public static int getKeyFromString(String str) {
		str = str.toUpperCase();

		switch (str) {
		case "A": return KeyEvent.VK_A;
		case "B": return KeyEvent.VK_B;
		case "C": return KeyEvent.VK_C;
		case "D": return KeyEvent.VK_D;
		case "E": return KeyEvent.VK_E;
		case "F": return KeyEvent.VK_F;
		case "G": return KeyEvent.VK_G;
		case "H": return KeyEvent.VK_H;
		case "I": return KeyEvent.VK_I;
		case "J": return KeyEvent.VK_J;
		case "K": return KeyEvent.VK_K;
		case "L": return KeyEvent.VK_L;
		case "M": return KeyEvent.VK_M;
		case "N": return KeyEvent.VK_N;
		case "O": return KeyEvent.VK_O;
		case "P": return KeyEvent.VK_P;
		case "Q": return KeyEvent.VK_Q;
		case "R": return KeyEvent.VK_R;
		case "S": return KeyEvent.VK_S;
		case "T": return KeyEvent.VK_T;
		case "U": return KeyEvent.VK_U;
		case "V": return KeyEvent.VK_V;
		case "W": return KeyEvent.VK_W;
		case "X": return KeyEvent.VK_X;
		case "Y": return KeyEvent.VK_Y;
		case "Z": return KeyEvent.VK_Z;
		case "UP":return KeyEvent.VK_UP;
		case "DOWN":return KeyEvent.VK_DOWN;
		case "LEFT":return KeyEvent.VK_LEFT;
		case "RIGHT":return KeyEvent.VK_RIGHT;
		case "SPACE":return KeyEvent.VK_SPACE;
		}

		return 0;
	}

}
