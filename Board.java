/**
 * Board class defines the structure of the Webale Chess Board. Generally,
 * the Board constitutes of 56 Squares, where Pieces of each square (if applicable)
 * is defined by a Player's pieceList.
 * @author Adi
 */
public class Board {
    public Square[][] squareList = new Square[8][7];

    /**
     * Red player
     */
    private Player p1;

    /**
     * Blue player
     */
    private Player p2;


    /**
     * Board constructor for new games
     * @param p1 Red player
     * @param p2 Blue player
     */
    public Board(Player p1, Player p2,boolean load){
        this.p1 = p1;
        this.p2 = p2;
        initBoard();
        emptyAllSquares();
        if(!load)
            startingArrangement();
    }

    public Board(){
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
        squareList[0][0].setPiece(p2.getPieceList().get(0));
        squareList[0][1].setPiece(p2.getPieceList().get(1));
        squareList[0][2].setPiece(p2.getPieceList().get(2));
        squareList[0][3].setPiece(p2.getPieceList().get(3));
        squareList[0][4].setPiece(p2.getPieceList().get(4));
        squareList[0][5].setPiece(p2.getPieceList().get(5));
        squareList[0][6].setPiece(p2.getPieceList().get(6));
        squareList[1][0].setPiece(p2.getPieceList().get(7));
        squareList[1][2].setPiece(p2.getPieceList().get(8));
        squareList[1][4].setPiece(p2.getPieceList().get(9));
        squareList[1][6].setPiece(p2.getPieceList().get(10));

        //Red side arrangement
        squareList[7][0].setPiece(p1.getPieceList().get(0));
        squareList[7][1].setPiece(p1.getPieceList().get(1));
        squareList[7][2].setPiece(p1.getPieceList().get(2));
        squareList[7][3].setPiece(p1.getPieceList().get(3));
        squareList[7][4].setPiece(p1.getPieceList().get(4));
        squareList[7][5].setPiece(p1.getPieceList().get(5));
        squareList[7][6].setPiece(p1.getPieceList().get(6));
        squareList[6][0].setPiece(p1.getPieceList().get(7));
        squareList[6][2].setPiece(p1.getPieceList().get(8));
        squareList[6][4].setPiece(p1.getPieceList().get(9));
        squareList[6][6].setPiece(p1.getPieceList().get(10));
    }

    /**
     * Loads a piece's arrangment into the board.
     * This method is used for setting up pieces in load games.
     * Player's pieces are individually checked beforehand by calling class
     * and its location in the board is passed.
     * @param p Piece to be added to board
     * @param row Row location (y-axis) of piece
     * @param col Column location (x-axis) of piece
     */
    public void loadArrangement(Piece p,int row,int col){
        squareList[row][col].setPiece(p);
    }

