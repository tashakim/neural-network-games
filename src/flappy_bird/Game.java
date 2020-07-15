package flappy_bird;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import java.util.ArrayList;

import com.sun.prism.paint.Color;

import javafx.event.*;
import javafx.animation.*;

public class Game {

	private BorderPane _gamePane;
	private Timeline _timeline;
	private Label _label1;
	private Label _label2;
	private Label _label3;
	private Label _label4;

	private boolean _regular;
	private int _currentGen;
	private double _avgFitness;
	private Bird _prevBestBird;
	private Bird _prevSecondBird;

	private ArrayList<Integer>_generations;
	private ArrayList<Double> _fitnessHistory;

	public ArrayList <Pipes> _pipeList;
	private Pipes _closestPipe;
	private Bird _bird;
	private Population _pop;

	public Game(BorderPane bp) {
		
		_gamePane = new BorderPane();
		Image im = new Image(this.getClass().getResourceAsStream("sprite2.png"));
		//_gamePane.setStyle("-fx-background-color: powderblue;");
		_gamePane.setBackground(new Background(new BackgroundImage(im, null, null, null, null)));
		bp.setCenter(_gamePane);

		//labels
		_label1 = new Label("Number");
		_label1.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
		_label2 = new Label("Current Generation: 1");
		_label2.setFont(Font.font("Cambria", 15));
		_label3 = new Label("Computing Average Fitness...");
		_label3.setFont(Font.font("Cambria", 11));
		_label4 = new Label("Computing Best Fitness...");
		_label4.setFont(Font.font("Cambria", 11));

		_currentGen = 1;
		_regular = true;

		//_gamePane.getChildren().addAll(_numberOfBirds);
		this.createLabelPane(bp);                                           
		this.startGame();
	}
	public Game(BorderPane bp, int i) {
		_gamePane = new BorderPane();
		bp.setCenter(_gamePane);

	}
	public void createLabelPane(BorderPane bp) {
		VBox _labelPane = new VBox();
		//_labelPane.setStyle("-fx-background-color: black;");
		_labelPane.getChildren().addAll(_label1, _label2, 
				_label3, _label4);
		bp.setBottom(_labelPane);
	}

	public void startGame() {
		//when game starts, clears the pane to a clean slate 
		_gamePane.getChildren().clear();
		this.setUpTimeline();

		Pipes _firstPipe = new Pipes(_gamePane);
		//adding _firstPipe graphically
		_firstPipe.setLowerY(_firstPipe.random(200,400));
		_firstPipe.setLowerX(500);
		_firstPipe.setUpperY(_firstPipe.getLowerY() - Constants.PIPE_HEIGHT -Constants.PIPE_GAP);
		_firstPipe.setUpperX(500);
		_firstPipe.setLowerLength(500-_firstPipe.getLowerY());
		_gamePane.getChildren().addAll(_firstPipe.getLowerPipe(), _firstPipe.getUpperPipe());

		//adding _firstPipe logically
		_pipeList = new ArrayList <Pipes>();
		_pipeList.add(_firstPipe);
		_closestPipe = _firstPipe;
		//System.out.println(_pop);

		//	System.out.println("_generations length: " + _generations.size());
		//	System.out.println("_fitnessHistory length: " + _fitnessHistory.size());
		//		_visualizer.plot(_generations, _fitnessHistory, "Average Fitness over Time");



		if (_currentGen == 1) {
			_pop = new Population(_gamePane, this);
		} else {
			_pop.makeNextGen();
			//_visualizer.show(_pop.getBestBird().getSyn0(), _pop.getBestBird().getSyn1());


		}
	}
	public void physics() {
		this.scroll();
		if(_pipeList.get(_pipeList.size()-1).getLowerX()<500) {
			this.addPipe();
		} if (_pipeList.get(0).getLowerX() <= -60) {
			_pipeList.remove(_pipeList.get(0));
		}

	}
	public void addPipe() {
		Pipes _lastPipe = _pipeList.get(_pipeList.size() - 1);

		Pipes _newPipe = new Pipes(_gamePane);
		_newPipe.setLowerX(_lastPipe.getLowerX() + Constants.DISTANCE_BETWEEN_PIPES);
		_newPipe.setLowerY(_lastPipe.random(200,400));
		_newPipe.setLowerLength(500-_newPipe.getLowerY());
		_newPipe.setUpperX(_newPipe.getLowerX());
		_newPipe.setUpperY(_newPipe.getLowerY() - Constants.PIPE_HEIGHT - Constants.PIPE_GAP);

		_pipeList.add(_newPipe);
		_gamePane.getChildren().addAll(_newPipe.getLowerPipe(), _newPipe.getUpperPipe());
	}
	public void scroll() {
		//System.out.println("Closest pipe has x value: " + _closestPipe.getLowerX() + Constants.PIPE_WIDTH);
		for(int i=0; i<_pipeList.size(); i++) {
			Pipes p = _pipeList.get(i);
			p.setLowerX(p.getLowerX() - 3);
			p.setUpperX(p.getUpperX() - 3); 
		} for (int i=0; i< _pipeList.size(); i++) {
			Pipes p = _pipeList.get(i);
			if(p.getLowerX()+Constants.PIPE_WIDTH<0) {
				_pipeList.remove(p);
				_gamePane.getChildren().removeAll(p.getLowerPipe(), p.getUpperPipe());
				_pop.increaseFitness(50); //fitness variation
			}
		}
		if(_closestPipe.getLowerX() + Constants.PIPE_WIDTH < 200) {
			_closestPipe = _pipeList.get(_pipeList.indexOf(_closestPipe) +1);
		}
	}
	public Pipes getClosestPipe() {
		//System.out.println(_closestPipe.getLowerY());
		return _closestPipe;
	}

