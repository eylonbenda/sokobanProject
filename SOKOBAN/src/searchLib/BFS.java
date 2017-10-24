package searchLib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/*
 * OPEN = [initial state] // a priority queue of states to be evaluated
 * CLOSED = [] // a set of states already evaluated
while OPEN is not empty
do
   1. n  dequeue(OPEN) // Remove the best node from OPEN
   2. add(n,CLOSED) // so we won’t check n again
   3. If n is the goal state,
         backtrace path to n (through recorded parents) and return path.
   4. Create n's successors.
   5. For each successor s do:
      a. If s is not in CLOSED and s is not in OPEN:
        i. update that we came to s from n
        ii. add(s,OPEN)
      b. Otherwise, if this new path is better than previous one
        i. If it is not in OPEN add it to OPEN.
        ii. Otherwise, adjust its priority in OPEN

 */

public class BFS<T> extends CommonSearcher<T> {

	private PriorityQueue<State<T>> openList;
	private HashSet<State<T>> closedSet;
	
	
	
	public BFS() {
		this.openList = new PriorityQueue<>();
		this.closedSet = new HashSet<>();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Solution search(Searchable<T> s) {
		
		this.openList.add(s.getInitialState());
		State<T> currState = s.getInitialState();
		currState.setCameFrom(null);


		while(openList.size()>0){

			 currState = openList.poll();
			this.evaluatedNodes++;
			closedSet.add(currState);

			if(currState.equals(s.getGoalState()))
				return backTrace(currState);// private method, back traces through the parents

			HashMap<Action, State<T>> successors = s.getAllPossibleMoves(currState);//however it is implemented

			for(Action action : successors.keySet()){

				State<T> state = successors.get(action);

				if(!closedSet.contains(state)) {

					if(!openList.contains(state)){

						state.setCameFrom(currState);
						state.setAction(action);
						//state.setCost(currState.getCost()+1);
						openList.add(state);
					}

					else{

						State<T> temp = null;
						for(State<T> state2:openList){
							if(state2.equals(state)){
								temp = state2;
								break;
							}
						}

						if(temp != null){

							if(state.getCost() < temp.getCost()){
								openList.remove(temp);
								state.setCameFrom(currState);
								state.setAction(action);
								openList.add(state);

							}

						}
					}
				}


			}
		}
		return null;
	}
}

