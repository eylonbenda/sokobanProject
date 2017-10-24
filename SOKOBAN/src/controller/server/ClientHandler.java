package controller.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public interface ClientHandler {
	
	void handleClient(BufferedReader inFromClient, PrintWriter outToClient);

}
