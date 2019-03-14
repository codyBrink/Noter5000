package Menus;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import Frames.FontFamily;
import Frames.FontSize;
import Frames.MainFrame;
import MenuItems.SetFontFamily;
import MenuItems.SetFontSize;

public class FontMenu extends JMenu{
	
	private JFrame fontSize, fontFamily;
	
	private JMenuItem setFontSize, setFontFamily;
	
	private MainFrame mainFrame;
	
	public FontMenu(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		this.setText("Font");
		
		setFontSize = new SetFontSize(mainFrame);
		setFontFamily = new SetFontFamily(mainFrame);
		
		
		this.add(setFontSize);
		
		this.add(setFontFamily);
		
	}

}
