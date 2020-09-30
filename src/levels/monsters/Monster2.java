package levels.monsters;// this is the library used to create this monster
import city.cs.engine.*;

/* monsters extends with the walker class as they also walk in horizontal
 * directions just like the character */
public class Monster2 extends Walker {
    // the order the keywords are written in can be different
    // these are the fields used to create the runner monster in level 2
    private final static Shape monsterShape = new PolygonShape(-1.18f,-1.97f, -1.18f,0.76f, 1.35f,0.77f, 1.35f,-1.97f);
    private final static BodyImage monsterImage = new BodyImage("data/level2Monster.gif", 4);

    // this monster has a fast movement speed so its speed is doubled compared to tree monster
    int move = 15;

    // runner monster constructor
    public Monster2(World world) {
        super(world, monsterShape);
        addImage(monsterImage);
    }

    // move left when method is called
    public int leftMove() {
        return -move;
    }

    // move right when method is called
    public int rightMove() {
        return move;
    }
}
