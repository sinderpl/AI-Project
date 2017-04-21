package ie.gmit.sw.ai.Nodes;
import java.util.*;

import ie.gmit.sw.ai.Traversators.AStarTraversator;
import ie.gmit.sw.ai.Traversators.Traversator;

public class Player extends Node {
	private Node exit;
	private AStarTraversator traversator;
	private Node[][] maze;
	private List<Node> playerPath;
	private boolean sword;
	private boolean bomb;
	private boolean hbomb;
	private boolean help;
	private double health = 100;
	
	public Player(int row, int col, int nodeType, Node[][] maze) {
		super(row, col, nodeType);
		this.maze = maze;
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

	public boolean isSword() {
		return sword;
	}

	public void setSword(boolean sword) {
		this.sword = sword;
	}

	public boolean isBomb() {
		return bomb;
	}

	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	public boolean isHbomb() {
		return hbomb;
	}

	public void setHbomb(boolean hbomb) {
		this.hbomb = hbomb;
	}

	public boolean isHelp() {
		return help;
	}

	public void setHelp(boolean help) {
		this.help = help;
	}

	public double getWeapon() {
		if(isSword())
			return 3.0;
		if(isBomb())
			return 6.0;
		if(isHbomb())
			return 9.0;
		return 0;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}


	
	

}
