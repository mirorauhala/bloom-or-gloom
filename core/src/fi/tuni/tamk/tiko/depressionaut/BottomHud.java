package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class BottomHud {
    private Texture game = new Texture("UI/gamePressed.png");
    private Texture shop = new Texture("UI/shopPressed.png");
    private Texture settings = new Texture("UI/settingsPressed.png");

    private int hudSelection = 0;


    /**
     * Draws the hud that is selected by a number 0-4
     *
     * 0 = No HUD
     * 1 = Game HUD
     * 2 = Shop HUD
     * 3 = Settings HUD
     * @param batch
     */
    public void draw(SpriteBatch batch) {

        if(hudSelection == 1) {
            batch.draw(game, 0, 0);
        }
        if(hudSelection == 2) {
            batch.draw(shop, 0, 0);
        }
        if(hudSelection == 3) {
            batch.draw(settings, 0, 0);
        }

    }

    /**
     * 0 = No HUD
     * 1 = Game HUD
     * 2 = Shop HUD
     * 3 = Settings HUD
     * @param hudSelection
     */
    public void setHudSelection(int hudSelection) {
        this.hudSelection = hudSelection;
    }
}
