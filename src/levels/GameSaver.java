/* these are the classes required to construct this class
* these are classes stored within a package, these packages are used
* to contain all the classes that have similarities and functions that are linked together */

package levels;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import collision.hero.Hero;
import levels.level.Level1;
import levels.level.Level2;
import levels.level.Level3;
import levels.level.Level4; // these classes are imported to create the bodies and the levels for this save class

import java.io.FileWriter;
import java.io.IOException;
// these libraries are used to write data to a specified file

public class GameSaver {
    private String file; // file field to assign the field name
    private Game game;
    private Level1 world1;
    private Level2 world2;
    private Level3 world3;
    private Level4 world4; // these are all the levels in the whole game

    /** this class is used to save data about the character (lives, snacks, position) and the
     * current level when clicked in the GUI buttons. After saving, this data can be used to load this data and resume
     * the game from where the user left off
     *
     * @param file - this is the text file where the data will be saved
     * @param game - to get access to the current level, game parameter with the Game class is assigned
     * @param world1 - first level
     * @param world2 - second level
     * @param world3 - third level
     * @param world4 - last level */

    //save class constructor
    public GameSaver(String file, Game game, Level1 world1, Level2 world2, Level3 world3, Level4 world4) {
        this.file = file;
        this.game = game;
        this.world1 = world1;
        this.world2 = world2;
        this.world3 = world3;
        this.world4 = world4;
        // assigning value to the to all the fields using the parameters
    }

    /** this method uses the throws keyword meaning the specified exception may occur to maintain code flow
     * this method is used to save the data depending on which level the user is currently in
     *
     * @throws IOException - this will catch errors from the input and output */

    public void saveState() throws IOException {
        System.out.println("Saving state..."); // console test to check that the method is working

        FileWriter writer = null; // set field to null to give it no value
        if (game.getLevel() == 1) { // if the level is set to 1
            try {
                writer = new FileWriter(file); // assign the text file to this field

                writer.write(game.getLevel() + "\n"); // write the game level and make a line break
                writer.write(world1.getInterval() + "\n"); // write the current time
                writer.write(world1.getLives() + "\n"); // write the current lives
                writer.write(world1.getSnack() + "\n"); // write the current snack

                writer.write(world1.importPlayer().getPosition().x + "\n"); // get the xPosition of the hero in level 1
                writer.write(world1.importPlayer().getPosition().y + "\n"); // get the yPosition of the hero in level 1

                for (DynamicBody body : world1.getDynamicBodies()) { // target all the dynamic bodies in the current level
                    // write the name of the class, its x position and y position
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }

                for (StaticBody body : world1.getStaticBodies()) { // target all the static bodies in the current level
                    // getSimpleName method removes the directory of its package when being written
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }

            } finally { // finally keyword will run the code written inside whether there is an error or not
                // if there is no more to write, close the file
                if (writer != null) {
                    writer.close(); // must always be closed
                }
            }
        }
        else if (game.getLevel() == 2) { // if user in level 2, run this method
            try { // try all these code and when there is an error, jump to finally section
                writer = new FileWriter(file); // assign the file name

                writer.write(game.getLevel() + "\n");
                writer.write(world2.getInterval() + "\n");
                writer.write(world2.getLives() + "\n");
                writer.write(world2.getSnack() + "\n");
                // store all the data about the current level, time and hero data

                writer.write(world2.importPlayer().getPosition().x + "\n");
                writer.write(world2.importPlayer().getPosition().y + "\n");
                // save the current position of the hero

                for (DynamicBody body : world2.getDynamicBodies()) { // for all the dynamic bodies in second level
                    // save data of class name and position
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }

                for (StaticBody body : world2.getStaticBodies()) { // for all the static bodies in second level
                    // save data of class name and position
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }
            } finally {
                if (writer != null) {
                    writer.close(); // close the file when no more to write
                }
            }
        }
        else if(game.getLevel() == 3) { // if the user is in level 3, run this code
            try {
                writer = new FileWriter(file); // assign text file to the writer field

                writer.write(game.getLevel() + "\n");
                writer.write(world3.getInterval() + "\n");
                writer.write(world3.getLives() + "\n");
                writer.write(world3.getSnack() + "\n");
                // store all enlisted data

                writer.write(world3.importPlayer().getPosition().x + "\n");
                writer.write(world3.importPlayer().getPosition().y + "\n");
                // store the position of the hero

                for (DynamicBody body : world3.getDynamicBodies()) { // store the class name and position of dynamic body
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }

                for (StaticBody body : world3.getStaticBodies()) { // store the class name and position of static body
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }
            } finally {
                if (writer != null) {
                    writer.close(); // close the file when there is no more to write
                }
            }
        }
        else if (game.getLevel() == 4) { // if user in level 4
            try {
                writer = new FileWriter(file); // assign text file to writer field

                writer.write(game.getLevel() + "\n");
                writer.write(world4.getInterval() + "\n");
                writer.write(world4.getLives() + "\n");
                writer.write(world4.getSnack() + "\n");
                // this will write all enlisted data with a line break
                // line breaks will allow writing to text file in multiple lines

                writer.write(world4.importPlayer().getPosition().x + "\n");
                writer.write(world4.importPlayer().getPosition().y + "\n");
                // write character position

                for (DynamicBody body : world4.getDynamicBodies()) {
                    // write class name and position of all dynamic bodies
                    // separated by commas, important for load class
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }

                for (StaticBody body : world4.getStaticBodies()) {
                    // write class name and position of all static bodies
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }
            } finally {
                if (writer != null) {
                    writer.close(); // close file
                }
            }
        }
    }
}
