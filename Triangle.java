/**
 * @author aisyah
 */
public class Triangle extends Piece{
    
    /**
	 * Default .png file name for the piece's icon without the side's name
	 * e.g "Arrow.png" could be "RedArrow.png" or "BlueArrow.png"
	 */
	private static String fileName = "Triangle.png";
	
	/**
	 * The orientation of the Triangle piece.
	 * Triangle pieces are only allowed to move diagonally
	 * boolean finalmove is set to false
	 */

	public Triangle(char side) {
		super(side,fileName);
		setName("Triangle");
	}
	
	
	@Override
    public boolean move(Square start, Square end){
	    
	/**
		* Triangle piece can move multiple squares diagonally  
		*/
	 
		/*** How triangle move (Implementation)
			1. check if the start and end square are located diagonally
				1.1 if it is not diagonally, then return false
			2. Check if the end square is occupied or not and if occupied, check the side of the piece in end square
				2.1 return false is occupied and the piece is the same side
			3. Movement logic
				3.1 number of square from x of start square to x of end square and number of square from y of start square to y of end square must be the same
		***/

        int xStart = start.getX();
        int yStart = start.getY();
        int xEnd = end.getX();
        int yEnd = end.getY();
        boolean move1 = false;
        int dx,dy;
        
	
	 /**
	*check if number of square from x of start square to x of end square and number of square from y of start square to y of end square must be the same
	*/
        if ((xEnd < xStart) && (yEnd < yStart)) //(0,0) to (2,2)
        {
            if ((start.getY() - end.getY()) == (start.getX() - end.getX()))
			{
				move1 = true;
			}
        }
        else if ((xEnd > xStart) && (yEnd < yStart))
        {
            if ((start.getY() - end.getY()) == (end.getX() - start.getX()))
			{
				move1 = true;
			}
        }
        else if ((xEnd < xStart) && (yEnd > yStart))
        {
            if ((end.getY() - start.getY()) == (start.getX() - end.getX()))
			{
				move1 = true;
			}
        }
        else if ((xEnd > xStart) && (yEnd > yStart))
        {
            if ((end.getY() - start.getY()) == (end.getX() - start.getX()))
			{
				move1 = true;
			}
        }
	else{
		move1 = false;
	}
	

	boolean move2 = false;
	
	/**
	*check if the new square has another piece, same side or not
	*/		
	if ((end.isOccupied() && (start.getPiece().getSide())==(end.getPiece().getSide()))){
		move2 = false;
	}
	else 
	{
		move2 = true;
	}
		
	//final move validation of triangle piece
	boolean finalmove = false;
	if (move1 && move2){
		finalmove = true;
	}
				
		return finalmove;
	}
	
}
