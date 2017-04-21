package ie.gmit.sw.ai.FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class Engageable {
	
	public static double engage(double weapon, double spiderhealth, double currentLife){
		 String fileName = "engage.fcl";
	        FIS fis = FIS.load(fileName,true);
	        FunctionBlock fb = fis.getFunctionBlock("Engage");
	        
	        fis.setVariable("weapon", weapon);
	        fis.setVariable("angerLevel", spiderhealth);
	        fis.setVariable("currentLife", currentLife);
	        fis.evaluate();

	        System.out.println("Player Health: " + fb.getVariable("lifeForce").getValue() +""); //Output end result
			return Math.round(fb.getVariable("lifeForce").getValue());
	}
}
