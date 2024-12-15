package enums.fish;

public enum FishRarity {
	Common, Rare, Epic, Legendary, Mythic;


	private static final int[] basePrice = { 30, 75, 125, 200, 400 };
    private static final String[] colors = {
			"#87CEEB", // Common
			"#6495ED", // Rare
			"#1E90FF", // Epic
			"#ffb005", // Legendary
			"#f72537" // Mythic
    };
    
    public String getColor() {
    	return colors[this.ordinal()];
    }
    
	public int getBasePrice() {
		return basePrice[this.ordinal()];
	}

	public static FishRarity fromString(String rarity) {
		for (FishRarity fr : FishRarity.values()) {
			if (fr.name().equalsIgnoreCase(rarity)) {
				return fr;
			}
		}
		return null;
	}

}
