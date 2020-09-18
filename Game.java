import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

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
     * <li>Blue Player: side and name
     * <li>Red PLayer: side and name
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

    /**
     * Loads all data saved in the saveFile "webalesave.txt".
     * Data loading is focused to different line types
     * @see saveFile
     * @return
     * @throws FileNotFoundException
     */
    public boolean load() throws FileNotFoundException {
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
            if(((line.substring(2).trim().isEmpty())||(line.substring(2)==null)) || ((line.charAt(0)!='r') && (line.charAt(0)!='b')))
                throw new Exception();
            
            board.setP2(new Player(line.substring(2), line.charAt(0)));

            //Sets all pieces by blue player to captured status
            board.getP2().piecesCaptured(true);

            //2nd line: side(char) and name(String)
            //Line example: r Massimo
            //Line example: r Robert Lewandowski

            //Red player's side and name
            if(fRead.hasNextLine())
                line = fRead.nextLine();

            //Check validity of retrieved line 2 according to standard save file format
            if(((line.substring(2).trim().isEmpty())||(line.substring(2)==null)) || ((line.charAt(0)!='r') && (line.charAt(0)!='b')))
                throw new Exception();
            
            board.setP1(new Player(line.substring(2), line.charAt(0)));

            //Sets all pieces by red player to captured status
            board.getP1().piecesCaptured(true);

            //3rd line: side(char)
            //Line example: r
            //Line example: b
            
            if(fRead.hasNextLine())
                line = fRead.nextLine();

            //Check validity of retrieved line 3 according to standard save file format
            if((line.charAt(0)!='r') && (line.charAt(0)!='b'))
                throw new Exception();


            //Set whose turn it is for the NEXT turn
            if (line.equals("r"))
                board.getP2().setTurn(true);
            else board.getP1().setTurn(true);



            //Consecutive lines: (String)
            //Should there be an existing piece of a side, then only the player's piece
            //corresponding to the pieceList indexes will be set to "not captured"
            //line example: 45 rChevron
            //line example: 11 bArrow true
            Piece tempPiece;
            Player tempPlayer;
            boolean coordinatesCheck, sideCheck, pieceCheck, rotateCheck;
            rotateCheck = false;
            while(fRead.hasNextLine()){
                line = fRead.nextLine();

                //Exception thrown when either one is not a digit (coordinates can only be formed with integers)
                coordinatesCheck = ((!Character.isDigit(line.charAt(0)))||(!Character.isDigit(line.charAt(1))));


                //Exception thrown when piece type does not correspond to either red or blue
                sideCheck = ((line.charAt(3)!='r')&&(line.charAt(3)!='b'));

                //Exception thrown when piece name (identifier) does not start with any piece types' initial
                //e.g 'p' for plus, 's' for sun
                pieceCheck = ((line.charAt(4)!='p')&&(line.charAt(4)!='t')&&(line.charAt(4)!='c')&&(line.charAt(4)!='s')&&(line.charAt(4)!='a'));
                
                //(FOR ARROW PIECES ONLY)
                //Exception thrown when rotation identifier initial does not correspond to true/false
                if(line.charAt(4)=='a')
                    rotateCheck = ((line.charAt(10)!='f')&&(line.charAt(10)!='t'));
                
                if(coordinatesCheck||sideCheck||pieceCheck||rotateCheck)
                    throw new Exception();                

                int row = Integer.parseInt(Character.toString(line.charAt(0)));
                int col = Integer.parseInt(Character.toString(line.charAt(1)));
                char side = line.charAt(3);
                if (side=='r')
                    tempPlayer = board.getP1();
                else tempPlayer = board.getP2();

                switch(Character.toLowerCase(line.charAt(4))){
                    case 'c':   tempPiece = tempPlayer.getPieceList().get(2);

                                if (tempPiece.isCaptured()) tempPiece.setCaptured(false);
                                else tempPiece = board.getP1().getPieceList().get(4);
                                board.loadArrangement(tempPiece, row, col);
                                break;

                    case 't':   tempPiece = tempPlayer.getPieceList().get(1);

                                if (tempPiece.isCaptured()) tempPiece.setCaptured(false);
                                else tempPiece = board.getP1().getPieceList().get(5);
                                board.loadArrangement(tempPiece, row, col);
                                break;

                    case 's':   tempPiece = tempPlayer.getPieceList().get(3);

                                if (tempPiece.isCaptured()) tempPiece.setCaptured(false);
                                board.loadArrangement(tempPiece, row, col);
                                break;

                    case 'p':   tempPiece = tempPlayer.getPieceList().get(0);

                                if (tempPiece.isCaptured()) tempPiece.setCaptured(false);
                                else tempPiece = board.getP1().getPieceList().get(6);
                                board.loadArrangement(tempPiece, row, col);
                                break;
                                
                    case 'a':   tempPiece = tempPlayer.getPieceList().get(7);
                                if (tempPiece.isCaptured()) tempPiece.setCaptured(false);
                                else {
                                    tempPiece = board.getP1().getPieceList().get(8);
                                    if (tempPiece.isCaptured())
                                        tempPiece.setCaptured(false);
                                    else{
                                        tempPiece = board.getP1().getPieceList().get(9);
                                        if (tempPiece.isCaptured())
                                            tempPiece.setCaptured(false);
                                        else
                                            tempPiece = board.getP1().getPieceList().get(9);
                                    }
                                }
                                Arrow a = (Arrow)tempPiece;
                                a.setRotate(line.charAt(10)=='f' ? false : true);
                                board.loadArrangement(a, row, col);
                                break;
                }
            }
        } catch (Exception e) {
            fRead.close();
            return false;
        }

        fRead.close();
        return true;
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
     * <li>Player names for both sides
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

        //Blue player name
        fw.write(board.getP2().getName()+"\n");

        //Red player side
        fw.write(Character.toString(board.getP1().getSide())+" ");

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
    public static void main(String[] args) throws IOException {
        Game g = new Game();
        System.out.println(g.loadExists());
        if (!g.loadExists())
            g.createSaveFile();
        System.out.println(g.loadExists());
        Player p1 = new Player("Merah", 'r');
        Player p2 = new Player("Biru", 'b');
        Board board = new Board(p1, p2);
        board.getP1().setTurn(true);
        g = new Game(board);
        //g.getBoard().debug();
        g.save();
    }
}