package maze.gui;

import maze.gui.GFrame;
import maze.gui.Miscellaneous;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;

import javax.swing.JSlider;
import javax.swing.JSpinner;


import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;

import java.awt.Font;

import javax.swing.JComboBox;



// TODO: Auto-generated Javadoc
/**
 * The Class optionsFrame.
 * 
 * this class is responsible for the options customization interface
 *
 */
public class optionsFrame{

	private GFrame gF;
	private JFrame Options = new JFrame();
	private JPanel Return;
	private JButton btnSave;
	private JButton btnQuit;
	private JLabel DragonLabel;
	private JLabel DiffLabel;
	private JLabel MazeSizeLabel;
	private JLabel MazeTypeLabel;
	private JLabel diffDescriptionLabel;
	private JLabel lbSendEagleKey;
	private JLabel lbDownKey;
	private JLabel lbLeftKey;
	private JLabel lbRightKey;
	private JLabel lbUpKey;
	private JLabel KeysLabel;
	private boolean custom = false;
	
	/** The Difficulty slider. */
	JSlider DiffSlider;
	
	/** The Mazesize spinner. */
	JSpinner MazeSizeSpinner;
	
	/** The Number Dragon spinner. */
	JSpinner DragonSpinner;
	
	/** The Maze type. */
	JComboBox<String> MazeType;
	
	/** The up key box. */
	JComboBox<String> upKeyBox;
	
	/** The down key box. */
	JComboBox<String> downKeyBox;
	
	/** The left key box. */
	JComboBox<String> leftKeyBox;
	
	/** The right key box. */
	JComboBox<String> rightKeyBox;
	
	/** The send eagle key box. */
	JComboBox<String> sendEagleKeyBox;
	private JPanel Config;
	
	/** The maze random flag. */
	boolean mRandom = false;
	private CustomMaze  cM;


	/**
	 * Instantiates a new options frame with a custom maze
	 *
	 * @param cM the custom maze
	 */
	public optionsFrame(CustomMaze  cM)
	{
		this.cM = cM;
		Options.setTitle("Options");
		Options.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Options.setResizable( false );
		Return = new JPanel();
		Config = new JPanel();
		btnSave = new JButton("Save");
		btnQuit = new JButton("Quit");

		custom = true;
		DiffSlider = new JSlider();
		MazeSizeSpinner = new JSpinner(new SpinnerNumberModel(cM.getSize() , new Integer(7),(Comparable<?>)null , new Integer(1)));
		DragonSpinner = new JSpinner(new SpinnerNumberModel(new Integer(1) , new Integer(1),(Comparable<?>)null , new Integer(1)));


		DiffLabel = new JLabel("Difficulty");
		MazeSizeLabel = new JLabel("Maze Size (NxN):");


		diffDescriptionLabel = new JLabel("Stopped          Moving      Moving/Sleeping");


		Options.getContentPane().add(Return,BorderLayout.SOUTH);
		Return.setLayout(new GridLayout(1,0,0,0));


		Options.getContentPane().add(Config, BorderLayout.CENTER);
		Config.setLayout(null);


		addComponents();
		setComponents();
		setButtons();
		setLabels();

	}


