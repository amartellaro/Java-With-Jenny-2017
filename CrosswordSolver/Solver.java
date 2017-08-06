package CrosswordSolver;

import java.util.List;

public interface Solver {
	
	 /**
	  * Takes a dictionary of words and a set of filters, and returns
	  * a list of only the words that matched all filters.
	  * @param dict
	  * @param filters
	  * @return
	  */
	 public List<String> filterAll(Dictionary dict, WordFilter[] filters);

}