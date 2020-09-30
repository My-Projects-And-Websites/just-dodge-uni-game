package levels.trackers;// city engine library to implement step listener class
import city.cs.engine.*;
import collision.hero.Hero;
import levels.Game;
import levels.monsters.Monster3;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// tracker class for level 3 only
public class Tracker3 implements StepListener {
    // these fields are involved in what will be tracked for this level
    private WorldView view;
    private Game game;
    private Monster3 monster;
    private Monster3 monster2; // the amount of monster fields declared = number of monsters in game
    private Hero body;

    // third tracker constructor foor level 3
    public Tracker3(WorldView view, Game game, Monster3 monster, Monster3 monster2, Hero body) {
        this.view = view;
        this.game = game;
        this.monster = monster;
        this.monster2 = monster2;
        this.body = body;
    }

    // this method must be written as required by the step listener even if no code is written inside
    public void preStep(StepEvent stepEvent) {

    }

    // this method has the if statements that will control movement of the bat monsters
    public void postStep(StepEvent stepEvent) {
        // if the x-position of the bat monster at the top is less than -15, return true and move right
        if (monster.getPosition().x <= -15) {
            monster.removeAllImages();
            monster.addImage(new BodyImage("data/level3Monster2.gif", 5));
            monster.stopWalking();
            monster.startWalking(monster.rightMove());
        }

        // if the x-position of the bat monster at the top is greater than 15, return true and move left
        else if (monster.getPosition().x >= 15) {
            monster.removeAllImages();
            monster.addImage(new BodyImage("data/level3Monster.gif", 5));
            monster.stopWalking();
            monster.startWalking(monster.leftMove());
        }

        // if the x-position of the bat monster at the bottom is less than -20, return true and move right
        else if (monster2.getPosition().x <= -20) {
            monster2.removeAllImages();
            monster2.addImage(new BodyImage("data/level3Monster2.gif", 5));
            monster2.stopWalking();
            monster2.startWalking(monster2.rightMove());
        }

        // if the x-position of the bat monster at the top is greater than 20, return true and move right
        else if (monster2.getPosition().x >= 20) {
            monster2.removeAllImages();
            monster2.addImage(new BodyImage("data/level3Monster.gif", 5));
            monster2.stopWalking();
            monster2.startWalking(monster2.leftMove());
        }

        // if the character falls off the window, reset position and subtract one life
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
