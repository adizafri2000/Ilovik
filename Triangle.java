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
	public boolean pathwayClear1(Square start, Square end){ //(2,2) to (0,0)
		boolean move = true;
		Square temp;
		if (end.getX() < start.getX() && end.getY() < start.getY()){
			temp = new Square.Builder().x(end.getX()+1).y(end.getY()+1).build(); //??
			if (!temp.isOccupied()){
				pathwayClear1(start,temp);
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
