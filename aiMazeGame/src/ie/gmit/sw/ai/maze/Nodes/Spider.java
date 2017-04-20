package ie.gmit.sw.ai.maze.Nodes;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

import ie.gmit.sw.ai.maze.ThreadPool;
import ie.gmit.sw.ai.traversers.Traversator;
import ie.gmit.sw.ai.traversers.heuristic.AStarTraversator;

//A spider class, it extends Node so that it can be applied to the Maze array.
public class Spider extends Node{

	//Pool for reference
	private ExecutorService pool;
	//Lock object
	Object lock;
	//Maze reference variable
	Node[][] maze;
	private ExecutorService executor = Executors.newFixedThreadPool(1);

	private Player player = null;
	private Node nextPosition;
	private boolean canMove = false;

	public Spider(int row, int col, int nodeType, Object lock, ThreadPool pool, Node[][] maze, Player player) {

		//Set the location variables in the parent
		super(row, col, nodeType);
		//Assign the pool variable to the local var
		this.pool = pool.getPool();
		//Lock variable
		this.lock = lock;
		//Maze variable
		this.maze = maze;
		//Player variable
		this.player = player;

		//Execute the spider movement in a thread
		executor.submit(() ->{
			while(true){
				try{
					//Time between movements
					Thread.sleep(600);
					//Find the path to take
					traverse(getRow(), getCol());

					
					// Move around the maze if within range
					if(canMove && getHeuristic(player) < 9){
						System.out.println("Searching");
						roam();     
					} else {    
						System.out.println("Walking");
						randomMove();       
					}

				}catch (Exception e) {
					System.out.println(e);
				}
			}
		});			
	}

	//Roam around the map
	public void roam(){
		if(nextPosition != null){
			synchronized(lock){
				// Figure out all the nodes around
				Node[] surroundingNodes = adjacentNodes(maze);
				//List of empty surrounding nodes
				ArrayList<Node> emptySurroundingNodes = new ArrayList<>();


				// Check if they are empty
				for(Node n : surroundingNodes){
					if(nextPosition.equals(n) && n.getNodeType() == -1)
					{		
						//New position of the object
						int newPositionX, newPositionY;
						//Previous position of the object
						int previousPositonX = getRow(), previousPositionY = getCol();

						newPositionX = nextPosition.getRow();
						newPositionY = nextPosition.getCol();

						setRow(newPositionX);
						setCol(newPositionY);

						maze[newPositionX][newPositionY] = (Spider)this;
						maze[previousPositonX][previousPositionY] = nextPosition;
						nextPosition = null;
						canMove = false;
						return;
					}	
				}
				// Move to random in empty
				randomMove();

				nextPosition = null;
				canMove = false;
				return;
			}
		}
		else{
			randomMove();

			canMove = false;
		}
	}
	private void randomMove() {
		synchronized(lock){
			// Figure out all the nodes around
			Node[] surroundingNodes = adjacentNodes(maze);
			//List of empty surrounding nodes
			ArrayList<Node> emptySurroundingNodes = new ArrayList<>();


			// Check if they are empty
			for(Node n : surroundingNodes){
				if(n.getNodeType() == -1)
				{
					emptySurroundingNodes.add(n);
				}
			}
			
			if(emptySurroundingNodes.size() > 0){
				System.out.println(emptySurroundingNodes);
				
				Random random = new Random();
				int position = random.nextInt(emptySurroundingNodes.size());
				
				//New position of the object
				int newPositionX, newPositionY;
				//Previous position of the object
				int previousPositonX = getRow(), previousPositionY = getCol();
				System.out.println("Previous: " + previousPositonX + " " + previousPositionY);
				newPositionX = emptySurroundingNodes.get(position).getRow();//nextPosition.getRow();
				newPositionY = emptySurroundingNodes.get(position).getCol();//nextPosition.getCol();
				System.out.println("New: " + newPositionX + " " + newPositionY + " TypeOfNode: " + maze[newPositionX][newPositionY].getNodeType());
				setRow(newPositionX);
				setCol(newPositionY);
				

				maze[newPositionX][newPositionY] = (Spider)this;
				System.out.println("New position in maze: " + maze[newPositionX][newPositionY]);
				System.out.println("New position in maze: " + maze[newPositionX][newPositionY].getNodeType());
				
				maze[previousPositonX][previousPositionY] = new Node(previousPositonX, previousPositionY, -1);
			}
		}
		
	}
	public void traverse(int row, int col){
			Traversator t = new AStarTraversator(player);
			t.traverse(maze, maze[row][col]);
			nextPosition = t.getNextNode();

			if(nextPosition != null){
	            canMove = true;
	        } else {
	        	canMove = false;
	        }
	}
}