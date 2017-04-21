package ie.gmit.sw.ai.FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Runner {
	public double getLifeForce(double weapon, double angerLevel, double currentLife){
        String fileName = "engage.fcl";
        FIS fis = FIS.load(fileName,true);
        FunctionBlock fb = fis.getFunctionBlock("Engage");
        
        fis.setVariable("weapon", weapon);
        fis.setVariable("angerLevel", angerLevel);
        fis.setVariable("currentLife", currentLife);
        fis.evaluate();
        
        Variable lifeForce = fb.getVariable("lifeForce");

        // Print ruleSet
        System.out.println("LifeForce: " + fb.getVariable("lifeForce").getValue() +""); //Output end result
		return fb.getVariable("lifeForce").getValue();
	}

}