package strategy;

public class EpicRodStrategy implements FishingStrategy {
    @Override
    public double getSmoothMotion() {
        return 0.7; // Fish are crazier
    }

    @Override
    public double getHookSize() {
        return 0.25; // Bigger hook
    }

    @Override
    public double getHookPower() {
        return 0.8; // Strong pulling power
    }
}
