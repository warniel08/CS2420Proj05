/*
 *   Warner Nielsen
 *   2/19/19
 *   Project 05
 *   CS 2420
 *   Garth Sorenson
 * */
package NielWarnProj05;

/*
 *   The creature class holds data for the creature that will be
 *   used on the maze. It holds values for its current x and y
 *   coordinate values. The values get updated as the creature
 *   moves around the board. It is only responsible for moving
 *   itself one square and updating its position. It also will
 *   report its current position.
 * */
public class Creature {
    private int currX_;
    private int currY_;

    // constructor to set creature to starting x and y coordinates
    public Creature(int startX, int startY) {
        currX_ = startX;
        currY_ = startY;
    }

    // get method for creature's current x position
    public int getCurrCreatureX() { return currX_; }

    // get method for creature's current y position
    public int getCurrCreatureY() { return currY_; }

    // method to move creature north one square in maze
    public void moveNorth() { currY_ -= 1; }

    // method to move creature west one square in maze
    public void moveWest() { currX_ -= 1; }

    // method to move creature east one square in maze
    public void moveEast() { currX_ += 1; }

    // method to move creature south one square in maze
    public void moveSouth() { currY_ += 1; }

    // prints current position of creature
    public void getCurrPosition() {
        System.out.println("Creature's current position: (" + currX_ + ", " + currY_ + ")");
    }
}
