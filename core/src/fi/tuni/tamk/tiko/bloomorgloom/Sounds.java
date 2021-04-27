package fi.tuni.tamk.tiko.bloomorgloom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Sounds {
    private final MyGdxGame game;
    public GameClock clock;
    public com.badlogic.gdx.audio.Sound click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
    public com.badlogic.gdx.audio.Sound menuClick = Gdx.audio.newSound(Gdx.files.internal("sounds/settingsClick.mp3"));
    public com.badlogic.gdx.audio.Sound buySound = Gdx.audio.newSound(Gdx.files.internal("sounds/buySound.mp3"));

    public com.badlogic.gdx.audio.Music crickets = Gdx.audio.newMusic(Gdx.files.internal("sounds/crickets.mp3"));
    public com.badlogic.gdx.audio.Music nature = Gdx.audio.newMusic(Gdx.files.internal("sounds/nature.mp3"));
    public com.badlogic.gdx.audio.Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/bgmusic.mp3"));


    public Sounds(MyGdxGame game) {
        this.game = game;
        clock = new GameClock(game);
    }

    /**
     * used to play a clicking sound whenever the player clicks the game screen
     */
    public void clicksoundPlay() {
        if(game.settings.getSound() || !game.settings.containsSound()) {
            click.play(0.1f, MathUtils.random(0.95f, 1.05f), 0);
        }

    }


    /**
     * used to play a clicking sound whenever the player presses a menu button
     */
    public void menuClicksoudPlay() {
        if(game.settings.getSound() || !game.settings.containsSound()) {
            menuClick.play(0.1f, MathUtils.random(0.95f, 1.05f), 0);
        }
    }
    /**
     * used to play buysound
     */
    public void buySoundPlay() {
        if(game.settings.getSound() || !game.settings.containsSound()) {
            buySound.play(0.6f);
        }
    }
    /**
     * used to play crickets during night
     */
    public void ambientPlay() {
        if(clock.isNight() && (game.settings.getMusic() || !game.settings.containsMusic()) ) {
            crickets.setLooping(true);
            crickets.setVolume(0.01f);
            crickets.play();
            System.out.println(clock.isNight());
        } else {
            nature.setLooping(true);
            nature.setVolume(0.02f);
            nature.play();
        }
    }

    public void stopAmbient() {
        if(crickets.isPlaying()) {
            crickets.stop();
        }
        if(nature.isPlaying()) {
            nature.stop();
        }
    }

    public void musicPlay() {
        if(game.settings.getMusic()) {
            music.setLooping(true);
            music.setVolume(0.1f);
            music.play();
        }
    }
    public void stopMusic() {
        if(music.isPlaying()) {
            music.stop();
        }

    }
}

