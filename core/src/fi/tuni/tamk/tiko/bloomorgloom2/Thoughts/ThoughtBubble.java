package fi.tuni.tamk.tiko.bloomorgloom2.Thoughts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Class for the thought bubbles that appear around the player every minute.
 *
 * There are two types of thought bubbles that can appear around the player:
 * negative and positive. When on the screen, the negative bubbles will disable
 * the player's ability to tap the screen to earn points until the bubble is
 * popped. The positive bubbles increase the tap multiplier by one for 30
 * seconds and can be stacked.
 */
public class ThoughtBubble extends Actor {
    // Textures
    private final Texture posBubble = new Texture("thoughts/positiveBubble.png");
    private final Texture negBubble = new Texture("thoughts/negativeBubble.png");

    // List containing thought bubbles
    private ThoughtBubble[] thoughts = new ThoughtBubble[3];

    // Values for bubbles.
    private Vector2 position;
    private Emotion emotion;
    private Rectangle hitbox;
    private float scale;

    /**
     * A thought bubble's emotion type.
     */
    public enum Emotion{
        POSITIVE,
        NEGATIVE;
    }

    /**
     * Creates a new bubble when called and when there are less than 3 bubbles
     * on the screen.
     *
     * @param offset Character's position offset to ensure the bubbles
     *               correctly form around the player's head.
     */
    public void createThought(float offset) {
        // Randomize bubble position
        int randInt = 0;
        while(!isListFull()) {
            randInt = (int)(Math.random()*3);
            if (thoughts[randInt] == null) {
                ThoughtBubble temp = new ThoughtBubble();
                temp.position = getPosition(randInt, offset);
                temp.scale = 0.01f;
                // Randomize emotion
                switch ((int)(Math.random()*2)) {
                    case 0: temp.emotion = Emotion.POSITIVE;
                        break;
                    case 1: temp.emotion = Emotion.NEGATIVE;
                        break;
                }
                // Assign hitbox
                temp.hitbox = new Rectangle(temp.position.x,
                        temp.position.y,
                        temp.getBubbleTexture().getWidth(),
                        temp.getBubbleTexture().getHeight());

                thoughts[randInt] = temp;
                break;
            }
        }
    }

    /**
     * Draws the bubbles and calls supporting methods.
     *
     * @param batch SpriteBatch needed for the draw() method.
     */
    public void render(SpriteBatch batch) {
        for (ThoughtBubble bubble : thoughts) {
            if (bubble != null) {
                // draw bubble
                batch.draw(bubble.getBubbleTexture(),
                        bubble.position.x,
                        bubble.position.y,
                        bubble.getBubbleTexture().getWidth() / 2f,
                        bubble.getBubbleTexture().getHeight() / 2f,
                        bubble.getBubbleTexture().getWidth(),
                        bubble.getBubbleTexture().getHeight(),
                        bubble.scale,
                        bubble.scale,
                        0,
                        0,
                        0,
                        bubble.getBubbleTexture().getWidth(),
                        bubble.getBubbleTexture().getHeight(),
                        false,
                        false);

                bubble.scale();
            }
        }
    }

    /**
     * Scales the textures of the bubbles up until they reach 100% scale.
     */
    public void scale() {
        if (this.scale <= 1) {
            this.scale *= 1.1;
        }
    }

    /**
     * Checks if given coordinates are inside a bubble and returns
     * corresponding emotion.
     * @param x touch position x
     * @param y touch position y
     * @return Returns emotion of the bubble hit.
     */
    public Emotion checkForClear(float x, float y) {
        Emotion tempEmotion = null;
        for (int i = 0; i < thoughts.length; i++) {
            if (thoughts[i] != null) {
                if (thoughts[i].hitbox.contains(x, y)) {
                    tempEmotion = thoughts[i].emotion;
                    thoughts[i] = null;
                }
            }
        }
        return tempEmotion;
    }

    /**
     * Returns the coordinates of one of the possible positions for the bubbles.
     *
     * @param index A bubble's index.
     * @param offset Character's position offset to ensure the bubbles
     *               correctly form around the player's head.
     * @return Returns the coordinates.
     */
    public Vector2 getPosition(int index, float offset) {
        switch (index) {
            case 0: return new Vector2(180, 1920 - 1380 + offset);
            case 1: return new Vector2(217, 1920 - 965 + offset);
            case 2: return new Vector2(668, 1920 - 773 + offset);
        }
        return new Vector2();
    }

    /**
     * Checks if the list containing the bubbles is full. (max. 3)
     * @return Returns boolean depending on the results.
     */
    public boolean isListFull() {
        for (ThoughtBubble thought : thoughts) {
            if (thought == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the texture of a bubble depending on its emotion.
     *
     * @return The texture of the bubble.
     */
    public Texture getBubbleTexture() {
        if (this.emotion == Emotion.POSITIVE) {
            return posBubble;
        } else {
            return negBubble;
        }
    }

    /**
     * Returns the number of negative thought bubbles.
     *
     * @return amount
     */
    public int getNegThoughtsAmount() {
        int amount = 0;
        for (ThoughtBubble thought : thoughts) {
            if (thought != null && thought.emotion == Emotion.NEGATIVE) {
                amount++;
            }
        }
        return amount;
    }
}
