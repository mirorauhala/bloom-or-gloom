package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;


public class DailyBonus {

    private String [] emotionTips;

    public Texture bonusWindowEN = new Texture("UI/Dailybonus/BonuswindowEN");
    public Texture bonusWindowFI = new Texture("UI/Dailybonus/BonuswindowFI");
    public Texture noBonusEN = new Texture("UI/Dailybonus/NobonusEN");
    public Texture noBonusFI = new Texture("UI/Dailybonus/NobonusFI");

    public void txtToArray() {
        FileHandle handle = Gdx.files.internal("UI/Dailybonus/TipsEN.txt");
        String text = handle.readString();
        emotionTips = text.split("\\r?\\n");
    }

    public void randomTip() {

    }



}
