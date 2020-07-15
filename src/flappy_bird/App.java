package flappy_bird;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		StackPane _root = new StackPane();
		BorderPane firstpane = new BorderPane();

		Image iv1 = new Image(this.getClass().getResourceAsStream("sprite2.png"));
		//_gamePane.setStyle("-fx-background-color: powderblue;");
		firstpane.setBackground(new Background(new BackgroundImage(iv1, null, null, null, null)));
		_root.getChildren().add(firstpane);

		Button button = new Button("Machine Learning");
		Button button2 = new Button("Regular Flappy Bird");
		Button button3 = new Button("go to second form");
		_root.getChildren().addAll(button, button2, button3);
		_root.setAlignment(button, Pos.TOP_CENTER);
		_root.setAlignment(button2, Pos.CENTER);
		_root.setAlignment(button3, Pos.BOTTOM_CENTER);
		_root.setMargin(button, new Insets(200));
		_root.setMargin(button3, new Insets(200));

		Scene scene = new Scene(_root,1000,500);
		primaryStage.setScene(scene);
		primaryStage.show();

		button.setOnAction(e->{

			StackPane _root2 = new StackPane();
			//			Label label = new Label("you are now in second form");
			//			_root2.getChildren().add(label);
			PaneOrganizer organizer = new PaneOrganizer();
			Scene secondScene = new Scene(organizer.getRoot(),1000,500);

			Stage secondStage = new Stage();
			secondStage.setScene(secondScene);
			secondStage.setTitle("Second Form");
			secondStage.show();
			primaryStage.close();
		});
		button2.setOnAction(e->{

			StackPane _root3 = new StackPane();
			PaneOrganizer organizer = new PaneOrganizer(5);
			Scene thirdScene = new Scene(organizer.getRoot(), 500,500);
			Stage thirdStage = new Stage();
			thirdStage.setScene(thirdScene);
			thirdStage.show();
			primaryStage.close();

		});

	}



	public static void main(String[] argv) {
		launch(argv);
	}
}
