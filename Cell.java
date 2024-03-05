/**
    22013666, 23013604   
    Abdulahi Sharif, Muhammad Chikhoun
*/

import javafx.scene.paint.Color; 
import java.util.List;

/**
 * A class representing the shared characteristics of all forms of life
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public abstract class Cell {

    private boolean alive;    
    private boolean nextAlive; // The state of the cell in the next iteration
    private Color color = Color.WHITE;
    private CellType type; // The cell's type
    private CellType nextCellType; // The cell's next type
    
    protected Field field;
    protected Location location;
    
    protected static int numGenerations = 0; //The number of generations
    
    /**
     * Create a new cell at location in field.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param type The type of cell
     */
    public Cell(Field field, Location location, CellType type) {
        alive = true;
        nextAlive = false;
        this.field = field;
        this.type = type;
        setLocation(location);
    }

    /**
     * Make this cell act - that is: the cell decides it's status in the
     * next generation.
     */
    abstract public void act();

    /**
     * Check whether the cell is alive or not.
     * @return true if the cell is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Indicate that the cell is no longer alive.
     */
    protected void setDead() {
        alive = false;
    }

    /**
     * Indicate that the cell will be alive or dead in the next generation.
     */
    public void setNextState(boolean value) {
        nextAlive = value;
    }
    
    /**
    * Changes the type of cell.
    * 
    * @param nextType The cell's next type.
    */
    public void updateState(CellType nextType) {
        nextCellType = nextType;
    }
    
    /**
     * Changes the state of the cell
     */
    public void updateState() {
        alive = nextAlive;
    }

    /**
     * Changes the color of the cell
     * @param col The new color of the cell
     */
    public void setColor(Color col) {
        color = col;
    }

    /**
     * Returns the cell's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Return the cell's location.
     * @return The cell's location.
     */
    protected Location getLocation() {
        return location;
    }

    /**
     * Place the cell at the new location in the given field.
     * @param location The cell's location.
     */
    protected void setLocation(Location location) {
        this.location = location;
        field.place(this, location);
    }

    /**
     * Return the cell's field.
     * @return The cell's field.
     */
    protected Field getField() {
        return field;
    }
    
    /**
     * Increment the number of generations
     */
    public static void incrementGen() {
        numGenerations++;
    }
    
    /**
     * Returns the cell's type.
     * 
     * @return The cell's type.
     */ 
    protected CellType getType() {
        return type;
    }

    /**     
     * Returns the cell's next type.
     * 
     * @return The cell's next type.
     */
      protected CellType getNextType() {
        return nextCellType;
    }
  
    /**
     * Returns a list of the cell's neighbours.
     * 
     * @return A list of the cell's neighbours.
     */
    protected List<Cell> getNeighbours() {
        return getField().getLivingNeighbours(getLocation());
    }
}
