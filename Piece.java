//REMEMBER TO INCLUDE JAVADOCS COMMENTATION

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
     * Tile location of this piece
     */
    private Tile tile;

    /**
     * Constructs a Piece object.
     * @param name the name of the piece
     * @param side the player's side owning the piece
     */
    public Piece(String name,char side,Tile t){
        this.name = name;
        this.side = side;
        this.tile = t;
    }

    /**
     * Sets the name of piece.
     * @param name the name to be set for the piece
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
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