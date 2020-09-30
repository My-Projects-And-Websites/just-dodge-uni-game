/* these classes are stored inside the levels package
* these are the classes required to be able to load the
* current level and hero data */

package levels;

import city.cs.engine.*;
import collision.hero.Hero;
import levels.level.*;
import levels.monsters.*;
import org.jbox2d.common.Vec2;
import snacks.*;

/* these classes are used to load the data written in the specified text file
* these will read the all the lines in the text file and load the data */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; // this is an exception

// game loader class
public class GameLoader {
    private String file;
    private Game game;
    private Level1 world1;
    private Level2 world2;
    private Level3 world3;
    private Level4 world4;
    // fields declared that will have the required data to load

    // constructor for the load class
    public GameLoader(String file, Game game, Level1 world1, Level2 world2, Level3 world3, Level4 world4) {
        this.file = file;
        this.game = game;
        this.world1 = world1;
        this.world2 = world2;
        this.world3 = world3;
        this.world4 = world4;
        // assigned the parameter values to the fields declared
    }

    /** this is the method which is called when the Load State button is pressed, GameLevel class used to return
     * the level, this method has a throws exception which will maintain the code flow by letting the programmer
     * know that this type of error may occur during the code flow
     *
     * @return currentLevel - return this field which contains the value of a level class and create the level
     * based on the value of the field
     * @throws IOException - an input or output error may occur */

