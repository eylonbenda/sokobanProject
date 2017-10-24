package model.data;

import java.io.IOException;

import SerializationUtil.SerializationUtil;

/**
 * 
 * 
 * @author eylon ben david
 * 
 *
 */
public class MyObjectLevelSaver implements LevelSaver {

	@Override
	public void save(String filepath, Level level) throws IOException {
		
		SerializationUtil.serialize(level, filepath);
		
	}

}
