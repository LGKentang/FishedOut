package enums.fish;

public enum FishRarity {
	Common, Rare, Epic, Legendary, Mythic;


	private static final int[] basePrice = { 30, 75, 125, 200, 400 };

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
