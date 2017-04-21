package ie.gmit.sw.ai.nn;

import ie.gmit.sw.ai.nn.activator.Activator;


public class SpiderCharacter{

    private NeuralNetwork nn = null;

    public SpiderCharacter(){

        nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 4, 1, 2);
        Trainator trainer = new BackpropagationTrainer(nn);
        trainer.train(data, expected, 0.2, 3000000);
    }

    /*
     * 1 = Spider health  (8 = Full Health, 4 = Injured , 2 = Close to death)
     * 2 = EnemyWeapon	(3 = Hydrogen Bomb ,2 = Bomb, 1 = Sword , 0 = None)
     * 3 = Proximity ( 8 = Far Away, 4 = Nearby,  2 = Interacting)
     * 4 = EnemyHealth (8  = Full Health, 4 = Half Health 2 = Close to death)
     */
    private double[][] data =

    { 	
    		//Check all health
    		
    		//For No weapon
    		{ 8, 0, 2, 8 } , {8, 0, 2, 4 } , {8, 0, 2, 2} ,  //Full Health, no enemy weapons, proximity interacting, Enemy health all
    		{ 4, 0, 2, 8 } , {4, 0, 2, 4 } , {4, 0, 2, 2} ,  // Injured,  no enemy weapons, proximity interacting, Enemy health all
    		{ 2, 0, 2, 8 } , {2, 0, 2, 4 } , {2, 0, 2, 2} , // Close to death,  no enemy weapons, proximity interacting, Enemy health all
    		
    		{ 8, 0, 4, 2 } , {8, 0, 4, 4 } , {8, 0, 4, 8} ,  //Full Health, no enemy weapons, proximity all, Enemy health all
    		{ 4, 0, 4, 2 } , {4, 0, 4, 4 } , {4, 0, 4, 8} ,  // Injured,  no enemy weapons, proximity all, Enemy health all
    		{ 2, 0, 4, 2 } , {2, 0, 4, 4 } , {2, 0, 4, 8} , // Close to death,  no enemy weapons, proximity all, Enemy health all
    		
    		{ 8, 0, 8, 2 } , {8, 0, 8, 4 } , {8, 0, 8, 8} ,  //Full Health, no enemy weapons, Far Away, Enemy health all
    		{ 4, 0, 8, 2 } , {4, 0, 8, 4 } , {4, 0, 8, 8} ,  // Injured,  no enemy weapons, Far Away, Enemy health all
    		{ 2, 0, 8, 2 } , {2, 0, 8, 4 } , {2, 0, 8, 8} , // Close to death,  no enemy weapons, Far Away, Enemy health all
    		
    		
    		//for Sword
    		{ 8, 1, 2, 8 } , {8, 1, 2, 4 } , {8, 1, 2, 2} ,  //Full Health, Sword, proximity interacting, Enemy health all
    		{ 4, 1, 2, 8 } , {4, 1, 2, 4 } , {4, 1, 2, 2} ,  // Injured,  Sword, proximity interacting, Enemy health all
    		{ 2, 1, 2, 8 } , {2, 1, 2, 4 } , {2, 1, 2, 2} , // Close to death,  Sword, proximity interacting, Enemy health all
    		
    		{ 8, 1, 4, 2 } , {8, 1, 4, 4 } , {8, 1, 4, 8} ,  //Full Health, Sword, proximity all, Enemy health all
    		{ 4, 1, 4, 2 } , {4, 1, 4, 4 } , {4, 1, 4, 8} ,  // Injured,  Sword, proximity all, Enemy health all
    		{ 2, 1, 4, 2 } , {2, 1, 4, 4 } , {2, 1, 4, 8} , // Close to death,  Sword, proximity all, Enemy health all
    		
    		{ 8, 1, 8, 2 } , {8, 1, 8, 4 } , {8, 1, 8, 8} ,  //Full Health, Sword, Far Away, Enemy health all
    		{ 4, 1, 8, 2 } , {4, 1, 8, 4 } , {4, 1, 8, 8} ,  // Injured,  Sword, Far Away, Enemy health all
    		{ 2, 1, 8, 2 } , {2, 1, 8, 4 } , {2, 1, 8, 8} , // Close to death,  Sword, Far Away, Enemy health all
    		
    		
    		//for Bomb
    		{ 8, 2, 2, 8 } , {8, 2, 2, 4 } , {8, 2, 2, 2} ,  //Full Health, Bomb, proximity interacting, Enemy health all
    		{ 4, 2, 2, 8 } , {4, 2, 2, 4 } , {4, 2, 2, 2} ,  // Injured,  Bomb, proximity interacting, Enemy health all
    		{ 2, 2, 2, 8 } , {2, 2, 2, 4 } , {2, 2, 2, 2} , // Close to death,  Bomb, proximity interacting, Enemy health all
    		
    		{ 8, 2, 4, 2 } , {8, 2, 4, 4 } , {8, 2, 4, 8} ,  //Full Health, Bomb, proximity all, Enemy health all
    		{ 4, 2, 4, 2 } , {4, 2, 4, 4 } , {4, 2, 4, 8} ,  // Injured,  Bomb, proximity all, Enemy health all
    		{ 2, 2, 4, 2 } , {2, 2, 4, 4 } , {2, 2, 4, 8} , // Close to death,  Bomb, proximity all, Enemy health all
    		
    		{ 8, 2, 8, 2 } , {8, 2, 8, 4 } , {8, 2, 8, 8} ,  //Full Health, Bomb, Far Away, Enemy health all
    		{ 4, 2, 8, 2 } , {4, 2, 8, 4 } , {4, 2, 8, 8} ,  // Injured,  Bomb, Far Away, Enemy health all
    		{ 2, 2, 8, 2 } , {2, 2, 8, 4 } , {2, 2, 8, 8} , // Close to death,  Bomb, Far Away, Enemy health all
    		
    		//for H Bomb
    		{ 8, 3, 2, 8 } , {8, 1, 2, 4 } , {8, 1, 2, 2} ,  //Full Health, H Bomb, proximity interacting, Enemy health all
    		{ 4, 3, 2, 8 } , {4, 1, 2, 4 } , {4, 1, 2, 2} ,  // Injured,  H Bomb, proximity interacting, Enemy health all
    		{ 2, 3, 2, 8 } , {2, 1, 2, 4 } , {2, 1, 2, 2} , // Close to death,  H Bomb, proximity interacting, Enemy health all
    		
    		{ 8, 3, 4, 2 } , {8, 3, 4, 4 } , {8, 3, 4, 8} ,  //Full Health, H Bomb, proximity all, Enemy health all
    		{ 4, 3, 4, 2 } , {4, 3, 4, 4 } , {4, 3, 4, 8} ,  // Injured,  H Bomb, proximity all, Enemy health all
    		{ 2, 3, 4, 2 } , {2, 3, 4, 4 } , {2, 3, 4, 8} , // Close to death,  H Bomb, proximity all, Enemy health all
    		
    		{ 8, 3, 8, 2 } , {8, 3, 8, 4 } , {8, 3, 8, 8} ,  //Full Health, H Bomb, Far Away, Enemy health all
    		{ 4, 3, 8, 2 } , {4, 3, 8, 4 } , {4, 3, 8, 8} ,  // Injured,  H Bomb, Far Away, Enemy health all
    		{ 2, 3, 8, 2 } , {2, 3, 8, 4 } , {2, 3, 8, 8} , // Close to death,  H Bomb, Far Away, Enemy health all
    		
    	
    
    };

    
    private double[][] expected =

    {
    	//For No weapon
    	//these can all be the same, the spider will attack no matter what as long as you don't have a weapon yet.
		{ 1, 0} , {1, 0 } , {1, 0} ,  //Full Health, no enemy weapons, proximity interacting, Enemy health all
		{ 1, 0} , {1, 0} , {1, 0} ,  // Injured,  no enemy weapons, proximity interacting, Enemy health all
		{ 1, 0 } , {1, 0 } , {1, 0} , // Close to death,  no enemy weapons, proximity interacting, Enemy health all		
		{ 1, 0 } , {1, 0 } , {1, 0} ,  //Full Health, no enemy weapons, proximity interacting, Enemy health all
		{ 1, 0} , {1, 0} , {1, 0} ,  // Injured,  no enemy weapons, proximity interacting, Enemy health all
		{ 1, 0 } , {1, 0 } , {1, 0} , // Close to death,  no enemy weapons, proximity interacting, Enemy health all
		{ 1, 0 } , {1, 0 } , {1, 0} ,  //Full Health, no enemy weapons, proximity interacting, Enemy health all
		{ 1, 0} , {1, 0} , {1, 0} ,  // Injured,  no enemy weapons, proximity interacting, Enemy health all
		{ 1, 0 } , {1, 0 } , {1, 0} , // Close to death,  no enemy weapons, proximity interacting, Enemy health all
	
	
		//For Sword
		//The spider will attack as long as he is not close to death
		{ 1, 0 } , {1, 0 } , {1, 0} ,  //Full Health, Sword, proximity interacting, Enemy health all
		{ 1, 0} , {1, 0} , {1, 0} ,  // Injured,  Sword, proximity interacting, Enemy health all
		{ 0, 1 } , {0, 1 } , {0, 1} , // Close to death,  Sword, proximity interacting, Enemy health all
		{ 1, 0 } , {1, 0 } , {8, 1} ,  //Full Health, Sword, proximity interacting, Enemy health all
		{ 1, 0} , {1, 0} , {1, 0} ,  // Injured,  Sword, proximity interacting, Enemy health all
		{ 0, 1 } , {0, 1 } , {0, 1} , // Close to death,  Sword, proximity interacting, Enemy health all
		{ 1, 0 } , {1, 0 } , {1, 0} ,  //Full Health, Sword, proximity interacting, Enemy health all
		{ 1, 0} , {1, 0} , {1, 0} ,  // Injured,  Sword, proximity interacting, Enemy health all
		{ 0, 1 } , {0, 1 } , {2, 1} , // Close to death,  Sword, proximity interacting, Enemy health all
		
		
		
		//For bomb
		// The spider will be more cautious
		{ 1, 0 } , {1, 0 } , {1, 0} ,  //Full Health, no enemy weapons, proximity interacting, Enemy health all
		{ 0, 0} , {1, 0} , {1, 0} ,  // Injured,  no enemy weapons, proximity interacting, Enemy health all
		{ 0, 1 } , {0, 1 } , {0, 1} , // Close to death,  no enemy weapons, proximity interacting, Enemy health all
		{ 1, 0 } , {1, 0 } , {1, 0} ,  //Full Health, no enemy weapons, proximity interacting, Enemy health all
		{ 0, 0} , {1, 0} , {1, 0} ,  // Injured,  no enemy weapons, proximity interacting, Enemy health all
		{ 0, 1 } , {0, 1 } , {0, 1} , // Close to death,  no enemy weapons, proximity interacting, Enemy health all
		{ 1, 0 } , {1, 0 } , {1, 0} ,  //Full Health, no enemy weapons, proximity interacting, Enemy health all
		{ 0, 0} , {1, 0} , {1, 0} ,  // Injured,  no enemy weapons, proximity interacting, Enemy health all
		{ 0, 1 } , {0, 1 } , {0, 1} , // Close to death,  no enemy weapons, proximity interacting, Enemy health all
		
		
		
		//For H bomb
		{ 0, 1 } , {0, 1 } , {1, 0} ,  //Full Health, no enemy weapons, proximity interacting, Enemy health all
		{ 0, 1} , {0, 1} , {0, 1} ,  // Injured,  no enemy weapons, proximity interacting, Enemy health all
		{ 0, 1 } , {0, 1 } , {0, 1} , // Close to death,  no enemy weapons, proximity interacting, Enemy health all
		{ 0, 1 } , {0, 1 } , {1, 0} ,  //Full Health, no enemy weapons, proximity interacting, Enemy health all
		{ 0, 1} , {0, 1} , {0, 1} ,  // Injured,  no enemy weapons, proximity interacting, Enemy health all
		{ 0, 1 } , {0, 1 } , {0, 1} , // Close to death,  no enemy weapons, proximity interacting, Enemy health all
		{ 0, 1 } , {0, 1 } , {1, 0} ,  //Full Health, no enemy weapons, proximity interacting, Enemy health all
		{ 0, 1} , {0, 1} , {0, 1} ,  // Injured,  no enemy weapons, proximity interacting, Enemy health all
		{ 0, 1 } , {0, 1 } , {0, 1} , // Close to death,  no enemy weapons, proximity interacting, Enemy health all
    			
		
		
    };


    public int action(double health, double enemyWeapon, double proximity, double enemyHealth) throws Exception{

        double[] params = {health, enemyWeapon, proximity, enemyHealth};

        double[] result = nn.process(params);

        for(double val : result){
            System.out.println(val);
        }

        int output = (Utils.getMaxIndex(result) + 1);
        //System.out.println("output: " + output);
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
                return 2;
        } 

    } 

} 