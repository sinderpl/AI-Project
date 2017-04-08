package ie.gmit.sw.ai;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class MenuView extends JFrame{
	
	private String mazeType = "HuntAndKill";
	
	//Listener for the buttons
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				switch (e.getActionCommand()){
				case "start":
					
						//Create a new frame and start the game
						startGame();
						//Set the menu frame to invisble in the background
						setVisibility();
					break;
				case "mazeType":
					mazeType = ((JRadioButton) e.getSource()).getText();
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	//Button Action listener
	public ButtonListener listener = new ButtonListener();
	
	public MenuView(){
		
		
				//JPanels
		//JPanel for the maze contents
		JPanel mazePanel =  new JPanel();
		mazePanel.setLayout(new GridLayout(7, 1));
		
				//ButtonGroups
		//JRadio Button Group for the maze options
		ButtonGroup mazeButtonGroup = new ButtonGroup();
	
				//buttons
		//Create the radio buttons for maze creation and add to group
		//List<String> mazeArray = Arrays.asList("HuntAndKill", "asd");
		ArrayList<String> mazeArray = new ArrayList<String>(Arrays.asList("HuntAndKill", "BinaryTree", "RandomDepthFirst",
				"RecursiveBacktracker", "RecursiveDivision" , "RandomizedPrim", "RandomizedKruskal"));
		
		ArrayList<JRadioButton> mazeRadioButtons = new ArrayList<JRadioButton>();
		
		for (String str : mazeArray){
			JRadioButton button = new JRadioButton(str);
			button.addActionListener(listener);
			button.setActionCommand("mazeType");
			mazeRadioButtons.add(button);
			mazePanel.add(button);
		}
		
		
		
		//Start button
		JButton startButton= new JButton("Start");
		
		
		
				//Configure the panels
		//mazePanel.
		
		
		//Add panels to frame
		this.add(mazePanel);
		
		
			//Start button configuration
		startButton.addActionListener(listener);
		startButton.setActionCommand("start");
		
		//Add the start button
		this.add(startButton);
		
		//Set the layout
		this.setLayout( new GridLayout(2, 1));
		
		//Set the title of the frame
		this.setTitle("Maze game");
		
		
		//Set the size of the menu
		this.setSize(400, 400);
		
		//Turn off application on exit
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set frame to visibile
		setVisibility();
	}	

	//Start the game 
	public void startGame() throws Exception{
		System.out.println(mazeType);
		new GameRunner(mazeType);
	}
	
	
	//This sets visibility on/off depending
	//on current state of the frame
	//This allows the action listener to turn frame off
	//When game starts
	public void setVisibility(){
		if(this.isVisible() == false)
			this.setVisible(true);
		else
			this.setVisible(false);
	}
	
	public static void main(String[] args) throws Exception{
		MenuView view = new MenuView();
	}
	
	
}	
