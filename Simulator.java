/**
    22013666, 23013604   
    Abdulahi Sharif, Muhammad Chikhoun
*/

import javafx.scene.paint.Color; 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A Life (Game of Life) simulator, first described by British mathematician
 * John Horton Conway in 1970.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2024.02.03
 */

public class Simulator {

    private static final double MYCOPLASMA_ALIVE_PROB = 0.3;
    private static final double LIVINGCELL_ALIVE_PROB = 0.05;
    
    private static List<Cell> cells;
    private Field field;
    private int generation;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(SimulatorView.GRID_HEIGHT, SimulatorView.GRID_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        cells = new ArrayList<>();
        field = new Field(depth, width);
        reset();
    }

    /**
     * Run the simulation from its current state for a single generation.
     * Iterate over the whole field updating the state of each life form.
     */
    public void simOneGeneration() {
        generation++;
        Cell.incrementGen();
        for (Iterator<Cell> it = cells.iterator(); it.hasNext(); ) {
            Cell cell = it.next();
            cell.act();
        }

        for (Cell cell : cells) {
          cell.updateState();
        }
        
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Location location = new Location(row, col);
                Cell currentCell = field.getObjectAt(location);
                
                if (currentCell.getNextType() == CellType.EMPTYCELL) {
                    EmptyCell emptycell = new EmptyCell(field, location, CellType.EMPTYCELL);
                    field.place(emptycell, location);
                    addCell(emptycell, currentCell);
                }
                
                else if (currentCell.getNextType() == CellType.MYCOPLASMA) {
                    Mycoplasma mycoplasma = new Mycoplasma(field, location, CellType.MYCOPLASMA);
                    field.place(mycoplasma, location);
                    addCell(mycoplasma, currentCell);
                } 
                
                else if (currentCell.getNextType() == CellType.LIVINGCELL) {
                    LivingCell livingcell = new LivingCell(field, location, CellType.LIVINGCELL);
                    field.place(livingcell, location);
                    addCell(livingcell, currentCell);
                } 
                
                else if (currentCell.getNextType() == CellType.CANCER) {
                    Cancer cancer = new Cancer(field, location, CellType.CANCER);
                    field.place(cancer, location);
                    addCell(cancer, currentCell);
                }
                
                else if (currentCell.getNextType() == CellType.CHEMO) {
                    Chemo chemo = new Chemo(field, location, CellType.CHEMO);
                    field.place(chemo, location);
                    addCell(chemo, currentCell);
                }
            }
        }
           
        //introduces chemo to the cancerous tissue
        if(generation == 120){
            for (Cell cell : cells) {
                double chemoProb = Math.random();
                if(chemoProb < 0.002){
                    Location location = cell.getLocation();
                    Cell oldCell = field.getObjectAt(location);
                    
                    Chemo chemo = new Chemo(field, location,CellType.CHEMO);
                    field.place(chemo, location);
                    addCell(chemo, oldCell);
                }
            }
        }        
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        generation = 0;
        cells.clear();
        populate();
    }

    /**
     * Randomly populate the field live/dead life forms
     */
    private void populate() {
      Random rand = Randomizer.getRandom();
      field.clear();
      for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
          Location location = new Location(row, col);
          if (rand.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
            Mycoplasma myco = new Mycoplasma(field, location,CellType.MYCOPLASMA);
            cells.add(myco);
          }
          else if (rand.nextDouble() <= LIVINGCELL_ALIVE_PROB) {
            LivingCell livingcell = new LivingCell(field, location,CellType.LIVINGCELL);
            cells.add(livingcell);
          }
          else {
            EmptyCell emptyCell = new EmptyCell(field, location,CellType.EMPTYCELL);
            cells.add(emptyCell);
          }
        }
      }
    }
    
    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    public void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            //wake up
        }
    }
    
    public Field getField() {
        return field;
    }

    public int getGeneration() {
        return generation;
    }
    
    public static void addCell(Cell cell, Cell oldCell) {
         int index = cells.indexOf(oldCell);
         cells.set(index,cell);
    }
}
