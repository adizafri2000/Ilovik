import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;



public class Controller implements ActionListener {
    private Game game;
    private View view;
    private Player player1;
    private Player player2;
    private Board board;
    private Square start;
    private Square end;
    private Square sq;
    private boolean pieceClick = false;

    
    public Controller(View view,Game game, Player player1, Player player2, Board board){
        this.view = view;
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        enableBoardButtons(false);
    }
    
    public void initController(){
       
       view.getNewGameMenu().addActionListener(e -> createNewGame());
       view.getLoadMenu().addActionListener(e -> loadGame());
       view.getSaveMenu().addActionListener(e -> saveGame());
      
    }

    public void initNewController(){
       view.getNewGameMenu().addActionListener(e -> createNewGame());
       view.getLoadMenu().addActionListener(e -> loadGame());
       view.getSaveMenu().addActionListener(e -> saveGame());
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                view.getSquareButton()[i][j].addActionListener(this);
                view.getSquareButton()[i][j].setActionCommand(i+""+j);
            }
        }
    }
    
    private void createNewGame(){
        //view.setVisible(false); //close previous board
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                view.getSquareButton()[i][j].addActionListener(this);
                view.getSquareButton()[i][j].setActionCommand(i+""+j);
            }
        }
        //View view = new View(); //create new board
        /*Player player1 = new Player("", 'b'); //player 1 use blue piece
        Player player2 = new Player("", 'r'); //player 2 use red piece
        Board board = new Board(player1, player2,false);
        Game game = new Game(board);
        Controller controller = new Controller(view, game, player1, player2, board);
        controller.initNewController();*/
        //
        
        String namePlayer1 = JOptionPane.showInputDialog("Enter name of player 1:");
        Player blue = new Player(namePlayer1, 'b');
        view.getPlayer1Name().setText("Player 1: " + namePlayer1);
        //game.getBoard().getP2().setTurn(true);
        
        String namePlayer2 = JOptionPane.showInputDialog("Enter name of player 2:");
        Player red = new Player(namePlayer2, 'r');
        view.getPlayer2Name().setText("Player 2: " + namePlayer2);
        Board b = new Board(blue, red, false);

        //BLUE PLAYER GERAK DULU, BOLEH JE NAK TUKAR
        b.getP1().setTurn(true);

        game = new Game(b);
        setGame(game);
        enableBoardButtons(true);
        setViewBoardIcons();
        /*
        player1.setName(namePlayer1);
        player2.setName(namePlayer2);
        System.out.println(player1.getName() + " " + player1.getSide());
        System.out.println(player2.getName() + " " + player2.getSide()); */
    }
    
   private void loadGame(){
        JOptionPane.showMessageDialog(null, "load game");
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                view.getSquareButton()[i][j].addActionListener(this);
                view.getSquareButton()[i][j].setActionCommand(i+""+j);
            }
        }
        //view.getLoadMenu();
        if (game.loadExists()){
            try{
                Board b = game.load();
                game = new Game(b);
                setGame(game);
                setViewBoardIcons();
                
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
            
        }
        else{
            JOptionPane.showMessageDialog(null, "file not exist.");
        }
    }
    
    private void saveGame(){
        //game.save();
        JOptionPane.showMessageDialog(null, "save game");
        JOptionPane.showMessageDialog(null, "GAME SAVED SUCCESSFULLY");
    }
    
    public void actionPerformed(ActionEvent e){
        String action = e.getActionCommand();
        char rowClicked = action.charAt(0);
        char colClicked = action.charAt(1);
        int row = Character.getNumericValue(rowClicked);
        int col = Character.getNumericValue(colClicked);
        movePiece(row, col);
    }
    
    public void enableBoardButtons(boolean condition){
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                view.getSquareButton()[i][j].setEnabled(condition);
            }
        }
    }

    /**
     * Sets all pieces' respective icon into the board buttons in the view.
     * Icons follow the pieces contained in the board (from model part).
     */
    public void setViewBoardIcons(){
        System.out.println("\nENTERING SETVIEWBOARDICONS()\n");
        Square temp;
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                temp = game.getBoard().getSquareList()[i][j];
                if (temp.isOccupied()){
                    view.addIcon(i, j, temp.getPiece().getIconFile());
                }
                else{
                    //view.addIcon(i, j, null);
                }
            }
        }
    }

    public void emptyViewBoardIcons(){
        System.out.println("\nENTERING EMPTYVIEWBOARDICONS()\n");
        view.emptyIcon();
    }

    

    public void changeTurns(){
        if(game.getBoard().getP1().isTurn()){
            game.getBoard().getP1().updateMoves();
            game.getBoard().getP1().setTurn(false);
            game.getBoard().getP2().setTurn(true);
            
        }
        else{
            game.getBoard().getP2().updateMoves();
            game.getBoard().getP2().setTurn(false);
            game.getBoard().getP1().setTurn(true);
        }

        //game.getBoard().flip();
    }
    
    private void movePiece(int i, int j){
    try{
        if (pieceClick == false){
            if (game.getBoard().getSquareList()[i][j].isOccupied()){ //occupied square
                if (game.getBoard().getP1().getSide() == 'b' && game.getBoard().getP1().isTurn() == true &&
                game.getBoard().getSquareList()[i][j].getPiece().getName().charAt(0) == 'b'){
                    start = game.getBoard().getSquareList()[i][j];
                    view.getSquareButton()[i][j].setBorder(new LineBorder(Color.RED, 4));
                    pieceClick = true;
                    System.out.println("HERE AT BLUE");
                    System.out.println(start.getPiece().getName());
                }
                else if (game.getBoard().getP2().getSide() == 'r' && game.getBoard().getP2().isTurn() == true &&
                game.getBoard().getSquareList()[i][j].getPiece().getName().charAt(0) == 'r'){
                    view.getSquareButton()[i][j].setBorder(new LineBorder(Color.RED, 4));
                    start = game.getBoard().getSquareList()[i][j];
                    pieceClick = true;
                    System.out.println("HERE AT RED");
                    System.out.println(start.getPiece().getName());
                }
                else
                    JOptionPane.showMessageDialog(null, "Not your turn yet!");
            }
            else
                 System.out.println("click on empty square");
        }
        else if (pieceClick == true){
                if(start.getY() == i && start.getX() == j ){ //deselect square
                    view.getSquareButton()[i][j].setBorder(new LineBorder(Color.WHITE));
                    pieceClick = false;
                    System.out.println("DESELECT");
                    
                } 
                else{
                end = game.getBoard().getSquareList()[i][j];
                        boolean isMoveValid = start.getPiece().move(start, end); 
                        if (isMoveValid){
                            //selected square is empty
                            if (!end.isOccupied()){
                                //add icon to board and set icon
                                game.getBoard().getSquareList()[end.getY()][end.getX()].setPiece(start.getPiece());
                                view.getSquareButton()[end.getY()][end.getX()].setIcon(view.getIcon(game.getBoard().getSquareList()[start.getY()][start.getX()].getPiece().getIconFile()));
                                
                                //remove icon from board and remove icon
                                view.getSquareButton()[start.getY()][start.getX()].setBorder(new LineBorder(Color.WHITE));
                                game.getBoard().getSquareList()[start.getY()][start.getX()].setPiece(null);
                                view.getSquareButton()[start.getY()][start.getX()].setIcon(null);
                                
                                pieceClick = false;
                                System.out.println("Valid Move");
                                
                            }  //player blue turn and kill the opponent(red) piece
                            else if (end.isOccupied() && end.getPiece().getName().charAt(0) == 'r' && game.getBoard().getP1().isTurn() == true){
                                //capture sun and player 1 wins
                    if (end.getPiece().getName().equals("rSun")){
                                     //set opponent piece to captured
                                    game.getBoard().getSquareList()[end.getY()][end.getX()].getPiece().setCaptured(true);
                                    view.getSquareButton()[end.getY()][end.getX()].setIcon(null);
                                    
                                    //add icon to board and set icon
                                    game.getBoard().getSquareList()[end.getY()][end.getX()].setPiece(start.getPiece());
                                    view.getSquareButton()[end.getY()][end.getX()].setIcon(view.getIcon(game.getBoard().getSquareList()[start.getY()][start.getX()].getPiece().getIconFile()));
                                    
                                    //remove icon from previous square
                                    game.getBoard().getSquareList()[start.getY()][start.getX()].setPiece(null);
                                    view.getSquareButton()[start.getY()][start.getX()].setIcon(null);
                                    
                                    view.getSquareButton()[start.getY()][start.getX()].setBorder(new LineBorder(Color.WHITE));
                                    System.out.println("RDEAD???");
                                    
                                     JOptionPane.showMessageDialog(null, "!!PLAYER 2 WINS!!");
                                     int dialogResult = JOptionPane.showConfirmDialog(null, "Create new game? Select no to exit game.", "Start new game?", JOptionPane.YES_NO_OPTION);
                                     if (dialogResult == 0) {
                                         System.out.println("Yes option");
                                         createNewGame();
                                     }
                                     else {
                                         System.out.println("No Option");
                                         System.exit(0);
                                     }
                                }
                                else{
                                    //set opponent piece to captured
                                    game.getBoard().getSquareList()[end.getY()][end.getX()].getPiece().setCaptured(true);
                                    view.getSquareButton()[end.getY()][end.getX()].setIcon(null);
                                    
                                    //add icon to board and set icon
                                    game.getBoard().getSquareList()[end.getY()][end.getX()].setPiece(start.getPiece());
                                    view.getSquareButton()[end.getY()][end.getX()].setIcon(view.getIcon(game.getBoard().getSquareList()[start.getY()][start.getX()].getPiece().getIconFile()));
                                    
                                    //remove icon from previous square
                                    game.getBoard().getSquareList()[start.getY()][start.getX()].setPiece(null);
                                    view.getSquareButton()[start.getY()][start.getX()].setIcon(null);
                                    
                                    view.getSquareButton()[start.getY()][start.getX()].setBorder(new LineBorder(Color.WHITE));
                                    System.out.println("RDEAD???");
                                    
                                    //checkArrowFlip();
                                    //setPlayerTurn();
                                    pieceClick = false;
                                }
                            }//player red turn and kill the opponent(blue) piece
                            else if (end.isOccupied() && end.getPiece().getName().charAt(0) == 'b' && game.getBoard().getP2().isTurn() == true){
                                if (end.getPiece().getName().equals("bSun")){
                                    game.getBoard().getSquareList()[end.getY()][end.getX()].getPiece().setCaptured(true);
                                    
                                    view.getSquareButton()[end.getY()][end.getX()].setIcon(null);
                                    
                                    //add icon to board and set icon
                                    game.getBoard().getSquareList()[end.getY()][end.getX()].setPiece(start.getPiece());
                                    view.getSquareButton()[end.getY()][end.getX()].setIcon(view.getIcon(game.getBoard().getSquareList()[start.getY()][start.getX()].getPiece().getIconFile()));
                                    
                                    //remove icon from previous square
                                    game.getBoard().getSquareList()[start.getY()][start.getX()].setPiece(null);
                                    view.getSquareButton()[start.getY()][start.getX()].setIcon(null);
                                    
                                    view.getSquareButton()[start.getY()][start.getX()].setBorder(new LineBorder(Color.WHITE));
                                    System.out.println("BDEAD?");
                                     JOptionPane.showMessageDialog(null, "!!PLAYER 2 WINS!!");
                                     int dialogResult = JOptionPane.showConfirmDialog(null, "Create new game? Select no to exit game.", "Start new game?", JOptionPane.YES_NO_OPTION);
                                     if (dialogResult == 0) {
                                         System.out.println("Yes option");
                                         createNewGame();
                                     }
                                     else {
                                         System.out.println("No Option");
                                         System.exit(0);
                                     }
                                 }
                                
                                 else{
                                    //set opponent piece to captured
                                    game.getBoard().getSquareList()[end.getY()][end.getX()].getPiece().setCaptured(true);
                                    
                                    view.getSquareButton()[end.getY()][end.getX()].setIcon(null);
                                    
                                    //add icon to board and set icon
                                    game.getBoard().getSquareList()[end.getY()][end.getX()].setPiece(start.getPiece());
                                    view.getSquareButton()[end.getY()][end.getX()].setIcon(view.getIcon(game.getBoard().getSquareList()[start.getY()][start.getX()].getPiece().getIconFile()));
                                    
                                    //remove icon from previous square
                                    game.getBoard().getSquareList()[start.getY()][start.getX()].setPiece(null);
                                    view.getSquareButton()[start.getY()][start.getX()].setIcon(null);
                                    
                                    view.getSquareButton()[start.getY()][start.getX()].setBorder(new LineBorder(Color.WHITE));
                                    System.out.println("BDEAD?");
                                    
                                    //checkArrowFlip();
                                    //setPlayerTurn();
                                    pieceClick = false;
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "The square has the same piece color!");
                            }
                            
                            setPlayerTurn();
                }
                else{
                                JOptionPane.showMessageDialog(null, "Invalid move!");
                                pieceClick = true;
                            }   
                    //setPlayerTurn();
                                //checkArrowFlip(); 
                                    
            }
            
            
            System.out.println("start: " + start.getY() + "," + start.getX() + " end: " + end.getY() + "," + end.getX());
        }
    }catch (NullPointerException ex){ex.getMessage();}
    }
    
    /*
    private void checkArrowFlip(){
        if (game.getBoard().getSquareList()[][])
        for (int j = 0; j < 7; j++){
                if (game.getBoard().getSquareList()[0][j].getPiece().getName().equals("bArrow")){
                    view.getSquareButton()[0][j].setIcon(null);
                    view.addIcon(0,j,"BlueArrow.png");
                }
                else if (game.getBoard().getSquareList()[0][j].getPiece().getName().equals("rArrow")){
                    view.getSquareButton()[0][j].setIcon(null);
                    view.addIcon(0,j,"RedArrow2.png");
                }
        }
        for (int k = 0; k < 7; k++){
                if (game.getBoard().getSquareList()[7][k].getPiece().getName().equals("bArrow")){
                    view.getSquareButton()[7][k].setIcon(null);
                    view.addIcon(7,k,"BlueArrow2.png");
                }
                else if (game.getBoard().getSquareList()[7][k].getPiece().getName().equals("rArrow")){
                    view.getSquareButton()[7][k].setIcon(null);
                    view.addIcon(7,k,"RedArrow.png");
                }
        }
    } */
    
    private void setPlayerTurn(){
        
        if (game.getBoard().getP1().isTurn()){
            game.getBoard().getP1().setTurn(false);
            game.getBoard().getP2().setTurn(true); 
            game.getBoard().getP1().updateMoves();
            //checkArrowFlip();
        }
        else if (game.getBoard().getP2().isTurn()){
            game.getBoard().getP2().setTurn(false);
            game.getBoard().getP1().setTurn(true);
            game.getBoard().getP2().updateMoves();
            //checkArrowFlip();
        }


        
        
        if ((game.getBoard().getP1().getMoves()==2)&&(game.getBoard().getP2().getMoves() == 2)){
            game.getBoard().getP1().setMoves(0);
            game.getBoard().getP2().setMoves(0);
            swapPiece();
        }  
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
    private void swapPiece(){
        System.out.println("in SWAPAIECE()");
        //game.getBoard().debug();
        game.getBoard().getP1().debug();
        game.getBoard().getP2().debug();
        System.out.println("\n\n\nJUMPER\n\n\n");
        Board bo = game.getBoard();
        bo.swapPieces();
        bo.getP1().debug();
        bo.getP2().debug();
        game = new Game(bo);
        //game.setBoard(bo);
        
        game.getBoard().debug();
        emptyViewBoardIcons();
        setViewBoardIcons();

        /*for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                if (game.getBoard().getSquareList()[i][j].isOccupied() == true){
                    
                    sq = game.getBoard().getSquareList()[i][j];
                    String pieceName = sq.getPiece().getName();
                    System.out.println("in SWAPAIECE() 2");
                    
                    if (pieceName.equals("bTriangle")){
                        game.getBoard().getSquareList()[i][j].setPiece(null);
                        game.getBoard().getSquareList()[i][j].setPiece(game.getBoard().getP1().getPieceList().get(0));
                        view.getSquareButton()[i][j].setIcon(null);
                        view.addIcon(i, j, "BluePlus.png");
                    }
                    else if (pieceName.equals("rTriangle")){
                        game.getBoard().getSquareList()[i][j].setPiece(null);
                        game.getBoard().getSquareList()[i][j].setPiece(game.getBoard().getP2().getPieceList().get(0));
                        view.getSquareButton()[i][j].setIcon(null);
                        view.addIcon(i, j, "RedPlus.png");
                    }
                    
                    else if (pieceName.equals("bPlus")){
                        game.getBoard().getSquareList()[i][j].setPiece(null);
                        game.getBoard().getSquareList()[i][j].setPiece(game.getBoard().getP1().getPieceList().get(1));
                        view.getSquareButton()[i][j].setIcon(null);
                        view.addIcon(i, j, "BlueTriangle.png");
                    }
                    else if (pieceName.equals("rPlus")){
                        game.getBoard().getSquareList()[i][j].setPiece(null);
                        game.getBoard().getSquareList()[i][j].setPiece(game.getBoard().getP2().getPieceList().get(1));
                        view.getSquareButton()[i][j].setIcon(null);
                        view.addIcon(i, j, "RedTriangle.png");
                    }
                }
            }   
        }*/
        
    }
    //test controller
    public static void main(String[] args){
        View v = new View();
        Game g = new Game();
        Player p1 = new Player("Kamal", 'b');
        p1.setTurn(true);
        Player p2 = new Player("Adli", 'r');
        Board b = new Board (p1, p2,false);
        g = new Game(b);
        Controller c = new Controller(v,g,p1,p2,b);
        c.initController();
    } 
}   
    // PLAYER 1 RED
    // PLAYER 2 BLUE

    /*
    Dalam repository ni, ada folder Official MVC Example, or boleh refer website Iffah bagi in whatsapp.
    Aku assume structure gini: 
    1. Controller add listeners to interactable components (JButton, Menu PLS REFER View.java) to do whatever is necessary.
        1.1 Figure out apa information is retrieved from view.
        1.2 Figure out what to do with the information.
        1.3 If model (Controller.game) is involved, retrieve, change whatever is necessary.
        1.4 For ALL changes made, kalau benda tu viewable (User can see it eg. Piece move to a different square, nama masing2 ada kat JFrame),
            change the respective components in view part.
    2. Interactable components (like buttons) in Controller.view kena tekan.
    3. Listeners (actionListener, menuListener) in Controller acivated.
    4. Necessary changes happen (UP TO US FIGURE OUT SENDIRI)
    5. Update the view.
    */

    /*
    REQUIRED FUNCTIONS (BELUM LISTENER LAGI) BY COMPONENTS, refer source file pls
    1. Menu newGameMenu
        1.1 CAN CREATE A NEW GAME
            1.1.1 SPECIFIC RESPONSIBLE MODEL CLASS: Game.Game()
        1.2 PROMPT JOPTIONPANE TO ASK PLAYERS' NAMES
            1.2.1 RESPONSIBLE MODEL CLASS: Player.Player()

    2. Menu loadMenu
        2.1 CAN CREATE NEW GAME WITH SPECIFIED PIECES AT SPECIFIED LOCATIONS WITH SPECIFIED PLAYER NAMES
            2.1.1 RESPONSIBLE MODEL CLASS: 
        2.2 IF FAILED, POPUP JOPTIONPANE TO DISPLAY ERROR MESSAGE (MACAM GUI, BUT SINCE CONTROLLER MANAGES LISTENERS, NI CONTROLLER PUNYA ROLE)
            2.2.1 RESPONSIBLE MODEL CLASS:

        
    3. Menu saveMenu
        3.1 CAN SAVE (THE CURRENTLY AVAILABLE PIECES, THEIR LOCATIONS, PLAYER NAMES, WHOSE TURN IS IT) IN A .txt FILE
            3.1.1 SPECIFIC RESPONSIBLE MODEL CLASS: Game.save()
        3.2 ONCE DONE, POPUP JOPTIONPANE TO DISPLAY MESSAGE: "GAME SAVED SUCCESSFULLY"

    4. JButton squares[][]
        4.1 CAN SET START SQUARE
        4.2 CAN SET END SQUARE
        4.3 CAN CHECK MOVEMENT VALIDITY
        4.4 CAN MOVE PIECE TO ANOTHER SQUARE
        4.5 CAN CHANGE BUTTON'S PIECE ICON

    5. JLabel player1Name
        5.1 DISPLAY RED PLAYER'S NAME
            5.1.1 RESPONSIBLE MODEL CLASS: Player.getName()

    6. JLabel player2Name
        6.1 DISPLAY BLUE PLAYER'S NAME
            6.1.1RESPONSIBLE MODEL CLASS: Player.getName()
    
    */


