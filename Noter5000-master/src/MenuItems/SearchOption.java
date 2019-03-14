package MenuItems;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import Frames.MainFrame;
import Frames.Search;

public class SearchOption extends JMenuItem{
	
	
	private Search search;
	
	public SearchOption(){
	
		this.setText("Search");
		
		this.setMnemonic(KeyEvent.VK_F);
		this.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK));
		
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				search = new Search();
				search.setVisible(true);
				search.setSize(350, 350);
			}
			
		});
	}

}
