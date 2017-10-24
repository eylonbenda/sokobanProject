package Client;

import java.io.IOException;


import model.data.Level;
import model.data.LoadReceiver;

/**
 * 
 * 
 * @author eylon ben david
 * 
 */
public class Test {

	public static void main(String[] args) {
	
		
		LoadReceiver load = new LoadReceiver();
		Level lvl = null;
		ClientSokoban client = null;
		try {
			 lvl = load.Action("levels/level1.txt");
			 //lvl.setLevelName("levels/level1");
			  client = new ClientSokoban(lvl);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		client.start("127.0.0.1", 6565);

	}

}
