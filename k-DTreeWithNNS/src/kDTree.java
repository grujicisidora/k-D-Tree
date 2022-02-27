//import java.util.Scanner;

public class kDTree {

	public int k; //dimenzija stabla 
	public boolean rootInitialized;
	public Node root; //koren stabla
	
	public kDTree(int k) {
		this.k = k;
		root = new Node(k, 0);
		rootInitialized = false;
	}
		
	public String readNode(Node node) {
		if(node == null) return " Empty ";
		else {
			String res = "";
			for (int i = 0; i < k; i++) {
					res += node.array[i];
					res += "   ";
			}
			return res;	
		}	
	}
	
	public Node getNode(double[] values, Node n) {
		if(n == null) return n; //ako pocetni cvor ne postoji, ne mozemo naci vrednost, ali vracamo n, to jest null vrednost -- isto je 
		else {
			if(rootInitialized == false) {
				return n; //ako root nije inicijalizovan, vracamo ga
			}
			boolean indicator = n.isEqual(values); //proveravamo da li cvor ima iste vrednosti kao one koje hocemo da unesemo
			if(indicator == true) return null; //ako ima, vracamo null vrednost
			//ako nema, pronalazimo sledeceg kandidata za insert
			//zato je ovo rekurzivna funkcija
			else {
				if(values[n.level] < n.array[n.level]) {
					if(n.getLeftSubtree() == null) return n;
					else {
						return getNode(values, n.children[0]); 
					}			
				}
				else {
					if(n.getRightSubtree() == null) return n;
					else {
						return getNode(values, n.children[1]);
					}		
				}
			}
		}
	}
	
	public int insertNode(double[] values) {
		if(root == null) {
			return -1;
		}
		else {
			if(rootInitialized == false) {
				getNode(values, root).insert(values, true);
				rootInitialized = true;
				return 1;
			}
			else {
				Node n = getNode(values, root);
				if(n == null) return 0;
				else {
					if(values[n.level] < n.array[n.level]) {
						getNode(values, root).insert(values, false);
					}		
					else {
						getNode(values, root).insert(values, false);
					}	
					return 1;
				}	
			}
		}
	}
	
	public Node nearestNeighbor(Node n, double[] goal, Node best) {
		Node goodSide;
		Node badSide;
		if(n == null)
			return best;
		if(n.distance(goal) < best.distance(goal)) 
			best = n;
		if(goal[n.level] < n.array[n.level]) {
			goodSide = n.children[0];
			badSide = n.children[1];
		}
		else {
			goodSide = n.children[1];
			badSide = n.children[0];
		}
		best = nearestNeighbor(goodSide, goal, best);
		if(Math.abs(goal[n.level] - n.array[n.level]) < best.distance(goal)) {
			best = nearestNeighbor(badSide, goal, best);
		}
		return best;
	}
}
