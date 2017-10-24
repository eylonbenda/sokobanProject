package plannerLib;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.data.Level;
import model.object_data.Position;
import model.object_data.Square;
import model.object_data.SquareTarget;
import model.policy.MySokobanPolicy;
import searchLib.BFS;
import searchLib.Searcher;
import searchLib.Solution;
import searchLib.searchables.SearchableBoxTarget;
import searchLib.searchables.SearchablePlayerBox;

public class SokobanPlannable implements Plannable {

	Level level;
	Clause kb;
	MySokobanPolicy policy;
	SearchablePlayerBox playerPath;
	Position playerPos;

	public SokobanPlannable(Level lvl) {

		level = lvl;
		kb = new Clause(null);
		policy = new MySokobanPolicy(level);
	}

	@Override
	public Clause getGoal() {

		Clause goal = new Clause(null);
		for (Predicate p : kb.setPredicates) {
			if (p.type.startsWith("tar"))
				goal.add(new Predicate("boxAt", "", p.value));
		}
		return goal;
	}

	@Override
	public Clause getKnowledgebase() {

		ArrayList<ArrayList<Square>> map = level.getLevelmap();
		int countBox = 0;
		int countTarget = 0;
		for (int i = 0; i < map.size(); i++) {
			for (int j = 0; j < map.get(i).size(); j++) {
				switch (map.get(i).get(j).toString()) {
				case "#":
					kb.add(new Predicate("wallAt", "", i + "," + j));
					break;
				case "@":
					countBox++;
					kb.add(new Predicate("boxAt", "b" + countBox, i + "," + j));
					break;
				case "o":
					countTarget++;
					kb.add(new Predicate("targetAt", "t" + countTarget, i + "," + j));
					break;
				case "A":
					kb.add(new Predicate("playerAt", "", i + "," + j));
					playerPos = new Position(i, j);
					break;
				case " ":
					kb.add(new Predicate("clearAt", "", i + "," + j));
					break;
				default:
					break;
				}
			}
		}

		return kb;
	}

	@Override
	public Set<Action> getsatisfyingActions(Predicate top) {

		// Extract the position of the predicate
		String[] str = top.value.split(",");
		int x = Integer.parseInt(str[0]);
		int y = Integer.parseInt(str[1]);
		Position CurrPos = new Position(x, y);

		if (top.value.startsWith("boxAt")) {
			
			Set<Action> setActions = new HashSet<>();
			Action pushUp = new Action("puhUp", "", "");
			Action pushDown = new Action("pushDown", "", "");
			Action pushRight = new Action("pushRight", "", "");
			Action pushLeft = new Action("pushLeft", "", "");

			for (Action action : setActions) {

				if (action.type.equals("pushUp")) {

					pushUp.setPreconditions(new Clause((new Predicate("playerAt", "", x + 2 + " " + y)),
							new Predicate("boxAt", "", x + 1 + " " + y)));
				} else if (action.type.equals("pushDown")) {

					pushDown.setPreconditions(new Clause((new Predicate("playerAt", "", (x - 2) + " " + y)),
							new Predicate("boxAt", "", (x - 1) + " " + y)));
				} else if (action.type.equals("pushLeft")) {

					pushLeft.setPreconditions(new Clause((new Predicate("playerAt", "", x + " " + y + 2)),
							new Predicate("boxAt", "", x + " " + y + 1)));

				} else if (action.type.equals("pushRight")) {

					pushRight.setPreconditions(new Clause((new Predicate("playerAt", "", x + " " + (y - 2))),
							new Predicate("boxAt", "", x + " " + (y - 1))));
				}

			}

			if (top.value.startsWith("player")) {

				Action moveAction = new Action("move", playerPos.getX() + "," + playerPos.getY(), x + "," + y);

			}

			// find box position according knowledge-base
			Position boxPos = null;
			for (Predicate p : kb.setPredicates) {
				String[] temp = p.value.split(",");
				int row = Integer.parseInt(temp[0]);
				int col = Integer.parseInt(temp[1]);

				if (p.type.startsWith("box")) {
					boxPos = new Position(row, col);

					SearchableBoxTarget boxPath = new SearchableBoxTarget(level, boxPos, CurrPos);
					Searcher<Position> searcher = new BFS<>();
					Solution sol = searcher.search(boxPath);
					String actionName = null;
					Clause preConditions = new Clause(null);
					Clause temp1 = new Clause(null);
					List<searchLib.Action> playerPath = new ArrayList<>();
					Square box = new Square(boxPos);

				}
			}
		}

		return null;

	}

	@Override
	public Action getsatisfyingAction(Predicate top) {

		Set<Action> actions = getsatisfyingActions(top);
		// TODO: sort the actions and return the action with most preconditions
		// that satisfies

		Action action = null;

		// Extract the position of the goal
		String[] str = top.value.split(",");
		int x = Integer.parseInt(str[0]);
		int y = Integer.parseInt(str[1]);
		Position goalPos = new Position(x, y);

		// find box position according knowledge-base
		Position boxPos = null;
		for (Predicate p : kb.setPredicates) {
			String[] temp = p.value.split(",");
			int row = Integer.parseInt(temp[0]);
			int col = Integer.parseInt(temp[1]);

			if (p.type.startsWith("box")) {
				boxPos = new Position(row, col);

				SearchableBoxTarget boxPath = new SearchableBoxTarget(level, boxPos, goalPos);
				Searcher<Position> searcher = new BFS<>();
				Solution sol = searcher.search(boxPath);
				String actionName = null;
				Clause preConditions = new Clause(null);
				Clause temp1 = new Clause(null);
				List<searchLib.Action> playerPath = new ArrayList<>();
				Square box = new Square(boxPos);

				if (sol != null) {

					for (searchLib.Action action1 : sol.getActions()) {

					}

				}
				return action;
			}
		}
		return null;
	}

	private Clause checkPreConditions(String actionName, Position boxPos) {

		int row = boxPos.getX();
		int col = boxPos.getY();
		Clause clause = null;
		switch (actionName) {
		case "move up":
			clause = new Clause(new Predicate("clearAt", "", row - 1 + " " + col),
					new Predicate("tagetAt", " ", row - 1 + " " + col),
					new Predicate("playerAt", "", row + 1 + " " + col));
			break;

		case "move down":
			clause = new Clause(new Predicate("clearAt", "", row + 1 + " " + col),
					new Predicate("tagetAt", " ", row + 1 + " " + col),
					new Predicate("playerAt", "", row - 1 + " " + col));
			break;
		case "move right":
			clause = new Clause(new Predicate("clearAt", "", row + " " + col + 1),
					new Predicate("tagetAt", " ", row + " " + col + 1),
					new Predicate("playerAt", "", row + " " + (col - 1)));
			break;
		case "move left":
			clause = new Clause(new Predicate("clearAt", "", row + " " + (col - 1)),
					new Predicate("tagetAt", " ", row + " " + (col - 1)),
					new Predicate("playerAt", "", row + " " + col + 1));
			break;

		default:
			break;
		}

		return clause;
	}

	private List<searchLib.Action> updatePath(Square box, String order) {

		List<searchLib.Action> listMoves = null;
		Position boxPos = box.getPosition();
		Searcher<Position> searcher = new BFS<>();
		Position posByBox = calculatePos(boxPos, order);

		return listMoves;

	}

	private Position calculatePos(Position boxPos, String order) {
		// TODO Auto-generated method stub
		return null;
	}

}
