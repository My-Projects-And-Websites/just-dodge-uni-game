package levels; /** @author Jimm, Inciong, jimminciong163@gmail.com
 * @version 13.0.1
 * @since 13 */

import city.cs.engine.SoundClip;
import collision.hero.Hero;
import levels.level.*;
import levels.trackers.Tracker;
import levels.trackers.Tracker2;
import levels.trackers.Tracker3;
import levels.trackers.Tracker4;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/* the JFrame extension enables the user to create windows and implement other
* objects such as panels or option panes */
public class Game extends JFrame {
    /** these fields are all the levels of the game where the world number assigned
     * corresponds to the level of the game */
    private Level1 world;
    private Level2 world2;
    private Level3 world3;
    private Level4 world4;
    private SoundClip gameMusic;
    private SoundClip lavaSound;
    private SoundClip iceSound;

    private MyView userView; // this instantiation controls the rendering of images and texts in the game
    // this field holds the number of the current level
    private int level;

    // this field implements the use of buttons such as pause, play, etc.
    ControlPanel buttons;

    // the window is created here with the title provided as the argument
    final JFrame window = new JFrame("Just Dodge!");

    public Game() {
        // make the first world
        world = new Level1(this, world); // this will start up the game by implementing the GameWorld class int he window
        world2 = new Level2(this, world2); // start the next world
        world3 = new Level3(this, world3); // initialise world3 field with levels.level.Level3 class
        world4 = new Level4(this, world4); // initialise world4 field with levels.level.Level4 class

        // this sets the size of the window and the current world that will be executed
        userView = new MyView(world, world.importPlayer(), world, this, 1450, 700);

        // set level to 1
        level = 1;

        // this is the background music for the whole game
        // the try catch method means try (run) the code inside the try brackets and if there is an error, run code in catch
        try {
            gameMusic = new SoundClip("data/music/bg_music.wav");
            gameMusic.play(); // plays the music specified in the file
            gameMusic.setVolume(0.75); // set the volume
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // if an error occurs, the error will be printed on the console
            System.out.println(e);
        }

        buttons = new ControlPanel(this, world, world2, world3, world4);
        window.add(buttons.getMainPanel(), BorderLayout.SOUTH); // add the JPanel where the buttons can be pressed

        // sets the window properties
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationByPlatform(true); // this will make the window appear in the center when program is executed
        window.add(userView); // add the view object
        window.setResizable(false); // the window cannot be resizable
        window.pack(); // sizes the frame so all the contents are in their set sizes
        window.setVisible(true); // makes the frame appear on the screen
        window.setLocationRelativeTo(null);

        window.requestFocus(); // take for the focus to be set in the window
        window.addKeyListener(new Control(world.importPlayer())); // set controls
        world.addStepListener(new Tracker(userView, this, world.importMonster(), world.importPlayer())); // add the tracker classes

        // this will give focus to the window to avoid having the user to click ont he window first
        window.addMouseListener(new Focus(window));

        // let's go!
        world.start();
    }

    // this is the main class which becomes executed, the levels.Game class will be executed followed by the code inside its constructor
    public static void main(String[] args) {
        // run the game method
        new Game();
    }

    // return the world depending which level the user is currently in
    public GameLevel getWorld() {
        if (level == 1)
            return world;
        else if (level == 2)
            return world2;
        else if (level == 3)
            return world3;
        else if (level == 4)
            return world4;
        return null; // if level is less than 1 or greater than 4 then return null
    }

