package model;

import javafx.beans.Observable;
import model.data.Level;
import commands.MoveCommand;


public interface Model {
	
	
	public void saveLevel(String filepath);
	
	public void loadlLevel(String filepath);
	
	public  void exitLevel();
	
	public void move(String direction);
	
	public void solveLevel();
	
	 public Level getLevel();

}
