package fi.tuni.tamk.tiko.depressionaut;

/*
    head offset values:
    - tier 1: down 65px
    - tier 2: down 15px
 */

/* TODO: implement the following:
public void blink() {
        eyestest++;
        if(eyestest > 400) {
        eyestest = 0;
        }
        if(eyestest > 390) {
        batch.draw(eyes.get(1), x, y);
        } else {
        batch.draw(eyes.get(0), x, y);
        }

        }
*/

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Arrays;

public class GameCharacter {
    public ArrayList<ArrayList<Texture>> lists = new ArrayList<ArrayList<Texture>>();

    ArrayList<Texture> bodies = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/body/tier1.png"),
                    new Texture("character/body/tier2.png")
            )
    );

    ArrayList<Texture> heads = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/head/tier1.png"),
                    new Texture("character/head/tier2.png")
            )
    );

    ArrayList<Texture> smiles = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/smile/tier1.png"),
                    new Texture("character/smile/tier2.png")
            )
    );

    ArrayList<Texture> hands = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/hands/empty.png"),
                    new Texture("character/hands/phone.png")
            )
    );

    ArrayList<Texture> eyes = new ArrayList<Texture>(
            Arrays.asList(
                    new Texture("character/eyes/open.png"),
                    new Texture("character/eyes/closed.png")
            )
    );


    public int tier = 0;
    public int handTier = 0;

    private enum heldItem{
        EMPTY,
        PHONE
    }

    private enum textureIndex{
        BODY,
        HEAD,
        SMILE,
        HANDS
    }

    public GameCharacter() {
        lists.add(bodies);
        lists.add(heads);
        lists.add(smiles);
        lists.add(hands);
    }

    public void draw(SpriteBatch batch) {
        // draw body
        for (int lIndex = 0; lIndex <= 2; lIndex++) {
            batch.draw(lists.get(lIndex).get(getTier()),
                    0,
                    0);
        }

        /*
        // draw hands
        batch.draw(lists.get(getTextureIndex(textureIndex.HANDS)).get(getItemIndex(heldItem.EMPTY)),
                0,
                getTierOffset(getTier()));

         */

        // draw face
        batch.draw(eyes.get(0),
                0,
                getTierOffset(getTier()));
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getTier() {
        return tier - 1;
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
            case HEAD: return 1;
            case SMILE: return 2;
            case HANDS: return 3;
        }
        return -1;
    }

    public int getTierOffset(int tier) {
        switch (tier + 1) {
            case 1: return -65;
            case 2: return 0;
        }
        return 0;
    }

}
