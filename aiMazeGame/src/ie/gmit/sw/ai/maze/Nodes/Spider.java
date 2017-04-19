package ie.gmit.sw.ai.maze.Nodes;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

import ie.gmit.sw.ai.maze.ThreadPool;

//A spider class, it extends Node so that it can be applied to the Maze array.
public class Spider extends Node{
	
	//Pool for reference
	private ExecutorService pool;
	//Lock object
	Object lock;
	//Maze reference variable
	Node[][] maze;
	private ExecutorService executor = Executors.newFixedThreadPool(1);
	public Spider(int row, int col, int nodeType, Object lock, ThreadPool pool, Node[][] maze) {
		
		//Set the location variables in the parent
		super(row, col, nodeType);
		//Assign the pool variable to the local var
		this.pool = pool.getPool();
		//Lock variable
		this.lock = lock;
		//Maze variable
		this.maze = maze;
		
		//Execute the spider movement in a thread
		executor.submit(() ->{
			while(true){
			try{
				//Time between movements
				Thread.sleep(1000);
				// Move around the maze
				roam();
				
			}catch (Exception e) {
				System.out.println(e);
			}
			}
		});			
	}
	
	//Roam around the map
	public void roam(){
		synchronized(lock){
			System.out.println("moving");
			// Figure out all the nodes around
			Node[] surroundingNodes = adjacentNodes(maze);
			//List of empty surrounding nodes
			ArrayList<Node> emptySurroundingNodes = new ArrayList<>();
			
			
			// Check if they are empty
			for(Node n : surroundingNodes){
				if (n.getNodeType() == -1){
					emptySurroundingNodes.add(n);
				}
			}
			// Move to random in empty
			
			if(emptySurroundingNodes.size() > 0){
				//New position of the object
				 int newPositionX, newPositionY;
				 //Previous position of the object
				 int previousPositonX = getRow(), previousPositionY = getCol();
				 
				 
				 //Choose a position to move to from the array of available nodes
				 Random random = new Random();
				 int position = random.nextInt(emptySurroundingNodes.size());
				 newPositionX = emptySurroundingNodes.get(position).getRow();
				 newPositionY = emptySurroundingNodes.get(position).getCol();
				 
				 setRow(newPositionX);
				 setCol(newPositionY);
				 maze[newPositionX][newPositionY] = (Spider)this;
				 maze[previousPositonX][previousPositionY] = emptySurroundingNodes.get(position);
				 
			}
			
		}
		
	}
	
}