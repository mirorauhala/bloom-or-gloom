package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * A class containing all of the settings used through out the game.
 *
 * This does violate the rule of "Don't Repeat Yourself", but the added
 * visibility into the keys used, and the fact that this allows IDEs
 * to autocomplete better, do improve the overall developer experience.
 */
public class Settings {
    private final Preferences prefs;

    public Settings() {
        prefs = Gdx.app.getPreferences("bloom-or-gloom-settings");
    }

    /**
     * Get the user's selected language.
     * @return String
     */
    public String getLang() {
        return prefs.getString("language");
    }

    /**
     * Set the user's selected language.
     */
    public void setLang(String value) {
        prefs.putString("language", value);
        prefs.flush();
    }

    /**
     * Are the sound on or off?
     * @return boolean
     */
    public boolean getSound() {
        return prefs.getBoolean("sound");
    }

    /**
     * Set the sound on or off.
     * @return boolean
     */
    public void setSound(boolean value) {
        prefs.putBoolean("sound", value);
        prefs.flush();
    }

    /**
     * Is music on or off?
     * @return boolean
     */
    public boolean getMusic() {
        return prefs.getBoolean("music");
    }

    /**
     * Set music on or off?
     */
    public void setMusic(boolean value) {
        prefs.putBoolean("music", value);
        prefs.flush();
    }

    /**
     * Has the user seen the startup-comic screen?
     * @return boolean
     */
    public boolean getHasSeenComic() {
        return prefs.getBoolean("has-seen-comic");
    }

    /**
     * Set has the user seen the startup-comic screen.
     */
    public void setHasSeenComic(boolean value) {
        prefs.putBoolean("has-seen-comic", value);
        prefs.flush();
    }

    /**
     * Get the date the player was last seen.
     * @return String
     */
    public String getLastSeen() {
        return prefs.getString("last-seen");
    }

    /**
     * Set the date the player was last seen.
     */
    public void setLastSeen(String value) {
        prefs.putString("last-seen", value);
        prefs.flush();
    }

    /**
     * Get the bonus day date.
     * @return String
     */
    public String getBonusDay() {
        return prefs.getString("bonus-day");
    }

    /**
     * Set the bonus day date.
     */
    public void setBonusDay(String value) {
        prefs.putString("bonus-day", value);
        prefs.flush();
    }
}
