package EightPuzzle.view;

import EightPuzzle.EightPuzzle;
import EightPuzzle.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;

//Controller class for Main menu page
public class MainMenuController {



    @FXML
    private Button buttonStart;

    private EightPuzzle myGame;





    public MainMenuController(){ }

    //Loads new game from main menu
    @FXML
    void startGame(ActionEvent event) throws IOException, InterruptedException {
        Main.showGame();

    }

}