	/**
	 * Instantiates a new options frame.
	 *
	 * @param gF the game frame
	 */
	public optionsFrame(final GFrame gF)
	{
		this.gF = gF;
		Options.setTitle("Options");
		Options.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Options.setResizable( false );
		Return = new JPanel();
		Config = new JPanel();
		btnSave = new JButton("Save");
		btnQuit = new JButton("Quit");

		DiffSlider = new JSlider();
		MazeSizeSpinner = new JSpinner(new SpinnerNumberModel(new Integer(7) , new Integer(7),(Comparable<?>)null , new Integer(2)));
		DragonSpinner = new JSpinner(new SpinnerNumberModel(new Integer(1) , new Integer(1),(Comparable<?>)null , new Integer(1)));

		DragonLabel = new JLabel("Number of Dragons:");
		DiffLabel = new JLabel("Difficulty");
		MazeSizeLabel = new JLabel("Maze Size (NxN):");
		MazeTypeLabel = new JLabel("Random Maze:");		
		KeysLabel = new JLabel("Gameplay Keys:");
		lbSendEagleKey = new JLabel("Send Eagle");
		lbRightKey = new JLabel("Right Key");
		lbUpKey = new JLabel("Up Key");
		lbDownKey = new JLabel("Down Key");
		lbLeftKey = new JLabel("Left Key");
		diffDescriptionLabel = new JLabel("Stopped          Moving      Moving/Sleeping");


		MazeType = new JComboBox<String>();
		upKeyBox = new JComboBox<String>();
		upKeyBox.setSize(79, 20);
		upKeyBox.setLocation(190, 321);
		upKeyBox.setMaximumRowCount(30);
		leftKeyBox = new JComboBox<String>();
		leftKeyBox.setMaximumRowCount(30);
		leftKeyBox.setLocation(190, 393);
		leftKeyBox.setSize(79, 20);
		rightKeyBox = new JComboBox<String>();
		rightKeyBox.setMaximumRowCount(31);
		rightKeyBox.setLocation(190, 367);
		rightKeyBox.setSize(79, 20);
		downKeyBox = new JComboBox<String>();
		downKeyBox.setMaximumRowCount(30);
		downKeyBox.setLocation(190, 344);
		downKeyBox.setSize(79, 20);
		sendEagleKeyBox = new JComboBox<String>();
		sendEagleKeyBox.setMaximumRowCount(30);
		sendEagleKeyBox.setLocation(190, 417);
		sendEagleKeyBox.setSize(79, 20);


		Options.getContentPane().add(Return,BorderLayout.SOUTH);
		Return.setLayout(new GridLayout(1,0,0,0));


		Options.getContentPane().add(Config, BorderLayout.CENTER);
		Config.setLayout(null);

		addComponents();
		setComponents();
		setButtons();
		setLabels();

	}

