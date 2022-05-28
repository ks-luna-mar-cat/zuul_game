package Assignments.A4;

/**
 * This class is about items in the game that holds description and weight
 *
 * @author  Kate Son
 */

public class Item {
    private String description;
    private int weight;

    /**
     * Constructor Create an item with the given description and weight
     * @param description
     * @param weight
     */
    public Item(String description, int weight){
        this.description = description;
        this.weight = weight;
    }

    /**
     * @return the item's description
     */
    public String getDescription(){
        return description;
    }
}
