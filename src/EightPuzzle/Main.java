package EightPuzzle;

import EightPuzzle.view.GameController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Arrays;

public class Main extends Application {
    private static Stage primaryStage;
    private static VBox vbox;
    private static GameController myController;

    @FXML



    @Override
    public void start(Stage primaryStage) throws IOException {
        //Start app, show main view
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Eight Puzzle");
        showMainView();

    }
    //Loads homepage from fxml
    public static void showMainView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/8GameMainMenu.fxml"));
        vbox = loader.load();
        Scene scene = new Scene((vbox));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Loads game page with numbers
    public static void showGame() throws IOException, InterruptedException {
        //Creates new game

        EightPuzzle myGame = new EightPuzzle();
        //Prints initial state
        System.out.println(Arrays.deepToString(myGame.getPuzzle()));

        //If initial state in not solvable, reshuffles game array
       while(!GFG.isSolvable(myGame.getState())) {
            System.out.println("Solution does not exist. Generating a new problem. Generating problem with a solution...");
            myGame.shuffle();
            System.out.println(Arrays.deepToString(myGame.getPuzzle()));
        }

       //Loads new game game from fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/8Game.fxml"));
        VBox vboxGame = loader.load();
        myController = loader.getController();
        //create boards adds numbers to their correct pane
        myController.createBoard(myGame);
        vbox.getChildren().setAll(vboxGame);
    }

    public static void main(String[] args) {
        launch(args);

    }

}
