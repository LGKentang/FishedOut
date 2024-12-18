package model;

import factory.FishFactory;
import factory.RodFactory;

public class Player {

    private String id;
    private String name;
    private Rod fishingRod;
    private double money;
    
    private static volatile Player player = null;

    public static Player getPlayer() {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    RodFactory rf = new RodFactory();
                    player = new Player("1", "Darren", 100, rf.createDefaultRod());
                }
            }
        }
        return player;
    }

    public Rod getFishingRod() {
        return fishingRod;
    }

    public void setFishingRod(Rod fishingRod) {
        this.fishingRod = fishingRod;
    }

    private Player(String id, String name, double money, Rod fishingRod) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.fishingRod = fishingRod;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    
    public void incrementMoney(double money) {
        synchronized (this) {
            this.money += money;
        }
    }
    
    public void decrementMoney(double money) {
        synchronized (this) {
            this.money -= money;
        }
    }

    public Fish catchFish() {
        FishFactory fishFactory = new FishFactory();
        return fishFactory.catchRandomFish();
    }

    public String jsonify() {
        return "{"
                + "\"id\": \"" + id + "\", "
                + "\"name\": \"" + name + "\", "
                + "\"money\": " + money + ", "
                + "\"rod\": " + (fishingRod != null ? fishingRod.jsonify() : "null")
                + "}";
    }
}
