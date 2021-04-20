package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GameClock {

    Date date = new Date();
    Calendar calendarG = new GregorianCalendar();
    int hours, minutes, seconds;
    int lastBubble = 0;

    public GameClock() {

    }

    public void timer() {
        date.setTime(System.currentTimeMillis());
        calendarG.setTime(date);
        hours = calendarG.get(Calendar.HOUR_OF_DAY);
        minutes = calendarG.get(Calendar.MINUTE);
        seconds = calendarG.get(Calendar.SECOND);
        Gdx.app.debug("Clock", hours + ":" + minutes + ":" + seconds);
    }

    public boolean thoughtBubbleTimer() {
        if (minutes - lastBubble >= 1) {
            lastBubble = minutes;
            return true;
        } else if (minutes < lastBubble) {
            lastBubble = 0;
        }
        return false;
    }

}
