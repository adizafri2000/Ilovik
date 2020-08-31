public class Sun extends Piece{
	
	public Sun(char side,String iconFile){
		super(side, iconFile);
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
}