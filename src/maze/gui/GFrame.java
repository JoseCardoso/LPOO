package maze.gui;

import maze.game.MazeGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class GFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frmFairyTailSclass = new JFrame();
	private JButton btnNewGame;
	private JButton btnExit;
	private JButton options;
	private JPanel buttonsPanel;
	private GPanel gamePanel;
	private optionsFrame Op;
	private JPanel buttonsPanel2;
	private JButton btnOptions;

	/**
	 * Create the frame.
	 */
	public GFrame() throws IOException {
		frmFairyTailSclass.setTitle("Fairy Tail S-Class Quest");
		frmFairyTailSclass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonsPanel = new JPanel();
		buttonsPanel2 = new JPanel();
		
		gamePanel = new GPanel();
		btnNewGame = new JButton("New Game");
		btnExit = new JButton("Exit");
		
		btnOptions = new JButton("Options");
		Op = new optionsFrame();

		addButtons();
		setUpButtons(); 

	}		  

	private void setUpButtons() {


		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "Do you want to start a new game?";
				int res = JOptionPane.showConfirmDialog(null, msg);

				if (res == JOptionPane.YES_OPTION) {
					frmFairyTailSclass.setSize(642, 598);

					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frmFairyTailSclass.setLocation(dim.width / 2 - frmFairyTailSclass.getSize().width / 2, dim.height
							/ 2 - frmFairyTailSclass.getSize().height / 2);

					// starting new game with new options
					buttonsPanel.setVisible(false);
					frmFairyTailSclass.getContentPane().add(gamePanel, BorderLayout.CENTER);
					gamePanel.startGame();
				}
			}
		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "Do you want to quit?";
				int res = JOptionPane.showConfirmDialog(null, msg);

				if (res == JOptionPane.YES_OPTION) {
					frmFairyTailSclass.dispose();
				}
			}
		});
		

		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Op.show();
				
			}
		});


	}

	/**
	 * Adds buttons to game windows layout.
	 */

	private void addButtons() {
		buttonsPanel.setLayout(new GridLayout(1, 3));
		buttonsPanel.add(btnNewGame);
		buttonsPanel.add(btnExit);
		frmFairyTailSclass.getContentPane().add(buttonsPanel, BorderLayout.NORTH);
		

		buttonsPanel2.setLayout(new GridLayout(0, 1, 0, 0));
		buttonsPanel2.add(btnOptions);
		frmFairyTailSclass.getContentPane().add(buttonsPanel2, BorderLayout.SOUTH);
		



	}

	public void start() {


		frmFairyTailSclass.setSize(new Dimension(500, 500));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmFairyTailSclass.setLocation(
				dim.width / 2 - frmFairyTailSclass.getSize().width / 2,
				dim.height / 2 - frmFairyTailSclass.getSize().height / 2);

		frmFairyTailSclass.setVisible(true);
	}

}
