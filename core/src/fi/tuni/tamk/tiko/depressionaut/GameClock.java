package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;

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

    public GameClock() {
        calendarG.setTime(date);
        hours = calendarG.get(Calendar.HOUR_OF_DAY);
        minutes = calendarG.get(Calendar.MINUTE);
        seconds = calendarG.get(Calendar.SECOND);
        lastBubble = seconds;
        lastBuffCheck = seconds;
    }

    public void timer() {
        date.setTime(System.currentTimeMillis());
        calendarG.setTime(date);
        hours = calendarG.get(Calendar.HOUR_OF_DAY);
        minutes = calendarG.get(Calendar.MINUTE);
        seconds = calendarG.get(Calendar.SECOND);
        //Gdx.app.debug("Clock", hours + ":" + minutes + ":" + seconds);

        buffTimer();
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

    public void addBuff(int duration) {
        buffs.add(duration);
    }

    public int amountOfBuffs() {
        return buffs.size();
    }

}
