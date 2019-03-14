package Menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Frames.MainFrame;
import MenuItems.Copy;
import MenuItems.Cut;
import MenuItems.Paste;

public class EditMenu extends JMenu{
	
	private MainFrame mainFrame;
	
	//menu items for CCP operations
	private JMenuItem cut, copy, paste;
	
	
	public EditMenu(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		
		this.setText("Edit");
		
		//creates the CCP menu items
		cut = new Cut();
		copy = new Copy();
		paste = new Paste();
		
		this.add(cut);
		this.add(copy);
		this.add(paste);
	}

}
