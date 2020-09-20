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
               view.getSquareButton()[i][j].addActionListener(this);
               view.getSquareButton()[i][j].setActionCommand(i+""+j);
           }
        }
       
    }
    
    private void createNewGame(){
        view.setVisible(false); //close previous board
        
        View view = new View(); //create new board
        Player player1 = new Player("", 'b'); //player 1 use blue piece
        Player player2 = new Player("", 'r'); //player 2 use red piece
        Board board = new Board(player1, player2);
        Game game = new Game(board);
        Controller controller = new Controller(view, game, player1, player2, board);
        controller.initController();
        
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
    }
    
   private void loadGame(){
        JOptionPane.showMessageDialog(null, "load game");
        
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
    }
    
    private void saveGame(){
        //game.save();
        JOptionPane.showMessageDialog(null, "save game");
        JOptionPane.showMessageDialog(null, "GAME SAVED SUCCESSFULLY");
    }
    
    public void actionPerformed(ActionEvent e){
        String action = e.getActionCommand();
        JButton baction = (JButton)e.getSource();
        action = baction.getName();
        char rowClicked = action.charAt(0);
        char colClicked = action.charAt(1);
        int row = Character.getNumericValue(rowClicked);
        int col = Character.getNumericValue(colClicked);
        movePiece(row, col);
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
                else{
                    JOptionPane.showMessageDialog(null, "Not your turn yet!");
                } 
            }
            else{
                 System.out.println("click on empty square");
            }
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
                    }
                    else{
                        System.out.println("Selected square is empty");
                    }
                    
                    boolean isMoveValid = start.getPiece().move(start, end); 
                    if(isMoveValid){
                        pieceClick = false;
                        System.out.println("Valid Move");
                    }
                    else{
                        System.out.println("Invalid Move");
                    }
                    view.getSquareButton()[start.getY()][start.getX()].setBorder(new LineBorder(Color.WHITE));
                                System.out.println("start: " + start.getY() + "," + start.getX()
                                + " end: " + end.getY() + "," + end.getX());

                    /*
                    if(!end.isOccupied()){
                        view.getSquareButton()[start.getY()][start.getX()].setBorder(new LineBorder(Color.WHITE)); //set border non-red
                        
                            //isMoveValid = game.getBoard().getSquareList()[i][j].getPiece().move(start, end);
                            if (isMoveValid){
                                view.getSquareButton()[start.getY()][start.getX()].setBorder(new LineBorder(Color.WHITE));
                                System.out.println("start: " + start.getY() + "," + start.getX()
                                + " end: " + end.getY() + "," + end.getX());
                                pieceClick = false;
                                System.out.println("Valid move"); 
                                
                        } 
                        else
                            System.out.println("Invalid move"); 
                    }
                    else{
                        isMoveValid = game.getBoard().getSquareList()[i][j].getPiece().move(start, end);
                        if (isMoveValid){
                                view.getSquareButton()[start.getY()][start.getX()].setBorder(new LineBorder(Color.WHITE));
                                System.out.println("start: " + start.getY() + "," + start.getX()
                                + " end: " + end.getY() + "," + end.getX());
                                pieceClick = false;
                                System.out.println("Valid move"); 
                                
                        } 
                        else
                            System.out.println("Invalid move"); 
                    }*/
                    
               
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
}
