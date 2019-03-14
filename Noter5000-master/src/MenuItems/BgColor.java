package MenuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Frames.MainFrame;

public class BgColor extends JMenuItem{
	
	private MainFrame mainFrame;
	
	public BgColor(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
		this.setText("Background Color");
		
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel bgPanel = new JPanel();
				
				//makes the static variable "bg" equal to the color of whatever the user chose
				MainFrame.setBg(JColorChooser.showDialog(null, "choose background", MainFrame.getBg()));
				
				if (MainFrame.getBg() == null){
					//if the user hits "cancel" it will set the background color
					//to the currently used color
					MainFrame.setBg(MainFrame.getCurBg());
					
				}else{
					//if the user did choose a color it updates the curBg variable
					MainFrame.setCurBg(MainFrame.getBg());
				}
				//calls the update() method from MainFrame class to update UI
				mainFrame.update();
			}
		});
		
	}

}
