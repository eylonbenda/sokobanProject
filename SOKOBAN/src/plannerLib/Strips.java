package plannerLib;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;



public class Strips implements Planner {

	private Plannable plannable;

	/*
	 * (non-Javadoc)
	 * @see strips.Planner#plan(strips.Plannable)
        Push goal into the stack
       Repeat until stack is empty
       If top is a multipart goal, push unsatisfied sub-goals into the stack V
      If top is a single unsatisfied goal, V
      Replace it with an action that satisfies the goal V
      Push the action preconditions into the stack V
      If top is an action, V
      Pop it from the stack V
     Simulate its execution and update the knowledge base with its effects V
      Add the action to the plan V
      If top is a satisfied goal, pop it from the stack V
	 */
	@Override
	public List<Action> plan(Plannable plannable) {
		LinkedList<Action> plan=new LinkedList<>();
		this.plannable=plannable;
		Stack<Predicate> stack=new Stack<>();
		stack.push(plannable.getGoal());
		while(!stack.isEmpty()){
			Predicate top=stack.peek();
			if(! (top instanceof Action)){
				if(!plannable.getKnowledgebase().satisfies(top)){ // unsatisfied
					if(top instanceof Clause){ // multipart
						Clause c=(Clause)top; 
						for(Predicate p : c.setPredicates){
							stack.push(p);
						}
					}else{ // single and unsatisfied
						stack.pop();
						Action action=plannable.getsatisfyingAction(top);
						stack.push(action);
						stack.push(action.preconditions);
						
					
					}
				}else{
					stack.pop();
				}
			}else{ // top is an action at the top of the stack
				stack.pop();
				Action a=(Action)top;
				plannable.getKnowledgebase().update(a.effects);
				plan.add(a);
			}
		}
		return plan;
	}
}
