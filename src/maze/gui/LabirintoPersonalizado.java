package maze.gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.JComboBox;

public class LabirintoPersonalizado {

	private JFrame frmLabirintoPersonalizado;
	private JPanel panel1;
	private JPanel panel2;
	private JButton btnTamanho;
	private int size;
	private Dimension dim;
	JComboBox<String> objects;

	public LabirintoPersonalizado()
	{
		size = 7;
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		frmLabirintoPersonalizado = new JFrame();
		frmLabirintoPersonalizado.setTitle("Labirinto Personalizado");

		panel1 = new JPanel();
		frmLabirintoPersonalizado.getContentPane().add(panel1, BorderLayout.NORTH);

		try {
			panel2 = new PainelCriaLabirinto(size,this);
			panel2.requestFocusInWindow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frmLabirintoPersonalizado.getContentPane().add(panel2, BorderLayout.CENTER);



		btnTamanho = new JButton("Tamanho");
		objects = new JComboBox<String>();


		addButtons();
		setUpButtons();
	}


	void show()
	{

		frmLabirintoPersonalizado.setSize(520,520);
		frmLabirintoPersonalizado.setLocation(dim.width / 2 - frmLabirintoPersonalizado.getSize().width / 2, dim.height / 2
				- frmLabirintoPersonalizado.getSize().height / 2);
		frmLabirintoPersonalizado.setVisible(true);

	}

	private void addButtons()
	{
		panel1.setLayout(new GridLayout(0, 2, 0, 0));
		panel1.add(btnTamanho);

		panel1.add(objects);

	}


	private void setUpButtons() {
		btnTamanho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tamanhoFrame();			

			}

		});

		objects.setModel(new DefaultComboBoxModel<String>(new String[] {"Dragon",  "Exit","Hero", "Path","Sword", "Wall" }));
		objects.setSelectedIndex(3);

	}


	private void tamanhoFrame()
	{
		JFrame sizeF = new JFrame("gimmeSize");
		JPanel sizeP = new JPanel();
		JSpinner siz = new JSpinner(new SpinnerNumberModel(new Integer(7) , new Integer(7),(Comparable<?>)null , new Integer(1)));
		siz.setBounds(174, 43, 125, 30);

		sizeP.add(siz);
		sizeF.getContentPane().add(sizeP,BorderLayout.NORTH);			


		sizeF.setSize(361,520);
		sizeF.setLocation(dim.width / 2 - sizeF.getSize().width / 2, dim.height / 2
				- sizeF.getSize().height / 2);
		sizeF.setVisible(true);

	}

}
