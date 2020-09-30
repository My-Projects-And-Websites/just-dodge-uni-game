package levels.monsters;// this is the library used to create this monster
import city.cs.engine.*;

/* monsters extends with the walker class as they also walk in horizontal
* directions just like the character */
public class Monster extends Walker {
    // these are the fields created to create the tree monster on the first level
    // the final keyword means the value assigned cannot be changed anywhere in the class
    private static final Shape monsterShape = new PolygonShape(-0.7f,2.05f, -1.75f,-0.68f, -1.15f,-1.7f, 1.47f,-1.7f, 2.05f,-0.47f, 1.07f,2.05f);
    private static final BodyImage monsterImage = new BodyImage("data/monster.gif", 5);

    // movement speed of the monster
    int move = 7;

    // constructor for the monster
    public Monster(World world) {
        super(world, monsterShape);
        addImage(monsterImage);
    }

    // this method will reverse the direction of the monster hence move left
    public int leftMove() {
        return -move;
    }

    // this will make the monster move right
    public int rightMove() {
        return move;
    }
}
