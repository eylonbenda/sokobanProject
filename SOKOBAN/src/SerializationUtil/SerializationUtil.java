package SerializationUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {
	

	/*public static Object deserialize(InputStream is) throws IOException,
	ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(is);
    Object obj = ois.readObject();
    ois.close();
   return obj;
}*/
	
	public static Object deserialize(InputStream in) throws IOException,
	ClassNotFoundException {
       //FileInputStream fis = new FileInputStream(in);
      ObjectInputStream ois = new ObjectInputStream(in);
      Object obj = ois.readObject();
      ois.close();
      return obj;
	}

// serialize the given object and save it to file
    public static void serialize(Object obj, String fileName)
	  throws IOException {
       FileOutputStream fos = new FileOutputStream(fileName);
       ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);

        fos.close();
	}

}
