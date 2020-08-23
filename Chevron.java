/**
 * Chevron class defines structure of a Chevron Piece object.
 * @author Adi
 */
public class Chevron extends Piece{

    int[][] movements;

    public Chevron(String name,char side,int x,int y){
        super(name, side,x,y);
    }

    public boolean validMovement(){
        return false;
    }

    @Override
    public void move() {
        
    }
}
