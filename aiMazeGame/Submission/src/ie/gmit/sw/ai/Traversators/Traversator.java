package ie.gmit.sw.ai.Traversators;

import ie.gmit.sw.ai.Nodes.Node;

public interface Traversator {

	public void traverse(Node[][] maze, Node start);
	public Node getNextNode();
}
