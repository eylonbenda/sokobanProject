package searchLib;


import java.util.LinkedList;


public class Solution {
	
	private LinkedList<Action> actions;
	
	public Solution(LinkedList<Action> actions) {
		this.actions = actions;
	}

	public Solution() {
		// TODO Auto-generated constructor stub
	}

	public LinkedList<Action> getActions() {
		return actions;
	}

	public void setActions(LinkedList<Action> actions) {
		this.actions = actions;
	}
	
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		for(Action action: actions)
			sb.append(action.getNameAction()).append("\n");
		
		return sb.toString();
	}
	

}
