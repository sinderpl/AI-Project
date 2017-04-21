package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import ie.gmit.sw.ai.Nodes.Node;
import ie.gmit.sw.ai.Nodes.Player;
import ie.gmit.sw.ai.Sprites.Sprite;
public class GameView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_VIEW_SIZE = 800;	
	private int cellspan = 5;	
	private int cellpadding = 2;
	private Maze maze;
	private Sprite[] sprites;
	private int enemy_state = 5;
	private Timer timer;
	private int currentRow;
	private int currentCol;
	private boolean zoomOut = false;
	private int imageIndex = -1;
	private int offset = 48; //The number 0 is ASCII 48.
	private Color[] reds = {new Color(255, 0, 0)}; //Animate enemy "dots" to make them easier to see
	private Player player;
	
	public GameView(Maze maze) throws Exception{
		this.maze = maze;
		setBackground(Color.LIGHT_GRAY);
		setDoubleBuffered(true);
		timer = new Timer(300, this);
		timer.start();
	}
	
	public void setCurrentRow(int row) {
		if (row < cellpadding){
			currentRow = cellpadding;
		}else if (row > (maze.size() - 1) - cellpadding){
			currentRow = (maze.size() - 1) - cellpadding;
		}else{
			currentRow = row;
		}
	}

	public void setCurrentCol(int col) {
		if (col < cellpadding){
			currentCol = cellpadding;
		}else if (col > (maze.size() - 1) - cellpadding){
			currentCol = (maze.size() - 1) - cellpadding;
		}else{
			currentCol = col;
		}
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        
        cellspan = zoomOut ? maze.size() : 5;         
        final int size = DEFAULT_VIEW_SIZE/cellspan;
        g2.drawRect(0, 0, GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
        
        for(int row = 0; row < cellspan; row++) {
        	for (int col = 0; col < cellspan; col++){  
        		int x1 = col * size;
        		int y1 = row * size;
        		
        		int i = 0;
       		
        		if (zoomOut){
        			i = maze.get(row, col).getNodeType();
        			if (i >= 5){
	        			if (row == currentRow && col == currentCol){
	        				g2.setColor(Color.BLACK);
	        			}else{
	        				g2.setColor(reds[0]);
	        			}
        				g2.fillRect(x1, y1, size, size);
        			}
        		}else{
        			i = maze.get(currentRow - cellpadding + row, currentCol - cellpadding + col).getNodeType();
        		}
        		
        		

        		
        		imageIndex = i;
        		if (imageIndex < 0){
        			g2.setColor(Color.LIGHT_GRAY);//Empty cell
        			g2.fillRect(x1, y1, size, size);
        		}
        		else{
        			g2.drawImage(sprites[imageIndex].getNext(), x1, y1, null);
        		}
        		/**
        		//if(player != null){
        		
        		List<Node> playerPath = player.startTraversator();
                
                playerPath.remove(playerPath.size()-1);
                playerPath.remove(0);
        		
        		for(Node n: playerPath){
        			if (n.getNodeType() == -1 && n.getRow() == row && n.getCol() == col){
        				//System.out.println(n);
        				g2.setColor(Color.yellow);//Empty cell
            			g2.fillRect(x1, y1, size, size);
        			}
        			}
        		//}**/
        	}
        }
	}
	
	public void toggleZoom(){
		zoomOut = !zoomOut;		
	}

	public void actionPerformed(ActionEvent e) {	
		if (enemy_state < 0 || enemy_state == 5){
			enemy_state = 6;
		}else{
			enemy_state = 5;
		}
		this.repaint();
	}
	
	public void setSprites(Sprite[] sprites){
		this.sprites = sprites;
	}
}