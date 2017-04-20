package ie.gmit.sw.ai.maze.Nodes;

import ie.gmit.sw.ai.traversers.Traversator;
import ie.gmit.sw.ai.traversers.heuristic.AStarTraversator;

public class Player extends Node {

	private int health;
	private Sword sword;
	private Door door;
	private Node[][] maze;

	public Player(int row, int col, int nodeType) {
		super(row, col, nodeType);
	}

}
