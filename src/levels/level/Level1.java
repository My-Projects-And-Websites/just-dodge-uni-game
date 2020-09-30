package levels.level;/* these libraries are used to create the world and implement
 * bodies such as platforms, traps and characters in the game */
import city.cs.engine.*;
import collision.Collect_Collision;
import collision.Death_Collision;
import collision.hero.Hero;
import levels.Game;
import levels.GameLevel;
import levels.monsters.Monster;
import org.jbox2d.common.Vec2; // used for positioning elements in the window
import snacks.Cookie;
import traps.SwordFall;

import java.util.Timer;
import java.util.TimerTask; // timer classes

// first level of the whole game, GameWorld class
public class Level1 extends GameLevel { // this class extends into the abstract class, everything in levels.GameLevel is inherited
    private Game game;
    private Hero hero;
    private Monster monster;
    private SwordFall swordFall = new SwordFall(); // this is the class for when the swords fall
    private boolean collectedSnack = false; // this checks if the level has been completed

    int interval = 150; // time is set to 150
    Timer myTimer = new Timer(); // instantiating a new timer

    /** this is the first level of the game, the hero character must collect
     * the snacks (in this level, there are 3) to proceed to the next level
     * in total there are 17 snacks in the game and they must all be collected
     * to finish the game, this level contains 9 platforms the character can move on
     * using the keys WAD (W - jump, A - move left, D - move right), there are 4 levels in total
     * with different kind of monsters and different speed to make it difficult for the user and must
     * complete the game within 200 seconds
     *
     * this class extends to the abstract class created and it is populated by the timer built in that
     * class, this class also has its own timer which is used to be displayed by the view object in levels.Game class
     *
     * @param game - this parameter will pass the value of the levels.Game class where this class is called
     * @param lvl - parameter to access any level*/

