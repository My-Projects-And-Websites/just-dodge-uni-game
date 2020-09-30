package levels.level;/* these libraries are used to create the world and implement
 * bodies such as platforms, traps and characters in the game */
import city.cs.engine.*;
import collision.Collect_Collision;
import collision.Death_Collision;
import collision.hero.Hero;
import levels.Game;
import levels.GameLevel;
import levels.monsters.Monster3;
import org.jbox2d.common.Vec2;
import snacks.Choco;

import java.util.Timer; // timer class to build the timer for this level
import java.util.TimerTask;

public class Level3 extends GameLevel {
    private Game game;
    private Hero hero;
    private Monster3 monster;
    private Monster3 monster2; // there are two monsters implemented in this level
    private boolean collectedSnack = false;

    int interval = 150; // 150 second timer
    Timer myTimer = new Timer();

    public Level3(Game game, GameLevel lvl) {
        this.game = game;
        super.populate(game); // implement the method from the abstract class

        // create the hero for the level
        hero = new Hero(this);
        hero.setPosition(new Vec2(0, 0));

        // create the monsters in the level and set position
        monster = new Monster3(this);
        monster.setPosition(new Vec2(18, 13));
        monster.addCollisionListener(new Death_Collision(hero, this));

        monster2 = new Monster3(this);
        monster2.setPosition(new Vec2(-20, -5));
        monster2.addCollisionListener(new Death_Collision(hero, this)); // when hero collides, lose a life

        // all platforms are slippery so friction are set to 0

        // starting platform
        Shape startPlat = new BoxShape(7, 1);
        Body startPlatBody = new StaticBody(this, startPlat);
        startPlatBody.setPosition(new Vec2(0, -5));
        startPlatBody.addImage(new BodyImage("data/ogIce.png", 2.5f));
        SolidFixture iceFixture = new SolidFixture(startPlatBody, startPlat);
        iceFixture.setFriction(0);

        // platform at the top where bat monster is
        Shape topPlat = new BoxShape(12, 1);
        Body topPlatBody = new StaticBody(this, topPlat);
        topPlatBody.setPosition(new Vec2(0,10));
        topPlatBody.addImage(new BodyImage("data/Ice_Platform.png", 1.95f));
        SolidFixture iceFixture1 = new SolidFixture(topPlatBody, topPlat);
        iceFixture1.setFriction(0);

        // lower left platform near the center
        Shape plat1 = new BoxShape(5, 1);
        Body plat1Body = new StaticBody(this, plat1);
        plat1Body.setPosition(new Vec2(-15,2.5f));
        plat1Body.addImage(new BodyImage("data/ogIce.png", 1.65f));
        SolidFixture iceFixture2 = new SolidFixture(plat1Body, plat1);
        iceFixture2.setFriction(0);

        // lower right platform near the center
        Shape plat2 = new BoxShape(5, 1);
        Body plat2Body = new StaticBody(this, plat2);
        plat2Body.setPosition(new Vec2(15,2.5f));
        plat2Body.addImage(new BodyImage("data/ogIce.png", 1.65f));
        SolidFixture iceFixture3 = new SolidFixture(plat2Body, plat2);
        iceFixture3.setFriction(0);

        // ground platform where the other bat monster moves
        Shape plat3 = new BoxShape(22.5f, 1.5f);
        Body plat3Body = new StaticBody(this, plat3);
        plat3Body.setPosition(new Vec2(0, -12.5f));
        plat3Body.addImage(new BodyImage("data/Ice_Platform.png", 3.55f));
        SolidFixture iceFixture4 = new SolidFixture(plat3Body, plat3);
        iceFixture4.setFriction(0);

        // small left platform (original position of the top bat monster)
        Shape plat4 = new BoxShape(2.5f, 1);
        Body plat4Body = new StaticBody(this, plat4);
        plat4Body.setPosition(new Vec2(17.5f,10));
        plat4Body.addImage(new BodyImage("data/smallIcePlat.png", 2.25f));
        SolidFixture iceFixture5 = new SolidFixture(plat4Body, plat4);
        iceFixture5.setFriction(0);

        // small right platform (turning point of the top bat monster)
        Shape plat5 = new BoxShape(2.5f, 1);
        Body plat5Body = new StaticBody(this, plat5);
        plat5Body.setPosition(new Vec2(-17.5f,10));
        plat5Body.addImage(new BodyImage("data/smallIcePlat.png", 2.25f));
        SolidFixture iceFixture6 = new SolidFixture(plat5Body, plat5);
        iceFixture6.setFriction(0);

        // left upper platform on the I shaped platform
        Shape plat6 = new BoxShape(4, 1);
        Body plat6Body = new StaticBody(this, plat6);
        plat6Body.setPosition(new Vec2(-30,5));
        plat6Body.addImage(new BodyImage("data/smallIcePlat.png", 3));
        SolidFixture iceFixture7 = new SolidFixture(plat6Body, plat6);
        iceFixture7.setFriction(0);

        // right upper platform on the I shaped platform
        Shape plat7 = new BoxShape(4, 1);
        Body plat7Body = new StaticBody(this, plat7);
        plat7Body.setPosition(new Vec2(30,5));
        plat7Body.addImage(new BodyImage("data/smallIcePlat.png", 3));
        SolidFixture iceFixture8 = new SolidFixture(plat7Body, plat7);
        iceFixture8.setFriction(0);

        // left lower platform on the I shaped platform
        Shape plat8 = new BoxShape(4, 1.5f);
        Body plat8Body = new StaticBody(this, plat8);
        plat8Body.setPosition(new Vec2(-30,-5));
        plat8Body.addImage(new BodyImage("data/smallIcePlat.png", 3));
        SolidFixture iceFixture9 = new SolidFixture(plat8Body, plat8);
        iceFixture9.setFriction(0);

        // right lower platform on the I shaped platform
        Shape plat9 = new BoxShape(4, 1.5f);
        Body plat9Body = new StaticBody(this, plat9);
        plat9Body.setPosition(new Vec2(30,-5));
        plat9Body.addImage(new BodyImage("data/smallIcePlat.png", 3));
        SolidFixture iceFixture10 = new SolidFixture(plat9Body, plat9);
        iceFixture10.setFriction(0);

        // right wall near the center
        Shape wall1 = new BoxShape(1, 2.75f);
        Body wall1Body = new StaticBody(this, wall1);
        wall1Body.setPosition(new Vec2(19, 6.25f));
        wall1Body.addImage(new BodyImage("data/iceWall.png", 7.5f));
        SolidFixture iceFixture13 = new SolidFixture(wall1Body, wall1);
        iceFixture13.setFriction(0);

        // left wall near the center
        Shape wall2 = new BoxShape(1, 2.75f);
        Body wall2Body = new StaticBody(this, wall2);
        wall2Body.setPosition(new Vec2(-19, 6.25f));
        wall2Body.addImage(new BodyImage("data/iceWall.png", 7.5f));
        SolidFixture iceFixture14 = new SolidFixture(wall2Body, wall2);
        iceFixture14.setFriction(0);

        // leftmost wall near the center
        Shape lWall = new BoxShape(1, 4);
        Body lWallBody = new StaticBody(this, lWall);
        lWallBody.setPosition(new Vec2(-30, 0));
        lWallBody.addImage(new BodyImage("data/iceWall.png", 10));
        SolidFixture iceFixture15 = new SolidFixture(lWallBody, lWall);
        iceFixture15.setFriction(0);

        // rightmost wall near the center
        Shape rWall = new BoxShape(1, 4);
        Body rWallBody = new StaticBody(this, rWall);
        rWallBody.setPosition(new Vec2(30, 0));
        rWallBody.addImage(new BodyImage("data/iceWall.png", 10));
        SolidFixture iceFixture16 = new SolidFixture(rWallBody, rWall);
        iceFixture16.setFriction(0);

        // spike near the right wall near the center
        Shape spike1 = new BoxShape(0.5f, 0.5f);
        Body spike1Body = new StaticBody(this, spike1);
        spike1Body.setPosition(new Vec2(12.75f, 3.5f));
        spike1Body.addImage(new BodyImage("data/spike.png", 1));
        spike1Body.addCollisionListener(new Death_Collision(hero, this));

        // spike near the left wall near the center
        Shape spike2 = new BoxShape(0.5f, 0.5f);
        Body spike2Body = new StaticBody(this, spike2);
        spike2Body.setPosition(new Vec2(-12.75f, 3.5f));
        spike2Body.addImage(new BodyImage("data/spike.png", 1));
        spike2Body.addCollisionListener(new Death_Collision(hero, this));

        // rightmost spike
        Shape spike3 = new BoxShape(0.5f, 0.5f);
        Body spike3Body = new StaticBody(this, spike3);
        spike3Body.setPosition(new Vec2(32, -3));
        spike3Body.addImage(new BodyImage("data/spike.png", 1));
        spike3Body.addCollisionListener(new Death_Collision(hero, this));

        // leftmost spike
        Shape spike4 = new BoxShape(0.5f, 0.5f);
        Body spike4Body = new StaticBody(this, spike4);
        spike4Body.setPosition(new Vec2(-32, -3));
        spike4Body.addImage(new BodyImage("data/spike.png", 1));
        spike4Body.addCollisionListener(new Death_Collision(hero, this));

        // the chocolate snack beside the right wall near the center
        Choco choco1 = new Choco(this);
        choco1.setPosition(new Vec2(16, 5));
        choco1.addCollisionListener(new Collect_Collision(hero));

        // the chocolate snack beside the left wall near the center
        Choco choco2 = new Choco(this);
        choco2.setPosition(new Vec2(-16, 5));
        choco2.addCollisionListener(new Collect_Collision(hero));

        // the chocolate snack above starting position
        Choco choco3 = new Choco(this);
        choco3.setPosition(new Vec2(0, 6));
        choco3.addCollisionListener(new Collect_Collision(hero));

        // leftmost chocolate snack
        Choco choco4 = new Choco(this);
        choco4.setPosition(new Vec2(-32.5f, 1));
        choco4.addCollisionListener(new Collect_Collision(hero));

        // rightmost chocolate snack
        Choco choco5 = new Choco(this);
        choco5.setPosition(new Vec2(32.5f, 1));
        choco5.addCollisionListener(new Collect_Collision(hero));

        // timer for level 3
        myTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() { // this method will run the decrementation of the timer by 1 second and check the if statement every 1 second
                if (game.getLevel() == 3) { // this would always be true as current level is 3
                    if (isCollectedSnack3()) { // if true, proceed to the alst level and output text in the console
                        System.out.println("Nice! Next level...");
                        game.nextLevel(lvl);

                    }
                }
            }
        }, 1000, 1000);
    }

    public int getInterval() { // decrement the seconds
        return --interval;
    }

    // abstract methods from the GameLevel class to return value for the save and load class
    @Override
    public int getLevelNumber() {
        return 3;
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

    public Hero importPlayer() { // return the value of the hero field
        return hero;
    }

    public Monster3 importMonster1() { // return the value of the first monster field
        return monster;
    }

    public Monster3 importMonster2() { // return the value of the second monster field
        return monster2;
    }

    public boolean isCollectedSnack3() { // the third goal for level completion is to have 13 snacks in total
        if (hero.getCollectSnack() >= 13) {
            return collectedSnack = true; // return true if user has collected 13 snacks
        }
        return collectedSnack; // return false if not
    }
}
