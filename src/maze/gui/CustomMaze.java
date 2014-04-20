package maze.gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.filechooser.FileNameExtensionFilter;



public class CustomMaze {

	private JFrame frmCustomMaze;
	private JPanel panel1;
	private JPanel panel2;
	private JButton btnOptions;
	private int size;
	private int diff;
	private Dimension dim;
	private JComboBox<String> objects;
	private JButton btnSave;
	private JSlider DiffSlider;

	public CustomMaze()
	{
		size = 7;
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		frmCustomMaze = new JFrame();
		frmCustomMaze.setTitle("Labirinto Personalizado");

		panel1 = new JPanel();
		frmCustomMaze.getContentPane().add(panel1, BorderLayout.NORTH);

		try {
			panel2 = new CustomMazeCreationPanel(size,this);
			panel2.requestFocusInWindow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frmCustomMaze.getContentPane().add(panel2, BorderLayout.CENTER);

		btnOptions = new JButton("Op\u00E7\u00F5es");


		addButtons();
		setUpButtons();
	}


	void show()
	{

		frmCustomMaze.setSize(520,520);
		frmCustomMaze.setLocation(dim.width / 2 - frmCustomMaze.getSize().width / 2, dim.height / 2
				- frmCustomMaze.getSize().height / 2);
		frmCustomMaze.setVisible(true);

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

				if(((CustomMazeCreationPanel) panel2).readyToSave())
				{
					int diff;
					try
					{
						diff = (Integer) DiffSlider.getValue();
					}
					catch(Exception ex)
					{
						diff = 3;
					}

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


							Object saveM[] = new Object[2];
							saveM[0] = diff;
							saveM[1] = ((CustomMazeCreationPanel) panel2).getMaze();
							fw.writeObject(saveM);
							fw.close();
						}  
						catch(IOException ex){
							JOptionPane.showMessageDialog(new JFrame().getRootPane(), "Error Saving!");
						}  
					}   
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame().getRootPane(), "Your maze isn't ready!");
				}




			}

		});


		objects.setModel(new DefaultComboBoxModel<String>(new String[] {"Dragon",  "Exit","Hero", "Path","Sword", "Wall" }));
		objects.setSelectedIndex(3);

	}


	private void optionsFrame()
	{
		optionsFrame frame = new optionsFrame(this);
		frame.show();
	}

	public void updateMaze()
	{
		JFrame previousFrame = frmCustomMaze;
		frmCustomMaze = new JFrame();
		previousFrame.dispose();
		frmCustomMaze.getContentPane().add(panel1, BorderLayout.NORTH);
		try {
			panel2 = new CustomMazeCreationPanel(size, this);
			frmCustomMaze.getContentPane().add(panel2, BorderLayout.CENTER);
			show();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame().getRootPane(), "Error!");
		}
		
	}

	public JComboBox<String> getObjects() {
		return objects;
	}


	public int getSize() {
		return size;
	}


	public int getDiff() {
		return diff;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public void setDiff(int diff) {
		this.diff = diff;
	}

}
