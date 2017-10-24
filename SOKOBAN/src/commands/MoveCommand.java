package commands;


import model.Model;
import model.data.Level;
import model.object_data.Square;
import model.policy.Policy;

public class MoveCommand extends SokobanCommand {
	
	//private Policy p;
	//private Model m;
	

	
	public  MoveCommand(Model model) {
		super(model);
		
	}
 
	@Override
	public void execute() {
		
		String direction=params.get(0);
		model.move(direction);
		
	
	}


	

}
