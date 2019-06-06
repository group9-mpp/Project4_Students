package ui;

import business.CheckoutEntry;
import business.CheckoutException;
import business.CheckoutRecord;
import business.ControllerInterface;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CheckoutWindow {
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

		Text scenetitle = new Text("Checkout");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); // Tahoma
		grid.add(scenetitle, 0, 0, 2, 1);

		Label id = new Label("Library Member ID:");
		grid.add(id, 0, 1);

		TextField idTextField = new TextField();
		// userTextField.setPrefColumnCount(10);
		// userTextField.setPrefWidth(30);
		grid.add(idTextField, 1, 1);

		Label isbnLabel = new Label("ISBN:");
		grid.add(isbnLabel, 0, 2);
		grid.setGridLinesVisible(false);

		TextField isbnField = new TextField();
		grid.add(isbnField, 1, 2);

		Button checkout = new Button("Checkout");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(checkout);
		grid.add(hbBtn, 1, 4);

		checkout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					ControllerInterface c = new SystemController();
					CheckoutRecord checkoutRecord = c.checkout(idTextField.getText().trim(),
							isbnField.getText().trim());

					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Checkout");
					alert.setHeaderText("Checkout successful.");
					alert.setContentText(checkoutRecord.toString());
					alert.showAndWait();

					for (CheckoutEntry checkoutEntry : checkoutRecord.getCheckoutEntries()) {
						System.out.println(checkoutEntry);
					}
				} catch (CheckoutException ex) {

					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Checkout");
					alert.setHeaderText("Erros!!!");
					alert.setContentText(ex.getMessage());
					alert.showAndWait();

				}

			}
		});

		return grid;
	}

}
