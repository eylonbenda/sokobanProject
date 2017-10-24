package view;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import model.data.Level;




public class NewCli extends Observable implements View {

	
	
	@Override
	public void displayLevel(Level level) {
		System.out.println(level);
		
	}

	@Override
	public void start() {
		
		Scanner scanner = new Scanner(System.in);
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					
					
					System.out.print("Enter command: \n");
					String commandLine = scanner.nextLine();
					
					String[] arr = commandLine.split(" ");
					List<String> params = new LinkedList<String>();
					
					for (String s: arr) {
						params.add(s);
					}
					
					setChanged();
					notifyObservers(params);
					
					
					
					/*if (commandLine.equals("exit"))
						break;*/
				}				
			}
		});
		thread.start();		
		
		
	}

	@Override
	public void displayError(String msg) {
		System.out.println("Error:" + msg);
		
	}

	

}
