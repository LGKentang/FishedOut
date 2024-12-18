package factory;

import java.util.UUID;

import enums.rod.RodType;
import model.Rod;

public class RodFactory {

    public Rod createRod(String name) {
        return new Rod(RodType.fromString(name));
    }
    

    public Rod createDefaultRod() {
        return new Rod(RodType.fromString("Default"));
    }
}
