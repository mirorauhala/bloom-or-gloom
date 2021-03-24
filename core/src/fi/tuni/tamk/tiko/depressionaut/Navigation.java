package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Navigation {
    private final Texture game = new Texture("UI/gamePressed.png");
    private final Texture shop = new Texture("UI/shopPressed.png");
    private final Texture settings = new Texture("UI/settingsPressed.png");
    private final Rectangle area = new Rectangle(0, 10, 100, 10 );

    private Screen hudSelection;

    public enum Screen {
        NONE(0),
        GAME(1),
        SHOP(2),
        SETTINGS(3);

        private final int screenIndex;

        Screen(int screenIndex) {
            this.screenIndex = screenIndex;
        }

        /**
         * @return int  The integer value for the navigation level.
         */
        public int getScreenIndex() {
            return screenIndex;
        }
    }

    public Navigation() {
        hudSelection = Screen.GAME;
    }

    /**
     * Draws the hud that is selected by a number 0-4.
     *
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        if(hudSelection == Screen.GAME) {
            batch.draw(game, 0, 0);
        }
        if(hudSelection == Screen.SHOP) {
            batch.draw(shop, 0, 0);
        }
        if(hudSelection == Screen.SETTINGS) {
            batch.draw(settings, 0, 0);
        }
    }

    /**
     * @param hudSelection
     */
    public void setHudSelection(Screen hudSelection) {
        this.hudSelection = hudSelection;
    }
}
