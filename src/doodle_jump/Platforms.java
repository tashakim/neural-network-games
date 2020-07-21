package DoodleJump;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platforms {

	private Rectangle _platform;
	
	public Platforms(BorderPane p) {
		_platform = new Rectangle(Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
		_platform.setFill(Color.GREEN);
	}
	public double getXLoc() {
		return _platform.getX();
	}
	public double getYLoc() {
		return _platform.getY();
	}
	public void setXLoc (double x) {
		_platform.setX(x);
	}
	public void setYLoc (double y) {
		_platform.setY(y);
	}
	public Rectangle getRectangle() {
		return _platform;
	}
	
	public double random (double lower, double higher) {
		int x = (int) (Math.random()*(higher-lower+1)+lower);
		return x;
	}
	
}
