package Collections;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class ContaminatedSites
{
    static List<Site> sites = new ArrayList<Site>();
    static List<String> uniqueSites = new ArrayList<String>();
    static Map<String, Integer> contaminatedCount = new HashMap<String, Integer>();
    static Map<String, Integer> restrictedCount = new HashMap<String, Integer>();
    
    // reads and parses CSV file, creates objects, then adds them to sites array list
    public static void readFile(String filename)
    {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(filename)));
            String line;
            String[] params = null;
            while((line = reader.readLine()) != null) {
                params = line.split(",");
                String taskName = params[5];
                String classification = params[17];
                Site site = new Site(taskName, classification);
                sites.add(site);
            }
        } catch (IOException fnf) {
            System.out.println("File not found!");
        }
    }
    
    // makes an array list that keeps track of names of unique sites
    public static void countUniqueSites()
    {
        Iterator iter = sites.iterator();
        while(iter.hasNext()){
            Site s = (Site) iter.next();
            String siteName = s.getTaskName();
            if(!uniqueSites.contains(siteName))
            {
                uniqueSites.add(siteName);
            }
        }
        int uniqueCount = uniqueSites.size();
        System.out.println("There are " + uniqueCount + " unique sites included in the file.");
    }
    
    // iterates through array list then makes hashmap using location as a key and the number of contaminated sites as a value
    public static void keepingCounts()
    {
        Iterator iter = sites.iterator();
        while(iter.hasNext()){
            Site s = (Site) iter.next();
            String siteName = s.getTaskName();
            if (s.getClassification().equals("CONTAMINATED"))
            {
                if(!contaminatedCount.containsKey(siteName))
                {
                    contaminatedCount.put(siteName, 1);
                } else {
                    Integer count = contaminatedCount.get(siteName);
                    count++;
                    contaminatedCount.replace(siteName, count);
                }
            } else if (s.getClassification().equals("CLOSED WITH RESTRICTIONS")) {
                if(!restrictedCount.containsKey(siteName))
                {
                    restrictedCount.put(siteName, 1);
                } else {
                    Integer count = restrictedCount.get(siteName);
                    count++;
                    restrictedCount.replace(siteName, count);
                }
            }         
        }
    }
    
    // iterates through hashmap to determine which site is most contaminated
    public static void mostContaminated()
    {
        String site = null;
        Integer number = 0;
        for(Map.Entry<String, Integer> entry : contaminatedCount.entrySet()) {
            if(entry.getValue() > number)
            {
                site = entry.getKey();
                number = entry.getValue();
            }
        }
        System.out.println("The most contaminated company was " + site + " with " + number + " contaminated sites.");
    }
    
    // iterates through hashmap to determine which site is most restricted
    public static void mostRestricted()
    {
        String site = null;
        Integer number = 0;
        for(Map.Entry<String, Integer> entry : restrictedCount.entrySet()) {
            if(entry.getValue() > number)
            {
                site = entry.getKey();
                number = entry.getValue();
            }
        }
        System.out.println("The most restricted company was " + site + " with " + number + " sites closed with restrictions.");
    }
    
    public static void main (String[] args)
    {
        // open CSV file and read it into memory
        readFile("Contaminated-Site.csv");
        
        // print out the answers for each of the questions
            // How many unique places are included in the file?
            // Which place had the most occurances of "contaminated"?
            // Which place had the most occurrances of "closed with restrictions"?
        countUniqueSites();
        keepingCounts();
        mostContaminated();
        mostRestricted();
    }
}
