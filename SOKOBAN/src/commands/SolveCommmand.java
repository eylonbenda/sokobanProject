package commands;


import model.Model;


public class SolveCommmand extends SokobanCommand {
	
	

	public SolveCommmand(Model model) {
		super(model);
		
	}

	@Override
	public void execute() {
		
		model.solveLevel();

	}

}
