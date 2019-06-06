package ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
public class MainUIWindow extends Stage implements LibWindow {
	public static final MainUIWindow INSTANCE = new MainUIWindow();
	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	
	public void start() {
		try {
//			System.out.println(cmbFeaturesDropDown.getItems().size());

			// Load layout from fxml file.
			AnchorPane rootLayout = (AnchorPane) FXMLLoader.load(MainUIWindow.class.getResource("MainUIWindow.fxml"));
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			setTitle("Welcome! Please Select a Feature");
			setScene(scene);
			show();

		} catch (IOException e) {
			e.printStackTrace();
		}

//
		

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		start();
		
	}

}
*/