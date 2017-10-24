package searchLib.searchables;

import java.util.ArrayList;
import java.util.HashMap;

import model.data.Level;
import model.object_data.Position;
import model.object_data.Square;
import model.policy.MySokobanPolicy;
import searchLib.Action;
import searchLib.Searchable;
import searchLib.State;

public class SearchablePlayerBox implements Searchable<Position> {

	private Level level;
	private Position playerPos;
	private Position boxPos;
	private MySokobanPolicy policy;
	

	public SearchablePlayerBox(Level level,Position startPos,Position targetPos) {
		this.level = level;
		playerPos = startPos;
		boxPos = targetPos;

	}

	@Override
	public State<Position> getInitialState() {

		if (level != null) {
			State<Position> state = new State<Position>(playerPos,0);
			return state;
		}
		return null;

	}

	@Override
	public State<Position> getGoalState() {
		
		State<Position> goalState = new State<Position>(boxPos,0);	
		return goalState;
	}
	
	@Override
	public HashMap<Action, State<Position>> getAllPossibleMoves(State<Position> state) {

		HashMap<Action, State<Position>> data = new HashMap<>();
		ArrayList<ArrayList<Square>> map = level.getLevelmap();
		
		int row = state.getState().getX();
		int col = state.getState().getY();

		Position upPlayer = new Position(row - 1, col);
		Position downPlayer = new Position(row + 1, col);
		Position rightPlayer = new Position(row, col + 1);
		Position leftPlayer = new Position(row, col - 1);

		if(map.get(row-1).get(col).toString() == " " ||  map.get(row-1).get(col).toString() == "o" )
			data.put(new Action("move up"), new State<Position>(upPlayer,state.getCost()+1));
		
		if(map.get(row+1).get(col).toString() == " " ||  map.get(row+1).get(col).toString() == "o" )
			data.put(new Action("move down"), new State<Position>(downPlayer,state.getCost()+1));
		
		if(map.get(row).get(col+1).toString() == " " ||  map.get(row).get(col+1).toString() == "o" )
			data.put(new Action("move right"), new State<Position>(rightPlayer,state.getCost()+1));
		
		if(map.get(row).get(col-1).toString() == " " ||  map.get(row).get(col-1).toString() == "o" )
			data.put(new Action("move left"), new State<Position>(leftPlayer,state.getCost()+1));

		return data;
	}

}
