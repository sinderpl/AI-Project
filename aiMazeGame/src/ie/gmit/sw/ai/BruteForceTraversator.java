package ie.gmit.sw.ai;

import java.util.*;
public class BruteForceTraversator implements Traversator{
	private boolean dfs = false;
	private LinkedList<Node> path;
	
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
				path.addAll(queue);
			}			
		}
	}

	@Override
	public Node getNextNode() {
		
		return path.getFirst();
	}
}