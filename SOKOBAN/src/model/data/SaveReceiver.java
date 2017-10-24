package model.data;

import java.io.IOException;
import java.util.HashMap;

/**
 * 
 * 
 * @author eylon ben david
 * 
 *
 */
public class SaveReceiver  {
	
	private HashMap<String, LevelSaver> kindlevel;
	
	
	public  SaveReceiver(){
		
		kindlevel=new HashMap<String,LevelSaver>();
		kindlevel.put("txt", new MyTextLevelSaver());
		kindlevel.put("obj", new MyObjectLevelSaver());
		kindlevel.put("xml", new MyXmlLevelSaver());
		
	}
	
	
	public void SaveAction(String filepath,Level lvl){
		
		String temp;
		int c=filepath.indexOf('.', 0);
		temp=filepath.substring(c+1, filepath.length());
		
		LevelSaver saverLevel=kindlevel.get(temp);
		try {
			saverLevel.save(filepath, lvl);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	

}
