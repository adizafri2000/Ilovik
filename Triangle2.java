/**
 * @author aisyah
 */
public class Triangle2 extends Piece{
    
    /**
	 * Default .png file name for the piece's icon without the side's name
	 * e.g "Arrow.png" could be "RedArrow.png" or "BlueArrow.png"
	 */
	private static String fileName = "Triangle.png";

	public Triangle2(char side) {
		super(side,fileName);
		setName("Triangle");
    }
    
    /**
     * Checking method to identify if UPPPER-LEFT pathway is clear from starting until 
     * destination (end) square.
     * @param start Starting square
     * @param end Ending(Destination) square
     */    
	public boolean pathwayClear1(Square start, Square end){ //(2,2) to (0,0)
		//boolean move;
        Square temp = start;
        //temp square location is one upper-left square in distance from start square
        temp.setX(temp.getX()-1);
        temp.setY(temp.getY()-1);

        //Final true condition reached: start Square has reached end Square without prior false returns
        //When this condition is reached, start Square has its pathway clear from its initial start position until
        //1 square before its destination
        if((temp.getX()==end.getX())&&(temp.getY()==end.getY()))
            return true;

        else{
            //False condition reached: A square along this pathway has another piece
            if(temp.isOccupied())
                return false;
            
            //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
            //be more squares ahead unchecked
            else
                return pathwayClear1(temp, end);
        }
	}
    
    /**
     * Checking method to identify if UPPPER-RIGHT pathway is clear from starting until 
     * destination (end) square
     */
	public boolean pathwayClear2(Square start, Square end){ //(2,2) to (4,0)
		boolean move = true;
		Square temp;
		if (end.getX() > start.getX() && end.getY() < start.getY()){
			temp = new Square.Builder().x(end.getX()+1).y(end.getY()-1).build();
			if (!temp.isOccupied()){ 
				pathwayClear2(start,temp);
			}
			else
			{
				move = false;
			}
		}
		else if (start.getX() == end.getX() && start.getY() == end.getY()){
			move = true;
		}
		return move;
	}
    
    /**
     * Checking method to identify if LOWER-LEFT pathway is clear from starting until 
     * destination (end) square
     */
	public boolean pathwayClear3(Square start, Square end){ //(2,2) to (0,4)
		boolean move = true;
		Square temp;
		if (end.getX() < start.getX() && end.getY() > start.getY()){
			temp = new Square.Builder().x(end.getX()-1).y(end.getY()+1).build(); //??
			if (!temp.isOccupied()){
				pathwayClear3(start,temp);
			}
			else
			{
				move = false;
			}
		}
		else if (start.getX() == end.getX() && start.getY() == end.getY()){
			move = true;
		}
		return move;
	}
    
    /**
     * Checking method to identify if LOWER-RIGHT pathway is clear from starting until 
     * destination (end) square
     */    
	public boolean pathwayClear4(Square start, Square end){ //(2,2) to (4,4)
		boolean move = true;
		Square temp;
		if (end.getX() > start.getX() && end.getY() > start.getY()){
			temp = new Square.Builder().x(end.getX()-1).y(end.getY()-1).build(); //??
			if (!temp.isOccupied()){
				pathwayClear4(start,temp);
			}
			else
			{
				move = false;
			}
		}
		else if (start.getX() == end.getX() && start.getY() == end.getY()){
			move = true;
		}
		return move;
	}
	
	@Override
    public boolean move(Square start, Square end){

        int xStart = start.getX();
        int yStart = start.getY();
        int xEnd = end.getX();
        int yEnd = end.getY();
        boolean move1 = true;
        int dx,dy;
        
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
        //CHECK IF PATHWAY CLEAR
        if (dx == dy)
        {
			if ((xEnd < xStart) && (yEnd < yStart)) //(2,2) to (0,0)
			{
				move1 = pathwayClear1(start,end);
			}
			else if ((xEnd > xStart) && (yEnd < yStart)) //(2,2) to (4,0)
			{
				move1 = pathwayClear2(start,end);
			}
			else if ((xEnd < xStart) && (yEnd > yStart)) //(2,2) to (0,4)
			{
				move1 = pathwayClear3(start,end);
			}
			else if ((xEnd > xStart) && (yEnd > yStart)) //(2,2) to (4,4)
			{
				move1 = pathwayClear4(start,end);
			}
			}
			else{
				move1 = false;
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
		
			//final move or not
		boolean finalmove = false;
		if (move1 && move2){
			finalmove = true;
		}
				
		return finalmove;
	}
	
}
