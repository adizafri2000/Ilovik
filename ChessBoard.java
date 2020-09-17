/* RESIZE FRAME, SET ICON CENTER , ADD LABEL PLAYER NAME , SET JPANEL1 ,JPANEL2 */

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ChessBoard extends JFrame {
    private JFrame frame;  // Main window
    private MenuBar menuBar;
    private Menu newGameMenu, loadMenu, saveMenu;
    private String name1, name2;
    private JButton squares[][] = new JButton[8][7];  // components

    public ChessBoard() {
        frame = new JFrame("Ilovik Webale Chess");
        frame.setSize(600, 600);
        frame.setMinimumSize(frame.getSize());
        frame.setLayout(new GridLayout(8, 7));
        
        menuBar = new MenuBar();
        createMenu();
        addMenuBarMenus(); // Add Menu to MenuBar
        frame.setMenuBar(menuBar); // Add MenuBar to the Frame
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        //create board components
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                squares[i][j] = new JButton();
                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(Color.BLACK);
                } else {
                    squares[i][j].setBackground(Color.WHITE);
                }   
                frame.add(squares[i][j]);
            }
        }
        
        String pathBP = "/icons/BluePlus.png";
        String pathBT = "/icons/BlueTriangle.png";
        String pathBC = "/icons/BlueChevron.png";
        String pathBS = "/icons/BlueSun.png";
        String pathBA = "/icons/BlueArrow.png";
        
        String pathRP = "/icons/RedPlus.png";
        String pathRT = "/icons/RedTriangle.png";
        String pathRC = "/icons/RedChevron.png";
        String pathRS = "/icons/RedSun.png";
        String pathRA = "/icons/RedArrow.png";
        
        ImageIcon BluePlus = new ImageIcon(getClass().getResource(pathBP));
        ImageIcon BlueTriangle = new ImageIcon(getClass().getResource(pathBT)); 
        ImageIcon BlueChevron = new ImageIcon(getClass().getResource(pathBC));
        ImageIcon BlueSun = new ImageIcon(getClass().getResource(pathBS));
        ImageIcon BlueArrow = new ImageIcon(getClass().getResource(pathBA)); 
        
        ImageIcon RedPlus = new ImageIcon(getClass().getResource(pathRP));
        ImageIcon RedTriangle = new ImageIcon(getClass().getResource(pathRT)); 
        ImageIcon RedChevron = new ImageIcon(getClass().getResource(pathRC));
        ImageIcon RedSun = new ImageIcon(getClass().getResource(pathRS));
        ImageIcon RedArrow = new ImageIcon(getClass().getResource(pathRA)); 

        squares[0][0].add(new JLabel(BluePlus));
        squares[0][1].add(new JLabel(BlueTriangle));
        squares[0][2].add(new JLabel(BlueChevron));
        squares[0][3].add(new JLabel(BlueSun));
        squares[0][4].add(new JLabel(BlueChevron));
        squares[0][5].add(new JLabel(BlueTriangle));
        squares[0][6].add(new JLabel(BluePlus));
        squares[1][0].add(new JLabel(BlueArrow));
        squares[1][2].add(new JLabel(BlueArrow));
        squares[1][4].add(new JLabel(BlueArrow));
        squares[1][6].add(new JLabel(BlueArrow));
    
        squares[7][0].add(new JLabel(RedPlus));
        squares[7][1].add(new JLabel(RedTriangle));
        squares[7][2].add(new JLabel(RedChevron));
        squares[7][3].add(new JLabel(RedSun));
        squares[7][4].add(new JLabel(RedChevron));
        squares[7][5].add(new JLabel(RedTriangle));
        squares[7][6].add(new JLabel(RedPlus));
        squares[6][0].add(new JLabel(RedArrow));
        squares[6][2].add(new JLabel(RedArrow));
        squares[6][4].add(new JLabel(RedArrow));
        squares[6][6].add(new JLabel(RedArrow));
        
        
        frame.setVisible(true); // set the frame visible
    }

    private void createMenu() {  // Create Menu objects to add to the MenuBar
        newGameMenu = new Menu("New Game");
        loadMenu = new Menu("Load");
        saveMenu = new Menu("Save");
    }
    
    private void addMenuBarMenus() {    // Add Menu to the MenuBar
        menuBar.add(newGameMenu);
        menuBar.add(loadMenu);
        menuBar.add(saveMenu);
    } 
 
    public static void main(String[] args) {
        new ChessBoard();
    }
 
}