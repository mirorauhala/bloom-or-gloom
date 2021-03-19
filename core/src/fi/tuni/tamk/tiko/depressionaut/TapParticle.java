package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class TapParticle {
    private Texture particle;
    private ArrayList<TapParticle> Particles = new ArrayList<>();

    // Particle scaling:
    private float scale = 0;

    // Particle rotating:
    private float rotation;

    public TapParticle() {
        particle = new Texture("visuals.png");
    }

    public void createParticle() {

    }

    public void dispose() {
        particle.dispose();
    }
}
