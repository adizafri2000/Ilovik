import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainMenu extends JFrame{

    JLabel welcomeLabel = new JLabel("Ilovik's Webale Chess");
    JButton startButton = new JButton("New Game");
    JButton loadButton = new JButton("Load Game");

    public MainMenu(){
        JPanel top = new JPanel();
        top.setBackground(Color.gray);
        top.add(welcomeLabel);
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.BLUE);
        startButton.setLocation(320,390);
        bottom.add(startButton);
        top.setLocation(0,0);
        top.setSize(640,300);
        bottom.setLocation(0,300);
        bottom.setSize(640,180);
        this.add(top);
        this.add(bottom);
        this.pack();
        setSize(640, 480);
        setTitle("W: "+this.getWidth()+", H:"+this.getHeight());
        setResizable(true);
		setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
    
}
