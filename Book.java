//Book Class is a Blueprint to creating 
// Book Objects (vars)

// import the LocalDateTime class
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;

public class Book 
{
    //The following variables (properties) exist within 
	//  a Book 
	//(Public means all can access: Wallet in the hallway)
	//CREATE USER INPUT FOR THESE
    private String title;					//Already checking for bad words
    private String authorFirst;		//Check for bad words in the set
    private String authorLast;		// Same thing here
    public String Email;				//Min of 8 chars, Max of 75
    public LocalDate DatePublished;  //Do not ask user for this
    private int pages;					//Already checking
    private double Price;				//CHeck if it is a Double (driver) & Positive value (here)
    public String Feedback;
    
 //Default constructor   
    public Book()
    {
    	Feedback="";
    	title = "";
    	authorFirst ="";
    	authorLast = "";
    	Email = "";
    	DatePublished = LocalDate.now();
    	pages = 0;
    	Price = 0.0;
    }
    
    //Overloaded Constructor that gets Title, and Author's name
    public Book(String tTitle, String tAuthFirst, String tAuthLast)
    {
    	Feedback="";
    	SetTitle( tTitle);							//Protect out info from Bad Incoming data
    	SetAuthFirst(tAuthFirst);
    	SetAuthLast( tAuthLast);
    	Email = "";
    	DatePublished = LocalDate.now();
    	pages = 0;
    	Price = 0.0;
    }
 

    
    //Overloaded Constructor that receives and sets all info
    public Book(String tTitle, String tAuthFirst, String tAuthLast, String tEmail, LocalDate tDatePublished, int tPages, double tPrice)
    {
    	Feedback="";
    	SetTitle( tTitle);							//Protect out info from Bad Incoming data
    	SetAuthFirst(tAuthFirst);
    	SetAuthLast( tAuthLast);
    	SetDatePublished(tDatePublished);
    	SetDatePublished(tDatePublished);
    	SetPages(tPages);
    	SetPrice(tPrice);
    }
 

    
    
    
    //*********** Sets and Gets for Title **********************
    public String GetTitle()
    {
    	return title;
    }
    
    public void SetTitle(String tTitle)
    {
    	//if tTitle does not contain bad words, store it
    	if (Validation.GotBadWord(tTitle) == false)
    	{
    		title = tTitle;  //if good, store it.
    	}
    	else
    	{
    		//If it is bad, make it an empty string
    		Feedback = "\nERROR: This Title Contains Bad Words, such as Poop.";    		
    	}
    }
    
    
    //*********** Sets and Gets for Authors First Name **************
	public String GetAuthFirst()
	{
		return authorFirst;
	}
	
	public void SetAuthFirst(String tAuthorFirst)
	{
		//Check for bad words
		authorFirst = tAuthorFirst;
	}
    	
	  //*********** Sets and Gets for Authors Last Name **************
   public void SetAuthLast(String tAuthLast)
   {
	   //Check for bad words
	   this.authorLast = tAuthLast;
   }
	
	public String GetAuthLast()
	{
		return authorLast;
	}

	
	
  //*********** Sets and Gets for Author's Email **************
public void SetEmail(String tEmail)
{
   //Check for Good email???
   this.Email = tEmail;
}


public String GetEmail()
{
	return Email;
}



//*********** Sets and Gets for DatePublished **************
public void SetDatePublished(LocalDate tDatePublished)
{
 //Check for Good email???
 this.DatePublished = tDatePublished;
}


public LocalDate GetDatePublished()
{
	return DatePublished;
}



	
   	
	  //*********** Sets and Gets for # of pages (Int) **************
   public void SetPages(int tPages)
   {
	   if (Validation.IsWithinRange(tPages, 10, 3000))
	   {
		   this.pages = tPages;
	   }
	   else
	   {
		   Feedback += "\nERROR: Sorry this number needs to be between 10 and 3000 pages.";
	   }
	   
   }
	
