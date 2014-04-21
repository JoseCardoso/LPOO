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



import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.filechooser.FileNameExtensionFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class GFrame.
 */
public class GFrame {
	
	/** The frm Dbz Battle. */
	JFrame frmDbzBattle = new JFrame();
	
	/** The game panel. */
	GPanel gamePanel;
	
	/** The Op Frame. */
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
	private JButton btnLoadCustom;
	private JLabel background;
	
	/** The number of dragons. */
	int nD = 1;
	

	/** The maze size. */
	int nM = 7;
	

	/** The difficulty. */
	int nDf = 1;
	
	/** The m random. */
	boolean mRandom = false;
	
	/** The flag to see if changes are made ingame. */
	boolean inGameChange =false;
	
	/** The up key. */
	int upKey = KeyEvent.VK_UP;
	
	/** The left key. */
	int leftKey = KeyEvent.VK_LEFT;
	
	/** The right key. */
	int rightKey = KeyEvent.VK_RIGHT;
	
	/** The down key. */
	int downKey = KeyEvent.VK_DOWN;
	
	/** The send eagle key. */
	int sendEagleKey = KeyEvent.VK_SPACE;
	
	/** The saved up key. */
	int savedUpKey;
	
	/** The saved left key. */
	int savedLeftKey;
	
	/** The saved right key. */
	int savedRightKey;
	
	/** The saved down key. */
	int savedDownKey;
	
	/** The saved send eagle key. */
	int savedSendEagleKey;
	
	/**
	 * Creates the base frame for all GUI.
	 * 
	 * 
	 *
	 * @param nD the number of dragons
	 * @param nDf the difficulty
	 * @param nM the maze size
	 * @param mRandom the m random
	 * @param upKey the up key
	 * @param rightKey the right key
	 * @param leftKey the left key
	 * @param downKey the down key
	 * @param sendEagleKey the send eagle key
	 * @throws IOException Signals that an I/O exception has occurred.
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
		
		frmDbzBattle.setTitle("Dragon Ball Z Epic Battle");
		frmDbzBattle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		background=new JLabel(new ImageIcon("res/background.png"));
		frmDbzBattle.getContentPane().add(background, BorderLayout.CENTER);
		background.setLayout(null);
		

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
		btnLoadCustom = new JButton("Load Custom Maze");
		
		addButtons();
		setUpButtons(); 

	}		  

	private void setUpButtons() {


		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "Do you want to start a new game?";
				int res = JOptionPane.showConfirmDialog(null, msg);

				if (res == JOptionPane.YES_OPTION) {
					frmDbzBattle.setSize(642, 598);

					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frmDbzBattle.setLocation(dim.width / 2 - frmDbzBattle.getSize().width / 2, dim.height
							/ 2 - frmDbzBattle.getSize().height / 2);

					// starting new game with new options


					buttonsPanel.setVisible(false);
					buttonsPanel2.setVisible(false);
					buttonsPanel3.setVisible(true);
					background.setVisible(false);

					frmDbzBattle.getContentPane().add(gamePanel, BorderLayout.CENTER);
					frmDbzBattle.getContentPane().add(buttonsPanel3, BorderLayout.SOUTH);
				
					gamePanel.startGame();
				}
			}
		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "Do you want to quit?";
				int res = JOptionPane.showConfirmDialog(null, msg);

				if (res == JOptionPane.YES_OPTION) {
					frmDbzBattle.dispose();
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
							frmDbzBattle.setSize(642, 598);
						
					

							Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
							frmDbzBattle.setLocation(dim.width / 2 - frmDbzBattle.getSize().width / 2, dim.height
									/ 2 - frmDbzBattle.getSize().height / 2);

							// starting new game with new options

							buttonsPanel.setVisible(false);
							buttonsPanel2.setVisible(false);
							buttonsPanel3.setVisible(true);
							background.setVisible(false);

							frmDbzBattle.getContentPane().add(gamePanel, BorderLayout.CENTER);
							frmDbzBattle.getContentPane().add(buttonsPanel3, BorderLayout.SOUTH);
						
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
		
		
		btnLoadCustom.addActionListener(new ActionListener() {
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
						if(obj instanceof Object[]){
							frmDbzBattle.setSize(642, 598);
						

							Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
							frmDbzBattle.setLocation(dim.width / 2 - frmDbzBattle.getSize().width / 2, dim.height
									/ 2 - frmDbzBattle.getSize().height / 2);

							// starting new game with new options

							buttonsPanel.setVisible(false);
							buttonsPanel2.setVisible(false);
							buttonsPanel3.setVisible(true);
							background.setVisible(false);

							frmDbzBattle.getContentPane().add(gamePanel, BorderLayout.CENTER);
							frmDbzBattle.getContentPane().add(buttonsPanel3, BorderLayout.SOUTH);
						
							Object[] obj2 = (Object[]) obj;
							int diff = (Integer) obj2[0];
							char[][] m = (char[][]) obj2[1];
							gamePanel.startGame(m,diff);
							
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
				
				CustomMaze lP = new CustomMaze();
				lP.show();
			}
		});
	}


	private void addButtons() {


		buttonsPanel.setLayout(new GridLayout(0, 4));
		buttonsPanel.add(btnNewGame);
		buttonsPanel.add(btnLoad);
		buttonsPanel.add(btnCreateMaze);

		buttonsPanel2.setLayout(new GridLayout(1, 0, 0, 0));
		buttonsPanel2.add(btnOptions);
		buttonsPanel2.add(btnExit);

		buttonsPanel3.add(btnSave);
		buttonsPanel3.add(btnOptionsInGame);
		buttonsPanel3.setLayout(new GridLayout(1, 0, 0, 0));
		
		frmDbzBattle.getContentPane().add(buttonsPanel, BorderLayout.NORTH);
		
		btnLoadCustom = new JButton("Load Custom Maze");
		buttonsPanel.add(btnLoadCustom);
		
		
		frmDbzBattle.getContentPane().add(buttonsPanel2, BorderLayout.SOUTH);
				
		
	}
	
	

	/**
	 * Starts the frame
	 * 
	 */
	public void start() {


		frmDbzBattle.setSize(new Dimension(1023, 680));
		frmDbzBattle.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmDbzBattle.setLocation(
				dim.width / 2 - frmDbzBattle.getSize().width / 2,
				dim.height / 2 - frmDbzBattle.getSize().height / 2);

		frmDbzBattle.setVisible(true);
	}

}
