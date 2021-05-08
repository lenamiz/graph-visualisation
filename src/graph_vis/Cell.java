package graph_vis;


public class Cell {

    private final int i;
    private final int j;
    private final boolean[] walls = {true, true, true, true};   //cell's walls clockwise from the top

    protected Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }

    protected boolean getWall(int x) {     //returns true if cell has a wall in the x direction
        return this.walls[x];
    }

}