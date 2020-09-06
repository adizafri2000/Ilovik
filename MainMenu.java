import java.awt.*;

import javax.swing.GroupLayout;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author adiza
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenuGUI
     */
    public MainMenu() {
        //initComponents();
        /* */
        JLabel text1 = new JLabel("WELCOME TO OUR CHESS GAME");
        JLabel text2 = new JLabel("THIS IS ANOTHER TEXT");
        JLabel text3 = new JLabel("ANOTHER TEXT THIS IS");
        JLabel text4 = new JLabel("A TEXT IS THIS");
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(11,55,100));
        GroupLayout panel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(panel1Layout);

        text1.setForeground(Color.WHITE);
        text2.setForeground(Color.WHITE);
        text3.setForeground(Color.WHITE);
        text4.setForeground(Color.WHITE);

        //jPanel1.setLayout(new BorderLayout());
        //jPanel1.setLayout(new GridLayout(4,4));
        text1.setSize(new Dimension());

        text1.setHorizontalAlignment(JLabel.CENTER);
        
        text2.setHorizontalAlignment(JLabel.CENTER);
        jPanel1.add(text1);
        jPanel1.add(text2);
        jPanel1.add(text3);
        jPanel1.add(text4);
        //jPanel1.add(text1,BorderLayout.NORTH);
        //jPanel1.add(text2,BorderLayout.SOUTH);
        //jPanel1.add(text3,BorderLayout.WEST);
        //jPanel1.add(text4,BorderLayout.EAST);
        add(jPanel1);
        setTitle("Ilovik Webale Chess");
        setVisible(true);
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //jPanel1.setBackground(new java.awt.Color(204, 255, 204));104, 69, 5
        jPanel1.setBackground(new Color(104, 69, 5));
        //jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        //jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jPanel1.setName(""); // NOI18N

        jPanel2.setBackground(new Color(204, 255, 255));

        jLabel1.setBackground(new Color(0, 0, 0));
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ILOVIK'S WEBALE CHESS");

        //initialize GroupLayout to which component(container)
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true);
            }
        });*/
        new MainMenu();
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration                   
}
