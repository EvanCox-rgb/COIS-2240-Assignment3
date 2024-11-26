import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
public class Transaction {

	ArrayList<String> logs;
    // Perform the borrowing of a book
	private static Transaction instance;		//static reference for our singleton design
	public Transaction() {}						//non static constructor
	
	public void saveTransaction(String transaction){				//method declaration. will be passed transaction info
		try {														//throw any exceptions
			ArrayList<String> prevLogs = new ArrayList<String>();	//declare a new array list. will be filled with .txt's previous content
																	//new declaration each method call prevents duplicate info being stored
			BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"));	//read .txt file
			String line;											//will be compared to .txt lines to verify not null
			while((line = reader.readLine()) != null){				//loop through .txt file until there is no more readable content
				prevLogs.add(line);									//each line will be added as a new element is prevLogs
				logs = prevLogs;									//logs is the permanent log holder
			}														//at that point the logs will already be saved in the .txt file
			reader.close();											//stop reader
			
			if (logs!=null) {			//if logs holds elements, then loop through each element, overiding old .txt file with old values + new ones
				BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt"));
				for (String logLine: logs) {
					writer.write(logLine + "\n");
				}
				writer.write(transaction + "\n");
				writer.close();
			}							//if logs holds no elements (e.i empty) then just write the transaction to the .txt file
			else {
				BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt"));
				writer.write(transaction + "\n");
				writer.close();
			}
			
		} catch (IOException e) {		//print out StackTrace if an exception occurs
			e.printStackTrace();
		}
	}
	
	public void displayTransactionHistory() {	//method declaration
		boolean emptyLogs = true;				//will be used to verify if there are no transactions in the .txt file, avoiding exceptions
		try {									//throw any exceptions
			BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"));	//same as saveTransaction() code
			String line;
			while((line = reader.readLine()) != null){
				System.out.println(line + "\n");
				emptyLogs = false;  //obviously if there are lines in the .txt file to read, then the logs are not be empty 
			}
			reader.close();			//stop reader
			if (emptyLogs == true) 	//if logs are empty notify user
				System.out.println("No transactions found");
		  } 
		  catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }
    
    public static Transaction getTransaction() {	//getter for our Transaction instance
    	if (instance == null) {
    		instance = new Transaction();
    	}
    	return instance;
    }

    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}