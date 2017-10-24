package commands;

import java.io.IOException;

import model.Model;
import model.data.Level;
import model.data.LoadReceiver;


public class LoadfFileCommand extends SokobanCommand {
	
	
	private Level lvl;
	//private Model model;
	
	public LoadfFileCommand(Model model){
		super(model);
	}

	public void execute() {
		
		String filepath=params.get(0);
		model.loadlLevel(filepath);
		
	}

	public Level getLvl() {
		return lvl;
	}

	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}
	

}
