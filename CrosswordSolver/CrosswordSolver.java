package CrosswordSolver;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class CrosswordSolver implements Solver
{
    public List<String> filterAll(Dictionary dict, WordFilter[] filters)
    {
        List<String> matches = new ArrayList<String>();
		String passedWord = null;
		WordFilter filterTest = null;

        for (int i = 0; i < dict.size(); i++)
        {
            String word = dict.getWord(i);
            Boolean passedTests = true;
			for (int j = 0; j < filters.length; j++)
			{
				 filterTest = filters[j];
				 passedWord = filterTest.filter(word);
				 if(passedWord == null)
				 {
				     passedTests = false;
				 }
			}
    		if(passedTests == true)
			{
				matches.add(passedWord);
			}
		}
        return matches;
    }
}
