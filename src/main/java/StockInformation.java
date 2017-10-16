import java.math.BigDecimal;

/**
 * Created by Tejas on 16-10-2017.
 */
public class StockInformation {

    BigDecimal currentPrice,dayHigh,dayLow,change;

    StockInformation(BigDecimal currentPrice,BigDecimal dayHigh,BigDecimal dayLow,BigDecimal change)
    {
        this.currentPrice=currentPrice;
        this.dayHigh=dayHigh;
        this.dayLow=dayLow;
        this.change=change;
    }
}

class HistoricalInformation{

    String date,closingPrice;

    HistoricalInformation(String date,String closingPrice)
    {
        this.date=date;
        this.closingPrice=closingPrice;
    }

}
