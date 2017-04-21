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
 The output is a new health variable for player. Overall there are 27 rules in our codeblock:
 ```
 RULE 4 : IF weapon IS sword AND angerLevel IS annoyed AND currentLife IS damaged THEN lifeForce IS low;
 ```

