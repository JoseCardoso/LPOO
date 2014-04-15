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
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class optionsFrame{

	private static final long serialVersionUID = 1L;

	private JFrame Options = new JFrame();

	private JPanel Config;
	private JButton btnMazeConf;
	private JButton btnDiff;
	private JButton btnReturn;
	private JButton btnNDragons;
	
	
	
	public optionsFrame()
	{
		Options.setTitle("Options");
		Options.setDefaultCloseOperation(Options.EXIT_ON_CLOSE);
		
		Config = new JPanel();
		Options.getContentPane().add(Config, BorderLayout.NORTH);
		Config.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnMazeConf = new JButton("Maze Configurations");
		
		btnNDragons = new JButton("Number Of Dragons");
		
		btnDiff = new JButton("Difficulty");
		
		btnReturn = new JButton("Return");
		
		
		
	}
	public void setButtons()
	{
		
		
		
	}
	
	public void addButtons()
	{

		Config.add(btnMazeConf);
		Config.add(btnNDragons);
		Config.add(btnDiff);
		Config.add(btnReturn);
	}
	


	public void show() {
		Options.setSize(534, 401);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Options.setLocation(dim.width / 2 - Options.getSize().width / 2, dim.height / 2
				- Options.getSize().height / 2);

		Options.setVisible(true);
	}

}