    public void emptyAllSquares(){
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                squareList[i][j].setOccupied(false);
            }
        }
    }

    /**
     * @return Red player
     */
    public Player getP1() {
        return p1;
    }

    /**
     * @return Blue player
     */
    public Player getP2() {
        return p2;
    }

    /**
     * @param p1 Red player to set
     */
    public void setP1(Player p1) {
        this.p1 = p1;
    }

    /**
     * @param p2 Blue player to set
     */
    public void setP2(Player p2) {
        this.p2 = p2;
    }

    /**
     * @return the squareList
     */
    public Square[][] getSquareList() {
        return squareList;
    }

    /**
     * To check if triangle/plus pieces have clear pathways from their starting position until
     * end position
     * @param p Piece to be checked for its type
     * @param start Starting square
     * @param end Destination square
     * @return true if pathway from start to end is clear from any piece
     */
    public boolean isClearPathway(Square start,Square end){
        if (start.getPiece() instanceof Triangle){
            //Triangle pathway check
            boolean move = false;
            if ((end.getX() > start.getX()) && (end.getY() > start.getY()))
            {
                /*Square temp = squareList[start.getY()][start.getX()];
                while(temp.getX() != (end.getX()+1)){
                    temp = squareList[temp.getY()-1][temp.getX()-1]; 
                        if(temp.isOccupied()){
                            move = false;
                            break;
                        }
                        else{
                            move = true;
                            }
                }*/
                move = pathwayClear4(start, end);
            }
            else if ((end.getX() < start.getX()) && (end.getY() > start.getY()))
            {
                /*Square temp = squareList[start.getY()][start.getX()];
                while(temp.getX() != (end.getX()+1)){
                    temp = squareList[temp.getY()+1][temp.getX()-1]; 
                        if(temp.isOccupied()){
                            move = false;
                            break;
                        }
                        else{
                            move = true;
                            }
                }*/
                move = pathwayClear3(start, end);
            }
            else if ((end.getX() > start.getX()) && (end.getY() < start.getY()))
            {
                /*Square temp = squareList[start.getY()][start.getX()];
                while(temp.getX() != (end.getX()+1)){
                    temp = squareList[temp.getY()-1][temp.getX()+1]; 
                        if(temp.isOccupied()){
                            move = false;
                            break;
                        }
                        else{
                            move = true;
                            }
                }*/
                move = pathwayClear2(start, end);
            }
            else if ((end.getX() < start.getX()) && (end.getY() < start.getY()))
            {
                /*Square temp = squareList[start.getY()][start.getX()];
                while(temp.getX() != (end.getX()+1)){
                    temp = squareList[temp.getY()+1][temp.getX()+1]; 
                        if(temp.isOccupied()){
                            move = false;
                            break;
                        }
                        else{
                            move = true;
                            }
                }*/
                move = pathwayClear1(start, end);
            }
            else{
                    move = false;
                }
            return move;
        }
        else if (start.getPiece() instanceof Plus){
            //Plus pathway check
            if ((end.getX() == start.getX()) && (end.getY() > start.getY() )) {		     // foward
                return forwardClear(start, end);
            }

            else if ((end.getX() == start.getX()) && (end.getY() < start.getY() )) {		// backward
                return backwardClear(start, end);
            }
                    
            else if ((end.getY() == start.getY()) && (end.getX() > start.getX() )) {		// right
                    return rightClear(start, end);
            }
                    
            else if ((end.getY() == start.getY()) && (end.getX() < start.getX() )) {		// left
                return leftClear(start, end);
            }
            else
                return false;			
        }
        else if(start.getPiece() instanceof Arrow){
            Square temp;
            Arrow tempArrow = (Arrow)start.getPiece();
            //Check if Arrow piece is moving 2 squares
            if(Math.abs(end.getY()-start.getY())==2){
                if(tempArrow.getSide()=='r'){
                    if(tempArrow.isRotating()){
                        temp = squareList[start.getY()+1][start.getX()];
                        return ((temp.isOccupied()) ? false : true);
                    }
                    else{
                        temp = squareList[start.getY()-1][start.getX()];
                        return ((temp.isOccupied()) ? false : true);
                    }
                }
                else{
                    if(tempArrow.isRotating()){
                        temp = squareList[start.getY()-1][start.getX()];
                        return ((temp.isOccupied()) ? false : true);
                    }
                    else{
                        temp = squareList[start.getY()+1][start.getX()];
                        return ((temp.isOccupied()) ? false : true);
                    }
                }
            }
        }
        return true;
    }

        /**
     * Checking method to identify if UPPPER-LEFT pathway is clear from starting until 
     * destination (end) square.
     * @param start Starting square
     * @param end Ending(Destination) square
     */    
	private boolean pathwayClear1(Square start, Square end){ 
        Square temp = squareList[start.getY()+1][start.getX()+1];
        //Final true condition reached: start Square has reached end Square without prior false returns
        //When this condition is reached, start Square has its pathway clear from its initial start position until
        //1 square before its destination
        if((temp.getX()==end.getX())&&(temp.getY()==end.getY())){
            return true;
        }

        else{
            //False condition reached: A square along this pathway is occupied
            if(temp.isOccupied()){
                return false;
            }
            
            //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
            //be more squares ahead unchecked
            else{
                return pathwayClear1(temp, end);
            }
        }
	}
    
    /**
     * Checking method to identify if UPPPER-RIGHT pathway is clear from starting until 
     * destination (end) square
     * @param start Starting square
     * @param end Ending(Destination) square
     */    
	private boolean pathwayClear2(Square start, Square end){ //(2,2) to (0,0)
        Square temp = squareList[start.getY()-1][start.getX()-1];

        //temp square location is one upper-right square in distance from start square
        temp.setX(temp.getX()+1);
        temp.setY(temp.getY()-1);

        //Final true condition reached: start Square has reached end Square without prior false returns
        //When this condition is reached, start Square has its pathway clear from its initial start position until
        //1 square before its destination
        if((temp.getX()==end.getX())&&(temp.getY()==end.getY()))
            return true;

        else{
            //False condition reached: A square along this pathway is occupied
            if(temp.isOccupied())
                return false;
            
            //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
            //be more squares ahead unchecked
            else
                return pathwayClear2(temp, end);
        }
	}
    
    /**
     * Checking method to identify if LOWER-LEFT pathway is clear from starting until 
     * destination (end) square
     * @param start Starting square
     * @param end Ending(Destination) square
     */    
	private boolean pathwayClear3(Square start, Square end){ //(2,2) to (0,0)
        Square temp = squareList[start.getY()+1][start.getX()-1];

        //temp square location is one lower-left square in distance from start square
        temp.setX(temp.getX()-1);
        temp.setY(temp.getY()+1);

        //Final true condition reached: start Square has reached end Square without prior false returns
        //When this condition is reached, start Square has its pathway clear from its initial start position until
        //1 square before its destination
        if((temp.getX()==end.getX())&&(temp.getY()==end.getY()))
            return true;

        else{
            //False condition reached: A square along this pathway is occupied
            if(temp.isOccupied())
                return false;
            
            //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
            //be more squares ahead unchecked
            else
                return pathwayClear3(temp, end);
        }
	}
    
    /**
     * Checking method to identify if LOWER-RIGHT pathway is clear from starting until 
     * destination (end) square
     * @param start Starting square
     * @param end Ending(Destination) square
     */    
	private boolean pathwayClear4(Square start, Square end){ //(2,2) to (0,0)
        Square temp = squareList[start.getY()-1][start.getX()-1];

        //temp square location is one lower-right square in distance from start square
        temp.setX(temp.getX()+1);
        temp.setY(temp.getY()+1);

        //Final true condition reached: start Square has reached end Square without prior false returns
        //When this condition is reached, start Square has its pathway clear from its initial start position until
        //1 square before its destination
        if((temp.getX()==end.getX())&&(temp.getY()==end.getY()))
            return true;

        else{
            //False condition reached: A square along this pathway is occupied
            if(temp.isOccupied())
                return false;
            
            //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
            //be more squares ahead unchecked
            else
                return pathwayClear4(temp, end);
        }
	}

    private boolean forwardClear(Square start,Square end){
        Square temp = squareList [start.getY()+1][start.getX()];
		
        System.out.printf("\n\nStart: (%d,%d)\n",start.getX(),start.getY());
        System.out.printf("Temp: (%d,%d)\n",temp.getX(),temp.getY());
        System.out.printf("End: (%d,%d)\n",end.getX(),end.getY());
                
        // 1 square before its condition
        if((temp.getX()==end.getX())&&(temp.getY()== end.getY())){
            System.out.println("True reached.");
            return true;
        }

        else{
            //False condition reached: A square along this pathway is occupied
            if(temp.isOccupied()){
                System.out.println("False reached.");
                return false;
            }
                    
            //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
            //be more squares ahead unchecked
            else{
                System.out.println("Recursion reached.");
                return forwardClear(temp, end);
            }
        }	
    }

    private boolean backwardClear(Square start,Square end){
        Square temp = squareList [start.getY()-1][start.getX()];
			
        System.out.printf("\n\nStart: (%d,%d)\n",start.getX(),start.getY());
        System.out.printf("Temp: (%d,%d)\n",temp.getX(),temp.getY());
        System.out.printf("End: (%d,%d)\n",end.getX(),end.getY());
                    
            // 1 square before its condition
            if((temp.getX()==end.getX())&&(temp.getY()== end.getY())){
                System.out.println("True reached.");
                return true;
            }

            else{
                //False condition reached: A square along this pathway is occupied
                if(temp.isOccupied()){
                    System.out.println("False reached.");
                    return false;
                }
                            
                //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
                //be more squares ahead unchecked
                else{
                    System.out.println("Recursion reached.");
                    return backwardClear(temp, end);
                }
            }	
    }

    private boolean rightClear(Square start,Square end){
        Square temp = squareList [start.getY()][start.getX()+1];
			
        System.out.printf("\n\nStart: (%d,%d)\n",start.getX(),start.getY());
        System.out.printf("Temp: (%d,%d)\n",temp.getX(),temp.getY());
        System.out.printf("End: (%d,%d)\n",end.getX(),end.getY());
                    
            // 1 square before its condition
            if((temp.getX()==end.getX())&&(temp.getY()== end.getY())){
                System.out.println("True reached.");
                return true;
            }

            else{
                //False condition reached: A square along this pathway is occupied
                if(temp.isOccupied()){
                    System.out.println("False reached.");
                    return false;
                }
                            
                //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
                //be more squares ahead unchecked
                else{
                    System.out.println("Recursion reached.");
                    return rightClear(temp, end);
                }
            }
    }

    private boolean leftClear(Square start,Square end){
        Square temp = squareList [start.getY()][start.getX()-1];
			
        System.out.printf("\n\nStart: (%d,%d)\n",start.getX(),start.getY());
        System.out.printf("Temp: (%d,%d)\n",temp.getX(),temp.getY());
        System.out.printf("End: (%d,%d)\n",end.getX(),end.getY());
                    
        // 1 square before its condition
        if((temp.getX()==end.getX())&&(temp.getY()== end.getY())){
            System.out.println("True reached.");
            return true;
        }

        else{
            //False condition reached: A square along this pathway is occupied
            if(temp.isOccupied()){
                System.out.println("False reached.");
                return false;
            }
                        
            //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
            //be more squares ahead unchecked
            else{
                System.out.println("Recursion reached.");
                return leftClear(temp, end);
            }
        }
    }

    public void swapPieces(){
        p1.swapPiece();
        p2.swapPiece();
        int plusCount = 0;
        int triangleCount = 0;
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                if(squareList[i][j].isOccupied()){
                    
                    Piece temp = squareList[i][j].getPiece();
                    if(temp instanceof Plus){
                        if(temp.getSide()=='r'){
                            if(plusCount==0){
                                temp = p1.getPieceList().get(0);
                            }
                            else{
                                temp = p1.getPieceList().get(6);
                            }
                        }
                        else{
                            if(plusCount==0){
                                temp = p2.getPieceList().get(0);
                            }
                            else{
                                temp = p2.getPieceList().get(6);
                            }
                        }
                    }
                    else if (temp instanceof Triangle){
                        if(temp.getSide()=='r'){
                            if(triangleCount==0){
                                temp = p1.getPieceList().get(1);
                            }
                            else{
                                temp = p1.getPieceList().get(5);
                            }
                        }
                        else{
                            if(triangleCount==0){
                                temp = p2.getPieceList().get(1);
                            }
                            else{
                                temp = p2.getPieceList().get(5);
                            }
                        }
                    }

                    //squareList[i][j].setOccupied(false);
                    squareList[i][j].setPiece(temp);
                }
            }
        }
    }
    
    /**
     * Flips the board.
     */
    public void flip(){
        Square[][] temp = new Square[8][7];

        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                temp[i][j] = squareList[7-i][6-j];
            }
        }

        squareList = temp;
    }

    public void debug(){
        Square temp;
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                temp = squareList[i][j];
                if (temp.isOccupied())
                    System.out.printf("Row: %d, Column: %d, %s\n",i,j,temp.getPiece().getName());
                else
                    System.out.printf("Row: %d, Column: %d, empty\n",i,j);
            }
        }
    }


}
