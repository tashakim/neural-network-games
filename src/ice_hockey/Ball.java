package IceHockey;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	private Circle _ball;

	public Ball(double xPosition, double yPosition) {
		_ball = new Circle();
		_ball.setRadius(Constants.BALL_RADIUS);
		_ball.setFill(Color.web("0x23395D"));
		_ball.setCenterX(xPosition);
		_ball.setCenterY(yPosition);
	}

	public void setX(double xPosition) {
		_ball.setCenterX(xPosition);
	}

	public void setY(double yPosition) {
		_ball.setCenterY(yPosition);
	}

	public double getX() {
		return _ball.getCenterX();
	}

	public double getY() {
		return _ball.getCenterY();
	}

	public Node getNode() {
		return _ball;
	}
}
