package collision;// library imported to create and access features of the engine
import city.cs.engine.*;
import collision.hero.Hero;
import snacks.Candy;
import snacks.Choco;
import snacks.Cookie;
import snacks.Cupcake;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/* listens to the collision win the collide method and activates
* the following code inside this method when the if statement returns true*/

public class Collect_Collision implements CollisionListener {
    // the hero will be the main focus of this collision so a field must be declared
    private Hero hero;
    private SoundClip pickupSound;

    // assigns parameter value to the hero field
    public Collect_Collision(Hero hero) {
        this.hero = hero;
    }

    // checks for collisions for all the snacks
    // activates when hero collide with snacks
    public void collide(CollisionEvent e) {
        // if the snack was an object made of the following classes, then activate this conditional statement when colliding
        if (e.getOtherBody() == hero && (e.getReportingBody() instanceof Cookie || e.getReportingBody() instanceof Candy || e.getReportingBody() instanceof Choco || e.getReportingBody() instanceof Cupcake)) {
            // this SoundClip will play when collecting any kind of snack
            try {
                pickupSound = new SoundClip("data/music/eat.wav");
                pickupSound.play(); // the play method means play it once
                // if loop() was used, the clip will keep playing once a snack has been collected
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException event) {
                System.out.println(event); // print the error type in the console
            }

            // snack score increments and deletes the snack
            hero.plusOneSnack();
            e.getReportingBody().destroy();
        }
    }
}
