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
        enableSaveButton(false);
    }
    
    public void initController(){
       
       view.getNewGameMenu().addActionListener(e -> createNewGame());
       view.getLoadMenu().addActionListener(e -> loadGame());
       view.getSaveMenu().addActionListener(e -> saveGame());
      
    }

    /*public void initNewController(){
       view.getNewGameMenu().addActionListener(e -> createNewGame());
       view.getLoadMenu().addActionListener(e -> loadGame());
       view.getSaveMenu().addActionListener(e -> saveGame());
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                view.getSquareButton()[i][j].addActionListener(this);
                view.getSquareButton()[i][j].setActionCommand(i+""+j);
            }
        }
    }*/

    private void resetClick(){
        pieceClick = false;
    }
    
    private void createNewGame(){
        //view.setVisible(false); //close previous boar
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                view.getSquareButton()[i][j].addActionListener(this);
                view.getSquareButton()[i][j].setActionCommand(i+""+j);
            }
        }
        //game.getBoard().emptyAllSquares();
        
        String namePlayer1 = JOptionPane.showInputDialog("Enter name of player 1:");
        Player blue = new Player(namePlayer1, 'b');
        view.getPlayer1Name().setText("Player 1: " + namePlayer1);
        //game.getBoard().getP2().setTurn(true);
        
        String namePlayer2 = JOptionPane.showInputDialog("Enter name of player 2:");
        Player red = new Player(namePlayer2, 'r');
        view.getPlayer2Name().setText("Player 2: " + namePlayer2);
        Board b = new Board(red, blue, false);
        b.getP2().setTurn(true);

        game = new Game(b);
        setGame(game);
        enableBoardButtons(true);
        enableSaveButton(true);
        emptyViewBoardIcons();
        setViewBoardIcons();
        resetClick();
    }
    
   private void loadGame(){
        JOptionPane.showMessageDialog(null, "load game");
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++){
                view.getSquareButton()[i][j].addActionListener(this);
                view.getSquareButton()[i][j].setActionCommand(i+""+j);
            }
        }
        if (game.loadExists()){
            try{
                Board b = game.load();
                b.debug();
                game = new Game(b);
                setGame(game);
                emptyViewBoardIcons();
                setViewBoardIcons();
                
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
            enableBoardButtons(true);
            enableSaveButton(true);
            resetClick();
            
        }
        else{
            JOptionPane.showMessageDialog(null, "file not exist.");
        }
    }
    
    private void saveGame(){
        //game.save();
        if(game.loadExists()){
            try{
                game.save();
            }catch(IOException e){}
        }
        else{
            try {
                game.createSaveFile();
                game.save();
            } catch (IOException e) {}
            
        }

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

    public void enableSaveButton(boolean condition){
        view.getSaveMenu().setEnabled(condition);
        view.getSaveMenu().setVisible(condition);
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
    //try{
        if (pieceClick == false){
            if (game.getBoard().getSquareList()[i][j].isOccupied()){ //occupied square
                if (game.getBoard().getP2().isTurn() &&
                game.getBoard().getSquareList()[i][j].getPiece().getSide() == 'b'){
                    start = game.getBoard().getSquareList()[i][j];
                    view.getSquareButton()[i][j].setBorder(new LineBorder(Color.RED, 4));
                    pieceClick = true;
                    System.out.println("HERE AT BLUE");
                    System.out.println(start.getPiece().getName());
                }
                else if (game.getBoard().getP1().isTurn() &&
                game.getBoard().getSquareList()[i][j].getPiece().getSide() == 'r'){
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
        else{
                if(start.getY() == i && start.getX() == j ){ //deselect square
                    view.getSquareButton()[i][j].setBorder(new LineBorder(Color.WHITE));
                    pieceClick = false;
                    System.out.println("DESELECT");
                    
                } 
                else{
                end = game.getBoard().getSquareList()[i][j];
                        boolean isMoveValid = start.getPiece().move(start, end); 
                        boolean pathwayCleared = game.getBoard().isClearPathway(start, end);
                        if (isMoveValid && pathwayCleared){
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
                            else if (end.isOccupied() && end.getPiece().getName().charAt(0) == 'r' && game.getBoard().getP2().isTurn() == true){
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
                            else if (end.isOccupied() && end.getPiece().getName().charAt(0) == 'b' && game.getBoard().getP1().isTurn() == true){
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
    //}catch (NullPointerException ex){ex.getMessage();}
    }
    
    private void setPlayerTurn(){
        
        if (game.getBoard().getP1().isTurn()){
            game.getBoard().getP1().setTurn(false);
            game.getBoard().getP2().setTurn(true); 
            game.getBoard().getP1().updateMoves();
        }
        else{
            game.getBoard().getP2().setTurn(false);
            game.getBoard().getP1().setTurn(true);
            game.getBoard().getP2().updateMoves();
        }

        System.out.printf("P1: %d moves\n",game.getBoard().getP2().getMoves());
        System.out.printf("P2: %d moves\n",game.getBoard().getP1().getMoves());

        if ((game.getBoard().getP1().getMoves()==2)&&(game.getBoard().getP2().getMoves()==2)){
            swapPiece();
            game.getBoard().getP1().setMoves(0);
            game.getBoard().getP2().setMoves(0);
            
        } 
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
    private void swapPiece(){
        System.out.println("in SWAPPIECE()");
        Board bo = game.getBoard();
        bo.swapPieces();
        game = new Game(bo);
        emptyViewBoardIcons();
        setViewBoardIcons();
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