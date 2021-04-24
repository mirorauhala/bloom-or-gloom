package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class GameClock {
    public Preferences prefs = Gdx.app.getPreferences("general");

    Date date = new Date();
    Calendar calendarG = new GregorianCalendar();
    int hours, minutes, seconds;
    int lastBubble;
    int lastSkyObject;
    private ArrayList<Integer> buffs = new ArrayList<>();
    int lastBuffCheck;
    float dayOpacity;
    public String strDate;

    /**
     * Constructor sets necessary values.
     */
    public GameClock() {
        calendarG.setTime(date);
        hours = calendarG.get(Calendar.HOUR_OF_DAY);
        minutes = calendarG.get(Calendar.MINUTE);
        seconds = calendarG.get(Calendar.SECOND);
        lastBubble = seconds;
        lastBuffCheck = seconds;
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        strDate = formatter.format(date);

    }

    public boolean isFirstOfTheDay() {
        return !strDate.equals(prefs.getString("lastLogin"));
    }
    public void setLastLogin() {
        prefs.putString("lastLogin", strDate);
        prefs.flush();
    }


    /**
     * Main "render loop" updates timers.
     */
    public void timer() {
        date.setTime(System.currentTimeMillis());
        calendarG.setTime(date);
        hours = calendarG.get(Calendar.HOUR_OF_DAY);
        minutes = calendarG.get(Calendar.MINUTE);
        seconds = calendarG.get(Calendar.SECOND);
        //Gdx.app.debug("Clock", hours + ":" + minutes + ":" + seconds);

        buffTimer();
        dayNightCycle(true);
    }

    /**
     * Timer for creating thought bubbles.
     *
     * Debug mode creates bubbles every 5 seconds and non-debug mode every 1 minute.
     *
     * @param debug Debug boolean
     * @return Returns true if an appropriate amount of time has passed since last
     *         thought bubble was created.
     */
    public boolean thoughtBubbleTimer(boolean debug) {
        if (debug) {
            if (seconds - lastBubble >= 5) {
                lastBubble = seconds;
                return true;
            } else if (seconds < lastBubble) {
                lastBubble = 0;
            }
        } else {
            if (minutes - lastBubble >= 1) {
                lastBubble = minutes;
                return true;
            } else if (minutes < lastBubble) {
                lastBubble = 0;
            }
        }
        return false;
    }

    /**
     * Timer for creating clouds and birds on the sky layer.
     *
     * @return Returns true if an appropriate amount of time has passed since last
     *         skyObject was created.
     */
    public boolean skyObjectTimer() {
        if (seconds - lastSkyObject >= Math.random()*2+6) {
            lastSkyObject = seconds;
            return true;
        } else if (seconds < lastSkyObject) {
            lastSkyObject = 0;
        }
        return false;
    }

    /**
     * Timer that for thought bubble buffs.
     *
     * Removes "1" from each buff's timer every second.
     */
    public void buffTimer() {
        if (seconds - lastBuffCheck >= 1) {
            lastBuffCheck = seconds;
            for (int i = 0; i < buffs.size(); i++) {
                buffs.set(i, buffs.get(i)-1);
                if (buffs.get(i) <= 0) {
                    buffs.remove(i);
                }
            }
        } else if (seconds < lastBuffCheck) {
            lastBuffCheck = 0;
        }
    }

    /**
     * Adds a new buff to the buff list.
     *
     * @param duration Duration of the buff in seconds.
     */
    public void addBuff(int duration) {
        buffs.add(duration);
    }

    /**
     * Return the amount of buffs in the list of buffs.
     *
     * @return Amount of buffs.
     */
    public int amountOfBuffs() {
        return buffs.size();
    }

    /**
     * Calculates dayNightCycle percentage.
     *
     * Calculates how far into a day the system clock is and returns a corresponding
     * percentage. The returned percentage value is then used as an opacity value for
     * the daySky texture and the lighting effect in the room.
     * When debug is true, the dayNightCycle is calculated using seconds instead of hours.
     *
     * @param debug Debug boolean
     */
    public void dayNightCycle(boolean debug) {
        float distance;
        int timeUnit;
        float max;
        if (debug) {
            timeUnit = seconds - 5;
            max = 60 / 2f;
        } else {
            timeUnit = hours - 2;
            max = 24 / 2f;
        }

        if (timeUnit <= max) {
            distance = timeUnit;
        } else {
            distance = max - (timeUnit - max);
        }

        dayOpacity = distance / max;
    }

    /**
     * Returns the dayOpacity value.
     *
     * @return dayOpacity value
     */
    public float getDayOpacity() {
        Gdx.app.debug("Clock", "h: " + (24f * (seconds / 60f)));
        if (dayOpacity < 0.25) {
            return 0;
        } else if (dayOpacity < 0.5) {
            return (dayOpacity - 0.25f) / 0.25f;
        } else {
            return 1;
        }
    }

}
