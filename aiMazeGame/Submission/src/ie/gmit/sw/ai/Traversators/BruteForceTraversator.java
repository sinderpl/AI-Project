package ie.gmit.sw.ai.Traversators;

import java.util.*;

import ie.gmit.sw.ai.Nodes.Node;
public class BruteForceTraversator implements Traversator{
	private boolean dfs = false;
	private LinkedList<Node> path;
	private boolean pathFound = false;
	
	public BruteForceTraversator(boolean depthFirst){
		this.dfs = depthFirst;
	}
	
	public void traverse(Node[][] maze, Node node) {
        long time = System.currentTimeMillis();
    	int visitCount = 0;
    	path = new LinkedList<>();
		Deque<Node> queue = new LinkedList<Node>();
		queue.offer(node);
		
		while (!queue.isEmpty()){
			node = queue.poll();
			node.setVisited(true);
			visitCount++;
			
			if (node.isGoalNode()){
		        time = System.currentTimeMillis() - time; //Stop the clock
		        //TraversatorStats.printStats(node, time, visitCount);
				break;
			}
			
			try { //Simulate processing each expanded node				
				Thread.sleep(10);						
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Node[] children = node.adjacentNodes(maze);
			for (int i = 0; i < children.length; i++) {
				if (children[i] != null && !children[i].isVisited()){
					children[i].setParent(node);
					if (dfs){
						queue.addFirst(children[i]);
					}else{
						queue.addLast(children[i]);
					}
					
				}
			}
			System.out.println(queue.getFirst());
			path.addAll(queue);
			pathFound =true;		
		}
	}

	@Override
	public Node getNextNode() {
		System.out.println(path.getFirst());
		if(pathFound  == true){
			return path.getFirst();
		}
		else{
			return null;
		}
	}
}