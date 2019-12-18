package model;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    @org.junit.jupiter.api.Test
    void testEmpty() {
        Sudoku sudoku = new Sudoku();
        assertTrue(sudoku.solve());
        sudoku = null;
    }

    @org.junit.jupiter.api.Test
    void testSolvable() {
        Sudoku sudoku = new Sudoku();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                 sudoku.setBoxValue(i, j, Matrices.solvable[i][j]);
            }
        }
        sudoku.printMatrix();
        assertTrue(sudoku.solve());
        sudoku = null;
    }

    @org.junit.jupiter.api.Test
    void testUnsolvable() {
        Sudoku sudoku = new Sudoku();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku.setBoxValue(i, j, Matrices.unsolvable[i][j]);
            }
        }
        assertFalse(sudoku.solve());
        sudoku = null;
    }

    @org.junit.jupiter.api.Test
    void testUnsolvableUserInput() {
        Sudoku sudoku = new Sudoku();
        sudoku.setBoxValue(0, 0, 1);
        sudoku.setBoxValue(0, 1, 1);
        assertFalse(sudoku.solve());
        sudoku = null;
    }

}