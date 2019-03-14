package Document;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Frames.MainFrame;
import MenuItems.Copy;
import MenuItems.Cut;
import MenuItems.Paste;
import TabPane.TextPanel;


import com.inet.jortho.SpellChecker;
import com.inet.jortho.FileUserDictionary;

import com.inet.jortho.SpellCheckerOptions;
import com.inet.jortho.UserDictionaryProvider;

import javax.swing.JPopupMenu;
import com.inet.jortho.PopupListener; 

public class TextArea extends JPanel{
	//the textarea that will hold text that
	//the user enters
	private JTextArea textArea;
	
	//scrollpane for our text area
	private JScrollPane jsp;
	
	//textarea that acts as the line numbers
	private JTextArea lines;
	
	//object of our mainFrame class
	private MainFrame mainFrame;
	
	//object of our popup menu
	private JPopupMenu popup;
	
	public TextArea(){
		
		textArea = new JTextArea();
		
		
		
		//sets the layout manager for this class
		this.setLayout(new BorderLayout());
		
		//adds the textArea to the center of the panel
		this.add(textArea, BorderLayout.CENTER);
		
		//scrollbar for line numbers
		jsp = new JScrollPane();
				
		//textarea for line numbers
		lines = new JTextArea("1");
				
		textArea.getDocument().addDocumentListener(new DocumentListener(){
			public String getText(){
				//gets the position of the caret
				int caretPosition = textArea.getDocument().getLength();
				javax.swing.text.Element root = textArea.getDocument().getDefaultRootElement();
				//writes the line number after the document goes to a new line
				String text = "1" + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
					text += i + System.getProperty("line.separator");
				}
				return text;
			}
			
			//any change to the current document will cause the
			//boolean variable "isClear" to be false.
			public void changedUpdate(DocumentEvent de) {
				lines.setText(getText());
				TextPanel.setClear(false);
				
			}
 
			public void insertUpdate(DocumentEvent de) {
				lines.setText(getText());
				TextPanel.setClear(false);
				
			}
 
			public void removeUpdate(DocumentEvent de) {
				lines.setText(getText());
				TextPanel.setClear(false);
				
			}
			
		});
		
		//calls the method that sets up JOrtho
		spellCheckerSetUp();
		
		
		//sets which component the scrollpane will create a scroll for
		jsp.getViewport().add(textArea);
		jsp.setRowHeaderView(lines);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		lines.setEditable(false);
		
		this.add(jsp);
		this.setSize(500,500);
		this.setVisible(true);
		
		
	
		//mouse events to show the popup menu
		textArea.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent evt) {
		        if (evt.isPopupTrigger()) {
		          popup.show(evt.getComponent(), evt.getX(), evt.getY());
		        }
		      }
			
			public void mouseReleased(MouseEvent evt) {
		        if (evt.isPopupTrigger()) {
		          popup.show(evt.getComponent(), evt.getX(), evt.getY());
		        }
		      }
		});
		
		
	}
	
	//methods that perform the typical CCP operations
	public void cut(){
		textArea.cut();
	}
	
	public void copy(){
		textArea.copy();
	}
	
	public void paste(){
		textArea.paste();
	}
	
	
	private void spellCheckerSetUp(){
		//path to dictionary file 
				String userDictionaryPath = "/dictionary/";
				
				//sets the dictionary file to be used when the user adds a new word 
				//to the dictionary
				SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
				
				//registers the language of the dictionary as English
				SpellChecker.registerDictionaries(getClass().getResource(userDictionaryPath), "en");
				
				//registers the textArea object to be checked for spelling errors
				SpellChecker.register(textArea);
				
				//calls the method that creates our popup menu
				configPopupMenu();
	}
	
	private void configPopupMenu(){
		
		//creates the popup menu
		popup = new JPopupMenu();
		
		//creates the CCP options for popup menu 
		Cut cut = new Cut();
		Copy copy = new Copy();
		Paste paste = new Paste();
		
		popup.add(cut);
		popup.add(copy);
		popup.add(paste);
		
		//adds the spellchecker to the popup menu
		popup.add(SpellChecker.createCheckerMenu());
		
		
		textArea.addMouseListener(new PopupListener(popup));
		
	}
	
	
	
	//getter and setter methods
	public JTextArea getLines() {
		return lines;
	}

	public void setLines(JTextArea lines) {
		this.lines = lines;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public JScrollPane getJsp() {
		return jsp;
	}

	public void setJsp(JScrollPane jsp) {
		this.jsp = jsp;
	}
		
}
