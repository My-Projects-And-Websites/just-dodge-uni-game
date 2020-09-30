package snacks;// imports the library to create the snack
import city.cs.engine.*;

// creates snack for level 3

public class Choco extends StaticBody { // it stays in position so static is required
    // shape and image of chocolate
    private static final Shape chocoShape = new BoxShape(2, 1);
    private static final BodyImage chocoImage = new BodyImage("data/choco.png", 2);

    // chocolate constructor
    public Choco(World world) {
        // refers to the parent class constructor and adds the image
        super(world, chocoShape);
        addImage(chocoImage);
    }
}
