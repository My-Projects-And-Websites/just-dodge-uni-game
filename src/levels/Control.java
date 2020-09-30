package levels;// libraries required to provide controls on the character using the keyboard
import city.cs.engine.*;
import collision.hero.Hero;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// class that inherits the keyboard controls features
public class Control extends KeyAdapter {
    // fields that controls the speed of the jump and the walking speed
    private static final float jumpSpeed = 13.5f;
    private static final float walkSpeed = 6.5f;

    // field to determine which body to control
    private Hero body;

    // constructor to determine the value of the body field
    public Control(Hero body) {
        this.body = body;
    }

    /* assigns controls on the keyboard (WAD) is used
    * to control the character*/
    public void keyPressed(KeyEvent e) {
        // variable to get the value of the key pressed
        int pressedCode = e.getKeyCode();

        // when W is pressed this conditional statement will return true and then execute the following code
        // W = Jump
        if (pressedCode == KeyEvent.VK_W) {
            // this method will make the character jump when the nested if also returns true
            Vec2 jump = body.getLinearVelocity();
            if (Math.abs(jump.y) < 0.01f) {
                body.jump(jumpSpeed);
            }
        }

        /* when the A key is pressed it will walk left or when the D key is pressed
        * the character will walk right. The first rendered image is removed when either key is pressed
        * and a new image will be rendered to make the character face the other direction */
        else if (pressedCode == KeyEvent.VK_A) {
            body.removeAllImages();
            body.addImage(new BodyImage("data/player2.gif", 4));
            body.startWalking(-walkSpeed);
        }
        else if (pressedCode == KeyEvent.VK_D) {
            body.removeAllImages();
            body.addImage(new BodyImage("data/player.gif", 4));
            body.startWalking(walkSpeed);
        }
    }

    // this method is ran when the key press is not active anymore
    // when key is released method will run
    public void keyReleased(KeyEvent e) {
        // value assigned is the key that was released
        int releasedCode = e.getKeyCode();

        // hero will stop walking if the A or D key is released
        if (releasedCode == KeyEvent.VK_A) {
            body.stopWalking();
        }
        else if (releasedCode == KeyEvent.VK_D) {
            body.stopWalking();
        }
    }
}