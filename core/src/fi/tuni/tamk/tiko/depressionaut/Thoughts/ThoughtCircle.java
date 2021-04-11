package fi.tuni.tamk.tiko.depressionaut.Thoughts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ThoughtCircle extends Actor {
    private final Texture posCircle = new Texture("thoughts/positiveCircle.png");
    private final Texture negCircle = new Texture("thoughts/negativeCircle.png");

    private Vector2 position;
    private float scale = 1;
    private float distance = 20;

    public ThoughtCircle(Vector2 headPos, Vector2 bubblePos, int num) {
        position = new Vector2(
            bubblePos.x + ((distance * num) * ((headPos.x - bubblePos.x) / (headPos.x - bubblePos.x + headPos.y - bubblePos.y))),
            bubblePos.y + ((distance * num) * ((headPos.y - bubblePos.y) / (headPos.x - bubblePos.x + headPos.y - bubblePos.y)))
        );
    }

    public Texture getCircleTexture(ThoughtBubble.Emotion emotion) {
        if (emotion == ThoughtBubble.Emotion.POSITIVE) {
            return posCircle;
        } else {
            return negCircle;
        }
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public float getScale() {
        return this.scale;
    }
}
