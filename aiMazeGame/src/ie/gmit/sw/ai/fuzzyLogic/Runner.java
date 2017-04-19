package ie.gmit.sw.ai.fuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Runner {
	public static void main(String[] args) throws Exception {
        String fileName = "fcl/engage.fcl";
        FIS fis = FIS.load(fileName,true);
        FunctionBlock fb = fis.getFunctionBlock("Engage");
        JFuzzyChart.get().chart(fb);
        
        fis.setVariable("weapon", 9);
        fis.setVariable("angerLevel", 6);
        fis.setVariable("currentLife", 5);
        fis.evaluate();
        
        Variable lifeForce = fb.getVariable("lifeForce");
        JFuzzyChart.get().chart(lifeForce, true);

        // Print ruleSet
        System.out.println("LifeForce: " + fb.getVariable("lifeForce").getValue() +""); //Output end result
        JFuzzyChart.get().chart(lifeForce.getDefuzzifier(), "Result Life Force (%)", true);
	}

}