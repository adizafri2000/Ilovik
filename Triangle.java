/**
 * @author aisyah
 */
public class Triangle extends Piece{
    
    public Triangle(char side,String file){
    super(side, file);
    }
	
	@Override
    public boolean move(Square start, Square end){

        int xStart = start.getX();
        int yStart = start.getY();
        int xEnd = end.getX();
        int yEnd = end.getY();
        boolean move1 = false;
        
		//to check if movement is valid
        if ((xEnd < xStart) && (yEnd < yStart))
        {
            int dx = xStart - xEnd;
            int dy = yStart - yEnd;
        }
        else if ((xEnd > xStart) && (yEnd < yStart))
        {
            int dx = xEnd - xStart;
            int dy = yStart - yEnd;
        }
        else if ((xEnd < xStart) && (yEnd > yStart))
        {
            int dx = xStart - xEnd;
            int dy = yEnd - yStart;
        }
        else if ((xEnd > xStart) && (yEnd > yStart))
        {
            int dx = xEnd - xStart;
            int dy = yEnd - yStart;
        }
        else
        {
            int dx = 0;
            int dy = 0;
        }
        
        if (dx == dy)
        {
            move1 = true;
		}
        
       
    
		boolean move2 = false;
	//check if the new square has another piece, same side or not
	//same side = cannot, diff side = eat
		
		if (start.getPiece().equals("triangle"))
		{
			move1 = true;
		}
		
		if ((start.getPiece().getSide()).equals(side)){
			move1 = false;
		}
		else 
		{
			move1 = true;
		}
		
		boolean finalmove = false;
		if (move1 && move2){
			finalmove = true;
		}
			
		return finalmove;
	}
	
}
