package levels.monsters;// this is the library used to create this monster
import city.cs.engine.*;

/* monsters extends with the walker class as they also walk in horizontal
 * directions just like the character */
public class Monster3 extends Walker {
    // these are the fields used to create the bat monster in level 3
    // the polygon shape is used to determine the specific shape of the bat
    private final static Shape batShape = new PolygonShape(-2.48f,2.48f, -2.49f,-2.47f, 2.46f,-2.47f, 2.48f,2.47f);
    // this is the rendered image
    private final static BodyImage batImage = new BodyImage("data/level3Monster.gif", 5);

    // this is the movement speed of the bat
    float move = 12.5f;

    // bat monster constructor
    public Monster3(World world) {
        super(world, batShape);
        addImage(batImage);
    }

    // movement directions for the bat monster
    public float leftMove() {
        return -move;
    }

    public float rightMove() {
        return move;
    }

}
