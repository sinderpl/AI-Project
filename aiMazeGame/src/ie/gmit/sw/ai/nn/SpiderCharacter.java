package ie.gmit.sw.ai.nn;

import ie.gmit.sw.ai.nn.activator.Activator;


public class SpiderCharacter{

    private NeuralNetwork nn = null;

    public SpiderCharacter(){

        nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 2, 1, 2);
        Trainator trainer = new BackpropagationTrainer(nn);
        trainer.train(data, expected, 0.2, 3000000);
    }

    /*
        1 = Health (1 = Healthy, 0.5 = Minor Injuries, 0 = Serious Injuries)
        2 = Has Sword (1 = Yes, 0 = No)
        3 = Has Bomb (1 = Yes, 0 = No)
        4 = Enemies Status (0 = One, 0.5 = Two, 1 = Three or More)
     */

    /*
     * 1 = Spider health  (1 = Full Health, 0.5 = Injured , 0 = Close to death)
     * 2 = EnemyWeapon	(0 = None, 0.3 = Sword , 0.6 = Bomb, 1 = Hydrogen Bomb)
     * 3 = Proximity (	)
     */
    private double[][] data =

    { //Health, Sword, Bomb, Enemies
    		
    		{ 1, 0 }, { 1, 0.3 }, { 1, 0.6 }, { 1, 1 }, // Full health, EnemyWeapon types added
    		{ 0.5, 0 }, { 0.5, 0.3 }, { 0.5, 0.6 }, { 0.5, 1 }, // Injured, EnemyWeapon types added
    		{ 0.5, 0 }, { 0.5, 0.3 }, { 0.5, 0.6 }, { 0.5, 1 } // Close to death, EnemyWeapon types added
    	
    		/**
	    	{ 1, 0, 0, 0 }, { 1, 0, 0, 0.5 }, { 1, 0, 0, 1 }, // full health, enemies covered
	        { 0.5, 0, 0, 0 }, { 0.5, 0, 0, 0.5 }, { 0.5, 0, 0, 1 }, // minior injuries, enemies covered
	        { 0, 0, 0, 0 }, { 0, 0, 0, 0.5 }, { 0, 0, 0, 1 }, // serious injuries, enemies covered
    		
    		
            // No Sword, No Bomb
            { 1, 0, 0, 0 }, { 1, 0, 0, 0.5 }, { 1, 0, 0, 1 }, // full health, enemies covered
            { 0.5, 0, 0, 0 }, { 0.5, 0, 0, 0.5 }, { 0.5, 0, 0, 1 }, // minior injuries, enemies covered
            { 0, 0, 0, 0 }, { 0, 0, 0, 0.5 }, { 0, 0, 0, 1 }, // serious injuries, enemies covered

            // Sword, No Bomb
            { 1, 1, 0, 0 }, { 1, 1, 0, 0.5 }, { 1, 1, 0, 1 }, // full health, enemies covered
            { 0.5, 1, 0, 0 }, { 0.5, 1, 0, 0.5 }, { 0.5, 1, 0, 1 }, // minior injuries, enemies covered
            { 0, 1, 0, 0 }, { 0, 1, 0, 0.5 }, { 0, 1, 0, 1 }, // serious injuries, enemies covered

            // No Sword, Bomb
            { 1, 0, 1, 0 }, { 1, 0, 1, 0.5 }, { 1, 0, 1, 1 }, // full health, enemies covered
            { 0.5, 0, 1, 0 }, { 0.5, 0, 1, 0.5 }, { 0.5, 0, 1, 1 }, // minior injuries, enemies covered
            { 0, 0, 1, 0 }, { 0, 0, 1, 0.5 }, { 0, 0, 1, 1 }, // serious injuries, enemies covered

            // Sword, Bomb
            { 1, 1, 1, 0 }, { 1, 1, 1, 0.5 }, { 1, 1, 1, 1 }, // full health, enemies covered
            { 0.5, 1, 1, 0 }, { 0.5, 1, 1, 0.5 }, { 0.5, 1, 1, 1 }, // minior injuries, enemies covered
            { 0, 1, 1, 0 }, { 0, 1, 1, 0.5 }, { 0, 1, 1, 1 } // serious injuries, enemies covered
    	**/
    
    };

    
    private double[][] expected =

    { // Attack, Run

            // No Sword, No Bomb
            { 1, 0, 0, 0}, { 1, 0, 0, 0 }, { 0, 0, 1, 0 }, // full health, enemies covered
            { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 }, // minior injuries, enemies covered
            { 0, 0, 1, 0 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 }, // serious injuries, enemies covered

            // Sword, No Bomb
            { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, // full health, enemies covered
            { 1, 0, 0, 0 }, { 0, 0, 1, 0 }, { 0, 1, 0, 0 }, // minior injuries, enemies covered
            { 0, 0, 1, 0 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 }, // serious injuries, enemies covered

            // No Sword, Bomb
            { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, // full health, enemies covered
            { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, // minior injuries, enemies covered
            { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 }, // serious injuries, enemies covered

            // Sword, Bomb
            { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, // full health, enemies covered
            { 1, 0, 0, 0 }, { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, // minior injuries, enemies covered
            { 1, 0, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 } // serious injuries, enemies covered
    };


    public int action(double health, double enemyWeapon, double proximity) throws Exception{

        double[] params = {health, enemyWeapon};

        double[] result = nn.process(params);

        for(double val : result){
            System.out.println(val);
        }

        int output = (Utils.getMaxIndex(result) + 1);
        
        //Check the output against predetermined actions
        switch(output){
            case 1:
                // Attack the player
                return 1;
            case 2:
                // Run Away from the player
                return 2;
            default:
                // Roam the maze
                return 3;
        } 

    } 

} 