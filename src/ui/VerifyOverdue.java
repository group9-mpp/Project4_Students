package ui;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import business.Book;
import business.BookCopy;
import business.CheckoutEntry;
import business.ControllerInterface;
import business.OverdueView;
import business.SystemController;
import business.exceptions.InvalidFieldException;
import javafx.collections.FXCollections;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class VerifyOverdue extends BaseWindow {

	public VerifyOverdue(Start mainApp) {
		super(mainApp);
	}

	private Book bookExistsWithISBN(String isbn, List<Book> listOfBooks) {
		for (Book book : listOfBooks) {
			if (book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		return null;
	}

	protected Pane getScreen() {

		VBox vbox = new VBox();

		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		//Text scenetitle = new Text("Overdue Record");
		//scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); // Tahoma
		//grid.add(scenetitle, 0, 0, 2, 1);

		Label id = new Label("ISBN");
		grid.add(id, 1, 1);

		TextField txtISBN = new TextField();
		grid.add(txtISBN, 2, 1);

		grid.setGridLinesVisible(false);

		Button getRecord = new Button("Get Overdue Record");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(getRecord);
		grid.add(hbBtn, 2, 4);

		Label resultText = new Label("");
		grid.add(resultText, 2, 6);

		vbox.getChildren().add(grid);

		TableView<OverdueView> tableView = new TableView<>();

		List<Pair<String, String>> columns = new ArrayList<>();

		columns.add(new Pair<String, String>("isbn", "ISBN"));
		columns.add(new Pair<String, String>("title", "Book Title"));
		columns.add(new Pair<String, String>("copyNumber", "Copy Number"));
		columns.add(new Pair<String, String>("member", "Checked Out By"));
		columns.add(new Pair<String, String>("dueDate", "Due Date"));

		for (Pair<String, String> pair : columns) {
			TableColumn<OverdueView, String> column = new TableColumn<>(pair.getValue());
			column.setCellValueFactory(new PropertyValueFactory<>(pair.getKey()));

			column.prefWidthProperty().bind(tableView.widthProperty().divide(columns.size()));

			tableView.getColumns().add(column);
		}

		vbox.getChildren().add(tableView);

		getRecord.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					String isbn = txtISBN.getText().trim();
					if (!isbn.isEmpty()) {
						tableView.setItems(null);
						resultText.setText("");
						ControllerInterface sc = new SystemController();
						List<Book> listOfBooks = sc.allBooks();
						Book book = bookExistsWithISBN(isbn, listOfBooks);
						if (book != null) {
							BookCopy[] copies = book.getCopies();
							List<BookCopy> checkedOutCopies = new ArrayList<BookCopy>();

							for (BookCopy copy : copies) {
								if (!copy.isAvailable()) {
									checkedOutCopies.add(copy);
								}
							}
							if (checkedOutCopies.size() > 0) {
								List<OverdueView> overdueCheckOuts = new ArrayList<OverdueView>();

								for (BookCopy copy : checkedOutCopies) {
									// get checkout entry that has this copy and check due date
//									if due date is before today, add to overdueCheckOuts,
									CheckoutEntry checkoutEntry = copy.getCheckoutEntry();
									LocalDate dueDate = checkoutEntry.getDueDate();
									if(dueDate.isBefore(LocalDate.now())) {
										overdueCheckOuts.add(new OverdueView(isbn, book.getTitle(), copy.getCopyNum()
												, checkoutEntry.getCheckoutRecord().getOwner().getName(), checkoutEntry.getDueDate()));
									}									
								}
								if (overdueCheckOuts.size() > 0) {
									// display record
									
									tableView.setItems(FXCollections.observableArrayList(overdueCheckOuts));

								} else {
									displayMessage(Alert.AlertType.INFORMATION, "No Overdue Copies",
											"No checkout of this book is currently overdue.");
								}
							} else {
								displayMessage(Alert.AlertType.INFORMATION, "No Checkout Record",
										"No copy of this book is currently checked out.");

							}
						} else {
							throw new Exception("Book Not Found!");
						}
					} else {
						throw new InvalidFieldException("You have an error in your input");
					}

				} catch (InvalidFieldException emExc) {
					displayMessage(Alert.AlertType.ERROR, "Please fill all fields correctly!", emExc.getMessage());
				} catch (Exception ex) {
					displayMessage(Alert.AlertType.ERROR, "Error", ex.getMessage());
				}

			}
		});

		return vbox;
	}

}
