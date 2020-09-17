import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Game {
    private Board board;
    private File saveFile;
    private String fileName = "webalesave.txt";

    /**
     * Creates a new Webale Chess game.
     * @param b Board to be used in playing
     */
    public Game(Board b) {
        this.board = b;
    }

    public Game() {

    }

    /**
     * Checks if the save file (.txt) exists in the same directory. 
     * @return existence of save file "webalesave.txt"
     */
    public boolean loadExists() {
        saveFile = new File(fileName);
        return saveFile.exists();
    }

    /**
     * Creates a new save file
     * @throws IOException
     */
    public void createSaveFile() throws IOException {
        FileWriter fw = new FileWriter(saveFile);
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
    public void save(FileWriter fw) throws IOException {
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
        Square temp;
        for(int i=0;i<8;i++){
            for(int j=0;i<7;j++){
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
        
    }

    public static void main(String[] args) throws IOException {
        Game g = new Game();
        System.out.println(g.loadExists());
        if (!g.loadExists())
            g.createSaveFile();
        System.out.println(g.loadExists());
    }
}