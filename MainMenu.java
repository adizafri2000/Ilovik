import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;


public class MainMenu extends JFrame implements ComponentListener{

    public MainMenu(){
        setSize(640, 480);
        setTitle("W: "+this.getWidth()+", H:"+this.getHeight());
        //setResizable(true);
		setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        getContentPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e){
                Component c = (Component)e.getSource();
                setTitle("W: "+c.getWidth()+", H:"+c.getHeight());
            }
        });
    }

    public static void main(String[] args) {
        new MainMenu();
    }
    
}
