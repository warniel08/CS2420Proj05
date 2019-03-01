/*
 *   Warner Nielsen
 *   2/19/19
 *   Project 05
 *   CS 2420
 *   Garth Sorenson
 * */
package NielWarnProj05;

import java.util.Scanner;

/*
*   This driver class asks a user for the name of a text file to be used
*   in this project. The name given should be the name of the maze file
*   that is saved in the package folder. Only the name part is necessary,
*   the .txt extension will be added automatically by the driver program.
*   The program will traverse the maze from beginning to end if the maze
*   is solvable.
* */
public class NielWarnProj05 {
    public static void main(String[] args) {
        /*
        *   The program will automatically collect the starting and ending
        *   coordinates from the maze file.
        * */
        String startCoords, endCoords, userInput;
        System.out.println("Welcome to the A-Mazing Maze Solver Program!!!");

        // New input scanner to collect user input
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the name of the file you would like to run without the extension: ");
        userInput = input.next();
        String fileName = "src/NielWarnProj05/" + userInput + ".txt";

        // The name of the maze file is used for constructing a new maze
        Maze m = new Maze(fileName);
        Creature c = new Creature(m.getStartX(), m.getStartY()); // Creature uses coordinates from maze constructor

        // Starting and ending coords are saved to return to the user
        startCoords = "(" + m.getStartX() + ", " + m.getStartY() + ")";
        endCoords = "(" + m.getEndX() + ", " + m.getEndY() + ")";

        // Print starting and ending coords for user
        System.out.println("Starting coords are: " + startCoords);
        System.out.println("Ending coords are: " + endCoords);

        // boolen success reference is defined outside of IF statement
        boolean success;

        /*
        *   This conditional statement checks to make see if the creature's
        *   current coordinates are at the end of the maze, if so, the maze
        *   returns success = true. If the creatures coordinates are not at
        *   the end of the maze it will run through each directional method.
        *   If the first condition fails, the it starts goNorth(), if that
        *   fails, it starts goWest(), if that fails it starts goSouth(), if
        *   that fails it starts goEast(). This ensures the the maze will get
        *   started and run on its own.
        * */
        if (m.isEnd(c.getCurrCreatureX(), c.getCurrCreatureY())) {
            success = true;
        } else {
            success = goNorth(m, c);
            if (!success) {
                success = goWest(m, c);
                if (!success) {
                    success = goSouth(m, c);
                    if(!success) {
                        goEast(m, c);
                    }
                }
            }
        }

        /*
        *   This prints the current position of the creature which should
        *   be the same coordinates as the ending coordinates of the maze.
        * */
        c.getCurrPosition();

        /*
        *   This method displays the maze and the path that the creature
        *   has taken to traverse the maze.
        * */
        m.displayMaze();

    }

    /*
    *   This goNorth() method calls the isValid() method to make sure that
    *   a move to the north of the current position is clear, is part of the
    *   maze, and has not been visited. If those conditions pass then it will
    *   move the creature north and mark the previous spot with a P. If the creature
    *   is at the ending coordinates then success is true and the creature will
    *   stop moving. Otherwise it will continue to run the goNorth() method
    *   recursively until the first conditional is false. If it is false then it
    *   will run goWest() recursively until its first condition is false. Otherwise
    *   runs the goEast() condition. If all of those conditions fail then the creature
    *   will move one space South and mark the previous spot with a V to indicate
    *   that it has visited that square.
    * */
    public static boolean goNorth(Maze maze, Creature creature) {
        boolean success;
        if (maze.isValidNorth(creature.getCurrCreatureX(), creature.getCurrCreatureY())) {
            creature.moveNorth();
            maze.markPath(creature.getCurrCreatureX(), creature.getCurrCreatureY());
            if (maze.isEnd(creature.getCurrCreatureX(), creature.getCurrCreatureY())) {
                success = true;
            } else {
                success = goNorth(maze, creature);
                if (!success) {
                    success = goWest(maze, creature);
                    if (!success) {
                        success = goEast(maze, creature);
                        if (!success) {
                            maze.markVisited(creature.getCurrCreatureX(), creature.getCurrCreatureY());
                            creature.moveSouth();
                        }
                    }
                }
            }
        } else {
            success = false;
        }
        return success;
    }

