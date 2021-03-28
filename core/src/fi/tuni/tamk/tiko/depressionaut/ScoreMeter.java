package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreMeter {

    private OrthographicCamera camera;
    private MyGdxGame game;

    public Texture meterBase = new Texture("UI/UpperHUDbase.png");
    public Texture meterTexture;
    public ScoreCounter score = new ScoreCounter();


    public List<Texture> meterTextures = Arrays.asList(
            new Texture("UI/Meters/Meter0.png"),
            new Texture("UI/Meters/Meter1.png"),
            new Texture("UI/Meters/Meter2.png"),
            new Texture("UI/Meters/Meter3.png"),
            new Texture("UI/Meters/Meter4.png"),
            new Texture("UI/Meters/Meter5.png"),
            new Texture("UI/Meters/Meter6.png"),
            new Texture("UI/Meters/Meter7.png"),
            new Texture("UI/Meters/Meter8.png")
    );

    public void setMeterTexture(int amountOfBarsInMeter) {
        meterTexture = meterTextures.get(amountOfBarsInMeter);
    }

    public Texture getMeterTexture() {
        return meterTexture;
    }

    public void determineMeterHeight() {
        if(score.getScore() < 100) {
            setMeterTexture(0);
        }
        else if(score.getScore() < 200) {
            setMeterTexture(1);
        }
        else if(score.getScore() < 300) {
            setMeterTexture(2);
        }
        else if(score.getScore() < 400) {
            setMeterTexture(3);
        }
        else if(score.getScore() < 500) {
            setMeterTexture(4);
        }
        else if(score.getScore() < 600) {
            setMeterTexture(5);
        }
        else if(score.getScore() < 700) {
            setMeterTexture(6);
        }
        else if(score.getScore() < 800) {
            setMeterTexture(7);
        }
        else {
            setMeterTexture(8);
        }

    }

    public ScoreMeter(MyGdxGame game) {
        this.game = game;
        this.camera = game.camera;
    }

    public void draw(SpriteBatch batch) {
        determineMeterHeight();
        batch.draw(meterBase, 0, 0);
        batch.draw(meterTexture, 0, 0);
    }
}
