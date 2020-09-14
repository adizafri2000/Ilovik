import java.awt.Color;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.event.ActionEvent;

public class Controller{
    private Marker marker;
    private View view;

    public Controller(Marker model, View v){
        this.marker = model;
        this.view = v;
        //initListeners();
    }

    /**
     * @return the marker
     */
    public Marker getMarker() {
        return marker;
    }

    /**
     * @return the view
     */
    public View getView() {
        return view;
    }

    /**
     * @param marker the marker to set
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    /**
     * @param view the view to set
     */
    public void setView(View view) {
        this.view = view;
    }

    public void initListeners(){
        view.getButton().addActionListener(e -> changeColor());
    }

    public void changeColor(){
        if(getMarker().getColor().equals("red")){
            view.getPanel().setBackground(Color.magenta);
            getMarker().setColor("notred");
        }
        else{
            view.getPanel().setBackground(Color.red);
            getMarker().setColor("red");
        }
        JOptionPane.showMessageDialog(view,"Color changed.");
        
        
    }
}