    /** this method integrates the levels when this method is called, this method
     * is called when the user has collected enough snacks in the current level
     *
     * a sound loops throughout this whole level and proper tracker, key listeners and character data
     * are set to build connection between multiple levels
     * after stopping the previous world, a new world will be created and it will be started which means the next level
     * will be loaded once this method has been run again
     *
     * @param lvl - to get access to all methods and fields from this class*/
    public void nextLevel(GameLevel lvl) {
        if (level == 1) { // because first level is 1, this will run as soon as this method is called
            Hero counter = world.importPlayer(); // return the value of the importPlayer method found in the world object
            world.stop(); // stop the current world

            lvl = world; // assign world to lvl parameter

            level = lvl.getLevelNumber();
            world = (Level1)lvl; // cast the parameter to this class

            try {
                lavaSound = new SoundClip("data/music/FastLava.wav");
                lavaSound.loop(); // when the sound clip finishes, play it again
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }

            level = level + 1; // increment one to the level field

            window.addKeyListener(new Control(world2.importPlayer())); // assign controls to the hero character in level 2

            // set the current number of snacks to the amount of snacks collected in the previous level
            world2.importPlayer().setCollectSnack(counter.getCollectSnack());
            // set the amount of lives to the amount of lives left in the previous level
            world2.importPlayer().setNumberOfLives(counter.getNumberOfLives());
            userView.setHero(world2.importPlayer()); // this will continue the data displayed from the previous level to the rest of the levels

            // apply the second tracker
            world2.addStepListener(new Tracker2(userView, this, world2.importMonster(), world2.importPlayer()));
            // set the world to the second world
            userView.setWorld(world2);
            world2.start(); // after stopping the previous world, start the second one. start!!
        }

        /* after incrementing from the first conditional statement, the level will now be 2 so when this
        * method is called once again, this section of code will run */
        else if (level == 2) {
            Hero counter2 = world2.importPlayer(); // get the value of importPlayer from world2

            world2.stop(); // stop the current world

            lvl = world2;

            level = lvl.getLevelNumber();
            world2 = (Level2)lvl; // cast the parameter to this class

            getLavaSound().stop();

            try {
                iceSound = new SoundClip("data/music/jingle.wav");
                iceSound.loop();
                iceSound.setVolume(0.25); // set the volume level
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e); // print error type in console
            }

            level = level + 1; // increment level by 1 when called again to move to the next level

            // add controls to the third hero character in level 3
            window.addKeyListener(new Control(world3.importPlayer()));

            // resume the data from the previous level when collecting snacks in the current level
            world3.importPlayer().setCollectSnack(counter2.getCollectSnack());
            world3.importPlayer().setNumberOfLives(counter2.getNumberOfLives());
            userView.setHero(world3.importPlayer());

            // apply the third tracker
            world3.addStepListener(new Tracker3(userView, this, world3.importMonster1(), world3.importMonster2(), world3.importPlayer()));
            userView.setWorld(world3); // set the world to the next level
            world3.start(); // start!
        }

        else if (level == 3) {
            Hero counter3 = world3.importPlayer(); // get the value of importPlayer from world3

            world3.stop(); // stop world3

            lvl = world3;
            level = lvl.getLevelNumber();
            world3 = (Level3)lvl; // cast the parameter to this class

            getIceSound().stop();
            getLavaSound().stop();

            level = level + 1; // increment level by 1

            window.addKeyListener(new Control(world4.importPlayer())); // add controls to level 4 hero character

            // continue the data from the previous level when collecting snacks in the current level
            world4.importPlayer().setCollectSnack(counter3.getCollectSnack());
            world4.importPlayer().setNumberOfLives(counter3.getNumberOfLives());
            userView.setHero(world4.importPlayer());

            // apply the fourth tracker
            world4.addStepListener(new Tracker4(userView, this, world4.importPlayer(), world4.importMonster(), world4.importMonster2(), world4.importMonster3(), world4.importMonster4(), world4.importMonster5()));
            userView.setWorld(world4); // set the world to the next level to change the images and the platform positioning
            world4.start(); // start the last level!!
        }

        else if (level == 4) {
            world4.stop();

            lvl = world4;

            level = lvl.getLevelNumber();
            world4 = (Level4)lvl; // cast the parameter to this class
        }
    }

    // return the current level
    public int getLevel() {
        return level;
    }

    // this method is assigned to the pause button, pause the current world depending on the level
    public void pause() {
        if (getLevel() == 1) {
            world.stop();
        }
        else if (getLevel() == 2) {
            world2.stop();
        }
        else if (getLevel() == 3) {
            world3.stop();
        }
        else if (getLevel() == 4) {
            world4.stop();
        }
    }

    // this method is assigned to the play button, play the current world (if paused) depending on the level
    public void play() {
        if (getLevel() == 1) {
            world.start();
        }
        else if (getLevel() == 2) {
            world2.start();
        }
        else if (getLevel() == 3) {
            world3.start();
        }
        else if (getLevel() == 4) {
            world4.start();
        }
    }


    // exit the game when quit button is pressed
    public void quit() {
        System.exit(0);
    }

    public SoundClip getGameMusic() {
        return gameMusic;
    }

    public SoundClip getIceSound() {
        return iceSound;
    }

    public SoundClip getLavaSound() {
        return lavaSound;
    }
}

