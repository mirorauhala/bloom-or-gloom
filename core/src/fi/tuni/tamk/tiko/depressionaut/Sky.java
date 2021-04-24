package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sky {
    private Texture daySky = new Texture("sky/daySky.png");
    private Texture nightSky = new Texture("sky/nightSky.png");

    public void draw(SpriteBatch batch, GameClock clock) {
        batch.draw(nightSky, 0, 0);
        batch.setColor(1,1,1, clock.getDayOpacity()); // set daySky opacity
        batch.draw(daySky, 0, 0);
        batch.setColor(clock.getDayOpacity()+0.25f,clock.getDayOpacity()+0.25f,clock.getDayOpacity()+0.25f,1); // lighting effect
    }

}
