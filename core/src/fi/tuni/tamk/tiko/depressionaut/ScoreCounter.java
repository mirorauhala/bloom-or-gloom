package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;

import java.util.concurrent.TimeUnit;


/**
 * ScoreCounter class has methods for counting the score,
 * adding multiplier and temporary buffs, drawing the score etc.
 *
 * @author Jaakko Saranpää
 */
public class ScoreCounter {


    /*EVERYTHING FOR THE MAIN SCORE
    A unit gets added to the main score when the user presses the screen
    The amount of score that gets added is calculated by multiplying the temporary multiplier (buffs and daily bonuses),
    the multiplier (which can be bought on the store) and the clickpower (which can also be upgraded from the store)
     */
    private static long score = 0;
    private static long passiveIncome = 0;
    private static double multiplier = 1; //Has to alway be atleast 1
    private static long clickPower = 1; //Has to alway be atleast 1
    private static double tempMultiplier = 1; //Has to alway be atleast 1


    /*EVERYTHING FOR THE WALLET SYSTEM
    A unit gets added to the wallet everytime a unit is added to the main score
    so if your main score is 100, your wallet will be 100 units too until you spend the units on
    something
    */
    private static long walletScore = 0;


    /**
     * Used when the player buys something
     * Substracts the cost of the object from the wallet score
     * Return a boolean whether the player has enough funds for the purchase
     *
     * @param costAmount
     */
    public static boolean removeScoreFromWallet(long costAmount) {
        if (costAmount <= walletScore) {
            walletScore -= costAmount;
            return true;
        } else {
            return false;
        }
    }


    /**
     * addScore() adds points to the main score and the wallet
     * by multiplying the temporary multiplier (buffs and daily bonuses),
     * the multiplier (which can be bought on the store) and the clickpower (which can also be upgraded from the store)
     *
     *
     */
    private static void addScore() {
        score += tempMultiplier * multiplier * clickPower;
        walletScore += tempMultiplier * multiplier * clickPower;
    }

    /**
     * Increases the main score and the walletscore passively each second, if the player has bought something
     * from the store that adds a passive income
     */
    public static void addPassiveIncome() {
        if(passiveIncome > 0) {
            score += tempMultiplier * multiplier * passiveIncome;
        }
    }

    /**
     * Used for adding passive income to already existing passive income
     * Passive income starts at 0, and can be increased by buying objects from the store
     * @param amount
     */
    public static void increasePassiveIncome(long amount) {
        passiveIncome += amount;
    }

    /**
     * Used to increase the amount of score the player receives by clicking once
     * @param amount
     */
    public static void addToClickPower(double amount) {
        clickPower += amount;
    }

    /**
     * increases the multiplier which multiplies the passive income, and the income the player gets from clicking
     * @param amount
     */
    public static void addToMultiplier(double amount) {
        multiplier += amount;
    }

    /**
     * Checks if the screen is pressed and adds score
     *
     *
     * Will get changed to a rectangle, so the clickable area is smaller
     * Also this will only be active when the player is in the game screen, this is not implemented yet
     *
     */
    public static void checkForClick() {
        if(Gdx.input.justTouched()) {
            addScore();
        }
    }

    public static long getScore() {
        return score;
    }

    public static long getWalletScore() {
        return walletScore;
    }


    /**
     * drawScore() and drawWallet() are used to draw the amount of units the player has of each on the screen
     * Will also have a method of changing decimal places when the score gets above a certain limit
     *
     * currently prints them in the console
     *
     */
    public static void drawScore() {
        if(ScoreCounter.getScore() >= 1000000) {
            System.out.println("Score: " + ScoreCounter.getScore() / 1000000 + "M");
        } else if(ScoreCounter.getScore() >= 1000) {
            System.out.println("Score: " + ScoreCounter.getScore() / 1000 + "K");
        } else {
            System.out.println("Score: " + ScoreCounter.getScore());
        }
    }
    public static void drawWallet() {
        if(ScoreCounter.getWalletScore() >= 1000000) {
            System.out.println("Score: " + ScoreCounter.getWalletScore() / 1000000 + "M");
        } else if(ScoreCounter.getWalletScore() >= 1000) {
            System.out.println("Score: " + ScoreCounter.getWalletScore() / 1000 + "K");
        } else {
            System.out.println("Score: " + ScoreCounter.getWalletScore());
        }
    }
}
