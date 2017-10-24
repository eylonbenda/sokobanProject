package model.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import SerializationUtil.SerializationUtil;
import model.object_data.Square;

/**
 * 
 * 
 * @author eylon ben david
 * 
 *
 */
public class MyObjectLevelLoader implements LevelLoader {
	
	

	@Override
	public Level loadlevel(InputStream in) throws IOException, ClassNotFoundException {
		
		
		Level level;
		level=(Level) SerializationUtil.deserialize(in);
		
		return level;
	}

}
