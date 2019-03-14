package MenuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import Frames.FontSize;
import Frames.MainFrame;

public class SetFontSize extends JMenuItem{
	
	private MainFrame mainFrame;
	
	private FontSize fontSize;
	
	public SetFontSize(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		
		this.setText("Font Size");
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fontSize = new FontSize(mainFrame);
				fontSize.setSize(350, 350);			
				fontSize.setVisible(true);
				
				mainFrame.update();
				
			}
			
			
		});
	}

}
