package IceHockey;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	@Override
	public void start(Stage stage) {
		// Create top-level object, set up the scene, and show the stage here.
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(), Constants.GUI_WIDTH, Constants.GUI_HEIGHT);
		stage.setScene(scene);
		stage.setTitle("Ice Hockey Classic");
		stage.show();
	}

	/*
	 * Here is the mainline! No need to change this.
	 */
	public static void main(String[] argv) {
		// launch is a method inherited from Application
		launch(argv);
	}
}
