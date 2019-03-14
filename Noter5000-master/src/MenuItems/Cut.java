package MenuItems;

import java.awt.Event;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import Document.TextArea;
import Frames.MainFrame;

public class Cut extends JMenuItem{
	
	
	public Cut(){
		
		this.setText("Cut");
		
		this.setMnemonic(KeyEvent.VK_X);
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
		
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//gets the currently selected textArea
				int index = MainFrame.getPane().getSelectedIndex();
				TextArea textArea = (TextArea) MainFrame.getPane().getComponentAt(index);
				
				//calls the "cut()" method of the TextArea class
				textArea.cut();
				
			}
			
		});
	}

	

}
