import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Arrays;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.*;

public class Testing extends JFrame implements ActionListener
{
    Boolean player = new Boolean(true);
    JButton[] btn = new JButton[56];
    Object[] options = {"New game", "Quit"};
	Boolean gameStarted = false;
	
	public Testing()
    {
		super("Game Board");
        JPanel topBoard = new JPanel(new BorderLayout());
        JLabel timeLbl = new JLabel ("  < Click to start new game");
        topBoard.add(timeLbl,BorderLayout.CENTER);
        JButton startBtn = new JButton(" Start ");
        startBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startBtn.setEnabled(false);
                gameStarted = true;
            }
        });
		
		topBoard.add(startBtn,BorderLayout.WEST);

        JPanel gameBoard = new JPanel(new GridLayout(8,7));
        //Arrays.fill(moles,0);
        for(int i=0;i<56;i++){
            btn[i] = new JButton();
            btn[i].setFocusable(false);
            btn[i].setBackground(new Color(104,69,5));
            gameBoard.add(btn[i]);
            btn[i].addActionListener(this);
        }
        this.setLayout(new BorderLayout());
        add(topBoard,BorderLayout.NORTH);
        add(gameBoard,BorderLayout.CENTER);
        setSize(500,500);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent evt){}
	
	public static void main(String[] args){
        new Testing();
    }
	
	
}