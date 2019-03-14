package Frames;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FontFamily extends JFrame{

	//variable to be used as the fontFamily. set to "Century Gothic" by default
	private static String fontFamily = "Century Gothic";
	
	//array that holds all the font families to chose from
	private static Object[] families = {"Algerian", "Arial", "Bell MT", "Bookman Old Style",
			"Calibri", "Century Gothic", "Comic Sans MS", "French Script MT", "Georgia",
			"Harrington", "Magneto", "Onyx"};
	
	
	//scrollpane for font family list
	private JScrollPane scrollPane;
	
	//list to hold the font families 
	private JList familyList;
	
	//text area that holds text to model the currently selected font family
	private JTextArea sampleText;

	//button for closing the frame and setting the newly selected color
	private JButton confirm;
	
	//button that acts as the cancel button
	private JButton cancel;
	
	private MainFrame mainFrame;
	
	
	public FontFamily(MainFrame mainFrame){
		
		//passes a title for the frame to the parent class (JFrame)
		super("Select font families");
		
		this.mainFrame = mainFrame;
		
		this.setResizable(false);
		
		//using absolute positioning 
		this.setLayout(null);
		
		//label to act as the title 
		JLabel title = new JLabel("Change the font family");
		
		//labelthat goes above the "sampleText" text area
		JLabel sample = new JLabel("Sample Text:");
		
		sampleText = new JTextArea("Hey now, Brown cow");
		
		Insets insets = this.getInsets();
		
		//code for the title text
		Font titleFont = new Font(fontFamily, Font.BOLD, 16);
		title.setFont(titleFont);
		title.setForeground(Color.GREEN);
		Dimension size = title.getPreferredSize();
		title.setBounds(60 + insets.left, 0 + insets.top,
	             size.width, size.height);
		
		this.add(title);
		
		//code for the list
		familyList = new JList(families);
		
		//sets the selected value to whatever is stored in the static variable "fontFamily"
		familyList.setSelectedValue(fontFamily, false);
		
		//makes it where only one item can be selected at a time
		familyList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		//displays the list items vertically
		familyList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		
		familyList.setVisibleRowCount(-1);
		
		familyList.addListSelectionListener(new ListSelectionListener(){

			//sets the value of "fontFamily" of what list item
			//was selected whenever a new list item is selected
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//gets the selected index of list
				//and give it to the value for font changing
				fontFamily = (String) familyList.getSelectedValue();
				
				//calls the update() method for updaing the "sampleText"
				update();
				
			}
			
		});
		
		
		//code for the scroll pane
		scrollPane = new JScrollPane(familyList);
		scrollPane.setPreferredSize(new Dimension(100, 150));
		size = scrollPane.getPreferredSize();
		scrollPane.setBounds(10 + insets.left, 20 + insets.top,
	             size.width, size.height);
		
		
		scrollPane.setVisible(true);
		this.add(scrollPane);
		
		//code for the "confirm" button
		confirm = new JButton("Ok");
		
		
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.update();
				dispose();
				
			}
			
		});
		size = confirm.getPreferredSize();
		confirm.setBounds(205 + insets.left, 280 + insets.top,
	             size.width, size.height);
		
		this.add(confirm);
		
		//code for the cancel button
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
						
			}
					
		});
		
			size = cancel.getPreferredSize();
			cancel.setBounds(260 + insets.left, 280 + insets.top,
			             size.width, size.height);
				
				this.add(cancel);
				
				//code for sample label
				size = sample.getPreferredSize();
				sample.setBounds(120 + insets.left, 145 + insets.top,
			             size.width, size.height);
				
				this.add(sample);
				
				
				//code for the "sampleText"
				sampleText.setPreferredSize(new Dimension(250, 80));
				size = sampleText.getPreferredSize();
				sampleText.setBounds(50 + insets.left, 175 + insets.top,
			             size.width, size.height);
				
				this.add(sampleText);
		
		
	}
	
	//method to update the sampleText
	public void update(){
		Font f = new Font(fontFamily, Font.BOLD, 16);
		sampleText.setFont(f);
	}
	
	//getter and setter methods
	public static String getFontFamily() {
		return fontFamily;
	}

	public static void setFontFamily(String fontFamily) {
		FontFamily.fontFamily = fontFamily;
	}
	
}
