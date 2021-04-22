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

import org.w3c.dom.css.Rect;

import fi.tuni.tamk.tiko.depressionaut.MyGdxGame;
import fi.tuni.tamk.tiko.depressionaut.Sounds;
import fi.tuni.tamk.tiko.depressionaut.Thoughts.ThoughtBubble;

public class SettingsScreen implements Screen {
    private MyGdxGame game;

    public Preferences prefs = Gdx.app.getPreferences("settings");

    private Rectangle soundRectangle;
    private Rectangle musicRectangle;
    private Rectangle finnishRectangle;
    private Rectangle englishRectangle;
    private Rectangle resetRectangle;
    private Rectangle comicRectangle;
    private Rectangle confirmRectangle;
    private Rectangle cancelRectangle;

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
    private Texture musicOn = new Texture("Settings/MusicButton/music-on.png");
    private Texture langEn = new Texture("Settings/LangButton/english.png");
    private Texture langFi = new Texture("Settings/LangButton/finnish.png");
    private Texture comicBtn = new Texture("Settings/ComicButton/comicbutton.png");
    private Texture infoBox = new Texture("Settings/InfoBox.png");


    private final Texture resetOffEn = new Texture("Settings/ResetButton/EN/resetOffEn.png");
    private final Texture resetOnEn = new Texture("Settings/ResetButton/EN/resetOnEn.png");
    private final Texture resetEn = new Texture("Settings/ResetButton/EN/resetEn.png");
    private final Texture resetConfirmationEn = new Texture("Settings/ResetButton/EN/resetConfirmEn.png");

    private final Texture resetOffFi = new Texture("Settings/ResetButton/FI/resetOffFi.png");
    private final Texture resetOnFi = new Texture("Settings/ResetButton/FI/resetOnFi.png");
    private final Texture resetFi = new Texture("Settings/ResetButton/FI/resetFi.png");
    private final Texture resetConfirmationFi = new Texture("Settings/ResetButton/FI/resetConfirmationFi.png");




    private boolean resetPressed;

    public SettingsScreen(MyGdxGame game) {
        this.game = game;

        settingsBatch = game.hudBatch;
        camera = game.camera;

        resetPressed = false;

        soundRectangle = new Rectangle(200, 1550, 300, 300);
        musicRectangle = new Rectangle(580, 1550, 300, 300);
        finnishRectangle = new Rectangle(50, 1300, 250, 200);
        englishRectangle = new Rectangle(415, 1300, 250, 200);
        comicRectangle = new Rectangle(780, 1300, 250, 200);
        resetRectangle = new Rectangle(240, 240, 600, 230);
        cancelRectangle = new Rectangle(120, 550, 380, 150);
        confirmRectangle = new Rectangle(580, 550, 380, 150);


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
            if(musicRectangle.contains(touch.x, touch.y)) {
                if(prefs.getString("music").equals("off")) {
                    prefs.putString("music", "on");
                    prefs.flush();
                } else {
                    prefs.putString("music", "off");
                    prefs.flush();
                }
                sounds.menuClicksoudPlay();
            }
            if(finnishRectangle.contains(touch.x, touch.y)) {
                if(prefs.getString("lang").equals("en")) {
                    prefs.putString("lang", "fi");
                    prefs.flush();
                } else {
                    prefs.putString("lang", "fi");
                    prefs.flush();
                }
                sounds.menuClicksoudPlay();
            }
            if(englishRectangle.contains(touch.x, touch.y)) {
                if(prefs.getString("lang").equals("fi")) {
                    prefs.putString("lang", "en");
                    prefs.flush();
                } else {
                    prefs.putString("lang", "en");
                    prefs.flush();
                }
                sounds.menuClicksoudPlay();
            }
            if(comicRectangle.contains(touch.x, touch.y)) {
                game.setScreen(new Comic(game));
            }

            if(resetRectangle.contains(touch.x, touch.y)) {
                if(!resetPressed) {
                    resetPressed = true;
                    sounds.menuClicksoudPlay();
                }
            }
            if(cancelRectangle.contains(touch.x, touch.y)) {
                if(resetPressed) {
                    sounds.menuClicksoudPlay();
                    resetPressed = false;
                }
            }
            if(confirmRectangle.contains(touch.x, touch.y)) {
                if(resetPressed) {
                    sounds.menuClicksoudPlay();
                }
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
        drawButtonMusic();
        drawButtonLang();
        settingsBatch.draw(comicBtn, 0 ,0);
        settingsBatch.draw(infoBox, 0, 0);
        drawButtonReset();
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
    public void drawButtonMusic() {
        if(prefs.getString("music").equals("on")) {
            settingsBatch.draw(musicOn, 0 ,0);
        } else {
            settingsBatch.draw(musicOff, 0 ,0);
        }
    }
    public void drawButtonLang() {
        if(prefs.getString("lang").equals("en")) {
            settingsBatch.draw(langEn, 0 ,0);
        } else {
            settingsBatch.draw(langFi, 0 ,0);
        }
    }
    public void drawButtonReset() {
        if(prefs.getString("lang").equals("en")) {
            if(resetPressed) {
                settingsBatch.draw(resetOnEn, 0 ,0);
                settingsBatch.draw(resetEn, 0, 0);

            } else {
                settingsBatch.draw(resetOffEn, 0 ,0);
            }
        } else {
            if(resetPressed) {
                settingsBatch.draw(resetOnFi, 0 ,0);
                settingsBatch.draw(resetFi, 0, 0);

            } else {
                settingsBatch.draw(resetOffFi, 0 ,0);
            }
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