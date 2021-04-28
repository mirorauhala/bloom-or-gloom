package fi.tuni.tamk.tiko.bloomorgloom2.Sky;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fi.tuni.tamk.tiko.bloomorgloom2.GameClock;

/**
 * Class for rendering everything on the sky layer.
 */
public class Sky {
    // Textures for the sky
    private Texture daySky = new Texture("sky/daySky.png");
    private Texture nightSky = new Texture("sky/nightSky.png");

    // Contains all objects moving in the sky
    SkyObject skyObject = new SkyObject();

    /**
     * Draws everything on the sky layer.
     *
     * @param batch SpriteBatch needed for the draw() method.
     * @param clock GameClock object needed for timing skyObject creation.
     */
    public void draw(SpriteBatch batch, fi.tuni.tamk.tiko.bloomorgloom2.GameClock clock) {
        // Creates a new object in the sky every 6-8 seconds.
        if (clock.skyObjectTimer()) {
            skyObject.createSkyObject();
        }

        batch.draw(nightSky, 0, 0); // draw night sky
        batch.setColor(1,1,1, clock.getDayOpacity()); // set daySky opacity

        // Draw the day sky on top of the night sky. Depending on the time of the day, the day
        // sky's opacity and coverage of the night sky will be different.
        batch.draw(daySky, 0, 0);

        batch.setColor(1,1,1, 1); // reset batch opacity

        skyObject.draw(batch); // draw sky objects
    }

    public float getSkyOpacity(GameClock clock) {
        if (clock.getDayOpacity() < 0.25) {
            return 0;
        } else if (clock.getDayOpacity() < 0.75) {
            return (clock.getDayOpacity() - 0.25f) / 0.5f;
        } else {
            return 1;
        }
    }

}
