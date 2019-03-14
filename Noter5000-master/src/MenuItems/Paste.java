package MenuItems;

import java.awt.Event;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import Document.TextArea;
import Frames.MainFrame;

public class Paste extends JMenuItem{

	private MainFrame mainFrame;
	
	public Paste(){
		
		this.setText("Paste");
		
		this.setMnemonic(KeyEvent.VK_V);
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK));
		
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = MainFrame.getPane().getSelectedIndex();
				TextArea textArea = (TextArea) MainFrame.getPane().getComponentAt(index);
				
				textArea.paste();
				
				
			}
				
			
		});
		
	}
}
