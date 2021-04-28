package fi.tuni.tamk.tiko.bloomorgloom2.Shop.Resources;

import java.util.HashMap;
import java.util.ArrayList;

public class OwnedProducts {
    private HashMap<String, ArrayList<Integer>> products = new HashMap<>();

    public HashMap<String, ArrayList<Integer>> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, ArrayList<Integer>> products) {
        this.products = products;
    }
}
