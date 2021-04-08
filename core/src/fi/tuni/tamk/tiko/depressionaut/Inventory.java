package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.HashMap;

public class Inventory {
    private final MyGdxGame game;
    private HashMap<String, Integer> inventory;
    Preferences prefs;

    public Inventory(MyGdxGame game) {
        this.game = game;
        this.prefs = Gdx.app.getPreferences("inventory");
    }

    public void set(String key, Integer value) {
        prefs.putInteger(key, value);
        prefs.flush();
    }

    public int get(String key) {
        return prefs.getInteger(key);
    }

}
