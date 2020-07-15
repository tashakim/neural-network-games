package flappy_bird;


public class NeuralNetwork {
//
//	private double[] z;
//	private double[] a;
//	private double _output;
//	private double _activatedOutput;
	private double[][] _syn0;
	private double[][] _syn1;

	public NeuralNetwork() {

		_syn0 = new double[5][4];
		for (int i=0; i<5; i++) {
			for(int j=0; j<4; j++) {
				_syn0[i][j] = (2*Math.random()-1); //random value from -1 to 1.
			}
		}
		_syn1 = new double[1][5];
		for(int i=0; i<5; i++) {
			_syn1[0][i] = (2*Math.random()-1);
		}
		//this.printMatrix(_syn0);
	}
	public NeuralNetwork(double[][] syn0, double[][] syn1) {
		_syn0 = copy(syn0);
		_syn1 = copy(syn1);
		
		mutate();
		//this.printMatrix(_syn0);
		//this.printMatrix(_syn1);
	}
	
	public void printMatrix(double[][] matrix) {
		for (double[] line: matrix) {
			for (double val: line) {
			}
		}

	}
	
	public double[][] copy(double[][] mat) {
		double[][] matrix = new double[mat.length][mat[0].length];
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat[0].length; j++) {
				matrix[i][j] = mat[i][j]; 
			}
		}
		return matrix;
	}
	
	public double getOutput(double[] inputs) {
		double[] z = new double[5];
		double[] a = new double[5];
		double output = 0;
		
		for (int i=0; i<5; i++) {
			for(int j=0; j<4; j++) {
				z[i] += _syn0[i][j]*inputs[j];
			}
		}
		
		
		for(int i=0; i<5;i++) {    
			a[i]= 1/(1+Math.pow(Math.E, -z[i])); //activates layer 1
//			a[i] = Math.ata5n(z[i]);

		}
		
		for(int i =0; i<5; i++) {
			output += _syn1[0][i]*a[i];
		}
				
		output = 1/(1+Math.pow(Math.E, -output)); //activates layer 2
//		_activatedOutput = Math.atan(_output);
		//System.out.println("The Activated Output is : " +_activatedOutput);
		return output;
	}
	public double[][] getSyn0() {
		return _syn0;
	}
	public double[][] getSyn1() {
		return _syn1;
	}
	public void mutate() {
		for (int i=0; i < 5; i++) {
			for(int j =0; j <4; j++) {
				if(Math.random()<0.25) {
					_syn0[i][j] = _syn0[i][j] + 0.5*Math.random()-0.25;//new value
					if (_syn0[i][j] <-1) {
						_syn0[i][j] =-1;
					}
					if (_syn0[i][j] >1) {
						_syn0[i][j] = 1;
					}
				//	_syn0[i][j] = Math.random() * 2 - 1;
				}
			}	
		}
		for (int i=0; i<1; i++) {
			for(int j=0; j<5; j++) {
				if(Math.random()<0.25) {
					_syn1[i][j] = _syn1[i][j] + 0.5*Math.random()-0.25; //new value
					//System.out.println("syn1 :" + _syn1[i][j]);
					if (_syn1[i][j] <-1) {
						_syn1[i][j] =-1;
					}
					if (_syn1[i][j] >1) {
						_syn1[i][j] = 1;
					}
			//		_syn1[i][j] = Math.random() * 2 - 1;
				}
			}
		}
	}
}