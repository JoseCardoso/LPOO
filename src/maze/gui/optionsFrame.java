package maze.gui;

import maze.gui.GFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JTextPane;



public class optionsFrame{

	private static final long serialVersionUID = 1L;

	private JFrame Options = new JFrame();
	private JPanel Return;
	private JButton btnMazeConf;
	private JButton btnDiff;
	private JButton btnReturn;
	private JButton btnNDragons;
	JSlider DiffSlider;
	JSpinner MazeSizeSpinner;
	JSpinner DragonSpinner;
	private JTextPane txtpnMazeSize;
	private JPanel Config;
	private JTextPane txtpnNumberOfDragons;
	private JPanel TextPanel;
	private JTextPane txtpnDifficulty;
	int nD = 1 , nM = 7 , nDf =1;

	private GFrame gF;
	
	
	public optionsFrame(final GFrame gF)
	{
		this.gF = gF;
		Options.setTitle("Options");
		Options.setDefaultCloseOperation(Options.EXIT_ON_CLOSE);
		Options.setResizable( false );
		Return = new JPanel();
		Options.getContentPane().add(Return,BorderLayout.SOUTH);
		Return.setLayout(new GridLayout(1,0,0,0));
		
		btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				gF.nD=(Integer)  DragonSpinner.getValue();
 
				
				gF.nDf= DiffSlider.getValue();
				Options.setVisible(false); //you can't see me!
				
			}
		});
		
		Config = new JPanel();
		Options.getContentPane().add(Config, BorderLayout.CENTER);
		Config.setLayout(null);
		
		DiffSlider = new JSlider();
		DiffSlider.setBounds(145, 0, 144, 218);
		Config.add(DiffSlider);
		DiffSlider.setOrientation(SwingConstants.VERTICAL);
		DiffSlider.setToolTipText("");
		DiffSlider.setPaintTicks(true);
		DiffSlider.setPaintLabels(true);
		DiffSlider.setMajorTickSpacing(1);
		DiffSlider.setMinimum(1);
		DiffSlider.setMaximum(3);
		
		MazeSizeSpinner = new JSpinner(new SpinnerNumberModel(new Integer(7) , new Integer(7),(Comparable)null , new Integer(2)));
		MazeSizeSpinner.setBounds(299, 88, 125, 30);
		Config.add(MazeSizeSpinner);
		
		DragonSpinner = new JSpinner(new SpinnerNumberModel(new Integer(1) , new Integer(1),(Comparable)null , new Integer(1)));
		DragonSpinner.setBounds(10, 88, 125, 30);
		Config.add(DragonSpinner);
		
		TextPanel = new JPanel();
		Options.getContentPane().add(TextPanel, BorderLayout.NORTH);
		TextPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		txtpnNumberOfDragons = new JTextPane();
		txtpnNumberOfDragons.setEditable(false);
		TextPanel.add(txtpnNumberOfDragons);
		txtpnNumberOfDragons.setText("Number of Dragons");
		
		txtpnDifficulty = new JTextPane();
		txtpnDifficulty.setText("Difficulty");
		TextPanel.add(txtpnDifficulty);
		
		txtpnMazeSize = new JTextPane();
		TextPanel.add(txtpnMazeSize);
		txtpnMazeSize.setText("Maze Size");
		
		addButtons();
		
	}
	
	public void setButtons()
	{
		
		
		
	}
	
	public void addButtons()
	{
		Return.add(btnReturn);
	}
	


	public void show() {
		Options.setSize(440, 300);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Options.setLocation(dim.width / 2 - Options.getSize().width / 2, dim.height / 2
				- Options.getSize().height / 2);

		Options.setVisible(true);
	}
}
