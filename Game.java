import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

/**
 * @author Adi
 */
public class Game {
    /** Board to be used for this game */
    private Board board;

    /** File for saving the current state of the game.
     * File contents are partitioned and specified by row:
     * 
     * <ol>
     * <li>Blue Player: side, move counter, name
     * <li>Red PLayer: side, move counter, name
     * <li>Recently moved side (Before game is saved)
     * <li>Piece (next n<=22 lines): coordinates, side+name (+ rotation status for arrows)
     * </ol>
     * 
     * Failure to contain lines 1,2,3 or data being incorrectly written
     * results in an invalid save file.
    */
    private File saveFile;

    /** Default file name for the game's save file */
    private String fileName = "webalesave.txt";

    /**
     * Creates a new Webale Chess game.
     * 
     * @param b Board to be used in playing
     */
    public Game(Board b) {
        this.board = b;
    }

    public Game() {
        this.board = new Board();
    }

    /**
     * Checks if the save file (.txt) exists in the same directory.
     * 
     * @return existence of save file "webalesave.txt"
     */
    public boolean loadExists() {
        saveFile = new File(fileName);
        return saveFile.exists();
    }

    public boolean checkLoad() throws FileNotFoundException {
        Scanner fRead = new Scanner(saveFile);
        String line = " ";

        try {
            //1st line: side(char) and name(String)
            //Line example: b George
            //Line example: b Zoe LaVerne
                
            //Blue player's side and name
            if(fRead.hasNext())
                line = fRead.nextLine();

            //Check validity of retrieved line 1 according to standard save file format
            if(!validLine1or2(line))
                throw new Exception();

            //2nd line: side(char) and name(String)
            //Line example: r Massimo
            //Line example: r Robert Lewandowski

            //Red player's side and name
            if(fRead.hasNextLine())
                line = fRead.nextLine();

            //Check validity of retrieved line 2 according to standard save file format
            if(!validLine1or2(line))
                throw new Exception();

            //3rd line: side(char)
            //Line example: r
            //Line example: b

            if(fRead.hasNextLine())
                line = fRead.nextLine();

            //Check validity of retrieved line 3 according to standard save file format
            if(!validLine3(line))
                throw new Exception();

            //Consecutive lines: (String)
            //Should there be an existing piece of a side, then only the player's piece
            //corresponding to the pieceList indexes will be set to "not captured"
            //line example: 45 rChevron
            //line example: 11 bArrow true
            while(fRead.hasNextLine()){
                line = fRead.nextLine();

                if(!validPieceLine(line))           
                    throw new Exception();
            }
            
        } catch (Exception e) {
            return false;
        }

        fRead.close();
        return true;
    }

