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



public class optionsFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel config;
	private JPanel Return;
	private JButton DragonNumber;
	private JButton MazeSize;
	private JButton Diff;
	private JButton Back;
	private JDialog SizeDiaglog = new JDialog();


	public optionsFrame()
	{
		setTitle("Options");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		config = new JPanel();

		MazeSize = new JButton("Maze size");
		MazeSize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//cria a caixa de diálogo para o tamanho do labirinto
				/*String s = SizeDiaglog.showInputDialog(
						"Choose the size of the maze\n"
								+ "(must be an odd number)\n",
								"Customized Dialog",
								"Input");
*/
				//If a string was returned, say so.
				/*
				if ((s != null) && (s.length() > 0)) {
					//setLabel("Green eggs and... " + s + "!");
					return;
				}*/

				//If you're here, the return value was null/empty.
				//setLabel("Come on, finish the sentence!");

			}
		});
		MazeSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


			}
		});


		DragonNumber = new JButton("Number of Dragons");
		DragonNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});


		Diff = new JButton("Difficulty");
		Back = new JButton("Back");
		
		config.setLayout(new GridLayout(2, 2));
		config.add(MazeSize);
		config.add(Diff);
		config.add(DragonNumber);
		config.add(Back);
		
		getContentPane().add(config, BorderLayout.CENTER);

	}


	public void show() {
		setSize(534, 401);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);

		setVisible(true);
	}

}
