package enums.rod;

import enums.fish.FishSize;

public enum RodType {
	Default,
	Leaf,
	Mechanical,
	Industrial,
	Katana,
	Falcon,
	Divine
	;
	
	
	public static RodRarity[] rarities = {
		RodRarity.Default,
		RodRarity.Common,
		RodRarity.Rare,
		RodRarity.Epic,
		RodRarity.Legendary,
		RodRarity.Legendary,
		RodRarity.Mythic
	};
	
	public RodRarity getRodRarity() {
		return rarities[this.ordinal()];
	}
	
    public static RodType fromString(String type) {
        for (RodType fs : RodType.values()) {
            if (fs.name().equalsIgnoreCase(type)) {
                return fs;
            }
        }
        return null;
    }

}
