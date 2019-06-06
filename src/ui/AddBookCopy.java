package ui;

import java.util.List;

import business.Book;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class AddBookCopy {

	public static void setScreen(Start mainApp) {

		mainApp.setScreen(getScreen(mainApp));
	}

	private static boolean entriesAreValid(String isbn, String qty) {
		return true;
	}

	private static void displayMessage(AlertType messageType, String title, String content) {
		Alert alert = new Alert(messageType);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();

	}

	private static Book bookExistsWithISBN(String isbn, List<Book> listOfBooks) {
		for (Book book : listOfBooks) {
			if (book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		return null;
	}

	private static Pane getScreen(Start mainApp) {

		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		grid.add(new Label("ISBN"), 0, 0);
		TextField txtISBN = new TextField();
		grid.add(txtISBN, 1, 0);

		grid.add(new Label("Quantity"), 0, 1);
		TextField txtQty = new TextField();
		grid.add(txtQty, 1, 1);

		Button btnSave = new Button("Save");

		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					String isbn = txtISBN.getText().trim();
					String qtyString = txtQty.getText().trim();
					if (entriesAreValid(isbn, qtyString)) {
						int qty = Integer.parseInt(txtQty.getText().trim());
						SystemController sc = new SystemController();
						List<Book> listOfBooks = sc.allBooks();
						Book book = bookExistsWithISBN(isbn, listOfBooks);
						if (book != null) {
							book.addCopy(qty);
							sc.updateBook(book);

							displayMessage(Alert.AlertType.INFORMATION, "Copy Added", "The Addition was successful");

							AllBooksWindow.setScreen(mainApp);

						} else {
							throw new Exception("Book Not Found!");
						}
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