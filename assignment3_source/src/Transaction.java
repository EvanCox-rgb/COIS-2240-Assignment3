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
	
	public void saveTransaction(String transaction){
		try {
			ArrayList<String> prevLogs = new ArrayList<String>();
			
			BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"));
			String line;
			while((line = reader.readLine()) != null){
				prevLogs.add(line);
				//System.out.println(line);
				logs = prevLogs;
			}
			reader.close();
			
			if (logs!=null) {
				BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt"));
				for (String logLine: logs) {
					writer.write(logLine + "\n");
				}
				writer.write(transaction + "\n");
				writer.close();
			}
			else {
				BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt"));
				writer.write(transaction + "\n");
				writer.close();
			}
			
		} catch (IOException e) {
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