/**
 * The titler changes a book title into proper uppercase for a title.  It skips
 * words in the skip-words list, such as "the", "of", etc.
 * 
 * Title case: Every word gets capitalized except skipwords.  The
 * skipwords only get capitalized if they are the first or last
 * word of the book title, since the first and last are always capitalized.
 * 
 * 
 */
public class BookTitler
{
    String[] skipwords = { "a", "an", "the", "and", "but", "for", "nor", "or", "so", "yet", "by", 
        "like", "as", "at", "on", "of", "from", "is", "in" };
    
    public BookTitler()
    {
    }
    
    public String fixCase(String input)
    {
        // --- your code here ----
        // you may also add more methods to this class if you want them.
        String[] titleWords = input.split(" ");
        char firstLetter = titleWords[0].charAt(0);
        String strFirstLetter = Character.toString(firstLetter);
        char newFirstLetter = Character.toUpperCase(firstLetter);
        String strNewFirstLetter = Character.toString(newFirstLetter);
        titleWords[0] = titleWords[0].replace(strFirstLetter, strNewFirstLetter);
        String newTitle = titleWords[0];
        newTitle = newTitle.concat(" ");
        
        for (int i = 1; i < titleWords.length; i++){
            boolean needCaps = true;
            for (int j = 0; j < skipwords.length; j++){
                if (titleWords[i].equals(skipwords[j])){
                    newTitle = newTitle.concat(titleWords[i]);
                    needCaps = false;
                }
            }
            if(needCaps == true){
                char oldLetter = titleWords[i].charAt(0);
                String strOldLetter = Character.toString(oldLetter);
                char newLetter = Character.toUpperCase(oldLetter);
                String strNewLetter = Character.toString(newLetter);
                titleWords[i] = titleWords[i].replaceFirst(strOldLetter, strNewLetter);
                newTitle = newTitle.concat(titleWords[i]);
            }
            if(i != titleWords.length - 1){
                newTitle = newTitle.concat(" ");
            }
        }
        
        return newTitle; // fixme
        // --- end your code -----

    }
    
    public static void main(String[] args)
    {
        BookTitler titler = new BookTitler();
        
        System.out.println(titler.fixCase("a cat sat on a hat"));
        System.out.println(titler.fixCase("the pig is in the corn"));
        System.out.println(titler.fixCase("where did the barn blow off to"));
        System.out.println(titler.fixCase("charlotte's web"));
        System.out.println(titler.fixCase("alice in wonderland"));
        
        if (! "A Cat Sat on a Hat".equals(titler.fixCase("a cat sat on a hat"))) {
            System.out.println("Error in cat-hat");
        }
        if (! "The Pig is in the Corn".equals(titler.fixCase("the pig is in the corn"))) {
            System.out.println("Error in pig");
        }
        if (! "Where Did the Barn Blow Off To".equals(titler.fixCase("where did the barn blow off to"))) {
            System.out.println("Error in barn");
        }
        if (! "Charlotte's Web".equals(titler.fixCase("charlotte's web"))) {
            System.out.println("Error in web");
        }
        if (! "Alice in Wonderland".equals(titler.fixCase("alice in wonderland"))) {
            System.out.println("Error in wonderland.");
        }
    }


}
