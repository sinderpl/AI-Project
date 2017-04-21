package ie.gmit.sw.ai;

public interface Traversator {

	public void traverse(Node[][] maze, Node start);
	public Node getNextNode();
}
