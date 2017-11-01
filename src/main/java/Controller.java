import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Tejas on 16-10-2017.
 */
public class Controller extends Application{

    /*
        Controller will be used to design the layout
        and make API calls for getting realtime data
    */

    //Final Comm









    public static void main(String args[])
    {

        launch(args);
    }

    public void  start(Stage primaryStage) throws Exception
    {
         Parent root = FXMLLoader.load(getClass().getResource("/stockmarket.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Stock Market Handler");
        primaryStage.show();




    }
}
