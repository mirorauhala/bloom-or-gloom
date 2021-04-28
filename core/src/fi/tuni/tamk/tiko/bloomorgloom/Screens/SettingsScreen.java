package fi.tuni.tamk.tiko.bloomorgloom.Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fi.tuni.tamk.tiko.bloomorgloom.MyGdxGame;

/**
 * SettingsScreen houses all of the settings and stats of the player
 *
 * @author Jaakko Saranpää
 */
public class SettingsScreen implements Screen {
    private MyGdxGame game;

    private Stage stage;

    private Rectangle soundRectangle;
    private Rectangle musicRectangle;
    private Rectangle finnishRectangle;
    private Rectangle englishRectangle;
    private Rectangle resetRectangle;
    private Rectangle comicRectangle;
    private Rectangle confirmRectangle;
    private Rectangle cancelRectangle;
    private Rectangle guideContinueRectangle;

    private OrthographicCamera camera;

    public BitmapFont font;

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

    private final Texture guideFI = new Texture("Settings/GuideFI.png");
    private final Texture guideEN = new Texture("Settings/GuideEN.png");


    private boolean resetPressed;
    private boolean guidePressed;

    public SettingsScreen(MyGdxGame game) {
        this.game = game;
        settingsBatch = game.hudBatch;
        camera = game.camera;
        stage = new Stage(new ScreenViewport());

        font = new BitmapFont(Gdx.files.internal("UI/QuicksandASCII.fnt"));

        resetPressed = false;
        guidePressed = false;

        soundRectangle = new Rectangle(200, 1550, 300, 300);
        musicRectangle = new Rectangle(580, 1550, 300, 300);
        finnishRectangle = new Rectangle(50, 1300, 250, 200);
        englishRectangle = new Rectangle(415, 1300, 250, 200);
        comicRectangle = new Rectangle(780, 1300, 250, 200);
        resetRectangle = new Rectangle(240, 240, 600, 230);
        cancelRectangle = new Rectangle(120, 550, 380, 150);
        confirmRectangle = new Rectangle(580, 550, 380, 150);
        guideContinueRectangle = new Rectangle(240, 270, 600, 230);


    }

    /**
     * checkForTap checks if the player has pressed a button
     */
    public void checkForTap() {

        if(Gdx.input.justTouched()) {

            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if(!guidePressed) {
                if(soundRectangle.contains(touch.x, touch.y)) {
                    game.settings.setSound(!game.settings.getSound());
                    game.sounds.menuClicksoudPlay();
                }
                if(musicRectangle.contains(touch.x, touch.y)) {
                    game.settings.setMusic(!game.settings.getMusic());
                    game.sounds.menuClicksoudPlay();
                    if(game.settings.getMusic()) {
                        game.sounds.ambientPlay();
                        game.sounds.musicPlay();
                    }
                    if(!game.settings.getMusic()) {
                        game.sounds.stopAmbient();
                        game.sounds.stopMusic();
                    }
                }
                if(finnishRectangle.contains(touch.x, touch.y)) {

                    if(game.settings.getLang().equals("en")) {
                        game.settings.setLang("fi");
                    } else {
                        game.settings.setLang("en");
                    }
                    game.sounds.menuClicksoudPlay();
                }
                if(englishRectangle.contains(touch.x, touch.y)) {
                    if(game.settings.getLang().equals("en")) {
                        game.settings.setLang("fi");
                    } else {
                        game.settings.setLang("en");
                    }
                    game.sounds.menuClicksoudPlay();
                }
                if(comicRectangle.contains(touch.x, touch.y)) {
                    game.sounds.menuClicksoudPlay();
                    guidePressed = true;
                }

                if(resetRectangle.contains(touch.x, touch.y)) {
                    if(!resetPressed) {
                        resetPressed = true;
                        game.sounds.menuClicksoudPlay();
                    }
                }
                if(cancelRectangle.contains(touch.x, touch.y)) {
                    if(resetPressed) {
                        game.sounds.menuClicksoudPlay();
                        resetPressed = false;
                    }
                }
                if(confirmRectangle.contains(touch.x, touch.y)) {
                    if(resetPressed) {
                        game.sounds.menuClicksoudPlay();
                        game.settings.reset();
                        game.score.emptyScorePrefs();
                        game.inventory.removeInventoryPrefs();
                        game.settings.setFirstDay(game.settings.getLastSeen());
                        resetPressed = false;
                    }
                }
            }
            if(guideContinueRectangle.contains(touch.x, touch.y)) {
                if(guidePressed) {
                    game.sounds.menuClicksoudPlay();
                    guidePressed = false;
                }
            }
        }
    }

