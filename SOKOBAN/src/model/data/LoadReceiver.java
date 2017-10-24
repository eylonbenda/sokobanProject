package model.data;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * 
 * 
 * @author eylon ben david
 * 
 *
 */
public class LoadReceiver {
	
	private HashMap<String, LevelLoader> levelCreatorFactory;
	
	public LoadReceiver(){
		levelCreatorFactory=new HashMap<String,LevelLoader>();
		levelCreatorFactory.put("txt", new MyTextLevelLoader());
		levelCreatorFactory.put("obj", new MyObjectLevelLoader());
		levelCreatorFactory.put("xml", new MyXMLLevelLoader());
	}
	
	
	public Level Action(String filepath) throws ClassNotFoundException, IOException{
		
		String temp;
		int c=filepath.indexOf('.', 0);
		temp=filepath.substring(c+1, filepath.length());
		
		Level level=null;
		
		LevelLoader loader;
		loader=levelCreatorFactory.get(temp);
		BufferedInputStream bi=new BufferedInputStream(new FileInputStream(filepath));
		level=loader.loadlevel(bi);
		
		//String levelname=filepath.substring(41,c);//add level name according to file name.
		//level.setLevelName(levelname);
		
		return level;
		
		
	}

}
