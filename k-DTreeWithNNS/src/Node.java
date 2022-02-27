
public class Node {

	public int k; //odredjuje dimenziju cvora, odnosno stabla
	public Integer level;
	public double[] array; //vrednosti tacke u cvoru 
	public Node[] children; //leva i desna grana u odnosu na svaki cvor
	
	public Node(int k, Integer level) {
		this.k = k;
		this.level = level;
		array = new double[k];
		children = new Node[2];
		
		for(int i = 0; i < 2; i++)
			children[i] = null; 
	}
	
	public boolean isEqual(double[] values) {
		boolean res = true;
		for(int i = 0; i < k; i++) {
			if (array[i] != values[i])
					res = false;
		}
		return res;
	}
	
	public double getCompared() {
		return array[level];
	}
	
	public double distance(double[] goal) {
		double res = 0.0;
		double sqrEuclidean = 0.0;
		for(int i = 0; i < k; i++) {
			double sqrCurrent = Math.pow((array[i] - goal[i]) , 2);
			sqrEuclidean += sqrCurrent; 
		}
		res = Math.sqrt(sqrEuclidean);
		return res;
		
	}
	
	public Node getLeftSubtree() {
		return children[0];
	}
	
	public Node getRightSubtree() {
		return children[1];
	}
	
	public void setValues(double[] values) {
		for(int i = 0; i < k; i++)
			array[i] = values[i];
	}
	
	public void insert(double[] values, boolean isRoot) {
		if(isRoot == true)
			setValues(values);
		else {
			Integer newLevel = (level + 1) % k;
			if(values[level] < array[level]) {	
				children[0] = new Node(k, newLevel);
				children[0].setValues(values);
			}
			else {
				children[1] = new Node(k, newLevel);
				children[1].setValues(values);
			}
		}		
	}
}
