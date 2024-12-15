package strategy;

public class MythicRodStrategy implements FishingStrategy {
    @Override
    public double getSmoothMotion() {
        return 0.5; // Craziest fish motion
    }

    @Override
    public double getHookSize() {
        return 0.3; // Maximum hook size
    }

    @Override
    public double getHookPower() {
        return 1.0; // Fastest pulling speed
    }
}
