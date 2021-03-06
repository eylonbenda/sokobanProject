package searchLib;

public interface Searcher<T> {
	

	// the search method
	public Solution search(Searchable<T> s);
	// get how many nodes were evaluated by the algorithm
	public int getNumberOfNodesEvaluated();

}
