package ie.gmit.sw.ai.FuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

//Engageable handles the fuzzylogic calculations
public class Engageable {
	
	//Takes parameters players weapon type, spider anger(depends on the spider), players current life level
	public static double engage(double weapon, double anger, double currentLife){
		 String fileName = "fcl/engage.fcl";
	        FIS fis = FIS.load(fileName,true);
	        FunctionBlock fb = fis.getFunctionBlock("Engage");
	        
	        fis.setVariable("weapon", weapon);
	        fis.setVariable("angerLevel", anger);
	        fis.setVariable("currentLife", currentLife);
	        fis.evaluate();
	        
	        //Returns a double lifeForce which is the new player health value
			return Math.round(fb.getVariable("lifeForce").getValue());
	}
}