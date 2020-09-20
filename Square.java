
/**
 * Square class represents the individual tiles present on the Board.
 * Square objects are the ones that will hold the chess pieces.
 */
public class Square {
    /**
     * X-coordinate (column no) of the square
     */
    private int x;

    /**
     * Y-coordinate (row no) of the square
     */
    private int y;

    /**
     * Piece that is held (inside) by the square
     */
    private Piece piece;

    /**
     * Status of the square:
     * If a piece is moved into a square, occupied is set to true.
     */
    private boolean occupied;

    /* Square constructor uses Builder DP to ease instantiation 
     Square objects with/without piece, and what type of piece
    is contained in it.
    */
    public Square(Builder squareBuilder){
        this.x = squareBuilder.x;
        this.y = squareBuilder.y;
        this.piece = squareBuilder.piece;
        this.occupied = squareBuilder.occupied;
    }

    public static class Builder{
        private int x;
        private int y;
        private Piece piece;
        private boolean occupied;

        public Builder x(int X){
            x = X;
            return this;
        }

        public Builder y(int Y){
            y = Y;
            return this;
        }
        public Builder piece(Piece P){
            piece = P;
            return this;
        }
        public Builder occupied(boolean O){
            occupied = O;
            return this;
        }

        public Square build(){
            return new Square(this);
        }
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public Piece getPiece() {
        return piece;
    }

    public boolean isOccupied() {
        return occupied;
    }


    public void setX(int x) {
        this.x = x;
    }


    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets a piece to be contained in this square.
     * Once set, square is automatically set to occupied
     */
    public void setPiece(Piece p) {
        this.piece = p;
        this.occupied = true;
    }


    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public static void main(String[] args) {
        Square sq = new Square.Builder().x(5).y(5).occupied(false).build();
        System.out.printf("sq: (%d,%d),%s\n",sq.getX(),sq.getY(),sq.isOccupied());

        sq.setX(23);
        System.out.printf("sq: (%d,%d),%s\n",sq.getX(),sq.getY(),sq.isOccupied());
    }
}