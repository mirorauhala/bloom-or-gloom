package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import fi.tuni.tamk.tiko.depressionaut.Screens.Comic;


public class DailyBonus {

    private String [] emotionTips;

    public Texture bonusWindowEN = new Texture("UI/Dailybonus/BonuswindowEN.png");
    public Texture bonusWindowFI = new Texture("UI/Dailybonus/BonuswindowFI.png");
    public Texture noBonusEN = new Texture("UI/Dailybonus/NobonusEN.png");
    public Texture noBonusFI = new Texture("UI/Dailybonus/NobonusFI.png");

    public Rectangle continueButton;

    public Preferences prefs = Gdx.app.getPreferences("general");

    public BitmapFont font;

    public String todaysTip;

    public boolean isBonusWindowOnScreen;



    public GameClock clock;
    public Sounds sounds;

    public DailyBonus() {


        continueButton = new Rectangle(230, 380, 620, 240);

        font = new BitmapFont(Gdx.files.internal("UI/QuicksandASCII.fnt"));

        txtToArray();
        randomTip();

        clock = new GameClock();
        sounds = new Sounds();

    }

    public void txtToArray() {
        FileHandle handle;
        if(prefs.getString("lang").equals("en")) {
            handle = Gdx.files.internal("UI/Dailybonus/TipsEN.txt");
        } else {
            handle = Gdx.files.internal("UI/Dailybonus/TipsFI.txt");
        }

        String text = handle.readString();
        emotionTips = text.split("\\r?\\n");
    }

    public void randomTip() {
        int randomNum = MathUtils.random(emotionTips.length - 1);
        todaysTip = emotionTips[randomNum];
    }

    public void drawWindow(SpriteBatch batch) {
        if(clock.isFirstOfTheDay()) {
            isBonusWindowOnScreen = true;
            if(prefs.getString("lang").equals("en")) {
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
    public void checkForTap(Camera camera) {
        if(Gdx.input.justTouched() && clock.isFirstOfTheDay()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if(continueButton.contains(touch.x, touch.y)) {
                sounds.menuClicksoudPlay();
                clock.setLastLogin();
                isBonusWindowOnScreen = false;
                if(clock.isMorning()) {
                    clock.addBonus();
                }
            }
        }
    }
}
