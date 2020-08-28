import java.util.*;

/**
 * MoveTester ni nak test method move() masing2 dalam piece type masing2.
 * Class ni only limited to test moving one piece from one side in an empty board.
 * Takda added pieces dalam ni, just satu piece which is the type yang korang buat.
 * Kalau nak alter for better testing, ubah je then sound kat group. Jangan lupa letak comments.
 */
public class MoveTester {
    //Game board with 8 rows and 7 columns (7*8 board as mentioned in requirements)
    char[][] board = new char[8][7];
    static Piece p;

    public static void main(String[] args) {

        MoveTester home = new MoveTester();
        Scanner input = new Scanner(System.in);
        char pieceType = ' ';
        boolean check = true;

        //Since square class belum ada lagi, kita kena manually set position piece and destination dia
        int startX;
        int startY;
        int endX;
        int endY;

        while(check){
            System.out.print("Enter piece type's initial in LOWERCASE e.g Chevron = 'c', Triangle = 't' etc\n:");
            pieceType = input.next().charAt(0);
            

            switch(pieceType){
                
                case 'c': p = new Chevron('r',"RedChevron.png");check = false;break;
                //INSERT YOUR RESPECTIVE CONSTRUCTORS HERE KALAU NAK CHECK
                //case 's': p = new Sun('r',"RedChevron.png");check = false;break;
                //case 't': p = new Triangle('r',"RedChevron.png");check = false;break;
                //case 'p': p = new Plus('r',"RedChevron.png");check = false;break;
                //case 'a': p = new Arrow('r',"RedChevron.png");check = false;break;
                default: System.out.println("No such piece with the initial.\n");break;
            }
        }

        System.out.println("Starting point coordinates");
        while (true){
            System.out.print("Enter starting point x-coordinate between 0-6: ");
            startX = input.nextInt();
            System.out.print("Enter starting point y-coordinate between 0-7: ");
            startY = input.nextInt();

            if ((startX<7 && startX>-1)&&(startY<8 && startY>-1)) break;
            else System.out.println("Coordinates are limited to a range of integers. Please re-enter.\n");
        }

        System.out.println("\n\nDestination point coordinates");
        while (true){
            System.out.print("Enter destination point x-coordinate between 0-6: ");
            endX = input.nextInt();
            System.out.print("Enter destination point y-coordinate between 0-7: ");
            endY = input.nextInt();

            
            if ((endX<7 && endX>-1)&&(endY<8 && endY>-1)) 
                break;
            else {
                if(endX==startX && endY==startY) 
                    System.out.println("Destination point cannot be the same to starting point.\n");
                else 
                    System.out.println("Coordinates are limited to a range of integers. Please re-enter.\n");
            }
        }
        
        home.clearCMD();
        home.initBoard();
        home.addPiece(startX, startY, pieceType);
        home.addPiece(endX, endY, '#');

        System.out.printf("Check Info:\nPiece: %c\nStarting coordinates (x,y): (%d,%d)\nDestination (x,y): (%d,%d)\n",pieceType,startX,startY,endX,endY);
        System.out.println("A '#' marks the destination square of the piece.\n\n");
        

        home.showBoard();

        boolean flag = p.move(startX, startY, endX, endY);
        System.out.print("Valdity of movement: ");
        System.out.println(flag);

        System.out.println("Check another destination coordinate? Enter 'y' to continue, else to end:");
        String cont = input.next();
        while (cont.equals("y")){
            System.out.println("\nDestination point coordinates");
            while (true){
                System.out.print("Enter destination point x-coordinate between 0-6: ");
                endX = input.nextInt();
                System.out.print("Enter destination point y-coordinate between 0-7: ");
                endY = input.nextInt();

                if ((endX<7 && endX>-1)&&(endY<8 && endY>-1)) 
                    break;
                else {
                    if(endX==startX && endY==startY) 
                        System.out.println("Destination point cannot be the same to starting point.\n");
                    else 
                        System.out.println("Coordinates are limited to a range of integers. Please re-enter.\n");
                }
            }
            home.clearCMD();
            home.initBoard();
            home.addPiece(startX, startY, pieceType);
            home.addPiece(endX, endY, '#');

            System.out.printf("Check Info:\nPiece: %c\nStarting coordinates (x,y): (%d,%d)\nDestination (x,y): (%d,%d)\n",pieceType,startX,startY,endX,endY);
            System.out.println("A '#' marks the destination square of the piece.\n\n");
            

            home.showBoard();

            flag = p.move(startX, startY, endX, endY);
            System.out.print("Valdity of movement: ");
            System.out.println(flag);

            System.out.println("Check another destination coordinate? Enter 'y' to continue, else to end:");
            cont = input.next();
        }
        
        System.out.println("Move testing program ended.");

        
    }

    public void initBoard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                board[i][j] = ' ';
            }
        }
    }

    public void clearCMD(){
        System.out.print("\033[H\033[2J"); 
        System.out.flush();
    }

    public void addPiece(int startX,int startY,char pieceType){
        board[startY][startX] = pieceType;
    }

    public void showBoard(){
        System.out.println(" | 0 | 1 | 2 | 3 | 4 | 5 | 6 |");

        for(int i=0;i<8;i++){
            System.out.printf("%d|",i);
            for(int j=0;j<7;j++){
                System.out.printf("%2c |",board[i][j]);
            }
            System.out.println();
        }
    }
}