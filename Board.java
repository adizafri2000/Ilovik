
/**
 * Board class defines the structure of the Webale Chess Board. Generally,
 * the Board constitutes of 56 Squares, where Pieces of each square (if applicable)
 * is defined by a Player's pieceList.
 */
public class Board {
    private Square[][] squareList = new Square[8][7];
    private Player p1;
    private Player p2;

    public Board(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
        initBoard();
    }

    /**
     * Initializes all the squares in the squareList, setting each to empty(unoccupied) status
     */
    public void initBoard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                squareList[i][j] = new Square.Builder().x(j).y(i).occupied(false).build();
            }
        }
    }

    /**
     * Arranges the pieces to the standard Webale Chess' starting position
     */
    public void startingArrangement(){
        //Blue side arrangement
        squareList[0][0].setPiece(p1.getPieceList().get(0));
        squareList[0][1].setPiece(p1.getPieceList().get(1));
        squareList[0][2].setPiece(p1.getPieceList().get(2));
        squareList[0][3].setPiece(p1.getPieceList().get(3));
        squareList[0][4].setPiece(p1.getPieceList().get(4));
        squareList[0][5].setPiece(p1.getPieceList().get(5));
        squareList[0][6].setPiece(p1.getPieceList().get(6));
        squareList[1][0].setPiece(p1.getPieceList().get(7));
        squareList[1][2].setPiece(p1.getPieceList().get(8));
        squareList[1][4].setPiece(p1.getPieceList().get(9));
        squareList[1][6].setPiece(p1.getPieceList().get(10));
    }

    /**
     * @return the p1
     */
    public Player getP1() {
        return p1;
    }

    /**
     * @return the p2
     */
    public Player getP2() {
        return p2;
    }

    /**
     * @param p1 the p1 to set
     */
    public void setP1(Player p1) {
        this.p1 = p1;
    }

    /**
     * @param p2 the p2 to set
     */
    public void setP2(Player p2) {
        this.p2 = p2;
    }


}