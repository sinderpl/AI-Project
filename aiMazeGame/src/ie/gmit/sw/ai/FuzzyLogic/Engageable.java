package ie.gmit.sw.ai.FuzzyLogic;

public interface Engageable {
	
	public static double engage(double weapon, double angerLevel, double currentLife){
		Runner fuzzy = new Runner();
		double lifeForce = fuzzy.getLifeForce(weapon, angerLevel, currentLife);
		return lifeForce;
	}
}
