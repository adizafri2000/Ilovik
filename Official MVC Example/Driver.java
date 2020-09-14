public class Driver {
    public static void main(String[] args) {
        Marker model = new Marker("red");
        View view = new View();
        Controller controller = new Controller(model,view);
        controller.initListeners();

    }
}