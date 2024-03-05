/**
    22013666, 23013604   
    Abdulahi Sharif, Muhammad Chikhoun
*/

import javafx.scene.paint.Color; 

/**
 * Empty cell
 * Simplifies some of the programming aspects
 * 
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public class EmptyCell extends Cell {
    /**
     * Create a new Empty Cell.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param type The type of cell
     */
    public EmptyCell(Field field, Location location, CellType type) {
        super(field, location, type);
        setColor(Color.TRANSPARENT);
    }

    /**
    * Empty cell doesnt do anything
    */
    public void act() {
        setNextState(true);
    }
}
