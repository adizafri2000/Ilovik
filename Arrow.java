/**
 * @author Puteri
 */
public class Arrow extends Piece{
	
	private boolean rotate;
	
	
	public Arrow(char side, String iconFile,boolean rotate){
		super(side, iconFile);
		setName("Arrow");
		this.rotate = rotate;
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
	//if (side==r){ //red side
			if (((yStart == 0) || (yStart == 1) || (yStart == 2) || (yStart == 3) || (yStart == 4) || (yStart == 5) || (yStart == 6)) &&(xEnd == xStart)){
				if((yEnd == yStart + 1) || (yEnd == yStart + 2)) 
					return true;
				else 
					return false;
			} 
			else if ((yStart == 7) && (xEnd == xStart)){
				if((yEnd == yStart - 1) || (yEnd == yStart - 2)) 
					return true;
				else 
					return false;				 
			}
			else 
				return false;
	}
	/***
	else{ //blue side
	//if (side==r){ 
		if(xEnd == xStart){
			if (yStart == 0 && yStart < 7){
				if((yEnd == yStart + 1) || (yEnd == yStart + 2))//move 1 step forward 
					return true;
				//else if (yEnd == yStart + 2) //move 2 steps forward 
					//return true;
				else 
					return false;
			} 
			else if (yStart == 7 && yStart > 0){
				if((yEnd == yStart - 1) || (yEnd == yStart - 2))//move 1 step forward 
					return true;
				//else if (yEnd == yStart - 2) //move 2 steps forward 
					//return true;
				else 
					return false;				 
			}   
		}	 
		else 
			return false;
		***/
}
	
	
			                    