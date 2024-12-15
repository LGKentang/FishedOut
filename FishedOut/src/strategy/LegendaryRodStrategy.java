package strategy;

public class LegendaryRodStrategy implements FishingStrategy {
    @Override
    public double getSmoothMotion() {
        return 0.6; // Very crazy fish
    }

    @Override
    public double getHookSize() {
        return 0.28; // Very large hook
    }

    @Override
    public double getHookPower() {
        return 0.9; // High pulling power
    }
}