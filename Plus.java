public class Plus extends Piece {
	
	public Plus(String name,char side, int x, int y){
		super(name,side,x,y);	
	}

	public Plus(char side, String iconFile) {
		super(side,iconFile);
		setName("Plus");
	}

	//public boolean validMovement(){
	//	return false;
	//}
	
	/**
	* Plus piece can move anywhere from 1 to 7 squares in any direction (forward,backward,left or right) 
	* until it reaches the end of board or blocked by another piece.
	* 
	* Plus can move 1 to 7 squares (vertical) forward,backward.
	* Plus can move 1 to 6 squares (horizontal) left or right.	
	* 
	* Plus captures on the same path it moves,by occupying the square on which an enemy piece stands.
	* 
	* False : Move diagonally or jump over other pieces.
	* 
	* Plus movement for 1 step :
		Move foward : (yEnd == yStart + 1) , (xEnd == xStart)
		Move backward :(yEnd == yStart - 1) , (xEnd == xStart)
		Move to the left : (xEnd == xStart + 1) , (yEnd == yStart)
		Move to the right : (xEnd == xStart - 1) , (yEnd == yStart)
	*/
	
	@Override
	public boolean move(int xStart, int yStart, int xEnd, int yEnd) {
		if (xEnd == xStart){										
			if (yEnd == yStart + 1) || (yEnd == yStart - 1) 		// move 1 step forward,backward
				return true;
			else if (yEnd == yStart + 2) || (yEnd == yStart - 2) 	// move 2 step forward,backward
				return true;
			else if (yEnd == yStart + 3) || (yEnd == yStart - 3) 	// move 3 step forward,backward
				return true;
			else if (yEnd == yStart + 4) || (yEnd == yStart - 4) 	// move 4 step forward,backward
				return true;
			else if (yEnd == yStart + 5) || (yEnd == yStart - 5) 	// move 5 step forward,backward
				return true;
			else if (yEnd == yStart + 6) || (yEnd == yStart - 6) 	// move 6 step forward,backward
				return true;
			else if (yEnd == yStart + 7) || (yEnd == yStart - 7) 	// move 7 step forward,backward
				return true;	
			else
				return false;
		}
		else if (yEnd == yStart){				
			if (xEnd == xStart + 1) || (xEnd == xStart - 1) 		// move 1 step right,left
				return true;
			else if (xEnd == xStart + 2) || (xEnd == xStart - 2)  	// move 2 step right,left
				return true;
			else if (xEnd == xStart + 3) || (xEnd == xStart - 3)  	// move 3 step right,left
				return true;
			else if (xEnd == xStart + 4) || (xEnd == xStart - 4) 	// move 4 step right,left
				return true;
			else if (xEnd == xStart + 5) || (xEnd == xStart - 5)  	// move 5 step right,left
				return true;	
			else if (xEnd == xStart + 6) || (xEnd == xStart - 6)  	// move 6 step right,left
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	@Override
	public boolean move(Square start, Square end){
		
		boolean move;
		
		if (start == "Plus") {
			move = true;
		}
		
		if(start.getSide() == side){
			move = false;
		}
		else{
			move = true;
		}
		
		return move;
	}
	
}