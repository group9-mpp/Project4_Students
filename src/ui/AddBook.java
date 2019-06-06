package ui;

import java.util.ArrayList;
import java.util.List;

import business.Author;
import business.Book;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

public class AddBook extends BaseWindow {

	public AddBook(Start mainApp) {
		super(mainApp);
	}

	private boolean entriesAreValid(String isbn, String qty) {
		return true;
	}

	protected Pane getScreen() {

		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		grid.add(new Label("ISBN"), 0, 0);
		TextField txtISBN = new TextField();
		grid.add(txtISBN, 1, 0);

		grid.add(new Label("Book Title"), 0, 1);
		TextField txtTitle = new TextField();
		grid.add(txtTitle, 1, 1);

		grid.add(new Label("Max. Checkout Period (In Days)"), 0, 2);
		ComboBox<String> cmbMaxCheckoutLength = new ComboBox<String>();
		cmbMaxCheckoutLength.getItems().add("7");
		cmbMaxCheckoutLength.getItems().add("27");
		grid.add(cmbMaxCheckoutLength, 1, 2);

		grid.add(new Label("Number of Copies Available"), 0, 3);
		TextField txtNumOfCopies = new TextField();
		grid.add(txtNumOfCopies, 1, 3);

		grid.add(new Label("Author"), 0, 4);
		ComboBox<Author> cmbAuthor = new ComboBox<Author>();
		ControllerInterface sc = new SystemController();
		List<Author> listOfAuthors = sc.allAuthors();
		
		cmbAuthor.getItems().addAll(listOfAuthors);
		grid.add(cmbAuthor, 1, 4);


		Button btnSave = new Button("Add Book");

		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					String isbn = txtISBN.getText().trim();
					String title = txtTitle.getText().trim();
					String maxCheckoutLengthStr = cmbMaxCheckoutLength.getValue().trim();
					String numberOfCopiesStr = txtNumOfCopies.getText().trim();

					if (entriesAreValid(isbn, title)) {
						int maxCheckoutLength = Integer.parseInt(maxCheckoutLengthStr);
						int numOfCopies = Integer.parseInt(numberOfCopiesStr);
//
//						ControllerInterface sc = new SystemController();
//						List<Book> listOfBooks = sc.allBooks();
//						Book book = bookExistsWithISBN(isbn, listOfBooks);
//						if (book != null) {
//							book.addCopy(qty);
//							sc.updateBook(book);
//
//							displayMessage(Alert.AlertType.INFORMATION, "Copy Added", "The Addition was successful");
//
//							new AllBooksWindow(mainApp).setScreen();
//
//						} else {
//							throw new Exception("Book Not Found!");
//						}
					} else {
						throw new Exception("Your inputs have errors");
					}

				} catch (Exception ex) {
					displayMessage(Alert.AlertType.ERROR, "Error!!!", ex.getMessage());
				}

			}
		});

		grid.add(btnSave, 1, 5);

		return grid;

	}

}