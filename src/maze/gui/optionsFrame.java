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

import java.awt.event.ActionEvent;

import javax.swing.JSlider;
import javax.swing.JSpinner;


import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import java.awt.Font;
import javax.swing.JComboBox;



public class optionsFrame{

	private static final long serialVersionUID = 1L;
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
	JSlider DiffSlider;
	JSpinner MazeSizeSpinner;
	JSpinner DragonSpinner;
	JComboBox<String> MazeType;
	JComboBox<String> upKeyBox;
	JComboBox<String> downKeyBox;
	JComboBox<String> leftKeyBox;
	JComboBox<String> rightKeyBox;
	JComboBox<String> sendEagleKeyBox;

	private JPanel Config;
	boolean mRandom = false;


	public optionsFrame(final GFrame gF)
	{
		this.gF = gF;
		Options.setTitle("Options");
		Options.setDefaultCloseOperation(Options.DISPOSE_ON_CLOSE);
		Options.setResizable( false );
		Return = new JPanel();
		Config = new JPanel();
		btnSave = new JButton("Save");
		btnQuit = new JButton("Quit");

		DiffSlider = new JSlider();
		MazeSizeSpinner = new JSpinner(new SpinnerNumberModel(new Integer(7) , new Integer(7),(Comparable)null , new Integer(2)));
		DragonSpinner = new JSpinner(new SpinnerNumberModel(new Integer(1) , new Integer(1),(Comparable)null , new Integer(1)));

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

	public void setButtons()
	{
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

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
		});


		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Options.dispose();
				Options.setVisible(false); //you can't see me!
			}
		});

	}

	public void configGameKeys(boolean inGameChange)
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

	public void setLabels()
	{
		DragonLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DragonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		DragonLabel.setBounds(21, 46, 112, 22);

		DiffLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DiffLabel.setBounds(22, 105, 69, 30);

		MazeSizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MazeSizeLabel.setBounds(21, 189, 99, 22);

		MazeTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MazeTypeLabel.setBounds(21, 237, 112, 22);



		KeysLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		KeysLabel.setBounds(21, 285, 99, 14);

		lbSendEagleKey.setBounds(122, 418, 57, 14);
		lbRightKey.setBounds(122, 368, 46, 14);
		lbUpKey.setBounds(122, 322, 46, 14);
		lbDownKey.setBounds(123, 345, 57, 14);
		lbLeftKey.setBounds(122, 394, 46, 14);


		diffDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		diffDescriptionLabel.setBounds(101, 139, 244, 14);






	}

	public void setComponents()
	{

		DiffSlider.setBorder(new CompoundBorder());
		DiffSlider.setBounds(122, 105, 177, 30);

		DiffSlider.setToolTipText("");
		DiffSlider.setPaintTicks(true);
		DiffSlider.setMajorTickSpacing(1);
		DiffSlider.setMinimum(1);
		DiffSlider.setMaximum(3);

		MazeSizeSpinner.setBounds(174, 186, 125, 30);
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
		sendEagleKeyBox.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "UP", "RIGHT", "LEFT", "DOWN", "SPACE"}));
		sendEagleKeyBox.setSelectedIndex(30);


	}

	public void addComponents()
	{
		Return.add(btnSave);
		Return.add(btnQuit);

		Config.add(DragonLabel);
		Config.add(DiffLabel);
		Config.add(MazeSizeLabel);
		Config.add(MazeType);
		Config.add(MazeTypeLabel);
		Config.add(DragonSpinner);
		Config.add(MazeSizeSpinner);
		Config.add(DiffSlider);
		Config.add(upKeyBox);
		Config.add(downKeyBox);
		Config.add(leftKeyBox);
		Config.add(rightKeyBox);
		Config.add(sendEagleKeyBox);
		Config.add(diffDescriptionLabel);
		Config.add(KeysLabel);
		Config.add(lbSendEagleKey);
		Config.add(lbRightKey);
		Config.add(lbUpKey);
		Config.add(lbDownKey);
		Config.add(lbLeftKey);



	}



	public void show() {
		Options.setSize(361,520);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Options.setLocation(dim.width / 2 - Options.getSize().width / 2, dim.height / 2
				- Options.getSize().height / 2);

		Options.setVisible(true);
	}
}
