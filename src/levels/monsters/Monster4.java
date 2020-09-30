package levels.monsters;// this is the library used to create this monster
import city.cs.engine.*;

/* monsters extends with the walker class as they also walk in horizontal
 * directions just like the character */
public class Monster4 extends Walker {
    // the alien monsters are created in level 5 and these are the fields used to create them
    private static final Shape alienShape = new PolygonShape(-1.15f,2.5f, 1.13f,2.5f, 1.12f,-2.38f, -1.13f,-2.38f, -1.16f,2.46f) ;
    // the use of gif files makes the monster move more realistic
    private static final BodyImage alienImage = new BodyImage("data/level4Monster.gif", 5);

    // not all alien monsters have the same speed
    int move = 10;

    // alien monster constructor
    public Monster4(World world) {
        super(world, alienShape);
        addImage(alienImage);
    }

    // movement directions of the alien monsters
    public int leftMove() {
        return -move;
    }

    public int rightMove() {
        return move;
    }
}
