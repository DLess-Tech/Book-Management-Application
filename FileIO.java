//***** NEW FEATURES ADDED (START) *****


//Add libraries we need for File I/O
import java.security.DigestOutputStream;
import java.io.File;				//NEW - File IO
import java.io.PrintWriter;	//NEW - Printing to Files

import java.io.FileReader;			//Opens file for input
import java.io.BufferedReader;		//Reads each line in as string
import java.io.IOException;			//Provides for different file errors


//Class that acts a Function Library for File Input and Output
public class FileIO 
{
	
	//Function (or Method) to Append Specified data to a Specified datafile
	public static String AppendToFile(String tData, String tFileName)
	{
		String tFeedback ="";
		
	    File file = new File(tFileName);		//Create File Object
	    	
	    // Create a file using Try and catch to keep any issues from crashing
	    // The code in the parenthesis sets up a new object that auto-closes
	    // when hitting the closing brace for TRY
	    try 
	    (PrintWriter output = new java.io.PrintWriter(new java.io.FileOutputStream(file, true));) 
	    {
		      // Write formatted output to the file
		      output.println(tData);
		      tFeedback = "SUCCESS";
	    }
	    catch (Exception err)
	    {
	    	//if there is an error, display it in the feedback label
		      tFeedback = "ERROR: " + err.getMessage();
	    }

	    return tFeedback;  //Return whether writing was a success
	}
	
	

	//Function (or Method) to Append Specified data to a Specified datafile
	public static String OverwriteFile(String tData, String tFileName)
	{
		String tFeedback ="";
		
	    File file = new File(tFileName);		//Create File Object
	    	
	    // Create a file using Try and catch to keep any issues from crashing
	    // The code in the parenthesis sets up a new object that auto-closes
	    // when hitting the closing brace for TRY
	    try 
	    (PrintWriter output = new java.io.PrintWriter(new java.io.FileOutputStream(file, false));) 
	    {
		      // Write formatted output to the file
		      output.println(tData);
		      tFeedback = "SUCCESS";
	    }
	    catch (Exception err)
	    {
	    	//if there is an error, display it in the feedback label
		      tFeedback = "ERROR: " + err.getMessage();
	    }

	    return tFeedback;  //Return whether writing was a success
	}
	
	
	//Function (or Method) to Append Specified data to a Specified datafile
	public static String CreateNewDataFile(String tData, String tFileName)
	{
		String tFeedback ="";
				
	    File file = new File(tFileName);		//Create File Object
	    
	    //If file already exists, return error message, exits func early
	    if (file.exists()) 
	    {
	    	 tFeedback = "ERROR: File already exists";
		     return tFeedback;
	    }
   
	    // Create a file using Try and catch to keep any issues from crashing
	    // The code in the parenthesis sets up a new object that auto-closes
	    // when hitting the closing brace for TRY
	    try 
	    (PrintWriter output = new java.io.PrintWriter(new java.io.FileOutputStream(file, false));) 
	    {
		      // Write formatted output to the file
		      output.println(tData);
		      tFeedback = "SUCCESS";
	    }
	    catch (Exception err)
	    {
	    	//if there is an error, display it in the feedback label
		      tFeedback = "ERROR: " + err.getMessage();
	    }

	    return tFeedback;  //Return whether writing was a success
	}
	
	
	//Function to retrieve one line of data
	public static String GetOneLineOfData(String tFileName)
	{
        String filePath = tFileName; // Replace with your file path
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            if ((line = br.readLine()) != null) 
            {
                // Process each line here...we already have the line...do nada
            }
        } 
        catch (IOException e) 
        {
            line = "ERROR: " + e.getMessage();
        }
        
        return line;
    }

	
	
	//Function to retrieve all lines of data
	public static String GetAllLinesOfData(String tFileName)
	{
        String filePath = tFileName; // Replace with your file path
        String line, allLines="";
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            while ((line = br.readLine()) != null) 
            {
                // Process each line here...
            	allLines += line;
            }
        } 
        catch (IOException e) 
        {
            allLines = "ERROR: " + e.getMessage();
        }
        
        return allLines;
    }


	
	
	//Function to retrieve all lines of data
	public static int CountAllLinesOfData(String tFileName)
	{
        String filePath = tFileName; // Replace with your file path
        String line;
        int tNumOfLines = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            while ((line = br.readLine()) != null) 
            {
                // Process each line here...
            	tNumOfLines++;
            }
        } 
        catch (IOException e) 
        {
            line = "ERROR: " + e.getMessage();
        }
        
        return tNumOfLines;
    }


	
	//Function to retrieve all lines of data
	public static String[] GetArrayOfData(String tFileName)
	{
        String filePath = tFileName; // Replace with your file path
        String line;
        
        //Create array
        
        //(1) Orig Way....Guess and Go High, Way High
        //String[] AllLines = new String[1000000];

        // (2) More efficient in one way, but not another
        int numOfLines = CountAllLinesOfData(tFileName);
        String[] AllLines = new String[numOfLines];
        int counter=0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
            while ((line = br.readLine()) != null) 
            {
                // Process each line here...
            	AllLines[counter] = line;
            	counter++;
            }
        } 
        catch (IOException e) 
        {
            AllLines[counter] = "ERROR: " + e.getMessage();
        }
        
        return AllLines;
    }

	

}

//***** NEW FEATURES ADDED (END) *****
