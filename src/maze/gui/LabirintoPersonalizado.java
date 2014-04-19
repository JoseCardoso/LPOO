package maze.gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.border.CompoundBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import maze.game.MazeGame;

public class LabirintoPersonalizado {

	private JFrame frmLabirintoPersonalizado;
	private JPanel panel1;
	private JPanel panel2;
	private JButton btnOptions;
	private int size;
	private Dimension dim;
	private JComboBox<String> objects;
	private JButton btnSave;
	private JSlider DiffSlider;
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



		btnOptions = new JButton("Op\u00E7\u00F5es");


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
		panel1.setLayout(new GridLayout(0, 3, 0, 1));
		objects = new JComboBox<String>();

		panel1.add(objects);
		panel1.add(btnOptions);

		btnSave = new JButton("Guardar");
		panel1.add(btnSave);

	}


	private void setUpButtons() {
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionsFrame();			

			}

		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MazeGame game = ((PainelCriaLabirinto) panel2).createComponents((Integer) DiffSlider.getValue());
				
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

						fw.writeObject(game);
						fw.close();
					}  
					catch(IOException ex){
						JOptionPane.showMessageDialog(new JFrame().getRootPane(), "Error Saving!");
					}  
				}   
				

			}

		});


		objects.setModel(new DefaultComboBoxModel<String>(new String[] {"Dragon",  "Exit","Hero", "Path","Sword", "Wall" }));
		objects.setSelectedIndex(3);

	}


	private void optionsFrame()
	{
		JFrame opF = new JFrame("Op\u00E7\u00F5es");
		JPanel opP = new JPanel();
		JSpinner siz = new JSpinner(new SpinnerNumberModel(new Integer(7) , new Integer(7),(Comparable<?>)null , new Integer(1)));
		DiffSlider = new JSlider();
		JLabel	DiffLabel = new JLabel("Difficulty");
		JLabel diffDescriptionLabel = new JLabel("Stopped          Moving      Moving/Sleeping");

		diffDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		diffDescriptionLabel.setBounds(101, 139, 244, 14);

		DiffLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DiffLabel.setBounds(22, 105, 69, 30);

		siz.setBounds(174, 43, 125, 30);

		DiffSlider.setBorder(new CompoundBorder());
		DiffSlider.setBounds(122, 105, 177, 30);

		DiffSlider.setToolTipText("");
		DiffSlider.setPaintTicks(true);
		DiffSlider.setMajorTickSpacing(1);
		DiffSlider.setMinimum(1);
		DiffSlider.setMaximum(3);

		opP.setLayout(new GridLayout(3,0,0,0));
		opP.add(DiffSlider);
		opP.add(siz);
		opF.getContentPane().add(opP,BorderLayout.NORTH);			


		opF.setSize(361,520);
		opF.setLocation(dim.width / 2 - opF.getSize().width / 2, dim.height / 2
				- opF.getSize().height / 2);
		opF.setVisible(true);

	}


	public JComboBox<String> getObjects() {
		return objects;
	}

}
