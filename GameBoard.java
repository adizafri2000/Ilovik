import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GameBoard extends JPanel {
	
	//public int JFrame frame;
	private final int boardWidth = 7;
	private final int boardHeight = 8;
	public Grid [][] boardGrid = new Grid[boardWidth][boardHeight];
	public int playerTurn;
		
	public GameBoard(){
		frameSetup();
		WebaleBoard();
	}
	
	public void frameSetup(){
		JFrame frame = new JFrame("Webale Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setSize(550,550);
		frame.setVisible(true);
		frame.setResizable(true);
	}

	public void WebaleBoard(){
		playerTurn = 0;
		
		for(int i =0; i < boardWidth; i++){
			for (int j = 0; j <boardHeight; j++){
				boardGrid[i][j] = new Grid();
			}
		}
		
	} 

	
	public static void main(String[] args){
		new GameBoard();
	
	}
	/***
	public startButton(){ 
		super("Start");
		JPanel p = new JPanel();
		add(p);
		setSize(250,80);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	***/
}