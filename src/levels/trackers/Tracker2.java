package levels.trackers;// city engine library to implement step listener class
import city.cs.engine.*;
import collision.hero.Hero;
import levels.Game;
import levels.level.Level2;
import levels.monsters.Monster2;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// tracker class for level 2 only
public class Tracker2 implements StepListener {
    // these fields are involved in what will be tracked
    private WorldView view;
    private Game game;
    private Hero body;
    private Monster2 monster;
    private boolean fireFall = true; // boolean field to control fall of the fireball

    // tracker constructor for the second level
    public Tracker2(WorldView view, Game game, Monster2 monster, Hero body) {
        this.view = view;
        this.game = game;
        this.body = body;
        this.monster = monster;
    }

    // this method must be written as required by the step listener even if no code is written inside
    @Override
    public void preStep(StepEvent stepEvent) {

    }

    // this method has the if statements that will control movement of the monster and fire fall
    @Override
    public void postStep(StepEvent stepEvent) {
        // if the runner monster x-position is less than or equal to 25 then do this...
        if (monster.getPosition().x <= -25) {
            monster.removeAllImages(); // remove the current image
            monster.addImage(new BodyImage("data/level2Monster.gif", 5)); // add new image
            monster.stopWalking();
            monster.startWalking(monster.rightMove()); // stop walking and start moving right
        }

        // if the runner monster x-position is greater than or equal to 25 then do this...
        else if (monster.getPosition().x >= 25) {
            monster.removeAllImages(); // remove image
            monster.addImage(new BodyImage("data/level2Monster2.gif", 5)); // add new image
            monster.stopWalking();
            monster.startWalking(monster.leftMove()); // stop walking and start moving left
        }

        // if the character moves on the top right or top left of the window, the fireballs will fall
        else if (((body.getPosition().x <= -10 && body.getPosition().y >= 7) || (body.getPosition().x >= 10 && body.getPosition().y >= 7)) && fireFall) {
            ((Level2)view.getWorld()).makeItFire(); // view object casted as levels.level.Level2 class
            fireFall = false; // set the boolean field to false to stop returning true
        }

        // if the character falls, reset to original position and subtract one life from current amount
        else if (body.getPosition().y <= -20) {
            body.setPosition(new Vec2(0, 0));
            body.decrementLives();

            try {
                SoundClip respawn = new SoundClip("data/music/respawn.wav");
                respawn.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                System.out.println(ex);
            }
        }
    }
}
