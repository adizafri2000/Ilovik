


/* RESIZE FRAME, SET ICON CENTER , ADD LABEL PLAYER NAME , SET JPANEL1 ,JPANEL2 */
/***
-set icon dalam jButton:
 --> controller return square.occupied jadi false, kosongkan icon
 --> the same to controller return apa2 piece to a newer location
        -->set icon to new piece punya icon
-icon punya file name, controller bagi jgn set sendiri okay
PLAYER 1 RED
PLAYER 2 BLUE
***/


import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;


public class View extends JFrame {
    private JFrame frame;       // Main window
    private JMenuBar menuBar;
    private JButton newGameMenu, loadMenu, saveMenu;
    public JButton[][] squares = new JButton[8][7];  // components
    private JLabel instruction;
    /**
     * Holds the text for RED player's name
     */
    private JLabel player1Name;

    /**
     * Holds the text for BLUE player's name
     */
    private JLabel player2Name; 
    private JPanel boardPanel, home;
    
    public View() {
        super("Ilovik Webale Chess");
        setSize(800, 800);
        setMinimumSize(getSize());
        //getContentPane().setLayout(new xBoxLayout(getContentPane(),BoxLayout.Y_AXIS));
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
        setJMenuBar(menuBar);  // Add MenuBar to the Frame
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
        
        /*

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
        
        // Blue Icon Pieces
        ImageIcon BluePlus = new ImageIcon(getClass().getResource(pathBP)); // load the image to imageIcon
        Image bluePlus = BluePlus.getImage(); //transform it 
        Image newBluePlus = bluePlus.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        BluePlus = new ImageIcon(newBluePlus); //transfer it back
        
        ImageIcon BlueTriangle = new ImageIcon(getClass().getResource(pathBT)); // load the image to imageIcon
        Image blueTriangle = BlueTriangle.getImage(); //transform it 
        Image newBlueTriangle = blueTriangle.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        BlueTriangle = new ImageIcon(newBlueTriangle); //transfer it back
        
        ImageIcon BlueChevron = new ImageIcon(getClass().getResource(pathBC));// load the image to imageIcon
        Image blueChevron = BlueChevron.getImage(); //transform it 
        Image newBlueChevron = blueChevron.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        BlueChevron = new ImageIcon(newBlueChevron); //transfer it back
        
        ImageIcon BlueSun = new ImageIcon(getClass().getResource(pathBS));// load the image to imageIcon
        Image blueSun = BlueSun.getImage(); //transform it 
        Image newBlueSun = blueSun.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        BlueSun = new ImageIcon(newBlueSun); //transfer it back
        
        ImageIcon BlueArrow = new ImageIcon(getClass().getResource(pathBA));// load the image to imageIcon
        Image blueArrow = BlueArrow.getImage(); //transform it 
        Image newBlueArrow = blueArrow.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        BlueArrow = new ImageIcon(newBlueArrow); //transfer it back
        
        // Red Icon Pieces
        ImageIcon RedPlus = new ImageIcon(getClass().getResource(pathRP));// load the image to imageIcon
        Image redPlus = RedPlus.getImage(); //transform it 
        Image newRedPlus = redPlus.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        RedPlus = new ImageIcon(newRedPlus); //transfer it back
        
        ImageIcon RedTriangle = new ImageIcon(getClass().getResource(pathRT));// load the image to imageIcon
        Image redTriangle = RedTriangle.getImage(); //transform it 
        Image newRedTriangle = redTriangle.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        RedTriangle = new ImageIcon(newRedTriangle); //transfer it back
        
        ImageIcon RedChevron = new ImageIcon(getClass().getResource(pathRC)); // load the image to imageIcon
        Image redChevron = RedChevron.getImage(); //transform it 
        Image newRedChevron = redChevron.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        RedChevron = new ImageIcon(newRedChevron); //transfer it back
        
        ImageIcon RedSun = new ImageIcon(getClass().getResource(pathRS)); // load the image to imageIcon
        Image redSun = RedSun.getImage(); //transform it 
        Image newRedSun = redSun.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        RedSun = new ImageIcon(newRedSun); //transfer it back
        
        ImageIcon RedArrow = new ImageIcon(getClass().getResource(pathRA));// load the image to imageIcon
        Image redArrow = RedArrow.getImage(); //transform it 
        Image newRedArrow = redArrow.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        RedArrow = new ImageIcon(newRedArrow); //transfer it back
        
        squares[0][0].setIcon(BluePlus);      
        squares[0][1].setIcon(BlueTriangle);
        squares[0][2].setIcon(BlueChevron);
        squares[0][3].setIcon(BlueSun);
        squares[0][4].setIcon(BlueChevron);
        squares[0][5].setIcon(BlueTriangle);
        squares[0][6].setIcon(BluePlus);
        squares[1][0].setIcon(BlueArrow);
        squares[1][2].setIcon(BlueArrow);
        squares[1][4].setIcon(BlueArrow);
        squares[1][6].setIcon(BlueArrow);
        
        squares[7][0].setIcon(RedPlus);
        squares[7][1].setIcon(RedTriangle);
        squares[7][2].setIcon(RedChevron);
        squares[7][3].setIcon(RedSun);
        squares[7][4].setIcon(RedChevron);
        squares[7][5].setIcon(RedTriangle);
        squares[7][6].setIcon(RedPlus);
        squares[6][0].setIcon(RedArrow);
        squares[6][2].setIcon(RedArrow);
        squares[6][4].setIcon(RedArrow);
        squares[6][6].setIcon(RedArrow);
        */
        
        setLocationRelativeTo(null);
        setVisible(true); // set the frame visible
    }
    
    public void addIcon(int row, int col, String iconFile){
        String iconPath = "/icons/";
        iconPath = iconPath+iconFile;
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath)); // load the image to imageIcon
        Image iconP = icon.getImage(); //transform it 
        Image iconP2 = iconP.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(iconP2); //transfer it back
        squares[row][col].setIcon(icon);
    }
    
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
    
     public static void main(String[] args) {
        //new View();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new View();
            }
        });
    }  
    
    
} 
