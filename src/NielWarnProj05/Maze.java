/*
 *   Warner Nielsen
 *   2/19/19
 *   Project 05
 *   CS 2420
 *   Garth Sorenson
 * */
package NielWarnProj05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
*   The Maze class stores data that refers to the size of the row, the size
*   of the columns, and the starting and ending x and y positions. There are
*   chars that represent the walls, paths, visited spaces, and clear spaces
*   as found throughout the maze.
* */
public class Maze {
    private int rowSize_, colSize_, startX_, startY_, endX_, endY_;
    private char wall_ = 'x';
    private char path_ = 'P';
    private char visited_ = 'V';
    private char clear_ = ' ';
    private char[][] maze_;
    private char[] stringToChar_; // extra char array to store arrays that are colSize_ long

    /*
     *   This method creates a new maze char array using several other methods
     *   It takes in a filename provided by the user then creates the maze
     *   from the text file. It also populates the other maze data members
     *   to give the starting and ending X,Y coordinates as provided by the file.
     * */
    public Maze(String userFilename) {
        getMazeInfo(userFilename); // maze is initialized using this method
    }

    // starting x coordinate is returned
    public int getStartX() { return startX_; }

    // starting y coordinate is returned
    public int getStartY() { return startY_; }

    // ending x coordinate is returned
    public int getEndX() { return endX_; }

    // ending y coordinate is returned
    public int getEndY() { return endY_; }

    /*
    *   The getMazeInfo() method takes in a filename string
    *   given by the user and returns a new maze and all of
    *   the maze data fields initialized by the values from
    *   the maze .txt file.
    * */
    private void getMazeInfo(String filename) {
        try {
            Scanner fileInput = new Scanner(new File(filename));

            /*
            *   While the input file has a next line of data
            *   it will store the integer values in the appropriate
            *   integer data fields. THen it will create two char arrays
            *   populated with the newly obtained data from the data fields.
            *   Then it stores each next line of information from the input
            *   file into a one dimensional char array. That line of the
            *   char array is copied to the maze array char by char.
            * */
            while (fileInput.hasNextLine()) {
                colSize_= fileInput.nextInt();
                rowSize_ = fileInput.nextInt();
                startY_ = fileInput.nextInt();
                startX_ = fileInput.nextInt();
                endY_ = fileInput.nextInt();
                endX_ = fileInput.nextInt();
                fileInput.nextLine();
                maze_ = new char[rowSize_][colSize_];
                stringToChar_ = new char[colSize_];

                for (int i = 0; i < rowSize_; i++) {
                    stringToChar_ = fileInput.nextLine().toCharArray();
                    maze_[i] = stringToChar_;
                }
            }
            fileInput.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /*
    *   This method displays the maze using two for loops to iterate
    *   through each row then each column to print out all of the
    *   data found in the 2d maze array.
    * */
    public void displayMaze() {
        // the creature is shown by the char o found in the starting coords
        maze_[startY_][startX_] = 'o';
        for (int i = 0; i < maze_.length; i++) {
            for (int j = 0; j < maze_[i].length; j++) {
                System.out.print(maze_[i][j]);
            }
            System.out.println();
        }
    }

    /*
    *   The isValidNorth() method takes in x and y coordinates taken
    *   from the creatures current coords. It checks if the next coords north are
    *   in the maze and then returns true if that next block north is clear and not
    *   visited.
    * */
    public boolean isValidNorth(int x, int y) {
        if (isInMaze(x, y-1)) {
            return maze_[y-1][x] == clear_ && maze_[y-1][x] != visited_;
        }
        return false;
    }

    /*
     *   The isValidWest() method takes in x and y coordinates taken
     *   from the creatures current coords. It checks if the next coords west are
     *   in the maze and then returns true if that next block west is clear and not
     *   visited.
     * */
    public boolean isValidWest(int x, int y) {
        if (isInMaze(x-1, y)) {
            return maze_[y][x-1] == clear_ && maze_[y][x-1] != visited_;
        }
        return false;
    }

    /*
     *   The isValidEast() method takes in x and y coordinates taken
     *   from the creatures current coords. It checks if the next coords east are
     *   in the maze and then returns true if that next block east is clear and not
     *   visited.
     * */
    public boolean isValidEast(int x, int y) {
        if (isInMaze(x+1, y)) {
            return maze_[y][x+1] == clear_ && maze_[y][x+1] != visited_;
        }
        return false;
    }

    /*
     *   The isValidSouth() method takes in x and y coordinates taken
     *   from the creatures current coords. It checks if the next coords south are
     *   in the maze and then returns true if that next block south is clear and not
     *   visited.
     * */
    public boolean isValidSouth(int x, int y) {
        if (isInMaze(x, y+1)) {
            return maze_[y+1][x] == clear_ && maze_[y+1][x] != visited_;
        }
        return false;
    }

    // Returns true if the x and y values taken as parameters are within the maze coords
    public boolean isInMaze(int x, int y) { return ((y >= 0 && y < rowSize_) && (x >= 0 && x < colSize_)); }

    // Returns true if the x and y coords equal the ending x and y coords
    public boolean isEnd(int x, int y) { return (endX_ == x && endY_ == y); }

    // Will mark the x and y coords given by the creatures current x and y and mark the block with a 'P'
    public void markPath(int x, int y) { maze_[y][x] = path_; }

    // Will mark the appropriate x and y coords given by the creature with a 'V'
    // if that path is visited and not to be visited again
    public void markVisited(int x, int y) { maze_[y][x] = visited_; }
}
