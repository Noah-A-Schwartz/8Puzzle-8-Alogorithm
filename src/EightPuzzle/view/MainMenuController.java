package EightPuzzle.view;

import EightPuzzle.EightPuzzle;
import EightPuzzle.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;

public class MainMenuController {



    @FXML
    private Button buttonStart;

    private EightPuzzle myGame;

    private VBox vbox;

    private static GameController myController;

    public MainMenuController(){ }


    @FXML
    void startGame(ActionEvent event)throws IOException {
        Main.showGame(myGame);

    }
    @FXML
    public Button getButtonStart() {
        return buttonStart;
    }
}


