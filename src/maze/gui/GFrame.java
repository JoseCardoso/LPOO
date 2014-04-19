package maze.gui;

import maze.game.MazeGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.filechooser.FileNameExtensionFilter;

public class GFrame {
	JFrame frmFairyTailSclass = new JFrame();
	GPanel gamePanel;
	optionsFrame Op;
	private JPanel buttonsPanel;
	private JPanel buttonsPanel2;
	private JPanel buttonsPanel3;
	private JButton btnNewGame;
	private JButton btnExit;
	private JButton btnOptions;
	private JButton btnLoad;
	private JButton btnSave;
	private JButton btnOptionsInGame;
	private JButton btnCreateMaze;
	int nD = 1, nM = 7 , nDf = 1;
	boolean mRandom = false;
	boolean inGameChange =false;
	int upKey = KeyEvent.VK_UP;
	int leftKey = KeyEvent.VK_LEFT;
	int rightKey = KeyEvent.VK_RIGHT;
	int downKey = KeyEvent.VK_DOWN;
	int sendEagleKey = KeyEvent.VK_SPACE;
	int savedUpKey;
	int savedLeftKey;
	int savedRightKey;
	int savedDownKey;
	int savedSendEagleKey;
	/**
	 * Create the frame.
	 */
	public GFrame(int nD , int nDf, int nM, boolean mRandom ,int upKey,int rightKey,int leftKey,int downKey,int sendEagleKey) throws IOException {
		
		this.nD = nD;
		this.nM = nM;
		this.nDf = nDf;
		this.mRandom = mRandom;
		this.downKey = downKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.upKey = upKey;
		this.sendEagleKey = sendEagleKey;
		
		savedUpKey = upKey;
		savedDownKey = downKey;
		savedLeftKey = leftKey;
		savedRightKey = rightKey;
		savedSendEagleKey = sendEagleKey;
		
		frmFairyTailSclass.setTitle("Fairy Tail S-Class Quest");
		frmFairyTailSclass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		gamePanel = new GPanel(this);
		Op = new optionsFrame(this);
		
		buttonsPanel = new JPanel();
		buttonsPanel2 = new JPanel();
		buttonsPanel3 = new JPanel();

		
		btnNewGame = new JButton("New Game");
		btnOptions = new JButton("Options");
		btnCreateMaze = new JButton("Create Maze");
		btnExit = new JButton("Exit");
		btnLoad = new JButton("Load Game");
		btnSave = new JButton("Save");
		btnOptionsInGame = new JButton("Options");
		
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
					frmFairyTailSclass.getContentPane().add(buttonsPanel3, BorderLayout.SOUTH);
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
				inGameChange=false;
				Op.show();
				gamePanel.requestFocusInWindow();

			}
		});
		
		btnOptionsInGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inGameChange=true;
				Op.show();
				gamePanel.requestFocusInWindow();
			}
		});



		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();  
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  

				fc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("dat","dat");
				fc.setFileFilter(filter);  

				int returnVal = fc.showSaveDialog(new JFrame("Save"));

				if (returnVal == JFileChooser.APPROVE_OPTION) {  

					try{  


						File outFile = fc.getSelectedFile();  
						String tFileName = outFile.getName();	

						if(!tFileName.endsWith(".dat"))
							outFile= new File(outFile + ".dat");

						FileOutputStream saveFile = new FileOutputStream(outFile);
						ObjectOutputStream fw = new ObjectOutputStream(saveFile);

						fw.writeObject(gamePanel.game);
						fw.close();
					}  
					catch(IOException ex){
						JOptionPane.showMessageDialog(new JFrame().getRootPane(), "Error Saving!");
					}  
				}   
				gamePanel.requestFocusInWindow();
			}  



		});

		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				JFileChooser fc = new JFileChooser();  
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  

				fc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("dat","dat");
				fc.setFileFilter(filter);  

				int returnVal = fc.showOpenDialog(new JFrame("Load"));


				if (returnVal == JFileChooser.APPROVE_OPTION) {  

					try{  


						File inFile = fc.getSelectedFile();  
						FileInputStream loadFile = new FileInputStream(inFile);
						ObjectInputStream reader = new ObjectInputStream(loadFile);
						Object obj = reader.readObject();
						
						reader.close();
						if(obj instanceof MazeGame){
							frmFairyTailSclass.setSize(642, 598);
						
					

							Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
							frmFairyTailSclass.setLocation(dim.width / 2 - frmFairyTailSclass.getSize().width / 2, dim.height
									/ 2 - frmFairyTailSclass.getSize().height / 2);

							// starting new game with new options

							buttonsPanel.setVisible(false);
							buttonsPanel2.setVisible(false);
							buttonsPanel3.setVisible(true);

							frmFairyTailSclass.getContentPane().add(gamePanel, BorderLayout.CENTER);
							frmFairyTailSclass.getContentPane().add(buttonsPanel3, BorderLayout.SOUTH);
						
							((MazeGame) obj).createRandomWrapper();
							gamePanel.startGame((MazeGame) obj);
							
							
						  }
						

					}  
					catch(Exception ex){
						JOptionPane.showMessageDialog(new JFrame().getRootPane(), "Error Loading!");
					}  
				}   

			}
		});
		
		
		btnCreateMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				LabirintoPersonalizado lP = new LabirintoPersonalizado();
				lP.show();
			}
		});
	}

	
	/**
	 * Adds buttons to game windows layout.
	 */

	
	private void addButtons() {


		buttonsPanel.setLayout(new GridLayout(1, 3));
		buttonsPanel.add(btnNewGame);
		buttonsPanel.add(btnLoad);
		buttonsPanel.add(btnCreateMaze);

		buttonsPanel2.setLayout(new GridLayout(1, 0, 0, 0));
		buttonsPanel2.add(btnOptions);
		buttonsPanel2.add(btnExit);

		buttonsPanel3.add(btnSave);
		buttonsPanel3.add(btnOptionsInGame);
		buttonsPanel3.setLayout(new GridLayout(1, 0, 0, 0));
		
		frmFairyTailSclass.getContentPane().add(buttonsPanel, BorderLayout.NORTH);
		
		
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
