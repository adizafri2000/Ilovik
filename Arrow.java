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
	public boolean move(Square start, Square end){
	/**
     * Arrow piece moves 1 or 2 steps forward each time
     * Arrow piece will turn around and heads back in the opposite direction when it reaches the other edge of the board
     */
	 
	 /*** how arrow move
		1. Check the end destination is not occupied or have the piece of the opposite side
			1.1 If the piece are from the same side, return false
		rotate = true : Moving from the opposite end of the board to the other end of the board
		rotate = false : Moving from original position to the end of the board
		1. Can move forward only 1 or 2 steps.
		--> check side (blue or red)
		--> need to check the position of the arrow
		--> if yEnd == 7 || yEnd == 0, The boolean rotate will be true and the arrow will move to opposite direction
		--> icon should rotate 180 degree
	***/
	 
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
			if(rotate == false){ 
				if(((end.getY() == start.getY() + 1) || (end.getY() == start.getY() + 2)) &&(end.getX() == start.getX())){
					if (end.getY() == 7)
						rotate = true;
					return true;
				}
				else 
					return false;
			}else
				if(((end.getY() == start.getY() - 1) || (end.getY() == start.getY() - 2)) && (end.getX() == start.getX())){
					if (end.getY() == 0)
						rotate = false;
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

	
	
			                    