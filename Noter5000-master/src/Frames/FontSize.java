package Frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FontSize extends JFrame{

	//gets the font family from "FontFamily" class
	private String fontFamily = FontFamily.getFontFamily();
	
	//default value of 16
	private static int sizeOfFont = 16;
	
	//array used to populate the JList
	private static Object[] sizes = {6, 8, 12, 14, 16, 18, 20, 22, 24, 26, 28};
	
	private MainFrame mainFrame;
	
	private JList sizeList;
	
	private JScrollPane scrollPane;
	
	private JTextArea theText;
	
	private JButton confirm;
	
	private JButton close;
	
	
	
	public FontSize(MainFrame mainFrame){
		//Passes a title to the Parent Constructor (JFrame)
		super("Select font size");
		
		this.mainFrame = mainFrame;
		
		//do not want the user to resize the frame
		this.setResizable(false);
			
		//using absolute positioning (no layout manager)
		this.setLayout(null);
		
		JLabel title = new JLabel("Change the size of text");
		
		JLabel sample = new JLabel("Sample Text:");
		
		theText = new JTextArea("Hey now, Brown cow");
		
		Insets insets = this.getInsets();
		
		//code for our title
		Font titleFont = new Font(fontFamily, Font.BOLD, 16);
		title.setFont(titleFont);
		title.setForeground(Color.GREEN);
		Dimension size = title.getPreferredSize();
		title.setBounds(60 + insets.left, 0 + insets.top,
	             size.width, size.height);
		
		this.add(title);
		
		
		//code for the list of sizes
		sizeList = new JList(sizes);
		
		sizeList.setSelectedValue(sizeOfFont, false);
		
		//makes it where user can only select 1 item at a time
		sizeList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		//displays list items vertically
		sizeList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		
		sizeList.setVisibleRowCount(-1);
		
		//adds a list selection listener to the list
		sizeList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				//gets the selected index of list
				//and give it to the static varaible "SizeOfFont"
				//must cast as int because array is type "object"
				sizeOfFont = (int) sizeList.getSelectedValue();
				
				//calls the update() method defined below
				update();
				
			}
			
		});
		
		//code for our scrollpane
		scrollPane = new JScrollPane(sizeList);
		scrollPane.setPreferredSize(new Dimension(40, 150));
		size = scrollPane.getPreferredSize();
		scrollPane.setBounds(10 + insets.left, 20 + insets.top,
	             size.width, size.height);
		
		this.add(scrollPane);

	
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
		
		//code for close button
		close = new JButton("Close");
		close.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		size = close.getPreferredSize();
		close.setBounds(260 + insets.left, 280 + insets.top,
	             size.width, size.height);
		
		this.add(close);
		
		//code for sample label
		size = sample.getPreferredSize();
		sample.setBounds(120 + insets.left, 145 + insets.top,
	             size.width, size.height);
		
		this.add(sample);
		
		
		//code for theText
		theText.setPreferredSize(new Dimension(250, 80));
		size = theText.getPreferredSize();
		theText.setBounds(50 + insets.left, 175 + insets.top,
	             size.width, size.height);
		
		this.add(theText);
		
		
	}

	public static int getSizeOfFont() {
		return sizeOfFont;
	}

	public static void setSizeOfFont(int x){
		sizeOfFont = x;
	}
	
	public void update(){
		Font f = new Font(fontFamily, Font.BOLD, sizeOfFont);
		theText.setFont(f);
	}
	
}