    public GameLevel loadState() throws IOException {
        FileReader reader = null;
        BufferedReader bReader = null;
        // initialise inside the try brackets

        game.getWorld().start(); // if the world is stopped, start when load button is clicked

        GameLevel currentLevel = null; // initialise outside the if statement to be able to return the value
        if (game.getLevel() == 1) { // if user is in level 1, run this code
            try { // run code and if an error occurs jump to the finally section
                System.out.println("Reading from file..."); // print in console to test whether the load is working

                reader = new FileReader(file); // assign text file to reader field
                bReader = new BufferedReader(reader); // assign the file reader to the bReader field

                String line = bReader.readLine(); // read the first line
                int level = Integer.parseInt(line); // assign the data in the first line in level variable

                line = bReader.readLine(); // read next line
                int time = Integer.parseInt(line); // assign integer from the line in the time variable

                line = bReader.readLine(); // read next line
                int lives = Integer.parseInt(line); // assign integer from the line in the lives variable

                line = bReader.readLine(); // read next line
                int snacks = Integer.parseInt(line); // assign integer from the line in the snacks variable

                line = bReader.readLine(); // read next line
                float x = Float.parseFloat(line); // assign float from the line in the x variable (this is the xPosition of the character)

                line = bReader.readLine(); // read next line
                float y = Float.parseFloat(line); // assign float from the line in the y variable (this is the yPosition of the character)
                Vec2 heroPos = new Vec2(x, y); // heroPos field set to a Vec2 class to contain the x and y position

                world1.importPlayer().setPosition(heroPos); // set the position of the hero using the x and y values

                if (level == 1) { // if level = 1
                    currentLevel = new Level1(game, currentLevel); // create a new level based on the currentLevel value
                } else if (level == 2) { // if level = 2
                    currentLevel = new Level2(game, currentLevel); // create new Level2
                } else if (level == 3) { // if level = 3
                    currentLevel = new Level3(game, currentLevel); // create new Level3
                } else if (level == 4) { // if level = 4
                    currentLevel = new Level4(game, currentLevel); // create new Level4
                }

                while ((line = bReader.readLine()) != null) { // read lines until the next line is empty
                    String[] tokens = line.split(","); // split the values in the current line using the comma with a regualar expression

                    String className = tokens[0]; // the first value is set to be a String which is the class name
                    float xPos = Float.parseFloat(tokens[1]); // the xPosition of the body
                    float yPos = Float.parseFloat(tokens[2]); // the yPosition of the body
                    Vec2 nowPos = new Vec2(xPos, yPos); // Vec2 position to put x and y values in one field

                    if (className.equals("Cookie")) { // if the className is = Cookie
                        Body b = new Cookie(currentLevel); // create the new class based on the which String class it is
                        b.setPosition(nowPos); // set the position of the class
                    } else if (className.equals("Monster")) { // if the className is = Monster
                        Body b = new Monster(currentLevel); // create the new class based on the which String class it is
                        b.setPosition(nowPos); // set new position for the class
                    } else if (className.equals("StaticBody")) { // for all static bodies
                        assert currentLevel != null; // declare an expected boolean condition, in this case its true
                        Body b = new StaticBody(currentLevel); // create new StaticBody
                        b.setPosition(nowPos); // set position to the value of this argument
                    } else if (className.equals("DynamicBody")) {
                        assert currentLevel != null; // value is true
                        Body b = new DynamicBody(currentLevel); // create new DynamicBodies
                        b.setPosition(nowPos);
                    }
                }

                world1.setInterval(time); // set the time of the game based from the text file
                world1.importPlayer().setNumberOfLives(lives); // set the lives of the character based from the lives data in the text file
                world1.importPlayer().setCollectSnack(snacks); // set snacks number based from text file

                return currentLevel; // return the level from if statements
            } finally { // run this code whether there is an error or not
                if (bReader == null) { // if the  is not null then close the file
                    reader.close();
                }
            }
        }
        else if (game.getLevel() == 2) { // if user is in level 2
            try {
                System.out.println("Reading from file..."); // print test in console to check functionality

                reader = new FileReader(file); // assign text file to the reader field
                bReader = new BufferedReader(reader); // assign file reader to the text file

                String line = bReader.readLine(); // read next line
                int level = Integer.parseInt(line); // assign read data to level variable

                line = bReader.readLine(); // read next line
                int time = Integer.parseInt(line); // assign read data to time variable

                line = bReader.readLine(); // read next line
                int lives = Integer.parseInt(line); // assign read data to lives variable

                line = bReader.readLine(); // read next line
                int snacks = Integer.parseInt(line); // assign read data to snacks variable

                line = bReader.readLine(); // read next line
                float x = Float.parseFloat(line); // assign read data to x variable

                line = bReader.readLine(); // read next line
                float y = Float.parseFloat(line); // assign read data to y variable
                Vec2 heroPos = new Vec2(x, y); // place x and y variable into one field

                if (level == 1) {
                    currentLevel = new Level1(this.game, currentLevel); // create this level if level = 1
                } else if (level == 2) {
                    currentLevel = new Level2(this.game, currentLevel); // create this level if level = 2
                } else if (level == 3) {
                    currentLevel = new Level3(this.game, currentLevel); // create this level if level = 3
                } else if (level == 4) {
                    currentLevel = new Level4(this.game, currentLevel); // create this level if level = 4
                }

                while ((line = bReader.readLine()) != null) { // read the next lines
                    String[] tokens = line.split(","); // split the line using the split

                    String className = tokens[0]; // assign the first data in the line to this token
                    float xPos = Float.parseFloat(tokens[1]); // the second data is the x position of the body
                    float yPos = Float.parseFloat(tokens[2]); // the third data is the y position of the body
                    Vec2 nowPos = new Vec2(xPos, yPos); // place x and y in one field

                    if (className.equals("Hero")) { // if className is equal to Hero
                        Body b = new Hero(currentLevel);
                        b.setPosition(heroPos); // create this class and set its position based on the argument
                    } else if (className.equals("Candy")) { // if className is equal to Candy
                        Body b = new Candy(currentLevel);
                        b.setPosition(nowPos); // create this class and set its position based on the argument
                    } else if (className.equals("Monster2")) { // if className is equal to Monster2
                        Body b = new Monster2(currentLevel);
                        b.setPosition(nowPos); // create this class and set its position based on the argument
                    } else if (className.equals("StaticBody")) { // if className is equal to StaticBody
                        assert currentLevel != null;
                        Body b = new StaticBody(currentLevel);
                        b.setPosition(nowPos); // create this class and set its position based on the argument
                    } else if (className.equals("DynamicBody")) { // if className is equal to DynamicBody
                        assert currentLevel != null;
                        Body b = new DynamicBody(currentLevel);
                        b.setPosition(nowPos); // create this class and set its position based on the argument
                    }
                }

                world2.setInterval(time); // set the world time based on time data from text file
                world2.importPlayer().setNumberOfLives(lives); // set the character lives based from the text file
                world2.importPlayer().setCollectSnack(snacks); // set the snacks based from the text file
                world2.importPlayer().setPosition(heroPos); // set the character position based from the text file

                return currentLevel; // return value of currentLevel
            } finally {
                if (bReader == null) { // if there is no more to be read in the file then...
                    reader.close(); // close the file
                }
            }
        }
        else if (game.getLevel() == 3) { // if user in level 3
            try {
                System.out.println("Reading from file...");

                reader = new FileReader(file);
                bReader = new BufferedReader(reader); // assign value to fields

                String line = bReader.readLine(); // read next line
                int level = Integer.parseInt(line); // assign read data to level variable

                line = bReader.readLine(); // read next line
                int time = Integer.parseInt(line); // assign read data to time variable

                line = bReader.readLine(); // read next line
                int lives = Integer.parseInt(line); // assign read data to lives variable

                line = bReader.readLine(); // read next line
                int snacks = Integer.parseInt(line); // assign read data to snacks variable

                line = bReader.readLine(); // read next line
                float x = Float.parseFloat(line); // assign read data to x variable

                line = bReader.readLine(); // read next line
                float y = Float.parseFloat(line); // assign read data to y variable
                Vec2 heroPos = new Vec2(x, y); // place value of x and y into one field

                if (level == 1) {
                    currentLevel = new Level1(this.game, currentLevel); // create world if level is 1
                } else if (level == 2) {
                    currentLevel = new Level2(this.game, currentLevel); // create world if level is 2
                } else if (level == 3) {
                    currentLevel = new Level3(this.game, currentLevel); // create world if level is 3
                } else if (level == 4) {
                    currentLevel = new Level4(this.game, currentLevel); // create world if level is 4
                }

                while ((line = bReader.readLine()) != null) { // read the rest of the lines
                    String[] tokens = line.split(","); // split the data from the commas using regex

                    String className = tokens[0]; // the first data from the splitted line is the className
                    float xPos = Float.parseFloat(tokens[1]); // the second data from the splitted line is the xPosition
                    float yPos = Float.parseFloat(tokens[2]); // the third data from the splitted line is the yPosition
                    Vec2 nowPos = new Vec2(xPos, yPos); // place xPos and yPos into nowPos field

                    if (className.equals("Hero")) {
                        Body b = new Hero(currentLevel);
                        b.setPosition(heroPos); // create Hero class and set its position based on the argument
                    } else if (className.equals("Choco")) {
                        Body b = new Choco(currentLevel);
                        b.setPosition(nowPos); // create Choco class and set its position based on the argument
                    } else if (className.equals("Monster3")) {
                        Body b = new Monster3(currentLevel);
                        b.setPosition(nowPos); // create Monster3 class and set its position based on the argument
                    } else if (className.equals("StaticBody")) {
                        assert currentLevel != null;
                        Body b = new StaticBody(currentLevel);
                        b.setPosition(nowPos); // create StaticBody class and set its position based on the argument
                    } else if (className.equals("DynamicBody")) {
                        assert currentLevel != null;
                        Body b = new DynamicBody(currentLevel);
                        b.setPosition(nowPos); // create DynamicBody class and set its position based on the argument
                    }
                }

                world3.setInterval(time); // set the world time based on time data from text file
                world3.importPlayer().setNumberOfLives(lives); // set the lives based on time data from text file
                world3.importPlayer().setCollectSnack(snacks); // set the snacks based on time data from text file
                world3.importPlayer().setPosition(heroPos); // set the hero position based on time data from text file

                return currentLevel; // return value of this field
            } finally {
                if (bReader == null) {
                    reader.close();
                }
            }
        }
        else if (game.getLevel() == 4) {
            try {
                System.out.println("Reading from file...");

                reader = new FileReader(file);
                bReader = new BufferedReader(reader); // initialise text file into these fields

                String line = bReader.readLine(); // read next line
                int level = Integer.parseInt(line); // assign read data to level variable

                line = bReader.readLine(); // read next line
                int time = Integer.parseInt(line); // assign read data to time variable

                line = bReader.readLine(); // read next line
                int lives = Integer.parseInt(line); // assign read data to lives variable

                line = bReader.readLine(); // read next line
                int snacks = Integer.parseInt(line); // assign read data to snacks variable

                line = bReader.readLine(); // read next line
                float x = Float.parseFloat(line); // assign read data to x variable

                line = bReader.readLine(); // read next line
                float y = Float.parseFloat(line); // assign read data to y variable
                Vec2 heroPos = new Vec2(x, y);
                System.out.println(heroPos);

                if (level == 1) {
                    currentLevel = new Level1(this.game, currentLevel); // create this world if level = 1
                } else if (level == 2) {
                    currentLevel = new Level2(this.game, currentLevel); // create this world if level = 1
                } else if (level == 3) {
                    currentLevel = new Level3(this.game, currentLevel); // create this world if level = 1
                } else if (level == 4) {
                    currentLevel = new Level4(this.game, currentLevel); // create this world if level = 1
                }

                while ((line = bReader.readLine()) != null) { // read the rest of the lines
                    String[] tokens = line.split(","); // splt lines using regex

                    String className = tokens[0]; // first data is a String which is the className
                    float xPos = Float.parseFloat(tokens[1]); // the second data from the line is the xPosition
                    float yPos = Float.parseFloat(tokens[2]);// the third data from the line is the yPosition
                    Vec2 nowPos = new Vec2(xPos, yPos);

                    if (className.equals("Hero")) {
                        Body b = new Hero(currentLevel);
                        b.setPosition(heroPos); // create Hero class and set its position based on the argument
                    } else if (className.equals("Cupcake")) {
                        Body b = new Cupcake(currentLevel);
                        b.setPosition(nowPos); // create Cupcake class and set its position based on the argument
                    } else if (className.equals("Monster4")) {
                        Body b = new Monster4(currentLevel);
                        b.setPosition(nowPos); // create Monster4 class and set its position based on the argument
                    } else if (className.equals("StaticBody")) {
                        assert currentLevel != null;
                        Body b = new StaticBody(currentLevel);
                        b.setPosition(nowPos); // create StaticBody class and set its position based on the argument
                    } else if (className.equals("DynamicBody")) {
                        assert currentLevel != null;
                        Body b = new DynamicBody(currentLevel);
                        b.setPosition(nowPos); // create DynamicBody class and set its position based on the argument
                    }
                }

                world4.setInterval(time); // set world time based text file data
                world4.importPlayer().setNumberOfLives(lives); // set lives based text file data
                world4.importPlayer().setCollectSnack(snacks); // set snacks based text file data
                world4.importPlayer().setPosition(heroPos); // set hero position based text file data

                return currentLevel; // return value of currentLevel field
            } finally {
                if (bReader == null) {
                    reader.close();
                }
            }
        }

        return currentLevel; // return null if none of the if statements are true
    }
}
