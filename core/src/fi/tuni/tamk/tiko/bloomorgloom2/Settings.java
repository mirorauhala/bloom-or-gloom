package fi.tuni.tamk.tiko.bloomorgloom2;

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
        String lang = prefs.getString("language");
        return !lang.equals("") ? lang : "fi";
    }

    /**
     * Set the user's selected language.
     * @param value String
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
     * @param value boolean
     */
    public void setSound(boolean value) {
        prefs.putBoolean("sound", value);
        prefs.flush();
    }

    /**
     * checks if sound settings have been set
     * @return true or false
     */
    public boolean containsSound() {
        return prefs.contains("sound");
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
     * @param value boolean
     */
    public void setMusic(boolean value) {
        prefs.putBoolean("music", value);
        prefs.flush();
    }
    /**
     * checks if music settings have been set
     * @return true or false
     */
    public boolean containsMusic() {
        return prefs.contains("music");
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
     * @param value boolean
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
     * @param value boolean
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
     * @param value String
     */
    public void setBonusDay(String value) {
        prefs.putString("bonus-day", value);
        prefs.flush();
    }

    /**
     * Get the bonus day date.
     * @return String
     */
    public String getFirstDay() {
        return prefs.getString("first-day");
    }

    /**
     * Set the bonus day date.
     * @param value String
     */
    public void setFirstDay(String value) {
        prefs.putString("first-day", value);
        prefs.flush();
    }

    public void reset() {
        prefs.remove("has-seen-comic");
        prefs.remove("bonus-day");
    }
}
