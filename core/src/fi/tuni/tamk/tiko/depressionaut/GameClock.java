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
        calendarG.setTime(date);
        hours = calendarG.get(Calendar.HOUR_OF_DAY);
        minutes = calendarG.get(Calendar.MINUTE);
        seconds = calendarG.get(Calendar.SECOND);
        lastBubble = seconds;
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
        if (seconds - lastBubble >= 5) {
            lastBubble = seconds;
            return true;
        } else if (seconds < lastBubble) {
            lastBubble = 0;
        }
        return false;
    }

}
