package model;


import java.util.Random;

public class Sudoku {


    private int[][] matrix;
    private static final int EMPTY = 0;
    private static final int SIZE = 9;

    public Sudoku() {
        this.matrix = new int[SIZE][SIZE];
    }

    /**
     * Returns the value in the specified position
     * @param x row
     * @param y column
     * @return the value
     */
    public int getBoxValue(int x, int y) {
        return matrix[x][y];
    }

    /**
     * Sets the value at the specified position
     * to the the specified value
     * @param x row
     * @param y column
     * @param value the desired value
     */
    public void setBoxValue(int x, int y, int value) {
        matrix[x][y] = value;
    }

    /**
     * Prints the Sudoku in a readable layout
     * for debugging
     */
    public void printMatrix() {
        for (int i = 0; i < SIZE; i++) {
            System.out.println();
            System.out.print(i + 1 + ":");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + matrix[i][j]);
            }
        }
        System.out.println();
    }

    /**
     * Fills the Sudoku with random numbers
     * at random position.
     * Used to emulate user input
     */
    public void fillRandomly() {
        this.matrix = new int[9][9];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int positionX = random.nextInt(9);
            int positionY = random.nextInt(9);
            int element = random.nextInt(9) + 1;
            this.matrix[positionX][positionY] = element;
        }
    }

    /**
     * Checks if the specified number is in the specified row
     * @param row
     * @param number
     * @return false if the specified number is NOT in the specified row
     */
    private boolean isInRow(int row, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (matrix[row][i] == number)
                return true;
        }
        return false;
    }

    /**
     * Checks if the specified number is in the specified column
     * @param col
     * @param number
     * @return false if the specified number is NOT in the specified column
     */
    private boolean isInCol(int col, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (matrix[i][col] == number)
                return true;
        }
        return false;
    }

    private boolean isInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (matrix[i][j] == number)
                    return true;
        return false;
    }

    private boolean isOk(int row, int col, int number) {
        return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
    }

    public boolean solve() {
        if (!controlUserInput()) {
            return false;
        }
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (matrix[row][col] == EMPTY) {
                    for (int number = 1; number <= 9; number++) {
                        if (isOk(row, col, number)) {

                            matrix[row][col] = number;

                            if (solve()) {
                                return true;
                            } else {
                                matrix[row][col] = EMPTY;
                            }

                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean controlUserInput() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.matrix[i][j] != 0) {
                    int value = this.matrix[i][j];
                    this.matrix[i][j] = 0;
                    if (!isOk(i, j, value)) {
                        return false;
                    }
                    this.matrix[i][j] = value;
                }
            }

        }
        return true;
    }
}