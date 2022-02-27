import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileHandling {

	private Scanner scanner;
	
	public void createDotFile(kDTree tree) {
		try {
			FileWriter file = new FileWriter("kDTree.dot");
			DotFile df = new DotFile();
			file.write(df.toDot(tree, tree.root));
			file.close();
			System.out.println("You successfully made your dot file.");
		}
		catch(IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public void openFile() {
		try {
			scanner = new Scanner(new File("nodes.txt"));
		}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
		}
	}
	
	public void readFile(PointList list) throws FormatException {
		int count = 0;
		while(scanner.hasNext()) {
			try {
				for(int i = 0; i < list.n; i++)
					for(int j = 0; j < list.k; j++) {
						list.pointList[i][j] = scanner.nextDouble(); 
						count++;
					}
			}
			catch(Exception e) {
				System.out.println("Error! The file length is incompatible with the dimension and the number of nodes. Please check the input file and rerun the program.");
				System.exit(0);
			}
		}
		if(count > (list.n * list.k))
			throw new FormatException("Error! There are too many input values. Please check the input file and rerun the program.");
	}
	
	public void closeFile() {
		scanner.close();
	}
		
}
