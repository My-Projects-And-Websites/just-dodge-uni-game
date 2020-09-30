package snacks;// imports the library to create the snack
import city.cs.engine.*;

// creates candy snack for level 2

public class Candy extends DynamicBody { // it will fall from above so dynamic body
    // shape and image of candy
    private final static Shape candyShape = new PolygonShape(-0.372f,0.172f, 0.368f,0.17f, 0.372f,-0.172f, -0.366f,-0.168f, -0.372f,0.168f);
    private final static BodyImage candyImage = new BodyImage("data/candy.png", 2);

    // candy constructor
    public Candy(World world) {
        // refers to the parent class constructor and adds the image
        super(world, candyShape);
        addImage(candyImage);
    }
}
