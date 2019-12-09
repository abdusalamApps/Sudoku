package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import model.Sudoku;
import org.apache.http.util.TextUtils;

import java.util.ArrayList;

public class Board extends Application {

    static TextField[][] textFields;
    static int[][] intMatrix;
    static Sudoku sudoku;

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(9);
        tilePane.setPrefRows(9);

        HBox hBox = new HBox();
        Button solveButton = new Button();
        solveButton.setText("Solve");
        Button clearButton = new Button();
        clearButton.setText("Clear");
        hBox.getChildren().addAll(solveButton, clearButton);

        textFields = new TextField[9][9];
        intMatrix = new int[9][9];
        sudoku = new Sudoku(intMatrix);

        for (int i = 0; i < intMatrix.length; i++) {
            for (int j = 0; j < intMatrix.length; j++) {
                textFields[i][j] = new TextField();
                Utils.addTextLimiter(textFields[i][j], 1);
                textFields[i][j].setPrefColumnCount(1);
                tilePane.getChildren().add(textFields[i][j]);

                if ((i < 3 || i > 5) && j < 3) {
                    textFields[i][j].setStyle("-fx-border-color: orange;");
                }

                if ((i < 3 || i > 5) && j > 5) {
                    textFields[i][j].setStyle("-fx-border-color: orange;");
                }

                if (i > 2 && i < 6 && j > 2 && j < 6) {
                    textFields[i][j].setStyle("-fx-border-color: orange;");
                }
            }

        }

        fillFieldsRandomly();
        solveButton.setOnAction(e -> {
            for (int i = 0; i < textFields.length; i++) {
                for (int j = 0; j < textFields.length; j++) {
                    String text = textFields[i][j].getText();
                    if (!TextUtils.isEmpty(text)) {
                        intMatrix[i][j] = Integer.parseInt(text);
                    }
                }
            }
            System.out.println("Copied");
            sudoku.printMatrix();
        });


        root.setCenter(tilePane);
        root.setTop(hBox);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void fillFieldsRandomly() {
        for (int i = 0; i < textFields.length; i++) {
            for (int j = 0; j < textFields.length; j++) {
                int num = sudoku.getMatrix()[i][j];
                if (num > 0) {
                    textFields[i][j].setText(String.valueOf(num));
                }
            }

        }
    }

    private static boolean arrayContains(int[] array, int number) {
        for (int value : array) {
            if (number == value) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsZeros(int[] array) {
        for (int value : array) {
            if (value == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
