package flappy_bird;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;


public class Pipes {
	private Rectangle _lowerPipe;
	private Rectangle _upperPipe;

	public Pipes(BorderPane bp) {
		_lowerPipe = new Rectangle(Constants.PIPE_WIDTH, Constants.PIPE_HEIGHT);
		Image im1 = new Image(this.getClass().getResourceAsStream("sprite5.png"));
		_lowerPipe.setFill(new ImagePattern(im1));

		Image im4 = new Image(this.getClass().getResourceAsStream("sprite4.png"));
		_upperPipe = new Rectangle(Constants.PIPE_WIDTH, Constants.PIPE_HEIGHT);
		_upperPipe.setFill(new ImagePattern(im4));
		_upperPipe.setX(this.getLowerX());
		_upperPipe.setY(this.getLowerY()-Constants.PIPE_HEIGHT-Constants.PIPE_GAP);
	}
	public Rectangle getLowerPipe() {
		return _lowerPipe;
	}
	public Rectangle getUpperPipe() {
		return _upperPipe;
	}
	public double getLowerX() {
		return _lowerPipe.getX();
	}
	public double getUpperX() {
		return _upperPipe.getX();
	}
	public void setLowerX(double x) {
		_lowerPipe.setX(x);
	}
	public void setUpperX(double x) {
		_upperPipe.setX(x);
	}
	public double getLowerY() {
		return _lowerPipe.getY();
	}
	public double getUpperY() {
		return _upperPipe.getY();
	}
	public void setLowerY(double y) {
		_lowerPipe.setY(y);
	}
	public void setUpperY(double y) {
		_upperPipe.setY(y);
	}
	public int random(double a, double b) {
		int x = (int) (Math.random()*(b- a ) + a);
		return x;
	}


	//setting lengths of pipe
	public void setLowerLength(double y) {
		_lowerPipe.setHeight(y);
	}
}
