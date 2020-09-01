import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 * Code ni ambik kat internet. Since program kita kena resizable, that means if main window (JFrame)
 * is resized, components dalam dia pun akan rearranged.
*/
public class Component {

  public static void main(String args[]) {

    JFrame jFrame = new JFrame();
    Container cPane = jFrame.getContentPane();
    ComponentListener componenetListener = new ComponentListener() {
      @Override
      public void componentHidden(ComponentEvent event) {
        dump("Hidden", event);
      }

      @Override
        public void componentMoved(ComponentEvent event) {
      dump("Moved", event);
      }

      @Override
      public void componentResized(ComponentEvent event) {
        dump("Resized", event);
      }

      @Override
      public void componentShown(ComponentEvent event) {
        dump("Shown", event);
      }

      private void dump(String str, ComponentEvent event) {
        System.out.println(event.getComponent().getName() + " : " + str);
      }
    };

    JButton lbutton = new JButton("Left");
    lbutton.setName("Left");
    lbutton.addComponentListener(componenetListener);
    final JButton lright = new JButton("Right");
    lright.setName("Right");
    lright.addComponentListener(componenetListener);

    ActionListener action = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        lright.setVisible(!lright.isVisible());
      }
    };

    lbutton.addActionListener(action);
    JSplitPane splitBar = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, lbutton, lright);
    cPane.add(splitBar, BorderLayout.CENTER);
    jFrame.setSize(500, 400);
    jFrame.setVisible(true);
  }
}