package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;

import java.util.concurrent.TimeUnit;

public class ScoreCounter {
    private static long score = 0;
    private static double multiplier = 1;
    private static long clickPower = 1;
    private static double tempMultiplier = 1;

    private static void addScore() {
        score += tempMultiplier * multiplier * clickPower;
    }
    public static void addToClickPower(double amount) {
        clickPower += amount;
    }

    public static void addToMultiplier(double amount) {
        multiplier += amount;
    }

    public static void checkForClick() {
        if(Gdx.input.justTouched()) {
            addScore();
        }
    }

    public static long getScore() {
        return score;
    }

    public static void drawScore() {
        if(ScoreCounter.getScore() >= 1000000) {
            System.out.println(ScoreCounter.getScore() / 1000000 + "M");
        } else if(ScoreCounter.getScore() >= 1000) {
            System.out.println(ScoreCounter.getScore() / 1000 + "K");
        } else {
            System.out.println(ScoreCounter.getScore());
        }
    }
}
