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




//Controller class for Game Page
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
    //Button click for solve button, solves game, displays solution
    @FXML
    void Solve(ActionEvent event) throws InterruptedException {

        myGame.solveGame();
        showSolutionPath(myGame);



    }
    @FXML
    void returnToMain(ActionEvent event) throws IOException {
        Main.showMainView();

    }
    //This method is supposed to display the the the numbers switching in the GUI, but i did not realize until i was done that Java requires threading for The GUI to update "live",
    //So currently, just looks like display goes from initial state to goal state, but the method does display each node in the goal path.
    //Not sure if it will be possible to implement threading at this point
    //I used print statements in console to show the switching of the panes as the path is found for now.
    @FXML
    void showSolutionPath(EightPuzzle myGame) throws InterruptedException {
        //Array index of 0 of current node
        int row;
        int column;
        //Array index of 0 for the the parent array
        int lastRow;
        int lastColumn;
        Integer[][] array;
        Integer[][] parentArray;
        Integer[] previousIndexes;
        Integer[] currentIndexes;
        List<Node> list = myGame.endPath; //Sets list equal to list that stores path from root to goal
        System.out.println("Initial Problem: \n" +  Arrays.deepToString((Integer[][])list.get(0).info)); //Prints the initial state
        System.out.println("Path to goal:");
        for (int i = 0; i < list.size() ; i++){
            //If not goal node(no children), store front of list as parent, and the next index as the child
            if(list.get(i).children.size() != 0){
                parentArray = EightPuzzle.cloneArray((Integer[][])list.get(i).info);
                array = EightPuzzle.cloneArray((Integer[][])list.get(i+1).info);
            }
            //If goal state, store the node and its parent
            else {
                array = EightPuzzle.cloneArray((Integer[][]) list.get(i).info);
                parentArray = EightPuzzle.cloneArray((Integer[][]) list.get(i).parent.info);
            }
            //Find zero in the parent array and child array
            previousIndexes  = findfindZero(parentArray);
            lastRow = previousIndexes[0]; lastColumn = previousIndexes[1];
            currentIndexes = findfindZero(array);
            row = currentIndexes[0]; column = currentIndexes[1];
            //Switches the blank space with to where it is is in the next node, as well as the number it was swapped with
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
    //Sets number in given pane based on row column and the num passed to it(each grid has a pane to store the number in)
    public void setPane(int row, int column, int num){
        //Loops through the grids until it finds grid that matches current row and column
        ObservableList<javafx.scene.Node> gridChildrens = gPane.getChildren();
        for (javafx.scene.Node node : gridChildrens) {
            Pane p = (Pane) node;
            //Find children in pane of a grid pane(only text in this case)
            if (GridPane.getRowIndex(p) == row && GridPane.getColumnIndex(p) == column) {
                ObservableList<javafx.scene.Node> paneChildren = p.getChildren();
                for (javafx.scene.Node node2 : paneChildren) {
                    Text t = (Text) node2;
                    //IF pane is set to 0, also make invisible
                    if(num == 0){
                        t.setText(Integer.toString(num));
                        t.setVisible(false);
                        return;
                    }
                    //if setting 0 to a number, make visible
                    else {
                        t.setText(Integer.toString(num));
                        t.setVisible(true);
                        return;
                    }
                }


            }


        }

    }
    //Used scene builder to build GUI and used a gridpane, but scenebuilder does not assign indexes to the grids, so had to do this manually
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
