package MenuItems;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Document.TextArea;
import Frames.MainFrame;
import TabPane.TextPanel;

public class SaveFile extends JMenuItem{
	
	private MainFrame mainFrame;
	
	private TextArea textArea;
	
	public SaveFile(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
		this.setText("Save");
        this.setMnemonic(KeyEvent.VK_S);
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = MainFrame.getPane().getSelectedIndex();
				String curPath = MainFrame.getSavedPaths().get(index);
				//gets the current component
				TextArea curFile = (TextArea) MainFrame.getPane().getComponentAt(index);
				//will open the JFileChooser IF the file title is "New File"
				//which is what the default name of a "new" document is
				if(MainFrame.getPane().getTitleAt(index).indexOf("New File") < 0){
				
					//creates a bufferWrite to write to a file
					try{
						BufferedWriter out = new BufferedWriter(new FileWriter(curPath));
						//writes the contents of the textarea to file
						out.write(curFile.getTextArea().getText());
						//closes the file
						out.close();
					} catch(IOException ex){
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					TextPanel.setClear(true);
				}else{
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
						
						MainFrame.getPane().setTabComponentAt(index, new TextPanel(MainFrame.getPane(), save.getSelectedFile().getName(), mainFrame));
						
						MainFrame.getSavedPaths().add(index, save.getSelectedFile().getPath().toString());
					}
				}
			}
        	
        });
	}

}

