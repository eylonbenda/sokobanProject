package plannerLib;

import java.util.HashSet;
import java.util.Iterator;
/**
 * 
 * @author eylon ben david
 *
 */
public class Clause extends Predicate {

	protected HashSet<Predicate> setPredicates;

	private void updateDescription() {
		value = "{";
		for (Predicate p : setPredicates) {
			value += p.toString() + " & ";
		}
		value += "}";
	}

	public Clause(Predicate... predicates) {
		super("And", " ", " ");
		if (predicates != null) {

			setPredicates = new HashSet<>();
			for (Predicate p : predicates) {
				setPredicates.add(p);
			}
			updateDescription();
		}
	}

	@Override
	public String toString() {
		
		return super.toString();
	}

	public void update(Clause effect) {

		for (Iterator<Predicate> p1 = effect.setPredicates.iterator(); p1.hasNext();) {
			
			Predicate temp1 = p1.next();
			for (Iterator<Predicate> p2 = setPredicates.iterator(); p2.hasNext();) {
				
				Predicate temp2 = p2.next();
				if (temp1.contradicts(temp2)){
					//setPredicates.remove(p2);
					p2.remove();
				}
				

			}
			
		}
		setPredicates.addAll(effect.setPredicates);
		updateDescription();
	}
	
	
	public void add(Predicate p){
		if(setPredicates==null)
			setPredicates=new HashSet<>();		
		this.setPredicates.add(p);
		updateDescription();
	}
	
	@Override
	public boolean satisfies(Predicate p){
		for(Predicate pr : setPredicates)
			if(pr.satisfies(p))
				return true;
		return false;
	}
	
	public boolean satisfies(Clause clause){
		for(Predicate p : clause.setPredicates){
			if(!satisfies(p))
				return false;
		}
		return true;
	}

	public HashSet<Predicate> getSetPredicates() {
		return setPredicates;
	}

	public void setSetPredicates(HashSet<Predicate> setPredicates) {
		this.setPredicates = setPredicates;
	}
	
	

}
