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
import java.awt.event.*;
import java.io.*;

public class View extends JFrame {
    private JFrame frame;  		// Main window
    private MenuBar menuBar;
    private Menu newGameMenu, loadMenu, saveMenu;
    private JButton squares[][] = new JButton[8][7];  // components

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
		player1Name = new JLabel("Kamal");
		player2Name = new JLabel("Ali");
        
		boardPanel = new JPanel(new GridLayout(8,7));
		home = new JPanel();
		home.setLayout(new BoxLayout(home,BoxLayout.Y_AXIS));
		setBackground(Color.BLACK);
		setForeground(Color.RED);
		
        menuBar = new MenuBar();
        createMenu();
        addMenuBarMenus(); 			// Add Menu to MenuBar
        setMenuBar(menuBar); 		// Add MenuBar to the Frame
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
        
        setVisible(true); // set the frame visible
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
	
	public Menu getNewGameMenu(){
		return newGameMenu;
	}
	
	public Menu getLoadMenu(){
		return loadMenu;
	}
	
	public Menu getSaveMenu(){
		return saveMenu;
	}
	
	public JButton [][] getSquareButton(){
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