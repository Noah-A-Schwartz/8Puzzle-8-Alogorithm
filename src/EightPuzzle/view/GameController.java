package EightPuzzle.view;

import EightPuzzle.EightPuzzle;
import EightPuzzle.Main;
import EightPuzzle.Node;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.util.Timer;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.animation.PathTransition;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.Arrays;
import java.util.List;
import java.io.IOException;





public class GameController {



    private static VBox vbox;
    @FXML
    private GridPane gPane;

    @FXML
    private Pane pane0;

    @FXML
    private Text text0;

    @FXML
    private Line line0To1;

    @FXML
    private Line line0To3;

    @FXML
    private Pane pane1;

    @FXML
    private Text text01;

    @FXML
    private Line line1To2;

    @FXML
    private Line line1To4;

    @FXML
    private Pane pane2;

    @FXML
    private Text text02;

    @FXML
    private Line line2To5;

    @FXML
    private Pane pane3;

    @FXML
    private Text text03;

    @FXML
    private Line line3To4;

    @FXML
    private Line line3To6;

    @FXML
    private Pane pane4;

    @FXML
    private Text text04;

    @FXML
    private Line line4To5;

    @FXML
    private Line line4To7;

    @FXML
    private Pane pane5;

    @FXML
    private Text text05;

    @FXML
    private Line line8To8;

    @FXML
    private Pane pane6;

    @FXML
    private Text text06;

    @FXML
    private Line line6To7;

    @FXML
    private Pane pane7;

    @FXML
    private Text text07;

    @FXML
    private Button startSolve;

    @FXML
    private Button mainButton;

    @FXML
    private Line line7To8;

    @FXML
    private Pane pane8;

    @FXML
    private Text text08;

    @FXML
    private Line line0To32;

    @FXML
    private Line line0To33;

    @FXML
    private Line line0To34;

    @FXML
    private Line line0To35;

    private EightPuzzle myGame;


    public GameController() {

    }

    @FXML
    void Solve(ActionEvent event) throws InterruptedException {

        myGame.solveGame();
        showSolutionPath(myGame);



    }
    @FXML
    void returnToMain(ActionEvent event) throws IOException {
        Main.showMainView();

    }

    @FXML
    void showSolutionPath(EightPuzzle myGame) throws InterruptedException {
        Timer timer;
        int row;
        int column;
        int lastRow;
        int lastColumn;
        Integer[][] array;
        Integer[][] parentArray;
        Integer[] previousIndexes;
        Integer[] currentIndexes;
        List<Node> list = myGame.endPath;
        System.out.println("Initial Problem: \n" +  Arrays.deepToString((Integer[][])list.get(0).info));
        System.out.println("Path to goal:");
        for (int i = 0; i < list.size() ; i++){
            if(list.get(i).children.size() != 0){
                parentArray = EightPuzzle.cloneArray((Integer[][])list.get(i).info);
                array = EightPuzzle.cloneArray((Integer[][])list.get(i+1).info);
            }
            else {
                array = EightPuzzle.cloneArray((Integer[][]) list.get(i).info);
                parentArray = EightPuzzle.cloneArray((Integer[][]) list.get(i).parent.info);
            }
            previousIndexes  = findfindZero(parentArray);
            lastRow = previousIndexes[0]; lastColumn = previousIndexes[1];
            currentIndexes = findfindZero(array);
            row = currentIndexes[0]; column = currentIndexes[1];
            setPane(lastRow, lastColumn, array[lastRow][lastColumn]);
            setPane(row, column, array[row][column]);
            if(i != list.size() - 1)
            System.out.println("Swap " +  parentArray[row][column] + " With " +array[row][column]);




                }
            }



    private Integer[] findfindZero(Integer[][] array) {
        Integer[] index = new Integer[2];
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (array[row][column] == 0) {
                    index[0] = row;
                    index[1] = column;
                }

            }
        }
        return index;
    }


    public void createBoard(EightPuzzle myGame)  {
        this.myGame = myGame;
        for (int row = 0; row < myGame.getPuzzle().length; row++) {
            for (int column = 0; column < myGame.getPuzzle()[row].length; column++) {
                setRowAndColumn();
                setPane(row, column, myGame.getPuzzle()[row][column]);
            }
        }

    }

    public void setPane(int row, int column, int num){

        ObservableList<javafx.scene.Node> gridChildrens = gPane.getChildren();
        for (javafx.scene.Node node : gridChildrens) {
            Pane p = (Pane) node;

            if (GridPane.getRowIndex(p) == row && GridPane.getColumnIndex(p) == column) {
                ObservableList<javafx.scene.Node> paneChildren = p.getChildren();
                for (javafx.scene.Node node2 : paneChildren) {
                    Text t = (Text) node2;
                    if(num == 0){
                        t.setText(Integer.toString(num));
                        //t.setVisible(false);
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
        ObservableList<javafx.scene.Node> gridChildrens = gPane.getChildren();
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