	public void hellGame() {
		for(int i=0; i<10; i++) {
			_timeline.setRate(2);
		}
	}
	public void speedUpGame() {
		//_timeline.stop();
		//this.startGame();
		_timeline.setRate(1.5);
	}
	public void slowDownGame() {
		//_timeline.stop();
		//this.startGame();
		_timeline.setRate(0.5);
	}
	public boolean getRegular() {
		return _regular;
	}
	public void setRegular(boolean b) {
		_regular = b;
	}
	public int getCurrentGen() {
		return _currentGen;
	}
	public Bird getBestBird() {
		System.out.println("Best bird's fitness: " +_prevBestBird.getFitness());
		return _prevBestBird;
	}
	public Bird getSecondBird() {
		return _prevSecondBird;
	}
	public void gameOver() {
		_timeline.stop();
		this.startGame();
	}
	public void setUpTimeline() {
		KeyFrame k = new KeyFrame(Duration.millis(Constants.DURATION), 
				new TimeHandler());
		_timeline = new Timeline(k);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.setAutoReverse(false);
		_timeline.play();
		_timeline.setRate(1);
	} 
	private class TimeHandler implements EventHandler <ActionEvent> {
		@Override
		public void handle(ActionEvent event) {

			physics();

			//			if(getRegular() == true) {
			//				_bird.updateYVel();
			//			} else if (getRegular() == false) {
			_pop.update();

			_label1.setText("No. Alive: " 
					+String.valueOf((int)_pop.getAliveBirds().size()));
			//			System.out.println(_pop.getDeadBirds().size());
			if(_pop.getAliveBirds().size() ==0) {
				_currentGen +=1;
				_avgFitness = _pop.getAvgFitness();
				//				_prevBestBird = _pop.getBestBird();
				//				_prevSecondBird = _pop.getSecondBird();
				_label2.setText("Current Generation: " 
						+ String.valueOf((int)_currentGen));
				//	_generations.add(_currentGen);
				Game.this.gameOver();
				_label3.setText("Average Fitness of Generation "
						+ String.valueOf((int)_currentGen-1)+ " : " 
						+ String.valueOf((int)_avgFitness));
				//	_fitnessHistory.add(_avgFitness);
				_label4.setText("Best Fitness of Generation" 
						+String.valueOf((int)_currentGen-1)+ " : "
						+ String.valueOf((int)_pop.getBestBird().getFitness()));
				//			}
			}
		}
	}
}
