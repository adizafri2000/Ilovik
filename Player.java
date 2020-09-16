import java.util.*;

/**
 * @author Aisyah, Iffah
 */
public class Player{
	
	private char side;

	/**
	 * Holds the pieces for a player side. Indexes are unique to piece types.
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
	 * Pieces' list index are determined from left to right (col 0 - 7) starting 
	 * arrangement from non-Arrow piece row to Arrow-piece row, specified as: 
	 * <p>0. Plus</p>
	 * <p>1. Triangle</p>
	 * <p>2. Chevron</p>
	 * <p>3. Sun</p>
	 * <p>4. Chevron</p>
	 * <p>5. Triangle</p>
	 * <p>6. Plus</p>
	 * <p>7. Arrow</p>
	 * <p>8. Arrow</p>
	 * <p>9. Arrow</p>
	 * <p>10. Arrow</p>
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
	
	//check whether the side is winning
	//sun is captured or not
	public boolean isWinner(){
		boolean c = Piece.isCaptured("Sun");
		if (c == true){win = true;}
		return win;
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
	
	
