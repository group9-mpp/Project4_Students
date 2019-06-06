package ui;

import javafx.scene.layout.Pane;

public class LibWindow {

	public  void setScreen(Start mainApp) {
		mainApp.setScreen(getScreen(mainApp));
	}

	protected  Pane getScreen(Start mainApp) {
		return null;
	}

}
