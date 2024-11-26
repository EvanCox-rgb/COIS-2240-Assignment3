import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryManagementTest {							

	@Test								//needed to, y'know.... test?
	public void testBookId() {			//method header
		try {
			Book book1 = new Book(100, "book 100");			//instantiate 2 books we expect to be valid and not throw exception
			Book book2 = new Book(999, "book 999");
		}catch(Exception e) {		//if there is an unexpected exception with initializing book1 or book2. test will fail, print exception message
			fail("book1 or book2 threw exception Error" + e.getMessage());
		}		
		try {											//try instantiating new invalid book. Id: 1000 is out of range
			Book book3 = new Book(1000,"book 1000");	
			fail("book 3 did not throw exception");		//test will fail if this does not throw an exception as we expect it to
		}catch (Exception e) {							//catch the expected exception and print out error message
			System.out.println("Error: "+ e.getMessage());	
			assertEquals("the id 1000 is invalid", e.getMessage());		//test will fail if exception message is not the same as provided string
		}
		try {											//try instantiation new invalid book. Id 99 is out of range
			Book book4 = new Book(99, "book 99");		
			fail("book 4 did not throw exception");		//test will fail if this does not throw an exception as we expect it to
		}catch(Exception e) {							//catch the expected exception and print out error message
			System.out.println("Error: " + e.getMessage());
			assertEquals("the id 99 is invalid", e.getMessage());		//test will fail if exception message is not the same as provided string
		}
	}
	
}
