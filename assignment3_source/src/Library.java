import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members = new ArrayList<Member>();
    private List<Book> books = new ArrayList<Book>();

    // Add a new member to the library
    public boolean addMember(Member member) {			//method constructor takes member passed from main, containing name and id
    	int id = member.getId();					//get the id and put it in variable id
    		if(findMemberById(id)==null) {			//if the id does not exist already
    			members.add(member);				//add the member to members array
    			System.out.println("Member added successfully.");	//notify user
    			return true;										//return true
    		}														//if id does exist, dont add, notify user, return false
    		else {
    			System.out.println("This member ID already exists. Please chose new ID");
    			return false;
    		}
    }
    // Add a new book to the library
    public boolean addBook(Book book) {				//method constructor takes book passed from main, containing title and id
    	int id = book.getId();						//get the id and put it in variable id
		if(findBookById(id)==null) {				//if the id does not exist already
			books.add(book);						//add the book to books array
			System.out.println("Book added successfully.");		//notify user
			return true;										//return true
		}														//if id does exist, dont add, notify user, return false
		else {
			System.out.println("This book ID already exists. Please chose new ID");
			return false;
		}
    }
    // Find a member by ID
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // Find a book by ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // Get the list of members
    public List<Member> getMembers() {
        return members;
    }
    
    // Get the list of books
    public List<Book> getBooks() {
        return books;
    }
}
