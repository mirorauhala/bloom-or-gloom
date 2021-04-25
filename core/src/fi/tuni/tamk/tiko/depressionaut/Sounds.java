package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class Sounds {
    private final MyGdxGame game;
    public com.badlogic.gdx.audio.Sound click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
    public com.badlogic.gdx.audio.Sound menuClick = Gdx.audio.newSound(Gdx.files.internal("sounds/settingsClick.mp3"));

    public Sounds(MyGdxGame game) {
        this.game = game;
    }

    /**
     * used to play a clicking sound whenever the player clicks the game screen
     */
    public void clicksoundPlay() {
        if(game.settings.getSound()) {
            click.play(0.1f, MathUtils.random(0.95f, 1.05f), 0);
        }

    }


    /**
     * used to play a clicking sound whenever the player presses a menu button
     */
    public void menuClicksoudPlay() {
        if(game.settings.getSound()) {
            menuClick.play(0.1f, MathUtils.random(0.95f, 1.05f), 0);
        }

    }
}
