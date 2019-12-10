package model;


import java.util.Random;

public class Sudoku {

    public static int[][] GRID_TO_SOLVE = {
            {9, 1, 1, 1, 0, 0, 0, 0, 5},
            {0, 0, 5, 0, 9, 0, 2, 0, 1},
            {8, 0, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 0, 7, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 6, 0, 0, 9},
            {2, 0, 0, 3, 0, 0, 0, 0, 6},
            {0, 0, 0, 2, 0, 0, 9, 0, 0},
            {0, 0, 1, 9, 0, 4, 5, 7, 0}
    };

    int[][] matrix;
    public static final int EMPTY = 0;
    public static final int SIZE = 9;

    public Sudoku(int[][] matrix) {
        this.matrix = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public int getBoxValue(int x, int y) {
        return matrix[x][y];
    }

    public void setBoxValue(int x, int y, int value) {
        matrix[x][y] = value;
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, matrix.length);
        }
    }

    /*private boolean solve(int i, int j) {
        return false;
    }*/

    public void printMatrix() {
        for (int i = 0; i < SIZE; i++) {
            System.out.println();
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + matrix[i][j]);
            }
        }
        System.out.println();
    }

    public void fillRandomly() {
        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            int positionX = random.nextInt(8);
            int positionY = random.nextInt(8);
            int element = random.nextInt(8);
            this.matrix[positionX][positionY] = element;
        }

    }

    /**
     * Checks if the specified number is in the specified row
     *
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
     *
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
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (matrix[row][col] == EMPTY) {
                    for (int number = 1; number <= SIZE; number++) {
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

    public static void main(String[] args) {
/*
        Sudoku sudoku = new Sudoku(GRID_TO_SOLVE);
        System.out.println("Grid to solve: ");
        sudoku.printMatrix();
*/

        int[][] matrix = new int[9][9];
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 1;

        Sudoku sudoku = new Sudoku(matrix);

        if (sudoku.solve()) {
            System.out.println("Solved");
            sudoku.printMatrix();
        } else {
            System.out.println("Couldn't solve!");
        }
    }
}