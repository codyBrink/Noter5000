package json;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Document.TextArea;
import Frames.FontFamily;
import Frames.FontSize;
import Frames.MainFrame;
import TabPane.TextPanel;




public class Reader {
	
	//array that holds the saved file paths from "config.json"
	private static Object[] savedPaths;
	
	//json array that loads in the paths from "config.json"
	private JSONArray paths;
	
	//Variable to be used to add to file names if they all ready are present
	private int incre = 1;
	
	//object of the TextArea class to be used when loading files into tabs
	private TextArea textArea;
	
	private MainFrame mainFrame;
	
	
	//object to read (parse) the data from "config.json"
	private JSONParser reader;
	
	public Reader(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
		reader = new JSONParser();
		
		try{
			Object obj = reader.parse(new FileReader("src/json/config.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			
			//try-catch() block for retrieving background color
			//if any of them are null, will set background to white
			try{
				long bgRed =  (long) jsonObject.get("bgRed");
				long bgGreen = (long) jsonObject.get("bgGreen");
				long bgBlue = (long) jsonObject.get("bgBlue");
			
				Color bg = new Color((int)bgRed, (int)bgGreen, (int)bgBlue);
				
				//sets the variable "bg" in MmainFrame to whatever was loaded from the json file
				MainFrame.setBg(bg);
				//sets the variable "curBg" in MainFrame to the background color above
				MainFrame.setCurBg(bg);
				
			}catch(NullPointerException e){
				//if the contents of the config file was null
				//for the background color, make it white
				MainFrame.setBg(Color.WHITE);
				MainFrame.setCurBg(Color.WHITE);
			}
			
			//try-catch() block for foreground colors
			try{
				long fgRed = (long) jsonObject.get("fgRed");
				long fgGreen = (long) jsonObject.get("fgGreen");
				long fgBlue = (long) jsonObject.get("fgBlue");
				
				Color fg  = new Color((int)fgRed, (int)fgGreen, (int)fgBlue);
				MainFrame.setFg(fg);
				MainFrame.setCurFg(fg);
				
			}catch(NullPointerException e){
				//if the contents in the config file was empty 
				//for the foreground color, make it black
				MainFrame.setFg(Color.BLACK);
				MainFrame.setCurFg(Color.BLACK);
			}
			
			//try-catch() block for font size
			try{
				long fontSize = (long) jsonObject.get("fontSize");
				FontSize.setSizeOfFont((int) fontSize);
			}catch(Exception e1){
				FontSize.setSizeOfFont(16);
			}
			
			//try-catch() block for font family
			try{
				String fontFamily = (String) jsonObject.get("fontFamily");
				FontFamily.setFontFamily(fontFamily);
			}catch(Exception e1){
				FontFamily.setFontFamily("Century Gothic");
			}
			
			
				//array for the saved file paths
				paths = (JSONArray) jsonObject.get("paths");
				Iterator<String> iterator = paths.iterator();
				
				//condition remains true as long as iterator has
				//something else to read
				while(iterator.hasNext()){
					//gets the file path from the iterator
					Path p = Paths.get(iterator.next());
					
					//label to be used for the tab name
					JLabel fileName = new JLabel();
					
					//sets the text of the tab title to the file name from the path object
					fileName.setText(p.getFileName().toString());
					
					//executed code if a tab with the same file name does not already exist
					if(MainFrame.getPane().indexOfTab(fileName.getText()) == -1){
						
						//adds a tab tothe tab pane with the file name and textArea object
						MainFrame.getPane().add(p.getFileName().toString(), textArea = new TextArea());
						
						//gets the index of the tab to be used later when adding it to savedPaths ArrayLlist & colors hashmap
						int index = MainFrame.getPane().indexOfTab(p.getFileName().toString());
						
						//adds a textPanel object to the tab at the given index
						MainFrame.getPane().setTabComponentAt(index, new TextPanel(MainFrame.getPane(), p.getFileName().toString(), mainFrame));
						
						//adds the path to the colors hashmap
						MainFrame.getColors().put(index, textArea);
						
						//adds the path to the ArrayList
						MainFrame.getSavedPaths().add(index, p.toString());
					}else{
						//executed code if there is already a file with the name in the tabbepane
						MainFrame.getPane().add(p.getFileName().toString() + incre, textArea = new TextArea());
						int index = MainFrame.getPane().indexOfTab(fileName.getText() + incre);
						incre++;
						MainFrame.getPane().setTabComponentAt(index, new TextPanel(MainFrame.getPane(), p.getFileName().toString(), mainFrame));
						
						MainFrame.getColors().put(index, textArea);
						MainFrame.getSavedPaths().add(index, p.toString());
					}
					
					try{
						//method passed in that gets the file path
						Scanner scan = new Scanner(new FileReader(p.toString()));
						//condition stays true while there is still something to read
						while (scan.hasNext())
							//append the line to the text area
							textArea.getTextArea().append(scan.nextLine() + "\n");
					} catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
					TextPanel.setClear(true);
					mainFrame.update();
				}
				
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
	}
}
