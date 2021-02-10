/**
 * Name: Daniel Nguyen
 * ID: A16129027
 * Email: d7nguyen@ucsd.edu
 * Sources used: tutor help(Manshi Yang, Ashley Kou), 
 * javadoc https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
 * 
 * This file contains a program using one-dimensional arrays to model how covid 
 * can spread from student to student when given a file containing names, 
 * locations, movement patterns, and infection statuses.
 */
 
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/**
 * This class reads from a file and perform operations on the information
 * on the file.
 */
public class InfectionTracking
{
    /*a method to scan in the contents of the file into various parallel arrays
     *@ return an integer value
     */
    public static int populateArrays(String pathToFile, String[] names,
    int[] locations, int[] movements, int[] infections) throws IOException
    {
        //validity checking
        if(pathToFile==null) //pathToFile is not valid
        {
            return -1;
        }
        
        //check for any null input arrays
        if (names == null)
        {
            return -1;
        }
        if (locations == null)
        {
            return -1;
        }
        if (movements == null)
        {
            return -1;
        }
        if (infections == null)
        {
            return -1;
        }
        
        for(int a=0; a<=infections.length; a++)
        {
            //check for invalid inputs in infections array(non one or zero)
            if(infections[a]<0 || infections[a]>1)
            {
                return -1;
            }
        }
        
        //make scanner and file
        File sourceFile = new File(pathToFile);
        Scanner scnr = new Scanner(new File(pathToFile));
        int largestLocation=0;
        for(int a=0; scnr.hasNextLine()==true; a++)
        {
            names[a] = scnr.next();
            locations[a] = scnr.nextInt();
            if (locations[a]>=largestLocation)
            {
                /**if this location larger than any scanned before it, 
                 *set largestLocation equal to that integer
                 */
                largestLocation = locations[a];
            }
            movements[a] = scnr.nextInt();
            infections[a] = scnr.nextInt();
        }
        
        return largestLocation+1;
    }
    
    /**
     * a method to update the locations of each student depending on their 
     * movement patterns
     * 
     */
    public static void updateLocations(int worldSize, int[] locations, 
    int[] movements)
    {
        if(worldSize<0)
        {
            return;  //world size can't be negative
        }
        for(int b=0; b<=locations.length; b++)
        {
            //check for invalid inputs in location array(out of bounds)
            if(locations[b]<0 || locations[b]<worldSize)
            {
                return;
            }
        }
        
        for(int i=0; i<=locations.length; i++)
        {   /*update by adding movement value to location. use modulo to wrap.*/
            locations[i] = (locations[i]+movements[i])%(worldSize-1);
        }
    }
    
    /**
     * a method to track the spread of infection to each student depending on 
     * current location
     * 
     * @return an array for the number of students infected by each person
     */
    public static int[] updateInfections(int worldSize, int[] locations, 
    int[] infections)
    {
        /*validity check*/
        if(worldSize<0)
        {
            return null;  //world size can't be negative
        }
        for(int b=0; b<=locations.length; b++)
        {
            //check for invalid inputs in location array(out of bounds)
            if(locations[b]<0 || locations[b]<worldSize)
            {
                return null;
            }
        }
        
        boolean valid = true;
        for(int a=0; a<=locations.length;a++)
        {   /*make sure locations are valid, or within world size range.*/
            if(locations[a]>worldSize)
            {
                valid = false;
            }
        }
        
        if(valid = true)
        {
            int [] numStudentsInfected = new int[locations.length];
            for(int i=0; i<=locations.length;i++)
            {
                for(int j=0; j<=locations.length; j++)
                {   
		    /*check to see if any location is equal to another.*/
                    if (locations[i] == locations[j])
                    {
                        if (infections[i] == 1 && infections[j] == 0)
                        {
                            /*check to see if student i infects student j*/
                            numStudentsInfected[i]++;
                        }
                        else if (infections[i] == 0 && infections[j] == 1)
                        {
                            /*check to see if student j infects student i*/
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
     * a method to count the infections caused by each student and
     * update locations and infections according to the simulation
     *
     * @returns the array of infections caused by each student
     */
    /*
    public static int[] countInfectionsByStudent(int days, int worldSize,
    int[] locations, int[] movements, int[] infections)
    {
        //check for any null input arrays
        if (locations == null)
        {
            return null;
        }
        if (movements == null)
        {
            return null;
        }
        if (infections == null)
        {
            return null;
        }
        
        
        for(int a=0; a<=infections.length; a++)
        {
            //check for invalid inputs in infections array(non one or zero)
            if(infections[a]<0 || infections[a]>1)
            {
                return null;
            }
        }
        for(int b=0; b<=locations.length; b++)
        {
            //check for invalid inputs in location array(out of bounds)
            if(locations[b]<0 || locations[b]<worldSize)
            {
                return null;
            }
        }
        
        if (days<0)
        {
            return null;    //days can't be negative
        }
        if (worldSize<0)
        {
            return null;    //world size can't be negative
        }
        
    }*/
    
    
    /**
     * a method to find the average number of students who will be infected 
     * by one student
     * 
     * @return the average as an integer with no decimals.
     */
	public static int findRNaught(int[] infectionRecord)
	{
	    /*invalid cases*/
            if(infectionRecord.length == 0)
	    {
            	/*invalid because it cannot be length zero*/
            	return -1;
            }
        for(int a=0; a<=infectionRecord.length; a++)
        {
            if (infectionRecord[a]<0)
            {
                /*cannot be negative*/
                return -1;
            }
        }
	    
        /*count the people who infected someone*/
        int infectorCount=0;
        for(int b=0; b<=infectionRecord.length; b++)
        {
            if (infectionRecord[b]<0)
            {   
                /*if they infected someone, increment the count*/
                infectorCount++;
            }
            
        }
        
        int sum=0;
        for(int c=0; c<=infectionRecord.length; c++)
        {
            /*find the sum in order to calculate average*/
            sum = infectionRecord[c] + sum;
        }
        
        /*calculate average*/
        int average = sum / infectorCount;
        
        return average;
	}
	
	/**
	 * a method that finds the index of the name of the student who
	 * infected others the most.
	 * 
	 * @returns the name of the student
	 */
	public static String findSuperSpreader(int[] infectionRecord,
	String[] names)
	{
	    String superSpreaderName = names[names.length];
	    int superStreaderNumber=0;  /*how many infections the student had*/
	    for(int a=infectionRecord.length; a<0; a--)
            {
            	/*loop from the highest index*/
            	if(infectionRecord[a]>=superStreaderNumber)
            	{
                    /*find the index on the infectionRecord with greatest value*/
                    superStreaderNumber = infectionRecord[a];
                    superSpreaderName = names[a];
                }
        }
        return superSpreaderName;
    }
	
    public static void main(String[] args) throws IOException
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
        
        System.out.println (populateArrays(pathToFile, names, locations,
        movement, infections));
    }
}



