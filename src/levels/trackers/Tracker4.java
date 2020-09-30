package levels.trackers;// city engine library to implement step listener class
import city.cs.engine.*;
import collision.hero.Hero;
import levels.Game;
import levels.monsters.Monster4;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// tracker class for level 4 only
public class Tracker4 implements StepListener {
    // these fields are involved in what will be tracked for this level
    private WorldView view;
    private Game game;
    private Hero hero;
    private Monster4 monster, monster2, monster3, monster4, monster5; // five alien monsters in level 4

    // fourth tracker constructor
    public Tracker4(WorldView view, Game game, Hero hero, Monster4 monster, Monster4 monster2, Monster4 monster3, Monster4 monster4, Monster4 monster5) {
        this.view = view;
        this.game = game;
        this.hero = hero;
        this.monster = monster;
        this.monster2 = monster2;
        this.monster3 = monster3;
        this.monster4 = monster4;
        this.monster5 = monster5;
        // assign values using parameters to the monsters
    }

    // this method must be written as required by the step listener even if no code is written inside
    @Override
    public void preStep(StepEvent stepEvent) {

    }

    // this method has the if statements that will control movement of the bat monsters
    @Override
    public void postStep(StepEvent stepEvent) {
        // middle alien monster
        if (monster.getPosition().x <= -12.5f) {
            monster.removeAllImages();
            monster.addImage(new BodyImage("data/level4Monster2.gif", 5));
            monster.stopWalking();
            monster.startWalking(3); // move with the speed of 3 if x-position is less than -12.5f (move right)
        }
        else if (monster.getPosition().x >= 12.5f) {
            monster.removeAllImages();
            monster.addImage(new BodyImage("data/level4Monster.gif", 5));
            monster.stopWalking();
            monster.startWalking(-3); // move with the speed of -3 if x-position is less than 12.5f (move left)
        }

        // top left alien monster
        else if (monster2.getPosition().x <= -18) {
            monster2.removeAllImages();
            monster2.addImage(new BodyImage("data/level4Monster2.gif", 5));
            monster2.stopWalking();
            monster2.startWalking(monster2.rightMove()); // move right when x-position is less than -18
        }

        else if (monster2.getPosition().x >= -10) {
            monster2.removeAllImages();
            monster2.addImage(new BodyImage("data/level4Monster.gif", 5));
            monster2.stopWalking();
            monster2.startWalking(monster2.leftMove()); // move left when x-position is greater than -10
        }

        // top right alien monster
        else if (monster3.getPosition().x >= 18) {
            monster3.removeAllImages();
            monster3.addImage(new BodyImage("data/level4Monster.gif", 5));
            monster3.stopWalking();
            monster3.startWalking(monster3.leftMove()); // move left when x-position is greater than 18
        }

        else if (monster3.getPosition().x <= 10) {
            monster3.removeAllImages();
            monster3.addImage(new BodyImage("data/level4Monster2.gif", 5));
            monster3.stopWalking();
            monster3.startWalking(monster3.rightMove()); // move right when x-position is less then 10
        }

        // bottom left alien monster
        else if (monster4.getPosition().x <= -25) {
            monster4.removeAllImages();
            monster4.addImage(new BodyImage("data/level4Monster2.gif", 5));
            monster4.stopWalking();
            monster4.startWalking(monster4.rightMove()); // move left when x-position is less than -25
        }

        else if (monster4.getPosition().x >= -12) {
            monster4.removeAllImages();
            monster4.addImage(new BodyImage("data/level4Monster.gif", 5));
            monster4.stopWalking();
            monster4.startWalking(monster4.leftMove()); // move right when x-position is greater then -12
        }

        // bottom left alien monster
        else if (monster5.getPosition().x >= 25) {
            monster5.removeAllImages();
            monster5.addImage(new BodyImage("data/level4Monster.gif", 5));
            monster5.stopWalking();
            monster5.startWalking(monster5.leftMove()); // move left when x-position is greater than 25
        }

        else if (monster5.getPosition().x <= 12) {
            monster5.removeAllImages();
            monster5.addImage(new BodyImage("data/level4Monster2.gif", 5));
            monster5.stopWalking();
            monster5.startWalking(monster5.rightMove()); // move right when x-position is less then 12
        }

        else if (hero.getPosition().y <= -20) {
            hero.setPosition(new Vec2(0, 0));
            hero.decrementLives();

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
