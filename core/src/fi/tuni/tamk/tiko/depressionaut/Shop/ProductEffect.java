package fi.tuni.tamk.tiko.depressionaut.Shop;

/**
 * A class representing a single product effect. Serialized to a POJO from JSON using google/gson.
 */
public class ProductEffect {
    private String method;
    private String action;
    private String amount;

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
     * @return String
     */
    public String getAmount() {
        return amount;
    }
}
