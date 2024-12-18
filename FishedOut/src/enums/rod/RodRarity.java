package enums.rod;

import strategy.CommonRodStrategy;
import strategy.DefaultRodStrategy;
import strategy.EpicRodStrategy;
import strategy.FishingStrategy;
import strategy.LegendaryRodStrategy;
import strategy.MythicRodStrategy;
import strategy.RareRodStrategy;

public enum RodRarity {
	Default, Common, Rare, Epic, Legendary, Mythic;

	private static final int[] basePrice = { 
			0, // Default
			100, // Common
			200, // Rare
			400, // Epic
			1000, // Legendary
			2000 // Mythic
	};

	private static final double[][] probabilities = {
		    //Common, Rare, Epic, Legendary, Mythic
		    {0.80, 0.20, 0.00, 0.00, 0.00 }, // Default
		    {0.70, 0.20, 0.10, 0.00, 0.00 }, // Common
		    {0.50, 0.30, 0.15, 0.05, 0.00 }, // Rare
		    {0.40, 0.30, 0.20, 0.10, 0.00 }, // Epic
		    {0.30, 0.20, 0.20, 0.20, 0.10 }, // Legendary
		    {0.20, 0.20, 0.20, 0.20, 0.20 }  // Mythic
		};

	private static final String[] colors = { 
			"#ADD8E6", // Default
			"#87CEEB", // Common
			"#6495ED", // Rare
			"#1E90FF", // Epic
			"#eda024", // Legendary
			"#ff2b4f" // Mythic
	};
	
	
	private static final FishingStrategy[] strategies = {
			new DefaultRodStrategy(),
			new CommonRodStrategy(),
			new RareRodStrategy(),
			new EpicRodStrategy(),
			new LegendaryRodStrategy(),
			new MythicRodStrategy(),
	};

	public double[] getProbabilities() {
		return probabilities[this.ordinal()];
	}

	public int getBasePrice() {
		return basePrice[this.ordinal()];
	}

	public String getColor() {
		return colors[this.ordinal()];
	}
	
	public FishingStrategy getStrategy() {
		return strategies[this.ordinal()];
	}

	public static RodRarity fromString(String rarity) {
		for (RodRarity rr : RodRarity.values()) {
			if (rr.name().equalsIgnoreCase(rarity)) {
				return rr;
			}
		}
		throw new IllegalArgumentException("Invalid RodRarity: " + rarity);
	}

	static {
		if (basePrice.length != RodRarity.values().length || probabilities.length != RodRarity.values().length
				|| colors.length != RodRarity.values().length) {
			throw new IllegalStateException("Arrays length mismatch with RodRarity enum.");
		}
	}

	@Override
	public String toString() {
		return this.name() + " (Base Price: " + getBasePrice() + ", Color: " + getColor() + ")";
	}
}
