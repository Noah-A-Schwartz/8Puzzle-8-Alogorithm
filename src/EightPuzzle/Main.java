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

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Eight Puzzle");
        showMainView();

    }
    public static void showMainView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/8GameMainMenu.fxml"));
        vbox = loader.load();
        Scene scene = new Scene((vbox));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showGame(EightPuzzle myGame)throws IOException{
        myGame = new EightPuzzle();
        System.out.println(Arrays.deepToString(myGame.getPuzzle()));

       while(!GFG.isSolvable(myGame.getState())) {
            System.out.println("Solution does not exist. Generating a new problem. Generating problem with a solution...");
            myGame.shuffle();
            System.out.println(Arrays.deepToString(myGame.getPuzzle()));
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/8Game.fxml"));
        VBox vboxGame = loader.load();
        myController = loader.getController();
        myController.createBoard(myGame);
        vbox.getChildren().setAll(vboxGame);
    }

    public static void main(String[] args) {
        launch(args);

    }

}
