package CrosswordSolver;

public class PatternFilter implements WordFilter
{
    private String pattern;
    
    public PatternFilter(String p)
    {
        this.pattern = p;
    }
    
    public String filter(String word)
    {
		boolean test = true;
		String goodWord = null;
        if(pattern.length() == word.length())
		{
		    for(int i = 0; i < pattern.length(); i++)
            {
				if(pattern.charAt(i) != '-')
				{
					if(pattern.charAt(i) != word.charAt(i))	
                	{
                   		test = false;
					} 
				}
			}
			if(test == true)
			{
				goodWord = word;
			}
		}
		return goodWord;
    }
}