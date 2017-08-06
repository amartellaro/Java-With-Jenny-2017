package CrosswordSolver;


import java.util.ArrayList;
import java.util.List;

public class CrosswordSolverTest {

	private String listWords(List<String> results)
	{
		StringBuffer sb = new StringBuffer();
		int count = 0;
		for (String word : results) {
			sb.append(word + "\t");
			count++;
			if (count % 7 == 0) {
				sb.append("\n");
			}
		}
		return sb.toString().trim();
	}

	private List<String> asList(String...words)
	{
		List<String> list = new ArrayList<String>();
		for (String word : words) {
			list.add(word);
		}
		return list;
	}
	

	private boolean isActualSameAsExpected(int tcase, List<String> results, List<String> expected) {
		System.out.println();
		if (results.size() == expected.size() && results.containsAll(expected)) {
			System.out.println("Test case " + tcase + " passed.");
			return true;
		} else {
			System.out.println("Test case " + tcase + " failed.");
			System.out.println(tcase + " expected: " + listWords(expected));
			System.out.println(tcase + " actual: " + listWords(results));
			return false;
		}
	}

	public boolean testCase1(CrosswordSolver solver)
	{
		Dictionary dict = new Dictionary();

		WordFilter[] filters = new WordFilter[2];
		filters[0] = new LengthFilter(8);
		filters[1] = new PatternFilter("a-te---s");
		
		List<String> actual = solver.filterAll(dict, filters);
		List<String> expected = asList("alterers", "antennas", "arteries", "attempts");
		return isActualSameAsExpected(1, actual, expected);
	}
	
	public boolean testCase2(CrosswordSolver solver)
	{
		Dictionary dict = new Dictionary();

		WordFilter[] filters = new WordFilter[2];
		filters[0] = new LengthFilter(9);
		filters[1] = new PatternFilter("x--------");
		
		List<String> actual = solver.filterAll(dict, filters);
		List<String> expected = asList();
		return isActualSameAsExpected(2, actual, expected);
	}
	

	public boolean testCase3(CrosswordSolver solver)
	{
		Dictionary dict = new Dictionary();

		WordFilter[] filters = new WordFilter[2];
		filters[0] = new LengthFilter(6);
		filters[1] = new PatternFilter("f-m---");
		
		List<String> actual = solver.filterAll(dict, filters);
		List<String> expected = asList("family", "famine","famish","famous","female","femurs","fumble","fuming");
		return isActualSameAsExpected(3, actual, expected);
	}
	
	
	public boolean testCase4(CrosswordSolver solver)
	{
		Dictionary dict = new Dictionary();

		WordFilter[] filters = new WordFilter[3];
		filters[0] = new LengthFilter(6);
		filters[1] = new PatternFilter("f----e");
		filters[2] = new PatternFilter("f-m---");
		
		List<String> actual = solver.filterAll(dict, filters);
		List<String> expected = asList("famine", "female", "fumble");
		return isActualSameAsExpected(4, actual, expected);
	}
	
	public boolean testCase5(CrosswordSolver solver)
	{
		Dictionary dict = new Dictionary();

		WordFilter[] filters = new WordFilter[2];
		filters[0] = new LengthFilter(6);
		filters[1] = new PatternFilter("f-----");
		
		List<String> actual = solver.filterAll(dict, filters);
		System.out.println();
		if (actual.size() == 281) {
			System.out.println("Test case 5 passed.");
			return true;
		} else {
			System.out.println("Test case 5 failed; expected 281 results, got " + actual.size());
			return false;
		}
	}
	

	public boolean run() 
	{
		CrosswordSolver solver = new CrosswordSolver();
		return testCase1(solver) && testCase2(solver) && testCase3(solver) && 
			   testCase4(solver) && testCase5(solver);
	}

	public static void main(String[] args) {
		CrosswordSolverTest tester = new CrosswordSolverTest();
		boolean success = tester.run();
		if (success) { 
			System.exit(0);
		} else {
			System.exit(1);
		}
	}
	

}
