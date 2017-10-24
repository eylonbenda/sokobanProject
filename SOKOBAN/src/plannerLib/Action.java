package plannerLib;

import java.util.List;

/**
 * 
 * 
 * @author eylon ben david
 * 
 */
public class Action extends Predicate {

	Clause preconditions,effects;
	
	public Action(String type, String id, String value) {
		super(type, id, value);
		
	}

	public Clause getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(Clause preconditions) {
		this.preconditions = preconditions;
	}

	public Clause getEffects() {
		return effects;
	}

	public void setEffects(Clause effects) {
		this.effects = effects;
	}
	
	
}