	private void setButtons()
	{
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!custom)
				{
					gF.nD=(Integer)DragonSpinner.getValue();
					gF.nM= (Integer)MazeSizeSpinner.getValue();
					gF.nDf= DiffSlider.getValue();
					if (MazeType.getSelectedItem() == "Yes")
						gF.mRandom = true;
					else
						gF.mRandom = false;

					configGameKeys(gF.inGameChange);

					Options.setVisible(false); //you can't see me!

					gF.gamePanel.requestFocusInWindow();
				}
				else
				{
					cM.setDiff(DiffSlider.getValue());
					cM.setSize((Integer)MazeSizeSpinner.getValue());
				}
			}
		});


		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Options.dispose();
				Options.setVisible(false); //you can't see me!
				if(custom)
					cM.updateMaze();
			}
		});

	}

	
	private void configGameKeys(boolean inGameChange)
	{
		gF.upKey = Miscellaneous.getKeyFromString((String) upKeyBox.getSelectedItem());
		gF.downKey = Miscellaneous.getKeyFromString((String) downKeyBox.getSelectedItem());
		gF.leftKey = Miscellaneous.getKeyFromString((String) leftKeyBox.getSelectedItem());
		gF.rightKey = Miscellaneous.getKeyFromString((String) rightKeyBox.getSelectedItem());
		gF.sendEagleKey = Miscellaneous.getKeyFromString((String) sendEagleKeyBox.getSelectedItem());

		if(!inGameChange)
		{
			gF.savedUpKey = Miscellaneous.getKeyFromString((String) upKeyBox.getSelectedItem());
			gF.savedDownKey = Miscellaneous.getKeyFromString((String) downKeyBox.getSelectedItem());
			gF.savedLeftKey = Miscellaneous.getKeyFromString((String) leftKeyBox.getSelectedItem());
			gF.savedRightKey = Miscellaneous.getKeyFromString((String) rightKeyBox.getSelectedItem());
			gF.savedSendEagleKey = Miscellaneous.getKeyFromString((String) sendEagleKeyBox.getSelectedItem());

		}

	}

	private void setLabels()
	{

		DiffLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DiffLabel.setBounds(22, 105, 69, 30);

		MazeSizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MazeSizeLabel.setBounds(21, 189, 99, 22);


		diffDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		diffDescriptionLabel.setBounds(101, 139, 244, 14);
		if(!custom)
		{

			DragonLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			DragonLabel.setHorizontalAlignment(SwingConstants.CENTER);
			DragonLabel.setBounds(21, 46, 112, 22);

			MazeTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			MazeTypeLabel.setBounds(21, 237, 112, 22);



			KeysLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			KeysLabel.setBounds(21, 285, 99, 14);

			lbSendEagleKey.setBounds(122, 418, 57, 14);
			lbRightKey.setBounds(122, 368, 46, 14);
			lbUpKey.setBounds(122, 322, 46, 14);
			lbDownKey.setBounds(123, 345, 57, 14);
			lbLeftKey.setBounds(122, 394, 46, 14);



		}




	}

	private void setComponents()
	{

		DiffSlider.setBorder(new CompoundBorder());
		DiffSlider.setBounds(122, 105, 177, 30);

		DiffSlider.setToolTipText("");
		DiffSlider.setPaintTicks(true);
		DiffSlider.setMajorTickSpacing(1);
		DiffSlider.setMinimum(1);
		DiffSlider.setMaximum(3);
		DiffSlider.setValue(1);

		MazeSizeSpinner.setBounds(174, 186, 125, 30);

		if(!custom)
		{
			((DefaultEditor) MazeSizeSpinner.getEditor()).getTextField().setEditable(false);
			DragonSpinner.setBounds(174, 43, 125, 30);

			MazeType.setFont(new Font("Tahoma", Font.PLAIN, 12));
			MazeType.setMaximumRowCount(2);
			MazeType.setBounds(253, 239, 46, 20);
			MazeType.setModel(new DefaultComboBoxModel<String>(new String[] {"Yes", "No"}));
			upKeyBox.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "UP", "RIGHT", "LEFT", "DOWN", "SPACE"}));
			upKeyBox.setSelectedIndex(26);
			downKeyBox.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "UP", "RIGHT", "LEFT", "DOWN", "SPACE"}));
			downKeyBox.setSelectedIndex(29);
			leftKeyBox.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "UP", "RIGHT", "LEFT", "DOWN", "SPACE"}));
			leftKeyBox.setSelectedIndex(28);
			rightKeyBox.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "UP", "RIGHT", "LEFT", "DOWN", "SPACE"}));
			rightKeyBox.setSelectedIndex(27);
			sendEagleKeyBox.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "UP", "RIGHT", "LEFT", "DOWN", "SPACE"}));
			sendEagleKeyBox.setSelectedIndex(30);
		}

	}

	private void addComponents()
	{
		Return.add(btnSave);
		Return.add(btnQuit);

		Config.add(DiffLabel);
		Config.add(MazeSizeLabel);
		Config.add(MazeSizeSpinner);
		Config.add(DiffSlider);
		Config.add(diffDescriptionLabel);
		
		if(!custom)
		{
			Config.add(DragonLabel);
			Config.add(MazeType);
			Config.add(MazeTypeLabel);
			Config.add(DragonSpinner);
			Config.add(upKeyBox);
			Config.add(downKeyBox);
			Config.add(leftKeyBox);
			Config.add(rightKeyBox);
			Config.add(sendEagleKeyBox);
			Config.add(KeysLabel);
			Config.add(lbSendEagleKey);
			Config.add(lbRightKey);
			Config.add(lbUpKey);
			Config.add(lbDownKey);
			Config.add(lbLeftKey);
		}


	}




	/**
	 * Shows the options frame
	 */
	public void show() {
		if(!custom)
			Options.setSize(361,520);
		else
			Options.setSize(361,361);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Options.setLocation(dim.width / 2 - Options.getSize().width / 2, dim.height / 2
				- Options.getSize().height / 2);

		Options.setVisible(true);
	}
}
