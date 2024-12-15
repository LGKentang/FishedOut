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
}


