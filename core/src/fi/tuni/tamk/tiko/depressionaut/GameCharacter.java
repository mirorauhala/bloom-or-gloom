package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;

public class GameCharacter {
    public ArrayList<ArrayList<Texture>> lists = new ArrayList<ArrayList<Texture>>();

    private ArrayList<Texture> bodies = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/body/tier1.png"),
                    new Texture("character/body/tier2.png")
            )
    );

    public Texture head = new Texture("character/head.png");

    private ArrayList<Texture> smiles = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/smile/tier1.png"),
                    new Texture("character/smile/tier2.png")
            )
    );

    private ArrayList<Texture> hands = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/hands/empty.png"),
                    new Texture("character/hands/phone.png")
            )
    );

    private ArrayList<Texture> eyes = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/eyes/open.png"),
                    new Texture("character/eyes/closed.png")
            )
    );

    private int blinkTimer = 0;

    public enum heldItem{
        EMPTY,
        PHONE
    }

    private enum textureIndex{
        BODY,
        SMILE,
        HANDS
    }

    public GameCharacter() {
        lists.add(bodies);
        lists.add(smiles);
        lists.add(hands);
    }

    public void draw(SpriteBatch batch, int tier, heldItem item) {
        // draw head
        batch.draw(head,
                getHeadPosition(tier).x,
                getHeadPosition(tier).y);

        // draw body
        for (int lIndex = 0; lIndex <= 1; lIndex++) {
            batch.draw(lists.get(lIndex).get(tier-1),
                    0,
                    0);
        }

        // draw hands
        batch.draw(lists.get(getTextureIndex(textureIndex.HANDS)).get(getItemIndex(item)),
                0,
                getTierOffset(tier));

        // draw eyes
        blink(batch, tier);
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

    public int getItemIndex(heldItem name) {
        switch (name) {
            case EMPTY: return 0;
            case PHONE: return 1;
        }
        return -1;
    }

    public int getTextureIndex(textureIndex name) {
        switch (name) {
            case BODY: return 0;
            //case HEAD: return 1;
            case SMILE: return 1;
            case HANDS: return 2;
        }
        return -1;
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
