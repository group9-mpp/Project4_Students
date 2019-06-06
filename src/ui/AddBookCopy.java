package ui;

import java.util.HashMap;

import business.Book;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class AddBookCopy {

	public static void setScreen(Start mainApp) {

		mainApp.setScreen(getScreen(mainApp));
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
					String qty = txtQty.getText().trim();

					DataAccessFacade dataAccessObject = new DataAccessFacade();
					HashMap<String, Book> booksMap = dataAccessObject.readBooksMap();

					if (booksMap.containsKey(isbn)) {
						
						Book book = booksMap.get(isbn);
						book.addCopy();
						dataAccessObject.updateBook(book);
						
						System.out.println("Book Copy Added Successfully");
						
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Save");
						alert.setContentText("Book Copy Added Successfully");
						alert.showAndWait();

						AllBooksWindow.setScreen(mainApp);
						
					} else {
						throw new Exception("Book Not Found!");
					}

				} catch (Exception ex) {

					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error!!!");
					alert.setContentText(ex.getMessage());
					alert.showAndWait();

				}

			}
		});

		grid.add(btnSave, 1, 5);

		return grid;

	}

}