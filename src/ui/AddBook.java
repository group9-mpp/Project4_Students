package ui;

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

public class AddBook extends BaseWindow {

	
	public AddBook(Start mainApp) {
		super(mainApp);
	}

	protected  Pane getScreen(Start mainApp) {

		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		grid.add(new Label("ISBN"), 0, 0);
		TextField txtISBN = new TextField();
		grid.add(txtISBN, 1, 0);

		grid.add(new Label("Title"), 0, 1);
		TextField txtTitle = new TextField();
		grid.add(txtTitle, 1, 1);

		grid.add(new Label("Quantity"), 0, 2);
		TextField txtQty = new TextField();
		grid.add(txtQty, 1, 2);

		Button btnSave = new Button("Save");

		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Save");
				alert.setHeaderText("Not implemented");
				alert.setContentText("Kindly implement ");
				alert.showAndWait();

			}
		});

		grid.add(btnSave, 1, 5);

		return grid;

	}

}