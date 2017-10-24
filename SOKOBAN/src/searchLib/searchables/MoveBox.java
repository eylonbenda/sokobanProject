package searchLib.searchables;

import java.util.ArrayList;
import java.util.List;

import model.MyModel;
import model.data.Level;
import model.object_data.Position;
import model.object_data.Square;
import model.object_data.SquareTarget;
import model.policy.MySokobanPolicy;
import searchLib.Action;
import searchLib.BFS;
import searchLib.Searchable;
import searchLib.Searcher;
import searchLib.Solution;

public class MoveBox {

	private SearchableBoxTarget searchableBoxTarget;
	private SearchablePlayerBox searchablePlayerBox;
	private MyModel model;
	private MySokobanPolicy sokoPolicy;

	public MoveBox(Searchable<Position> st) {
		this.searchableBoxTarget = (SearchableBoxTarget) st;
		model = new MyModel();

	}

	public List<Action> move(Level level, Position box, Position target) {
		
		sokoPolicy = new MySokobanPolicy(level);
		//model.setLevel(level);
		List<Action> list = new ArrayList<>();
		List<Action> playerPath = new ArrayList<>();
		Searcher<Position> searcher = new BFS<>();
		Solution sol = searcher.search(searchableBoxTarget);
		

		
		if (sol == null)
			return null;

		list = sol.getActions();
		if (list == null)
			return null;

		List<Action> moves = new ArrayList<>();// store the actions to move the
												// box to target
		
		for (Action action : list) {

			playerPath = move1Step(level, box, action.getNameAction());
			System.out.println(level);
			box = updateBoxPos(level, action.getNameAction());

			if (playerPath == null)
				return null;

			for (Action action2 : playerPath) {
				moves.add(action2);
			}

		}
		return moves;
	}

	private Position updateBoxPos(Level level, String nameAction) {

		ArrayList<ArrayList<Square>> map = level.getLevelmap();
		Position playerPos = level.getPlayer().getPosition();
		int row = playerPos.getX();
		int col = playerPos.getY();
		Position temp = null;

		switch (nameAction) {
		case "move up":
			temp =  new Position(row - 1, col);
			break;
		case "move down":
			temp =  new Position(row + 1, col);
			break;
		case "move right":
			temp =  new Position(row , col+1);
			break;
		case "move left":
			temp =  new Position(row , col-1);
			break;
		default:
			break;
		}
		return temp;

	}



	public List<Action> move1Step(Level level, Position box, String action) {

		Position boxPos = box;
		System.out.println("box position");
		System.out.println(boxPos.getX()+","+boxPos.getY());
		Searcher<Position> searcher = new BFS<>();
		Position posByBox = calculatePos(boxPos, action);
		Position playerPos = level.positionPlayer().getPosition();
		System.out.println("playerPos");
		System.out.println(playerPos.getX() + "," + playerPos.getY());
		searchablePlayerBox = new SearchablePlayerBox(level, playerPos, posByBox);
		Solution sol = searcher.search(searchablePlayerBox);
		List<Action> playerPath = new ArrayList<>();
		playerPath = sol.getActions();
		playerPath.add(new Action(action));

		for (Action action2 : playerPath) {
			String[] str = action2.getNameAction().split(" ");
			//model.move(str[1]);
			switch (str[1]) {
			case "up":
				sokoPolicy.moveUp();
				break;
			case "down":
				sokoPolicy.moveDown();
				break;
			case "right":
				sokoPolicy.moveRight();
				break;
			case "left":
				sokoPolicy.moveLeft();
				break;
			}
			playerPos = level.positionPlayer().getPosition();
			System.out.println(playerPos.getX() + "," + playerPos.getY());
		}

		

		return playerPath;
	}

	private Position calculatePos(Position boxPos, String action) {

		Position pos = null;
		int row = boxPos.getX();
		int col = boxPos.getY();

		switch (action) {
		case "move up":
			pos = new Position(row + 1, col);
			break;
		case "move down":
			pos = new Position(row - 1, col);
			break;
		case "move right":
			pos = new Position(row, col - 1);
			break;
		case "move left":
			pos = new Position(row, col + 1);
			break;
		default:
			break;
		}

		return pos;
	}

}
