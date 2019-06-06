package ui;

import business.SystemController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Start extends Application {

	Start mainApp = this;

	private MenuBar mainMenu = new MenuBar();
	Menu optionsMenu = new Menu("Menu");

	private Pane root;// = new BorderPane();

	public static void main(String[] args) {
		launch(args);
	}

	private static Stage primStage = null;

	public static Stage primStage() {
		return primStage;
	}

	public static class Colors {
		static Color green = Color.web("#034220");
		static Color red = Color.FIREBRICK;
	}

	@Override
	public void start(Stage primaryStage) {

		primStage = primaryStage;
		primStage.setTitle("The Library System");

		primStage.getIcons().add(new Image("/ui/icon.png"));

		setMenus();

		setHomeScreen();

		primaryStage.setResizable(false);
		primaryStage.sizeToScene();

		mainMenu.getMenus().addAll(optionsMenu);

		primaryStage.show();

	}

	void setHomeScreen() {

		VBox topContainer = new VBox();
		topContainer.setId("top-container");

		VBox imageHolder = new VBox();
		Image image = new Image("ui/library.jpg", 400, 300, false, false);

		// simply displays in ImageView the image as is
		ImageView iv = new ImageView();
		iv.setImage(image);
		imageHolder.getChildren().add(iv);
		imageHolder.setAlignment(Pos.CENTER);
		// HBox splashBox = new HBox();
		// Label splashLabel = new Label("The Library System");
		// splashLabel.setFont(Font.font("Trajan Pro", FontWeight.BOLD, 30));
		// splashBox.getChildren().add(splashLabel);
		// splashBox.setAlignment(Pos.CENTER);

		// topContainer.getChildren().add(splashBox);
		topContainer.getChildren().add(imageHolder);
		topContainer.setAlignment(Pos.CENTER);

		setScreen(topContainer);
	}

	private void setMenus() {

		optionsMenu.getItems().clear();

		ObservableList<MenuItem> menus = optionsMenu.getItems();

		if (SystemController.currentAuth != null) {
			
			switch (SystemController.currentAuth) {
			case ADMIN:
				menus.addAll(getMenbersMenu(), getAddMemberMenu());
				break;
			case LIBRARIAN: 
				menus.addAll(getBooksMenu(), getAddBookMenu(), getAddBookCopyMenu(),getCheckoutMenu(),getPrintCheckoutMenu(),getVerifyCheckoutMenu());
				break;
			case BOTH:
				menus.addAll(getMenbersMenu(), getAddMemberMenu());
				menus.addAll(getBooksMenu(), getAddBookMenu(),getAddBookCopyMenu(),getCheckoutMenu(),getPrintCheckoutMenu(),getVerifyCheckoutMenu());
				break; 
			}

			menus.add(getLogoutMenu());

		} else {
			MenuItem login = getLoginMenu();
			menus.add(login);
		}

	}

	private MenuItem getMenbersMenu() {

		MenuItem menu = new MenuItem("All Members");

		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				AllMembersWindow.setScreen(mainApp);
			}
		});
		return menu;
	}

	private MenuItem getBooksMenu() {

		MenuItem menu = new MenuItem("All Books");

		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				AllBooksWindow.setScreen(mainApp);
			}
		});
		return menu;

	}

	private MenuItem getLoginMenu() {

		MenuItem login = new MenuItem("Login");

		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				LoginWindow.setScreen(mainApp);
			}
		});
		return login;
	}

	private MenuItem getLogoutMenu() {

		MenuItem menu = new MenuItem("Logout");

		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				SystemController.currentAuth = null;
				setHomeScreen();
			}
		});
		return menu;
	}

	public void setScreen(Pane pane) {

		root = new VBox();

		root.getChildren().add(mainMenu);

		root.getChildren().add(pane);

		Scene scene = new Scene(root, 800, 600);

		setMenus();

		// scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		primStage.setScene(scene);

	}

	private MenuItem getAddMemberMenu() {

		MenuItem menu = new MenuItem("Add Member");

		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				AddMember.setScreen(mainApp);
			}
		});
		return menu;
	}

	private MenuItem getAddBookMenu() {

		MenuItem menu = new MenuItem("Add Book");

		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				AddBook.setScreen(mainApp);
			}
		});
		return menu;
	}

	private MenuItem getAddBookCopyMenu() {

		MenuItem menu = new MenuItem("Add Book Copy");

		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				AddBookCopy.setScreen(mainApp);
			}
		});
		return menu;
	}
	/*
	 * public static String CHECKOUT_BOOK = "Checkout A Book"; public static String
	 * PRINT_CHECKOUT_RECORD = "Print Checkout Record"; public static String
	 * VERIFY_OVERDUE = "Verify Overdue";
	 */
	private MenuItem getCheckoutMenu() {

		MenuItem menu = new MenuItem("Checkout A Book");

		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				CheckoutWindow.setScreen(mainApp);
			}
		});
		return menu;
	}

	private MenuItem getPrintCheckoutMenu() {

		MenuItem menu = new MenuItem("Print Checkout Record");

		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				PrintCheckoutWindow.setScreen(mainApp);

			}
		});
		return menu;
	}

	private MenuItem getVerifyCheckoutMenu() {

		MenuItem menu = new MenuItem("Verify Overdue");

		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

			}
		});
		return menu;
	}

	
}
