package MenuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Frames.MainFrame;

public class FgColor extends JMenuItem{
	
	private MainFrame mainFrame;

	public FgColor(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
		this.setText("Text Color");
		
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel fgPanel = new JPanel();
				MainFrame.setFg(JColorChooser.showDialog(null, "choose background", MainFrame.getFg()));
				if(MainFrame.getFg() == null){
					MainFrame.setFg(MainFrame.getCurFg());
				}else{
					MainFrame.setCurFg(MainFrame.getFg());
				}
				mainFrame.update();
			}
			
		});
	}
}
