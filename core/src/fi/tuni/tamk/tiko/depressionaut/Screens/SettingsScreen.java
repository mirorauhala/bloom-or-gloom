package fi.tuni.tamk.tiko.depressionaut.Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fi.tuni.tamk.tiko.depressionaut.MyGdxGame;

public class SettingsScreen implements Screen {
    private MyGdxGame game;

    private SpriteBatch settingsBatch;

    private Texture background = new Texture("splashScreen/splashBackground.jpg");
    private Texture soundOff = new Texture("Settings/SoundButton/sound-off.png");
    private Texture soundOn = new Texture("Settings/SoundButton/sound-on.png");
    private Texture musicOff = new Texture("Settings/MusicButton/music-off.png");
    private Texture musicOn = new Texture("Settings/MusicButton/music-off.png");
    private Texture langEn = new Texture("Settings/LangButton/english.png");
    private Texture langFi = new Texture("Settings/LangButton/finnish.png");
    private Texture comicBtn = new Texture("Settings/ComicButton/comicbutton.png");

    public SettingsScreen(MyGdxGame game) {
        this.game = game;

        settingsBatch = game.hudBatch;

    }

    /**
     * Called when this screen becomes the current screen for a {@link MyGdxGame}.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // set background to white
        settingsBatch.begin();
        settingsBatch.draw(background, 0, 0);
        settingsBatch.draw(soundOff, 0 ,0);
        settingsBatch.draw(musicOn, 0 ,0);
        settingsBatch.draw(langEn, 0 ,0);
        settingsBatch.draw(comicBtn, 0 ,0);
        settingsBatch.end();
    }

    /**
     * @param width the new width in pixels
     * @param height the new height in pixels
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link MyGdxGame}.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
