package Menus;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Frames.MainFrame;
import Frames.Search;
import MenuItems.BgColor;
import MenuItems.FgColor;
import json.Reader;

public class ColorSelection extends JMenu{
	
	//the menu items for this menu section
	private JMenuItem bgColor, fgColor;
	
	private MainFrame mainFrame;
	
	
	public ColorSelection(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		
		this.setText("Colors");
		
		bgColor = new BgColor(mainFrame);
		fgColor = new FgColor(mainFrame);
		
		
		//code for the "bgColor" option
		
		this.add(bgColor);
		
		//code for "fgColor" option		
		
		this.add(fgColor);
		
	}

}
