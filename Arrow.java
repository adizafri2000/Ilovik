public class Arrow extends Piece {
	
	boolean rotate;
	rotate = false;
	
	public Arrow(char side, String iconFile){
		super(side, iconFile);
		setName("Arrow");
	}
	
	@Override
    /**
     * Arrow piece moves 1 or 2 steps forward each time
     * Arrow piece will turn around and heads back in the opposite direction when it reaches the other edge of the board
     */
	public boolean move(int xStart, int yStart, int xEnd, int yEnd){
	/*** how arrow move
		COORDINATE FROM TOP TO BOTTOM : 0 -> 7 
		1. Can move forward only 1 or 2 steps.
		--> check side (blue or red)
		--> need to check the position of the arrow
		--> if yStart == 0 && yStart < 7 : (yEnd == yStart+1),(yEnd == yStart+2),(xEnd== xStart)
		--> if yStart == 7 && yStart >0:  (yEnd == yStart-1),(yEnd == yStart-2),(xEnd== xStart)
		--> if yEnd == 7 || yEnd == 0, The boolean rotate will be true and the arrow will move to opposite direction
		--> icon should rotate 180 degree
	***/
	if (side==b){ //blue side
		if(xEnd == xStart){
			if (yStart == 0 && yStart < 7){
				if(yEnd == yStart + 1) //move 1 step forward 
					return true;
				else if (yEnd == yStart + 2) //move 2 steps forward 
					return true;
				else 
					return false;
			} 
			else if (yStart == 7 && yStart > 0){
				if(yEnd == yStart - 1) //move 1 step forward 
					return true;
				else if (yEnd == yStart - 2) //move 2 steps forward 
					return true;
				else 
					return false;				 
			}   
		}	 
			else 
				return false;
	}
	if (side==r){ //red side
		if(xEnd == xStart){
			if (yStart == 0 && yStart < 7){
				if(yEnd == yStart + 1) || (yEnd == yStart + 2)//move 1 step forward 
					return true;
				//else if (yEnd == yStart + 2) //move 2 steps forward 
					//return true;
				else 
					return false;
			} 
			else if (yStart == 7 && yStart > 0){
				if(yEnd == yStart - 1) || (yEnd == yStart - 2)//move 1 step forward 
					return true;
				//else if (yEnd == yStart - 2) //move 2 steps forward 
					//return true;
				else 
					return false;				 
			}   
		}	 
			else 
				return false;
	}
	/*** for number 1
		//check whether it is in the same x-coordinate since it can only move forward
		if(xEnd == xStart){
			if(yEnd == yStart + 1) //move 1 step forward 
				return true;
			else if (yEnd == yStart + 2) //move 2 steps forward 
				return true;
			else if ((yEnd == 7)||(yEnd==0)) //rotate icon
				rotate = true;
				
			else 
				return false; //more than 2 steps or less than one step
		}
		else
			return false; //if the x-coordinate is not the same
	}
	***/
	
		
}                     