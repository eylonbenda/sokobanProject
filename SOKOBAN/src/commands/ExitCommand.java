package commands;



import controller.Controller;
import model.Model;

public class ExitCommand extends SokobanCommand {
	
	
	
	public ExitCommand(Controller controller) {
		super(controller);
	}

	@Override
	public void execute() {
		
		this.controller.stop();
		System.out.println("exiting...");
		System.exit(1);
		
	}
	

}