    /*
     *   This goWest() method calls the isValid() method to make sure that
     *   a move to the west of the current position is clear, is part of the
     *   maze, and has not been visited. If those conditions pass then it will
     *   move the creature west and mark the previous spot with a P. If the creature
     *   is at the ending coordinates then success is true and the creature will
     *   stop moving. Otherwise it will continue to run the goWest() method
     *   recursively until the first conditional is false. If it is false then it
     *   will run goSouth() recursively until its first condition is false. Otherwise
     *   runs the goNorth() condition. If all of those conditions fail then the creature
     *   will move one space East and mark the previous spot with a V to indicate
     *   that it has visited that square.
     * */
    public static boolean goWest(Maze maze, Creature creature) {
        boolean success;
        if (maze.isValidWest(creature.getCurrCreatureX(), creature.getCurrCreatureY())) {
            creature.moveWest();
            maze.markPath(creature.getCurrCreatureX(), creature.getCurrCreatureY());
            if (maze.isEnd(creature.getCurrCreatureX(), creature.getCurrCreatureY())) {
                success = true;
            } else {
                success = goWest(maze, creature);
                if (!success) {
                    success = goSouth(maze, creature);
                    if (!success) {
                        success = goNorth(maze, creature);
                        if (!success) {
                            maze.markVisited(creature.getCurrCreatureX(), creature.getCurrCreatureY());
                            creature.moveEast();
                        }
                    }
                }
            }
        } else {
            success = false;
        }
        return success;
    }

    /*
     *   This goEast() method calls the isValid() method to make sure that
     *   a move to the east of the current position is clear, is part of the
     *   maze, and has not been visited. If those conditions pass then it will
     *   move the creature east and mark the previous spot with a P. If the creature
     *   is at the ending coordinates then success is true and the creature will
     *   stop moving. Otherwise it will continue to run the goEast() method
     *   recursively until the first conditional is false. If it is false then it
     *   will run goSouth() recursively until its first condition is false. Otherwise
     *   runs the goNorth() condition. If all of those conditions fail then the creature
     *   will move one space West and mark the previous spot with a V to indicate
     *   that it has visited that square.
     * */
    public static boolean goEast(Maze maze, Creature creature) {
        boolean success;
        if (maze.isValidEast(creature.getCurrCreatureX(), creature.getCurrCreatureY())) {
            creature.moveEast();
            maze.markPath(creature.getCurrCreatureX(), creature.getCurrCreatureY());
            if (maze.isEnd(creature.getCurrCreatureX(), creature.getCurrCreatureY())) {
                success = true;
            } else {
                success = goEast(maze, creature);
                if (!success) {
                    success = goSouth(maze, creature);
                    if (!success) {
                        success = goNorth(maze, creature);
                        if (!success) {
                            maze.markVisited(creature.getCurrCreatureX(), creature.getCurrCreatureY());
                            creature.moveWest();
                        }
                    }
                }
            }
        } else {
            success = false;
        }
        return success;
    }

    /*
     *   This goSouth() method calls the isValid() method to make sure that
     *   a move to the south of the current position is clear, is part of the
     *   maze, and has not been visited. If those conditions pass then it will
     *   move the creature south and mark the previous spot with a P. If the creature
     *   is at the ending coordinates then success is true and the creature will
     *   stop moving. Otherwise it will continue to run the goSouth() method
     *   recursively until the first conditional is false. If it is false then it
     *   will run goWest() recursively until its first condition is false. Otherwise
     *   runs the goEast() condition. If all of those conditions fail then the creature
     *   will move one space North and mark the previous spot with a V to indicate
     *   that it has visited that square.
     * */
    public static boolean goSouth(Maze maze, Creature creature) {
        boolean success;
        if (maze.isValidSouth(creature.getCurrCreatureX(), creature.getCurrCreatureY())) {
            creature.moveSouth();
            maze.markPath(creature.getCurrCreatureX(), creature.getCurrCreatureY());
            if (maze.isEnd(creature.getCurrCreatureX(), creature.getCurrCreatureY())) {
                success = true;
            } else {
                success = goSouth(maze, creature);
                if (!success) {
                    success = goWest(maze, creature);
                    if (!success) {
                        success = goEast(maze, creature);
                        if (!success) {
                            maze.markVisited(creature.getCurrCreatureX(), creature.getCurrCreatureY());
                            creature.moveNorth();
                        }
                    }
                }
            }
        } else {
            success = false;
        }
        return success;
    }
}