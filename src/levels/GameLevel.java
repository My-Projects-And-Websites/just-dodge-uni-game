package levels;

// this library allows access to all of the features of the city engine library
import city.cs.engine.*;
import collision.hero.Hero;
import levels.Game;
import org.jbox2d.common.Vec2;

// these are the timer classes required to make the timer
import java.util.Timer;
import java.util.TimerTask;

/* this is an abstract class where all the levels are inherited from
* because it's abstract it cannot be instantiated but it is used mainly for
* inheritance of the subclasses */

public abstract class GameLevel extends World {
    // the timer is set to 150 seconds
    int interval = 150;
    Timer myTimer = new Timer();

    int levelNumber;

    // method will be applied on the super method in every level
    public void populate(Game game)  {
        // this controls the countdown
        myTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                // this decrements the interval with a delay of 1000 millisecond
                getInterval();
            }
        }, 1000, 1000);
    }

    // this method returns the value of the interval variable
    public int getInterval() {
        return --interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public abstract int getLevelNumber();

    public abstract int getSnack();

    public abstract int getLives();

    public abstract Hero getHero();
}
