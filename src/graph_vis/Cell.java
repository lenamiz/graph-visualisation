/**
 * graph visualisation tool
 * @author lenamiz
 * github.com/lenamiz/
 */
package graph_vis;

/**
 * Cell class is the base of the grid display
 */
public class Cell {

    private final int i;
    private final int j;
    private final boolean[] walls = {true, true, true, true};   //cell's walls clockwise from the top

    protected Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }

    /**
     *
     * @param x direction
     * @return true if cell has a wall in the x direction
     */
    protected boolean getWall(int x) {
        return this.walls[x];
    }

}