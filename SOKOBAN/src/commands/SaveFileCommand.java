package commands;
import model.Model;
import model.data.Level;


public class SaveFileCommand extends SokobanCommand {
	
	
	//private Model model;
	
	 public SaveFileCommand(Model model) {
		
		super(model);
	}
	

	@Override
	public void execute() {
		
		String filepath=params.get(0);
		model.saveLevel(filepath);
		System.out.println("level has been saved");
		
	}

}
