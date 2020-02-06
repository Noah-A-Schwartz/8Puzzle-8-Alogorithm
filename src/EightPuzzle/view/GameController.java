package EightPuzzle.view;

import EightPuzzle.EightPuzzle;
import EightPuzzle.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Observable;



public class GameController {

    private static  EightPuzzle myGame;

    private static VBox vbox;
    @FXML
    private GridPane gPane;

    @FXML
    private Pane pane0;

    @FXML
    private Text text0;

    @FXML
    private Pane pane1;

    @FXML
    private Text text01;

    @FXML
    private Pane pane2;

    @FXML
    private Text text02;

    @FXML
    private Pane pane3;

    @FXML
    private Text text03;

    @FXML
    private Pane pane4;

    @FXML
    private Text text04;

    @FXML
    private Pane pane5;

    @FXML
    private Text text05;

    @FXML
    private Pane pane6;

    @FXML
    private Text text06;

    @FXML
    private Pane pane7;

    @FXML
    private Text text07;

    @FXML
    private Pane pane8;

    @FXML
    private Text text08;

    @FXML
    private Button mainButton;


    public GameController() {

    }

    @FXML
    void Solve(ActionEvent event) {
        while((myGame.isGoal()) == false) {
            myGame.solveGame();
        }
    }
    @FXML
    void returnToMain(ActionEvent event) throws IOException {
        Main.showMainView();

    }

    public void createBoard(EightPuzzle myGame) {
        this.myGame = myGame;
        for (int row = 0; row < myGame.getPuzzle().length; row++) {
            for (int column = 0; column < myGame.getPuzzle()[row].length; column++) {
                setRowAndColumn();
                setPane(row, column, myGame.getPuzzle()[row][column]);
            }
        }

    }

    public void setPane(int row, int column, int num) {

        ObservableList<Node> gridChildrens = gPane.getChildren();
        for (Node node : gridChildrens) {
            Pane p = (Pane) node;

            if (GridPane.getRowIndex(p) == row && GridPane.getColumnIndex(p) == column) {
                ObservableList<Node> paneChildren = p.getChildren();
                for (Node node2 : paneChildren) {
                    Text t = (Text) node2;
                    if(num == 0){
                        t.setText(Integer.toString(num));
                        t.setVisible(false);
                        return;
                    }
                    else {
                        t.setText(Integer.toString(num));
                        return;
                    }
                }


            }


        }

    }

    public void setRowAndColumn() {
        int count = 0;
        ObservableList<Node> gridChildrens = gPane.getChildren();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GridPane.setRowIndex(gridChildrens.get(count), i);
                GridPane.setColumnIndex(gridChildrens.get(count), j);
                count++;
            }
            }
        }
        public void moveNumber(int rowStart, int rowEnd, int ColumnStart, int columnEnd){

        }
    }
