package Assignments.A4;

import java.util.Set;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits. Exits is hashmap - contains direction and connected room
 * in that direction as a key and value.
 * 
 * @author  Michael Kölling and David J. Barnes and Kate Son
 *
 *   Modifications:
 * - update getLongDescription method to show item description
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Item item;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Add an item to this room
     * @param item
     */
    public void setItem(Item item){
        this.item = item;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * @param direction room direction
     * @return The room that is reached if we go from this
     * room in direction "direction " If there is no room in 
     * that direction, return null.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    /**
     * @return The description of the room.(the one that was
     * defined in the constructor).
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Return a description of the room's exits.
     * for example, "Exits : north west".
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        Set<String> keys = exits.keySet();
        if(!keys.isEmpty()){
           String returnString = "Exits:";
        
           for(String exit : keys){
               returnString += " " + exit;
           }
           return returnString;
        }
        return "";
    }
    
    /**
     * Return a long discription of this room and item:
     *      You are in the kitchen.
     *      This room contains a gold pot.
     *      Exits: north west
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String descrip = "You are " + description + ".\n";
        if(item != null){
            descrip += "This room contains " + item.getDescription() + ".\n";
        }
        return descrip + getExitString();
    }
}
