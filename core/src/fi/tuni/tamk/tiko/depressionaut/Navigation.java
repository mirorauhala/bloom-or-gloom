package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Navigation {
    private Texture ui;
    private Texture game = new Texture("UI/gamePressed.png");
    private Texture shop = new Texture("UI/shopPressed.png");
    private Texture settings = new Texture("UI/settingsPressed.png");

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

        public int getScreenIndex() {
            return screenIndex;
        }
    };

    public Navigation() {
        ui = new Texture("UI/gamePressed.png");
        current = Screen.GAME;
    }



    /**
     * Draws the hud that is selected by a number 0-4
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
