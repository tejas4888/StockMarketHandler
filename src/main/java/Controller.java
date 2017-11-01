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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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


    static Statement stmt;


    public static void main(String args[])
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/StockMarketHandler","root","danimithil123");
            stmt = con.createStatement();
        }catch(Exception e){ System.out.println(e);}

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
