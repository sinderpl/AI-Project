package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ie.gmit.sw.ai.maze.Maze;
import ie.gmit.sw.ai.maze.MazeGenerators.MazeGenerator;
import ie.gmit.sw.ai.maze.MazeGenerators.MazeGeneratorFactory;
public class GameRunner implements KeyListener{
	private static final int MAZE_DIMENSION = 30;
	private static final int IMAGE_COUNT = 15;
	private GameView view;
	private Maze model;
	private int currentRow;
	private int currentCol;
	private MazeGenerator generator;
	//private static String mazeAlgo ="HuntAndKill";
	
	//GameRunner takes in String mazeAlgo as parameter for Maze Generation
	public GameRunner(String mazeAlgo) throws Exception{
		
		MazeGeneratorFactory factory = MazeGeneratorFactory.getInstance();
		System.out.println(mazeAlgo);
		switch (mazeAlgo) {
		case "BinaryTree":
			System.out.println("BinaryTree Selected");
			generator = factory.getMazeGenerator(MazeGenerator.GeneratorAlgorithm.BinaryTree, MAZE_DIMENSION, MAZE_DIMENSION);
			break;
		case "HuntAndKill":
			System.out.println("HuntAndKill Selected");
			generator = factory.getMazeGenerator(MazeGenerator.GeneratorAlgorithm.HuntAndKill, MAZE_DIMENSION, MAZE_DIMENSION);
			break;
		case "RandomDepthFirst":
			System.out.println("RandomDepthFirst Selected");
			generator = factory.getMazeGenerator(MazeGenerator.GeneratorAlgorithm.RandomDepthFirst, MAZE_DIMENSION, MAZE_DIMENSION);
			break;
		case "RandomizedKruskal":
			System.out.println("RandomizedKruskal Selected");
			generator = factory.getMazeGenerator(MazeGenerator.GeneratorAlgorithm.RandomizedKruskal, MAZE_DIMENSION, MAZE_DIMENSION);
			break;
		case "RandomizedPrim":
			System.out.println("RandomizedPrim Selected");
			generator = factory.getMazeGenerator(MazeGenerator.GeneratorAlgorithm.RandomizedPrim, MAZE_DIMENSION, MAZE_DIMENSION);
			break;
		case "RecursiveBacktracker":
			System.out.println("RecursiveBacktracker Selected");
			generator = factory.getMazeGenerator(MazeGenerator.GeneratorAlgorithm.RecursiveBacktracker, MAZE_DIMENSION, MAZE_DIMENSION);
			break;
		case "RecursiveDivision":
			System.out.println("RecursiveDivision Selected");
			generator = factory.getMazeGenerator(MazeGenerator.GeneratorAlgorithm.RecursiveDivision, MAZE_DIMENSION, MAZE_DIMENSION);
			break;
		default:
			break;
		}
		
		model = new Maze(generator, MAZE_DIMENSION);
    	view = new GameView(model);

    	Sprite[] sprites = getSprites();
    	view.setSprites(sprites);
    	
    	updateView();
    	//placePlayer();
    	
    	Dimension d = new Dimension(GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
    	
    	JFrame f = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setVisible(true);
        
	}
	

	private void updateView(){
		currentRow = model.getPlayer().getRow();
    	currentCol = model.getPlayer().getCol();
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
		System.out.println(model.getDoor());
	}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow, currentCol + 1)) currentCol++;   		
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
        	if (isValidMove(currentRow, currentCol - 1)) currentCol--;	
        }else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
        	if (isValidMove(currentRow - 1, currentCol)) currentRow--;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow + 1, currentCol)) currentRow++;        	  	
        }else if (e.getKeyCode() == KeyEvent.VK_Z){
        	view.toggleZoom();
        }else{
        	return;
        }
        
        updateView();       
    }
    public void keyReleased(KeyEvent e) {} //Ignore
	public void keyTyped(KeyEvent e) {} //Ignore

    
	private boolean isValidMove(int row, int col){
		if (row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == -1){
			model.set(currentRow, currentCol, model.get(row, col));
			model.set(row, col, model.getPlayer());
			return true;
		}else if (row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == -1|| model.get(row, col).getNodeType() == 1){
			model.get(row, col).setNodeType(0);
			//Weaponstrength+=3;
			return false;
		}
		else if (row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == -1|| model.get(row, col).getNodeType() == 2){
			model.get(row, col).setNodeType(0);
			//Weaponstrength+=3;
			return false;
		}
		else if (row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == -1|| model.get(row, col).getNodeType() == 3){
			model.get(row, col).setNodeType(0);
			//Weaponstrength+=3;
			return false;
		}
		else if (row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == -1|| model.get(row, col).getNodeType() == 4){
			model.get(row, col).setNodeType(0);
			//Weaponstrength+=3;
			return false;
		}
		else if (row <= model.size() - 1 && col <= model.size() - 1 && model.get(row, col).getNodeType() == -1|| model.get(row, col).getNodeType() == 14){
			System.exit(0);
		}else{
			return false; //Can't move
		}
		return false;
	}
	
	private Sprite[] getSprites() throws Exception{
		//Read in the images from the resources directory as sprites. Note that each
		//sprite will be referenced by its index in the array, e.g. a 3 implies a Bomb...
		//Ideally, the array should dynamically created from the images... 
		Sprite[] sprites = new Sprite[IMAGE_COUNT];
		sprites[0] = new Sprite("Hedge", "resources/hedge.png");
		sprites[1] = new Sprite("Sword", "resources/sword.png");
		sprites[2] = new Sprite("Help", "resources/help.png");
		sprites[3] = new Sprite("Bomb", "resources/bomb.png");
		sprites[4] = new Sprite("Hydrogen Bomb", "resources/h_bomb.png");
		sprites[5] = new Sprite("Spartan Warrior", "resources/spartan_1.png", "resources/spartan_2.png");
		sprites[6] = new Sprite("Black Spider", "resources/black_spider_1.png", "resources/black_spider_2.png");
		sprites[7] = new Sprite("Blue Spider", "resources/blue_spider_1.png", "resources/blue_spider_2.png");
		sprites[8] = new Sprite("Brown Spider", "resources/brown_spider_1.png", "resources/brown_spider_2.png");
		sprites[9] = new Sprite("Green Spider", "resources/green_spider_1.png", "resources/green_spider_2.png");
		sprites[10] = new Sprite("Grey Spider", "resources/grey_spider_1.png", "resources/grey_spider_2.png");
		sprites[11] = new Sprite("Orange Spider", "resources/orange_spider_1.png", "resources/orange_spider_2.png");
		sprites[12] = new Sprite("Red Spider", "resources/red_spider_1.png", "resources/red_spider_2.png");
		sprites[13] = new Sprite("Yellow Spider", "resources/yellow_spider_1.png", "resources/yellow_spider_2.png");
		sprites[14] = new Sprite("Door", "resources/door.png");
		return sprites;
	}
}