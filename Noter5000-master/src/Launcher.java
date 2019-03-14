import javax.swing.SwingUtilities;

import Frames.MainFrame;

public class Launcher {

public static void main(String[] args) {
    	
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
            
            MainFrame frame = new MainFrame("Noter5000 -Made by Cody Brink");
            
            
            }
        });
    }
}
