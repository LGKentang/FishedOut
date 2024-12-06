package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory {

    private List<Fish> fishList;
    private List<Rod> rodList;
    public static Inventory inventory = null;

    private Inventory(List<Fish> fishList, List<Rod> rodList) {
        this.fishList = fishList;
        this.rodList = rodList;
    }

    public static Inventory getInventory(){
        if (inventory == null){
            inventory = new Inventory(new ArrayList<Fish>(), new ArrayList<Rod>());
        }
        return inventory;
    }

    public Fish getFishById(String fishId) {
        return fishList.stream()
                       .filter(fish -> fish.getId().equals(fishId))
                       .findFirst()
                       .orElse(null); 
    }

//    public Rod getRodById(String rodId) {
//        return rodList.stream()
//                      .filter(rod -> rod.getId().equals(rodId))
//                      .findFirst()
//                      .orElse(null); 
//    }

    public Fish removeFish(String fishId) {
        Optional<Fish> fishToRemove = fishList.stream()
                                              .filter(fish -> fish.getId().equals(fishId))
                                              .findFirst();
        if(fishToRemove.isPresent()) {
            fishList.remove(fishToRemove.get());
            return fishToRemove.get();
        }
        return null;
    }

    
    public void moveFishToAquarium(String fishID) {
        // Logic to move fish to the aquarium
    }

    public void sellFish(String fishID) {
        // Logic to sell a fish
    }
}