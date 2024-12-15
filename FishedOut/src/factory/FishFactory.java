package factory;

import java.util.Random;
import java.util.UUID;

import enums.fish.FishRarity;
import enums.fish.FishSize;
import enums.fish.FishType;
import model.Fish;
import model.Player;
import java.util.Arrays;
public class FishFactory {

	private static final Random RANDOM = new Random();

	public Fish catchRandomFish() {
		System.out.println(Player.getPlayer().getFishingRod());
		
		if (Player.getPlayer().getFishingRod() == null)
			return null;
		
		
		double[] rodProbabilities = Player.getPlayer().getFishingRod().getRodType().getRodRarity().getProbabilities();
		System.out.println(rodProbabilities);
		Random random = new Random();

		double randValue = random.nextDouble();
		FishRarity rarity = null;

		double cumulativeProbability = 0.0;
		for (int i = 0; i < rodProbabilities.length; i++) {
			cumulativeProbability += rodProbabilities[i];
			if (randValue <= cumulativeProbability) {
				rarity = FishRarity.values()[i];
				break;
			}
		}

		if (rarity == null)
			return null;
		
		FishType randomFish = FishType.getRandomFishByRarity(rarity);
		System.out.println(randomFish);
		Fish fish = new Fish(UUID.randomUUID().toString(), randomFish, FishSize.getRandomSize());
		
		System.out.println(fish.jsonify());
		return fish;
	}

}
