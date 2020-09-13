import java.util.*;

public class Board{
    private Square[][] squareList;
    private Player player1;
    private Player player2;

    public Board(){
        squareList = new Square[8][7];
    }

    /**
     * Creates an empty board of 7x8 Square objects
     */
    public void init(){
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                this.squareList[i][j] = new Square.Builder().x(j).y(i).occupied(false).build();
            }
        }
    }
  
    /**
     * Arranges pieces in board according to initial starting position for
     * a new game
     */
    public void startingSetup(){
        int i = 0;
        int j = 0;

        //Blue pieces' setup
        //Pluses
        //squareList[0][0].setPiece();
        //squareList[0][6].setPiece();
        
        //Triangle
        //squareList[0][1].setPiece();
        //squareList[0][5].setPiece();
        
        //Chevron
        //squareList[0][2].setPiece();
        //squareList[0][4].setPiece();

        //Sun
        //squareList[0][3].setPiece();

        //Arrows
        //squareList[1][0].setPiece();
        //squareList[1][2].setPiece();
        //squareList[1][4].setPiece();
        //squareList[1][6].setPiece();


        //Red pieces' setup
        //Pluses
        //squareList[7][0].setPiece();
        //squareList[7][6].setPiece();
        
        //Triangle
        //squareList[7][1].setPiece();
        //squareList[7][5].setPiece();
        
        //Chevron
        //squareList[7][2].setPiece();
        //squareList[7][4].setPiece();

        //Sun
        //squareList[7][3].setPiece();

        //Arrows
        //squareList[6][0].setPiece();
        //squareList[6][2].setPiece();
        //squareList[6][4].setPiece();
        //squareList[6][6].setPiece();

        
    }

}
