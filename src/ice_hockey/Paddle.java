package IceHockey;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle {
	private Rectangle _paddle;

	public Paddle(double xPosition, double yPosition) {
		_paddle = new Rectangle();
		_paddle.setWidth(Constants.PADDLE_WIDTH);
		_paddle.setHeight(Constants.PADDLE_HEIGHT);
		_paddle.setFill(Color.BLACK);
		_paddle.setX(xPosition);
		_paddle.setY(yPosition);
	}

	public void setX(double xPosition) {
		_paddle.setX(xPosition);
	}

	public void setY(double yPosition) {
		_paddle.setY(yPosition);
	}

	public double getX() {
		return _paddle.getX();
	}

	public double getY() {
		return _paddle.getY();
	}

	public Node getNode() {
		return _paddle;
	}
}
