/**
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Manshi Yang), javadoc https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
 * 
 * This file contains a program to 
 */
 
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/**
 * This class will 
 */

public class InfectionTracking
{
    
    public static int populateArrays(String pathToFile, String[] names, int[] locations, int[] movements, int[] infections) throws IOException
    {
        //validity
        
        int largestLocation=0;
        
        File sourceFile = new File(pathToFile);
        Scanner scnr = new Scanner(new File(pathToFile));
        
        
        int line=0;
        while(scnr.hasNextLine())
        {
            names[line] = scnr.next();
            locations[line] = scnr.nextInt();
            if (locations[line]>=largestLocation)
            {
                /*if this location larger than any scanned before it, set largestLocation equal to that integer*/
                largestLocation = locations[line];
            }
            movements[line] = scnr.nextInt();
            infections[line] = scnr.nextInt();
            line++;
        }
        
        return largestLocation+1;
    }
    
    
    public static void updateLocations(int worldSize, int[] locations, int[] movements)
    {
        for(int i=0; i<=locations.length(); i++)
        {   /*update by adding movement value to location. use modulo to wrap.*/
            locations[i] = (locations[i]+movements[i])%worldSize;
        }
    }
    
    public static int[] updateInfections(int worldSize, int[] locations, int[] infections)
    {
        boolean valid = true;
        for(int a=0; a<=locations.length();a++)
        {   /*make sure locations are valid, or within world size range.*/
            if(locations[a]>worldSize)
            {
                valid = false;
            }
        }
        
        if(valid = true)
        {
            int [] numStudentsInfected = new array[locations.length()];
            for(int i=0; i<=locations.length();i++)
            {
                for(int j=0; j<=locations.length(); j++)
                {   /*check to see if any location is equal to another.*/
                    if (locations[i] == locations[j])
                    {
                        if (infections[i] == 1 && infections[j] == 0)
                        {
                            /*check to see if student i infects student j*/
                            infections[j] = 1;
                            numStudentsInfected[i]++;
                        }
                        else if (infections[i] == 0 && infections[j] == 1)
                        {
                            /*check to see if student j infects student i*/
                            infections[i] = 1;
                            numStudentsInfected[j]++;
                        }
                    }
                }
            }
            return numStudentsInfected;
        }
        else
        {
            /*invalid input: location is out of bounds*/
            return null;
        }
    }
    
    /*
    public static int[] countInfectionsByStudent(int days, int worldSize, int[] locations, int[] movements, int[] infections)
    {
        
    }*/
    

	public static void main(String[] args) 
	{
	    int worldsize=0;
	    
	    int days;
	    int numberOfStudents;
	    String pathToFile;
        
        Scanner s = new Scanner(System.in);
        pathToFile = s.next();
        days = s.nextInt();
        numberOfStudents = s.nextInt();
        
        
        String [] names = new String[numberOfStudents];
        int [] locations = new int[numberOfStudents];
        int [] movement = new int[numberOfStudents];
        int [] infections = new int[numberOfStudents];
        
        System.out.println (populateArrays(pathToFile, names, locations, movement, infections));
	}
}
