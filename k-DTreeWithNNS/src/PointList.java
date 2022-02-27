
public class PointList {

	public int k;
	public int n;
	public double[][] pointList;
	
	public PointList(int k, int n) {
		this.k = k;
		this.n = n;
		pointList = new double[n][k];
	}
	
	public PointList(int k, int n, double[][] list) {
		
		this.k = k;
		this.n = n;
		pointList = new double[n][k];
		
		for(int i = 0; i<n; i++)
			for(int j =0; j<k; j++)
				pointList[i][j] = list[i][j];
		
	}
	
}
