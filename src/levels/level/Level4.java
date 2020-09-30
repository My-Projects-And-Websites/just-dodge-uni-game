// these classes are built in the same program and they are imported to give access to its methods and classes
// package keyword allows access to the specified library

package levels.level;

/* these libraries are used to create the world and implement
 * bodies such as platforms, traps and characters in the game */
import city.cs.engine.*;
import collision.Collect_Collision;
import collision.Death_Collision;
import collision.hero.Hero;
import levels.Game;
import levels.GameLevel;
import levels.monsters.Monster4;
import org.jbox2d.common.Vec2;
import snacks.Cupcake;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

// this is the last level of the whole game
public class Level4 extends GameLevel {
    private Game game;
    private Hero hero;
    private Monster4 monster, monster2, monster3, monster4, monster5; // declare fields for all monsters in the level
    private boolean collectedSnack = false;
    private SoundClip completeSound;

    // 150 second timer for this level
    int interval = 150;
    Timer myTimer = new Timer();

    /** this level is the last level and it contains 5 monsters with different movement speeds
    * this increases the difficulty as the last snack is difficult to get due to the fast paced monsters
    * however it is still do-able, this class extends to the abstract class
    *
    * @param game - to access every method in game
     * @param lvl - to access the method in the parent class*/

    public Level4(Game game, GameLevel lvl) {
        this.game = game; // assign value to the game
        super.populate(game); // implement everything from parent class

        hero = new Hero(this);
        hero.setPosition(new Vec2(0, 0)); // set character position to (0, 0)

        monster = new Monster4(this);
        monster.setPosition(new Vec2(-12.5f, -2.5f));
        monster.addCollisionListener(new Death_Collision(hero, this)); // create the first monster, middle alien

        monster2 = new Monster4(this);
        monster2.setPosition(new Vec2(-18, 6));
        monster2.addCollisionListener(new Death_Collision(hero, this)); // create the second alien, top left

        monster3 = new Monster4(this);
        monster3.setPosition(new Vec2(18, 6));
        monster3.addCollisionListener(new Death_Collision(hero, this)); // create the third alien, top right

        monster4 = new Monster4(this);
        monster4.setPosition(new Vec2(-25, -10));
        monster4.addCollisionListener(new Death_Collision(hero, this)); // create the fourth alien, bottom left

        monster5 = new Monster4(this);
        monster5.setPosition(new Vec2(25, -10));
        monster5.addCollisionListener(new Death_Collision(hero, this)); // create the fifth alien, bottom right

        // middle platform
        Shape startPlat = new BoxShape(16, 2);
        Body startPlatBody = new StaticBody(this, startPlat);
        startPlatBody.setPosition(new Vec2(0, -5));
        startPlatBody.addImage(new BodyImage("data/meteoritePlat.png", 5));

        // top right platform
        Shape plat1 = new BoxShape(9, 1);
        Body plat1Body = new StaticBody(this, plat1);
        plat1Body.setPosition(new Vec2(15, 5));
        plat1Body.addImage(new BodyImage("data/meteoritePlat.png", 3));

        // top left platform
        Shape plat2 = new BoxShape(9, 1);
        Body plat2Body = new StaticBody(this, plat2);
        plat2Body.setPosition(new Vec2(-15, 5));
        plat2Body.addImage(new BodyImage("data/meteoritePlat.png", 3));

        // bottom right platform
        Shape plat3 = new BoxShape(11, 1);
        Body plat3Body = new StaticBody(this, plat3);
        plat3Body.setPosition(new Vec2(17.5f, -15));
        plat3Body.addImage(new BodyImage("data/meteoritePlat.png", 3));

        // bottom left platform
        Shape plat4 = new BoxShape(11, 1);
        Body plat4Body = new StaticBody(this, plat4);
        plat4Body.setPosition(new Vec2(-17.5f, -15));
        plat4Body.addImage(new BodyImage("data/meteoritePlat.png", 3));

        // small platform for one of the snacks
        Shape plat5 = new BoxShape(2, 1);
        Body plat5Body = new StaticBody(this, plat5);
        plat5Body.setPosition(new Vec2(0, -14));
        plat5Body.addImage(new BodyImage("data/meteoritePlat.png", 1));

        // top left cupcake
        Cupcake cake1 = new Cupcake(this);
        cake1.setPosition(new Vec2(-22, 10));
        cake1.addCollisionListener(new Collect_Collision(hero));

        // top right cupcake
        Cupcake cake2 = new Cupcake(this);
        cake2.setPosition(new Vec2(22, 10));
        cake2.addCollisionListener(new Collect_Collision(hero));

        // bottom cupcake
        Cupcake cake3 = new Cupcake(this);
        cake3.setPosition(new Vec2(0, -10));
        cake3.addCollisionListener(new Collect_Collision(hero));

        // top cupcake
        Cupcake cake4 = new Cupcake(this);
        cake4.setPosition(new Vec2(0, 15));
        cake4.addCollisionListener(new Collect_Collision(hero));

        // timer for this level
        myTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (game.getLevel() == 4) {
                    if (isCollectedSnack4()) {
                        game.getGameMusic().stop(); // stop the background music

                        // play this sound when all snacks has been collected
                        try {
                            completeSound = new SoundClip("data/music/complete.wav");
                            completeSound.play();
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                            System.out.println(e); // print error in console if exists
                        }

                        System.out.println("Congratulations!"); // print text in console

                        JOptionPane.showMessageDialog(null, "Congratulations!"); // dialog box will appear with a message
                        System.exit(0); // and when user clicks ok, exit the system
                    }
                }
            }
        }, 1000, 1000);
    }

    public int getInterval() { // return decrementing interval
        return --interval;
    }

    // abstract methods from the GameLevel class to return value for the save and load class
    @Override
    public int getLevelNumber() {
        return 4;
    }

    @Override
    public int getSnack() {
        return hero.getCollectSnack();
    }

    @Override
    public int getLives() {
        return hero.getNumberOfLives();
    }

    @Override
    public Hero getHero() {
        return hero;
    }

    public Hero importPlayer() { // return value of hero field
        return hero;
    }

    public Monster4 importMonster() { // return value of monster field
        return monster;
    }

    public Monster4 importMonster2() { // return value of monster2 field
        return monster2;
    }

    public Monster4 importMonster3() { // return value of monster3 field
        return monster3;
    }

    public Monster4 importMonster4() { // return value of monster4 field
        return monster4;
    }

    public Monster4 importMonster5() { // return value of monster5 field
        return monster5;
    }

    public boolean isCollectedSnack4() { // this boolean method will return true if the number of snacks collected is 17
        if (hero.getCollectSnack() == 17) {
            return collectedSnack = true;
        }
        return collectedSnack; // collectedSnack = false
    }
}
