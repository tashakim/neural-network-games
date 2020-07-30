package IceHockey;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class IceHockey {
	private Pane _root;
	private Paddle _player, _computer;
	private Ball _ball;
	private Timeline _timeline;
	private double[] _kickOffParam;

	public IceHockey(Pane root) {
		_root = root;
		_root.setOnKeyPressed(new KeyHandler());
		_root.setFocusTraversable(true);
		_root.requestFocus();
		this.createPaddle();
		this.createBall();
		_root.getChildren().addAll(_player.getNode(), _computer.getNode(), _ball.getNode());
		this.calcKickOffParameter();
		this.setupTimeLine();
	}

	private void createPaddle() {
		_player = new Paddle(Constants.PADDLE_INIT_X, Constants.PADDLE_PLAYER_INIT_Y);
		_computer = new Paddle(Constants.PADDLE_INIT_X, Constants.PADDLE_COMPUTER_INIT_Y);
	}

	private void createBall() {
		_ball = new Ball(Constants.BALL_CENTER_X, Constants.BALL_CENTER_Y);
	}

	public void setupTimeLine() {
		KeyFrame kf = new KeyFrame(Duration.millis(Constants.DURATION), new TimeHandler());
		_timeline = new Timeline(kf);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
	}

	private class TimeHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			IceHockey.this.restrainXLoc();
			IceHockey.this.kickOff();
		}
	}

	private void restrainXLoc() {
		if (_player.getX() >= Constants.CHANGE_CUTOFF_RIGHT) {
			_player.setX(Constants.CHANGE_CUTOFF_RIGHT);
		}
		if (_player.getX() <= Constants.CHANGE_CUTOFF_LEFT) {
			_player.setX(Constants.CHANGE_CUTOFF_LEFT);
		}
	}

	private double[] calcKickOffParameter() {
		double maxX = 0.3;
		double minX = -0.3;
		double maxY = -0.5;
		double minY = -0.7;
		double randX = (Math.random() * (maxX - minX) + minX);
		double randY = (Math.random() * (maxY - minY) + minY);
		_kickOffParam = new double[2];
		_kickOffParam[0] = randX;
		_kickOffParam[1] = randY;
		return _kickOffParam;
	}

	private void kickOff() {
		_ball.setX(_ball.getX() + _kickOffParam[0]);
		_ball.setY(_ball.getY() + _kickOffParam[1]);
	}

	private class KeyHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent e) {
			KeyCode keyPressed = e.getCode();
			// Switch statement to identify user's input
			switch (keyPressed) {
			case LEFT:
				_player.setX(_player.getX() - Constants.KEYSTROKE_X_COORDINATE);
				break;
			case RIGHT:
				_player.setX(_player.getX() + Constants.KEYSTROKE_X_COORDINATE);
				break;
			default:
				break;
			}
			e.consume();
		}
	}
}
