package CrosswordSolver;


/**
 * An implementing class provides a rule by which words are filtered.
 */
public interface WordFilter {
	
	/**
	 * Returns the word if it matches the filter criteria, or null otherwise.
	 * @param word
	 * @return
	 */
	public String filter(String word);

}
