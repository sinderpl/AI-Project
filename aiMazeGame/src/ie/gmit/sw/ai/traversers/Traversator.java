package ie.gmit.sw.ai.traversers;

import ie.gmit.sw.ai.maze.*;
import ie.gmit.sw.ai.maze.Nodes.Node;
public interface Traversator {
	public void traverse(Node[][] maze, Node start);
	
}
