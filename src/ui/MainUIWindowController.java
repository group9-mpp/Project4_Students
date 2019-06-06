package ui;

import business.SystemController;
import dataaccess.SiteFeatures;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class MainUIWindowController {
	ObservableList<String> featuresAvailable = FXCollections.observableArrayList();
	@FXML
	private ComboBox<String> cmbFeaturesDropDown;// = new ComboBox<String>();

	@FXML
	private Button btnProceed = new Button();

	@FXML
	private void loadFeatures(ActionEvent event) { 
		System.out.print(cmbFeaturesDropDown);
		System.out.print(event);
		System.out.print("loadFeatures");
		// cmbFeaturesDropDown.setItems ( new ComboItem featuresAvailable) ;

		/*
		 * cmbFeaturesDropDown.getItems().add("Testing");
		 * 
		 * for(Object feature : cmbFeaturesDropDown.getItems()) {
		 * System.out.println(feature); }
		 */
	}

	public void showFeature() {
//		String requestedAction = cmbFeaturesDropDown.getValue();
		// call feature
		System.out.println("James!!!");
		cmbFeaturesDropDown.getItems().add("Testing");
	}

	public MainUIWindowController() {
		// loadFeatures();
		if(cmbFeaturesDropDown!=null) {
		cmbFeaturesDropDown.getItems().add("Testing");
		}
		/*
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
		default:
			// ERROR HERE
			break;
		}*/
	}

}
