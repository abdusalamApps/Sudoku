package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import model.Sudoku;
import org.jdom.Text;

import java.util.ArrayList;

public class Board extends Application {

    static TextField[][] textFields;
    static int[][] intMatrix;

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

        solveButton.setOnAction(e -> {
            for (int i = 0; i < textFields.length; i++) {
                for (int j = 0; j < textFields.length; j++) {
                    String text = textFields[i][j].getText();
                    if (!text.equals(""))
                        intMatrix[i][j] = Integer.parseInt(text);
                }
            }

            Sudoku sudoku = new Sudoku(intMatrix);

            if (sudoku.solve()) {
                for (int i = 0; i < textFields.length; i++) {
                    for (int j = 0; j < textFields.length; j++) {
                        textFields[i][j].setText(String.valueOf(
                                sudoku.getMatrix()[i][j]
                        ));
                    }
                }
            } else {
                System.out.println("Unsolvable");
            }
        });

        clearButton.setOnAction(e -> {
            for (TextField[] fieldArray : textFields) {
                for (TextField field : fieldArray) {
                    field.setText("");
                }
            }
        });

        root.setCenter(tilePane);
        root.setTop(hBox);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
