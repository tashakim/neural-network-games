package flappy_bird;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PaneOrganizer {
	private BorderPane _root;
	public Game _game;
	
	public PaneOrganizer() {
		_root = new BorderPane();
		_root.setStyle("-fx-background-color: white");
		_game = new Game(_root);
		this.setUpButtons();
	}
	public BorderPane getRoot() {
		return _root;
		
	}
	public void setUpButtons() {
		VBox _buttonPane = new VBox();
		_buttonPane.setSpacing(5);
		_root.setRight(_buttonPane);
		
		Button b1 = new Button("Restart");
		
		Button b2 = new Button("Quit");
		
		_buttonPane.getChildren().addAll(b1,b2);
	}
	
}