/*
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;



public class Controller implements ActionListener {
    //Model: Game tak siap load() and save() lagi
    private Game game;
    private View view;
    private Player player1;
    private Player player2;
    private Board board;
    private Square start;
    private Square end;
    private boolean pieceClick = false;

    
    public Controller(View view,Game game, Player player1, Player player2, Board board){
        this.view = view;
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
    }
    
    public void initController(){
       
       view.getNewGameMenu().addActionListener(e -> createNewGame());
       view.getLoadMenu().addActionListener(e -> loadGame());
       view.getSaveMenu().addActionListener(e -> saveGame());
        for(int i = 0; i < 8; i++){
           for(int j = 0; j < 7; j++){
               //view.getSquareButton()[i][j].addActionListener(this);
               //view.getSquareButton()[i][j].setActionCommand(i+""+j);
           }
        }
       
    }
    
    private void createNewGame(){
        view.setVisible(false); //close previous board
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                view.getSquareButton()[i][j].addActionListener(this);
                view.getSquareButton()[i][j].setActionCommand(i+""+j);
            }
        }
        View view = new View(); //create new board
        Player player1 = new Player("", 'b'); //player 1 use blue piece
        Player player2 = new Player("", 'r'); //player 2 use red piece
        Board board = new Board(player1, player2);
        Game game = new Game(board);
        Controller controller = new Controller(view, game, player1, player2, board);
        //controller.initController();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                view.getSquareButton()[i][j].addActionListener(this);
                view.getSquareButton()[i][j].setActionCommand(i+""+j);
            }
        }
        
        String namePlayer1 = JOptionPane.showInputDialog("Enter name of player 1:");
        view.getPlayer1Name().setText(namePlayer1);
        game.getBoard().getP1().setTurn(true);
        
        String namePlayer2 = JOptionPane.showInputDialog("Enter name of player 2:");
        view.getPlayer2Name().setText(namePlayer2);
        /*
        player1.setName(namePlayer1);
        player2.setName(namePlayer2);
        System.out.println(player1.getName() + " " + player1.getSide());
        System.out.println(player2.getName() + " " + player2.getSide()); */
    
    /*
   private void loadGame(){
        JOptionPane.showMessageDialog(null, "load game");
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                view.getSquareButton()[i][j].addActionListener(this);
                view.getSquareButton()[i][j].setActionCommand(i+""+j);
            }
        }
        view.getLoadMenu();
        if (game.loadExists()){
            //game.load(); //set gui
        }
        else{
            JOptionPane.showMessageDialog(null, "file not exist.");
        }
    /*
    public String giveIcon(int i,int j){
        return game.getBoard().getSquareList()[i][j].getPiece().getIconFile();
    } */
    
        /*
        retrieve save data
        create game object from save data
        Update view with game object
            1. player names
                1.1 view: jlabel player1name, player2name
                1.2 model: game.getboard.getP1().getName() & P2
                1.3 controller: getView().getplayer1Name().setText()
            2. board (squares ada piece/takda)
                2.1 view : squares[][] 
                2.2 model: game.getBoard()
                3.3 controller: getView().getSquares()[][]

                View:
                pv updateIcon(int i,int j,String s){
                    String iconPath = "/icons/";
                    iconPath = iconPath+s;
                    ImageIcon icon = new ImageIcon(getClass().getResource(iconPath)); // load the image to imageIcon
                    Image iconP = icon.getImage(); //transform it 
                    Image idk = iconP.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(idk); //transfer it back
                    squares[i][j].setIcon(icon);
                }

                Controller:
                pv arrangeSquares(){
                    for(int i=0;i<8;i++){
                        for(int j=0;j<7;j++){
                            if(getView().getSquares()[i][j].isOccupied())
                                String fileName = getView().getSquares()[i][j].getPiece().getIconFile();
                                getView.updateIcon(i,j,fileName);
                        }
                    }       
                }
                
            3. inverted ke tak(siapa yang move)
        */
    
    /*
    private void saveGame(){
        //game.save();
        JOptionPane.showMessageDialog(null, "save game");
        JOptionPane.showMessageDialog(null, "GAME SAVED SUCCESSFULLY");
    }
    
    public void actionPerformed(ActionEvent e){
        String action = e.getActionCommand();
        char rowClicked = action.charAt(0);
        char colClicked = action.charAt(1);
        int row = Character.getNumericValue(rowClicked);
        int col = Character.getNumericValue(colClicked);
        movePiece(row, col);
    }

    public void loadBoardIcons(){
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                if(game.getBoard().getSquareList()[i][j].isOccupied())
                    view.addIcon(i, j, game.getBoard().getSquareList()[i][j].getPiece().getIconFile());
                else
                    view.getSquareButton()[i][j].setIcon(null);
            }
        }
    }
    
    public void flipBoard(){
        if(game.getBoard().getP1().isTurn()){
            
        }
            //flip, blue player bottom, red up
        else{

        }
            //flip, red player bottom, blue up
    }

    
    private void movePiece(int i, int j){
        //&&  board.squareList[i][j] != null
        
        try{
            if (pieceClick == false){
                if (game.getBoard().getSquareList()[i][j].isOccupied()){ //occupied square
                    if (game.getBoard().getP1().getSide() == 'b' && game.getBoard().getP1().isTurn() == true &&
                    game.getBoard().getSquareList()[i][j].getPiece().getName().charAt(0) == 'b'){
                        start = game.getBoard().getSquareList()[i][j];
                        pieceClick = true;
                        view.getSquareButton()[i][j].setBorder(new LineBorder(Color.RED));
                        System.out.println("HERE AT BLUE");
                        System.out.println(start.getPiece().getName());
                        System.out.println("This is an arrow: "+(start.getPiece() instanceof Arrow));
                        //System.out.println(start.getY() + " " + start.getX());
                    }
                    else if (game.getBoard().getP2().getSide() == 'r' && game.getBoard().getP2().isTurn() == true &&
                    game.getBoard().getSquareList()[i][j].getPiece().getName().charAt(0) == 'r'){
                        start = game.getBoard().getSquareList()[i][j];
                        pieceClick = true;
                        view.getSquareButton()[i][j].setBorder(new LineBorder(Color.RED));
                        System.out.println("HERE AT RED");
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Not your turn yet!");
                }
                else
                    System.out.println("click on empty square");
            }
            else if (pieceClick == true){
                if(start.getY() == i && start.getX() == j ){ //deselect square
                    view.getSquareButton()[i][j].setBorder(new LineBorder(Color.WHITE));
                    pieceClick = false;
                    System.out.println("DESELECT");
                    
                } 
                else{
                        end = game.getBoard().getSquareList()[i][j];

                        if (end.isOccupied()){
                            System.out.println(end.getPiece().getName());
                            JOptionPane.showMessageDialog(null, "The square is occupied!");
                        }
                        else{
                            System.out.println("Selected square is empty");
                        }
                        
                        boolean isMoveValid = start.getPiece().move(start, end); 
                        if(isMoveValid){
                            pieceClick = false;
                            System.out.println("Valid Move");
                        }
                        else
                            System.out.println("Invalid Move");
                        
                        view.getSquareButton()[start.getY()][start.getX()].setBorder(new LineBorder(Color.WHITE));
                                    System.out.println("start: " + start.getY() + "," + start.getX()
                                    + " end: " + end.getY() + "," + end.getX());
                }
                    
            }
        }catch (NullPointerException ex){ex.printStackTrace();}
    }

    //test controller
    public static void main(String[] args){
        View v = new View();
        Game g = new Game();
        Player p1 = new Player("Kamal", 'b');
        p1.setTurn(true);
        Player p2 = new Player("Adli", 'r');
        Board b = new Board (p1, p2);
        g = new Game(b);
        Controller c = new Controller(v,g,p1,p2,b);
        c.initController();
    } 
} */
