package model;

import enums.rod.RodType;
import strategy.FishingStrategy;

public class Rod {
	private RodType rodType;

	public Rod(RodType rodType) {
		super();
		this.rodType = rodType;
	}
	
	public RodType getRodType() {
		return rodType;
	}

	public void setRodType(RodType rodType) {
		this.rodType = rodType;
	}
	 
	public String jsonify() {
	    return "{"
	            + "\"name\": \"" + rodType.name() + "\", "
	            + "\"rarity\": \"" + rodType.getRodRarity().name() + "\", "
	            + "\"color\": \"" + rodType.getRodRarity().getColor() + "\", "
	            + "\"price\": " + rodType.getRodRarity().getBasePrice() + ", "  // Removed the extra quote here
	            + "\"smoothMotion\": " + rodType.getRodRarity().getStrategy().getSmoothMotion() + ", " // Removed the extra quote here
	            + "\"hookSize\": " + rodType.getRodRarity().getStrategy().getHookSize() + ", "  // Removed the extra quote here
	            + "\"hookPower\": " + rodType.getRodRarity().getStrategy().getHookPower()  // Removed the extra quote here
	            + "}";
	}

}
