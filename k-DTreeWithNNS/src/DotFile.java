
public class DotFile {
	public String readSubtreeDot(kDTree tree, Node node) {
		String res = "";
		for(Integer i = 0; i < 2; i++) {
			if(node.children[i] != null) {
				res += "\"";
				res += tree.readNode(node);
				res += "\"";
				res += "->";		
				res += "\"";
				res += tree.readNode(node.children[i]);
				res += "\"";
				if(i == 0) {
					res += "[color = ";
					res += "\"";
					res += "blue";
					res += "\"";
					res += "]";
				}
				else {
					res += "[color = ";
					res += "\"";
					res += "red";
					res += "\"";
					res += "]";
				}
				res += ";";	
			}
			else
				continue;
		}
		for(Integer i = 0; i < 2; i++) {
			if(node.children[i] == null) continue;
			else res += readSubtreeDot(tree, node.children[i]);
		}
		return res;
	}
	
	public String toDot(kDTree tree, Node node) {
		
		String res = "digraph kDTree {";
		res += readSubtreeDot(tree, node);
		res += "}";
		return res;
	}	
}
