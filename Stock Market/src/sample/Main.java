package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class Main extends Application {



        @Override
        public void start(Stage primaryStage) throws  Exception{

        Parent root = FXMLLoader.load(getClass().getResource("stockmarket.fxml"));
            //VBox root = new VBox();
           /// root.getChildren().addAll(btn,exit);
           Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Stock Spartan");
            primaryStage.show();
        }


    public static void main(String[] args) {
        launch(args);
    }
}
