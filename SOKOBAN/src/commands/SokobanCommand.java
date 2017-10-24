package commands;

import java.util.List;

import controller.Controller;
import model.Model;
import view.View;

public abstract class SokobanCommand implements Commmand {
	
  protected List<String> params;
  protected Model model;
  protected View view;
  protected Controller controller;
  
  
  public SokobanCommand(Controller controller) {
	this.controller=controller;
}
  
  
  public SokobanCommand(Model model) {
	this.model=model;
}
  
  public SokobanCommand(Model model,View view) {
	this.model=model;
	this.view=view;
}


	
	public void setParams(List<String> params) {
		this.params = params;
	}

	public abstract void execute() ;
	
	
	//public abstract void execute();

}
