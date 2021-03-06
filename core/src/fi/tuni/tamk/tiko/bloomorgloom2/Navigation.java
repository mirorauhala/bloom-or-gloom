package fi.tuni.tamk.tiko.bloomorgloom2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;

import fi.tuni.tamk.tiko.bloomorgloom2.Screens.GameScreen;
import fi.tuni.tamk.tiko.bloomorgloom2.Screens.SettingsScreen;
import fi.tuni.tamk.tiko.bloomorgloom2.Screens.ShopClothingScreen;

/**
 * Class for the navigation bar.
 */
public class Navigation {
    private OrthographicCamera camera;
    private MyGdxGame game;
    private Screen activeScreen;
    private HashMap<Screen, com.badlogic.gdx.Screen> screenPair = new HashMap<>();
    public Texture gameNav;
    public Texture shopNav;
    public Texture settingsNav;

    /**
     * Stores information for the different states of the navigation bar.
     */
    public enum Screen {
        GAME("Game", new Texture("UI/gamePressed.png"), new Rectangle(0, 0, 360, 202)),
        SHOP("Shop", new Texture("UI/shopPressed.png"), new Rectangle(360, 0 , 360, 202)),
        SETTINGS("Settings", new Texture("UI/settingsPressed.png"), new Rectangle(720, 0, 360, 202));

        private final String name;
        private final Texture texture;
        private final Rectangle rectangle;

        Screen(String name, Texture texture, Rectangle rectangle) {
            this.name = name;
            this.texture = texture;
            this.rectangle = rectangle;
        }

        /**
         * @return String  The name of the screen.
         */
        public String getName() {
            return name;
        }

        /**
         * @return Texture  Get the navigation texture for the active state of this screen.
         */
        public Texture getTexture() {
            System.out.println(texture);
            return texture;
        }

        /**
         * @return Rectangle  Get the area where the navigation button is.
         */
        public Rectangle getRectangle() {
            return rectangle;
        }
    }

    /**
     * Constructor for the class.
     *
     * @param game Needed for the functionality of screens in LibGDX.
     */
    public Navigation(MyGdxGame game) {
        this.game = game;
        this.camera = game.camera;

        // pair the actual gdx screen along with the corresponding enum
        this.screenPair.put(Screen.GAME, new GameScreen(game));
        this.screenPair.put(Screen.SHOP, new ShopClothingScreen(game));
        this.screenPair.put(Screen.SETTINGS, new SettingsScreen(game));

        gameNav = new Texture("UI/gamePressed.png");
        shopNav = new Texture("UI/shopPressed.png");
        settingsNav = new Texture("UI/settingsPressed.png");


    }


    /**
     * Draws the current navigation.
     * @param batch SpriteBatch
     */
    public void draw(SpriteBatch batch) {
        if(null == activeScreen) {
            return;
        }


        if(activeScreen == Screen.GAME) {
            batch.draw(gameNav, 0, 0);

        }
        if(activeScreen == Screen.SHOP) {
            batch.draw(shopNav, 0, 0);
        }
        if(activeScreen == Screen.SETTINGS) {
            batch.draw(settingsNav, 0, 0);
        }
        //batch.draw(activeScreen.getTexture(), 0, 0);
        listenForClick();
    }

    /**
     * Listen for a click on the navigation buttons and change the active screen.
     */
    public void listenForClick() {
        if(Gdx.input.justTouched()) {

            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            for (Screen screen : Screen.values()) {
                if(screen.getRectangle().contains(touch.x, touch.y)) {
                    Gdx.app.debug("NAV", "Set active navigation for: " + screen.getName());
                    if(game.getScreen() != screenPair.get(screen)) {
                        game.sounds.menuClicksoudPlay();
                    }

                    setActive(screen);
                    game.setScreen(screenPair.get(screen));
                }
            }
        }
    }

    /**
     * @param screen  The navigation to be set visible.
     */
    public void setActive(Screen screen) {
        this.activeScreen = screen;
    }

    /**
     * Set the active screen from memory.
     * @param screen Screen
     */
    public void setScreen(Screen screen) {
        this.game.setScreen(screenPair.get(screen));
    }
}
