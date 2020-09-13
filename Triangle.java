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
        super(side, fileName);
        setName("Triangle");
    }
    
    /*public Triangle(String name,char side,Tile t){
    super(name,side,t);
    }*/
    
    // Aisyah repairkan ni, sekalikan move check coordinate with move check square valid ke tak
	@Override
    public boolean move(int xStart, int yStart, int xEnd, int yEnd){

        //int oldx = Board.Tile.getX();
        //int oldy = Board.Tile.getY();
        //int newx = Piece.Tile.getX();
        //int newy = Piece.Tile.getY();
        boolean move = false;
        
		//to check if movement is valid
        if ((newx < oldx) && (newy < oldy))
        {
            int dx = oldx - newx;
            int dy = oldy - newy;
        }
        else if ((newx > oldx) && (newy < oldy))
        {
            int dx = newx - oldx;
            int dy = oldy - newy;
        }
        else if ((newx < oldx) && (newy > oldy))
        {
            int dx = oldx - newx;
            int dy = newy - oldy;
        }
        else if ((newx > oldx) && (newy > oldy))
        {
            int dx = newx - oldx;
            int dy = newy - oldy;
        }
        else
        {
            int dx = 0;
            int dy = 0;
        }
        
        if (dx == dy)
        {
            move = true;
		}
        
        return move;
       
    }
	
	//check if the new square has another piece, same side or not
	//same side = cannot, diff side = eat
	@Override
	public boolean move(Square start, Square end){
		
		boolean move;
		
		if (start == "triangle")
		{
			move = true;
		}
		
		if (start.getSide() == side){
			move = false;
		}
		else 
		{
			move = true;
		}
		
		return move;
	}
	
}