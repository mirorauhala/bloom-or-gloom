package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class Sounds {
    public com.badlogic.gdx.audio.Sound click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
    public com.badlogic.gdx.audio.Sound menuClick = Gdx.audio.newSound(Gdx.files.internal("sounds/settingsClick.mp3"));
    public Preferences prefs = Gdx.app.getPreferences("general");


    /**
     * used to play a clicking sound whenever the player clicks the game screen
     */
    public void clicksoundPlay() {
        if(prefs.getString("sound").equals("on")) {
            click.play(0.1f, MathUtils.random(0.95f, 1.05f), 0);
        }

    }


    /**
     * used to play a clicking sound whenever the player presses a menu button
     */
    public void menuClicksoudPlay() {
        if(prefs.getString("sound").equals("on")) {
            menuClick.play(0.1f, MathUtils.random(0.95f, 1.05f), 0);
        }

    }
}
