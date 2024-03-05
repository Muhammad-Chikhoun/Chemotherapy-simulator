/**
    22013666, 23013604   
    Abdulahi Sharif, Muhammad Chikhoun
*/

import javafx.scene.paint.Color; 
import java.util.List;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public class Mycoplasma extends Cell {
    /**
     * Create a new Mycoplasma.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param type The type of cell
     */
    public Mycoplasma(Field field, Location location, CellType type) {
        super(field, location, type);
        setColor(Color.GREEN);
    }

    /**
    * This is how the Mycoplasma decides if it's alive or not
    */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        
        
        int nonEmptyNeighbours = 0;
        
        
        //empty cells are technically living but shouldnt affect the mycoplasma
        for(Cell cell : neighbours) {
            if(cell instanceof EmptyCell == false){
                nonEmptyNeighbours++;
            }
        }   

        if (isAlive()) {
            if(nonEmptyNeighbours == 2 || nonEmptyNeighbours == 3){
                setNextState(true);
            }
            
            else if (nonEmptyNeighbours < 2 || nonEmptyNeighbours > 3){
                setNextState(false);
            }
        }
        
        else if(nonEmptyNeighbours == 3){
            setNextState(true);
        }
    }
}
