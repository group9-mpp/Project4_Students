package ui;

import java.io.IOException;
import java.util.HashMap;

import com.sun.xml.internal.ws.util.StringUtils;

import business.AddMemberException;
import business.Address;
import business.Book;
import business.BookNotFoundException;
import business.LibraryMember;
import business.SystemController;
import dataaccess.DataAccessFacade;
import dataaccess.SiteFeatures;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddNewBookCopyWindow extends Stage implements LibWindow {
	public static final AddNewBookCopyWindow INSTANCE = new AddNewBookCopyWindow();
	private boolean isInitialized = false;

	@FXML
	private Button btnAddCopy = new Button();
	@FXML
	private TextField txtISBN = new TextField();

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	public AddNewBookCopyWindow() {
	}

	public void start() {
		try {
			// Load layout from fxml file.
			AnchorPane rootLayout = (AnchorPane) FXMLLoader
					.load(AddNewBookCopyWindow.class.getResource("AddNewBookCopyWindow.fxml"));
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			setTitle("Add A New Book Copy");
			setScene(scene);
			show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean entriesAreValid(String isbn) {
		return true;
	}

	public void addBookCopy(ActionEvent event) {
		String isbn = txtISBN.getText().trim();
		boolean entriesAreValid = entriesAreValid(isbn);

		if (entriesAreValid) {
			DataAccessFacade dataAccessObject = new DataAccessFacade();
			HashMap<String, Book> booksMap = dataAccessObject.readBooksMap();
			if (booksMap.containsKey(isbn)) {
				Book book = booksMap.get(isbn);
				book.addCopy();
				dataAccessObject.updateBook(book);
				System.out.println("Book Copy Added Successfully");
			} else {
				System.out.println("Book Not Found!");
			}

		} else {
			System.out.println("Please check your values for errors");
		}

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		start();

	}

}
