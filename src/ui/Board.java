package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import model.Sudoku;
import org.apache.http.util.TextUtils;

public class Board extends Application {

    private static TextField[][] textFields;
    private static Sudoku sudoku = new Sudoku();
    private static TilePane tilePane;

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        tilePane = new TilePane();
        tilePane.setPrefColumns(9);
        tilePane.setPrefRows(9);

        HBox hBox = new HBox();
        Button solveButton = new Button();
        solveButton.setText("Solve");
        Button clearButton = new Button();
        clearButton.setText("Clear");
        Button fillRandomButton = new Button();
        fillRandomButton.setText("Fill Random");
        hBox.getChildren().addAll(solveButton, clearButton, fillRandomButton);

        createFields();

        solveButton.setOnAction(e -> {

            // Put the numbers that are in the matrix
            // in the board
            for (int i = 0; i < textFields.length; i++) {
                for (int j = 0; j < textFields.length; j++) {
                    String text = textFields[i][j].getText();
                    if (!TextUtils.isEmpty(text)) {
                        sudoku.setBoxValue(i, j, Integer.parseInt(text));
                    } else {
                        sudoku.setBoxValue(i, j, 0);
                    }
                }
            }

            if (sudoku.solve()) {
                sudoku.printMatrix();
                fillBoard();
            } else {
                Dialog dialog = new Dialog();
                dialog.setContentText("Couldn't solve");
                ButtonType button = new ButtonType("Close", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().add(button);
                dialog.show();
            }
        });

        clearButton.setOnAction(e -> {
            clear();
            sudoku.printMatrix();
        });

        fillRandomButton.setOnAction(e -> {
            clear();
            sudoku.fillRandomly();
            fillBoard();
        });

        root.setCenter(tilePane);
        root.setTop(hBox);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * Creates the board itself
     */
    private static void createFields() {
        textFields = new TextField[9][9];
        for (int i = 0; i < textFields.length; i++) {
            for (int j = 0; j < textFields.length; j++) {

                textFields[i][j] = new TextField();
                Utils.addTextLimiter(textFields[i][j], 1);
                textFields[i][j].setPrefColumnCount(1);
                tilePane.getChildren().add(textFields[i][j]);

                if ((i < 3 || i > 5) && j < 3) {
                    textFields[i][j].setStyle("-fx-border-color: royalblue;");
                }

                if ((i < 3 || i > 5) && j > 5) {
                    textFields[i][j].setStyle("-fx-border-color: royalblue;");
                }

                if (i > 2 && i < 6 && j > 2 && j < 6) {
                    textFields[i][j].setStyle("-fx-border-color: royalblue;");
                }

            }
        }
    }

    /**
     * Fills the board with numbers in the Sudoku matrix
     */
    private static void fillBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int number = sudoku.getBoxValue(i, j);
                if (number > 0) {
                    textFields[i][j].setText(
                            String.valueOf(number)
                    );
                }
            }
        }

    }

    /**
     * Clears both the matrix and the UI
     */
    private static void clear() {
        for (int i = 0; i < textFields.length; i++) {
            for (int j = 0; j < textFields.length; j++) {
                textFields[i][j].setText("");
                sudoku.setBoxValue(i, j, 0);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
