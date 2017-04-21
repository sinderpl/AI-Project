package ie.gmit.sw.ai.Nodes;
import java.util.*;

import ie.gmit.sw.ai.Traversators.AStarTraversator;
import ie.gmit.sw.ai.Traversators.Traversator;

public class Player extends Node {
	private Node exit;
	private AStarTraversator traversator;
	private Node[][] maze;
	private List<Node> playerPath;
	
	
	public Player(int row, int col, int nodeType, Node[][] maze) {
		super(row, col, nodeType);
		this.maze = maze;
		// TODO Auto-generated constructor stub
	}
	
	public Node getsetExit(){
		return exit;
	}
	
	public void setExit(Node exit){
		this.exit = exit;
		traversator = new AStarTraversator(exit);
		startTraversator();
	}
	
	public void startTraversator(){
		//while(true){
			System.out.println("player path");
			traversator.traverse(maze, maze[getRow()][getCol()]);
			playerPath = traversator.getPath();		
			System.out.println(playerPath);
		//}
	}
	
	

}
