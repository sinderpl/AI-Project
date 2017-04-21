# AI-Project
Example of AI in a game utilizing Fuzzy Logic and Neural Networks

Authors:
===========
* Alan Niemiec - G00313177
* Christopher Weir - G00309429

Github Link : https://github.com/sinderpl/AI-Project/

Overview:
============
This project is a simple introduction into AI development in Java. We utilise many different techniques acquired throught the year to create a game in which the enemy is controlled with sophisticated AI systems such as: Neural Networks, Fuzzy Logic, Maze Traversal Algorithms.

Running Instructions:
===========
1. Download the JAR from this repo.
2. Run the JAR with the following code:
```
java –cp ./game.jar ie.gmit.sw.ai.GameRunner
```

Game Instructions:
==============
* The player (yellow node) has to reach the exit (grey node) door to win.
* The player should avoid fighting with spiders (red nodes) unless well armed (sword, bomb, Hbomb). 
* The colour codings are for map overview (KeyPress z).


Marking scheme overview: 
==============
We have managed to implement a lot of the technologies specified in the Marking Scheme.
1. Fuzzy Logic:
 * Fuzzy logic has been implemented in multiple files. 
 The main Rule Block can be found in the **engage.fcl** file. 
 It features the following input variables
 ```
        weapon : REAL;  -> The weapon type of the player
        angerLevel : REAL; -> The anger/strength level of the spider
        currentLife : REAL; -> The current life of the player
 ```
 * The output is a new health variable for player. Overall there are 27 rules in our codeblock:
 ```
 RULE 4 : IF weapon IS sword AND angerLevel IS annoyed AND currentLife IS damaged THEN lifeForce IS low;
 ```
 * The spiders (**FuzzySprite.java** **NeuralSprite.java** ) can call on Fuzzy Logic using the following:
  ```
 Engageable e = new Engageable();
		double newHealth = e.engage(player.getWeapon(), anger, player.getHealth());
 ```
 
 2. Neural Network
  * We have managed to implement a Neural Network based on the in class example. We have created a very extensive data set for the spider to help the decision making (**NeuralSprite.java**).
  It is called on using the following four parameters:
  ```
   int action = enn.action(spiderHealth, enemyWeapon, proximity, playerHealth);
  ```
  
  There is a lot of input data to process for the spider (**SpiderCharacter.java** ):
  
  
   ```
    /*
     * 1 = Spider health  (8 = Full Health, 4 = Injured , 2 = Close to death)
     * 2 = EnemyWeapon	(3 = Hydrogen Bomb ,2 = Bomb, 1 = Sword , 0 = None)
     * 3 = Proximity ( 8 = Far Away, 4 = Nearby,  2 = Interacting)
     * 4 = EnemyHealth (8  = Full Health, 4 = Half Health 2 = Close to death)
     */
     
  { 8, 0, 2, 8 } , {8, 0, 2, 4 } , {8, 0, 2, 2} ,  //Full Health, no enemy weapons, proximity interacting, Enemy health all
  { 4, 0, 2, 8 } , {4, 0, 2, 4 } , {4, 0, 2, 2} ,  // Injured,  no enemy weapons, proximity interacting, Enemy health all
  { 2, 0, 2, 8 } , {2, 0, 2, 4 } , {2, 0, 2, 2} , // Close to death,  no enemy weapons, proximity interacting, Enemy health all
  ```
  The spider only has two choices implemented at the moment, it can either run away or engage the player in a fight depending on these variables.
   
   3. We have implemented both Uninformed and Heuristic searching algorithms in the spider's random movement techniques.(**FuzzySprite.java** **NeuralSprite.java**). Some of these are highly innacurate but the AStarTraversator works perfectly for both roaming and searching for the player.
   ```
   traversator = new AStarTraversator(player);
   ```
   We have started work on creating a help path for the player that would highlight the shortest route to the exit but it made the game extremely slow and we did not have time to fix it.
   
 4. The spiders are fully threaded using ExecutorService and run indepently of each other using the execute method in Executor service to start the spiders threaded run methods. (**Maze.java** **FuzzySpider.java** **NeuralSpider.java**)
   ```
   @Override
	public void run() {
		while(true){
			try {
				//Different sleep time per spider type
				Thread.sleep(500 * feature/2);
				//Find the path to take
				if(feature != 9){
					traverse(node.getRow(), node.getCol(), traversator);
				}
				// Move around the maze if within range
				if(node.getHeuristic(player) <= 1){
					engage();
				}
				else if(canMove && node.getHeuristic(player) < 10){
					roam();     
				} else {    
					randomMove();       
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}
     ```
 
