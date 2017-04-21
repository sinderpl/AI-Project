package ie.gmit.sw.ai.nn;

import ie.gmit.sw.ai.nn.activator.Activator;


public class PlayerCharacter {

    private NeuralNetwork nn = null;

    public PlayerCharacter(){

        nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 4, 3, 4);
        Trainator trainer = new BackpropagationTrainer(nn);
        trainer.train(data, expected, 0.2, 3000000);
    }

    /*
        1 = Health (1 = Healthy, 0.5 = Minor Injuries, 0 = Serious Injuries)
        2 = Has Sword (1 = Yes, 0 = No)
        3 = Has Bomb (1 = Yes, 0 = No)
        4 = Enemies Status (0 = One, 0.5 = Two, 1 = Three or More)
     */

    private double[][] data =

    { //Health, Sword, Bomb, Enemies

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
    };

    private double[][] expected =

    { // Attack, Panic, Heal,  Run

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


    public int action(double health, double sword, double bomb, double enemies) throws Exception{

        double[] params = {health, sword, bomb, enemies};

        double[] result = nn.process(params);

        for(double val : result){
            System.out.println(val);
        }

        int output = (Utils.getMaxIndex(result) + 1);

        switch(output){
            case 1:
                // attack
            	System.out.println("attacking");
                return 1;
            case 2:
                // panic
            	System.out.println("panic");
                return 2;
            case 3:
                // heal
            	System.out.println("heal");
                return  3;
            default:
                // run away
            	System.out.println("run away");
                return 4;
        } // switch

    } // action()

} // class