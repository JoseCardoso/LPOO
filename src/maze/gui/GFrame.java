package maze.gui;

import maze.game.MazeGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GFrame {
	private static final long serialVersionUID = 1L;
	JFrame frmFairyTailSclass = new JFrame();
	private JButton btnNewGame;
	private JButton btnExit;
	private JPanel buttonsPanel;
	GPanel gamePanel;
	optionsFrame Op;
	private JPanel buttonsPanel2;
	private JButton btnOptions;
	private JButton btnLoad;
	int nD = 1, nM = 7 , nDf = 1;
	private JPanel buttonsPanel3;
	private JButton btnSave;

	/**
	 * Create the frame.
	 */
	public GFrame() throws IOException {
		frmFairyTailSclass.setTitle("Fairy Tail S-Class Quest");
		frmFairyTailSclass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonsPanel = new JPanel();
		buttonsPanel2 = new JPanel();
		buttonsPanel3 = new JPanel();

		btnLoad = new JButton("Load Game");

		gamePanel = new GPanel(this);
		btnNewGame = new JButton("New Game");
		btnExit = new JButton("Exit");

		btnOptions = new JButton("Options");
		Op = new optionsFrame(this);



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
					buttonsPanel2.setVisible(false);
					buttonsPanel3.setVisible(true);

					frmFairyTailSclass.getContentPane().add(gamePanel, BorderLayout.CENTER);
					frmFairyTailSclass.getContentPane().add(buttonsPanel3, BorderLayout.NORTH);
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


		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();  
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
				FileNameExtensionFilter filter = new FileNameExtensionFilter("dat","txt");
				fc.setFileFilter(filter);  
				
				int returnVal = fc.showSaveDialog(null);
				
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {  

					try{  
						boolean isFile = false;  
						
						File outFile = fc.getSelectedFile();  
						ObjectOutputStream fw;
						if(!outFile.getName().endsWith(filter.toString()))
						{	
							fw = new ObjectOutputStream(new FileOutputStream(outFile.getName() + filter.toString()));
							
						}
						else
							fw = new ObjectOutputStream(new FileOutputStream(outFile.getName()));
						fw.writeObject(gamePanel.game.getHero());
						fw.writeObject(gamePanel.game.getAguia());
						fw.writeObject(gamePanel.game.getEspada());
						fw.writeObject(gamePanel.game.getDragonList());
						fw.writeObject(gamePanel.game.getSaida());
						fw.writeObject(gamePanel.game.getLab().getEmptyMaze());
						
					}  
					catch(IOException ex){
						ex.printStackTrace();
					}  
				}   

			}  



		});

		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


			}
		});
}

/**
 * Adds buttons to game windows layout.
 */

private void addButtons() {




	btnSave = new JButton("Save");
	buttonsPanel.setLayout(new GridLayout(1, 3));
	buttonsPanel.add(btnNewGame);
	buttonsPanel.add(btnExit);



	buttonsPanel2.setLayout(new GridLayout(1, 0, 0, 0));
	buttonsPanel2.add(btnOptions);
	frmFairyTailSclass.getContentPane().add(buttonsPanel2, BorderLayout.SOUTH);


	buttonsPanel2.add(btnLoad);

	buttonsPanel3.add(btnSave);
	buttonsPanel3.setLayout(new GridLayout(1, 0, 0, 0));
	frmFairyTailSclass.getContentPane().add(buttonsPanel, BorderLayout.NORTH);
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
