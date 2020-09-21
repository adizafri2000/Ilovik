


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


import javax.swing.*;
import java.awt.*;


public class View extends JFrame {
    private JFrame frame;
	private JMenuBar menuBar;
    private JButton newGameMenu, loadMenu, saveMenu;
    public JButton[][] squares = new JButton[8][7];  // components
    
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
		//setPreferredSize(new Dimension(100, 100));
        setMinimumSize(getSize());
        //getContentPane().setLayout(new xBoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        player1Name = new JLabel("Kamal");
        player2Name = new JLabel("Ali");
        
        boardPanel = new JPanel(new GridLayout(8,7));
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
				//squares[i][j].setPreferredSize(new Dimension(100, 100));
                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(Color.BLACK);
                } else {
                    squares[i][j].setBackground(Color.WHITE);
                }   
                squares[i][j].setName(Integer.toString(i)+Integer.toString(j));
                boardPanel.add(squares[i][j]);
            }
        }
        home.add(boardPanel);
        home.add(player2Name);
        add(home);
        
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
        /*
        //squares[0][0].add(new JLabel(BluePlus));
        //squares[0][0].setHorizontalAlignment(SwingConstants.CENTER);
        squares[0][0].add(new JLabel(BluePlus,SwingConstants.CENTER));      
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
        */
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
        
        setLocationRelativeTo(null);
        setVisible(true); // set the frame visible
    }

    /**
     * Sets a button with an icon of a piece, respective to the pieces' arrangement in model, given by controller
     * @param row row of square
     * @param col column of square
     * @param iconFile file name for the icon of the piece set in this square in the model
     */
    public void addIcon(int row, int col, String iconFile){
        String iconPath = "/icons/";
        iconPath = iconPath+iconFile;
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath)); // load the image to imageIcon
        Image iconP = icon.getImage(); //transform it 
        Image idk = iconP.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(idk); //transfer it back
        squares[row][col].setIcon(icon);
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
    /*
    public View() {
        super("Ilovik Webale Chess");
        setSize(800, 800);
        setMinimumSize(getSize());
        //getContentPane().setLayout(new xBoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        player1Name = new JLabel("Kamal");
        player2Name = new JLabel("Ali");
        
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
        boardPanel = new JPanel(new GridLayout(8,7));
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
        home.add(boardPanel);
        home.add(player2Name);
        add(home);
        
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

        //squares[0][0].add(new JLabel(BluePlus));
        //squares[0][0].setHorizontalAlignment(SwingConstants.CENTER);
        squares[0][0].add(new JLabel(BluePlus,SwingConstants.CENTER));      
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
        
        setLocationRelativeTo(null);
        setVisible(true); // set the frame visible
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
    
    
   */
    
} 
