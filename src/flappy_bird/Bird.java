package flappy_bird;


import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.event.EventHandler;


public class Bird {
	private Game _game;
	private Rectangle _myBird;
	private NeuralNetwork _brain;
	private double _yVelocity;
	private BorderPane _bp;

	private double _fitness;
	private boolean _dead;

	public Bird(BorderPane bp, Game g) {
		_game = g;
		_bp = bp;
		Image im = new Image(this.getClass().getResourceAsStream("sprite1.png"));
		_myBird = new Rectangle(Constants.BIRD_WIDTH, Constants.BIRD_HEIGHT);
		_myBird.setFill(new ImagePattern(im));
		
		_myBird.setOpacity(0.5);
		_myBird.setX(150);
		_myBird.setY(200);
		_yVelocity = 0;
		bp.getChildren().add(_myBird);

		_dead = false;
		_fitness = 0;
		_brain = new NeuralNetwork();

		bp.setOnKeyPressed(new KeyHandler());
		bp.setFocusTraversable(true);
	}
	
	public Bird(BorderPane bp, Game g, double[][] syn0, double[][] syn1) {
		_game = g;
		_bp = bp;
		Image im = new Image(this.getClass().getResourceAsStream("sprite1.png"));
		_myBird = new Rectangle(Constants.BIRD_WIDTH, Constants.BIRD_HEIGHT);
		_myBird.setFill(new ImagePattern(im));
		_myBird.setOpacity(0.5);
		_myBird.setX(150);
		_myBird.setY(200);
		_yVelocity = 0;
		bp.getChildren().add(_myBird);

		_dead = false;
		_fitness = 0;
		_brain = new NeuralNetwork(syn0, syn1);
	}
	public void tick(boolean _regular) {
		
		updateYVel();  
	}
	public double[] see() {
		double input1 = _game.getClosestPipe().getLowerX()-(_myBird.getX()+Constants.BIRD_WIDTH);
		//distance from pipe to bird
		double input2 = 500 - _myBird.getY();
		input2 = _myBird.getY();
		//height of bird from ground
		//double input3 = _myBird.getY()-(_game.getClosestPipe().getUpperY() + Constants.PIPE_HEIGHT);
		double input3 = _game.getClosestPipe().getUpperY() + Constants.PIPE_HEIGHT;
		//double input4 = (_game.getClosestPipe().getLowerY()) - _myBird.getY();
		double input4 = _game.getClosestPipe().getLowerY();
//		double input4 = _game._pipeList.get(_game._pipeList.indexOf(_game.getClosestPipe())+1).getUpperY();
		//System.out.println("Input closestPipe4 is : "+ input4);
		double[] inputs = {input1/500, input2/600, input3/500, input4/500};
		return inputs;
	}
	
	public void jump() {
		_yVelocity = Constants.REBOUND_VELOCITY;
	}

	public void setYVel(double y) {
		_yVelocity = y;
	}
	public double getYVel() {
		return _yVelocity;
	}
	public void updateYVel() {
		//System.out.println(this.getYVel());
		setYVel(getYVel() + Constants.GRAVITY*Constants.DURATION/1000);
		setYLoc(getYLoc() + getYVel()*Constants.DURATION/1000);
		//System.out.println("Activated Output is : " + _brain.getOutput(see()));
		
		//_brain.printMatrix(_brain.getSyn1());
		if(_brain.getOutput(see())>0.5) {
			jump();
		}
	}
	
//	public double testOutput() {
//		return _brain.getOutput(see());
//	}

	public void setXLoc(double x) {
		_myBird.setX(x);
	}
	public double getXLoc() {
		return _myBird.getX();
	}
	public void setYLoc(double y) {
		_myBird.setY(y);
	}
	public double getYLoc() {
		return _myBird.getY();
	}
	public Rectangle getBird() {
		return _myBird;
	}
	

	public double getFitness() {
		return _fitness;
	}
	public void setFitness(double x) {
		_fitness = x;
	}
	public double[][] getSyn0() {
		return _brain.getSyn0();
	}
	public double[][] getSyn1() {
		return _brain.getSyn1();
	}
	public boolean getBoolean() {
		return _dead;
	}
	public boolean isBirdDead() {
		if(getYLoc() >= 0 && getYLoc() <= 500) {
			for(int i = 0; i< _game._pipeList.size(); i++) {
				if(getBird().intersects(_game._pipeList.get(i).getLowerX(),
						_game._pipeList.get(i).getLowerY(), Constants.PIPE_WIDTH,
						Constants.PIPE_HEIGHT)) {
					_dead = true;
				}
			} for(int i = 0; i< _game._pipeList.size(); i++) {
				if(getBird().intersects(_game._pipeList.get(i).getUpperX(),
						_game._pipeList.get(i).getUpperY(),Constants.PIPE_WIDTH,
						Constants.PIPE_HEIGHT)) {
					_dead = true;
				}
			}
		} else {
			_dead = true;
		}
		return _dead;
	}
	public void killBird() {
		_dead = true;
	}
	private class KeyHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			KeyCode KeyPressed = event.getCode();
			if(KeyPressed == KeyCode.SPACE) {
				_yVelocity = Constants.REBOUND_VELOCITY;
				System.out.println("Bird sees.");
				System.out.println(_game.getClosestPipe().getLowerX()-_myBird.getX());
				System.out.println(500 - _myBird.getY());
				System.out.println(_game.getClosestPipe().getLowerY());
			}
			event.consume();
		}
	}
}
