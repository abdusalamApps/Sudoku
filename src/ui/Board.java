package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Board extends Application {

    static TextField[][] textFields;
    static int[][] intMatrix;
    static boolean fail = false;

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

        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            integers.add(i);
            intMatrix[0][i - 1] = i;
        }
        intMatrix[0][3] = 0;
        intMatrix[0][1] = 0;

        System.out.print("List:   ");
        integers.forEach(num -> System.out.print(num + " "));
        System.out.println();
        System.out.print("Matrix: ");
        for (int i = 0; i < intMatrix.length; i++) {
            System.out.print(intMatrix[0][i] + " ");
        }
        System.out.println();

        solveButton.setOnAction(e -> {
            while (containsZeros(intMatrix[0])) {
                solveRow(0);
            }

            for (int k = 0; k < intMatrix.length; k++) {
                System.out.print(intMatrix[0][k] + " ");
            }
            System.out.println();

        });

        System.out.print("New matrix: ");

        root.setCenter(tilePane);
        root.setTop(hBox);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void checkRow(ArrayList<Integer> list, int index) {
        if (!list.isEmpty() && index >= 0) {
            int current = intMatrix[0][index];
            if (list.contains(current)) {
                System.out.println(current);
//                System.out.println(list.remove(index));
                list.remove((Object) current);
                checkRow(list, list.size() - 1);
            } else {
                System.out.println("fail");
            }
        }

    }

    private static void solveRow(int column) {
        if (column <= 8) {
            if (intMatrix[0][column] == 0) {
                for (int i = 1; i <= 9; i++) {
                    if (!arrayContains(intMatrix[0], i)) {
                        intMatrix[0][column] = i;
                    }
                }
            } else {
                solveRow(column + 1);
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
