import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    /** Board to be used for this game */
    private Board board;

    /** File for saving the current state of the game */
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
     * 
     * @return
     * @throws FileNotFoundException
     */
    public boolean load() throws FileNotFoundException {
        Scanner fRead = new Scanner(saveFile);
        String line = " ";

        //1st line: side(char) and name(String)
        
        //Blue player's side and name
        if(fRead.hasNext())
            line = fRead.nextLine();
        board.setP2(new Player(line.substring(2), line.charAt(0)));

        //Red player's side and name
        if(fRead.hasNextLine())
            line = fRead.nextLine();
        board.setP1(new Player(line.substring(2), line.charAt(0)));

        //2nd line: side(char)
        if(fRead.hasNextLine())
            line = fRead.nextLine();

        if (line.equals("r"))
            board.getP1().setTurn(true);
        else board.getP2().setTurn(true);

        //Consecutive lines: (String)
        while(fRead.hasNextLine()){
            line = fRead.nextLine();
            int row = Integer.parseInt(Character.toString(line.charAt(0)));
            int col = Integer.parseInt(Character.toString(line.charAt(1)));
            char side = line.charAt(3);
            
            board.getSquareList()[row][col].setPiece(p);
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
        //g.save();
    }
}