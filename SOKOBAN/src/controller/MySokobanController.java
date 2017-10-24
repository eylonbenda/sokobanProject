package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import commands.SokobanCommand;
import commands.SolveCommmand;
import commands.DisplayLevelCommand;
import commands.ExitCommand;
import commands.LoadfFileCommand;
import commands.MoveCommand;
import commands.SaveFileCommand;
import model.Model;
import view.View;

public class MySokobanController implements Observer {

	private Model model;
	private View view;
	private Controller controller;
	private Map<String, SokobanCommand> commmands;

	public MySokobanController(Model model, View view) {

		this.model = model;
		this.view = view;
		controller = new Controller();
		commmands = new HashMap<String, SokobanCommand>();
		commmands.put("move", new MoveCommand(model));
		commmands.put("Load", new LoadfFileCommand(model));
		commmands.put("Save", new SaveFileCommand(model));
		commmands.put("Exit", new ExitCommand(controller));
		commmands.put("Display", new DisplayLevelCommand(model, view));
		commmands.put("solve", new SolveCommmand(model));

		controller.start();

	}

	@Override
	public void update(Observable o, Object arg) {

		if (arg instanceof String) {

			SokobanCommand solveCommand = commmands.get(arg);
			if (solveCommand != null) {

				controller.insertCommand(solveCommand);
			}

		} else {

			@SuppressWarnings("unchecked")
			List<String> params = (List<String>) arg;
			String commandKey = params.remove(0);
			
			SokobanCommand c = commmands.get(commandKey);
			if (c == null) {
				view.displayError("Command " + commandKey + " not found");
				return;
			}

			c.setParams(params);
			controller.insertCommand(c);

		}

	}

}
