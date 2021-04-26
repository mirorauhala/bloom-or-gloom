package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GameCharacter {

    public Texture head = new Texture("character/head/head.png");
    private Shirts shirts = new Shirts();
    private int shirtIndex;
    private int sleeveIndex;
    private int hatIndex;

    public HashMap<heldItem, Texture> hands = new HashMap<heldItem, Texture>();

    private ArrayList<Texture> bodies = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/body/tier1/tier1.png"),
                    new Texture("character/body/tier2/tier2.png"),
                    new Texture("character/body/tier3/tier3.png"),
                    new Texture("character/body/tier4/tier4.png"),
                    new Texture("character/body/tier5/tier5.png")
            )
    );

    private List<Texture> hats = Arrays.asList(
            new Texture("character/hats/t1v0.png"),
            new Texture("character/hats/t3v1.png"),
            new Texture("character/hats/t3v2.png"),
            new Texture("character/hats/t3v3.png"),
            new Texture("character/hats/t4v4.png"),
            new Texture("character/hats/t4v5.png"),
            new Texture("character/hats/t4v6.png"),
            new Texture("character/hats/t5v7.png"),
            new Texture("character/hats/t5v8.png"),
            new Texture("character/hats/t5v9.png")
    );

    private ArrayList<Texture> smiles = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/smile/tier1.png"),
                    new Texture("character/smile/tier2.png"),
                    new Texture("character/smile/tier3.png"),
                    new Texture("character/smile/tier4.png"),
                    new Texture("character/smile/tier5.png")
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
        PHONE,
        BOOK,
        LAPTOP,
        COMPUTER
    }

    private heldItem currentItem = heldItem.EMPTY;
    private int tier;

    private int blinkTimer = 0;

    public GameCharacter() {
        hands.put(heldItem.EMPTY, new Texture("character/hands/empty.png"));
        hands.put(heldItem.PHONE, new Texture("character/hands/phone.png"));
        hands.put(heldItem.BOOK, new Texture("character/hands/book.png"));
        hands.put(heldItem.LAPTOP, new Texture("character/hands/laptop.png"));
    }

    public void draw(SpriteBatch batch) {
        // draw body
        batch.draw(bodies.get(getTier()),
                0,
                0);
        batch.draw(shirts.shirts.get(tier).get(shirtIndex),
                0,
                0);

        // draw head
        batch.draw(head,
                getHeadPosition(getTier()).x,
                getHeadPosition(getTier()).y);

        // draw mouth:
        batch.draw(smiles.get(getTier()),
                0,
                0);

        // draw eyes
        blink(batch, getTier());

        // draw hands
        batch.draw(hands.get(currentItem),
                0,
                getTierOffset(getTier()));
        batch.draw(shirts.sleeves.get(getSleeveIndex()).get(shirtIndex),
                0,
                getTierOffset(getTier()));

        // draw hats
        batch.draw(hats.get(hatIndex),
                0,
                getTierOffset(getTier()));
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

    public heldItem getItem() {
        return currentItem;
    }

    public void setShirtTier(int index) {
        shirtIndex = index;
    }

    public int getShirtTier() {
        return shirtIndex;
    }

    public void setHatIndex(int index) {
        hatIndex = index;
    }

    public int getHatIndex() {
        return hatIndex;
    }

    public void setSleeveIndex(int index) {
        sleeveIndex = index;
    }

    public int getSleeveIndex() {
        switch (currentItem) {
            case EMPTY: return 0;
            case PHONE: return 1;
            case BOOK:
            case LAPTOP:
            case COMPUTER: return 2;
        }
        return -1;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    public int getTierOffset(int tier) {
        return (int) (getHeadPosition(tier).y - getHeadPosition(0).y);
    }

    public float getStandingOffset() {
        if (getTier() >= 4) {
            return 100;
        } else {
            return 0;
        }
    }

    public Vector2 getHeadPosition(int tier) {
        switch (tier) {
            case 0: return new Vector2(514,1920 - 1336);
            case 1: return new Vector2(514,1920 - 1285);
            case 2:
            case 3:
                return new Vector2(514,1920 - 1271);
            case 4: return new Vector2(514,1920 - 1196);
        }
        return new Vector2();
    }

    public Vector2 getHeadCenter(int tier) {
        return new Vector2(
                getHeadPosition(tier).x + (head.getWidth() / 2f),
                getHeadPosition(tier).y + head.getHeight() / 2f
        );
    }

}
