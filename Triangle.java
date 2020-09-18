/**
 * @author aisyah
 */
public class Triangle extends Piece{
    
    /**
	 * Default .png file name for the piece's icon without the side's name
	 * e.g "Arrow.png" could be "RedArrow.png" or "BlueArrow.png"
	 */
	private static String fileName = "Triangle.png";

	public Triangle(char side) {
		super(side,fileName);
		setName("Triangle");
	}
	
	@Override
    public boolean move(Square start, Square end){

        int xStart = start.getX();
        int yStart = start.getY();
        int xEnd = end.getX();
        int yEnd = end.getY();
        boolean move1 = false;
        int dx,dy;
	Square[][] sl = new Board().getSquareList();
        
		//to check if movement is valid
        if ((xEnd < xStart) && (yEnd < yStart))
        {
            dx = xStart - xEnd;
            dy = yStart - yEnd;
        }
        else if ((xEnd > xStart) && (yEnd < yStart))
        {
            dx = xEnd - xStart;
            dy = yStart - yEnd;
        }
        else if ((xEnd < xStart) && (yEnd > yStart))
        {
            dx = xStart - xEnd;
            dy = yEnd - yStart;
        }
        else if ((xEnd > xStart) && (yEnd > yStart))
        {
            dx = xEnd - xStart;
            dy = yEnd - yStart;
        }
        else
        {
            dx = 0;
            dy = 0;
        }
        if (dx == dy)
        {
			int checksquare = dx - 1;
			if ((xEnd < xStart) && (yEnd < yStart)) //(2,2) to (0,0)
			{

				for (int i = 0; i < checksquare; i++)
				{
					if (!sl[xEnd+1][yEnd+1].isOccupied()){ //check (1,1)
						move1 = true;
					}
				}
			}
			else if ((xEnd > xStart) && (yEnd < yStart)) //(2,2) to (4,0)
			{
				for (int i = 0; i < checksquare; i++)
				{
					if (!sl[xEnd-1][yEnd+1].isOccupied()){ //check (3,1)
						move1 = true;
					}
				}            
			}
			else if ((xEnd < xStart) && (yEnd > yStart)) //(2,2) to (0,4)
			{
				for (int i = 0; i < checksquare; i++)
				{
					if (!sl[xEnd+1][yEnd-1].isOccupied()){ //check (1,3)
						move1 = true;
					}
				}
			}
			else if ((xEnd > xStart) && (yEnd > yStart)) //(2,2) to (4,4)
			{
				for (int i = 0; i < checksquare; i++)
				{
					if (!sl[xEnd-1][yEnd-1].isOccupied()){ //check (3,3)
						move1 = true;
					}
				}
			}
		}
        
        //move1 = (Math.abs(xStart-yStart)==Math.abs(yEnd-xEnd));
        
       
    
		boolean move2 = false;
	//check if the new square has another piece, same side or not
	//same side = cannot, diff side = eat
		
		if ((start.getPiece().getSide())==(end.getPiece().getSide()) && (end.isOccupied())){
			move2 = false;
		}
		else 
		{
			move2 = true;
		}
		
		boolean finalmove = false;
		if (move1 && move2){
			finalmove = true;
		}
			
		return finalmove;
	}
	
}
