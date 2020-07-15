package flappy_bird;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import javafx.scene.layout.BorderPane;

public class Population {

	private int totalPopulation;
	private ArrayList<Bird> _aliveBirds;
	private ArrayList<Bird> _tempStack;
	private Stack<Bird> _deadBirds;
	private Bird _bestBird;
	private Bird _secondBird;
	private double sum;
	private BorderPane _gamePane;
	private Game _game;

	public Population(BorderPane b, Game g) {
		_game = g;
		_gamePane = b;
		//Bird[] aliveBirds = new Bird[50];
		_aliveBirds = new ArrayList <Bird>(); 
		_deadBirds = new Stack<Bird>();
		//_tempStack = new ArrayList<Bird>();

		totalPopulation = 50;
		sum = 0;
		
		
//		if (g.getCurrentGen() ==1 ) {
		this.newPopulation();
//		} else {
//			System.out.println("Selection");
//			selection();
//		}
		//_deadBirds.clear();
	}

	
	public void increaseFitness(int inc) {
		for (int i=0; i< _aliveBirds.size(); i++ ) {
			if(_aliveBirds.get(i).isBirdDead() == false) {
				//double distanceFromGap = 1/(Math.pow((_game.getClosestPipe().getLowerY()-_aliveBirds.get(i).getYLoc())/300,2) 
				//		+Math.pow((_game.getClosestPipe().getLowerX()-_aliveBirds.get(i).getXLoc())/300,2));
				//System.out.println("bird" + i + "distance from gap :" +distanceFromGap);
				_aliveBirds.get(i).setFitness(_aliveBirds.get(i).getFitness()+inc);
			}
		}
	}
	public void update() {
		
		
		for (int i=0; i< _aliveBirds.size(); i++ ) {
			if(_aliveBirds.get(i).isBirdDead() == false) {
				if (i == 0) {
//					System.out.println(_aliveBirds.get(i).testOutput());
//					double[] inputs = _aliveBirds.get(i).see();
//					for (double input: inputs) {
//						System.out.println(input);
//					}
//					System.out.println();
				}
				_aliveBirds.get(i).updateYVel();
				//double distanceFromGap = 1/(Math.pow((_game.getClosestPipe().getLowerY()-_aliveBirds.get(i).getYLoc())/300,2) 
				//		+Math.pow((_game.getClosestPipe().getLowerX()-_aliveBirds.get(i).getXLoc())/300,2));
				//System.out.println("bird" + i + "distance from gap :" +distanceFromGap);
				_aliveBirds.get(i).setFitness(_aliveBirds.get(i).getFitness()+1);

			} else if(_aliveBirds.get(i).isBirdDead()== true) {
				_deadBirds.push(_aliveBirds.get(i));
				//_tempStack.add(_aliveBirds.get(i));
				_gamePane.getChildren().remove(_aliveBirds.get(i).getBird());
				_aliveBirds.remove(_aliveBirds.get(i));
			}
		}
	}

	public void makeNextGen() {
		_aliveBirds = new ArrayList <Bird>(); 
		_bestBird = _deadBirds.pop();
		_secondBird = _deadBirds.pop();
		if (_bestBird.getFitness() > 120) {
			this.selection();
		} else {
			this.newPopulation();
		}
		_deadBirds = new Stack<Bird>();
		//reset game
	}
	
	public void newPopulation() {
		for(int i=0; i < totalPopulation; i++) {
			Bird bird = new Bird(_gamePane, _game);
			_aliveBirds.add(bird);
		}
	}

	public ArrayList<Bird> getAliveBirds() {
		return _aliveBirds;
	}
	public Stack<Bird> getDeadBirds() {
		return _deadBirds;
	}
	public Bird getBestBird() {
	//	Collections.sort(_tempStack);
		return _bestBird;
	}
	public Bird getSecondBird() {
		return _secondBird;
	}

	public double getAvgFitness() {
		sum = 0;
		for (int i=0; i<_deadBirds.size(); i++) {
			sum +=_deadBirds.get(i).getFitness();	
		}
		return sum/totalPopulation;
	}
	
	public void selection() {
		//System.out.println("Selected");
		for(int i=0; i<totalPopulation;i++) {
//			if (i<totalPopulation*Constants.SELECTION_RATE) {
				Bird bird = new Bird(_gamePane,_game,_bestBird.getSyn0(), 
						_bestBird.getSyn1());
				_aliveBirds.add(bird);

//			} else if (totalPopulation*Constants.SELECTION_RATE <=i && 
//					i < 37){
//				Bird bird = new Bird(_gamePane,_game,_secondBird.getSyn0(), 
//						_secondBird.getSyn1());
//				_aliveBirds.add(bird);
//			} else {
//				Bird bird = new Bird(_gamePane, _game);
//				_aliveBirds.add(bird);
//			}
		}
		//System.out.println("Alive bird size: " + _aliveBirds.size());
	}
	
	
}