/**
 * @author Adi
 */
public class Chevron extends Piece{

    public Chevron(char side,String iconFile){
        super(side, iconFile);
        setName("Chevron");
    }

    @Override
    public boolean move(Square start, Square end) {
        /*
        Implementation steps
        1. Check validity of destination (square)
            1.1 Destination square has piece of same side? return false
        2. Check movement itself
            2.1 Upper L movement (y-2, x+/-1)
            2.2 Lower L movement (y+2, x+/-1)
            2.3 Left L movement  (x-2, y+/-1)
            2.4 Right L movement (x+2, y+/-1)
        */

        //1st condition: end square cannot contain peice of same side OR is not occupied
        if ((end.getPiece().getSide()!=start.getPiece().getSide())||(!end.isOccupied())){
            //move case 2.1
            if((end.getY()==start.getY()-2)&&((end.getX()==start.getX()+1)||(end.getX()==start.getX()-1))) return true;

            //move case 2.2
            if((end.getY()==start.getY()+2)&&((end.getX()==start.getX()+1)||(end.getX()==start.getX()-1))) return true;

            //move case 2.3
            if((end.getX()==start.getX()-2)&&((end.getY()==start.getY()+1)||(end.getY()==start.getY()-1))) return true;

            //move case 2.4
            if((end.getX()==start.getX()+2)&&((end.getY()==start.getY()+1)||(end.getY()==start.getY()-1))) return true;
        }

        return false;
    }

    /*
    
    public boolean move(int xStart, int yStart, int xEnd, int yEnd) {
        /*
        Implementation steps
        1. Check validity of destination (square)
            1.1 Destination square has piece of same side? return false
        2. Check movement itself
            2.1 Upper L movement (y-2, x+/-1)
            2.2 Lower L movement (y+2, x+/-1)
            2.3 Left L movement  (x-2, y+/-1)
            2.4 Right L movement (x+2, y+/-1)
        

        //move case 2.1
        if((yEnd==yStart-2)&&((xEnd==xStart+1)||(xEnd==xStart-1))) return true;

        //move case 2.2
        if((yEnd==yStart+2)&&((xEnd==xStart+1)||(xEnd==xStart-1))) return true;

        //move case 2.3
        if((xEnd==xStart-2)&&((yEnd==yStart+1)||(yEnd==yStart-1))) return true;

        //move case 2.4
        if((xEnd==xStart+2)&&((yEnd==yStart+1)||(yEnd==yStart-1))) return true;

        return false;
    }
    */
}