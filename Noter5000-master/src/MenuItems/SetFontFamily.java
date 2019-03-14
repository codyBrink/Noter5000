package MenuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import Frames.FontFamily;
import Frames.MainFrame;

public class SetFontFamily extends JMenuItem{
	
	private MainFrame mainFrame;
	
	private FontFamily fontFamily;
	
	public SetFontFamily(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
		this.setText("Font Family");
		
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fontFamily = new FontFamily(mainFrame);
				fontFamily.setSize(350, 350);
				fontFamily.setVisible(true);
				
				mainFrame.update();
				
			}
			
		});
	}

}
