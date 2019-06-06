package ui;

import java.io.IOException;

import business.SystemController;
import dataaccess.SiteFeatures;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
public class MainUIWindow extends Stage implements LibWindow {
	public static final MainUIWindow INSTANCE = new MainUIWindow();
	private boolean isInitialized = false;

	ObservableList<String> featuresAvailable = FXCollections.observableArrayList();
	@FXML
	private ComboBox<String> cmbFeaturesDropDown = new ComboBox<String>();

	@FXML
	private Button btnProceed = new Button();

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	public MainUIWindow() {
	}

	public void start() {
		try {

			// Load layout from fxml file.
			AnchorPane rootLayout = (AnchorPane) FXMLLoader.load(MainUIWindow.class.getResource("MainUIWindow.fxml"));
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			setTitle("Welcome! Please Select a Feature");
			setScene(scene);
			switch (SystemController.currentAuth) {
			case ADMIN:
				featuresAvailable = FXCollections.observableArrayList(SiteFeatures.ADD_MEMBER,
						SiteFeatures.EDIT_MEMBER_INFO, SiteFeatures.ADD_BOOK, SiteFeatures.ADD_BOOK_COPY);

				break;
			case LIBRARIAN:
				featuresAvailable = FXCollections.observableArrayList(SiteFeatures.CHECKOUT_BOOK,
						SiteFeatures.PRINT_CHECKOUT_RECORD);
				break;
			case BOTH:
				featuresAvailable = FXCollections.observableArrayList(SiteFeatures.ADD_MEMBER,
						SiteFeatures.EDIT_MEMBER_INFO, SiteFeatures.ADD_BOOK, SiteFeatures.ADD_BOOK_COPY,
						SiteFeatures.CHECKOUT_BOOK, SiteFeatures.PRINT_CHECKOUT_RECORD);
				break;

			}
			cmbFeaturesDropDown.setItems(featuresAvailable);

			show();

			// printing to test, So now we assume we have the features displayed
			System.out.println("Features Are:");

			for (String feature : cmbFeaturesDropDown.getItems()) {
				System.out.println(" - " + feature);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showFeature(ActionEvent event) {
		// getSelected feature
		String selectedFeature = SiteFeatures.ADD_MEMBER;// should actually get the feature from the combobox
		System.out.println("Selected Feature is: " + selectedFeature);
		switch (selectedFeature) {
		case (SiteFeatures.ADD_BOOK):
			// display the window to add a new book
			break;
		case (SiteFeatures.ADD_BOOK_COPY):
			// display the window to add a new book copy
			break;
		case (SiteFeatures.ADD_MEMBER):
			// display the window to add a new member
			AddNewMemberWindow.INSTANCE.init();
			INSTANCE.hide();
			break;
		case (SiteFeatures.CHECKOUT_BOOK):
			// display the window to checkout a book
			break;
		case (SiteFeatures.EDIT_MEMBER_INFO):
			// display the window to edit a member
			break;
		case (SiteFeatures.PRINT_CHECKOUT_RECORD):
			// display the window to print a checkout record
			break;
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		start();

	}

}
*/