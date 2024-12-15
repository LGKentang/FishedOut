package strategy;

public class DefaultRodStrategy implements FishingStrategy {
    @Override
    public double getSmoothMotion() {
        return 1.0; // Balanced smooth motion
    }

    @Override
    public double getHookSize() {
        return 0.15; // Medium hook size
    }

    @Override
    public double getHookPower() {
        return 0.5; // Moderate pulling speed
    }
}