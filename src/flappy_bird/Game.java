package flappy_bird;

import javafx.scene.layout.BorderPane;

public class Game {
	private BorderPane _gamePane;
	
	public Game(BorderPane bp) {
		_gamePane = new BorderPane();
		_gamePane.setStyle("-fx-background-color: powderblue;");
		bp.setCenter(_gamePane);
		
	
	}
}
