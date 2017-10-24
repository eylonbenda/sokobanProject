package model.data;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyXmlLevelSaver implements LevelSaver {

	@Override
	public void save(String filepath, Level level) throws IOException {
		// TODO Auto-generated method stub
		XMLEncoder x=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filepath)));
		x.writeObject(level);
		x.close();

	}

}
