package MenuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Document.TextArea;
import Frames.MainFrame;
import TabPane.TextPanel;

public class SaveAsFile extends JMenuItem{
	
	private MainFrame mainFrame;
	
	private TextArea textArea;
	
	public SaveAsFile(MainFrame mainFrame){
		this.setText("Save As");
        this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//opens a file chooser
				JFileChooser save = new JFileChooser();
				//opens a 'saveDialogBox'
				int option = save.showSaveDialog(save);
				//IF user pressed ok
				if (option == JFileChooser.APPROVE_OPTION){
					//creates a bufferWrite to write to a file
					try{
						BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
						//writes the contents of the textarea to file
						out.write(textArea.getTextArea().getText());
						//closes the file
						out.close();
					} catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					
					int index = MainFrame.getPane().getSelectedIndex();
					MainFrame.getPane().setTabComponentAt(index, new TextPanel(MainFrame.getPane(), save.getSelectedFile().getName(), mainFrame));
					
				
					MainFrame.getSavedPaths().set(index, save.getSelectedFile().getPath().toString());
				}
			}
        	
        });
	}

}
