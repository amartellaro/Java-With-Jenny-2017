package CrosswordSolver;

public class LengthFilter implements WordFilter
{
    private int length;
    
    public LengthFilter(int l)
    {
        this.length = l;
    }
    
    public String filter(String word)
    {
        if (word.length() == length)
        {
			return word;
        } else {
            return null;
        }
    }
}