    public Level1(Game game, GameLevel lvl) {
        this.game = game;

        super.populate(this.game); // inherits code from the levels.GameLevel class using the populate method

        // JOptionPane.showMessageDialog(null, "Welcome! Just dodge all traps laid out in this game. Use mouse click to create a brick platform and protect the player, use W, A and D for motion controls.");

        // imports the hero player
        hero = new Hero(this);
        hero.setPosition(new Vec2(0, 0));

        // tree monster created
        monster = new Monster(this);
        monster.setPosition(new Vec2(-10, -9)); // original position of the monster
        monster.addCollisionListener(new Death_Collision(hero, this)); // collision applied to the monster

        // ground platform where tree monster is moving on
        Shape groundPlatform = new BoxShape(15, 1.25f); // height and width divided in half to set mode in center
        Body groundPlat = new StaticBody(this, groundPlatform); // will stay in place where it is originally placed
        groundPlat.setPosition(new Vec2(0, -12));
        groundPlat.addImage(new BodyImage("data/brick_resized.png", 4)); // adds the image

        // creating more platforms with no friction
        // left slope without friction
        Shape platform1 = new BoxShape(6, 1);
        Body ground1 = new StaticBody(this, platform1);
        ground1.setPosition(new Vec2(-12.5f, -0.5f));
        ground1.addImage(new BodyImage("data/castlePlatform.png", 2));
        ground1.setAngleDegrees(30); // rotates the platform by 30 degrees
        SolidFixture fixture1 = new SolidFixture(ground1, platform1); // allows modification of properties of the platform
        fixture1.setFriction(0); // setting friction to 0 will make the platform slippery

        // right slope without friction
        Shape platform2 = new BoxShape(6, 1);
        Body ground2 = new StaticBody(this, platform1);
        ground2.setPosition(new Vec2(12.5f, -0.5f));
        ground2.setAngleDegrees(-30); // reverse rotation of platform by 30 degrees
        ground2.addImage(new BodyImage("data/castlePlatform.png", 2));
        SolidFixture fixture2 = new SolidFixture(ground2, platform2);
        fixture2.setFriction(0); // setting friction to 0 will make the platform slippery

        // top stone platform without friction
        Shape platform3 = new BoxShape(6, 1.25f);
        Body ground3 = new StaticBody(this, platform3);
        ground3.setPosition(new Vec2(0, 8));
        ground3.addImage(new BodyImage("data/castlePlatform.png", 2));
        SolidFixture fixture3 = new SolidFixture(ground3, platform3); // allows modification of properties of the platform
        fixture3.setFriction(0); // setting friction to 0 will make the platform slippery

        // middle stone platform without friction
        Shape platform4 = new BoxShape(6, 1); // rectangle shape
        Body ground4 = new StaticBody(this, platform4);
        ground4.setPosition(new Vec2(0, -5.5f));
        ground4.addImage(new BodyImage("data/castlePlatform.png", 2));
        SolidFixture fixture4 = new SolidFixture(ground4, platform4); // allows modification of properties of the platform
        fixture4.setFriction(0); // setting friction to 0 will make the platform slippery

        // leftmost bottom platform
        Shape platform5 = new BoxShape(6, 1);
        Body ground5 = new StaticBody(this, platform5);
        ground5.setPosition(new Vec2(-27.5f, -5));
        ground5.addImage(new BodyImage("data/brick_resized2.png", 3));

        // leftmost top platform
        Shape platform6 = new BoxShape(3, 1);
        Body ground6 = new StaticBody(this, platform6);
        ground6.setPosition(new Vec2(-25, 1));
        ground6.addImage(new BodyImage("data/brick_resized3.png", 3));

        // rightmost bottom platform
        Shape platform7 = new BoxShape(6, 1);
        Body ground7 = new StaticBody(this, platform7);
        ground7.setPosition(new Vec2(27.5f, -5));
        ground7.addImage(new BodyImage("data/brick_resized2.png", 3));

        // rightmost top platform
        Shape platform8 = new BoxShape(3, 1);
        Body ground8 = new StaticBody(this, platform8);
        ground8.setPosition(new Vec2(25, 1));
        ground8.addImage(new BodyImage("data/brick_resized3.png", 3));

        // drops cookies in the window
        for (int i = 0; i < 3; i++) { // this loop will drop 3 cookies in the window
            Body snack1 = new Cookie(this); // creates an object using snacks.Cookie class
            snack1.setPosition(new Vec2((i * 25) - (25), 30));
            snack1.addCollisionListener(new Collect_Collision(hero));
            // collecting these cookies will add one snack to the current amount
        }

        myTimer.scheduleAtFixedRate(new TimerTask() { // creating the display timer
            public void run() { // this method must be placed and cannot be renamed as TimerTask is abstract
                if (game.getLevel() == 1) { // this statement will be checked per period so if this is true...
                    if (isCollectedSnack()) { // this statement will also be checked and will only return true if the isCollectedSnack() method is true
                        System.out.println("Nice! Next level..."); // output text in the console
                        game.nextLevel(lvl); // call method from the levels.Game class
                    }
                }
            }
        }, 1000, 1000); // 1 second delay
    }

    public int getInterval() { // decrements the interval value
        return --interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getLevelNumber() {
        return 1;
    }

    // abstract methods from the GameLevel class to return value for the save and load class
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


    public Hero importPlayer() { // return the value of hero field
        return hero;
    }

    public Monster importMonster() { // return the value of the monster field
        return monster;
    }

    public void makeItRain() { // this method handles the fall of the swords
        for (int i = 0; i < swordFall.getSword().length; i++) { // this loop will repeat depending on the array length
            // making the shape and the body of the sword
            Shape swordShape = new BoxShape(0.05f, 0.5f);
            Body swordBody = new DynamicBody(this, swordShape);
            swordBody.setPosition(new Vec2(swordFall.getxPos(), swordFall.getyPos())); // random positions
            swordBody.addImage(new BodyImage("data/sword.png", 2));

            swordBody.addCollisionListener(new Death_Collision(hero, this)); // minus one life in collision with this trap
        }
    }

    public boolean isCollectedSnack() { // this boolean method checks whether the snacks have been collected
        if (hero.getCollectSnack() >= 3) { // if three has been collected then return true and proceed tot he next level
            return collectedSnack = true;
        }
        return collectedSnack;
    }
}