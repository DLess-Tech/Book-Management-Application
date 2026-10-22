
public class Validation 
{

	//*********************************************************************
	//Function to validate incoming String as an Integer
	//*********************************************************************
	public static boolean IsValidInteger(String temp)
	{
		boolean result = false;
		int test ;
		
		//attempt something, but be ready for potential error/crash
		try
		{
			test = Integer.parseInt(temp);
			result = true;
		}
		//if exception (error) occurs, then it is not a valid integer
		catch (NumberFormatException e)
		{
			result = false;
		}
		//finally section runs no matter error or not
		finally
		{
			//Do something that you want/need to no matter
			// whether there is an error or not
		}
		
		return result;	//cannot put a return in a finally		
	}

	

	//*********************************************************************
	//Function to validate incoming String as a Double
	//*********************************************************************
	public static boolean IsValidDouble(String temp)
	{
		boolean result = false;
		double test ;
		
		//attempt something, but be ready for potential error/crash
		try
		{
			test = Double.parseDouble(temp);
			result = true;
		}
		//if exception (error) occurs, then it is not a valid double
		catch (NumberFormatException e)
		{
			result = false;
		}
		//finally section runs no matter error or not
		finally
		{
			//Do something that you want/need to no matter
			// whether there is an error or not
		}
		
		return result;	//cannot put a return in a finally
		
	}

	

	//*********************************************************************
	//Function to validate incoming String as an Float
	//*********************************************************************
	public static boolean IsValidFloat(String temp)
	{
		boolean result = false;
		float test ;
		
		//attempt something, but be ready for potential error/crash
		try
		{
			test = Float.parseFloat(temp);
			result = true;
		}
		//if exception (error) occurs, then it is not a valid float
		catch (NumberFormatException e)
		{
			result = false;
		}
		//finally section runs no matter error or not
		finally
		{
			//Do something that you want/need to no matter
			// whether there is an error or not
		}
		
		return result;	//cannot put a return in a finally
		
	}

	

	//*********************************************************************
	//Function to validate incoming String as an Integer
	//*********************************************************************
	public static boolean IsValidBoolean(String temp)
	{
		boolean result = false;
		boolean test ;
		
		//attempt something, but be ready for potential error/crash
		try
		{
			test = Boolean.parseBoolean(temp);
			result = true;
		}
		//if exception (error) occurs, then it is not a valid Boolean (true/false)
		catch (NumberFormatException e)
		{
			result = false;
		}
		//finally section runs no matter error or not
		finally
		{
			//Do something that you want/need to no matter
			// whether there is an error or not
		}
		
		return result;	//cannot put a return in a finally
		
	}
	
	

	//*********************************************************************
	//Function to validate incoming String has a specified length
	//*********************************************************************
	public static boolean IsExactLength(String temp, int len)
	{
		boolean result = false;
				
		//get the length and compare
		int length = temp.length();
		
		return (length == len);
		
	}

	

	//*********************************************************************
	//Function to validate incoming String is between a min & 
	//   max length (both inclusive)
	//*********************************************************************
	public static boolean IsLengthBetween(String temp, int min, int max)
	{
		boolean result = false;
				
		//get the length and compare
		int length = temp.length();
		
		return (length >= min && length <= max);
		
	}



	

	//*********************************************************************
	//Function to check if string is all digits
	//*********************************************************************
	public static boolean IsAllDigits(String temp)
	{
		boolean result = true;
				
		//Loop thru each character in the string and check if it is a digit
		for (char tchar: temp.toCharArray())
		{
			if (Character.isDigit(tchar) == false)
				result = false;
		}
		
		return (result);
		
	}


	
	//*********************************************************************
	//Function to check if string is a valid Zip code 
	//   (All-digits and 5 chars)
	//*********************************************************************
	public static boolean IsValidZip(String temp)
	{		
		return (IsAllDigits(temp) && IsExactLength( temp, 5));
	}

	
	//*********************************************************************
	//Function to check if string is a valid Area code 
	//   (All-digits and 3 chars)
	//*********************************************************************
	public static boolean IsValidArea(String temp)
	{		
		return (IsAllDigits(temp) && IsExactLength( temp, 3));
	}
	
	
	
	//*********************************************************************
	//Function to check if string is a valid Phone # 
	//   (All-digits and 10 chars)
	//*********************************************************************
	public static boolean IsValidAreaPhone(String temp)
	{		
		return (IsAllDigits(temp) && IsExactLength( temp, 10));
	}

	
	//Function to check for bad words
	public static boolean GotBadWord(String tString)
	{
		return (tString.toUpperCase().contains("POOP"));
	}
	
	
	//Check if an integer value is between two numbers
	public static boolean IsWithinRange(int tInt, int tMin, int tMax)
	{
		return (tInt >= tMin && tInt <= tMax);
	}

	
	//Check if an integer value is between two numbers
	public static boolean IsWithinRange(double tInt, double tMin, double tMax)
	{
		return (tInt >= tMin && tInt <= tMax);
	}
	

	
}
