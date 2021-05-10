package fi.tuni.tamk.tiko.bloomorgloom2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

/**
 * Class for all the sound and music in the game.
 */
public class Sounds {
    private final MyGdxGame game;
    public GameClock clock;
    public com.badlogic.gdx.audio.Sound click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
    public com.badlogic.gdx.audio.Sound menuClick = Gdx.audio.newSound(Gdx.files.internal("sounds/settingsClick.mp3"));
    public com.badlogic.gdx.audio.Sound buySound = Gdx.audio.newSound(Gdx.files.internal("sounds/buySound.mp3"));

    public com.badlogic.gdx.audio.Music crickets = Gdx.audio.newMusic(Gdx.files.internal("sounds/crickets.mp3"));
    public com.badlogic.gdx.audio.Music nature = Gdx.audio.newMusic(Gdx.files.internal("sounds/nature.mp3"));
    public com.badlogic.gdx.audio.Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/bgmusic.mp3"));

    /**
     * Constructor for the class.
     *
     * @param game Needed for the functionality of screens in LibGDX.
     */
    public Sounds(MyGdxGame game) {
        this.game = game;
        clock = new GameClock(game);
    }

    /**
     * used to play a clicking sound whenever the player clicks the game screen
     */
    public void clicksoundPlay() {
        if(game.settings.getSound() || !game.settings.containsSound()) {
            click.play(0.07f * game.soundVolume, MathUtils.random(0.95f, 1.05f), 0);
        }

    }

    /**
     * used to play a clicking sound whenever the player presses a menu button
     */
    public void menuClicksoudPlay() {
        if(game.settings.getSound() || !game.settings.containsSound()) {
            menuClick.play(0.1f * game.soundVolume, MathUtils.random(0.95f, 1.05f), 0);
        }
    }
    /**
     * used to play buysound
     */
    public void buySoundPlay() {
        if(game.settings.getSound() || !game.settings.containsSound()) {
            buySound.play(1f * game.soundVolume);
        }
    }
    /**
     * used to play crickets during night
     */
    public void ambientPlay() {
        if(clock.isNight() && (game.settings.getMusic() || !game.settings.containsMusic()) ) {
            crickets.setLooping(true);
            crickets.setVolume(0.01f * game.musicVolume);
            crickets.play();
        } else {
            nature.setLooping(true);
            nature.setVolume(0.02f * game.musicVolume);
            nature.play();
        }
    }

    /**
     * Stops ambient music.
     */
    public void stopAmbient() {
        if(crickets.isPlaying()) {
            crickets.stop();
        }
        if(nature.isPlaying()) {
            nature.stop();
        }
    }

    /**
     * Plays music.
     */
    public void musicPlay() {
        if(game.settings.getMusic()) {
            music.setLooping(true);
            music.setVolume(0.1f * game.musicVolume);
            music.play();
        }
    }

    /**
     * Stops music.
     */
    public void stopMusic() {
        if(music.isPlaying()) {
            music.stop();
        }

    }
}

