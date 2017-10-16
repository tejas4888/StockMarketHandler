import javafx.scene.chart.XYChart;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tejas on 16-10-2017.
 */
public class APICalls {
    //All API calls will rest here
    static StockInformation GetPrice(String stockID)
    {
        try {
            StockQuote quote=YahooFinance.get(stockID).getQuote();
            return new StockInformation(quote.getPrice(),quote.getDayHigh(),quote.getDayLow(),quote.getChange());
        }catch (Exception e){
            BigDecimal bigDecimal=BigDecimal.valueOf(-1);
            return new StockInformation(bigDecimal,bigDecimal,bigDecimal,bigDecimal);
        }
    }

    static ArrayList<HistoricalInformation> commonHistoryFetcher(List<HistoricalQuote> list)
    {
        ArrayList<HistoricalInformation> arrayList=new ArrayList<HistoricalInformation>();

        for (HistoricalQuote historicalQuote:list)
        {
            Calendar calendar=historicalQuote.getDate();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String stringcalendar=sdf.format(calendar.getTime());

            String closingprice=String.valueOf(historicalQuote.getAdjClose());

            if(!closingprice.equals("null"))
            {
                arrayList.add(new HistoricalInformation(stringcalendar,closingprice));
            }
        }
        return arrayList;

    }

    static ArrayList<HistoricalInformation> getNormalHistoricalQuote(String stockID)
    {
        try {
            return commonHistoryFetcher(YahooFinance.get(stockID).getHistory());
        }catch (Exception e){
            return new ArrayList<HistoricalInformation>();
        }
    }

    static ArrayList<HistoricalInformation> getRangedHistoricalQuote(String stockID,Calendar fromDate,Calendar toDate)
    {
        try {
            return commonHistoryFetcher(YahooFinance.get(stockID).getHistory(fromDate,toDate));
        }catch (Exception e){
            return new ArrayList<HistoricalInformation>();
        }
    }

    static ArrayList<HistoricalInformation> getRangedIntervalHistoricalQuote(String stockID,Calendar fromDate,
                                                                             Calendar toDate,String type)
    {
        try {
            return commonHistoryFetcher(YahooFinance.get(stockID).getHistory(fromDate,toDate,Interval.valueOf(type)));
        }catch (Exception e){
            return new ArrayList<HistoricalInformation>();
        }
    }

}
