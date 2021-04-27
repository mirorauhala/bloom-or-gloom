package fi.tuni.tamk.tiko.bloomorgloom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Holds user's in-game inventory. Has methods for storing the items into the inventory.
 */
public class Inventory {
    Preferences prefs;

    public Inventory() {
        this.prefs = Gdx.app.getPreferences("inventory");
    }

    /**
     * Add an item to the user's inventory.
     *
     * @param key String The name of the item.
     * @param value int The level of the stored item.
     */
    public void set(String key, int value) {
        prefs.putInteger(key, value);
        prefs.flush();
    }

    /**
     * Get an item from the user's inventory.
     *
     * If the item does not exist, a zero is returned.
     *
     * @param key String Name of the item to fetch.
     * @return int Item's level
     */
    public int get(String key) {
        return prefs.getInteger(key);
    }

    /**
     * Removes all inventory prefs
     */
    public void removeInventoryPrefs() {
        prefs.clear();
        prefs.flush();
    }

}
