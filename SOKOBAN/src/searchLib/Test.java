package searchLib;

import java.util.LinkedList;

public class Test {

	public static void main(String[] args) {
		
		
		Maze gaMaze = new Maze();
		char[][] board = {  {'#','#','#','#','#',},
							{'x',' ','#','#','#',}, 
							{' ','@',' ',' ','#',},
							{'#','o','#','#','#',},};
		
		gaMaze.setBoard(board);
		Searcher<Position> searcher = new BFS<Position>();
		Searchable<Position> searchable = new MazeDomain<Position>(gaMaze);
		//LinkedList<Action> actions = new LinkedList<>();
		Solution sol = searcher.search(searchable);
		System.out.println(sol);
		
		System.out.println("# of nodes evaluated " +
	         	searcher.getNumberOfNodesEvaluated());

													

	}

}
