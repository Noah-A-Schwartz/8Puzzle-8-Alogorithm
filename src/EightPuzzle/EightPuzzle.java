package EightPuzzle;

import EightPuzzle.view.GameController;
import javafx.fxml.FXMLLoader;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EightPuzzle {
    private Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private Integer[][] state;
    private static Integer[][] goalState = {{1,2,3}, {8,0,4}, {7, 6, 5}};
    private boolean goal;
    List<Integer> list;
    private GameController myController;


    public EightPuzzle(){
        state =  new Integer[3][3];
        shuffle();



       }


    public Integer[][] getPuzzle(){
        return state;
    }

    public void shuffle(){
        list = Arrays.asList(numbers);
        Collections.shuffle(list);
        listToArray();

    }

    public void listToArray(){
        int count = 0;
        for (int row = 0; row < state.length; row++)
            for(int column = 0; column < state[row].length; column++){
                state[row][column] = list.get(count);
                count++;
            }
    }



    public boolean isGoal(EightPuzzle game){
        if(game.state == goalState)
            return true;
        else return false;
    }

    public Integer[][] getState(){
        return state;
    }

    public void solveGame(){

    }

}


