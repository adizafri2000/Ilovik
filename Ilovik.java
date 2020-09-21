public class Ilovik {
    public static void main(String[] args) {
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