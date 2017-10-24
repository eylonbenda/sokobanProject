package plannerLib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Model;
import model.MyModel;
import model.data.Level;
import model.data.LoadReceiver;
import model.object_data.Position;
import model.object_data.Square;
import model.object_data.SquareTarget;
import searchLib.Action;
import searchLib.BFS;
import searchLib.Searchable;
import searchLib.Searcher;
import searchLib.Solution;
import searchLib.searchables.MoveBox;
import searchLib.searchables.SearchableBoxTarget;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, IOException {

		Predicate p3 = new Predicate("clear", "b1", "0,0");
		Predicate p4 = new Predicate("box", "b1", "0,0");

		Predicate p1 = new SokoPredicate("box", "b1", "2,0");
		Predicate p2 = new SokoPredicate("box", "b2", "0,0");
		Predicate p5 = new SokoPredicate("clearAt", "c", "0,0");

		System.out.println(p2.contradicts(p5));

		Clause kb = new Clause(p1, p2);
		Clause cls = new Clause(p5);
		System.out.println(kb);

		kb.update(cls);
		System.out.println(kb);

		LoadReceiver load = new LoadReceiver();
		Model model = new MyModel();
		
		model.loadlLevel("levels/level1.txt");
		Level level = model.getLevel();
		System.out.println(level);
		level.addBoxesToArray();
		level.addTargets();
		/*
		 * SokobanPlannable soko = new SokobanPlannable(level); Clause kbb =
		 * soko.getKnowledgebase(); System.out.println(kbb);
		 * System.out.println(soko.getGoal());
		 */

		ArrayList<Square> arr = level.getListBox();
		Position boxPos = arr.get(0).getPosition();
		ArrayList<SquareTarget> tr = level.getSt();
		Position targetPos = tr.get(0).getPosition();

		

		for (int i = 0; i < arr.size(); i++) {

			Searchable<Position> searchable = new SearchableBoxTarget(level, arr.get(i).getPosition(), tr.get(i).getPosition());
			System.out.println( arr.get(i).getPosition().getX()+"," +arr.get(i).getPosition().getY());
			System.out.println(tr.get(i).getPosition().getX()+","+tr.get(i).getPosition().getY());
			Searcher<Position> searcher = new BFS<Position>();
			Solution sol = searcher.search(searchable);
			System.out.println(sol);

			MoveBox moveBox = new MoveBox(searchable);

			List<Action> list = moveBox.move(level, arr.get(i).getPosition(), tr.get(i).getPosition());
			for (Action action : list) {
				System.out.println(action.getNameAction());
			}
			System.out.println(level);
		}

		

	}

}
