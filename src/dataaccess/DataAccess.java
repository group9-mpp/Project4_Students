package dataaccess;

import java.util.HashMap;

import business.AddMemberException;
import business.Book;
import business.BookNotFoundException;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member) throws AddMemberException; 
	public void updateMember(LibraryMember member);
	public void updateBook(Book book);
}
