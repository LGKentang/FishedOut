package strategy;

public class CommonRodStrategy implements FishingStrategy {
    @Override
    public double getSmoothMotion() {
        return 0.9; // Slightly crazier motion
    }

    @Override
    public double getHookSize() {
        return 0.2; // Larger hook size
    }

    @Override
    public double getHookPower() {
        return 0.6; // Faster pulling speed
    }
}

