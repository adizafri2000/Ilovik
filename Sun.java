/**
 * @author Iffah
 */
public class Sun extends Piece{

	/**
	 * Default .png file name for the piece's icon without the side's name
	 * e.g "Arrow.png" could be "RedArrow.png" or "BlueArrow.png"
	 */
	private static String fileName = "Sun.png";
	
	public Sun(char side){
		super(side, fileName);
		setName("Sun");
	}
	
	/**
     * Sun piece moves only one step in any direction.
     * Direction involves moving up, down, left, right, and diagonally.
     */
	/*Movement logic:
	  false - if move Sun more than one step.
	  true - 1. move one box forward (x/y+1)                 
		   - 2. move one box backward (x/y-1)
		   - 3. move one box to the right (x+1/y)
		   - 4. move one box to the left (x-1/y)
		   - 5. move one box diagonally
			    a) upper right (x+1/y-1)
			    b) upper left (x-1/y-1)
			    c) lower right (x+1/y+1)
			    d) lower left (x-1/y+1)
	*/
	@Override
	public boolean move(Square start, Square end){
		 if( (!end.isOccupied()) || (end.getPiece().getSide() != start.getPiece().getSide()) ){
			//move 1 & 2
			if ( (end.getX() == start.getX()) && ( (end.getY() == (start.getY() + 1)) || (end.getY() == (start.getY() - 1)) ) )
				return true;
			//move 3 & 4
			else if ( (end.getY() == start.getY()) && ( (end.getX() == (start.getX() + 1)) || (end.getX() == (start.getX() - 1)) ) )
				return true;
			//move 5.a & 5.b
			else if ( (end.getY() == (start.getY() - 1)) && ( (end.getX() == (start.getX() + 1)) || (end.getX() == (start.getX() - 1)) ) )
				return true;
			//move 5.c & 5.d
			else if ( (end.getY() == (start.getY() + 1)) && ( (end.getX() == (start.getX() + 1)) || (end.getX() == (start.getX() - 1)) ) )
				return true;
		 }
		 return false;
	}
	/*
	@Override
    public boolean move(int xStart, int yStart, int xEnd, int yEnd){
		if ( (xEnd == xStart) && ( (yEnd == yStart + 1) || (yEnd == yStart - 1) ) )
			return true;
		else if ( (yEnd == yStart) && ( (xEnd == xStart + 1) || (xEnd == xStart - 1) ) )
			return true;
		else if ( (yEnd == yStart - 1) && ( (xEnd == xStart + 1) || (xEnd == xStart - 1) ) )
			return true;
		else if ( (yEnd == yStart + 1) && ( (xEnd == xStart + 1) || (xEnd == xStart - 1) ) )
			return true;
		else
			return false;
	}
	*/
}
