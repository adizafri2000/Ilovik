/**
 * @author Aina, Puteri, Iffah, Adi
 */
/***
PLAYER 1 RED
PLAYER 2 BLUE
***/

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;


public class View extends JFrame {
    private JMenuBar menuBar;
    private JButton newGameMenu, loadMenu, saveMenu;
    public JButton[][] squares = new JButton[8][7];  /** components **/
    
    /**
     * Holds the text for BLUE player's name
     */
    private JLabel player1Name;

    /**
     * Holds the text for RED player's name
     */
    private JLabel player2Name; 
    
    private JPanel boardPanel, home;
    
    /**
     * 1. Create board
     * 2. Set respective icon to each button
    **/
    public View() {
        super("Ilovik Webale Chess");
        setSize(800, 800);
        setMinimumSize(getSize());
        player1Name = new JLabel("Select New Game to start!");
        player2Name = new JLabel("");
        
        boardPanel = new JPanel(new GridLayout(8,7));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        home = new JPanel();
        home.setLayout(new BoxLayout(home,BoxLayout.Y_AXIS));
        
        setBackground(Color.BLACK);
        setForeground(Color.RED);
        
        menuBar = new JMenuBar();
        createMenu();
        addMenuBarMenus();          // Add Menu to MenuBar
        add(menuBar);   
        setJMenuBar(menuBar);  		// Add MenuBar to the Frame
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.add(player1Name);
        //create board components
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                squares[i][j] = new JButton();
                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(Color.BLACK);
                } else {
                    squares[i][j].setBackground(Color.WHITE);
                }   
                squares[i][j].setName(Integer.toString(i)+Integer.toString(j));
                boardPanel.add(squares[i][j]);
            }
        }
        home.add(boardPanel, BorderLayout.CENTER);
        home.add(player2Name);
        add(home);
        
        
            squares[0][0].setIcon(getIcon("BluePlus.png"));      
            squares[0][1].setIcon(getIcon("BlueTriangle.png"));
            squares[0][2].setIcon(getIcon("BlueChevron.png"));
            squares[0][3].setIcon(getIcon("BlueSun.png"));
            squares[0][4].setIcon(getIcon("BlueChevron.png"));
            squares[0][5].setIcon(getIcon("BlueTriangle.png"));
            squares[0][6].setIcon(getIcon("BluePlus.png"));
            squares[1][0].setIcon(getIcon("BlueArrow.png"));
            squares[1][2].setIcon(getIcon("BlueArrow.png"));
            squares[1][4].setIcon(getIcon("BlueArrow.png"));
            squares[1][6].setIcon(getIcon("BlueArrow.png"));
            
            squares[7][0].setIcon(getIcon("RedPlus.png"));
            squares[7][1].setIcon(getIcon("RedTriangle.png"));
            squares[7][2].setIcon(getIcon("RedChevron.png"));
            squares[7][3].setIcon(getIcon("RedSun.png"));
            squares[7][4].setIcon(getIcon("RedChevron.png"));
            squares[7][5].setIcon(getIcon("RedTriangle.png"));
            squares[7][6].setIcon(getIcon("RedPlus.png"));
            squares[6][0].setIcon(getIcon("RedArrow.png"));
            squares[6][2].setIcon(getIcon("RedArrow.png"));
            squares[6][4].setIcon(getIcon("RedArrow.png"));
            squares[6][6].setIcon(getIcon("RedArrow.png"));
        
        
        
        setLocationRelativeTo(null);
        setVisible(true); // set the frame visible
    }
    
    /**
     * Set icon to square by retrieving icon from file path
    **/
    public void addIcon(int row, int col, String iconFile){
        String iconPath = "/icons/";
        iconPath = iconPath+iconFile;
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath)); // load the image to imageIcon
        Image iconP = icon.getImage(); //transform it 
        Image iconP2 = iconP.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(iconP2); //transfer it back
        squares[row][col].setIcon(icon);
    }

     /**
     * Set square empty and remove an icon
    **/
    public void emptyIcon(){
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                squares[i][j].setIcon(null);
            }
        }
    }
    
    /**
     * Return icon to square by retrieving icon file name
    **/
    public ImageIcon getIcon(String file){
        String path = "/icons/";
        path = path+file;
        ImageIcon newIcon = new ImageIcon(getClass().getResource(path)); // load the image to imageIcon
        Image iconPiece = newIcon.getImage(); //transform it 
        Image iconPiece2 = iconPiece.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        newIcon = new ImageIcon(iconPiece2); //transfer it back
        return newIcon;
    }
    

    private void createMenu() {  // Create Menu objects to add to the MenuBar
        newGameMenu = new JButton("New Game");
        loadMenu = new JButton("Load");
        saveMenu = new JButton("Save");
    }
    
    private void addMenuBarMenus() {    // Add Menu to the MenuBar
        menuBar.add(newGameMenu);
        menuBar.add(loadMenu);
        menuBar.add(saveMenu);
    } 
    
    public JButton getNewGameMenu(){
        return newGameMenu;
    }
    
    public JButton getLoadMenu(){
        return loadMenu;
    }
    
    public JButton getSaveMenu(){
        return saveMenu;
    }
    
    public JButton[][] getSquareButton(){
        return squares;
    }
    
    public JLabel getPlayer1Name(){
        return player1Name;
    } 
    
    public JLabel getPlayer2Name(){
        return player2Name;
    }
    
} 
