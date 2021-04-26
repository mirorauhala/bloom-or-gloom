package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * DailyBonus creates a window every day that the user plays the game for the first time during that day and gives the player a bonus
 *
 * @author Jaakko Saranpää
 */
public class DailyBonus {
    private final MyGdxGame game;

    private String [] emotionTips;

    public Texture bonusWindowEN = new Texture("UI/Dailybonus/BonuswindowEN.png");
    public Texture bonusWindowFI = new Texture("UI/Dailybonus/BonuswindowFI.png");
    public Texture noBonusEN = new Texture("UI/Dailybonus/NobonusEN.png");
    public Texture noBonusFI = new Texture("UI/Dailybonus/NobonusFI.png");
    private Texture indicatorEN = new Texture("UI/Dailybonus/IndicatorEN.png");
    private Texture indicatorFI = new Texture("UI/Dailybonus/IndicatorFI.png");

    public Rectangle continueButton;

    public BitmapFont font;

    public String todaysTip;

    public boolean isBonusWindowOnScreen;



    public GameClock clock;

    public DailyBonus(MyGdxGame game) {
        this.game = game;

        continueButton = new Rectangle(230, 380, 620, 240);

        font = new BitmapFont(Gdx.files.internal("UI/QuicksandASCII.fnt"));

        txtToArray();
        randomTip();

        clock = new GameClock(game);

    }

    /**
     * txtToArray turns a .txt file into an array of strings divided by line breaks
     */
    public void txtToArray() {
        FileHandle handle;
        if(game.settings.getLang().equals("en")) {
            handle = Gdx.files.internal("UI/Dailybonus/TipsEN.txt");
        } else {
            handle = Gdx.files.internal("UI/Dailybonus/TipsFI.txt");
        }

        String text = handle.readString();
        emotionTips = text.split("\\r?\\n");
    }

    /**
     * randomTip() chooses a random string from an array of strings
     */
    public void randomTip() {
        int randomNum = MathUtils.random(emotionTips.length - 1);
        todaysTip = emotionTips[randomNum];
    }

    /**
     * drawWindow draws the dailybonus window and all its texts
     * @param batch spritebatch
     */
    public void drawWindow(SpriteBatch batch) {
        if(clock.isFirstOfTheDay()) {
            isBonusWindowOnScreen = true;
            if(game.settings.getLang().equals("en")) {
                if(clock.isMorning()) {
                    batch.draw(bonusWindowEN, 0, 0);
                } else {
                    batch.draw(noBonusEN, 0, 0);
                }

            } else {
                if(clock.isMorning()) {
                    batch.draw(bonusWindowFI, 0, 0);
                } else {
                    batch.draw(noBonusFI, 0, 0);
                }
            }
            if(todaysTip.length() <= 85) {
                font.getData().setScale(2.5f, 2.5f); //1.8 = 192 2.5 = 85 1.5 = 261
                font.draw(batch, todaysTip, 150, 1250, 780, 1, true);
            } else if (todaysTip.length() <= 192) {
                font.getData().setScale(1.8f, 1.8f); //1.8 = 192 2.5 = 85 1.5 = 261
                font.draw(batch, todaysTip, 100, 1275, 880, 1, true);
            } else {
                font.getData().setScale(1.5f, 1.5f); //1.8 = 192 2.5 = 85 1.5 = 261
                font.draw(batch, todaysTip, 100, 1290, 880, 1, true);
            }


        }

    }

    public void drawIndicator(SpriteBatch batch) {
        if (game.settings.getBonusDay().equals(clock.strDate)) {
            if (game.settings.getLang().equals("en")) {
                batch.draw(indicatorEN, 0, 0);
            } else {
                batch.draw(indicatorFI, 0, 0);
            }
        }
    }
    /**
     * checkForTap checks if the player presses the "continue" button on dailybonus window
     * @param camera camera
     */
    public void checkForTap(Camera camera) {
        if(Gdx.input.justTouched() && clock.isFirstOfTheDay()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if(continueButton.contains(touch.x, touch.y)) {
                game.sounds.menuClicksoudPlay();
                clock.setLastLogin();
                isBonusWindowOnScreen = false;
                if(clock.isMorning()) {
                    clock.addBonus();
                }
            }
        }
    }
}
