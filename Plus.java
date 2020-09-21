/**
 * @author aina
 */
 
public class Plus extends Piece {

	/**
	 * Default .png file name for the piece's icon without the side's name
	 * e.g "Arrow.png" could be "RedArrow.png" or "BlueArrow.png"
	 */
	private static String fileName = "Plus.png";

	/***
	* Plus piece can move anywhere from 1 to 7 squares in any direction (forward,backward,left or right) 
	* until it reaches the end of board or blocked by another piece.
	* 
	* Plus can move 1 to 7 squares (vertical) forward,backward.
	* Plus can move 1 to 6 squares (horizontal) left or right.	
	***/
	public Plus(char side) {
		super(side,fileName);
		setName("Plus");
	}
	
	
	/*** Plus captures on the same path it moves,by occupying the square on which an enemy piece stands.
	* 
	* False : Move diagonally or jump over other pieces.
	***/ 
	
	/*** Plus movement for 1 step :
		Move foward : (end.getY() == start.getY() + 1) , (end.getX() == start.getX())
		Move backward :(end.getY() == start.getY() - 1) , (end.getX() == start.getX())
		Move to the left : (end.getX() == start.getX() + 1) , (end.getY() == start.getY())
		Move to the right : (end.getX() == start.getX() - 1) , (end.getY() == start.getY())
	***/
	
	@Override
	public boolean move(Square start, Square end) {
		if( (!end.isOccupied()) || (end.getPiece().getSide() != start.getPiece().getSide()) ){
			if (end.getX() == start.getX()){										
				if ((end.getY() == start.getY() + 1) || (end.getY() == start.getY() - 1))		// move 1 step forward,backward
					return true;
				else if ((end.getY() == start.getY() + 2) || (end.getY() == start.getY() - 2)) 	// move 2 step forward,backward
					return true;
				else if ((end.getY() == start.getY() + 3) || (end.getY() == start.getY() - 3)) 	// move 3 step forward,backward
					return true;
				else if ((end.getY() == start.getY() + 4) || (end.getY() == start.getY() - 4))	// move 4 step forward,backward
					return true;
				else if ((end.getY() == start.getY() + 5) || (end.getY() == start.getY() - 5))	// move 5 step forward,backward
					return true;
				else if ((end.getY() == start.getY() + 6) || (end.getY() == start.getY() - 6))	// move 6 step forward,backward
					return true;
				else if ((end.getY() == start.getY() + 7) || (end.getY() == start.getY() - 7))	// move 7 step forward,backward
					return true;	
				else
					return false;
			}
			else if (end.getY() == start.getY()){				
				if ((end.getX() == start.getX() + 1) || (end.getX() == start.getX() - 1))		// move 1 step right,left
					return true;
				else if ((end.getX() == start.getX() + 2) || (end.getX() == start.getX() - 2))  // move 2 step right,left
					return true;
				else if ((end.getX() == start.getX() + 3) || (end.getX() == start.getX() - 3))  // move 3 step right,left
					return true;
				else if ((end.getX() == start.getX() + 4) || (end.getX() == start.getX() - 4))	// move 4 step right,left
					return true;
				else if ((end.getX() == start.getX() + 5) || (end.getX() == start.getX() - 5)) 	// move 5 step right,left
					return true;	
				else if ((end.getX() == start.getX() + 6) || (end.getX() == start.getX() - 6))  // move 6 step right,left
					return true;
				else
					return false;
			}
			else
				return false;
		}
		return false;
	}
}