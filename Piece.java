//REMEMBER TO INCLUDE JAVADOCS COMMENTATION

/**
 * Abstract class Piece defines the structure of a Piece object played in the Webale Chess. 
 * Piece objects have specific types i.e Sun, Triangle, Chevron, Plus, Arrow.
 * A Piece belongs to a Player and marked by the side the Player represents: red/blue.
 * @author Adi
 */
public abstract class Piece{
    
    private String name;
    private String iconFile;
    private char side;
    private boolean captured;

    public Piece(char side,String iconFile){
        this.captured = false;
        this.side = side;
        this.iconFile = iconFile;
        setName();
    }

    /**
     * Retrieves the name of this piece
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the icon file name of this piece
     * @return the iconFile
     */
    public String getIconFile() {
        return iconFile;
    }

    /**
     * Retrives the side that belongs this piece
     * @return the side
     */
    public char getSide() {
        return side;
    }

    /**
     * Checks if this piece is captured by the enemy
     * @return true if the piece is captured by the enemy, false if otherwise
     */
    public boolean isCaptured() {
        return captured;
    }

    /**
     * Sets the icon file name for this respective piece
     * @param iconFile the iconFile to set
     */
    public void setIconFile(String iconFile) {
        this.iconFile = iconFile;
    }

    /**
     * Sets the side owning this piece
     * @param side the side to set
     */
    public void setSide(char side) {
        this.side = side;
    }

    /**
     * Sets the condition of the piee whether it is captured by the enemy
     * @param captured the captured to set
     */
    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    /**
     * Generates the piece's name based on its side
     * @param side
     */
    public void setName(){
        this.name = Character.toString(side);
    }

    public void setName(String s){
        this.name+=s;
    }

    /**
     * Checks if the selected piece can move to another square specified by the player
     * @param start Square containing the selected piece to be moved 
     * @param end Destination square for the piece's movement
     * @return validity of this piece to move to the end square
     * Once Square class is created, uncomment this method and apply this one into your
     * Piece types.
     */
    //public abstract boolean move(Square start, Square end);

    /**
     * Dummy function while Square class is being created. Use this method first to identify
     * the math required for your piece to move. Once Square class is created, paste the algorithm
     * into the above of method.
     * @param xStart
     * @param yStart
     * @param xEnd
     * @param yEnd
     * @return
     */
    public abstract boolean move(int xStart, int yStart, int xEnd, int yEnd);
}