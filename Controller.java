public class Controller {
    //Model: Game tak siap lagi
    private View view;
    
    public Controller(){
        //this.game = game;
        //this.view = view;
    }

    //PLAYER 1 RED
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