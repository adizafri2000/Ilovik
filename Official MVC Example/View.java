import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class View extends JFrame{
    private JButton button;
    private JPanel panel;

    public View(){
        super("View GUI");
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        button = new JButton("Change Color");
        button.setSize(90, 20);
        panel.add(button);
        add(panel);
        //b.addActionListener(this);
        
        //b.addActionListener(controller);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
    }

    public void display(int n){
        if (n==1) setVisible(true);
        else setVisible(false);
    }

    public JButton getButton(){
        return button;
    }

    public JPanel getPanel(){
        return panel;
    }
    
}