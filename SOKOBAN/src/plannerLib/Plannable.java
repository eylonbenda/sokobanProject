package plannerLib;

import java.util.Set;

public interface Plannable {
	
	Clause getGoal();
	Clause getKnowledgebase();
	Set<Action> getsatisfyingActions(Predicate top);
	Action getsatisfyingAction(Predicate top);

}
