import java.util.*;

/**
 * @author Aisyah, Iffah, Adi
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
	 * <p>index no. : piecename
	 * 
	 * <ul>
	 * <li>0 : Plus
	 * <li>1 : Triangle
	 * <li>2 : Chevron
	 * <li>3 : Sun
	 * <li>4 : Chevron
	 * <li>5 : Triangle
	 * <li>6 : Plus
	 * <li>7 : Arrow
	 * <li>8 : Arrow
	 * <li>9 : Arrow
	 * <li>10: Arrow
	 * @see assignPlayerPieces()
	 */
	private ArrayList<Piece> pieceList = new ArrayList<Piece>();

	/**
	 * Name of player
	 */
	private String name;

	/**
	 * Winning status of player
	 */
	//private boolean win;

	/**
	 * Checker for whether it's the player's turn to move
	 */
	private boolean turn;
	private int moves;
	
	/**
	 * Creates a new Player respective to his/her name and side
	 * @param side
	 */
	public Player(String name,char side){
		this.name = name;
		this.side = side;
		assignPlayerPieces(side);
		this.turn = false;
	}
	
	/**
	 * Assigns respective pieces to players according to which side they are on.
	 * Order of adding pieces to this list follows the adding order protocol as defined in
	 * Player.pieceList
	 * @see pieceList
	 * @param side side owning the pieces
	 */
	public void assignPlayerPieces(char side){
			
		pieceList.add(new Plus(side));
		pieceList.add(new Triangle(side));
		pieceList.add(new Chevron(side));
		pieceList.add(new Sun(side));
		pieceList.add(new Chevron(side));
		pieceList.add(new Triangle(side));
		pieceList.add(new Plus(side));
		pieceList.add(new Arrow(side, false));
		pieceList.add(new Arrow(side, false));
		pieceList.add(new Arrow(side, false));
		pieceList.add(new Arrow(side, false));
	}
	
	/**
	 * Sets all pieces in the player's piecelist to captured or 
	 * not captured
	 * @param captured 
	 */
	public void pieceCaptured(boolean captured){
		for(Piece p:pieceList)
			p.setCaptured(captured);
	}

	/**
	 * Returns the player's list of pieces
	 * @return the pieceList
	 * @see pieceList
	 */
	public ArrayList<Piece> getPieceList() {
		return pieceList;
	}

	/**
	 * Returns the side of the player ('b'/'r')
	 * @return player's side
	 */
	public char getSide(){
		return side;
	}
	
	
	/**
	 * Sets the side of the player
	 * @param side playing side to be assigned to the player
	 */
	public void setSide(char side){
		this.side = side;
	}
	
	/**
	 * Returns the name of the player
	 * @return player's name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the name for this player
	 * @param name Name to be set for this player
	 */
	public void setName(String name){
		this.name = name;
	}
	
	//set piece that is wanted by the player to move
	/*public void setPieces(Piece piece){
		this.piece = piece;
	}*/
	

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
	/**
	 * Returns whether the side is in their turn or not
	 * @return turn to move status
	 */
	public boolean isTurn(){
		return turn;
	}
	
	/**
	 * Sets whether the player is to move or not
	 * @param turn
	 */
	public void setTurn(boolean turn){
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
	
	/**
	 * Swaps triangle pieces with plus pieces, and vice versa
	 */
	public void swapPiece(){
		//swap triangle and plus
		//Collections.swap(pieceList,"triangle,plus");
		Piece temp1 = pieceList.get(0);
		Piece temp2 = pieceList.get(6);
		pieceList.set(0, pieceList.get(1));
		pieceList.set(6, pieceList.get(5));
		pieceList.set(1, temp1);
		pieceList.set(5, temp2);
	} 
}
	
	
