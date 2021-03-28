package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameCharacter {

    public Texture head = new Texture("character/head.png");

    public HashMap<heldItem, Texture> hands = new HashMap<heldItem, Texture>();

    private ArrayList<Texture> bodies = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/body/tier1.png"),
                    new Texture("character/body/tier2.png")
            )
    );

    private ArrayList<Texture> smiles = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/smile/tier1.png"),
                    new Texture("character/smile/tier2.png")
            )
    );


    private ArrayList<Texture> eyes = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/eyes/open.png"),
                    new Texture("character/eyes/closed.png")
            )
    );

    public enum heldItem{
        EMPTY,
        PHONE
    }

    private heldItem currentItem = heldItem.EMPTY;
    private int tier = 1;

    private int blinkTimer = 0;

    public GameCharacter() {
        hands.put(heldItem.EMPTY, new Texture("character/hands/empty.png"));
        hands.put(heldItem.PHONE, new Texture("character/hands/phone.png"));
    }

    public void draw(SpriteBatch batch) {
        // draw head
        batch.draw(head,
                getHeadPosition(getTier()).x,
                getHeadPosition(getTier()).y);

        // draw mouth:
        batch.draw(smiles.get(getTier()-1),
                0,
                0);

        // draw body
        batch.draw(bodies.get(getTier()-1),
                0,
                0);

        // draw hands
        batch.draw(hands.get(currentItem),
                0,
                getTierOffset(getTier()));

        // draw eyes
        blink(batch, getTier());
    }

    public void blink(SpriteBatch batch, int tier) {
        blinkTimer++;
        if(blinkTimer > 400) {
            blinkTimer = 0;
        }
        if(blinkTimer > 390) {
            batch.draw(eyes.get(1), 0, getTierOffset(tier));
        } else {
            batch.draw(eyes.get(0), 0, getTierOffset(tier));
        }

    }

    public void setItem(heldItem item) {
        currentItem = item;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    public int getTierOffset(int tier) {
        return (int) (getHeadPosition(tier).y - getHeadPosition(1).y);
    }

    public Vector2 getHeadPosition(int tier) {
        switch (tier) {
            case 1: return new Vector2(514,1920 - 1336);
            case 2: return new Vector2(514,1920 - 1285);
        }
        return new Vector2();
    }

}
