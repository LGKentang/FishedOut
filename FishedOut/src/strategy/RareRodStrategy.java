package strategy;

public class RareRodStrategy implements FishingStrategy {
    @Override
    public double getSmoothMotion() {
        return 0.8; // Fish are harder to catch
    }

    @Override
    public double getHookSize() {
        return 0.22; // Larger hook
    }

    @Override
    public double getHookPower() {
        return 0.7; // Faster pulling
    }
}
