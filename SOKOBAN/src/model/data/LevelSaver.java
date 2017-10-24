package model.data;

import java.io.IOException;

public interface LevelSaver {
	
	void save(String filepath,Level level) throws IOException;

}