    /**
     * Loads all data from the saveFile "webalesave.txt".
     * Data loading is focused to different line types.
     * Load process fails should a line from the save file
     * does not follow the standard save format
     * @see saveFile
     * @return
     * @throws FileNotFoundException
     */
    public Board load() throws FileNotFoundException {
        //saveFile = new File(fileName);
        Scanner fRead = new Scanner(saveFile);
        System.out.println(saveFile.exists());
        String line = " ";
        Board tempBoard;

        //1st line: side(char) and name(String)
        //Line example: b George
        //Line example: b Zoe LaVerne
            
        //Blue player's side and name
        if(fRead.hasNext())
            line = fRead.nextLine();
        System.out.println(line);

        Player bluePlayer2 = new Player(line.substring(4), line.charAt(0));
        bluePlayer2.setMoves(Integer.parseInt(Character.toString(line.charAt(2))));
        bluePlayer2.piecesCaptured(true);

        //2nd line: side(char) and name(String)
        //Line example: r Massimo
        //Line example: r Robert Lewandowski

        //Red player's side and name
        if(fRead.hasNextLine())
            line = fRead.nextLine();
        System.out.println(line);

        Player redPlayer1 = new Player(line.substring(4), line.charAt(0));
        redPlayer1.setMoves(Integer.parseInt(Character.toString(line.charAt(2))));
        redPlayer1.piecesCaptured(true);

        tempBoard = new Board(redPlayer1, bluePlayer2);

        //3rd line: side(char)
        //Line example: r
        //Line example: b

        if(fRead.hasNextLine())
            line = fRead.nextLine();
        System.out.println(line);

        //Set whose turn it is for the NEXT turn
        if (line.equals("r"))
            tempBoard.getP2().setTurn(true);
        else tempBoard.getP1().setTurn(true);

        //Consecutive lines: (String)
        //Should there be an existing piece of a side, then only the player's piece
        //corresponding to the pieceList indexes will be set to "not captured"
        //line example: 45 rChevron
        //line example: 11 bArrow true
        Piece tempPiece;
        Player tempPlayer;
        while(fRead.hasNextLine()){
            line = fRead.nextLine();
            System.out.println(line);

            int row = Integer.parseInt(Character.toString(line.charAt(0)));
            int col = Integer.parseInt(Character.toString(line.charAt(1)));
            char side = line.charAt(3);
            if (side=='r')
                tempPlayer = tempBoard.getP1();
            else tempPlayer = tempBoard.getP2();

            switch(Character.toLowerCase(line.charAt(4))){
                case 'c':   tempPiece = tempPlayer.getPieceList().get(2);

                            if (tempPiece.isCaptured()) tempPiece.setCaptured(false);
                            else tempPiece = board.getP1().getPieceList().get(4);
                            tempBoard.loadArrangement(tempPiece, row, col);
                            break;

                case 't':   tempPiece = tempPlayer.getPieceList().get(1);

                            if (tempPiece.isCaptured()) tempPiece.setCaptured(false);
                            else tempPiece = board.getP1().getPieceList().get(5);
                            tempBoard.loadArrangement(tempPiece, row, col);
                            break;

                case 's':   tempPiece = tempPlayer.getPieceList().get(3);

                            if (tempPiece.isCaptured()) tempPiece.setCaptured(false);
                            tempBoard.loadArrangement(tempPiece, row, col);
                            break;

                case 'p':   tempPiece = tempPlayer.getPieceList().get(0);

                            if (tempPiece.isCaptured()) tempPiece.setCaptured(false);
                            else tempPiece = board.getP1().getPieceList().get(6);
                            tempBoard.loadArrangement(tempPiece, row, col);
                            break;
                            
                case 'a':   tempPiece = tempPlayer.getPieceList().get(7);
                            if (tempPiece.isCaptured()) tempPiece.setCaptured(false);
                            else {
                                tempPiece = tempBoard.getP1().getPieceList().get(8);
                                if (tempPiece.isCaptured())
                                    tempPiece.setCaptured(false);
                                else{
                                    tempPiece = tempBoard.getP1().getPieceList().get(9);
                                    if (tempPiece.isCaptured())
                                        tempPiece.setCaptured(false);
                                    else
                                        tempPiece = tempBoard.getP1().getPieceList().get(9);
                                }
                            }
                            Arrow a = (Arrow)tempPiece;
                            a.setRotate(line.charAt(10)=='f' ? false : true);
                            tempBoard.loadArrangement(a, row, col);
                            break;
            }
        }
        fRead.close();
        return tempBoard;
    }

    /**
     * Checks if a retrieved line in Game.load() is an empty line consisting of either whitespace, or
     * is null.
     * @param s String (Line) to be checked
     * @return whether string is empty or not
     */
    private boolean validLine(String s){
        return (s.trim().isEmpty()&&s==null);
    }

    /**
     * Checks whether a line retrieved from the save file has valid characters
     * to fit specified data of line 1/2 according to the standard save file format/
     * <p>
     * Line 1/2: side(SPACE)recent_move_counter(SPACE)name
     * @param s
     * @return
     */
    private boolean validLine1or2(String s){
        if(validLine(s))
            if(s.trim().length()>2)
                if((s.charAt(0)=='r')||(s.charAt(0)=='b'))
                    if(Character.isDigit(s.charAt(2)))
                        if(s.substring(2).trim().length()!=0)
                            return true;
                    
        return false;
    }

    /**
     * Checks whether a line retrieved from the save file has valid characters
     * to fit specified data of line 3 according to the standard save file format/
     * <p>
     * Line 3: side
     * @param s
     * @return
     */
    private boolean validLine3(String s){
        if(validLine(s))
            if((s.charAt(0)=='b')||(s.charAt(0)=='r'))
                return true;

        return false;
    }

