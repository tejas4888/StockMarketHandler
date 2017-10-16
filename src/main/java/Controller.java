import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

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

    public static void main(String args[])
    {
        System.out.println("Hello");

        try {
            //stockID can be found for various NASDAQ listed stocks from YahooFinance
            StockInformation stockInformation=APICalls.GetPrice("CSCO");
            System.out.println("Current Price: "+stockInformation.currentPrice);
            System.out.println("Day high: "+stockInformation.dayHigh);
            System.out.println("Day low: "+stockInformation.dayLow);
            System.out.println("Amount change: "+stockInformation.change);

            Calendar from=Calendar.getInstance();
            //Below calendar will start from 2nd November
            from.set(2015,10,1);

            Calendar to=Calendar.getInstance();
            to.set(2016,1,1);

            //Inteval can be "DAILY","WEEKLY","MONTHLY" only
            ArrayList<HistoricalInformation> arrayList=APICalls.getRangedIntervalHistoricalQuote("CSCO",from,to,"MONTHLY");

            for (HistoricalInformation information:arrayList)
            {
                System.out.println("Date: "+information.date+" Price: "+information.closingPrice);
            }

        }catch (Exception e){}
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
