package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	@Override
	public void checkout(String id, String isbn) throws CheckoutException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> booksMap = da.readBooksMap();
		HashMap<String,LibraryMember> memberMap = da.readMemberMap();
		if(!memberMap.containsKey(id)) {
			throw new CheckoutException("ID " + id + " not found");
		}
		LibraryMember member = memberMap.get(id);
		if(!booksMap.containsKey(isbn)) {
			throw new CheckoutException("ISBN " + isbn + " not found");
		}
		Book book = booksMap.get(isbn);
		if(!book.isAvailable()) {
			throw new CheckoutException("ISBN "+ isbn + " is not available");
		}
		
		BookCopy bookCopy = book.getNextAvailableCopy();
		bookCopy.changeAvailability();
		book.updateCopies(bookCopy);
		CheckoutRecord checkoutRecord = member.getCheckoutRecord();
		CheckoutEntry checkoutEntry = new CheckoutEntry( bookCopy, checkoutRecord);
		checkoutRecord.addCheckoutEntry(checkoutEntry);
		member.setCheckoutRecord(checkoutRecord);
		da.updateMember(member);
		//da.updateBook(book);
		
		
	}
	
	
}
