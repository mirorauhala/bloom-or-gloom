package fi.tuni.tamk.tiko.depressionaut.Sky;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;
import java.util.List;

import fi.tuni.tamk.tiko.depressionaut.GameClock;

public class Sky {
    private Texture daySky = new Texture("sky/daySky.png");
    private Texture nightSky = new Texture("sky/nightSky.png");
    private List<String> cloudTextures = Arrays.asList(
            "cosmetics/clouds/v1.png",
            "cosmetics/clouds/v2.png",
            "cosmetics/clouds/v3.png",
            "cosmetics/clouds/v4.png",
            "cosmetics/clouds/v5.png",
            "cosmetics/clouds/v6.png",
            "cosmetics/clouds/v7.png",
            "cosmetics/clouds/v8.png",
            "cosmetics/clouds/v9.png"
    );

    

    public void draw(SpriteBatch batch, GameClock clock) {
        batch.draw(nightSky, 0, 0);
        batch.setColor(1,1,1, clock.getDayOpacity()); // set daySky opacity
        batch.draw(daySky, 0, 0);
        batch.setColor(clock.getDayOpacity()+0.25f,clock.getDayOpacity()+0.25f,clock.getDayOpacity()+0.25f,1); // lighting effect
    }

    public void moveClouds() {

    }

}
