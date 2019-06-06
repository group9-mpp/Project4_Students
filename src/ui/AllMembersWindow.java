package ui;

import java.util.ArrayList;
import java.util.List;

import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class AllMembersWindow {

	public static void setScreen(Start mainApp) {

		mainApp.setScreen(getScreen(mainApp));
	}

	private static Pane getScreen(Start mainApp) {

		TableView<LibraryMember> tableView = new TableView<>();

		List<Pair<String, String>> columns = new ArrayList<>();

		columns.add(new Pair<String, String>("memberId", "Member Id"));
		columns.add(new Pair<String, String>("firstName", "First Name"));
		columns.add(new Pair<String, String>("lastName", "Last Name"));
		columns.add(new Pair<String, String>("telephone", "Telephone"));
		//columns.add(new Pair<String, String>("checkoutRecord", "Checkout Record"));
		columns.add(new Pair<String, String>("address", "Address"));

		for (Pair<String, String> pair : columns) {

			TableColumn<LibraryMember, String> column = new TableColumn<>(pair.getValue());
			column.setCellValueFactory(new PropertyValueFactory<>(pair.getKey()));
			column.prefWidthProperty().bind(tableView.widthProperty().divide(columns.size()));
			
			tableView.getColumns().add(column);
		}

		ControllerInterface ci = new SystemController();
		tableView.setItems(FXCollections.observableArrayList(ci.allMembers()));

		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Edit Member ");
				alert.setHeaderText("Not implemented");
				alert.setContentText(newSelection.toString());
				alert.showAndWait();
			}
		});

		return new VBox(tableView);

	}

}
