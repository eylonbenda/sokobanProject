package view;



import javafx.beans.Observable;
import model.data.Level;

public interface View  {
	
	public void displayLevel(Level level);
	
	public void displayError(String msg);
	
	public void start();
	
  
}
