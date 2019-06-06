package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	List<LibraryMember> allMembers();
	public void saveNewMember(LibraryMember member) throws AddMemberException;
	public LibraryMember getMember(String memberId);
	public void updateMember(LibraryMember member);
	List<Book> allBooks();
	List<Author> allAuthors();
	public void updateBook(Book book);
	public CheckoutRecord checkout(String id, String isbn)  throws CheckoutException;
	public LibraryMember getCheckoutRecord(String id) throws CheckoutException;
	
}
