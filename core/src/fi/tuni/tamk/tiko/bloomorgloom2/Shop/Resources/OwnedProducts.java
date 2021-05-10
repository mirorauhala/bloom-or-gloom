package fi.tuni.tamk.tiko.bloomorgloom2.Shop.Resources;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class for owned products.
 */
public class OwnedProducts {
    private HashMap<String, ArrayList<Integer>> products = new HashMap<>();

    public HashMap<String, ArrayList<Integer>> getProducts() {
        return products;
    }

    /**
     * Sets owned products.
     *
     * @param products List of owned products.
     */
    public void setProducts(HashMap<String, ArrayList<Integer>> products) {
        this.products = products;
    }
}
