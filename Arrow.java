public class Arrow extends Piece {
	
	public Arrow(char side, String iconFile){
		super(side, iconFile);
		setName("Arrow");
	}
	
	@Override
	public boolean move(int xStart, int yStart, int xEnd, int yEnd){
	/*** how arrow move
		COORDINATE FROM TOP TO BOTTOM : 0 -> 7 
		1. Can move forward only 1 or 2 steps. (yEnd == yStart+1),(yEnd == yStart+2), (xEnd== xStart)
		--> need to check the position of the arrow
		--> if yEnd == 7 || yEnd == 0, The boolean rotate will be true and the arrow will move to opposite direction
		2. When it reach the edge of the board, it will turn around and head back to the opposite direction (The icon should also turn around when it happens) icon should rotate 180 degree
	***/
	boolean rotate;
	rotate = false;
	
	// for number 1
		//check whether it is in the same x-coordinate since it can only move forward
		if(xEnd == xStart){
			if(yEnd == yStart + 1) //move 1 step forward 
				return true;
			else if (yEnd == yStart + 2) //move 2 steps forward 
				return true;
			else 
				return false; //more than 2 steps or less than one step
		}
		else
			return false; //if the x-coordinate is not the same
	}
	/***
	number 2
	move opposite direction
	(a) move forward one step
	--> yEnd == yStart - 1
	(b)move forward two steps
	--> yEnd == yStart - 2
	***/
		
	}
}                     