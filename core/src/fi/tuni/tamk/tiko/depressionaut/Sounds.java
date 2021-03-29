package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Sounds {
    public com.badlogic.gdx.audio.Sound click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));

    public void clicksoundPlay() {
        click.play(0.1f, MathUtils.random(0.95f, 1.05f), 0);
    }
}
