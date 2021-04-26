package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;


/**
 * ScoreMeter houses everything for the upper UI in the game screen
 */
public class ScoreMeter {

    private OrthographicCamera camera;
    private MyGdxGame game;
    public ScoreCounter score;
    public Texture meterBase = new Texture("UI/UpperHUDbase.png");
    public Texture meterTexture;
    public Texture meterBackground;

    private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private Rectangle meterFiller;
    private Texture meter;
    private Texture meterColor;

    public BitmapFont font;

    public Texture getMeterTexture() {
        return meterTexture;
    }


    public ScoreMeter(MyGdxGame game) {
        this.game = game;
        this.camera = game.camera;
        this.score = game.score;
        font = new BitmapFont(Gdx.files.internal("UI/Quicksand_white.fnt"));
        font.setUseIntegerPositions(false);

        meterFiller = new Rectangle();
        meterBackground = new Texture("UI/meterBackground.png");
        meter = new Texture("UI/meter.png");
        meterColor = new Texture("UI/meterColor.png");
        createFillerRectangles();
    }

    public void draw(SpriteBatch batch) {
        //determineMeterHeight();

        batch.draw(meterBase, 0, 0);
        batch.draw(meterBackground, 22, 1920 - 358, meter.getWidth(), meter.getHeight());
        for (int i = 0; i <= meter.getHeight() * getProgressToNextHappinessLevel(); i++) {
            batch.draw(meterColor,
                    rectangles.get(i).getX(),
                    rectangles.get(i).getY(),
                    rectangles.get(i).getWidth(),
                    rectangles.get(i).getHeight());
        }
        batch.draw(meter, 22, 1920 - 358, meter.getWidth(), meter.getHeight());
        font.getData().setScale(3.5f, 3.5f);
        font.draw(batch, score.getRationalizedScore(), 540, 1875);
        font.getData().setScale(2f, 2f);
        font.draw(batch, "e/s " + score.getPassiveIncome(), 410, 1740);

    }

    public float getProgressToNextHappinessLevel() {
        // percentage of the progress to next happiness level:
        float progress = score.getScore() / score.getScoreForNextLevel(score.getHappinessLevel());
        if (progress > 1) {
            return 1;
        } else {
            return progress;
        }
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
