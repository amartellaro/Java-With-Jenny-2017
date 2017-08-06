package ReadingAndWriting2;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.io.IOException;
import java.util.Scanner;

public class TakingAttendance
{   
    static ArrayList students = new ArrayList();
    static ArrayList presence = new ArrayList();
    static Scanner keyboard = new Scanner(System.in);
    
    public static void readFile(String filename)
    {
        BufferedReader reader = null; 
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null) {
               String[] words = line.split("\t");
               String lastname = words[0];
               String firstname = words[1];
               String studentid = words[2];
               Student student = new Student(lastname, firstname, studentid);
               students.add(student);
            }
        } catch (IOException fnf) {
            System.out.println("File not found!");
        } 
    }
    
    /* Takes in date, iterates through student list, recording whether each student is present in the presence array list*/
    public static void recordAttendance(String date)
    {
        Iterator iter = students.iterator();
        while(iter.hasNext()){
            Student s = (Student) iter.next();
            System.out.print(s.getStudentid() + " " + s.getFirstname() + " " + s.getLastname() + ": ");
            String present = keyboard.nextLine();
            /* checks to make sure user is inputting a or p */
            while (!(present.equals("p") || present.equals("a")))
            {
                System.out.println("Invalid entry, try again.");
                System.out.print(s.getStudentid() + " " + s.getFirstname() + " " + s.getLastname() + ": ");
                present = keyboard.nextLine();
            }
            /* creates new DayAttendance object and adds it to attendance array list */
            DayAttendance attendance = new DayAttendance(s, date, present);
            presence.add(attendance);
        }
    }
    
    public static void writeToFile(String date)
    {
        /* parses date to create filename */
        String filename = "attendance_";
        for(String parsedDate: date.split("/")) {
            filename = filename + parsedDate + "_";
        }
        filename = filename.substring(0,21);
        
        /* creates file, then iterates through attendance array list and prints attendance of each student */
        PrintWriter pw = null;
        try {
            pw = new PrintWriter (new FileWriter(new File(filename)));
            pw.println("studentid\tpresence");
            Iterator iter = presence.iterator();
            while(iter.hasNext()){
                DayAttendance d = (DayAttendance) iter.next();
                pw.println(d.getStudent() + "\t" + d.getPresence());
            }
        } catch (IOException e) {
            System.out.println("Something went terribly wrong!");
        } finally {
            pw.close();
        }
    }
    
    public static void main(String[] args)
    {
        System.out.print("Attendance for date (mm/dd/yyyy): ");
        String date = keyboard.nextLine();
        readFile("students.txt");
        recordAttendance(date);
        writeToFile(date);
    }
}
