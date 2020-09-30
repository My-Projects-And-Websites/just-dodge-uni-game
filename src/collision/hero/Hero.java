package collision.hero;// city engine library can be accessed using the import class
import city.cs.engine.*;

/* a walker is literally what it is! a character that can walk
* this involves the enemy monsters and the hero as these all moves */
public class Hero extends Walker {
    // created a polygon shape for the character to identify specific coordinates of the body
    private static Shape heroShape = new PolygonShape(1.34f,1.01f, 1.36f,-1.78f, -0.86f,-1.81f, -0.85f,0.98f);

    // this field initialises the first image use dont he character when it is first loaded, with the height of 4
    private static BodyImage heroImage = new BodyImage("data/player.gif", 4);

    // these fields are attributes for the data about the character, this records the collected items and the amount of lives left
    private int collectSnack;
    private int numberOfLives;

    // constructs the hero player and assigns the number of lives and image of the character
    public Hero(World world) {
        super(world, heroShape);
        addImage(heroImage);
        collectSnack = 0;
        numberOfLives = 10;
    }

    // sets the number of lives of my hero player to 10
    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    // returns the number of lives left
    public int getNumberOfLives() {
        return numberOfLives;
    }

    /* this method is called in the collision class and decrement lives
    * when character collides with traps or monsters, this decrements the number of lives */
    public int decrementLives() {
        numberOfLives--;
        return numberOfLives;
    }

    // sets the number of snack initialised in the constructor
    public void setCollectSnack(int collectSnack) {
        this.collectSnack = collectSnack;
    }

    // returns the number of the collected snack
    public int getCollectSnack() {
        return collectSnack;
    }

    /* this method is called when the character collides with the snacks throughout the game
    * and adds one to the collectSnack field, also outputs text in the console */
    public void plusOneSnack() {
        collectSnack++;
        System.out.println("Yummy! " + collectSnack);
    }
}
