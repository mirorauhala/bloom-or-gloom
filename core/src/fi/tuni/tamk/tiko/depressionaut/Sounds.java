package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class Sounds {
    private final MyGdxGame game;
    public GameClock clock;
    public com.badlogic.gdx.audio.Sound click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
    public com.badlogic.gdx.audio.Sound menuClick = Gdx.audio.newSound(Gdx.files.internal("sounds/settingsClick.mp3"));
    public com.badlogic.gdx.audio.Music crickets = Gdx.audio.newMusic(Gdx.files.internal("sounds/crickets.mp3"));

    public Sounds(MyGdxGame game) {
        this.game = game;
        clock = new GameClock(game);
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
    /**
     * used to play crickets during night
     */
    public void ambientPlay() {
        if(clock.isNight() && game.settings.getMusic()) {
            crickets.setLooping(true);
            crickets.setVolume(0.01f);
            crickets.play();
        } else {
            //birds.setLooping(true);
            //birds.play();
        }
    }

    public void stopAmbient() {
        if(crickets.isPlaying()) {
            crickets.stop();
        }
    }
}

