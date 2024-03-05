/**
    22013666, 23013604   
    Abdulahi Sharif, Muhammad Chikhoun
*/

import javafx.scene.paint.Color; 
import java.util.List;

/**
 * Chemo is used to heal and attack and stop the growth of the cancer
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public class Chemo extends Cell {
    private int age = 0;
    
    /**
     * Create a new Chemo.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param type The type of cell
     */
    public Chemo(Field field, Location location,CellType type) {
        super(field, location,type);
        setColor(Color.BLUE);
        age = 0;
    }

    /**
    * This is how the Chemo decides if it's alive or not
    */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(true);
        
        Location location = getLocation();
        

        Location randomNeighbourLocation = field.randomAdjacentLocation(this.getLocation());
        Cell neighbour = field.getObjectAt(randomNeighbourLocation);
        
        //probability the chemo can heal a neighbouring cancer cell
        double prob = Math.random(); 
        
        int healthyCellCounter = 0;
        
        if (isAlive()) {
            if (neighbour instanceof Cancer == true && prob < 0.5){
                Chemo chemo = new Chemo(field, randomNeighbourLocation,CellType.CHEMO);
                field.place(chemo, randomNeighbourLocation);
                Simulator.addCell(chemo, neighbour);
            }
            
            for(Cell cell : neighbours) {
                if(cell instanceof Chemo || cell instanceof LivingCell){
                    healthyCellCounter++;
                }
            }   
            
            //if the chemo is 5 generations old and is surrounded by chemo or living cells it heals into a living cell
            if(age >5){
                if(healthyCellCounter == 8 ){
                    updateState(CellType.LIVINGCELL);
                }
                
                else if((location.getRow() == 0 || location.getRow() == 79) && healthyCellCounter >=3){
                    updateState(CellType.LIVINGCELL);
                }
                
                else if((location.getCol() == 0 || location.getCol() == 99) && healthyCellCounter >=3){
                    updateState(CellType.LIVINGCELL);
                }
            }
            age++;
        }
    }
}

    

