import java.util.*;

/**
 * MoveTester ni nak test method move() masing2 dalam piece type masing2.
 * Class ni only limited to test moving one piece from one side in an empty board.
 * Takda added pieces dalam ni, just satu piece which is the type yang korang buat.
 * Kalau nak alter for better testing, ubah je then sound kat group. Jangan lupa letak comments.
 */
public class MoveTester {
    //Game board with 8 rows and 7 columns (7*8 board as mentioned in requirements)
    Square[][] board = new Square[8][7];
    static Piece p;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        MoveTester home = new MoveTester();
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
                case 's': p = new Sun('r',"RedChevron.png");check = false;break;
                //case 't': p = new Triangle('r',"RedChevron.png");check = false;break;
                //case 'p': p = new Plus('r',"RedChevron.png");check = false;break;
                case 'a': p = new Arrow('r',"RedChevron.png",false);check = false;break;
                default: System.out.println("No such piece with the initial.\n");break;
            }
        }
        home.initBoard();
        home.showBoard();

        System.out.println("Starting point coordinates");
        while (true){
            System.out.print("Enter starting point x-coordinate between 0-6: ");
            startX = input.nextInt();
            System.out.print("Enter starting point y-coordinate between 0-7: ");
            startY = input.nextInt();

            if ((startX<7 && startX>-1)&&(startY<8 && startY>-1)) break;
            else System.out.println("Coordinates are limited to a range of integers. Please re-enter.\n");
        }
        Square start = new Square.Builder().x(startX).y(startY).occupied(true).piece(p).build();

        System.out.print("Add dummy piece? 'y' for yes: ");
        boolean haih = (input.nextLine().equals("y")) ? true : false;
        if (haih){
            home.addDummy(start);
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

        
        Square end = new Square.Builder().x(endX).y(endY).occupied(false).build();
        
        home.clearCMD();
        home.addPiece(start);
        //home.addPiece(endX, endY, '#');

        System.out.printf("Check Info:\nPiece: %c\nStarting coordinates (x,y): (%d,%d)\nDestination (x,y): (%d,%d)\n",pieceType,startX,startY,endX,endY);
        System.out.println("A '#' marks the destination square of the piece.\n\n");
        

        home.showBoard();

        boolean flag = p.move(start,end);
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

            flag = p.move(start,end);
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
                board[i][j] = new Square.Builder().x(j).y(i).occupied(false).build();
            }
        }
    }

    public void clearCMD(){
        System.out.print("\033[H\033[2J"); 
        System.out.flush();
    }

    public void addDummy(Square pos){
        int x,y;
        while (true){
            System.out.print("Enter x-coordinate of dummy piece between 0-6: ");
            x = input.nextInt();
            System.out.print("Enter y-coordinate of dummy piece between 0-7: ");
            y = input.nextInt();

            
            if ((x<7 && x>-1)&&(y<8 && y>-1)){
                if(x!=pos.getX() && y!= pos.getY())
                    break;
                else System.out.println("Dummy piece cannot be placed on starting piece. Place it in different coordinates.\n");
            }
            else {
                System.out.println("Coordinates are limited to a range of integers. Please re-enter.\n");
            }
        }
        board[y][x].setPiece(new Chevron('b',"whatever"));
    }

    public void addPiece(Square pos){
        board[pos.getY()][pos.getX()].setPiece(p);
    }

    public void showBoard(){
        System.out.println(" | 0 | 1 | 2 | 3 | 4 | 5 | 6 |");

        for(int i=0;i<8;i++){
            System.out.printf("%d|",i);
            for(int j=0;j<7;j++){
                if (board[i][j].isOccupied())
                    System.out.printf("%2s |",board[i][j].getPiece().getName().substring(0,3));
                else
                    System.out.printf("%2s |"," ");
            }
            System.out.println();
        }
    }

    public void removeDummy(Square pos){
        int x,y;
        while (true){
            System.out.println("Removing dummy piece");
            System.out.print("Enter x-coordinate of dummy piece to be removed between 0-6: ");
            x = input.nextInt();
            System.out.print("Enter y-coordinate of dummy piece to be removed between 0-7: ");
            y = input.nextInt();

            
            if ((x<7 && x>-1)&&(y<8 && y>-1)){
                if(x!=pos.getX() && y!=pos.getY()){
                    if (board[y][x].isOccupied())
                        board[y][x].setOccupied(false);
                    else System.out.println("No dummy piece in this coordinate. Please re-enter.\n");
                }else System.out.println("Cannot remove original tested piece. Please re-enter.\n");
            }
            else {
                System.out.println("Coordinates are limited to a range of integers. Please re-enter.\n");
            }
        }
    }

    public Square setEnd(Square start){
        System.out.println("\n\nDestination point coordinates");
        int endX,endY;
        while (true){
            System.out.print("Enter destination point x-coordinate between 0-6: ");
            endX = input.nextInt();
            System.out.print("Enter destination point y-coordinate between 0-7: ");
            endY = input.nextInt();

            
            if ((endX<7 && endX>-1)&&(endY<8 && endY>-1)) 
                break;
            else {
                if(endX==start.getX() && endY==start.getY()) 
                    System.out.println("Destination point cannot be the same to starting point.\n");
                else 
                    System.out.println("Coordinates are limited to a range of integers. Please re-enter.\n");
            }
        }
        return new Square.Builder().build().x(endX).y(endY).;
    }
    
}
