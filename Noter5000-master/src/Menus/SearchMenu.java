package Menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Frames.MainFrame;
import Frames.Search;
import MenuItems.SearchOption;

public class SearchMenu extends JMenu{

	private JMenuItem search;
	
	private MainFrame mainFrame;
	
	//object of the search window
	private Search srhOpt;
	
	public SearchMenu(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
		this.setText("Search");
		
		
		search = new SearchOption();
		
		this.add(search);
	}
}
