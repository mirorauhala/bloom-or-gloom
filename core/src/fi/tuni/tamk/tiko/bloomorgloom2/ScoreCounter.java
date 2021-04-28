package fi.tuni.tamk.tiko.bloomorgloom2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

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
    private static float tempMultiplier; // Has to always be at least 1
    private float dailyBonus; // Has to always be at least 1
    private final Preferences prefs;
    private GameClock clock;

    public ScoreCounter(MyGdxGame game) {
        prefs = Gdx.app.getPreferences("score");
        clock = new GameClock(game);


        Thread counter = new Thread(new Runnable() {
            /**
             * Score counter.
             *
             * Instead of running every second, this counter runs every 10 milliseconds. This is to
             * make the user-interface part of the counter refresh more frequently than every second.
             */
            @SuppressWarnings("BusyWait")
            @Override
            public void run() {

                while(true) {
                    float amount = countPassiveIncomeIncrement()/100f;

                    setDailyBonus();
                    incrementScore(amount);
                    incrementWallet(amount);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        counter.start();

        if(MyGdxGame.DEBUG) {
            Thread printer = new Thread(new Runnable() {
                @SuppressWarnings("BusyWait")
                @Override
                public void run() {
                    while(true) {
                        Gdx.app.debug("SCORE", "Score is: " + getScore());

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            printer.start();
        }

    }

    /**
     * click() adds points to the main score and the wallet
     * by multiplying the temporary multiplier (buffs and daily bonuses),
     * the multiplier (which can be bought on the store) and the clickpower (which can also be upgraded from the store)
     */
    public void click() {
        incrementScore(countScoreIncrement());
        incrementWallet(countScoreIncrement());
        incrementTotalClicks(1);
    }

    /**
     * The math used when incrementing the score.
     *
     * @return long The amount of score to increment;
     */
    private long countScoreIncrement() {
        return (long)(getDailyBonus() * getTempMultiplier() * getMultiplier() * getClickPower());
    }

    /**
     * The math used when incrementing with passive income.
     *
     * @return long The increment amount of passive income.
     */
    public long countPassiveIncomeIncrement() {
        if(getPassiveIncome() <= 0) {
            return 0;
        }

        return (long)( getDailyBonus() * getTempMultiplier() * getMultiplier() * getPassiveIncome());
    }

    /**
     * Get the score.
     *
     * @return float The score amount.
     */
    public float getScore() {
        return prefs.getFloat("score");
    }

    /**
     * Set the score.
     *
     * @param amount The score amount.
     */
    public void setScore(float amount) {
        prefs.putFloat("score", amount);
        prefs.flush();
    }

    /**
     * Get the score.
     *
     * @return float The score amount.
     */
    public float getTotalClicks() {
        return prefs.getFloat("total-clicks");
    }

    /**
     * Set the total clicks.
     *
     * @param amount The score amount.
     */
    public void setTotalClicks(float amount) {
        prefs.putFloat("total-clicks", amount);
        prefs.flush();
    }

    /**
     * Increment the score by the amount.
     *
     * @param amount The increment amount.
     */
    public void incrementTotalClicks(float amount) {
        setTotalClicks(getTotalClicks() + amount);
    }

    /**
     * Increment the score by the amount.
     *
     * @param amount The increment amount.
     */
    public void incrementScore(float amount) {
        setScore(getScore() + amount);
    }

    /**
     * Get the amount of money in the wallet.
     *
     * @return float The amount money in the wallet.
     */
    public float getWallet() {
        return prefs.getFloat("wallet");
    }

    /**
     * Set the amount of money in the wallet to the given amount.
     *
     * @param amount The amount of money to set.
     */
    public void setWallet(float amount) {
        prefs.putFloat("wallet", amount);
        prefs.flush();
    }

    /**
     * Increment the amount of money in the wallet by the given amount.
     *
     * @param amount The increment amount.
     */
    public void incrementWallet(float amount) {
        setWallet(getWallet() + amount);
    }

    /**
     * Decrement the amount of money in the wallet by the given amount.
     *
     * Safeguards against the player not having enough funds.
     *
     * @param amount The decrement amount.
     * @return boolean True on a successful withdrawal. False if not enough money.
     */
    public boolean decrementWallet(float amount) {
        if (getWallet() < amount) {
            return false;
        }

        setWallet(getWallet() - amount);
        return true;
    }

    /**
     * Set the amount of passive income.
     *
     * @param amount The amount of passive income to set.
     */
    public void setPassiveIncome(float amount) {
        prefs.putFloat("passive-income", amount);
        prefs.flush();
    }

    /**
     * Get the amount of passive income.
     *
     * @return long The amount of passive income.
     */
    public float getPassiveIncome() {
        return prefs.getFloat("passive-income");
    }

    /**
     * Increment the amount of passive income by the given amount.
     *
     * @param amount The increment amount.
     */
    public void incrementPassiveIncome(float amount) {
        setPassiveIncome(getPassiveIncome() + amount);
    }

    /**
     * Get the multiplier.
     *
     * @return float The multiplier amount.
     */
    public float getMultiplier() {
        return prefs.getFloat("multiplier") + 1;
    }

    /**
     * Set the multiplier.
     *
     * @param amount The amount to set.
     */
    public void setMultiplier(float amount) {
        prefs.putFloat("multiplier", amount);
        prefs.flush();
    }

    /**
     * Sets the tempMultiplier based on buffs.
     *
     * @param amount Amount of buffs.
     */
    public void setTempMultiplier(float amount) {
        tempMultiplier = amount + 1;
    }

    /**
     * Get tempMultiplier.
     *
     * @return tempMultiplier
     */
    public float getTempMultiplier() {
        return tempMultiplier;
    }

    /**
     * Increment the multiplier by the given amount.
     *
     * @param amount The increment amount.
     */
    public void incrementMultiplier(float amount) {
        setMultiplier(getMultiplier() + amount);
    }

    /**
     * Get the click power amount.
     *
     * @return long The click power amount.
     */
    public float getClickPower() {
        return prefs.getFloat("click-power") + 1;
    }

    /**
     * Set the click power amount.
     *
     * @param amount The amount to set.
     */
    public void setClickPower(float amount) {
        prefs.putFloat("click-power", amount);
        prefs.flush();
    }

    /**
     * Increment the click power by the given amount.
     *
     * @param amount The increment amount.
     */
    public void incrementClickPower(float amount) {
        setClickPower(getClickPower() + amount);
    }

    /**
     * if the player has gotten a daily bonus today, setDailyBonus will add a 2x multiplier for the day
     */
    public void setDailyBonus() {
        if(clock.hasBonus()) {
            dailyBonus = 2;
        } else {
            dailyBonus = 1;
        }
    }

    /**
     * returns dailybonus
     * @return dailybonus amount
     */
    public float getDailyBonus() {
        return dailyBonus;
    }

    /**
     * Rounds the given value and adds a suffix to the end if needed.
     *
     * @param value Given value
     * @param type 1 = main, 2 = shop
     * @return Rounded value
     */
    public String getRationalizedValue(double value, int type) {
        if (value >= 1000000000) {
            value = Util.roundDouble((value / 1000000000), 3);
            if (value % 1 == 0) {
                return (int) value + "B";
            } else {
                return value + "B";
            }
        } else if(value >= 1000000) {
            value = Util.roundDouble((value / 1000000), 3);
            if (value % 1 == 0) {
                return (int) value + "M";
            } else {
                return value + "M";
            }
        } else if(value >= 1000 && type != 1) {
            return (int)Util.roundDouble((value / 1000), 3) + "K";
        }

        return "" + (int)value;
    }

    /**
     * getHappinessLevel turns the score into happinesslevel
     * @return happinesslevel int
     */
    public int getHappinessLevel() {
        if(getScore() > 10000000) {return 4;}
        if(getScore() > 1000000) {return 3;}
        if(getScore() > 100000) {return 2;}
        if(getScore() > 10000) {return 1;}
        return 0;
    }

    /**
     * returns the amount of score the player needs to get to get to the next level
     * @param happinessLevel int Integer form of the happiness level
     * @return int
     */
    public int getScoreForNextLevel(int happinessLevel) {
        switch (happinessLevel) {
            default: return 10000;
            case 1: return 100000;
            case 2: return 1000000;
            case 3: return 10000000;
        }
    }

    /**
     * Empties score prefs
     */
    public void emptyScorePrefs() {
        prefs.clear();
        prefs.flush();
    }
}
