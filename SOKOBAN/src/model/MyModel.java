package model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import Client.ClientSokoban;
import controller.MySokobanController;
import model.data.Level;
import model.data.LoadReceiver;
import model.data.SaveReceiver;
import model.policy.MySokobanPolicy;
import model.policy.Policy;
import searchLib.Action;

/**
 * 
 * 
 * @author eylon ben david
 * 
 *
 */
public class MyModel extends Observable implements Model {

	private MySokobanPolicy policy;
	private LoadReceiver loadlevel;
	private SaveReceiver savelevel;
	private Level level;
	private ClientSokoban clientSokoban;
	private List<Action> listMoves;

	public MyModel() {

		loadlevel = new LoadReceiver();
		savelevel = new SaveReceiver();

	}

	@Override
	public Level getLevel() {

		return this.level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public void saveLevel(String filepath) {

		savelevel.SaveAction(filepath, level);

	}

	@Override
	public void loadlLevel(String filepath) {

		Level level = null;
		loadlevel = new LoadReceiver();
		try {
			level = loadlevel.Action(filepath);
			
			int index1 = filepath.indexOf(".");
			int index2 = filepath.indexOf("Level");
			String name = filepath.substring(index2, index1);
			
			level.setLevelName(name);
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.level = level;

		this.setChanged();
		List<String> params = new LinkedList<String>();
		params.add("Display");
		this.notifyObservers(params);

	}

	@Override
	public void exitLevel() {
		System.out.println("exiting...");
		System.exit(1);

	}

	@Override
	public void move(String direction) {

		this.policy = new MySokobanPolicy(level);

		switch (direction) {
		case "up":
			policy.moveUp();
			break;
		case "down":
			policy.moveDown();
			break;
		case "right":
			policy.moveRight();
			break;
		case "left":
			policy.moveLeft();
			break;
		}
		if (level.checkIfWin())
			level.setWin(true);

		this.setChanged();
		List<String> params = new LinkedList<String>();
		params.add("Display");
		this.notifyObservers(params);

	}

	@Override
	public void solveLevel() {
		//TODO: connect client to serverSolution asking for solution to the level
		
		
		System.out.println("connect to server...");
		 listMoves = null;
		

		 ClientSokoban client = new ClientSokoban(level);
		 listMoves = client.start("127.0.0.1",3434);
		 

		 Thread thread = new Thread(new Runnable() {

			 @Override
			 public void run() {
				 // TODO Auto-generated method stub

				if (listMoves != null) {
					
					for (Action action : listMoves) {

						String[] arr = action.getNameAction().split(" ");
						List<String> params = new LinkedList<String>();

						for (String s : arr) {
							params.add(s);
						}
						try {
							executeActions(params);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}

		});
		thread.start();
	}


	
	
	private void executeActions(List<String> params) throws InterruptedException{
		
		this.setChanged();
		this.notifyObservers(params);
		Thread.sleep(100);
	}
}
