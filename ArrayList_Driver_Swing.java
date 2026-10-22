

//Include Swing and AWT libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.*;
import java.time.format.*;

//Class with the design (View)
public class ArrayList_Driver_Swing
{
    //Initial Tools we need to lay out our stuff in any Swing Project
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JPanel formPanel;

    int bookCtr; //Create a counter/pointer to our current record of data

    //**** NEW Moving from an Array of Books to and ArrayList of Books
    //NOW We create an array list of Books
    MyList<Book> tBookList = new MyArrayList<>();

    //Use this constructor to set up our GUI
    public ArrayList_Driver_Swing()
    {
        bookCtr = 0;    //Initialize counter to first record
        prepareBasicGUI();
    }

    //Set up our default Window (FRAME) with some basic stuff like
    // Panels to add items to, then add some output labels
    private void prepareBasicGUI()
    {
        //Create a Frame (Window) and add a Grid Layout
        mainFrame = new JFrame("Java ARRAYLIST SWING Examples");
        mainFrame.setSize(1200,800);
        mainFrame.setLayout(new GridLayout(4,1));

        //Initialize our standard window components
        headerLabel = new JLabel("",JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350, 100);

        //Add a listener for closing the window
        // Telling the nosey neighbor (Gert) to report when Close
        // is clicked. If it is, respond by exiting the program
        mainFrame.addWindowListener(new WindowAdapter()
        {
            //When user clicks the close button, Exit this program
            public void windowClosing(WindowEvent windowEvent)
            {
                System.exit(0);
            }
        });

        //Add a new panel (controlPanel) and give it a Flow layout
        // for Good & Bad buttons
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        //Create another panel for our form and the submit button
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8,2));

        //Add our header & status labels, as well as the control panel
        //Think of Grid...4 Rows, 1 Column
        mainFrame.add(headerLabel);     //Row 1
        mainFrame.add(controlPanel);    //Row 2
        mainFrame.add(formPanel);       //Row 3
        mainFrame.add(statusLabel);     //Row 4

        //Set the main frame (Window) visible
        mainFrame.setVisible(true);
    }

    //Method to create the personalized version of the user form
    private void CreateImplementGUI()
    {
        //Header for our GUI
        headerLabel.setText("Book ArrayList Tracker: Swing");

        //**** Start form fields **************************************

        //Title
        JLabel lblTitle = new JLabel("Book Title: ");
        final JTextField txtTitle = new JTextField(20);

        //Author First
        JLabel lblAuthFirst = new JLabel("Author First: ");
        final JTextField txtAuthFirst = new JTextField(20);

        //Author Last
        JLabel lblAuthLast = new JLabel("Author Last: ");
        final JTextField txtAuthLast = new JTextField(20);

        //Author Email
        JLabel lblEmail = new JLabel("Email: ");
        final JTextField txtEmail = new JTextField(20);

        //Published Date
        JLabel lblDatePublished = new JLabel("Publish Date: ");
        final JTextField txtDatePublished = new JTextField(20);

        //Set initial date to today, formatted the local date formatting
        DateTimeFormatter formatters =
                DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String tDate = LocalDate.now().format(formatters);

        txtDatePublished.setText(tDate);

        //Pages
        JLabel lblPages = new JLabel("Pages: ");

        //Create a numeric Spinner...First Set up the parameters
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(10,     //initial value
                                        1,      //min
                                        5000,   //max
                                        1);     //step

        //Next use the model to configure the spinner
        JSpinner spinPages = new JSpinner(spinnerModel);

        //Price
        JLabel lblPrice = new JLabel("Price: $");
        final JTextField txtPrice = new JTextField(20);

        //************************************************************
        //Create Add Button and its event handler
        //************************************************************
        JButton addButton = new JButton("Add Book");
        addButton.setHorizontalAlignment(SwingConstants.CENTER);

        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Create a Book instance and set values individually.
                // Is there another way to create an instance and fill in values?
                Book temp = new Book();
                Timer tTimer = new Timer();

                //Individually fill in the values from the form
                temp.SetTitle(txtTitle.getText());
                temp.SetAuthFirst(txtAuthFirst.getText());
                temp.SetAuthLast(txtAuthLast.getText());
                temp.SetEmail(txtEmail.getText());

                try
                {
                    //Convert to LocalDate, store it, then place it in Book

                    DateTimeFormatter formatter =
                            DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    // Specify the format

                    LocalDate tDatePublished =
                            LocalDate.parse(txtDatePublished.getText(),
                                            formatter);

                    temp.SetDatePublished(tDatePublished);

                    //Convert to integer, store it, then place it in Book
                    int tPages =
                            Integer.parseInt(spinPages.getValue().toString());
                    temp.SetPages(tPages);

                    //Convert to double, store it, then place it in Book
                    double tPrice =
                            Double.parseDouble(txtPrice.getText());
                    temp.SetPrice(tPrice);
                }
                catch (NumberFormatException err)
                {
                    temp.Feedback += "ERROR: Numeric data not correct.";
                }
                catch (DateTimeParseException err)
                {
                    temp.Feedback +=
                            "ERROR: The Publish date data not correct.";
                }

                //After book is filled in...Do we print it or do we print errors?
                if (temp.Feedback.contains("ERROR:") == false)
                {
                    //Retrieve the new Book as a Comma-Delimited string
                    //Also known as Comma-Separated Values (CSV)
                    String data = temp.OutputOneRecord_CSV();

                    //******NEW: Storing Data ************************
                    String tResults = "";

                    // (3) *** NEW *** Appending the data to the file
                    tResults = FileIO.AppendToFile(data, "Books.csv");

                    // Append the data to our Array List
                    tTimer.setStart();      //Start Timer
                    tBookList.add(temp);    //Add to end of list
                    tTimer.setEnd();        //End Timer

                    statusLabel.setText(
                            "Book Added to End of List: "
                            + tTimer.getElapsedNano() + " NanoSecs");
                }
                else
                {
                    statusLabel.setText(temp.Feedback);
                }
            }
        });

        //************************************************************

        //************************************************************
        //Create Add At Front Button and its event handler
        //************************************************************
        JButton addAtFrontButton =
                new JButton("Add Book at Front of List");
        addAtFrontButton.setHorizontalAlignment(SwingConstants.CENTER);

        addAtFrontButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Create a Book instance and set values individually.
                // Is there another way to create an instance and fill in values?
                Book temp = new Book();
                Timer tTimer = new Timer();

                //Individually fill in the values from the form
                temp.SetTitle(txtTitle.getText());
                temp.SetAuthFirst(txtAuthFirst.getText());
                temp.SetAuthLast(txtAuthLast.getText());
                temp.SetEmail(txtEmail.getText());

                try
                {
                    //Convert to LocalDate, store it, then place it in Book

                    DateTimeFormatter formatter =
                            DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    // Specify the format

                    LocalDate tDatePublished =
                            LocalDate.parse(txtDatePublished.getText(),
                                            formatter);

                    temp.SetDatePublished(tDatePublished);

                    //Convert to integer, store it, then place it in Book
                    int tPages =
                            Integer.parseInt(spinPages.getValue().toString());
                    temp.SetPages(tPages);

                    //Convert to double, store it, then place it in Book
                    double tPrice =
                            Double.parseDouble(txtPrice.getText());
                    temp.SetPrice(tPrice);
                }
                catch (NumberFormatException err)
                {
                    temp.Feedback += "ERROR: Numeric data not correct.";
                }
                catch (DateTimeParseException err)
                {
                    temp.Feedback +=
                            "ERROR: The Publish date data not correct.";
                }

                //After book is filled in...Do we print it or do we print errors?
                if (temp.Feedback.contains("ERROR:") == false)
                {
                    //Retrieve the new Book as a Comma-Delimited string
                    //Also known as Comma-Separated Values (CSV)
                    String data = temp.OutputOneRecord_CSV();

                    //*****NEW: Storing Data ************************
                    String tResults = "";

                    // (3) *** NEW *** Appending the data to the file
                    tResults = FileIO.AppendToFile(data, "Books.csv");

                    // Append the data to our Array List
                    tTimer.setStart();      //Start Timer
                    tBookList.add(0, temp); //Add at the front of the list
                    tTimer.setEnd();        //End the timer

                    statusLabel.setText(
                            "Book Added to Front of List: "
                            + tTimer.getElapsedNano() + " NanoSecs");
                }
                else
                {
                    statusLabel.setText(temp.Feedback);
                }
            }
        });

        //************************************************************


        //************************************************************
        //Create Button to fill form with good data and its event handler
        //************************************************************
        JButton goodButton = new JButton("Good Sample");

        goodButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                txtTitle.setText("Optimus Flies a Kite");
                txtAuthFirst.setText("Scotticus");
                txtAuthLast.setText("Prime");
                txtEmail.setText("Scotticus@Primes.com");
                txtDatePublished.setText("05/04/1977");
                txtPrice.setText("3.99");
            }
        });
        //************************************************************


        //************************************************************
        //Create Button to fill form with good data and its event handler
        //************************************************************
        JButton badButton = new JButton("Bad Sample");

        badButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                txtTitle.setText("Optimus Steps in Poopy");
                txtAuthFirst.setText("ScotticusPoop");
                txtAuthLast.setText("PoopyPrime");
                txtEmail.setText("NotAnEmail");
                txtDatePublished.setText("A while Ago");
                txtPrice.setText("A lot");
            }
        });
        //************************************************************


        //************************************************************
        //Create Button to READ DATA FILE INTO ARRAYLIST OF BOOKS
        //************************************************************
        JButton readButton = new JButton("Read DataFile");

        readButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Read the data file
                String[] ArrayOfData = new String[100000000];
                ArrayOfData = FileIO.GetArrayOfData("Books.csv");
                Timer tTimer = new Timer();

                tTimer.setStart(); //Start Timer

                // Now Fill our Book ArrayList
                //Loop through each row of Book Data String in the array
                for (int ctr = 0; ctr < ArrayOfData.length; ctr++)
                {
                    //If there is data within the current row of data, Parse it
                    if (ArrayOfData[ctr].length() > 0)
                    {
                        //Parse the current row of data in the array
                        Book tBook = Book.ParsingOneBook(ArrayOfData[ctr]);

                        //If the book is not null, add it to our array of books
                        if (tBook != null)
                        {
                            tBookList.add(tBook);
                        }
                    }
                }

                tTimer.setEnd(); //End timer

                int ctr = 0; //NEW | re-adjusted the pointer to 1st record

                txtTitle.setText(tBookList.get(bookCtr).GetTitle());
                txtAuthFirst.setText(
                        tBookList.get(bookCtr).GetAuthFirst());
                txtAuthLast.setText(
                        tBookList.get(bookCtr).GetAuthLast());
                txtEmail.setText(
                        tBookList.get(bookCtr).GetEmail());
                txtDatePublished.setText(
                        tBookList.get(bookCtr).GetDatePublished().toString());
                spinPages.setValue(
                        tBookList.get(bookCtr).GetPages());
                Double tPrice = tBookList.get(bookCtr).GetPrice();
                txtPrice.setText(tPrice.toString());

                statusLabel.setText(
                        "Read small file: "
                        + tTimer.getElapsedNano() + " NanoSecs");
            }
        });


        //************************************************************
        //NEW START

        //Create Button to IMPORT SAMPLE DATA INTO
        //ARRAYLIST OF BOOKS ONE AT ATIME (Think Online Sales)

        JButton importButton = new JButton("Import Data");

        importButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Read the data file
                String[] ArrayOfData = new String[100000000];
                ArrayOfData = FileIO.GetArrayOfData("random_books.csv");

                // Now Fill our Book array

                Timer tTimer = new Timer(); //Create timer

                tTimer.setStart(); //start timer

                //Loop through each row of Book Data String in the array
                for (int ctr = 0; ctr < ArrayOfData.length; ctr++)
                {
                    //If there is data within the current row of data, Parse it
                    if (ArrayOfData[ctr].length() > 0)
                    {
                        //Parse the current row of data in the array
                        Book tBook = Book.ParsingOneBook(ArrayOfData[ctr]);

                        //If the book is not null, add it to our array of books
                        if (tBook != null)
                        {
                            tBookList.add(tBook);
                        }
                    }
                }

                tTimer.setEnd(); //End timer

                int ctr = 0; //NEW | re-adjusted the pointer to 1st record

                txtTitle.setText(tBookList.get(bookCtr).GetTitle());
                txtAuthFirst.setText(
                        tBookList.get(bookCtr).GetAuthFirst());
                txtAuthLast.setText(
                        tBookList.get(bookCtr).GetAuthLast());
                txtEmail.setText(
                        tBookList.get(bookCtr).GetEmail());
                txtDatePublished.setText(
                        tBookList.get(bookCtr).GetDatePublished().toString());
                spinPages.setValue(
                        tBookList.get(bookCtr).GetPages());
                Double tPrice = tBookList.get(bookCtr).GetPrice();
                txtPrice.setText(tPrice.toString());

                statusLabel.setText(
                        "Import large file: "
                        + tTimer.getElapsedNano() + " NanoSecs");
            }
        });

        //NEW END


        //************************************************************
        // Button to change the index # to the prior position in array &
        // fill out the form
        JButton priorButton = new JButton(" <<");

        priorButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Create and Start timer
                Timer tTimer = new Timer();
                tTimer.setStart();

                //NEW VERSION USING ARRAY LIST

                //Decrement the counter / pointer to the next record
                bookCtr--;

                //Just in case, if we go beyond the arrays bounds,
                //bring them back
                if (bookCtr < 0)
                {
                    //fix it??? Move it back to first record
                    bookCtr = 0;

                    //If we wanted to loop back to bottom of array
                    //bookCtr = tBooks.length-1;
                }

                //Fill the form from a specific row's data
                txtTitle.setText(tBookList.get(bookCtr).GetTitle());
                txtAuthFirst.setText(
                        tBookList.get(bookCtr).GetAuthFirst());
                txtAuthLast.setText(
                        tBookList.get(bookCtr).GetAuthLast());
                txtEmail.setText(
                        tBookList.get(bookCtr).GetEmail());
                txtDatePublished.setText(
                        tBookList.get(bookCtr)
                                .GetDatePublished().toString());
                spinPages.setValue(
                        tBookList.get(bookCtr).GetPages());
                Double tPrice = tBookList.get(bookCtr).GetPrice();
                txtPrice.setText(tPrice.toString());

                tTimer.setEnd(); //End the timer

                statusLabel.setText(
                        "Shifting one record: "
                        + tTimer.getElapsedNano() + " NanoSecs");
            }
        });


        // Button to change the index # to the next position in array &
        // fill out the form
        JButton nextButton = new JButton(" >>");

        nextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Create and Start timer
                Timer tTimer = new Timer();
                tTimer.setStart();

                //NEW VERSION USING ARRAY LIST

                //Increment the counter / pointer to the next record
                bookCtr++;

                //Just in case, if we go beyond the arrays bounds,
                //bring them back
                if (bookCtr >= tBookList.size())
                {
                    //fix it??? Move it back to last record
                    bookCtr = tBookList.size()-1;

                    //If we wanted to loop back to top of array
                    //bookCtr = 0;
                }

                //Fill the form from a specific row's data
                txtTitle.setText(tBookList.get(bookCtr).GetTitle());
                txtAuthFirst.setText(
                        tBookList.get(bookCtr).GetAuthFirst());
                txtAuthLast.setText(
                        tBookList.get(bookCtr).GetAuthLast());
                txtEmail.setText(
                        tBookList.get(bookCtr).GetEmail());
                txtDatePublished.setText(
                        tBookList.get(bookCtr)
                                .GetDatePublished().toString());

                //Double tPrice = tBookList.get(ctr).GetPrice();

                spinPages.setValue(
                        tBookList.get(bookCtr).GetPages());
                Double tPrice = tBookList.get(bookCtr).GetPrice();
                txtPrice.setText(tPrice.toString());

                tTimer.setEnd(); //End the timer

                statusLabel.setText(
                        "Shifting one record: "
                        + tTimer.getElapsedNano() + " NanoSecs");
            }
        });

        //************************************************************

        //**** End form fields ***************************************

        //Add buttons to the Control Panel
        controlPanel.add(goodButton);
        controlPanel.add(badButton);

        controlPanel.add(readButton);      // *** Read button ***
        controlPanel.add(importButton);    // *** NEW Import button ***
        controlPanel.add(priorButton);     //*** (Prior << button) ***
        controlPanel.add(nextButton);      //*** (Next >> button) ***

        //Add our specific form labels,textfields, etc
        formPanel.add(lblTitle);
        formPanel.add(txtTitle);

        formPanel.add(lblAuthFirst);
        formPanel.add(txtAuthFirst);

        formPanel.add(lblAuthLast);
        formPanel.add(txtAuthLast);

        formPanel.add(lblEmail);
        formPanel.add(txtEmail);

        formPanel.add(lblDatePublished);
        formPanel.add(txtDatePublished);

        formPanel.add(lblPages);
        formPanel.add(spinPages);

        formPanel.add(lblPrice);
        formPanel.add(txtPrice);


        //Add add button
        formPanel.add(addButton);
        formPanel.add(addAtFrontButton);

        //Make frame visible
        mainFrame.setVisible(true);
    }

    public static void main(String[] args)
    {
        //Create an Instance of this GUI Driver class
        ArrayList_Driver_Swing Driver_Swing =
                new ArrayList_Driver_Swing();

        //Run code to generate this specific GUI and implement it
        Driver_Swing.CreateImplementGUI();
    }
}