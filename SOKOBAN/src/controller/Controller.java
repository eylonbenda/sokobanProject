package controller;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import commands.SokobanCommand;
import commands.Commmand;

/**
 * controller class
 * @author eylon ben david
 *
 */
public class Controller {
	
	private BlockingQueue<Commmand> queue;
	private boolean isStopped=false;
	
	public Controller() {
		queue=new ArrayBlockingQueue<Commmand>(10); 
	}
	
	
	public void insertCommand(Commmand command){
		
		try {
			queue.put(command);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	
	public void start(){
		Thread thread=new Thread(new Runnable() {
			@Override
			public void run() {
				while(!isStopped){
					try {
						Commmand cmd = queue.poll(1, TimeUnit.SECONDS);
						if (cmd != null)
							cmd.execute();						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		thread.start();
		
	}
	
	
	public void stop(){
		isStopped=true;
	}
	
	
}
