package model.data;

import java.io.IOException;
import java.io.InputStream;

public interface LevelLoader  {
	/**
	 * <h3>Javadoc - LevelLoader</h3></br>
 1) The separation was made in accordance with the principle of single responsibility (SOLID). We did this by creating the interface LevelLoader that load the information for Level class.
 </br></br>
 2) Because if another programmer wants to add loads of other file we can do this by creating a class that implements the LevelLoader interface.
 </br></br>
 3) If the user wants to load any type of level file he can do this by injecting the file of the same type to the appropriate level loading class.
 </br></br>
 4)The designation of inputstream is to stream information into our application from a file on your computer, it gives us easy access and lay deal with regular files and thus we do not need to check if the string received is indeed a standard string and if the file exists. In addition, this way we can ensure that every file we want to take care of the future of our department can access it using inputstream easy and convenient manner.
	 */
	Level loadlevel(InputStream in) throws IOException, ClassNotFoundException;

}
