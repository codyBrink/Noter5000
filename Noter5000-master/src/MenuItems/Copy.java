package MenuItems;

import java.awt.Component;
import java.awt.Event;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import Document.TextArea;
import Frames.MainFrame;


public class Copy extends JMenuItem{
	
	public Copy(){
		
		this.setText("Copy");
		
		this.setMnemonic(KeyEvent.VK_C);
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
		
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//gets the currently selected tab
				int index = MainFrame.getPane().getSelectedIndex();
				TextArea textArea = (TextArea) MainFrame.getPane().getComponentAt(index);
				
				//calls the "copy()" method of the TextArea class
				textArea.copy();
				
			}
			
		});
		
	}

}
