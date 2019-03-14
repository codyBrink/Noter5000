package json;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Frames.FontFamily;
import Frames.FontSize;
import Frames.MainFrame;

//using json to store config data, colors, paths, etc
public class Writer {

	//json object to write data
	JSONObject writer;
	
	//json array to be populated by the "paths" ArrayList
	JSONArray pathList;
	
	public Writer(){
		writer = new JSONObject();
		
		//data for the background color
		int bgRed = MainFrame.getBg().getRed();
		int bgGreen = MainFrame.getBg().getGreen();
		int bgBlue = MainFrame.getBg().getBlue();
		
		writer.put("bgRed", new Integer(bgRed));
		writer.put("bgGreen", new Integer(bgGreen));
		writer.put("bgBlue", new Integer(bgBlue));
		//end of background color data
		
		//data for the text color (ForeGround)
		int fgRed = MainFrame.getFg().getRed();
		int fgGreen = MainFrame.getFg().getGreen();
		int fgBlue = MainFrame.getFg().getBlue();
		
		writer.put("fgRed", new Integer(fgRed));
		writer.put("fgGreen", new Integer(fgGreen));
		writer.put("fgBlue", new Integer(fgBlue));
		//end of Foreground color data
		
		//data for font size
		int fontSize = FontSize.getSizeOfFont();
		
		writer.put("fontSize", fontSize);
		//end of font size
		
		//data for font family
		String fontFamily = FontFamily.getFontFamily();
		
		writer.put("fontFamily", fontFamily);
		//end of font family
		
		
		//array to keep track of our opened files
		
		if(MainFrame.getSavedPaths().size() == -1){
			
		}else{
			pathList = new JSONArray();
			
			for(int x = 0; x < MainFrame.getSavedPaths().size(); x++){
				pathList.add(MainFrame.getSavedPaths().get(x));
			}
			
			
			
			writer.put("paths", pathList);
		}
		
		try {
			
			FileWriter file = new FileWriter("src/json/config.json");
			file.write(writer.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
