import java.util.*;

/**
 * @author Aisyah, Iffah
 */
public class Player{
	
	/**
	 * Playing side of player 
	 * i.e 'r' for red side or 'b' for blue side
	 */
	private char side;

	/**
	 * Holds the pieces for a player side. ArrayList indexes are specified to piece types.
	 * Pieces' list index are determined from left to right (col 0 - 7) starting 
	 * arrangement from non-Arrow-pieces row to Arrow-pieces row, specified as:
	 * <p>Format: (index no.) piecename</p>
	 * 
	 * <p>1. (0) Plus
	 * <p>2. (1) Triangle
	 * <p>3. (2) Chevron
	 * <p>4. (3) Sun
	 * <p>5. (4) Chevron
	 * <p>6. (5) Triangle
	 * <p>7. (6) Plus
	 * <p>8. (7) Arrow
	 * <p>9. (8) Arrow
	 * <p>10. (9) Arrow
	 * <p>11. (10) Arrow
	 * @see assignPlayerPieces()
	 */
	private ArrayList<Piece> pieceList = new ArrayList<Piece>();
	private String name;
	private boolean win;
	private boolean turn;
	private int moves;
	//private int numPlayer = 0;
	
	//constructor assign side to player
	public Player(char side){
		this.side = side;
		assignPlayerPieces(side);
	}
	
	/**
	 * Assigns respective pieces to players according to which side they are on.
	 * Order of adding pieces to this list follows the adding order protocol as defined for
	 * pieceList ArrayList.
	 * @see pieceList
	 * @param side side owning the pieces
	 */
	public void assignPlayerPieces(char side){
			
		pieceList.add(new Plus('b'));
		pieceList.add(new Triangle('b'));
		pieceList.add(new Chevron('b'));
		pieceList.add(new Sun('b'));
		pieceList.add(new Chevron('b'));
		pieceList.add(new Triangle('b'));
		pieceList.add(new Plus('b'));
		pieceList.add(new Arrow('b', false));
		pieceList.add(new Arrow('b', false));
		pieceList.add(new Arrow('b', false));
		pieceList.add(new Arrow('b', false));
		
		//assign to each square
			//code here
			//maybe this part Board class ambik kot, dia take Player.pieceList and susun je
		
	}
	
	
	//get side that is moving
	public char getSide(){
		return side;
	}
	
	//set the side that is moving
	public void setSide(char side){
		this.side = side;
	}
	
	//get the name of the piece??
	public String getName(){
		return name;
	}
	
	//set name of the piece??
	public void setName(String name){
		this.name = name;
	}
	
	//set piece that is wanted by the player to move
	public void setPieces(Piece piece){
		this.piece = piece;
	}
	

	/**
	 * Checks whether the side is winning by checking if 
	 * sun is captured or not
	 * @return captured status of Sun piece (pieceList(3))
	 */
	public boolean isWinner(){
		/*boolean c = Piece.isCaptured("Sun");
		if (c == true){win = true;}
		return win;*/
		return (pieceList.get(3).isCaptured());
	}
	
	//return whether the side is in their turn or not
	public boolean isTurn(){
		return turn;
	}
	
	/*
	//put the side in their turn
	public void setTurn(boolean turn){
		if (Piece.move() == true)){
			getSide() = false;
		this.turn = turn;
	}
	
	//check whether triangle and plus must change or not
	//every 4 turn??
	public void updateMoves(int i){
		i++;
		if (i == 4)
		{
			swapPiece();
			i = 0;
		}
	}
	
	public int getMoves(){
		return moves;
	}
	
	public void swapPiece(){
		//swap triangle and plus
		Collections.swap(pieceList,"triangle,plus");
	} */
}
	
	
