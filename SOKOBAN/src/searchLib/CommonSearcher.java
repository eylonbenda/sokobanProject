package searchLib;

import java.util.LinkedList;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {
	

	//protected PriorityQueue<State> openList;
	protected int evaluatedNodes;
	
	public CommonSearcher() {
		//openList=new PriorityQueue<State>();
		evaluatedNodes=0;
	}
	/*protected State popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}*/
	
	@Override
	public abstract Solution search(Searchable<T> s);
	
	@Override
	public int getNumberOfNodesEvaluated() {
	return evaluatedNodes;
	}
	
	public  Solution backTrace(State<T> goalState){

		LinkedList<Action> actions = new LinkedList<Action>();
		State<T> currState = goalState;
		while (currState.getCameFrom() != null) {			
			actions.addFirst(currState.getAction());
			currState = currState.getCameFrom();
		}

		Solution sol = new Solution();
		sol.setActions(actions);
		return sol;
	}

}
