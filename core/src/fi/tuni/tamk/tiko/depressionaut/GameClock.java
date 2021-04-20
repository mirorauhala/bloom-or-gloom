package fi.tuni.tamk.tiko.depressionaut;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GameClock {

    Date date = new Date();
    Calendar calendarG = new GregorianCalendar();
    int hours, minutes, seconds;
    int lastBubble;
    private ArrayList<Integer> buffs = new ArrayList<>();
    int lastBuffCheck;
    float dayOpacity;

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
        dayNightCycle(false);
    }

    /**
     * Timer for creating thoughtbubbles.
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
        int distance;
        if (debug) {
            if (seconds <= 30) {
                distance = seconds;
            } else {
                distance = 30 - (seconds - 30);
            }
            dayOpacity = (distance * 2.5f) / 30f;
        } else {
            if (hours <= 12) {
                distance = hours;
            } else {
                distance = 12 - (hours - 12);
            }
            dayOpacity = (distance * 2.5f) / 12f;
        }
    }

    /**
     * Returns the dayOpacity value.
     *
     * @return dayOpacity value
     */
    public float getDayOpacity() {
        return dayOpacity;
    }

}