	public int GetPages()
	{
		return pages;
	}

	
	
   	
	  //*********** Sets and Gets for Price (Double) **************
 public void SetPrice(double tPrice)
 {
	   if (Validation.IsWithinRange(tPrice, 1.00, 3000.00))
	   {
		   this.Price = tPrice;
	   }
	   else
	   {
		   Feedback += "\nERROR: Sorry the price needs to be between $1 and $3000.";
	   }
	   
 }
	
	public double GetPrice()
	{
		return Price;
	}

	
	
	public String OutputOneRecord_CSV()
	{
		String tOutput = "";
		//Concatenate individual pieces into tOutput
		// NEW - I updated this to merge the data in comma-delimtted format
		if (this.Feedback.contains("ERROR") == false)
		{
			tOutput = this.GetTitle();
			tOutput += ("," + this.GetAuthFirst() + ", " + this.GetAuthLast());
			tOutput += ("," + this.Email);
			tOutput += ("," + this.DatePublished.toString());
			tOutput += ("," + this.GetPages());
			tOutput += ("," + this.Price);
		}
		
		return tOutput;
	}

	
	
	//***** NEW FEATURES ADDED (START) *****
		
	//***********************************
	//***NEW*** 
	//Helper function that receives CSV String and parses 
	//the one book from it 
	//Title, AuthorFirst,AuthorLast,Email,DatePublished,Pages,Price
	//*******************************
	public static Book ParsingOneBook(String tCsvBook)
	{
		Book tBook = new Book();
		
		//If there is data within the current string, Parse it 
		if (tCsvBook.length() > 0 )
		{
			
			//***First End Piece ***
			//Parse out the Title and store it 
			//Things we know...'
			// If length is greater than zero, it is filled.
			//First character starts at position 0
			//The first comma, comes directly after Title.
			// Example:Star Wars,George,Lucas
			int pos1 = 0; //Starting position -> "S" is at 0 position
			//find the position of the first comma -> position 9
			int pos2 = tCsvBook.indexOf(",", 0);
			//Trick Pos2 is also the # of chars to copy
			tBook.SetTitle( tCsvBook.substring(pos1, pos2) );
			
			
			
			//**** MIDDLE PIECE (1 of many)
			//Parse out the AuthorFirstName and store it
			//The first position will be a copy from is approximately
			// where we ended with the last piece data (title)
			//The second position will be the position of the second comma.
			// Just rememeber that when we use substring, we give it the
			// starting point and end point
			
			//We do not want to copy the comma, so me move one spot
			// to the right of the comma.
			pos1 = pos2 + 1;
			//Get next (2nd) comma, by starting the find at pos1
			pos2 = tCsvBook.indexOf(",", pos1);
		
			//Using example : Star Wars,George,Lucas and prior data, 
			// Pos1 is 10, Pos2 should be 16, and we want to copy George (6 chars)
			
			//Trick Pos2-Pos1 is also the # of chars to copy
			tBook.SetAuthFirst( tCsvBook.substring(pos1, pos2) );
		
			
			
			
			//**** MIDDLE PIECE (1 of many - WASH, RINSE, REPEAT)
			//Parse out the AuthorLastName and store it
			//Do exactly what we did with First Name
			// ALL MIDDLE PIECES USE THE SAME ALGORITHM
			
			//We do not want to copy the comma, so me move one spot
			// to the right of the comma.
			pos1 = pos2 + 1;
			//Get next (2nd) comma, by starting the find at pos1
			pos2 = tCsvBook.indexOf(",", pos1);
			
			//Trick Pos2-Pos1 is also the # of chars to copy
			tBook.SetAuthLast( tCsvBook.substring(pos1, pos2) );
			
			
			
			//**** MIDDLE PIECE (1 of many - WASH, RINSE, REPEAT)
			//Parse out the AuthorLastName and store it
			//Do exactly what we did with First Name
			// ALL MIDDLE PIECES USE THE SAME ALGORITHM
			
			//We do not want to copy the comma, so me move one spot
			// to the right of the comma.
			pos1 = pos2 + 1;
			//Get next (2nd) comma, by starting the find at pos1
			pos2 = tCsvBook.indexOf(",", pos1);
			
			//Trick Pos2-Pos1 is also the # of chars to copy
			tBook.SetEmail( tCsvBook.substring(pos1, pos2) ); 
			
			
			
			//**** MIDDLE PIECE (1 of many - WASH, RINSE,REPEAT)
			//Parse out the AuthorLastName and store it
			//Do exactly what we did with First Name
			// ALL MIDDLE PIECES USE THE SAME ALGORITHM
			
			//We do not want to copy the comma, so we move one spot
			// to the right of the comma.
			pos1 = pos2 + 1;
			//Get next (2nd) comma, by starting the find at pos1
			pos2 = tCsvBook.indexOf(",", pos1); 
			
			//Trick Pos2-Pos1 is also the # of chars to copy
			//Also need to convert the string back to a LocalDate
			// Specify the format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate tDate = LocalDate.parse((tCsvBook.substring(pos1, pos2)), formatter);
			tBook.SetDatePublished(tDate);
			
			
			
			
			//**** MIDDLE PIECE (1 of many - WASH, RINSE, REPEAT)
			//Parse out the AuthorLastName and store it
			//Do exactly what we did with First Name
			// ALL MIDDLE PIECES USE THE SAME ALGORITHM
		
			//We do not want to copy the comma, so me move one spot
			// to the right of the comma.
			pos1 = pos2 + 1;
			//Get next (2nd) comma, by starting the find at pos1
			pos2 = tCsvBook.indexOf(",", pos1);
			
			//Trick Pos2-Pos1 is also the # of chars to copy
			//Convert to integer, store it, then place it in Book
			int tPages = Integer.parseInt( ( tCsvBook.substring(pos1, pos2) ) );
			tBook.SetPages(tPages);
			
			
			
			//*** LAST END PIECE ***
			//Parse out the Price and Store it
			//The first position will be a copy from is approximately where we
			// ended with the last piece data (Pages)
			//The second position, seeing there should not be a comma at the end,
			// should be calculated via the string length
			// Just remember that when we use substring, we give it the starting
			// point and how many chars to copy
			//We do not want to copy the comma, so
			//we move one spot to the right of the comma.
			pos1 = pos2 + 1;
			//# of chars to copy
			pos2 = tCsvBook.length();
			
			//Convert to double, store it, then place it in Book
			double tPrice = Double.parseDouble(tCsvBook.substring(pos1, pos2));
			tBook.SetPrice(tPrice);

		}
		return tBook; //Return the filled in book
	}
		
	
		//*********************************************************
		// *** NEW ***
		// This function will receive array of comma-delimitted data,
		// and we must parse it back into arrays of usable data for books
		//Record format (Each string in array):
		// Title,AuthorFirst, AuthorLast,Email,DatePublished,Pages,Price
		//*********************************************************
		
		
		public static Book[] ParsingBooksData(String[] tArrayOfData)
				{
				//Create an blank array of books
				Book[] tBooks = new Book[tArrayOfData.length];
				
				//Loop through each row of Book Data String in the array )
				for (int ctr = 0; ctr < tArrayOfData.length; ctr++)
				{
				//If there is data within the current row of data, Parse it
				if (tArrayOfData[ctr].length() > 0)
				{
				//Parse the current row of data in the array
				Book tBook = ParsingOneBook(tArrayOfData[ctr]);
				//if the book is not null, add it to our array of books
				if (tBooks != null)
				{
					tBooks[ctr] = tBook;
				}
			}
		}
				//Return the filled in Array of Books.
				return tBooks;	
	}
	
	//***** NEW FEATURES ADDED (END) *****


}
