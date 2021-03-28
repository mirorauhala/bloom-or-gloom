package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * ScoreMeter houses everything for the upper UI in the game screen
 */
public class ScoreMeter {

    private OrthographicCamera camera;
    private MyGdxGame game;
    public ScoreCounter score;
    public Texture meterBase = new Texture("UI/UpperHUDbase.png");
    public Texture meterTexture;

    public BitmapFont font;


    /**
     * meterTextures houses all the increments for the happiness meter
     */
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

    /**
     * Determines the amount of bars the meter should have relative the players score
     *
     * will get changed when the happiness level is invented
     */
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
        this.score = game.score;
        font = new BitmapFont(Gdx.files.internal("font/Quicksand.fnt"));
        font.setUseIntegerPositions(false);
    }

    public void draw(SpriteBatch batch) {
        determineMeterHeight();
        batch.draw(meterBase, 0, 0);
        batch.draw(meterTexture, 0, 0);
        font.getData().setScale(3f, 3f);
        font.draw(batch, score.getRationalizedScore(), 520, 1870);
        font.getData().setScale(2f, 2f);
        font.draw(batch, "e/s " + score.getPassiveIncome(), 410, 1740);
    }
}
