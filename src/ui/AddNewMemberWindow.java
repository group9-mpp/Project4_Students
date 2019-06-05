package ui;

import java.io.IOException;

import com.sun.xml.internal.ws.util.StringUtils;

import business.AddMemberException;
import business.Address;
import business.LibraryMember;
import business.SystemController;
import dataaccess.DataAccessFacade;
import dataaccess.SiteFeatures;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddNewMemberWindow extends Stage implements LibWindow {
	public static final AddNewMemberWindow INSTANCE = new AddNewMemberWindow();
	private boolean isInitialized = false;

	@FXML
	private Button btnAdd = new Button();
	@FXML
	private TextField txtMemberID = new TextField();
	@FXML
	private TextField txtFirstName = new TextField();
	@FXML
	private TextField txtLastName = new TextField();
	@FXML
	private TextField txtStreet = new TextField();
	@FXML
	private TextField txtCity = new TextField();
	@FXML
	private TextField txtState = new TextField();
	@FXML
	private TextField txtZip = new TextField();
	@FXML
	private TextField txtPhone = new TextField();

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	public AddNewMemberWindow() {
	}

	public void start() {
		try {
			// Load layout from fxml file.
			AnchorPane rootLayout = (AnchorPane) FXMLLoader
					.load(AddNewMemberWindow.class.getResource("AddNewMemberWindow.fxml"));
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			setTitle("Add A New Member");
			setScene(scene);
			show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean entriesAreValid(String memberID, String firstName, String lastName, String street, String city,
			String state, String zipcode, String phonenumber) {
		return true;
	}

	public void addMember(ActionEvent event) {
		String memberID = txtMemberID.getText().trim();
		String firstName = txtFirstName.getText().trim();
		String lastName = txtLastName.getText().trim();
		String street = txtStreet.getText().trim();
		String city = txtCity.getText().trim();
		String state = txtState.getText().trim();
		String zipcode = txtZip.getText().trim();
		String phonenumber = txtPhone.getText().trim();
		boolean entriesAreValid = entriesAreValid(memberID, firstName, lastName, street, city, state, zipcode,
				phonenumber);

		if (entriesAreValid) {
			Address memberAddress = new Address(street, city, state, zipcode);
			LibraryMember newMember = new LibraryMember(memberID, firstName,
					lastName, phonenumber, memberAddress);
			DataAccessFacade dataAccessObject = new DataAccessFacade();
			try {
				dataAccessObject.saveNewMember(newMember);
				System.out.println("Member Addess Successfully");
			} catch (AddMemberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		start();

	}

}
