package fi.tuni.tamk.tiko.bloomorgloom2.Shop.Resources;

/**
 * A class representing a single product effect. Serialized to a POJO from JSON using google/gson.
 */
public class ProductEffect {
    private String method;
    private String action;
    private int amount;

    /**
     * Returns the effect's method.
     * @return String
     */
    public String getMethod() {
        return method;
    }

    /**
     * Returns the effect's action.
     * @return String
     */
    public String getAction() {
        return action;
    }

    /**
     * Returns the effect's amount.
     * @return int
     */
    public int getAmount() {
        return amount;
    }
}
