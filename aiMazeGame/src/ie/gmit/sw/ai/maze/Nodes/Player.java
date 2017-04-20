package ie.gmit.sw.ai.maze.Nodes;

import ie.gmit.sw.ai.traversers.Traversator;
import ie.gmit.sw.ai.traversers.heuristic.AStarTraversator;

public class Player extends Node {

	private int health;
	private Sword sword;
	private Door door;
	private Node[][] maze;

	public Player(int row, int col, int nodeType, Node[][] maze, Door door) {
		super(row, col, nodeType);
		this.door = door;
		this.maze = maze;

		/**
		while(true){
			try{
				traverse();
			}catch (Exception e) {
				
			}
		}**/
	}

	/**
	private void traverse() {
		Traversator t = new AStarTraversator(door);
		t.traverse(maze, maze[getRow()][getCol()]);
	}
**/
}
