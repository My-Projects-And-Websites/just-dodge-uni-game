package traps;// random class used to randomise positioning of where the swords will fall
import java.util.Random;

public class SwordFall {
    // amount of sword that will fall
    private int[] sword = new int[10];

    // this variable is used to hold the random values
    Random rand = new Random();

    // these variables are used to store random numbers for the x and y position of the swords
    int xPos, yPos;

    // the subtraction in the bounds allows fall of swords in negative bounds and return the value of x
    public int getxPos() {
        xPos = rand.nextInt(50) - 25;
        return xPos;
    }

    // the subtraction in the bounds allows fall of swords in negative bounds and return the value of y
    public int getyPos() {
        yPos = rand.nextInt(30) + 30;
        return yPos;
    }

    // return the value of the amount of the sword field
    public int[] getSword() {
        return sword;
    }
}
