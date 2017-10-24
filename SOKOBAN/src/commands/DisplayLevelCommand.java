package commands;

import model.Model;
import model.data.Level;
import view.View;

public class DisplayLevelCommand extends SokobanCommand {

	//private Level lvl;

	 public DisplayLevelCommand(Model model,View view){
		super(model,view);
		
	}
	@Override
	public void execute() {
		Level level=null;
		level=model.getLevel();
		view.displayLevel(level);
	}
	
}
