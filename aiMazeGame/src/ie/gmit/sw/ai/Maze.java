package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ie.gmit.sw.ai.Nodes.Node;
import ie.gmit.sw.ai.Nodes.Player;
import ie.gmit.sw.ai.Sprites.FuzzySprite;
import ie.gmit.sw.ai.Sprites.NeuralSprite;

//2d char array of nodes
public class Maze {
	private Node[][] maze;
	private Object lock = new Object();
	private ExecutorService es =Executors.newCachedThreadPool();
	private Player player;
	private Node door;
	private List<FuzzySprite> sprites = new ArrayList<>();	
	
	public Maze(int dimension) throws Exception{
		maze = new Node[dimension][dimension];
		init();
		buildMaze();
		
		int featureNumber = (int)((dimension * dimension) * 0.01);
		addFeature(1, 0, featureNumber); //1 is a sword, 0 is a hedge
		addFeature(2, 0, featureNumber); //2 is help, 0 is a hedge
		addFeature(3, 0, featureNumber); //3 is a bomb, 0 is a hedge
		addFeature(4, 0, featureNumber); //4 is a hydrogen bomb, 0 is a hedge
		
		//Place the Player in the maze
		placePlayer(5, -1);
		
		//Place the Door(Goal Node) in the maze
		placeDoor(14, -1);
		
		featureNumber = 10;
		
		//Fuzzy spiders
		addFeature(6, -1, featureNumber); //6 is a Black Spider, 0 is a hedge
		addFeature(7, -1, featureNumber); //7 is a Blue Spider, 0 is a hedge
		addFeature(8, -1, featureNumber); //8 is a Brown Spider, 0 is a hedge
		addFeature(9, -1, featureNumber); //9 is a Green Spider, 0 is a hedge
		
		//Neural spiders
		addFeature(10, -1, featureNumber); //: is a Grey Spider, 0 is a hedge
		addFeature(11, -1, featureNumber); //; is a Orange Spider, 0 is a hedge
		
		//Number 12 is the main neural network spider, It attacks/runs away depending on the NN
		addFeature(12, -1, featureNumber); //< is a Red Spider, 0 is a hedge
		addFeature(13, -1, featureNumber); //= is a Yellow Spider, 0 is a hedge
	}
	
	public void placePlayer(int feature, int replace){
		boolean placed = false;
		while(!placed){
			int row = (int) (maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
			if (maze[row][col].getNodeType() == replace){
				player = new Player(row,col,feature, maze);
				maze[row][col] = player;
				placed = true;
			}
		}
	}
	
	private void placeDoor(int feature, int replace) {
		boolean placedDoor = false;
		while(!placedDoor){
			int row = (int) (maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
			if (maze[row][col].getNodeType() == replace){
				door = new Node(row,col,feature);
				maze[row][col] = door;
				door.setGoalNode(true);
				player.setExit(door);
				placedDoor = true;
			}
		}
	}

	private void init(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				maze[row][col] = new Node(row,col, 0); //Index 0 is a hedge...
			}
		}
	}
	
	private void addFeature(int feature, int replace, int number) throws Exception{
		int counter = 0;
		while (counter < number){
			int row = (int) (maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());
			
			if (maze[row][col].getNodeType() == replace){
				
				if(feature > 5 && feature < 10){
					//Creates a new thread for each spider
					FuzzySprite sprite = new FuzzySprite(row, col, feature, lock, maze, getPlayer(), counter);
					sprites.add(sprite);
					es.execute(sprite);
				}
				else if(feature >= 10){
					es.execute(new NeuralSprite(row, col, feature, lock, maze, getPlayer(), counter));
				}
					maze[row][col].setNodeType(feature);
					counter++;
			}
		}
	}
	
	private void buildMaze(){ 
		for (int row = 1; row < maze.length - 1; row++){
			for (int col = 1; col < maze[row].length - 1; col++){
				int num = (int) (Math.random() * 10);
				if (num > 5 && col + 1 < maze[row].length - 1){
					maze[row][col + 1].setNodeType(-1); //\u0020 = 0x20 = 32 (base 10) = SPACE
				}else{
					if (row + 1 < maze.length - 1)maze[row + 1][col].setNodeType(-1);
				}
			}
		}		
	}
	
	public Node[][] getMaze(){
		return this.maze;
	}
	
	public Node get(int row, int col){
		return this.maze[row][col];
	}
	
	public void set(int row, int col, Node n){
		n.setRow(row);
		n.setCol(col);
		this.maze[row][col] = n;
	}
	
	public int size(){
		return this.maze.length;
	}
	
	public Node getDoor(){
		return this.door;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				sb.append(maze[row][col]);
				if (col < maze[row].length - 1) sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void setPlayer(int row, int col){
		this.maze[row][col] = this.player;
		this.player.setRow(row);
		this.player.setCol(col);
	}
	public Player getPlayer(){
		return this.player;
	}

	public FuzzySprite getSpriteId(int row, int col){
		for(FuzzySprite s : sprites ){
			if (maze[row][col].getRow() == row && maze[row][col].getCol() == col){
				return s;
			}
		}
		return null;
	}
}