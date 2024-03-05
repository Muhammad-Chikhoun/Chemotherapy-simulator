/**
    22013666, 23013604   
    Abdulahi Sharif, Muhammad Chikhoun
*/

import javafx.scene.paint.Color; 
import java.util.List;

/**
 * Cancer Cells
 * Spread to nearby living cells.
 *
 * @author Abdullahi Sharif, Muhammad Chikhoun
 * @version 2024.02.25
 */

public class Cancer extends Cell {
    /**
     * Create a new Cancer.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param type The type of cell
     */
    public Cancer(Field field, Location location,CellType type) {
        super(field, location,type);
        setColor(Color.SILVER);
    }

    /**
    * This is how the Cancer decides if it's alive or not
    */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(true);
   
        double spread = Math.random(); //probability of whether or not the cancer becomes malignant and spreads
        
        Location randomNeighbourLocation = field.randomAdjacentLocation(this.getLocation()); //location of the random neighbouring cell of the cancer cell
        Cell randomNeighbour = field.getObjectAt(randomNeighbourLocation);//the random neighbouring cell
        
        if (isAlive()) {
            
            //spreads only to a random neighbouring cell
            if (spread < 0.1){
                if(randomNeighbour instanceof Cancer == false && randomNeighbour instanceof Chemo == false ){
                    setColor(Color.RED);
                    Cancer cancer = new Cancer(field, randomNeighbourLocation,CellType.CANCER);
                    field.place(cancer, randomNeighbourLocation);
                    Simulator.addCell(cancer, randomNeighbour);
            
                    
                } 
            }
            
            //spreads to all neighbouring cells
            if (spread< 0.05){
                for(Cell cell : neighbours) {
                    if(cell instanceof Cancer == false && cell instanceof Chemo == false ){
                        randomNeighbourLocation = cell.getLocation();
                        randomNeighbour = field.getObjectAt(randomNeighbourLocation);
                        setColor(Color.RED);
                        Cancer cancer = new Cancer(field, randomNeighbourLocation,CellType.CANCER);
                        field.place(cancer, randomNeighbourLocation);
                        Simulator.addCell(cancer, randomNeighbour);
                        
                        
                    }
                }
            }
        }
    }
}

