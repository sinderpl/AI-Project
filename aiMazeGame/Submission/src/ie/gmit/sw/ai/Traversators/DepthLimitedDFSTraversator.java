package ie.gmit.sw.ai.Traversators;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import ie.gmit.sw.ai.Nodes.Node;

public class DepthLimitedDFSTraversator implements Traversator{

	private Node[][] maze;
	private int limit;
	private boolean keepRunning = true;
	private int visitCount = 0;
    private Node goal;
    private Set<Node> isVisited = null;
    private LinkedList<Node> pathToGoal = null;
	
	public DepthLimitedDFSTraversator(int limit,Node goal){
		this.limit = limit;
		this.goal = goal;
	}
	
	public void traverse(Node[][] maze, Node node) {
	    pathToGoal = new LinkedList<>();
		this.maze = maze;
		isVisited = new HashSet<>();

		if(dfs(node, 1) == true){
		    pathToGoal.addFirst(node);
        }
		isVisited = null;
    }
    public Node getNextNode(){

	    if(pathToGoal.size() > 0){

	        return pathToGoal.getFirst();
        }
        else
        {
	        return null;
        }

    }

	private boolean dfs(Node node, int depth){
		if (!keepRunning || depth > limit) return false;

		isVisited.add(node);
		visitCount++;

		if (node.equals(goal)){

		    pathToGoal.addFirst(node);

	        keepRunning = false;

			return true;
		}

        // get nodes children
		Node[] children = node.adjacentNodes(maze);
		for (int i = 0; i < children.length; i++) {

		    // only visit node's children if not visited
			if (children[i] != null && !isVisited.contains(children[i])){

			    // if goal was found
				if(dfs(children[i], depth + 1) == true) {

				    // save this node to path to goal
                    pathToGoal.addFirst(node);

                    // return true so next node in path is saved
                    return true;
                }
			}
		}
        return false;
	}
}