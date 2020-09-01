import java.util.*;

public class Player{
	
	private char side;
	private ArrayList<Pieces> pieceList;
	private String name;
	private boolean win;
	private boolean turn;
	private int moves;
	private int numPlayer = 0;
	
	//constructor assign pieces into each player
	public Player(){
		numPlayer++;
		
		if (numPlayer == 1){
			ArrayList<Pieces> p = new ArrayList();
			//p = assign piece arrangement
		}
		else if (numPlayer == 2){
			ArrayList<Pieces> p = new ArrayList();
			//p = assign piece arrangement
		}
		else{}
	}
	
	//get side that is moving
	public char getSide(){
		return side;
	}
	
	//set the side that is moving
	public void setSide(String side){
		this.side = side;
	}
	
	//get the name of the piece??
	public char getName(){
		return name;
	}
	
	//set name of the piece??
	public void setName(String name){
		this.name = name;
	}
	
	//set piece that is wanted by the player to move
	public void setPieces(){
		this.piece = piece;
	}
	
	//check whether the side is winning
	//sun is captured or not
	public boolean isWinner(){
		boolean c = getSide().getPieces("Sun").isCaptured();
		if (c == true){win = true;}
		return win;
	}
	
	//return whether the side is in their turn or not
	public boolean isTurn(){
		return turn;
	}
	
	//put the side in their turn
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
}
	
	
