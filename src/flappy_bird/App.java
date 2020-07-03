package flappy_bird;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(), 1000, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] argv) {
		launch(argv);
	}
}
