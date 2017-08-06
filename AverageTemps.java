
public class AverageTemps
{
    public static void averageDay (int[][] temps)
    {
        int dayAverage = 0;
        for (int column = 0; column < 7; column++)
        {
            for(int row = 0; row < 4; row++)
            {
                dayAverage += temps[row][column];
            }
            dayAverage /= 4;
            System.out.println("Day " + column + " average temp: " + dayAverage);
            dayAverage = 0;
        }
    }
    
    public static void averageTime (int[][] temps)
    {
        int timeAverage = 0;
        for (int row = 0; row < 4; row++)
        {
            for(int column = 0; column < 7; column++)
            {
                timeAverage += temps[row][column];
            }
            timeAverage /= 7;
            System.out.println("Time " + row + " average temp: " + timeAverage);
            timeAverage = 0;
        }
    }
    
    public static int averageTemp (int[][] temps)
    {
        int totalTemp = 0;
        for (int row = 0; row < 4; row++)
        {
            for(int column = 0; column < 7; column++)
            {
                totalTemp += temps[row][column];
            }
        }
        int avgTemp = totalTemp / 28;
        return avgTemp;
    }
    
    public static void main (String[] args)
    {
        int temps[][] = new int [4][7];
        temps[0][0] = 68;
        temps[0][1] = 70;
        temps[0][2] = 76;
        temps[0][3] = 70;
        temps[0][4] = 68;
        temps[0][5] = 71;
        temps[0][6] = 75;
        temps[1][0] = 76;
        temps[1][1] = 76;
        temps[1][2] = 87;
        temps[1][3] = 84;
        temps[1][4] = 82;
        temps[1][5] = 75;
        temps[1][6] = 83;
        temps[2][0] = 73;
        temps[2][1] = 72;
        temps[2][2] = 81;
        temps[2][3] = 78;
        temps[2][4] = 76;
        temps[2][5] = 73;
        temps[2][6] = 77;
        temps[3][0] = 64;
        temps[3][1] = 65;
        temps[3][2] = 69;
        temps[3][3] = 68;
        temps[3][4] = 70;
        temps[3][5] = 74;
        temps[3][6] = 72;
        
        averageDay(temps);
        averageTime(temps);
        System.out.println("Average temperature overall: " + averageTemp(temps));
    }
}
