package enums.fish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum FishType {
    Axolotl, Bass, Circlefish, Clownfish, Cod, FlyingFish, Goldfish, Octopus, Pufferfish, Rainbow, Shell, Snail,
    Starfish, Trash;


    public static FishRarity[] rarities = { 
            FishRarity.Mythic, 
            FishRarity.Common, 
            FishRarity.Rare, 
            FishRarity.Rare,
            FishRarity.Common, 
            FishRarity.Epic, 
            FishRarity.Rare, 
            FishRarity.Epic, 
            FishRarity.Rare, 
            FishRarity.Mythic,
            FishRarity.Legendary, 
            FishRarity.Common, 
            FishRarity.Legendary, 
            FishRarity.Common 
    };

    public final FishRarity getRarity() {
        return rarities[this.ordinal()];
    }

    public static FishType getRandomFishByRarity(FishRarity rarity) {
        List<FishType> matchingFish = new ArrayList<>();

        for (FishType fish : FishType.values()) {
            if (fish.getRarity() == rarity) {
                matchingFish.add(fish);
            }
        }

        if (matchingFish.isEmpty()) {
            return null;
        }

        Random random = new Random();
        return matchingFish.get(random.nextInt(matchingFish.size()));
    }

    public static FishType fromString(String type) {
        for (FishType fish : FishType.values()) {
            if (fish.name().equalsIgnoreCase(type)) {
                return fish;
            }
        }
        return null;
    }
}
