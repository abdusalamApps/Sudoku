package model;


import java.util.Random;

public class Sudoku {

    int[][] matrix;

    public Sudoku(int[][] matrix) {
        this.matrix = matrix;
        fillRandomly();
    }

    public int getBoxValue(int x, int y) {
        return matrix[x][y];
    }

    public void setBoxValue(int x, int y, int value) {
        matrix[x][y] = value;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    private boolean solve(int i, int j) {
        return false;
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + ", ");

            }

        }
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
}