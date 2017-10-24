package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;

public class MyServer {
	
	private int port;
	private ClientHandler ch;
	private boolean iStopped;
	private ExecutorService executor;
	
	
	public MyServer(int port,ClientHandler ch) {
		this.port=port;
		this.ch=ch;
		this.iStopped=false;
		
	}
	
	public void runServer() throws IOException{
		
		
			ServerSocket server=new ServerSocket(port);
			System.out.println("server is alive and waiting for clients");
			
			while(!iStopped){
			try{
			Socket aClient=server.accept(); //blocking call
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
					ch.handleClient(new BufferedReader(new InputStreamReader(aClient.getInputStream())), new PrintWriter(aClient.getOutputStream()));
					aClient.getInputStream().close();
					aClient.getOutputStream().close();
					aClient.close();
					server.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}).start();
		}catch (SocketTimeoutException e){
			e.printStackTrace();
			}
			
	   } 
	
	 }
	
	
	public void start(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					runServer();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	
		
	}
	
	public void stop(){
		this.iStopped=true;
	}
		
	}


