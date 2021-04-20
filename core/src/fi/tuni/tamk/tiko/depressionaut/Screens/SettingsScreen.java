package fi.tuni.tamk.tiko.depressionaut.Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import fi.tuni.tamk.tiko.depressionaut.MyGdxGame;
import fi.tuni.tamk.tiko.depressionaut.Sounds;
import fi.tuni.tamk.tiko.depressionaut.Thoughts.ThoughtBubble;

public class SettingsScreen implements Screen {
    private MyGdxGame game;

    public Preferences prefs = Gdx.app.getPreferences("settings");

    private Rectangle soundRectangle;

    private OrthographicCamera camera;

    public Sounds sounds = new Sounds();

    /*prefs.putString("lastLogin", strDate);
      prefs.flush();


      prefs.getString("lastLogin")*/

    private SpriteBatch settingsBatch;

    private Texture background = new Texture("splashScreen/splashBackground.jpg");
    private Texture soundOff = new Texture("Settings/SoundButton/sound-off.png");
    private Texture soundOn = new Texture("Settings/SoundButton/sound-on.png");
    private Texture musicOff = new Texture("Settings/MusicButton/music-off.png");
    private Texture musicOn = new Texture("Settings/MusicButton/music-off.png");
    private Texture langEn = new Texture("Settings/LangButton/english.png");
    private Texture langFi = new Texture("Settings/LangButton/finnish.png");
    private Texture comicBtn = new Texture("Settings/ComicButton/comicbutton.png");
    private Texture infoBox = new Texture("Settings/InfoBox.png");

    public SettingsScreen(MyGdxGame game) {
        this.game = game;

        settingsBatch = game.hudBatch;
        camera = game.camera;

        soundRectangle = new Rectangle(200, 1550, 300, 300);

    }
    public void checkForTap() {

        if(Gdx.input.justTouched()) {

            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);


            if(soundRectangle.contains(touch.x, touch.y)) {
                if(prefs.getString("sound").equals("off")) {
                    prefs.putString("sound", "on");
                    prefs.flush();
                } else {
                    prefs.putString("sound", "off");
                    prefs.flush();
                }
                sounds.menuClicksoudPlay();
            }

        }

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
        drawButtonSound();
        settingsBatch.draw(musicOn, 0 ,0);
        settingsBatch.draw(langEn, 0 ,0);
        settingsBatch.draw(comicBtn, 0 ,0);
        settingsBatch.draw(infoBox, 0, 0);
        settingsBatch.end();
        checkForTap();
    }

    public void drawButtonSound() {
        if(prefs.getString("sound").equals("on")) {
            settingsBatch.draw(soundOn, 0 ,0);
        } else {
            settingsBatch.draw(soundOff, 0 ,0);
        }

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
