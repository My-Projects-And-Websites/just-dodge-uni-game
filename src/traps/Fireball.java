package traps;// these are the libraries required to create the fireball drop in level 2
import java.util.Random;

// fireball class, used in level 2 which will drop when the character is in a certain location
public class Fireball {
    // the number of fire ball that will drop
    private int[] fire = new int[20];

    // new random variable is used to assign random numbers for every element in the arrat
    Random rand = new Random();

    // variables x and y to assign random positioning for the fireballs
    int x, y;

    // getX and getY method assigns the bounds for the x and y variables
    public int getX() {
        // subtracted bounds to get negative integers
        x = rand.nextInt(40) - 20;
        return x;
    }

    public int getY() {
        // random y values for every item in the element
        y = rand.nextInt(30) + 30;
        return y;
    }

    /* because the field fire is private, this method allows access
    for other classes to get the value for the fire method */
    public int[] getFire() {
        return fire;
    }
}
