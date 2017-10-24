package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class SokobanHandlerClient extends Observable implements ClientHandler  {

	@Override
	public void handleClient(BufferedReader inFromClient, PrintWriter outToClient) {
		
		
		String line=null;
		
		while(true){
			
			outToClient.println("enter command:");
			try {
				line=inFromClient.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String[] arr = line.split(" ");
			List<String> params = new LinkedList<String>();
			
			
			for (String s: arr) {
				params.add(s);
			}
			
			setChanged();
			notifyObservers(params);
			
		}
		

	}

}
