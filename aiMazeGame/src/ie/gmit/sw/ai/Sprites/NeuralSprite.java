package ie.gmit.sw.ai.Sprites;

import java.util.ArrayList;
import java.util.Random;

import ie.gmit.sw.ai.Nodes.Node;
import ie.gmit.sw.ai.Nodes.Player;
import ie.gmit.sw.ai.Traversators.AStarTraversator;
import ie.gmit.sw.ai.Traversators.Traversator;

public class NeuralSprite extends Sprite implements Runnable{

	private int feature;
	private Node[][] maze;
	private Object lock;
	private int row;
	private int col;
	private Node lastNode;
	private Node node = new Node(row,col,feature);;
	private boolean canMove;
	private Player player;
	private Node nextPosition;
	
	public NeuralSprite(String name, String... images) throws Exception {
		super(name, images);
	}
	public NeuralSprite(int row, int col, int feature, Object lock, Node[][] maze, Player player) throws Exception {
		this.row = row;
		this.col = col;
		this.feature =feature;
		this.player = player;
		node .setRow(row);
		node.setCol(col);
		node.setNodeType(feature);
		
		//Lock variable
		this.lock = lock;
		//Maze variable
		this.maze = maze;
		
		//Switch statement to check what type of spider
				//This determines the Traversator for each spider
				switch (node.getNodeType()) {
				case 6:
					traversator = new AStarTraversator(player);
					break;
				case 7:
					//IDA not very good for controlling spiders - too slow
					//t = new IDAStarTraversator(player);
					traversator = new AStarTraversator(player);
					break;
				case 8:
					//traversator= new BasicHillClimbingTraversator(player);
					traversator = new AStarTraversator(player);
					break;
				case 9:
					//traversator= new BasicHillClimbingTraversator(player);
					traversator = new AStarTraversator(player);
					break;
				case 10:
					//traversator = new BruteForceTraversator(true);
					traversator = new AStarTraversator(player);
					break;
				case 11:
					//traversator = new BruteForceTraversator(true);
					traversator = new AStarTraversator(player);
					break;
				case 12:
					//traversator = new DepthLimitedDFSTraversator(maze.length / 2);
					traversator = new AStarTraversator(player);
					break;
				case 13:
					//traversator = new DepthLimitedDFSTraversator(maze.length / 2);
					traversator = new AStarTraversator(player);
					break;

				default:
					break;
				}
	}

	@Override
	public void run() {
		long time = System.currentTimeMillis();
		while(true){
			try {
				//Different sleep time per spider type
				Thread.sleep(200);//(500 * feature/2);
				//Find the path to take
				traverse(node.getRow(), node.getCol(), traversator);

				// Move around the maze if within range
				if(canMove && node.getHeuristic(player) < 80){
					roam();     
				} else {    
					randomMove();       
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}
	//Roam around the map
		public void roam(){
			if(nextPosition != null){
				synchronized(lock){
					// Figure out all the nodes around
					Node[] surroundingNodes = node.adjacentNodes(maze);
					//List of empty surrounding nodes
					ArrayList<Node> emptySurroundingNodes = new ArrayList<>();
					// Check if they are empty
					for(Node n : surroundingNodes){
						if(n.getNodeType() == -1)
						{
							emptySurroundingNodes.add(n);
						}
					}

					// Check if they are empty
					for(Node n : emptySurroundingNodes){
						if(nextPosition.equals(n) )
						{		
							//New position of the object
							int newPositionX, newPositionY;
							//Previous position of the object
							int previousPositonX = node.getRow(), previousPositionY = node.getCol();

							System.out.println();
							newPositionX = nextPosition.getRow();
							newPositionY = nextPosition.getCol();

							node.setRow(newPositionX);
							node.setCol(newPositionY);

							maze[newPositionX][newPositionY] = node;
							maze[previousPositonX][previousPositionY] = new Node(previousPositonX, previousPositionY, -1);

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
			Node[] surroundingNodes = node.adjacentNodes(maze);
			//List of empty surrounding nodes
			ArrayList<Node> emptySurroundingNodes = new ArrayList<>();


			// Check if they are empty
			for(Node n : surroundingNodes){
				if(n.getNodeType() == -1 && n != lastNode)
				{
					emptySurroundingNodes.add(n);
				}
			}

			if(emptySurroundingNodes.size() > 0){

				Random random = new Random();
				int position = random.nextInt(emptySurroundingNodes.size());

				//New position of the object
				int newPositionX, newPositionY;
				//Previous position of the object
				int previousPositonX = node.getRow(), previousPositionY = node.getCol();
				newPositionX = emptySurroundingNodes.get(position).getRow();//nextPosition.getRow();
				newPositionY = emptySurroundingNodes.get(position).getCol();//nextPosition.getCol();
				node.setRow(newPositionX);
				node.setCol(newPositionY);

				lastNode = new Node(previousPositonX, previousPositionY, -1);
				maze[newPositionX][newPositionY] = node;
				maze[previousPositonX][previousPositionY] = lastNode;
			}
		}

	}
	public void traverse(int row, int col, Traversator t){
		t.traverse(maze, maze[row][col]);
		nextPosition = t.getNextNode();
		if(nextPosition != null){
			canMove = true;
		} else {
			canMove = false;
		}
	}

}
