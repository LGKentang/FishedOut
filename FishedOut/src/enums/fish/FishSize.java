package enums.fish;

import java.util.Random;

public enum FishSize {
    XS,
    S,
    M,
    L,
    XL,
    XXL,
    Jumbo,
    MEGA,
    UNKNOWN;
	 private static final Random random = new Random();
    private static final String[] colors = {
        "#ADD8E6", // XS: Light Blue
        "#87CEEB", // S: Sky Blue
        "#6495ED", // M: Cornflower Blue
        "#1E90FF", // L: Dodger Blue
        "#4169E1", // XL: Royal Blue
        "#00008B", // XXL: Dark Blue
        "#800080", // Jumbo: Purple
        "#FF0000", // MEGA: Red
        "#000000"  // UNKNOWN: Black
    };

    private static final double[] multipliers = {
        1,    // XS
        1.2,  // S
        1.5,  // M
        2,    // L
        2.75, // XL
        3.25, // XXL
        5,    // Jumbo
        10,   // MEGA
        50    // UNKNOWN
    };

    public String getColor() {
        return colors[this.ordinal()];
    }

    public double getMultiplier() {
        return multipliers[this.ordinal()];
    }

    public static FishSize fromString(String size) {
        for (FishSize fs : FishSize.values()) {
            if (fs.name().equalsIgnoreCase(size)) {
                return fs;
            }
        }
        return null;
    }


    static {
        if (colors.length != FishSize.values().length || multipliers.length != FishSize.values().length) {
            throw new IllegalStateException("Length of colors or multipliers does not match FishSize enum values.");
        }
    }

    @Override
    public String toString() {
        return this.name() + " (Color: " + getColor() + ", Multiplier: " + getMultiplier() + ")";
    }
    public static FishSize getRandomSize() {
        int randomIndex = random.nextInt(FishSize.values().length);
        return FishSize.values()[randomIndex];
    }
}