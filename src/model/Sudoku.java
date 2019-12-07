package model;


public class Sudoku {

    int[][] matrix;

    public Sudoku(int[][] matrix) {
        this.matrix = matrix;
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
}