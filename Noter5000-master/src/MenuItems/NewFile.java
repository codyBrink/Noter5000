package MenuItems;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import Document.TextArea;
import Frames.MainFrame;
import TabPane.TextPanel;

public class NewFile extends JMenuItem{
	
	private MainFrame mainFrame;
	
	private TextArea textArea;
	
	private int incre = 1;

	public NewFile(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
        this.setText("New");
        this.setMnemonic(KeyEvent.VK_N);
        this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
        this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String newTabTitle = "New File";
				
				//IF no file with the above title exists already...
				if(MainFrame.getPane().indexOfTab(newTabTitle) == -1){
					MainFrame.getPane().addTab(newTabTitle, textArea = new TextArea());
					int index = MainFrame.getPane().indexOfTab(newTabTitle);
					MainFrame.getPane().setTabComponentAt(index, new TextPanel(MainFrame.getPane(), newTabTitle, mainFrame));
					MainFrame.getColors().put(index, textArea);
					MainFrame.getSavedPaths().add(index, "New File");
					
				}else {
					
					//if there is already a file with that name
					MainFrame.getPane().add(newTabTitle + incre, textArea = new TextArea());
					int index = MainFrame.getPane().indexOfTab(newTabTitle + incre);
					incre++;
					MainFrame.getPane().setTabComponentAt(index, new TextPanel(MainFrame.getPane(), newTabTitle, mainFrame));
					MainFrame.getColors().put(index, textArea);
					MainFrame.getSavedPaths().add(index, "New File");
					
					
				}
				
				TextPanel.setClear(true);
				mainFrame.update();
				
			}
        	
        });
	}
}
