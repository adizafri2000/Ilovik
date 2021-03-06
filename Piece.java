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
        setIconFile(iconFile);
        setName();
    }

    /**
     * Retrieves the name of this piece
     * @return piece's name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the icon file name of this piece
     * @return name of file of piece's icon
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
     * Sets the icon file name for this respective piece.
     * This method appends subclass' iconFile String representing piece type's
     * name with the side name to the front of it.
     * e.g "Red" + "Arrow.png" = "RedArrow.png"
     * @param iconFile the iconFile to set
     */
    public void setIconFile(String iconFile) {
        StringBuilder s = new StringBuilder(iconFile);
        if (side=='r') s.insert(0,"Red");
        else s.insert(0,"Blue");
        this.iconFile = s.toString();
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

    /**
     * Appends a specified string to the end of the piece's
     * current name.
     * @param s String to be apppended to piece's name
     */
    public void setName(String s){
        this.name+=s;
    }

    /**
     * Checks if the selected piece can move to another square specified by the player
     * @param start Square containing the selected piece to be moved 
     * @param end Destination square for the piece's movement
     * @return validity of this piece to move to the end square
     */
    public abstract boolean move(Square start, Square end);
}