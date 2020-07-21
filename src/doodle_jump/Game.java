package DoodleJump;

import java.util.ArrayList;

import javafx.scene.layout.BorderPane;

public class Game {
	
	private BorderPane _dJPane;
	
	//private Label _intro;
	//private Label _time;
	//private Date _now;
	//private VBox _labelPane;
	private Doodle _doodle;
	private ArrayList <Platforms> _platformList;
	private Boolean _boolean;
	private NeuralNetwork _brain;
	private Platforms _closestPlatform;
	
	public Game(BorderPane b) {
		_dJPane = new BorderPane();
		_dJPane.setStyle("-fx-background-color: powderblue;");
		b.setCenter(_dJPane);
		
	//	_time = new Label("time");
	//	_intro = new Label("The aim of this game is to \nguide your character as he jumps \nup a series of platforms.");
	//	this.createLabel(_time, _intro);
		
		_doodle = new Doodle(_dJPane, this);
		_doodle.setYVel(Constants.REBOUND_VELOCITY);
		Platforms platform1 = new Platforms(_dJPane);
		platform1.setXLoc(180);
		platform1.setYLoc(460);
		
		_dJPane.getChildren().addAll(_doodle.getDoodle(), platform1.getRectangle());
		
	//	b.setLeft(_labelPane);
		
		_platformList = new ArrayList <Platforms> ();
		_platformList.add(platform1);
		_closestPlatform = platform1;
		this.addPlatform();
		
	//	_brain = new NeuralNetwork(syn0, syn1);
		
		//adds an ArrayList, a collection of our platforms to be generated.
	//	Platforms p1 = Platforms(_dJPane);

	}
	
	
	public void Game() {
		_doodle.setYVel(_doodle.getYVel()+ Constants.GRAVITY*Constants.DURATION/1000);
		_doodle.setYLoc(_doodle.getYLoc() + _doodle.getYVel()*Constants.DURATION/1000);
		
		if(_brain.getOutput(_doodle.see())>0.5) {
			_doodle.setYVel(Constants.REBOUND_VELOCITY);
		}
		
		if(_doodle.getYVel()>0) {
			for(int i=0; i<_platformList.size(); i++ ) {
				_boolean = _doodle.getDoodle().intersects(_platformList.get(i).getXLoc(),_platformList.get(i).getYLoc(), Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
				if (_boolean == true) {
					this.bounce();
				}
			}
		} else if (_doodle.getYVel() < 0) {
			this.scroll();
			
		} if (_platformList.get(_platformList.size()-1).getYLoc() > -20) {
			this.addPlatform();
			
		} if (_platformList.get(0).getYLoc() <=0) {
			_platformList.remove(_platformList.get(0));
		}
	}
	public Doodle getDoodle() {
		return _doodle;
	}
	public Platforms getClosestPlatform() {
		return _closestPlatform;
	}
	
	public void bounce() {
		_doodle.setYVel(Constants.REBOUND_VELOCITY);
	}
//	public void createLabel(Label l1, Label l2) {
//		_labelPane = new VBox();
//		_labelPane.getChildren().addAll(l1, l2);
//	}
	
//	public void setUpTimeline() {
//		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), new TimeHandler());
//		Timeline timeline = new Timeline(kf);
//		timeline.setCycleCount(Animation.INDEFINITE);
//		timeline.play();
//	}
	/**
	 * This class implements the EventHandler, and is responsible
	 * for setting the date, and making sure that _myDoodle is
	 * constantly falling according to gravity.
	 */
//	private class TimeHandler implements EventHandler<ActionEvent> {
//		private int _fall;
//		@Override
//		public void handle(ActionEvent event) {
//			_fall = 10;
//			_now = new Date();
//			_time.setText(_now.toString());
			
			/*if(_doodle.getYLoc() < 600 - Constants.DOODLE_HEIGHT) {
				_doodle.setYLoc(_doodle.getYLoc() + _fall );
			} else if(_doodle.getYLoc() >= 600 - Constants.DOODLE_HEIGHT) {
				_doodle.setYLoc(600 - Constants.DOODLE_HEIGHT);
				_doodle.bounce();
			}*/
//		}
//	}
	public void addPlatform() {
		for(int i=0; i<10; i++) {
			Platforms lastP = _platformList.get(_platformList.size()-1);
			double xloc = lastP.random(lastP.getXLoc() - 80, lastP.getXLoc() +80);
			if (xloc > 0 && xloc < 360) {
				Platforms newP = new Platforms (_dJPane);
				newP.setXLoc(xloc);
				newP.setYLoc(lastP.random(lastP.getYLoc()-40, lastP.getYLoc()-150));
				_dJPane.getChildren().add(newP.getRectangle());
				_platformList.add(newP);
			} else {
				this.addPlatform();
			}
		}
	}
	public void scroll() {
		if(_doodle.getYLoc()<250) {
			for(int i=0; i<_platformList.size(); i++) {
				_doodle.setYLoc(250);
				_platformList.get(i).setYLoc(_platformList.get(i).getYLoc() 
						- _doodle.getYVel()*Constants.DURATION/1000);
			
			if(_closestPlatform.getXLoc() < 250) {
				_closestPlatform = _platformList.get(_platformList.indexOf(_closestPlatform)+1);
			}
			}
		}
	}
}
