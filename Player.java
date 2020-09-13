import java.util.*;

public class Player{
	
	private char side;
	private ArrayList<Piece> bluePieceList = new ArrayList<Piece>();
	private ArrayList<Piece> redPieceList = new ArrayList<Piece>();
	private String name;
	private boolean win;
	private boolean turn;
	private int moves;
	//private int numPlayer = 0;
	
	//constructor assign side to player
	public Player(char side){
		this.side = side;
		assignPlayerPieces();
	}
	
	public void assignPlayerPieces(){
		if (getSide() == 'b'){
			
			bluePieceList.add(new Plus('b'));
			bluePieceList.add(new Triangle('b'));
			bluePieceList.add(new Chevron('b'));
			bluePieceList.add(new Sun('b'));
			bluePieceList.add(new Chevron('b'));
			bluePieceList.add(new Triangle('b'));
			bluePieceList.add(new Plus('b'));
			bluePieceList.add(new Arrow('b', false));
			bluePieceList.add(new Arrow('b', false));
			bluePieceList.add(new Arrow('b', false));
			bluePieceList.add(new Arrow('b', false));
			
			//assign to each square
			  //code here
		}
		else if (getSide() == 'r'){

			redPieceList.add(new Plus('r'));
			redPieceList.add(new Triangle('r'));
			redPieceList.add(new Chevron('r'));
			redPieceList.add(new Sun('r'));
			redPieceList.add(new Chevron('r'));
			redPieceList.add(new Triangle('r'));
			redPieceList.add(new Plus('r'));
			redPieceList.add(new Arrow('r', false));
			redPieceList.add(new Arrow('r', false));
			redPieceList.add(new Arrow('r', false));
			redPieceList.add(new Arrow('r', false));
			
			//assign to each square
			  //code here 
		}
		
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
	
	
