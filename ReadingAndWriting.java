import java.util.Scanner;
import java.io.File;
import java.io.Writer;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ReadingAndWriting
{
   static Scanner keyboard = new Scanner(System.in);
    
   public static void newFile()
   {
       System.out.print("Please enter the filename to save to: ");
       String filename = keyboard.next();
       
       PrintWriter pw = null;
       try {
           pw = new PrintWriter (new FileWriter(new File(filename)));
           String student = "null";
           while (!student.equals("quit"))
           {
               System.out.print("student: ");
               student = keyboard.next();
               if (!student.equals("quit"))
               {
                   pw.println(student);
                }
           }
        } catch (IOException e) {
            System.out.println("Something went terribly wrong!");
        } finally {
            pw.close();
        }
   }
   
   public static void main(String[] args)
   {
       System.out.println("Student Name List");
       newFile();
   }
}
