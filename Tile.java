/**
 * Defines the location of a tile on the 7x8 board. Location is identified using 
 * x and y coordinates
 * @author Adi
 */
public class Tile{
    /**
     * x-coordinate of this tile(column)
     */
    private int x;

    /**
     * y-coordinate of this tile (row)
     */
    private int y;

    /**
     * Creates a new Tile object
     * @param x x-coordinate of tile
     * @param y y-coordinate of tile
     */
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Returns x-coordinate of this tile
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns y-coordinate of this tile
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets a new x-coordinate for this tile
     * @param x x-coordinate to be set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets a new y-coordinate for this tile
     * @param y y-coordinate to be set
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
        
}