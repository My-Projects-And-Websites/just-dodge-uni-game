package levels;// these libraries execute code using objects and events
import collision.hero.Hero;
import levels.level.Level1;
import levels.level.Level2;
import levels.level.Level3;
import levels.level.Level4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlPanel {
    // these fields are the buttons and the panel created in the form
    private JPanel mainPanel;
    private JButton btnPause;
    private JButton btnRestart;
    private JButton btnExit;
    private JButton btnPlay;
    private JButton btnSave;
    private JButton btnLoad;

    /** this is the GUI buttons which controls the motion of the game according to
     * the buttons clicked (play, pause, save, load, restart, quit)
     *
     * every button has different features
     * @param game - this parameter will pass the value of "game" object from where this class is instantiated
     * @param world1 - parameter for first level
     * @param world2 - parameter to gain access to methods and fields in level 2
     * @param world3 - parameter for third level
     * @param world4 - parameter for last level*/

    public ControlPanel(Game game, Level1 world1, Level2 world2, Level3 world3, Level4 world4) {

        btnPause.addActionListener(new ActionListener() {
            // the only actions these GUI buttons can receive are mouse motion
            public void actionPerformed(ActionEvent e) {
                game.pause(); // pause the game
            }
        });

        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.play(); // continue the game
            }
        });

        btnRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Game(); // rerun the levels.Game class and restart
            }
        });


        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.quit(); // exit the game and close the window
            }
        });

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // declare GameSaver class and apply parameters
                GameSaver ss = new GameSaver("data/saveFile/player1.txt", game, world1, world2, world3, world4);
                try {
                    ss.saveState(); // call the method
                } catch (IOException ex) { // catch the error and print error type in console
                    System.out.println(ex);
                }
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // declare the load class and apply the parameters
                GameLoader ls = new GameLoader("data/saveFile/player1.txt", game, world1, world2, world3, world4);
                try {
                    GameLevel loadLevel = ls.loadState(); // assign the return value of the method
                    game.nextLevel(loadLevel); // call this method to switch levels
                } catch (IOException ex) {
                    System.out.println(ex); // print the error in the console
                }
            }
        });
    }

    // return the value of this panel to be placed on the window
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
