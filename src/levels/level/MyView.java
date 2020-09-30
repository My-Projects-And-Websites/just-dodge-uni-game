package levels.level;// these libraries are used to render the images of the background in every level
import city.cs.engine.*;
import collision.hero.Hero;
import levels.Game;
import levels.level.Level1;

import java.awt.*;
import javax.swing.*;

// the UserView class extension is inherited by this class
// this levels.level.MyView class handles the images and text shown in the window
public class MyView extends UserView {
    // these are the fields that contains the values for all the images in the background
    /* because some fields from another class are required here, their class must be instantiated and
    * the value must be passed on by a parameter in the constructor */
    private Hero hero;
    private Image background, background2, background3, background4; // you can declare fields in one line as long as they are the same class!
    private Level1 gWorld;
    private Game g;

    // constructor of the view and background images are initialised along with the other fields
    public MyView(World world, Hero hero, Level1 gWorld, Game g, int height, int width) {
        super(world, height, width); // the height and the width of the window is passed into these class

        // objects are instantiated
        this.hero = hero;
        this.gWorld = gWorld;
        this.g = g;

        // initialisation of the background images
        this.background = new ImageIcon("data/backgrounds/bg.png").getImage();
        this.background2 = new ImageIcon("data/backgrounds/bg2.png").getImage();
        this.background3 = new ImageIcon("data/backgrounds/bg3.png").getImage();
        this.background4 = new ImageIcon("data/backgrounds/bg4.png").getImage();
    }

    // the backgrounds are shown depending on which level the user is on
    // this is done by using conditional statements
    protected void paintBackground(Graphics2D g) {
        // if user is in level 1 render the first image and same goes to other if statements
        if (this.g.getLevel() == 1) {
            g.drawImage(background, 0, 0, this);
        }

        else if (this.g.getLevel() == 2) {
            g.drawImage(background2, 0, 0, this);
        }

        else if (this.g.getLevel() == 3) {
            g.drawImage(background3, 0, 0, this);
        }

        else if (this.g.getLevel() == 4) {
            g.drawImage(background4, 0 , 0, this);
        }
    }

    // this will display the text which displays the data about the character such as the lives or the collected snack
    protected void paintForeground(Graphics2D g) {
        // sets the font style, size and the color of the text
        g.setFont(new Font("Times", Font.PLAIN, 23));
        g.setColor(Color.white);

        // the drawString method writes text on the window, these displays the timer, lives left, amount of snack collected and current level
        g.drawString("Timer: " + gWorld.interval, 20, 40);
        g.drawString("Lives: " + hero.getNumberOfLives(), 20, 80);
        g.drawString("Snack: " + hero.getCollectSnack(), 20, 120);
        g.drawString("Level: " + this.g.getLevel(), 20, 160);

        // if the lives hits 0 or the timer hits 0 and the amount of snack is not equal to 17, write game over and stop world
        if (hero.getNumberOfLives() <= 0 || (gWorld.interval == 0 && hero.getCollectSnack() != 17)) {
            g.setFont(new Font("Arial", Font.BOLD, 45));
            g.drawString("GAME OVER", 585, 350);
            this.g.getWorld().stop();
        }
    }

    // accessor method for the hero field
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    // mutator method for the hero field
    public Hero getHero() {
        return hero;
    }
}
