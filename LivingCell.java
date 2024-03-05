/**
    22013666, 23013604   
    Abdulahi Sharif, Muhammad Chikhoun
*/

import javafx.scene.paint.Color; 
import java.util.List;


/**
 * Living Cells
 * Living Cells simply carry out cell division allowing them to grow and replicate and take up any empty space around them
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public class LivingCell extends Cell {
    
    private static int transformedCells = 0; //Number of random living cells that evolved into cancer cells

    /**
     * Create a new LivingCell.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param type The type of cell
     */
    public LivingCell(Field field, Location location,CellType type) {
        super(field, location,type);
        setColor(Color.MAGENTA);
    }

    /**
    * This is how the LivingCell decides if it's alive or not
    */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        List<Location> Deadneighbours = getField().adjacentLocations(getLocation());
        
        setNextState(true);
        
        //current cell location 
        Location location = getLocation();
        
        
        //Probability used to determine whether the cell is growing and whether or not it evolves into a cancer cell as it grows
        double grow = Math.random();
        
        if (isAlive()) {
            
            //living cell undergoes cell division and creates more cells in neighbouring empty spaces
            for(Cell cell : neighbours) {
                if((cell instanceof EmptyCell || cell instanceof Mycoplasma) && grow < 0.05){
                    LivingCell living = new LivingCell(field, cell.getLocation(),CellType.LIVINGCELL);
                    field.place(living, cell.getLocation());
                    Simulator.addCell(living, cell);
                    
                }
            } 
            
            //dead mycoplasma are not recognised in the living cell neighbour list so this is how they are recognised
            for(Location loc : Deadneighbours) {
                Cell currentCell = field.getObjectAt(loc);
                if(currentCell instanceof Mycoplasma){
                    LivingCell living = new LivingCell(field, loc,CellType.LIVINGCELL);
                    field.place(living, loc);
                    Simulator.addCell(living, currentCell);
                }
            }
            
            //living cell randomly evolves into a cancer cell after a minimum of 40 generations, a maximum of 3 cells can evolve to be cancer cells
            if (grow < 0.0001 && numGenerations > 40 && transformedCells < 3) {
                updateState(CellType.CANCER);
                transformedCells++;
            } 
        } 
    }
}
    

