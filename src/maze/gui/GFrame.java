package maze.gui;

import maze.game.MazeGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Toolkit;

public class GFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frmFairyTailSclass = new JFrame();
	private JButton btnNewGame;
	private JPanel buttonsPanel;
	private JPanel buttonsPanel2;
	private JPanel config;
	private JButton buttonsMazeSize;
	private JButton buttonsDragonNumber;
	private JButton buttonsDiff;
	private JButton btnQuitGame;
	private JButton options;
	private GPanel gamePanel;
	private optionsFrame Op;

	/**
	 * Create the frame.
	 */
	public GFrame() throws IOException {
		frmFairyTailSclass.setTitle("Fairy Tail S-Class Quest");
		frmFairyTailSclass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frmFairyTailSclass.setSize(new Dimension(500, 500));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmFairyTailSclass.setLocation(
				dim.width / 2 - frmFairyTailSclass.getSize().width / 2,
				dim.height / 2 - frmFairyTailSclass.getSize().height / 2);

		gamePanel = new GPanel();
		frmFairyTailSclass.getContentPane().add(gamePanel, BorderLayout.CENTER);

		/*
		 * GPanel = new GPanel(this); buttonsPanel = new JPanel(); buttonsPanel2
		 * = new JPanel(); options = new JButton(); buttonsMazeSize = new
		 * JButton(); buttonsDragonNumber = new JButton(); buttonsDiff= new
		 * JButton(); Op = new optionsFrame();
		 * 
		 * setUpButtons(); getContentPane().setLayout(new BorderLayout(0, 0));
		 * addButtons(); //getContentPane().add(GPanel); }
		 * 
		 * 
		 * private void setUpButtons() {
		 * 
		 * 
		 * btnNewGame = new JButton("New Game");
		 * btnNewGame.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { String msg =
		 * "Do you want to start a new game?"; int res =
		 * JOptionPane.showConfirmDialog(rootPane, msg);
		 * 
		 * if (res == JOptionPane.YES_OPTION) { setSize(642, 598);
		 * 
		 * Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 * setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 -
		 * getSize().height / 2);
		 * 
		 * // starting new game with new options } } });
		 * 
		 * 
		 * // Quit Game button btnQuitGame = new JButton("Quit");
		 * btnQuitGame.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { String msg =
		 * "Are you sure you want to quit?"; int res =
		 * JOptionPane.showConfirmDialog(rootPane, msg);
		 * 
		 * if (res == JOptionPane.YES_OPTION) System.exit(0); } });
		 * 
		 * options = new JButton("Options"); options.addMouseListener(new
		 * MouseAdapter() {
		 * 
		 * @Override public void mouseClicked(MouseEvent arg0) { Op.show(); }
		 * }); options.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { options.setVisible(true); } });
		 */
	}

	/**
	 * Adds buttons to game windows layout.
	 */
	private void addButtons() {/*
								 * buttonsPanel.setLayout(new GridLayout(1, 3));
								 * buttonsPanel.add(btnNewGame);
								 * 
								 * buttonsPanel2.setLayout(new GridLayout(1,
								 * 2)); buttonsPanel2.add(options);
								 * buttonsPanel2.add(btnQuitGame);
								 * 
								 * 
								 * 
								 * getContentPane().add(buttonsPanel,
								 * BorderLayout.NORTH); // Play Game button
								 * getContentPane().add(buttonsPanel2,
								 * BorderLayout.SOUTH);
								 */
	}

	public void start() {
		frmFairyTailSclass.setVisible(true);
	}

}
