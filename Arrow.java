/**
 * @author Puteri
 */
public class Arrow extends Piece{
	
	/**
	 * The orientation of the Arrow piece.
	 * Arrow pieces from both sides' rotate variable are set as false on starting arrangement.
	 * When respective sides' Arrow piece reach one end of the board, they rotate (move backwards)
	 * and boolean variable is set to true.
	 */
	private boolean rotate;
	
	public Arrow(char side, String iconFile,boolean rotate){
		super(side, iconFile);
		setName("Arrow");
		this.rotate = rotate;
	}

	public boolean isRotating() {
		return rotate;
	}
	
	@Override
    /**
     * Arrow piece moves 1 or 2 steps forward each time
     * Arrow piece will turn around and heads back in the opposite direction when it reaches the other edge of the board
     */
	/*** how arrow move
		COORDINATE FROM TOP TO BOTTOM : 0 -> 7 
		rotate = true : Moving form row 0 to row 7
		rotate = false : Moving from row 7 to row 0
		1. Can move forward only 1 or 2 steps.
		--> check side (blue or red)
		--> need to check the position of the arrow
		--> if yEnd == 7 || yEnd == 0, The boolean rotate will be true and the arrow will move to opposite direction
		--> icon should rotate 180 degree
	***/
	public boolean move(Square start, Square end){
	//red side 
	if((!end.isOccupied())||(end.getPiece().getSide()!=start.getPiece().getSide())){
		if (start.getPiece().getSide() == 'r'){
			if(rotate == false){
				if(((end.getY() == start.getY() - 1) || (end.getY() == start.getY() - 2)) && (end.getX() == start.getX())) {
					if (end.getY() == 0)
						rotate = true;
					return true;
				}
				else 
					return false;
			} 
			else{
				if(((end.getY() == start.getY() + 1) || (end.getY() == start.getY() + 2)) && (end.getX()== start.getX())){
					if (end.getY() == 7)
						rotate = false;
					return true;
				}
				else 
					return false;				 
			}
		}
		//blue side
		else{
			if(rotate == true){ 
				if(((end.getY() == start.getY() + 1) || (end.getY() == start.getY() + 2)) &&(end.getX() == start.getX())){
					if (end.getY() == 7)
						rotate = false;
					return true;
				}
				else 
					return false;
			}else
				if(((end.getY() == start.getY() - 1) || (end.getY() == start.getY() - 2)) && (end.getX() == start.getX())){
					if (end.getY() == 0)
						rotate = true;
					return true;
				}
				else 
					return false;				 
			}			
		}
		else
			return false;
	}
}

	
	
			                    