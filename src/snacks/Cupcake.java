package snacks;// imports the library to create the snack
import city.cs.engine.*;

// creates snack for the last level

public class Cupcake extends StaticBody { // it stays in its original position so static
    // creates the cupcake image and shape
    private static final Shape cakeShape = new CircleShape(1);
    private static final BodyImage cakeImage = new BodyImage("data/cupcake.png", 2);

    // cupcake constructor
    // parameter world allows inheritance from the parent constructor using supe rmethod
    public Cupcake(World world) {
        // adds the image to the shape
        super(world, cakeShape);
        addImage(cakeImage);
    }
}
