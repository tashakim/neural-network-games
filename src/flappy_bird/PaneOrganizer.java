package flappy_bird;

import javafx.application.Platform;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

public class PaneOrganizer {
	private BorderPane _root;
	private Game _flappyBird;
	

	public PaneOrganizer() {
		_root = new BorderPane();
		_root.setStyle("-fx-background-color: white;");
		_flappyBird = new Game(_root);
		this.setUpButtons();
	}
	public PaneOrganizer(int i) {
		_root = new BorderPane();
		_root.setStyle("-fx-background-color: black)");
		_flappyBird = new Game(_root, 5);
	}
	
	public BorderPane getRoot() {
		return _root;
	}

	public void setUpButtons() {
		VBox _buttonPane = new VBox();
		_buttonPane.setSpacing(5);
		_root.setRight(_buttonPane);

		Button b1 = new Button("Restart");
		b1.setOnAction(new ButtonHandler1());	
		Button b2 = new Button("Quit");
		b2.setOnAction(new ButtonHandler2());
		Button b11 = new Button("x2.0");
		b11.setOnAction(new ButtonHandler11());
		Button b3 = new Button("x1.5");
		b3.setOnAction(new ButtonHandler3());
		Button b4 = new Button("x0.5");
		b4.setOnAction(new ButtonHandler4());
		Button b5 = new Button("Regular Game");
		b5.setOnAction(new ButtonHandler5());
		Button b6 = new Button("Generate Population");
		b6.setOnAction(new ButtonHandler6());

		_buttonPane.getChildren().addAll(b1,b2, b11, b3,  b4, b5, b6);
	}
	private class ButtonHandler1 implements EventHandler<ActionEvent> {
		@Override 
		public void handle(ActionEvent event) {
			_flappyBird.gameOver();
		}
	}
	private class ButtonHandler11 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			_flappyBird.speedUpGame();
		}
	}
	private class ButtonHandler2 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Platform.exit();
		}
	}
	private class ButtonHandler3 implements EventHandler<ActionEvent> {
		@Override 
		public void handle(ActionEvent event) {
			_flappyBird.speedUpGame();
		}
	}
	
	private class ButtonHandler4 implements EventHandler<ActionEvent> {
		@Override 
		public void handle(ActionEvent event) {
			_flappyBird.slowDownGame();
		}
	}
	private class ButtonHandler5 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			_flappyBird.setRegular(true);
		}
	}
	private class ButtonHandler6 implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			_flappyBird.setRegular(false);
		}
	}
}
