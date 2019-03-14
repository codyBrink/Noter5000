package Frames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;

import Document.TextArea;

import javax.swing.text.JTextComponent;


public class Search extends JFrame{
	
	//starting position of the search function
	private int pos = 0;
	
	//Initial color of the highlighter
	private static Color hiClr = Color.YELLOW;
	
	//color used to keep the current color if
	//the user cancels out of the color chooser
	private static Color curHiClr = hiClr;
	
	private TextArea textArea;
	
	//the search bar
	private JTextField srhBar;
	
	//the search button
	private JButton srhBtn;
	
	
	
	//class for HighLighter
	class TxtHighLight extends DefaultHighlighter.DefaultHighlightPainter {
			//constructor
			public TxtHighLight(Color color){
					super(color);
				}
			}
			//instantiate of the HighLighter class
			Highlighter.HighlightPainter highLightclr = new TxtHighLight(hiClr);
			
			//method for highlighting the text 
			public void highlight(JTextComponent textComp, String pattern){
				try{
					//member variable that gets the highlight of formal parameter
					Highlighter hiLite = textComp.getHighlighter();
					
					Highlighter.Highlight[] hilites = hiLite.getHighlights();
					//member variable that gets the document from formal parameter
					Document doc = textComp.getDocument();
					String text = doc.getText(0, doc.getLength());
					
					//if the string returned true...
					if((pos=text.toUpperCase().indexOf(pattern.toUpperCase(),pos))>=0){
							
							//adds the highlight color to the text that matches the pattern
							hiLite.addHighlight(pos, pos+pattern.length(), highLightclr);
							
							textComp.setCaretPosition(pos);
							//increments "pos" for every instance of the pattern
							pos += pattern.length();
							
						
					}
					else{
						//if no matches were found remove any previous highlights 
						//and display a message
						hiLite.removeAllHighlights();
						JOptionPane.showMessageDialog(this, "No matches were found");
					}
				}catch(Exception e){
					//if an error occurred. will show it in dialogBox
					JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
						
				}
			}
	
	//the class for the search functionality 
	public Search(){
		
		//using absolute positioning (no layout manager)
		this.setLayout(null);
		
		Insets insets = this.getInsets();
		
		srhBar = new JTextField();
		srhBtn = new JButton("Search");
		
		//code for the search field
		srhBar.setPreferredSize(new Dimension(180, 26));
		Dimension size = srhBar.getPreferredSize();
		
		srhBar.setBounds(40 + insets.left, 120 + insets.top,
	             size.width, size.height);
		
		this.add(srhBar);
		
		//code for the search button
		srhBtn.setPreferredSize(new Dimension(75, 25));
		
		size = srhBtn.getPreferredSize();
		
		srhBtn.setBounds(220 + insets.left, 120 + insets.top,
	             size.width, size.height);
		
		srhBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//gets the tabbedpane
				JTabbedPane pane = MainFrame.getPane();
				
				//gets the index of the currently selected tab
				int index = pane.getSelectedIndex();
				
				//gets the textarea object from the "colors" Hashmap
				TextArea textArea = (TextArea) MainFrame.getPane().getComponentAt(index);
				
				highlight(textArea.getTextArea(), srhBar.getText());
				
			}
			
		});
		
		this.add(srhBtn);
		
	}
	
	//getter and setter methods
	public static Color getHiClr() {
		return hiClr;
	}
	
	public static Color getCurHiClr() {
		return curHiClr;
	}

	public static void setCurHiClr(Color curHiClr) {
		Search.curHiClr = curHiClr;
	}

}
