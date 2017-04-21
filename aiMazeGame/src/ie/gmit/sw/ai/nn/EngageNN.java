package ie.gmit.sw.ai.nn;

import ie.gmit.sw.ai.nn.activator.*;
public class EngageNN {
	
	 /*
    1 = Health (2 = Healthy, 1 = Minor Injuries, 0 = Serious Injuries)
    2 = Has Sword (1 = Yes, 0 = No)
    3 = Has Gun (1 = Yes, 0 = No)
    4 = Number of Enemies
 */

private double[][] data = { //Health, Sword, Gun, Enemies
	{ 2, 0, 0, 0 }, { 2, 0, 0, 1 }, { 2, 0, 1, 1 }, { 2, 0, 1, 2 }, { 2, 1, 0, 2 },
	{ 2, 1, 0, 1 }, { 1, 0, 0, 0 }, { 1, 0, 0, 1 }, { 1, 0, 1, 1 }, { 1, 0, 1, 2 }, 
	{ 1, 1, 0, 2 }, { 1, 1, 0, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 1 }, 
	{ 0, 0, 1, 2 }, { 0, 1, 0, 2 }, { 0, 1, 0, 1 } };

private double[][] expected = { //Panic, Attack, Hide, Run
        { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0 }, 
        { 0.0, 0.0, 0.0, 1.0 }, { 1.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, 
        { 1.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, 
        { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 1.0, 0.0, 0.0 }, 
        { 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };

public void panic(){
    System.out.println("Panic");
}

public void attack(){
    System.out.println("Attack");
}

public void hide(){
    System.out.println("Hide");
}

public void runAway(){
    System.out.println("Run Away");
}

public int action(double health, double sword, double bomb, double enemies) throws Exception{

    double[] params = {health, sword, bomb, enemies};
    int action = 0;
    
    NeuralNetwork nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 4, 3, 4);
    Trainator trainer = new BackpropagationTrainer(nn);
    trainer.train(data, expected, 0.01, 100000);
    
    double[] result = nn.process(params);

    for(double val : result){
        System.out.println(val);
    }

    System.out.println("==>" + (Utils.getMaxIndex(result) + 1));

    int output = (Utils.getMaxIndex(result) + 1);

    switch(output){
        case 1:
            panic();
            System.out.println("panic");
            action = 1;
            break;
        case 2:
            attack();
            System.out.println("attack");
            action = 2;
            break;
        case 3:
            hide();
            System.out.println("hide");
            action = 3;
            break;
        default:
            runAway();
            System.out.println("runAway");
            action = 4;
    }
    System.out.println("action: " + action);
    return action;
}
	
//	public static void main(String[] args) throws Exception{
//	    double health = 2;
//	    double sword = 1;
//	    double gun = 0;
//	    double enemies = 2;
//	    double[] v = {1, 2, 3, enemies};
//	
//	    double[] result = null;
//	
//	    result = Utils.normalize(v, 0.0, 2.0);
//	
//	    System.out.println(result[0]);
//	    new EngageNN().action(health, sword, gun, result[0]);
//	}
}