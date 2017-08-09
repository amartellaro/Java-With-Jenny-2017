/** Practice with Date and Calendar classes */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Dates
{  
   /** Parses strings to Dates, calculates the difference, then converts milliseconds to days */
   public static void dateDifference(String a, String b)
   {
       try
       {
           String pattern = "MMMMM d yyyy";
           SimpleDateFormat sdf = new SimpleDateFormat(pattern);
           Date aa = sdf.parse(a);
           Date bb = sdf.parse(b);
           long difference = bb.getTime() - aa.getTime();
           long days = TimeUnit.MILLISECONDS.toDays(difference);
           System.out.println("Those two dates are " + days + " days apart.");
       }
       catch (Exception e)
       {
       }
   }
   
   /** Parses string to date, then formats weekday. */
   public static void determineWeekday(String c)
   {
       try
       {
           String pattern = "MMMM d yyyy";
           SimpleDateFormat sdf = new SimpleDateFormat(pattern);
           Date cc = sdf.parse(c);
           String newPattern = "EEEE";
           SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern);
           String whatDay = sdf2.format(cc);
           System.out.println(c + " is on a " + whatDay + ".");
       }
       catch (Exception e)
       {
       }
   }
   
   public static void printFridays()
   {
      Calendar thisYear = Calendar.getInstance();
      thisYear.set(2017, 0, 1);
      
      String pattern = "EEEE, MMMM d, yyyy";
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      Date theDate = thisYear.getTime();
      
      System.out.println("\nThese are all the Fridays this year: ");
      
      while (thisYear.get(Calendar.YEAR) != 2018)
      {
          theDate = thisYear.getTime();
          int today = thisYear.get(Calendar.DAY_OF_WEEK);
          if(today == 6)
          {
              System.out.println(sdf.format(theDate));
              thisYear.add(Calendar.DATE, 1);
              theDate = thisYear.getTime();
          }
          else
          {
               thisYear.add(Calendar.DATE, 1);
               theDate = thisYear.getTime();
          }
      }
   }
   
   public static void printFriday13s()
   {
      Calendar thisYear = Calendar.getInstance();
      thisYear.set(2017, 0, 1);
      
      String pattern = "EEEE, MMMM d, yyyy";
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      Date theDate = thisYear.getTime();
      
      System.out.println("\nThese are all the Friday the 13s this year: ");
      
      while (thisYear.get(Calendar.YEAR) != 2018)
      {
          theDate = thisYear.getTime();
          int today = thisYear.get(Calendar.DAY_OF_WEEK);
          int date = thisYear.get(Calendar.DAY_OF_MONTH);
          if((today == 6) && (date == 13))
          {
              System.out.println(sdf.format(theDate));
              thisYear.add(Calendar.DATE, 1);
              theDate = thisYear.getTime();
          }
          else
          {
               thisYear.add(Calendar.DATE, 1);
               theDate = thisYear.getTime();
          }
      }
   }
   
   public static void loanPayments()
   {
      Calendar thisYear = Calendar.getInstance();
      thisYear.set(2017, 0, 1);
       
      String pattern = "EEEE, MMMM d, yyyy";
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      Date theDate = thisYear.getTime();
      
      System.out.println("\nPayments are due on the following dates: ");
      
      while (thisYear.get(Calendar.YEAR) != 2018)
      {
          theDate = thisYear.getTime();
          int today = thisYear.get(Calendar.DAY_OF_WEEK);
          int date = thisYear.get(Calendar.DAY_OF_MONTH);
          if((date == 1) && (today != 7) && (today != 1))
          {
              System.out.println(sdf.format(theDate));
              thisYear.add(Calendar.DATE, 1);
              theDate = thisYear.getTime();
          }
          else if ((date == 1) && (today == 7))
          {
              thisYear.add(Calendar.DATE, 2);
              theDate = thisYear.getTime();
              System.out.println(sdf.format(theDate));
          }
          else if((date == 1) && (today == 1))
          {
              thisYear.add(Calendar.DATE, 1);
              theDate = thisYear.getTime();
              System.out.println(sdf.format(theDate));
          }
          else
          {
               thisYear.add(Calendar.DATE, 1);
               theDate = thisYear.getTime();
          }
      }
   }
   
   public static void main(String[] args)
   {
       Scanner keyboard = new Scanner(System.in);
       System.out.println("Please give me a start date: ");
       String a = keyboard.nextLine();
       System.out.println("Please give me an end date: ");
       String b = keyboard.nextLine(); 
       dateDifference(a,b);
       
       System.out.println("\nPlease give me another date: ");
       String c = keyboard.nextLine();
       determineWeekday(c);
       
       printFridays();
       printFriday13s();
       loanPayments();
   }
}
