package EightPuzzle;

import EightPuzzle.view.GameController;
import javafx.fxml.FXMLLoader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EightPuzzle {
    private Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private Integer[][] state;
    private static Integer[][] goalState = {{1,2,3}, {8,0,4}, {7, 6, 5}};
    private boolean goal;
    private GameController myController;


    public EightPuzzle(){
        state =  new Integer[3][3];
        List<Integer> list = Arrays.asList(numbers);
        Collections.shuffle(list);
        int count = 0;
          for (int row = 0; row < state.length; row++)
              for(int column = 0; column < state[row].length; column++){
                state[row][column] = list.get(count);
                count++;
              }
       }


    public Integer[][] getPuzzle(){
        return state;
    }



    private boolean isGoal(EightPuzzle game){
        if(game.state == goalState)
            return true;
        else return false;
    }

    public void solveGame(){

    }

}