    private boolean validPieceLine(String s){
        if(validLine(s)){
            if(s.trim().length()>3){
                boolean coordinatesCheck, sideCheck, pieceCheck, rotateCheck;
                rotateCheck = false;

                //Exception thrown when either one is not a digit (coordinates can only be formed with integers)
                coordinatesCheck = ((!Character.isDigit(s.charAt(0)))||(!Character.isDigit(s.charAt(1))));

                //Exception thrown when piece type does not correspond to either red or blue
                sideCheck = ((s.charAt(3)!='r')&&(s.charAt(3)!='b'));

                //Exception thrown when piece name (identifier) does not start with any piece types' initial
                //e.g 'p' for plus, 's' for sun
                pieceCheck = ((s.charAt(4)!='p')&&(s.charAt(4)!='t')&&(s.charAt(4)!='c')&&(s.charAt(4)!='s')&&(s.charAt(4)!='a'));
                
                //(FOR ARROW PIECES ONLY)
                //Exception thrown when rotation identifier initial does not correspond to true/false
                if(s.charAt(4)=='a')
                    rotateCheck = ((s.charAt(10)!='f')&&(s.charAt(10)!='t'));

                if(coordinatesCheck&&sideCheck&&pieceCheck){
                    if(s.charAt(4)!='a')
                        return true;
                    else{
                        if(rotateCheck)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Creates a new save file
     * @throws IOException
     */
    public FileWriter createSaveFile() throws IOException {
        FileWriter fw = new FileWriter(saveFile);
        return fw;
    }


    /**
     * Saves current game into a .txt file of same directory. Extracted info are:
     * <ul>
     * <li>Both sides' side, move counter, name
     * <li>Recently moved side
     * <li>Occupied squares' positions with their respective pieces
     * 
     * @throws IOException
     */
    public void save() throws IOException {
        saveFile = new File(fileName);
        FileWriter fw = new FileWriter(saveFile);
        //Blue player side
        fw.write(Character.toString(board.getP2().getSide())+" ");

        //Blue's recent move counter
        String bMove = Integer.toString(board.getP2().getMoves());
        fw.write(bMove+" ");

        //Blue player name
        fw.write(board.getP2().getName()+"\n");

        //Red player side
        fw.write(Character.toString(board.getP1().getSide())+" ");

        //Red's recent move counter
        String rMove = Integer.toString(board.getP1().getMoves());
        fw.write(rMove+" ");

        //Red player name
        fw.write(board.getP1().getName()+"\n");

        //Recently moved side
        char recent = (board.getP1().isTurn()) ? board.getP1().getSide() : board.getP2().getSide();
        fw.write(Character.toString(recent)+"\n");

        //Occupied squares (with coordinates and respective pieces)
        //Format: coordinates<SPACE>pieceName
        //e.g "45 bChevron" translates to a Blue Chevron in Board location [4][5]
        Square temp;
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                if (board.getSquareList()[i][j].isOccupied()){
                    temp = board.getSquareList()[i][j];
                    //coordinates in format: row<SPACE>column<SPACE>
                    fw.write(Integer.toString(temp.getY())+Integer.toString(temp.getX())+" ");

                    //piece name with side in format: <side><piece type>
                    //e.g Red Sun is saved as "rSun"

                    //Arrow pieces contain additional piece info: rotating state
                    if(temp.getPiece() instanceof Arrow){
                        Arrow a = (Arrow)temp.getPiece();
                        fw.write(a.getName()+" "+a.isRotating()+"\n");
                    }

                    else
                        fw.write(temp.getPiece().getName()+"\n");
                    
                }
            }
        }

        fw.close();
    }


    
    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board b){
        this.board = b;
    }

    public static void main(String[] args) throws IOException {
        Game g = new Game();
        System.out.println(g.loadExists());
        if (!g.loadExists())
            g.createSaveFile();
        System.out.println(g.loadExists());
        //g.load();
        //Player p1 = new Player("Merah", 'r');
        //Player p2 = new Player("Biru", 'b');
        //Board board = new Board(p1, p2);
        //board.getP1().setTurn(true);
        //g = new Game(board);
        Board load = g.load();
        /*
        System.out.println(load.getP1().getName());
        for(int i=0;i<11;i++){
            System.out.printf("%s: %s\n",load.getP1().getPieceList().get(i).getName(),load.getP1().getPieceList().get(i).isCaptured());
        }
        System.out.println(load.getP2().getName());
        for(int i=0;i<11;i++){
            System.out.printf("%s: %s\n",load.getP2().getPieceList().get(i).getName(),load.getP2().getPieceList().get(i).isCaptured());
        }
        */
        
        load.debug();
        System.out.print("\n\n\nJUMPER\n\n\n");
        g = new Game(load);

        //g.getBoard().debug();
        //g.save();

    }
}