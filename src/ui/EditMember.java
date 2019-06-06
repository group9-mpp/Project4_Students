package ui;

import business.AddMemberException;
import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
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
import javafx.scene.layout.Pane;

public class EditMember extends BaseWindow {

	private Start mainApp;
	private LibraryMember member;
	TextField txtMemberID = new TextField();
	TextField txtFirstName = new TextField();
	TextField txtLastName = new TextField();
	TextField txtStreet = new TextField();
	TextField txtCity = new TextField();
	TextField txtState = new TextField();
	TextField txtZip = new TextField();
	TextField txtPhone = new TextField();

	public EditMember(Start mainApp, String memberID) {
		super(mainApp);
		ControllerInterface sc = new SystemController();
		member = sc.getMember(memberID);

	}

	private void populateFields() {
		txtMemberID.setText(member.getMemberId());
		txtFirstName.setText(member.getFirstName());
		txtLastName.setText(member.getLastName());
		txtPhone.setText(member.getTelephone());

		Address memberAddress = member.getAddress();
		txtStreet.setText(memberAddress.getStreet());
		txtCity.setText(memberAddress.getCity());
		txtState.setText(memberAddress.getState());
		txtZip.setText(memberAddress.getZip());

	}

	protected Pane getScreen() {

		txtMemberID.setDisable(true);

		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		grid.add(new Label("Member Id"), 0, 0);
		grid.add(txtMemberID, 1, 0);

		grid.add(new Label("First Name"), 0, 1);
		grid.add(txtFirstName, 1, 1);

		grid.add(new Label("Last Name"), 0, 2);
		grid.add(txtLastName, 1, 2);

		grid.add(new Label("Street"), 0, 3);
		grid.add(txtStreet, 1, 3);

		grid.add(new Label("City"), 0, 4);
		grid.add(txtCity, 1, 4);

		grid.add(new Label("State"), 0, 5);
		grid.add(txtState, 1, 5);

		grid.add(new Label("Zip Code"), 0, 6);
		grid.add(txtZip, 1, 6);

		grid.add(new Label("Telephone"), 0, 7);
		grid.add(txtPhone, 1, 7);

		Button btnSave = new Button("Save");
		populateFields();

		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					String firstName = txtFirstName.getText().trim();
					String lastName = txtLastName.getText().trim();
					String street = txtStreet.getText().trim();
					String city = txtCity.getText().trim();
					String state = txtState.getText().trim();
					String zipcode = txtZip.getText().trim();
					String phonenumber = txtPhone.getText().trim();
								
					member.setFirstName(firstName);
					member.setLastName(lastName);
					member.setTelephone(phonenumber);
					member.setAddress(new Address(street, city, state, zipcode));

					ControllerInterface sc = new SystemController();
					sc.updateMember(member);

					displayMessage(Alert.AlertType.INFORMATION, "Added Member", "Member Was Updated Successfully");

					new AllMembersWindow(mainApp).setScreen();

				} 
				catch (Exception ex) {
					displayMessage(Alert.AlertType.ERROR, "Error!", ex.getMessage());
				}

			}
		});

		grid.add(btnSave, 1, 9);

		return grid;

	}

}