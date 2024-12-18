package model;

import enums.fish.FishRarity;
import enums.fish.FishSize;
import enums.fish.FishType;

public class Fish {
	private String id;
	private FishType type;
	private FishSize size;
	private double price;

	public Fish(String id, String name, String size) {
		this.id = id;
		this.type = FishType.fromString(name);
		this.size = FishSize.fromString(size);
		this.price = calculateFishPrice(this.type, this.size);
	}

	public Fish(String id, FishType type, FishSize size) {
		super();
		this.id = id;
		this.type = type;
		this.size = size;
		this.price = calculateFishPrice(this.type, this.size);
	}

	public double calculateFishPrice(FishType type, FishSize size) {
		double basePrice = type.getRarity().getBasePrice();
		double sizeMultiplier = size.getMultiplier();

		return basePrice * sizeMultiplier;
	}

	public FishType getType() {
		return type;
	}

	public void setType(FishType type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public FishSize getSize() {
		return size;
	}

	public void setSize(FishSize size) {
		this.size = size;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public String jsonify() {
	    return "{"
	            + "\"id\": \"" + id + "\", "
	            + "\"name\": \"" + type.name() + "\", "
	            + "\"rarity\": \"" + type.getRarity().name() + "\", "
	            + "\"size\": \"" + size.name() + "\", "
	            + "\"color\": \"" + type.getRarity().getColor() + "\", "
	            + "\"price\": " + price
	            + "}";
	}

}
