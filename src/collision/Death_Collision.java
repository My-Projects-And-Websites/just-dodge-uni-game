package collision;// libraries required to create this collision
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.*;
import collision.hero.Hero;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/* this class listens for collision between the character and the monsters and other traps or when
* the character falls out of bounds
* this will decrement the character lives when collision is active */
public class Death_Collision implements CollisionListener {
    // fields to be assigned for the collision to work and to identify which bodies will collide
    private Hero hero;
    private World world;
    private SoundClip respawnSound;

    // collision constructor which assigns value to the fields
    public Death_Collision(Hero hero, World world) {
        this.hero = hero;
        this.world = world;
    }

    // collide method stores all the conditional statements which will listen for any collision when statement returns true
    public void collide(CollisionEvent e) {
        // decrement lives when if statement is true and reset position
        // also output text in the console
        if (e.getOtherBody() instanceof Hero) {
            // every SoundClip object must be initialised inside a try catch
            try {
                // when ehro des, play this sound
                respawnSound = new SoundClip("data/music/respawn.wav");
                respawnSound.play();
                respawnSound.setVolume(0.4); // set the volume lower than the background music
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException event) {
                System.out.println(event); // print the error type inside the console
            }

            System.out.println("Oh no! You died!");
            System.out.println("Lives left: " + hero.decrementLives());
            hero.setPosition(new Vec2(0, 0));
        }
    }

    // return the active world
    public World getWorld() {
        return world;
    }
}
