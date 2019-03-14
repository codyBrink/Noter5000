package TabPane;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import Frames.MainFrame;

public class TextPanel extends JPanel{
	private final JTabbedPane pane;
	private String title;
	
	private MainFrame mainFrame;
	
	//boolean variable used to alert the
	//user of any possible unsaved changes
	private static boolean isClear;
	
	private JTextArea textArea;
	private Graphics g;
	
	//constructor for "TextPanel" class
	public TextPanel(final JTabbedPane pane, String title, MainFrame mainFrame){
		//passes the desired layout manager to the parent class (JPAnel) constructor
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		this.mainFrame = mainFrame;
		
		//checks if the tabbedpane component
		//passed into the formal parameters was not instantiated
		if(pane == null){
			//if its null will throw an exception
			throw new NullPointerException("TabbedPane is null");
		}
		
		this.pane = pane;
		this.title = title;
		
		//sets transparency of the tabs  to false
		setOpaque(false);
		
		
		//label to be used as title
		JLabel theTitle = new JLabel(title);
		
		//adds the title to panel
		add(theTitle);
		
		//add more space between the label and the close button
        theTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        
        //creates and adds the close button to the panel
        JButton button = new CloseBtn(mainFrame);
        add(button);
        
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        
	}
	
	//code for the button class
	//
	//					class
	//
	//					for
	//
	//					CloseBtn
	//

	public class CloseBtn extends JButton implements ActionListener{
		
		public CloseBtn(MainFrame mainFrame){
			//desired size of the button
			int size = 17;
			
			//sets the size of button
			setPreferredSize(new Dimension(size, size));
			
			//tooltip for button
			setToolTipText("close this tab");
			
			//gives the button a basic look
			setUI(new BasicButtonUI());
			
			//Make the button transparent
	        setContentAreaFilled(false);
	        
	        setFocusable(false);
	        
	        //creates an "etched border" for the button 
	        //makes it so the button doesn't have a constant
	        //box around it
	        setBorder(BorderFactory.createEtchedBorder());
	        setBorderPainted(false);
	      
	        //adds action listener to our button
	        addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//will display a warning box if the variable "isClear" 
			//is set to false
			if(isClear == true){
				//gets index of the current textPanel in the tabbedpane
				int i = pane.indexOfTabComponent(TextPanel.this);
			
				//makes sure the index is not -1
				if (i != -1) {
					pane.remove(i);
					//removes the index and its value
					//from the HashMaps
					
					mainFrame.getSavedPaths().remove(i);
					MainFrame.getColors().remove(i);
				}
			}else{
				//if "isClear" is false...
				int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to close without saving?", 
                        "Unsaved Text", 
                        JOptionPane.YES_NO_OPTION); 
					if (selectedOption == JOptionPane.YES_OPTION) {
						//gets index of the current textPanel in the tabbedpane
						int i = pane.indexOfTabComponent(TextPanel.this);
					
						//makes sure the index is not -1
						if (i != -1) {
							pane.remove(i);
			
							
							MainFrame.getSavedPaths().remove(0);
							MainFrame.getColors().remove(i);
							
						}
					}
			}
			
		}
		
        //paint the "X" for our button
        protected void paintComponent(Graphics g) {
        	//passes in our Graphics object to the superclass
            super.paintComponent(g);
            
            //creates a 2dGraphics object
            Graphics2D g2 = (Graphics2D) g.create();
            
            //shift the image for pressed buttons
            //basically makes the "X" look different when clicked
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            if (getModel().isRollover()) {
            	//when mouse hovers over the clsoe button
            	//changes its color to red
                g2.setColor(Color.RED);
            }
            int delta = 6;
            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            g2.dispose();
        }

	}
	
	
	
	
	//getter and setter methods
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public static boolean isClear() {
		return isClear;
	}

	public static void setClear(boolean isClear) {
		TextPanel.isClear = isClear;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
