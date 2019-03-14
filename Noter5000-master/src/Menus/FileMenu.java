package Menus;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;

import Document.TextArea;
import Frames.MainFrame;
import MenuItems.NewFile;
import MenuItems.OpenFile;
import MenuItems.SaveAsFile;
import MenuItems.SaveFile;
import TabPane.TextPanel;

public class FileMenu extends JMenu{
	
	private MainFrame mainFrame;
	
	
	private JMenuItem openFile;
	
    private JMenuItem newFile;
    private static JMenuItem saveAsFile;
	
	private static JMenuItem saveFile;
	
	
	private TextArea textArea;
	
	
	
	
	public FileMenu(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
		this.setText("File");
		
		//instaniation of menuitems
		openFile = new OpenFile(mainFrame);
		newFile = new NewFile(mainFrame);
		saveAsFile = new SaveAsFile(mainFrame);
		saveFile = (new SaveFile(mainFrame));
		
		
		
		this.add(openFile);
		
		
        this.add(newFile);
        
		this.add(saveAsFile);
        
        
        this.add(saveFile);
		
		
	}

	//getter and setter methods
	public static JMenuItem getSaveAsFile() {
		return saveAsFile;
	}

	public void setSaveAsFile(JMenuItem saveAsFile) {
		this.saveAsFile = saveAsFile;
	}

	public static JMenuItem getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(JMenuItem saveFile) {
		this.saveFile = saveFile;
	}
	
}
