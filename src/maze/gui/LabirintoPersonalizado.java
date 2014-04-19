package maze.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LabirintoPersonalizado {

	private JFrame frmLabirintoPersonalizado;
	private JPanel panel1;
	private JButton btnTamanho;
	private int size;
	private Dimension dim;
	
	public LabirintoPersonalizado()
	{
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		frmLabirintoPersonalizado = new JFrame();
		frmLabirintoPersonalizado.setTitle("Labirinto Personalizado");
		
		panel1 = new JPanel();
		frmLabirintoPersonalizado.add(panel1, BorderLayout.NORTH);
		
		btnTamanho = new JButton("Tamanho");
		

		frmLabirintoPersonalizado.setSize(361,520);
		frmLabirintoPersonalizado.setLocation(dim.width / 2 - frmLabirintoPersonalizado.getSize().width / 2, dim.height / 2
				- frmLabirintoPersonalizado.getSize().height / 2);
		frmLabirintoPersonalizado.setVisible(true);
		
		
		addButtons();
		setUpButtons();
	}
	
	
	private void addButtons()
	{
		panel1.add(btnTamanho);
		
	}
	
	
	private void setUpButtons() {
		btnTamanho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tamanhoFrame();			
				
			}
				
		});
		
	}
	
	
	private void tamanhoFrame()
	{
		JFrame gimmeSize = new JFrame("gimmeSize");
		JPanel nigger = new JPanel();
		JSpinner tam = new JSpinner(new SpinnerNumberModel(new Integer(7) , new Integer(7),(Comparable<?>)null , new Integer(1)));
		tam.setBounds(174, 43, 125, 30);
		
		nigger.add(tam);
		gimmeSize.add(nigger,BorderLayout.NORTH);			
		
		
		gimmeSize.setSize(361,520);
		gimmeSize.setLocation(dim.width / 2 - gimmeSize.getSize().width / 2, dim.height / 2
				- gimmeSize.getSize().height / 2);
		gimmeSize.setVisible(true);
		
	}
	
}
