package DoodleJump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PaneOrganizer {

	public BorderPane _root;
	public Game _doodleJump;
	private Timeline _tl;
	
	private Label _label3;
	
	public PaneOrganizer() {
		_root = new BorderPane();
		_root.setStyle("-fx-background-color: white;");
		_doodleJump = new Game(_root);
		
		this.setUpButtons();
		this.setupTimeline();
	}
	public BorderPane getRoot() {
		return _root;
	}
	public void myGame() {
		BorderPane myPane = new BorderPane();
		myPane.setPrefSize(500, 500);
		myPane.setStyle("-fx-background-color:grey;");
		_root.setCenter(myPane);
		_doodleJump = new Game(myPane);
	}
	public void otherPane() {
		VBox bottom = new VBox();
		bottom.setAlignment(Pos.CENTER);
		bottom.setPrefSize(400, 200);
		bottom.setStyle("-fx-background-color:white;");
		
		_root.setBottom(bottom);
		_label3 = new Label("Game Over! Try Again");
		_label3.setTextFill(Color.WHITE);
		bottom.getChildren().add(_label3);
	}
	
	public void setUpButtons() {
		VBox buttonPane = new VBox();
		_root.setRight(buttonPane);
		Button b1 = new Button("Button 1");
		Button b2 = new Button("Button 2");
		Button b3 = new Button("Button 3");
		
		buttonPane.getChildren().addAll(b1,b2, b3);
		b1.setOnAction(new ButtonHandler());
		b2.setOnAction(new ButtonHandler());
		b3.setOnAction(new ButtonHandler());
	}
	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Platform.exit();
		}
	}
	private class TimeHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			_doodleJump.Game();
			if (_doodleJump.getDoodle().getYLoc() >= 500) {
				this.gameOver();
			}
		}
		public void gameOver() {
			_tl.stop();
			_label3.setTextFill(Color.RED);
		}
	}
	
	public void setupTimeline() {
		KeyFrame kf = new KeyFrame(Duration.millis(Constants.DURATION), new TimeHandler());
		_tl = new Timeline(kf);
		_tl.setCycleCount(Animation.INDEFINITE);
		_tl.play();
	}

}
