package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

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

    private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private Rectangle meterFiller;
    private Texture meter;
    private Texture meterColor;

    public BitmapFont font;

    public Texture getMeterTexture() {
        return meterTexture;
    }

    /**
     * Determines the amount of bars the meter should have relative the players score
     *
     * will get changed when the happiness level is invented
     */
    /*
    public void determineMeterHeight() {
        int amountOfScoreForNextLevel = 0;

        switch(score.getHappinessLevel()) {
            case 0:
                amountOfScoreForNextLevel = score.getScoreForNextLevel(0);
                break;
            case 1:
                amountOfScoreForNextLevel = score.getScoreForNextLevel(1);
                break;
            case 2:
                amountOfScoreForNextLevel = score.getScoreForNextLevel(2);
                break;
            case 3:
                amountOfScoreForNextLevel = score.getScoreForNextLevel(3);
                break;
            case 4:
                amountOfScoreForNextLevel = score.getScoreForNextLevel(4);
                break;
        }


        if(score.getScore() < amountOfScoreForNextLevel / 8) {
            setMeterTexture(0);
        }
        else if(score.getScore() < (amountOfScoreForNextLevel / 8) * 2) {
            setMeterTexture(1);
        }
        else if(score.getScore() < (amountOfScoreForNextLevel / 8) * 3) {
            setMeterTexture(2);
        }
        else if(score.getScore() < (amountOfScoreForNextLevel / 8) * 4) {
            setMeterTexture(3);
        }
        else if(score.getScore() < (amountOfScoreForNextLevel / 8) * 5) {
            setMeterTexture(4);
        }
        else if(score.getScore() < (amountOfScoreForNextLevel / 8) * 6) {
            setMeterTexture(5);
        }
        else if(score.getScore() < (amountOfScoreForNextLevel / 8) * 7) {
            setMeterTexture(6);
        }
        else if(score.getScore() < amountOfScoreForNextLevel) {
            setMeterTexture(7);
        }
    }

     */

    public ScoreMeter(MyGdxGame game) {
        this.game = game;
        this.camera = game.camera;
        this.score = game.score;
        font = new BitmapFont(Gdx.files.internal("font/Quicksand.fnt"));
        font.setUseIntegerPositions(false);

        meterFiller = new Rectangle();
        meter = new Texture("UI/meter.png");
        meterColor = new Texture("UI/meterColor.png");
        createFillerRectangles();
    }

    public void draw(SpriteBatch batch) {
        //determineMeterHeight();

        batch.draw(meterBase, 0, 0);
        for (int i = 0; i <= meter.getHeight() * getProgressToNextTier(); i++) {
            batch.draw(meterColor,
                    rectangles.get(i).getX(),
                    rectangles.get(i).getY(),
                    rectangles.get(i).getWidth(),
                    rectangles.get(i).getHeight());
        }
        batch.draw(meter, 22, 1920 - 358, meter.getWidth(), meter.getHeight());
        font.getData().setScale(3f, 3f);
        font.draw(batch, score.getRationalizedScore(), 520, 1870);
        font.getData().setScale(2f, 2f);
        font.draw(batch, "e/s " + score.getPassiveIncome(), 410, 1740);

    }

    public float getProgressToNextTier() {
        return score.getScore() / score.getScoreForNextLevel(score.getHappinessLevel());
    }

    public float getFillerWidth(int index) {
        // calculate distance to the center of the circle:
        float d = Math.abs(meter.getHeight()/2 - index);
        return (float) (2 * Math.sqrt(Math.pow(meter.getWidth()/2f, 2) - Math.pow(d, 2)));
    }

    public void createFillerRectangles() {
        for (int i = 0; i <= meter.getHeight(); i++) {
            rectangles.add(new Rectangle(
                    22 + (meter.getWidth() - getFillerWidth(i))/2f,
                    1920 - 358 + i,
                    getFillerWidth(i),
                    1
            ));
        }
    }
}
