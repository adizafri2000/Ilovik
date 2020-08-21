//REMEMBER TO INCLUDE JAVADOCS COMMENTATIONc

/**
 * Piece class defines basic structure of what a Piece (Arrow, Plus, Triangle, Chevron, Sun)
 * would have and implemented later in subclasses. 
 * <p>
 * Pieces are owned by each player from both sides:
 * <p>
 * <strong> 'r' for red player</strong>
 * <p>
 * <strong> 'b' for blue player</strong>
 * @author Adi
 * @version 1.02
 */
public abstract class Piece {

    /**
     * Determines the name of this piece.
     */
    private String name;

    /**
     * Determines player side who owns this piece.
     */
    public char side;
    
    /**
     * Determines x-coordinate of the piece (row)
     */
    public int x;

    /**
     * Determines y-coordinate of the piece (column)
     */
    public int y;

    /**
     * Constructs a Piece object.
     * @param name the name of the piece
     * @param side the player's side owning the piece
     */
    public Piece(String name,char side){
        this.name = name;
        this.side = side;
    }

    /**
     * Sets the name of piece.
     * @param name the name to be set for the piece
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the coordinates of this piece on the board
     * @param x x-coordinate of the piece (row no.)
     * @param y y-coordinate of the piece (col. no.)
     */
    public void setCoordinates(int x,int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the coordinates of this piece in an array of format 
     * {x-coordinate, y-coordinate}
     * @return int array of x and y-coordinates
     */
    public int[] getCoordinates(){
        return new int[] {x,y};
    }

    /**
     * Sets the player side owning this piece.
     * @param side the side owning this piece
     */
    public void setSide(char side) {
        this.side = side;
    }

    /**
     * Returns the name of this piece.
     * @return name of piece
     */
    public String getName() {
        return name;
    }

    /**
     * Returns side owning this piece.
     * @return the side that owns the piece
     */
    public char getSide() {
        return side;
    }

    /**
     * Specifies movement for a type of piece.
     */
    public abstract void move();

}