    /**
     * Called when this screen becomes the current screen for a {@link MyGdxGame}.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
        drawStats();
        drawGuide();
        settingsBatch.end();
        checkForTap();
    }

    private void drawStats() {
        if(!resetPressed) {
            if(game.settings.getLang().equals("en")) {
                font.getData().setScale(2.5f, 2.5f);
                font.draw(settingsBatch, "Stats", 150, 1150, 780, 1, true);
                font.getData().setScale(2f, 2f);
                font.draw(settingsBatch, "Click: " + (int)game.score.getClickPower(), 120, 1050, 420, -1, true);
                font.draw(settingsBatch, "Multiplier: " + (int)game.score.getMultiplier(), 120, 950, 420, -1, true);
                font.draw(settingsBatch, "Score: " + game.score.getRationalizedValue(game.score.getScore(), 1), 120, 850, 420, -1, true);
                font.draw(settingsBatch, "Happiness: " + (game.score.getHappinessLevel() + 1), 120, 750, 420, -1, true);
                font.draw(settingsBatch, "Total Clicks: ", 540, 1050, 420, -1, true);
                font.draw(settingsBatch, (int)game.score.getTotalClicks() + "", 540, 950, 420, -1, true);
                font.draw(settingsBatch, "Start date: ", 540, 850, 420, -1, true);
                font.draw(settingsBatch, game.settings.getFirstDay(), 540, 750, 420, -1, true);
            } else {
                font.getData().setScale(2.5f, 2.5f);
                font.draw(settingsBatch, "Tilastot", 150, 1150, 780, 1, true);
                font.getData().setScale(2f, 2f);
                font.draw(settingsBatch, "Klikkaus: " + (int)game.score.getClickPower(), 120, 1050, 420, -1, true);
                font.draw(settingsBatch, "Kerroin: " + (int)game.score.getMultiplier(), 120, 950, 420, -1, true);
                font.draw(settingsBatch, "Pisteet: " + game.score.getRationalizedValue(game.score.getScore(), 1), 120, 850, 420, -1, true);
                font.draw(settingsBatch, "Onni: " + (game.score.getHappinessLevel() + 1), 120, 750, 420, -1, true);
                font.draw(settingsBatch, "Klikkimäärä: ", 540, 1050, 420, -1, true);
                font.draw(settingsBatch,  (int)game.score.getTotalClicks() + "", 540, 950, 420, -1, true);
                font.draw(settingsBatch, "Aloituspäivä: ", 540, 850, 420, -1, true);
                font.draw(settingsBatch, game.settings.getFirstDay(), 540, 750, 420, -1, true);
            }
        }
    }

    /**
     * draw method for sound on/off button
     */
    public void drawButtonSound() {
        if(game.settings.getSound() || !game.settings.containsSound()) {
            settingsBatch.draw(soundOn, 0 ,0);
        } else {
            settingsBatch.draw(soundOff, 0 ,0);
        }
    }
    /**
     * draw method for music on/off button
     */
    public void drawButtonMusic() {
        if(game.settings.getMusic() || !game.settings.containsMusic()) {
            settingsBatch.draw(musicOn, 0 ,0);
        } else {
            settingsBatch.draw(musicOff, 0 ,0);
        }
    }
    /**
     * draw method for language english/finnish button
     */
    public void drawButtonLang() {
        if(game.settings.getLang().equals("en")) {
            settingsBatch.draw(langEn, 0 ,0);
        } else {
            settingsBatch.draw(langFi, 0 ,0);
        }
    }
    /**
     * draw method for reset button which shows two new buttons for cancel and continue
     */
    public void drawButtonReset() {
        if(game.settings.getLang().equals("en")) {
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

    public void drawGuide() {
        if(guidePressed) {
            if(game.settings.getLang().equals("en")) {
                settingsBatch.draw(guideEN, 0, 0);
            } else {
                settingsBatch.draw(guideFI, 0, 0);
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
