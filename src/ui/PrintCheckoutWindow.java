package ui;

import java.util.ArrayList;

import java.util.List;


import business.CheckoutEntry;
import business.CheckoutException;
import business.CheckoutRecord;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
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

public class PrintCheckoutWindow extends BaseWindow {

	public PrintCheckoutWindow(Start mainApp) {
		super(mainApp);
		// TODO Auto-generated constructor stub
	}

	protected  Pane getScreen() {

		VBox  vbox = new VBox();
		
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Checkout Record");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); // Tahoma
		grid.add(scenetitle, 0, 0, 2, 1);

		Label id = new Label("Library Member ID:");
		grid.add(id, 1, 1);

		TextField idTextField = new TextField();
		// userTextField.setPrefColumnCount(10);
		// userTextField.setPrefWidth(30);
		grid.add(idTextField, 2, 1);

		grid.setGridLinesVisible(false);

		Button getRecord = new Button("Print Checkout Record");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(getRecord);
		grid.add(hbBtn, 2, 4);
		
		Label libraryMemberText = new Label("");
		grid.add(libraryMemberText, 2, 6);

		vbox.getChildren().add(grid);
		
		TableView<CheckoutEntry> tableView = new TableView<>();
		
		List<Pair<String, String>> columns = new ArrayList<>();

		columns.add(new Pair<String, String>("checkoutDate", "Checkout Date"));
		columns.add(new Pair<String, String>("dueDate", "Due Date"));
		columns.add(new Pair<String, String>("bookCopy", "Book Copy"));

		
		for (Pair<String, String> pair : columns) {

			TableColumn<CheckoutEntry, String> column = new TableColumn<>(pair.getValue());
			column.setCellValueFactory(new PropertyValueFactory<>(pair.getKey()));

			column.prefWidthProperty().bind(tableView.widthProperty().divide(columns.size()));
			
			tableView.getColumns().add(column);
		}
		
		vbox.getChildren().add(tableView);
		
		
		getRecord.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					tableView.setItems(null);
					libraryMemberText.setText("");
					ControllerInterface c = new SystemController();
					LibraryMember libraryMember =  c.getCheckoutRecord(idTextField.getText().trim());
					libraryMemberText.setText(libraryMember.getFirstName() + " " +libraryMember.getLastName());
					CheckoutRecord checkoutRecord = libraryMember.getCheckoutRecord();
					tableView.setItems(FXCollections.observableArrayList(checkoutRecord.getCheckoutEntries()));

					for (CheckoutEntry checkoutEntry : checkoutRecord.getCheckoutEntries()) {
						System.out.println(checkoutEntry);
					}
				} catch (CheckoutException ex) {

					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Checkout Record");
					alert.setHeaderText("Erros!!!");
					alert.setContentText(ex.getMessage());
					alert.showAndWait();

				}

			}
		});

		return vbox;
	}

}
