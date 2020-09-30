package levels.trackers;// city engine library to implement step listener class
import city.cs.engine.*;
import collision.hero.Hero;
import levels.Game;
import levels.level.Level1;
import levels.monsters.Monster;
import org.jbox2d.common.Vec2; // this allows setting the position and access to grid-like window (x, y)

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// this tracker class is for the first level only
public class Tracker implements StepListener {
    // these fields are required to track the movement of the tree monster
    private Game game;
    private WorldView view;
    private Hero body;
    private Monster monster;

    /** these boolean fields will only allow the fall of the swords to activate only once
     * since there are two boolean fields, there will be two times in the level where the swords can fall twice */
    private boolean swordFall1 = true;
    private boolean swordFall2 = true;

    /** this is the tracker constructor in which values to the declared fields
     * will be given a value and be able to manipulate or check data about any field
     * listed above
     *
     * @param body - this parameter must have a field of collision.hero.Hero class in which case the hero character
     *             declared in every level can be used
     * @param monster - this parameter must have a monster field with monster class assigned, the tree monster will be used
     * @param game - value will be set to "this"
     * @param view - this will have the instantiated view object with the levels.level.MyView class */

    public Tracker(WorldView view, Game game, Monster monster, Hero body) {
        this.game = game;
        this.view = view;
        this.body = body;
        this.monster = monster;
    }

    // this method must be written as required by the step listener
    @Override
    public void preStep(StepEvent e) {

    }

    // this method contains the if statements that will activate the traps in the game
    @Override
    public void postStep(StepEvent e) {
        // System.out.println(new Vec2(body.getPosition()));
        /* if the x-position of the character is greater than 12
        * or less than -12 and first boolean field is true, then call the method for the fall of the swords */
        if ((body.getPosition().x >= 12 && swordFall1) || (body.getPosition().x <= -12 && swordFall1)) {
            ((Level1) view.getWorld()).makeItRain(); // view object casted to GameWorld class
            swordFall1 = false; // make the sword fall run once only

        // if the y-position of the character is greater than 9 then if statement return true
        } else if (( body.getPosition().y >= 9) && swordFall2) {
            ((Level1) view.getWorld()).makeItRain(); // view object casted to GameWorld class
            swordFall2 = false; // make the sword fall run once only

        // if the monster x-position is greater than 10 then move left
        } else if (monster.getPosition().x >= 10) {
            monster.removeAllImages(); // remove initial image
            monster.addImage(new BodyImage("data/monster2.gif", 5)); // add a new image (flipped version of the gif)
            monster.stopWalking();
            monster.startWalking(monster.leftMove()); // reverse the current direction

        // if the monster x-position is less then -10 then move right
        } else if (monster.getPosition().x <= -10) {
            monster.removeAllImages(); // remove current image
            monster.addImage(new BodyImage("data/monster.gif", 5)); // add a new image (flipped version of the gif)
            monster.stopWalking();
            monster.startWalking(monster.rightMove()); // monster starts moving right
        }

        // if the character falls, respawn in original position and lose 1 life
        else if (body.getPosition().y <= -20) {
            body.setPosition(new Vec2(0,0));
            body.decrementLives();

            try {
                SoundClip respawn = new SoundClip("data/music/respawn.wav");
                respawn.play();
                respawn.setVolume(0.4);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                System.out.println(ex);
            }
        }
    }
}
