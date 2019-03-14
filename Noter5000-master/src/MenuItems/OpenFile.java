package MenuItems;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Document.TextArea;
import Frames.MainFrame;
import TabPane.TextPanel;

public class OpenFile extends JMenuItem{
	
	private MainFrame mainFrame;
	private TextArea textArea;
	
	private int incre = 1;
	
	public OpenFile(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
		this.setText("Open");
		this.setMnemonic(KeyEvent.VK_O);
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//opens a dialog box that lets
				//the user choose a file
				JFileChooser open = new JFileChooser();
				//gets the option user chose (approve or cancel)
				int option = open.showOpenDialog(open);
				//since it's opening a file  
				//use "openDialogBox". 
				//if the user pressed ok..it returns "APPROVED_OPTION"
				//IF statement to open file
				if (option == JFileChooser.APPROVE_OPTION){
					JLabel fileName = new JLabel();
					fileName.setText(open.getSelectedFile().getName().toString());
					if(MainFrame.getPane().indexOfTab(fileName.getText()) == -1){
						MainFrame.getPane().add(open.getSelectedFile().getName().toString(), textArea = new TextArea());
						int index = MainFrame.getPane().indexOfTab(fileName.getText());
						//make sure that its "setTabComponentAt" NOT "setComponentAt"
						MainFrame.getPane().setTabComponentAt(index, new TextPanel(MainFrame.getPane(), open.getSelectedFile().getName().toString(), mainFrame));
					
						//stores in hash for updating colors
						MainFrame.getColors().put(index, textArea);
					
						MainFrame.getSavedPaths().add(index, open.getSelectedFile().getPath().toString());
					
					
					}else{
					
						MainFrame.getPane().add(open.getSelectedFile().getName().toString() + incre, textArea = new TextArea());
						int index = MainFrame.getPane().indexOfTab(fileName.getText() + incre);
						incre++;
						MainFrame.getPane().setTabComponentAt(index, new TextPanel(MainFrame.getPane(), open.getSelectedFile().getName().toString(), mainFrame));
					
					
						MainFrame.getColors().put(index, textArea);
						MainFrame.getSavedPaths().add(index, open.getSelectedFile().getPath().toString());
					
					}
					//creates a scanner to read the file
					try{
						//method passed in that gets the file path
						Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
						//while there is still something to read
						while (scan.hasNext()){
							//append the line to the text area
							textArea.getTextArea().append(scan.nextLine() + "\n");
						}
						scan.close();
					} catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					TextPanel.setClear(true);
					mainFrame.update();
				}
			
			}
    	
		});
    
	}
    
}