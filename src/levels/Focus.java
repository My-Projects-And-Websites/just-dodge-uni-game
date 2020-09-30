package levels;// these libraries allows application of mouse events in the game
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// this class will maintain focus in the window
public class Focus extends MouseAdapter {
    private Component target;

    // this will assign the parameter target to the field with Component class
    public Focus(Component target) {
        this.target = target;
    }

    // this will target the window and initially give focus to the window
    public void mouseEntered(MouseEvent e) {
        target.requestFocus();
    }

}
