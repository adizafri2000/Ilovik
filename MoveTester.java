import java.util.*;

/**
 * UPDATED VERSION
 * MoveTester ni nak test method move() masing2 dalam piece type masing2.
 * In program ni, korang boleh test:
 *      1. Validity of piece from one side move to another square.
 *      2. Validity of piece from one side moving to an occupied square of same/different side.
 * Starting position and type of main piece is one-time set and takleh ubah later in program.
 * Korang boleh tambah dummy pieces from mana2 side into the board jugak. Dummy pieces tu boleh remove.
 * Default dummy piece is Chevron piece.
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
        int dummyPieces = 0;

        //Since square class belum ada lagi, kita kena manually set position piece and destination dia
        int startX;
        int startY;

        while(check){
            System.out.print("Enter piece type's initial in LOWERCASE e.g Chevron = 'c', Triangle = 't' etc\n:");
            pieceType = input.next().charAt(0);
            

            switch(pieceType){
                
                case 'c': p = new Chevron('r',"RedChevron.png");check = false;break;
                //INSERT YOUR RESPECTIVE CONSTRUCTORS HERE KALAU NAK CHECK
                //case 's': p = new Sun('r',"RedChevron.png");check = false;break;
                //case 't': p = new Triangle('r',"RedChevron.png");check = false;break;
                //case 'p': p = new Plus('r',"RedChevron.png");check = false;break;
                //case 'a': p = new Arrow('r',"RedChevron.png",false);check = false;break;
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
        home.addPiece(start);
        home.clearCMD();
        home.showBoard();
        boolean cont = true;

        do{

            System.out.print("Add dummy piece? 'y' for yes: ");
            String ans = input.next();
            boolean haih = (ans.equals("y")) ? true : false;
            if (haih){
                home.addDummy(start);
                dummyPieces++;
            }

            if(dummyPieces>0){
                System.out.print("Remove a dummy piece? 'y' for yes: ");
                ans = input.next();
                haih = (ans.equals("y")) ? true : false;
                if (haih){
                    home.removeDummy(start);
                    dummyPieces--;
                }
            }
            
            Square end = home.setEnd(start);
            
            home.clearCMD();
    
            System.out.printf("Check Info:\nPiece: %c\nStarting coordinates (x,y): (%d,%d)\nDestination (x,y): (%d,%d)\n",pieceType,start.getX(),start.getY(),end.getX(),end.getY());
            //System.out.println("A '#' marks the destination square of the piece.\n\n");
            
    
            home.showBoard();
    
            boolean flag = p.move(start,end);
            System.out.print("Valdity of movement: ");
            System.out.println(flag);
    
            System.out.print("Make adjustments? 'y' for yes: ");
            ans = input.next();
            cont = (ans.equals("y")) ? true : false;
        }while(cont);
        
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
        String side;
        while (true){
            System.out.print("Enter x-coordinate of dummy piece between 0-6: ");
            x = input.nextInt();
            System.out.print("Enter y-coordinate of dummy piece between 0-7: ");
            y = input.nextInt();

            System.out.print("Enter piece side 'r' or 'b': ");
            side = input.next();

            if((side.equals("r"))||(side.equals("b"))){
                if ((x<7 && x>-1)&&(y<8 && y>-1)){
                    if(!((x==pos.getX())&&(y==pos.getY())))
                        break;
                    else System.out.println("Dummy piece cannot be placed on starting piece. Place it in different coordinates.\n");
                }
                else {
                    System.out.println("Coordinates are limited to a range of integers. Please re-enter.\n");
                }
            }
            else
                System.out.println("Only two sides are available: 'r' and 'b'. Please re-enter.\n");

        }
        board[y][x].setPiece(new Chevron(side.charAt(0),"whatever"));
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
                    System.out.printf("%2s |",board[i][j].getPiece().getName().substring(0,2));
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
                if(!((x==pos.getX())&&(y==pos.getY()))){
                    if (board[y][x].isOccupied()){
                        board[y][x].setOccupied(false);
                        break;
                    }
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
        return board[endY][endX];
    }
    
}
