import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
				
		int k;
		int n;
		boolean NNS = true;
		kDTree tree;
		PointList list;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the dimension of the tree you want to create: ");
		
		k = scanner.nextInt();
		
		tree = new kDTree(k);
		System.out.println("You have successfully created a tree.");
		
		System.out.println("Enter the number of nodes in your dataset: ");
		
		n = scanner.nextInt();
		
		list = new PointList(k, n);
		System.out.println("You have successfully created a point list.");
		System.out.println("Initializing file input...");
		
		FileHandling fh = new FileHandling();
		
		
		fh.openFile();
		try {
			fh.readFile(list);
		}
		catch(FormatException e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		fh.closeFile();
		
		System.out.println("Completed.");
		
		
		System.out.println("Inserting the nodes...");
		for(int i = 0; i < n; i++)
			tree.insertNode(list.pointList[i]);
		System.out.println("Completed.");
		
		fh.createDotFile(tree);
		
		while(NNS) {
			System.out.println("Do you wish to execute NNS? (y/n)");
			char ans = scanner.next().charAt(0);
			if(ans == 'n') {
				scanner.close();
				NNS = false;
			}
			else if(ans == 'y') {
				double[] goal = new double[tree.k];
				for(int i = 0; i < tree.k; i++) {
					System.out.println("Coord " + i + ": ");
					goal[i] = scanner.nextDouble();
				}

				Node res = tree.nearestNeighbor(tree.root, goal, tree.root);
				System.out.println("Nearest neighbor found.");
				System.out.println("The node: " + tree.readNode(res));
				System.out.println("The distance is " + res.distance(goal));
			}
			else {
				System.out.println("Invalid input command.");
			}
		}
	}
		
}
