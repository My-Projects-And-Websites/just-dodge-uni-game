package levels.level;/* these libraries are used to create the world and implement
 * bodies such as platforms, traps and characters in the game */
import city.cs.engine.*;
import collision.Collect_Collision;
import collision.Death_Collision;
import collision.hero.Hero;
import levels.Game;
import levels.GameLevel;
import levels.monsters.Monster2;
import org.jbox2d.common.Vec2;
import snacks.Candy;
import traps.Fireball;

import java.util.Timer;
import java.util.TimerTask;

// this is the second level of the game and it is an entirely different world
// there is no similarity between any levels except the character used
public class Level2 extends GameLevel {
    private Game game;
    private Hero hero;
    private Monster2 monster;
    private Fireball fireball = new Fireball(); // fire fall when character steps in a certain area
    private boolean collectedSnack = false; // until true, user cannot move on to the next level

    // 160 second timer for the game
    int interval = 150;
    Timer myTimer = new Timer();

    /* this is the second level of the game, it is based on a lava cave with
    * many platforms and walls with a single enemy with fast movement speed*/

    public Level2(Game game, GameLevel lvl) {
        this.game = game;
        super.populate(game); // implement everything inside populate() method in the levels.GameLevel class

        // creates the hero player
        hero = new Hero(this);
        hero.setPosition(new Vec2(0, 2.5f));

        // creates the runner monster
        monster = new Monster2(this);
        monster.setPosition(new Vec2(25, -12.5f));
        monster.addCollisionListener(new Death_Collision(hero, this)); // subtract one life in collision with monster

        // middle platform
        Shape startPlat = new BoxShape(5, 0.5f);
        Body platBody = new StaticBody(this, startPlat);
        platBody.setPosition(new Vec2(0, 0));
        platBody.addImage(new BodyImage("data/cavePlat.png", 0.75f));

        // right platform (lower wall of the right small wall)
        Shape plat1 = new BoxShape(5, 0.5f);
        Body plat1Body = new StaticBody(this, plat1);
        plat1Body.setPosition(new Vec2(10, -8));
        plat1Body.addImage(new BodyImage("data/cavePlat.png", 0.75f));

        // left platform (lower wall of the left small wall)
        Shape plat2 = new BoxShape(5, 0.5f);
        Body plat2Body = new StaticBody(this, plat2);
        plat2Body.setPosition(new Vec2(-10, -8));
        plat2Body.addImage(new BodyImage("data/cavePlat.png", 0.75f));

        // top right platform
        Shape plat3 = new BoxShape(5, 0.5f);
        Body plat3Body = new StaticBody(this, plat3);
        plat3Body.setPosition(new Vec2(10, 8));
        plat3Body.addImage(new BodyImage("data/cavePlat.png", 0.75f));

        // top left platform
        Shape plat4 = new BoxShape(5, 0.5f);
        Body plat4Body = new StaticBody(this, plat4);
        plat4Body.setPosition(new Vec2(-10, 8));
        plat4Body.addImage(new BodyImage("data/cavePlat.png", 0.75f));

        // left platform of the middle platform
        Shape plat5 = new BoxShape(4, 0.3f);
        Body plat5Body = new StaticBody(this, plat5);
        plat5Body.setPosition(new Vec2(-16.5f, 0));
        plat5Body.addImage(new BodyImage("data/cavePlat.png", 0.5f));

        // leftmost platform below the left set of stones
        Shape plat6 = new BoxShape(4, 0.3f);
        Body plat6Body = new StaticBody(this, plat6);
        plat6Body.setPosition(new Vec2(-28, 0));
        plat6Body.addImage(new BodyImage("data/cavePlat.png", 0.5f));

        // right platform of the middle platform
        Shape plat7 = new BoxShape(4, 0.3f);
        Body plat7Body = new StaticBody(this, plat7);
        plat7Body.setPosition(new Vec2(16.5f, 0));
        plat7Body.addImage(new BodyImage("data/cavePlat.png", 0.5f));

        // rightmost platform below the right set of stones
        Shape plat8 = new BoxShape(4, 0.3f);
        Body plat8Body = new StaticBody(this, plat8);
        plat8Body.setPosition(new Vec2(28, 0));
        plat8Body.addImage(new BodyImage("data/cavePlat.png", 0.5f));

        // platform of the runner monster
        Shape groundPlat = new BoxShape(30, 0.75f);
        Body groundPlatBody = new StaticBody(this, groundPlat);
        groundPlatBody.setPosition(new Vec2(0, -15));
        groundPlatBody.addImage(new BodyImage("data/groundPlat.png", 1.4f));

        // left wall near the middle platform
        Shape leftWall = new BoxShape(1, 3.25f);
        Body lWallBody = new StaticBody(this, leftWall);
        lWallBody.setPosition(new Vec2(-14, -4.25f));
        lWallBody.addImage(new BodyImage("data/leftWall.png", 8));
        SolidFixture lWallFixture = new SolidFixture(lWallBody, leftWall);
        lWallFixture.setFriction(0); // to avoid sticking to the wall, applied friction

        // right wall near the middle platform
        Shape rightWall = new BoxShape(1, 3.25f);
        Body rWallBody = new StaticBody(this, rightWall);
        rWallBody.setPosition(new Vec2(14, -4.25f));
        rWallBody.addImage(new BodyImage("data/rightWall.png", 8));
        SolidFixture rWallFixture = new SolidFixture(rWallBody, rightWall);
        rWallFixture.setFriction(0); // to avoid sticking to the wall, applied friction

        // bottom left wall
        Shape blWall = new BoxShape(1, 8);
        Body blwBody = new StaticBody(this, blWall);
        blwBody.setPosition(new Vec2(-28.5f, -7.25f));
        blwBody.addImage(new BodyImage("data/leftWall.png", 14.5f));
        SolidFixture blwFixture = new SolidFixture(blwBody, blWall);
        blwFixture.setFriction(0);

        // bottom right wall
        Shape brWall = new BoxShape(1, 8);
        Body brwBody = new StaticBody(this, brWall);
        brwBody.setPosition(new Vec2(28.5f, -7.25f));
        brwBody.addImage(new BodyImage("data/rightWall.png", 14.5f));
        SolidFixture brwFixture = new SolidFixture(brwBody, brWall);
        brwFixture.setFriction(0);

        // the stones on the upper left side of the window
        for (int i = 0; i < 4; i++) {
            Shape smallPlats = new BoxShape(0.5f, 0.5f);
            Body leftSmallPlatBody = new StaticBody(this, smallPlats);
            leftSmallPlatBody.setPosition(new Vec2((i * -4) - 18, 7.5f));
            leftSmallPlatBody.setAngleDegrees(45);
            leftSmallPlatBody.addImage(new BodyImage("data/stone.png", 0.75f));
        }

        // the stones on the upper right side of the window
        for (int i = 0; i < 4; i++) {
            Shape smallPlats = new BoxShape(0.5f, 0.5f);
            Body rightSmallPlatBody = new StaticBody(this, smallPlats);
            rightSmallPlatBody.setPosition(new Vec2((i * 4) + 18, 7.5f));
            rightSmallPlatBody.setAngleDegrees(45);
            rightSmallPlatBody.addImage(new BodyImage("data/stone.png", 0.75f));
        }

        // these candies are part of the snacks that must be collected and they will fall from above hence there body type
        for (int i = 0; i < 5; i++) {
            // creating the candy snacks
            Body snack2 = new Candy(this);
            snack2.setPosition(new Vec2((i * 13.5f) - 30, 25));
            snack2.addCollisionListener(new Collect_Collision(hero)); // add one snack when hero collides with candy
        }

        // level 2 timer to check whether the snacks have been collected
        myTimer.scheduleAtFixedRate(new TimerTask() {
            // runs the if statement every second
            public void run() {
                if (game.getLevel() == 2) {
                    // will only return true if the collected snack amount is 8
                    if (isCollectedSnack2()) { // there are 5 candies in the game and they must all be collected
                        System.out.println("Nice! Next level...");
                        game.nextLevel(lvl); // proceed to the next level when snacks are collected
                    }
                }
            }
        }, 1000, 1000);
    }

    public int getInterval() { // decrement the timer seconds
        return --interval;
    }

    // abstract methods from the GameLevel class to return value for the save and load class
    @Override
    public int getLevelNumber() {
        return 2;
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


    public Hero importPlayer() { // return hero field value
        return hero;
    }

    public Monster2 importMonster() { // return monster field value
        return monster;
    }

    public void makeItFire() { // creating the fire fall
        for (int i = 0; i < fireball.getFire().length; i++) { // array length determines the amount of time this loop will run
            // creating the shape and body of the fire fall
            Shape fireShape = new CircleShape(0.5f);
            Body fireBody = new DynamicBody(this, fireShape);
            fireBody.setPosition(new Vec2(fireball.getX(), fireball.getY())); // random positions
            fireBody.addImage(new BodyImage("data/fireball.gif", 1)); // adds gif image for the fire fall

            fireBody.addCollisionListener(new Death_Collision(hero, this)); // minus one life when collision happens
        }
    }

    public boolean isCollectedSnack2() { // determines the goal of the game
        // if hero has collected 8 snacks in total, return true
        if (hero.getCollectSnack() >= 8) {
            return collectedSnack = true;
        }
        return collectedSnack;
    }
}
