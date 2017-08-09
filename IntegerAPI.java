import java.util.Scanner;
import java.lang.Integer;

public class IntegerAPI
{ 
    public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      
      /* Testing compareTo */
      System.out.println("CompareTo test:");
      System.out.println("Please give me two ints!");
      Integer one =  keyboard.nextInt();
      Integer two = keyboard.nextInt();
      int three = one.compareTo(two);
      
      if (three == 0){
          System.out.println("These two ints are the same!");
      } else if (three > 0){
          System.out.println(one + " is greater than " + two + ".");
      } else if (three < 0){
          System.out.println(two + " is greater than " + one + ".");
      }
      
      /* Testing compare */
      System.out.println("\nCompare test:");
      System.out.println("Please give me two more ints!");
      int four = keyboard.nextInt();
      int five = keyboard.nextInt();
      int six = Integer.compare(four, five);
      
      if (six == 0){
          System.out.println("These two ints are the same!");
      } else if (six > 0){
          System.out.println(four + " is greater than " + five + ".");
      } else if (six < 0){
          System.out.println(five + " is greater than " + four + ".");
      }
      
      /* Testing decode */
      System.out.println("\nDecode test:");
      String a = "-10";
      String b = "0xFF";
      String c = "-0xAB";
      String d = "010";
      String e = "011";
      String f = "012";
      
      Integer aa = Integer.decode(a);
      System.out.println(aa);
      Integer bb = Integer.decode(b);
      System.out.println(bb);
      Integer cc = Integer.decode(c);
      System.out.println(cc);
      Integer dd = Integer.decode(d);
      System.out.println(dd);
      Integer ee = Integer.decode(e);
      System.out.println(ee);
      Integer ff = Integer.decode(f);
      System.out.println(ff);
      
      /* Testing floatValue */
	  System.out.println("\nfloatValue test:");
	  System.out.println("Please give me two more ints!");	
      Integer seven =  keyboard.nextInt();
      Integer eight =  keyboard.nextInt();
	  float nine = seven.floatValue();
	  float ten = eight.floatValue();
	  System.out.println(nine);
	  System.out.println(ten);
	  
	  /* Testing hashCode */
	  System.out.println("\nhashCode test:");
	  System.out.println("Two more ints please!");
	  Integer eleven = keyboard.nextInt();
	  Integer twelve = keyboard.nextInt();
	  int thirteen = Integer.hashCode(eleven);
	  int fourteen = Integer.hashCode(twelve);
	  System.out.println(thirteen);
	  System.out.println(fourteen);
	  
	  /* Comparing hex */
	  System.out.println("\nComparing hex codes:");
	  String g = "0xFA";
	  String h = "0xFB";
	  Integer gg = Integer.decode(g);
	  Integer hh = Integer.decode(h);
	  int fifteen = Integer.compare(gg, hh);
      
      if (fifteen == 0){
          System.out.println("These two ints are the same!");
      } else if (fifteen > 0){
          System.out.println(one + " is greater than " + two + ".");
      } else if (fifteen < 0){
          System.out.println(two + " is greater than " + one + ".");
      }

	  /* Testing toBinaryString */
	  System.out.println("\nTesting binary string:");
	  int sixteen = 256;
	  String i = Integer.toBinaryString(sixteen);
	  System.out.println(i);

	  /* Testing toHexString */
	  System.out.println("\nTesting hex string:");
	  int seventeen = 1024;
	  String j = Integer.toHexString(sixteen);
	  String k = Integer.toHexString(seventeen);
	  System.out.println(j);
	  System.out.println(k);

	  /* Testing valueOf */
	  System.out.println("\nvalueOf test:");
	  String l = "555";
	  Integer ii = Integer.valueOf(l);
	  System.out.println(ii);
   }
}
