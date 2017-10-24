package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import model.data.Level;
import searchLib.Action;
/**
 * 
 * 
 * @author eylon ben david
 * 
 *
 */
public class ClientSokoban {

	Socket theServer;
	ObjectInputStream inFromServer;
	ObjectOutputStream outToServer;
	List<Action> movesList;
	Level level;

	public ClientSokoban(Level level) {

		this.level = level;

	}

	
	

	public List<Action> start(String ip, int port) {

		try {
			theServer = new Socket(ip, port);
			System.out.println("connected to server");

			outToServer = new ObjectOutputStream(theServer.getOutputStream());
			inFromServer = new ObjectInputStream(theServer.getInputStream());

			Thread thread = new Thread(new Runnable() {

				@SuppressWarnings("unchecked")
				@Override
				public void run() {

					try {
						outToServer.writeObject(level);
						movesList = (List<Action>) inFromServer.readObject();
						for ( Action action: movesList) {
							System.out.println(action.getNameAction());
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

				}
			});
			thread.start();
			thread.join();
			
			outToServer.close();
			inFromServer.close();
			theServer.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movesList;
	}
	
	

}
