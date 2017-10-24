package searchLib;

import java.util.HashMap;

public class MazeDomain<T> implements Searchable<Position> {
	
	
	Maze game;
	
	public MazeDomain(Maze gaMaze) {
		this.game=gaMaze;
	}
	

	@Override
	public HashMap<Action, State<Position>> getAllPossibleMoves(State<Position> state) {
		
		HashMap<Action, State<Position>> map = new HashMap<>();
		
		Position currPos = state.getState();
		
		Position upPlayer = new Position(0, 0);
		upPlayer.setCol(currPos.getCol());
		upPlayer.setRow(currPos.getRow()-1);
		Position downPlayer = new Position(0, 0);
		downPlayer.setCol(currPos.getCol());
		downPlayer.setRow(currPos.getRow()+1);
		Position rightPlayer = new Position(0, 0);
		rightPlayer.setCol(currPos.getCol()+1);
		rightPlayer.setRow(currPos.getRow());
		Position leftPlayer = new Position(0, 0);
		leftPlayer.setCol(currPos.getCol()-1);
		leftPlayer.setRow(currPos.getRow());
		
		if(game.isEmptyPosition(upPlayer)){
			map.put(new Action("move up"), new State<Position>(upPlayer));
		}
		
		if(game.isEmptyPosition(downPlayer)){
			map.put(new Action("move down"), new State<Position>(downPlayer));
		}
		if(game.isEmptyPosition(rightPlayer)){
			map.put(new Action("move right"), new State<Position>(rightPlayer));
		}
		if(game.isEmptyPosition(leftPlayer)){
			map.put(new Action("move left"), new State<Position>(leftPlayer));
		}
		
			
		return map;
	}


	@Override
	public State<Position> getInitialState() {
		Position playerPos = game.playerPos();
		State<Position> iniState = new State<Position>(playerPos);
		
		return iniState;
	}


	@Override
	public State<Position> getGoalState() {
		Position goalPos = game.goalPosition();
		State<Position> goalState = new State<Position>(goalPos);
		
		return goalState;
	}

}
