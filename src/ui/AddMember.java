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

public class AddMember {

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

		grid.add(new Label("First Name"), 0, 0);
		TextField txtFn = new TextField();
		grid.add(txtFn, 1, 0);

		grid.add(new Label("Last Name"), 0, 1);
		TextField txtLn = new TextField();
		grid.add(txtLn, 1, 1);

		grid.add(new Label("Telephone"), 0, 2);
		TextField txtTel = new TextField();
		grid.add(txtTel, 1, 2);

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