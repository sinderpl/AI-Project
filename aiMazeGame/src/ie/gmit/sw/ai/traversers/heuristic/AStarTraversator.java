package ie.gmit.sw.ai.traversers.heuristic;

import ie.gmit.sw.ai.maze.*;
import ie.gmit.sw.ai.maze.Nodes.Node;
import ie.gmit.sw.ai.traversers.Traversator;
import ie.gmit.sw.ai.traversers.TraversatorStats;

import java.util.*;
public class AStarTraversator implements Traversator{
	private Node goal;
	private LinkedList<Node> pathToGoal = null;
	
	public AStarTraversator(Node goal){
		this.goal = goal;
	}
	
	public void traverse(Node[][] maze, Node node) {
		
        long time = System.currentTimeMillis();
    	int visitCount = 0;
    	
		PriorityQueue<Node> open = new PriorityQueue<Node>(20, (Node current, Node next)-> (current.getPathCost() + current.getHeuristic(goal)) - (next.getPathCost() + next.getHeuristic(goal)));
		java.util.List<Node> closed = new ArrayList<Node>();
    	   	
		open.offer(node);
		node.setPathCost(0);		
		while(!open.isEmpty()){
			node = open.poll();		
			closed.add(node);
			node.setVisited(true);	
			visitCount++;
			
			if (node.isGoalNode()){
		        time = System.currentTimeMillis() - time; //Stop the clock
		        TraversatorStats.printStats(node, time, visitCount);
				break;
			}
			
			/*try { //Simulate processing each expanded node
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			
			//Process adjacent nodes
			Node[] children = node.adjacentNodes(maze);
			
			for (int i = 0; i < children.length; i++) {
				Node child = children[i];
				int score = node.getPathCost() + 1 + child.getHeuristic(goal);
				int existing = child.getPathCost() + child.getHeuristic(goal);
				
				if ((open.contains(child) || closed.contains(child)) && existing < score){
					continue;
				}else{
					open.remove(child);
					closed.remove(child);
					child.setParent(node);
					child.setPathCost(node.getPathCost() + 1);
					open.add(child);
				}
			}									
		}
	}
	
	public Node getNextNode(){

	    if(pathToGoal.size() > 0){

	        return pathToGoal.getFirst();
        }
        else
        {

	        return null;
        } // if

    } // getNextNode()
}
