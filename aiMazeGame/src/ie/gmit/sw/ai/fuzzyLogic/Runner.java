package ie.gmit.sw.ai.fuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Runner {
	public double getLifeForce(double weapon, double angerLevel, double currentLife){
        String fileName = "src/fcl/engage.fcl";
        FIS fis = FIS.load(fileName,true);
        FunctionBlock fb = fis.getFunctionBlock("Engage");
        JFuzzyChart.get().chart(fb);
        
        fis.setVariable("weapon", weapon);
        fis.setVariable("angerLevel", angerLevel);
        fis.setVariable("currentLife", currentLife);
        fis.evaluate();
        
        Variable lifeForce = fb.getVariable("lifeForce");
        JFuzzyChart.get().chart(lifeForce, true);

        // Print ruleSet
        System.out.println("LifeForce: " + fb.getVariable("lifeForce").getValue() +""); //Output end result
        JFuzzyChart.get().chart(lifeForce.getDefuzzifier(), "Result Life Force (%)", true);
		return fb.getVariable("lifeForce").getValue();
	}

}