package model.data;

import java.beans.XMLDecoder;
import java.io.IOException;
import java.io.InputStream;


public class MyXMLLevelLoader implements LevelLoader {

	@Override
	public Level loadlevel(InputStream in) throws IOException, ClassNotFoundException {
		
		Level level;
		
		XMLDecoder dcoder=new XMLDecoder(in);
		level=(Level) dcoder.readObject();
		dcoder.close();
		
		return level;
	}

}
