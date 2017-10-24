package searchLib;

import java.io.Serializable;

public class Action implements Serializable {
	
	String nameAction;
	
	public Action(String name) {
		
		this.nameAction=name;
	}
	
	public Action() {
		// TODO Auto-generated constructor stub
	}

	public String getNameAction() {
		return nameAction;
	}

	public void setNameAction(String nameAction) {
		this.nameAction = nameAction;
	}
	
	@Override
	public boolean equals(Object o){
		Action action =(Action)o;
		return this.nameAction.equals(o);
	}
	
	
	@Override
	public int hashCode(){
		return this.nameAction.hashCode();
	}
	
	
	

}
