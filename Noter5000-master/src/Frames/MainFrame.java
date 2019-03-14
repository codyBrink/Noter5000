package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Document.TextArea;

import Menus.ColorSelection;
import Menus.EditMenu;
import Menus.FileMenu;
import Menus.FontMenu;
import Menus.SearchMenu;
import TabPane.TextPanel;
import json.Reader;
import json.Writer;

public class MainFrame extends JFrame{
	
	//color objects used to set the colors of the text documents
	private static Color bg;
	
	private static Color fg;
	
	//color objects to be used if the user cancels out of the ColorChooser
	private static Color curBg;
	
	private static Color curFg;
	
	//objects that read and write to "config.json" using "simple-json"
	private Writer writer;
	private Reader reader;
	
	//the menus
	private JMenu fileMenu, editMenu, colorMenu, fontMenu, searchMenu;
	
	
	//stores each object with its corresponding index number
	private static HashMap<Integer, TextArea> colors = new HashMap<Integer, TextArea>();
	
	//tab pane for the frame
    private static final JTabbedPane pane = new JTabbedPane();
    
    //ArrayList to hold the paths of currently opened files
    private static ArrayList<String> savedPaths = new ArrayList<String>();
    
    
    //constructor for our "Main Frame"
    public MainFrame(String title) {
    	
    	//pass a title to the parent JFrame constructor
        super(title);
        
        
        //sets the layout manager for this frame
        this.getContentPane().setLayout(new BorderLayout());
        
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        
        
      
        reader = new Reader(this);
        
        this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowOpened(WindowEvent e) {
				//checks if any tabs are up when window opens
				areTabs();
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				//if the "isClear" variable in "TextPanel" is
				//set to false. prompt the user with a dialog box
				if(!TextPanel.isClear()){
		        	int selectedOption = JOptionPane.showConfirmDialog(null, 
		                    "Are you sure you want to close without saving?", 
		                    "Unsaved Text", 
		                    JOptionPane.YES_NO_OPTION); 
						if (selectedOption == JOptionPane.YES_OPTION) {
							//if they chose "yes" then close window
							setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						}else if(selectedOption == JOptionPane.NO_OPTION){
							
						}
		        }else{
		        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        }
				
				
				writer = new Writer();
				
			}

        });
       
        //method to create the menu bar and its items
        initMenu();        
        
        add(getPane());   
        getPane().addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				//method that disables the "Save" and "Save As"
				//options IF there are no tabs present
				areTabs();
				
			}
        	
        });
        
        getColors().put(0, new TextArea());
        
        //makes the tab pane scrollable if too many tabs are up
        getPane().setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        
        //sets the properties of our jframe
        setSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
        setVisible(true);
        
        //sets the "Save" and "Save As" options to false when 
        //program first starts
        FileMenu.getSaveAsFile().setEnabled(false);
		FileMenu.getSaveFile().setEnabled(false);
		
    }
    
    //method that updates the color and font of components
    public void update(){
    	int count = getPane().getTabCount();
    	for(int x = 0; x < count; x++){
    		//loops thru and sets the following for each tab
    		TextArea t = getColors().get(x);
    		t.getLines().setForeground(bg);
    		t.getTextArea().setBackground(bg);
    		
    		t.getLines().setBackground(fg);
    		t.getTextArea().setForeground(fg);
    		
    		//updates the very first tab components 
    		TextArea fc = (TextArea) getPane().getComponentAt(0);
    		fc.getLines().setForeground(bg);
    		fc.getTextArea().setBackground(bg);
    		
    		fc.getLines().setBackground(fg);
    		fc.getTextArea().setForeground(fg);
    		
    			
    			
    		//gets static variable from "FontSize" class
    		int fontSize = FontSize.getSizeOfFont();
    		//gets the static varaible from "FontFamily" class
    		String fontFamily = FontFamily.getFontFamily();
    		Font ft = new Font(fontFamily, Font.BOLD, fontSize);
    		t.getTextArea().setFont(ft);
    		t.getLines().setFont(ft);
    		
    		fc.getTextArea().setFont(ft);
    		fc.getLines().setFont(ft);
    		repaint();
    	}
  
    	}
    
    
    //checks to see if there are any tabs
    public void areTabs(){
    	int tabs = getPane().getTabCount();
    	
    	if(tabs == 0){
    		FileMenu.getSaveAsFile().setEnabled(false);
    		FileMenu.getSaveFile().setEnabled(false);
    		TextPanel.setClear(true);
    	} else {
    		FileMenu.getSaveAsFile().setEnabled(true);
    		FileMenu.getSaveFile().setEnabled(true);
    	}
    }
    
    //method to create the menubar and its items
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        //creation of each menu class
        fileMenu = new FileMenu(this);
        
        editMenu = new EditMenu(this);
        
        colorMenu = new ColorSelection(this);
        
        fontMenu = new FontMenu(this);
        
        searchMenu = new SearchMenu(this);
        
        
        //adds the above menu items to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(colorMenu);
        menuBar.add(fontMenu);
        menuBar.add(searchMenu);
        
      
        setJMenuBar(menuBar);
    }

    //setter and getter methods 
    public static Color getBg() {
		return bg;
	}

	public static void setBg(Color bg) {
		MainFrame.bg = bg;
	}

	public static Color getFg() {
		return fg;
	}

	public static void setFg(Color fg) {
		MainFrame.fg = fg;
	}
    
	public static JTabbedPane getPane() {
		return pane;
	}
	
	
	public static Color getCurBg() {
		return curBg;
	}

	public static void setCurBg(Color curBg) {
		MainFrame.curBg = curBg;
	}

	public static Color getCurFg() {
		return curFg;
	}

	public static void setCurFg(Color curFg) {
		MainFrame.curFg = curFg;
	}

	public static HashMap<Integer, TextArea> getColors() {
		return colors;
	}

	public static void setColors(HashMap<Integer, TextArea> colors) {
		MainFrame.colors = colors;
	}
	
	public static ArrayList<String> getSavedPaths() {
		return savedPaths;
	}

	public static void setSavedPaths(ArrayList<String> savedPaths) {
		MainFrame.savedPaths = savedPaths;
	}

}