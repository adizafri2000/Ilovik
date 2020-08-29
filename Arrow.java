public class Arrow extends Piece {
	
	public Arrow(char side, String iconFile){
		super(side, iconFile);
		setName("Arrow");
	}
	
	@Override
	public boolean move(int xStart, int yStart, int xEnd, int yEnd){
	/*** how arrow move
		1. Can move forward only 1 or 2 steps. (yEnd == yStart+1),(yEnd == yStart+2), (xEnd== xStart)
		2. When it reach the edge of the board, it will turn around and head back to the opposite direction (The icon should also turn around when it happens) icon should rotate 180 degree
	***/
	// for number 1
		if(xEnd == xStart){
			if(yEnd == yStart + 1)
				return true;
			else if (yEnd == yStart + 2)
				return true;
			else 
				return false;
		}
		else
			return false;
	}
}                     