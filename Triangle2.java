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
	private boolean pathwayClear1(Square start, Square end){ //(2,2) to (0,0)
        Square temp = new Square.Builder().x(start.getX()-1).y(start.getY()-1).occupied(false).build();
        //temp square location is one upper-left square in distance from start square
        //temp.setX(temp.getX()-1);
        //temp.setY(temp.getY()-1);
        System.out.printf("\n\nStart: (%d,%d)\n",start.getX(),start.getY());
        System.out.printf("Temp: (%d,%d)\n",temp.getX(),temp.getY());
        System.out.printf("End: (%d,%d)\n",end.getX(),end.getY());

        //Final true condition reached: start Square has reached end Square without prior false returns
        //When this condition is reached, start Square has its pathway clear from its initial start position until
        //1 square before its destination
        if((temp.getX()==end.getX())&&(temp.getY()==end.getY())){
            System.out.println("True reached.");
            return true;
        }

        else{
            //False condition reached: A square along this pathway is occupied
            if(temp.isOccupied()){
                System.out.println("False reached.");
                return false;
            }
            
            //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
            //be more squares ahead unchecked
            else{
                System.out.println("Recursion reached.");
                return pathwayClear1(temp, end);
            }
        }
	}
    
    /**
     * Checking method to identify if UPPPER-RIGHT pathway is clear from starting until 
     * destination (end) square
     * @param start Starting square
     * @param end Ending(Destination) square
     */    
	private boolean pathwayClear2(Square start, Square end){ //(2,2) to (0,0)
        Square temp = start;

        //temp square location is one upper-right square in distance from start square
        temp.setX(temp.getX()+1);
        temp.setY(temp.getY()-1);

        //Final true condition reached: start Square has reached end Square without prior false returns
        //When this condition is reached, start Square has its pathway clear from its initial start position until
        //1 square before its destination
        if((temp.getX()==end.getX())&&(temp.getY()==end.getY()))
            return true;

        else{
            //False condition reached: A square along this pathway is occupied
            if(temp.isOccupied())
                return false;
            
            //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
            //be more squares ahead unchecked
            else
                return pathwayClear2(temp, end);
        }
	}
    
    /**
     * Checking method to identify if LOWER-LEFT pathway is clear from starting until 
     * destination (end) square
     * @param start Starting square
     * @param end Ending(Destination) square
     */    
	private boolean pathwayClear3(Square start, Square end){ //(2,2) to (0,0)
        Square temp = start;

        //temp square location is one lower-left square in distance from start square
        temp.setX(temp.getX()-1);
        temp.setY(temp.getY()+1);

        //Final true condition reached: start Square has reached end Square without prior false returns
        //When this condition is reached, start Square has its pathway clear from its initial start position until
        //1 square before its destination
        if((temp.getX()==end.getX())&&(temp.getY()==end.getY()))
            return true;

        else{
            //False condition reached: A square along this pathway is occupied
            if(temp.isOccupied())
                return false;
            
            //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
            //be more squares ahead unchecked
            else
                return pathwayClear3(temp, end);
        }
	}
    
    /**
     * Checking method to identify if LOWER-RIGHT pathway is clear from starting until 
     * destination (end) square
     * @param start Starting square
     * @param end Ending(Destination) square
     */    
	private boolean pathwayClear4(Square start, Square end){ //(2,2) to (0,0)
        Square temp = start;

        //temp square location is one lower-right square in distance from start square
        temp.setX(temp.getX()+1);
        temp.setY(temp.getY()+1);

        //Final true condition reached: start Square has reached end Square without prior false returns
        //When this condition is reached, start Square has its pathway clear from its initial start position until
        //1 square before its destination
        if((temp.getX()==end.getX())&&(temp.getY()==end.getY()))
            return true;

        else{
            //False condition reached: A square along this pathway is occupied
            if(temp.isOccupied())
                return false;
            
            //Recursion condition reached: Squares along the pathway reached until now are all clear, but there might
            //be more squares ahead unchecked
            else
                return pathwayClear4(temp, end);
        }
	}
	
	@Override
    public boolean move(Square start, Square end){

        int xStart = start.getX();
        int yStart = start.getY();
        int xEnd = end.getX();
        int yEnd = end.getY();
        boolean move1;
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
        
        //False condition reached: Incorrect movement location
        if(dy!=dx) 
            return false;

        else{
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
            else
                return false;
        }
    
		//check if the new square has another piece, same side or not
        //same side = cannot, diff side = eat
        
        if (move1){
            if ((!end.isOccupied())||(end.getPiece().getSide()!=start.getPiece().getSide())){
                return true;
            }
        }
            
        return false;
	}
	
}
