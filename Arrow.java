/**
 * @author Puteri
 */
public class Arrow extends Piece
{

	/**
	 * Default .png file name for the piece's icon without the side's name
	 * e.g "Arrow.png" could be "RedArrow.png" or "BlueArrow.png"
	 */
	private static String fileName = "Arrow.png";
	
	/**
	 * The orientation of the Arrow piece.
	 * Arrow pieces from both sides' rotate variable are set as false on starting arrangement.
	 * When respective sides' Arrow piece reach one end of the board, they rotate (move backwards)
	 * and boolean variable is set to true.
	 * By default (on start of new game) is set to FALSE.
	 */
	private boolean rotate;

	public Arrow(char side, boolean rotate)
	{
		super(side, fileName);
		setName("Arrow");
		this.rotate = rotate;
	}

	public boolean isRotating() 
	{
		return rotate;
	}
	
	@Override
	public boolean move(Square start, Square end)
	{
		/**
		* Arrow piece moves 1 or 2 steps forward each time
		* Arrow piece will turn around and heads back in the opposite direction when it reaches the other edge of the board
		*/
	 
		/*** How arrow move (Implementation)
			1. Check the end destination is not occupied or have the piece of the opposite side
				1.1 If the piece are from the same side, return false
			2. Check the side (blue or red)
			3. Check whether the rotate is true or false
				3.1 rotate = true : Moving from the opposite side end of the board to the other end of the board 
				3.2 rotate = false : Moving from original position to the end of the board
			4. Movement logic
				4.1 All of the movement will involve x end position to be the same as the x start position or else it will be false
				4.2 When side = 'r' && rotate = true
					4.2.1 Move one step forward : (y+1)
					4.2.2 Move two step forward : (y+2)
				4.3 When side = 'r' && rotate = false
					4.3.1 Move one step forward : (y-1)
					4.3.2 Move two step forward : (y-2)
				4.4 When side = 'b' && rotate = true
					4.4.1 Move one step forward : (y-1)
					4.4.2 Move two step forward : (y-2)
				4.5 When side = 'b' && rotate = false
					4.5.1 Move one step forward : (y+1)
					4.5.2 Move one step forward : (y+2)
			5. Change of rotate from false to true or vice versa
				5.1 Red side
					5.1.1 end == 0 : rotate = true
					5.1.2 end == 7 : rotate = false
				5.2 Blue side
					5.2.1 end == 0 : rotate = false
					5.2.2 end == 7 : rotate = true
		***/

		//Condition 1
		if((!end.isOccupied())||(end.getPiece().getSide()!=start.getPiece().getSide()))
		{
			//Condition 2
			if (start.getPiece().getSide() == 'r') //red side
			{
				//Condition 3
				if(rotate == false)
				{
					//Move case 4.3, 4.1
					if(((end.getY() == start.getY() - 1) || (end.getY() == start.getY() - 2)) && (end.getX() == start.getX())) 
					{
						//Move case 5.1.1
						if (end.getY() == 0)
							rotate = true;
						return true;
					}
					else 
						return false;
				} 
				else
				{
					//Move case 4.2,4.1
					if(((end.getY() == start.getY() + 1) || (end.getY() == start.getY() + 2)) && (end.getX()== start.getX()))
					{
						//Move case 5.1.2
						if (end.getY() == 7)
							rotate = false;
						return true;
					}
					else 
						return false;				 
				}
			}
			else //blue side
			{
				//Condition 3
				if(rotate == false)
				{ 
					//Move case 4.5, 4.1
					if(((end.getY() == start.getY() + 1) || (end.getY() == start.getY() + 2)) &&(end.getX() == start.getX()))
					{
						//Move case 5.2.2
						if (end.getY() == 7)
							rotate = true;
						return true;
					}
					else 
						return false;
				}
				else
				{
					//Move case 4.4, 4.1
					if(((end.getY() == start.getY() - 1) || (end.getY() == start.getY() - 2)) && (end.getX() == start.getX()))
					{
						//Move case 5.2.1
						if (end.getY() == 0)
							rotate = false;
						return true;
					}
					else 
						return false;				 
				}			
			}
		}
		else
			return false;
	}
}	
	
			                    