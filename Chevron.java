/**
 * @author Adi
 */
public class Chevron extends Piece{

    /**
	 * Default .png file name for the piece's icon without the side's name
	 * e.g "Arrow.png" could be "RedArrow.png" or "BlueArrow.png"
	 */
    private static String fileName = "Chevron.png";

    public Chevron(char side) {
        super(side, fileName);
        setName("Chevron");
    }

    @Override
    public boolean move(Square start, Square end) {
        /*
        Implementation steps
        1. Check validity of destination (square)
            1.1 Destination square is empty? -> (2), occupied? -> (1.2)
            1.2 Destination square has piece of same side? return false, different side? ->(2)
        2. Check movement itself
            2.1 Upper L movement (y-2, x+/-1)
            2.2 Lower L movement (y+2, x+/-1)
            2.3 Left L movement  (x-2, y+/-1)
            2.4 Right L movement (x+2, y+/-1)
        */
        //1st condition: end square cannot contain peice of same side OR is not occupied
        if(end.isOccupied()){
            if(end.getPiece().getSide()!=start.getPiece().getSide()){
                if((end.getY()==start.getY()-2)&&((end.getX()==start.getX()+1)||(end.getX()==start.getX()-1))) return true;
    
                //move case 2.2
                if((end.getY()==start.getY()+2)&&((end.getX()==start.getX()+1)||(end.getX()==start.getX()-1))) return true;
    
                //move case 2.3
                if((end.getX()==start.getX()-2)&&((end.getY()==start.getY()+1)||(end.getY()==start.getY()-1))) return true;
    
                //move case 2.4
                if((end.getX()==start.getX()+2)&&((end.getY()==start.getY()+1)||(end.getY()==start.getY()-1))) return true;
            }
        }
        else{
            if((end.getY()==start.getY()-2)&&((end.getX()==start.getX()+1)||(end.getX()==start.getX()-1))) return true;

            //move case 2.2
            if((end.getY()==start.getY()+2)&&((end.getX()==start.getX()+1)||(end.getX()==start.getX()-1))) return true;

            //move case 2.3
            if((end.getX()==start.getX()-2)&&((end.getY()==start.getY()+1)||(end.getY()==start.getY()-1))) return true;

            //move case 2.4
            if((end.getX()==start.getX()+2)&&((end.getY()==start.getY()+1)||(end.getY()==start.getY()-1))) return true;
        }
        /*if((!end.isOccupied())||(end.getPiece().getSide()!=start.getPiece().getSide())){
            if((end.getY()==start.getY()-2)&&((end.getX()==start.getX()+1)||(end.getX()==start.getX()-1))) return true;

            //move case 2.2
            if((end.getY()==start.getY()+2)&&((end.getX()==start.getX()+1)||(end.getX()==start.getX()-1))) return true;

            //move case 2.3
            if((end.getX()==start.getX()-2)&&((end.getY()==start.getY()+1)||(end.getY()==start.getY()-1))) return true;

            //move case 2.4
            if((end.getX()==start.getX()+2)&&((end.getY()==start.getY()+1)||(end.getY()==start.getY()-1))) return true;
        }*/
        return false;
    }
}