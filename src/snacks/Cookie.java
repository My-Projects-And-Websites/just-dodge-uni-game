package snacks;// imports the library to create the snack
import city.cs.engine.*;

// creates snack for the first level

public class Cookie extends DynamicBody { // it will fall from above so it must be a dynamic
    // creates the shape and image of cookie
    private static final Shape cookieShape = new CircleShape(0.5f);
    private static final BodyImage cookieImage = new BodyImage("data/cookie.png", 1);

    // cookie constructor
    public Cookie(World world) {
        // inherits the parent class constructor and adds the image
        super(world, cookieShape);
        addImage(cookieImage);
    }
}
