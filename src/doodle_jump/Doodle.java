package DoodleJump;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Doodle {

	private double _yVel;
	private Rectangle _myDoodle;
	private NeuralNetwork _brain;
	private Game _game;
	
	public Doodle(BorderPane _dJPane, Game g) {
		_yVel = 0;
		_game = g;
		
		_myDoodle = new Rectangle(Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
		_myDoodle.setFill(Color.BLACK);
		_myDoodle.setX(190);
		_myDoodle.setY(420);

		_brain = new NeuralNetwork();
		_dJPane.setOnKeyPressed(new KeyHandler());
		_dJPane.setFocusTraversable(true);
	}
	public void setYVel (double yVel) {
		_yVel = yVel;
	}
	public double getYVel() {
		return _yVel;
	}
	public Rectangle getDoodle() {
		return _myDoodle;
	}
	public double[] see() {
		double input1 = _myDoodle.getX();
		//distance from pipe to bird
		double input2 = 500 - _myDoodle.getY();
		input2 = _myDoodle.getY();
		//height of bird from ground
		//double input3 = _myBird.getY()-(_game.getClosestPipe().getUpperY() + Constants.PIPE_HEIGHT);
		double input3 = _game.getClosestPlatform().getXLoc();
		//double input4 = (_game.getClosestPipe().getLowerY()) - _myBird.getY();
		double input4 = _game.getClosestPlatform().getXLoc() + Constants.PLATFORM_WIDTH;
//		double input4 = _game._pipeList.get(_game._pipeList.indexOf(_game.getClosestPipe())+1).getUpperY();
		//System.out.println("Input closestPipe4 is : "+ input4);
		double[] inputs = {input1/500, input2/600, input3/500, input4/500};
		return inputs;
	}
	
	
	public void setXLoc (double x) {
		if (x<0) {
			_myDoodle.setX(0);
		} else if (x > 380) {
			_myDoodle.setX(380);
		} else {
			_myDoodle.setX(x);
		}
	}
	public void setYLoc (double y) {
		_myDoodle.setY(y);
	}
	
	public double getXLoc() {
		return _myDoodle.getX();
	}
	public double getYLoc() {
		return _myDoodle.getY();
	}
	/**
	 * This class implements the EventHandler interface, where 
	 * pressing the right arrow on the keyboard will move our 
	 * _myDoodle to the right, and pressing the left arrow on the 
	 * keyboard will move _myDoodle to the left.
	 */
	public class KeyHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			KeyCode KeyPressed = event.getCode();
			
			if (KeyPressed == KeyCode.RIGHT) {
				_myDoodle.setX(_myDoodle.getX()+20);
			} else if (KeyPressed == KeyCode.LEFT) {
				_myDoodle.setX(_myDoodle.getX()-20);
			} else if (KeyPressed == KeyCode.SPACE) {
				_yVel = Constants.REBOUND_VELOCITY;
			}
			event.consume();
		}
	}
	/**
	 * This class ensures _myDoodle is constantly falling by 
	 * gravity.
	*/ 
	 
	/*public void bounce() {
		for(int i = 0; i < Constants.DURATION; i++) {
		_myDoodle.setY(_myDoodle.getY()-10);
		}
	}
	
	public double getYLoc() {
		return _myDoodle.getY();
	}
	public void setYLoc(double d) {
		 _myDoodle.setY(d);
	}*/
}
