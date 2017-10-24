package searchLib.searchables;

import java.util.ArrayList;
import java.util.HashMap;

import model.data.Level;
import model.object_data.Position;
import model.object_data.Square;
import model.object_data.SquareTarget;
import searchLib.Action;
import searchLib.Searchable;
import searchLib.State;

// calculate the path from the box position to the target position
public class SearchableBoxTarget implements Searchable<Position> {

	private Position boxPos;
	private Position targetPos;
	private Level level;
	private ArrayList<ArrayList<Square>> map;

	public SearchableBoxTarget(Level level, Position startPos, Position goalPos) {

		this.level = level;
		this.map = level.getLevelmap();
		boxPos = startPos;
		targetPos = goalPos;

	}

	@Override
	public State<Position> getInitialState() {

		/*
		 * ArrayList<Square> listBox = new ArrayList<>(); listBox =
		 * level.getBoxesPosition(); State<Position> state = null; if (listBox
		 * != null) { for (Square square : listBox) { boxPos =
		 * square.getPosition(); state = new State<Position>(boxPos);
		 * listBox.remove(square); } }
		 * 
		 * return state;
		 */

		State<Position> startState = new State<Position>(boxPos, 0);
		return startState;
	}

	@Override
	public State<Position> getGoalState() {

		State<Position> goalState = new State<Position>(targetPos, 0);
		return goalState;

	}

	@Override
	public HashMap<Action, State<Position>> getAllPossibleMoves(State<Position> state) {

		HashMap<Action, State<Position>> actions = new HashMap<>();
		int row = state.getState().getX();
		int col = state.getState().getY();

		if (((map.get(row - 1).get(col).toString() == " " || map.get(row - 1).get(col).toString() == "o" || map.get(row - 1).get(col).toString() == "A"))
				&& ((map.get(row + 1).get(col).toString() == " ") || (map.get(row + 1).get(col).toString() == "o" || map.get(row + 1).get(col).toString() == "A"))) {
				
			Position pos1 = new Position(row-1, col);
			Position pos2 = new Position(row+1, col);
			actions.put(new Action("move up"), new State<Position>(pos1,state.getCost()+1));
			actions.put(new Action("move down"), new State<Position>(pos2,state.getCost()+1));
			
		}
		
		if (((map.get(row).get(col+1).toString() == " " || map.get(row).get(col+1).toString() == "o" || map.get(row - 1).get(col).toString() == "A" ))
				&& ((map.get(row ).get(col-1).toString() == " ") || (map.get(row).get(col-1).toString() == "o" || map.get(row - 1).get(col).toString() == "A" ))){
			
			Position pos3 = new Position(row, col-1);
			Position pos4 = new Position(row, col+1);
			actions.put(new Action("move right"), new State<Position>(pos4,state.getCost()+1));
			actions.put(new Action("move left"), new State<Position>(pos3,state.getCost()+1));
			
		}
		return actions;
	}

}
