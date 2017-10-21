import javafx.beans.property.SimpleStringProperty;
import java.math.BigDecimal;

/**
 * Created by Tejas on 21-10-2017.
 */
public class Commodity {

    private final SimpleStringProperty stock_name;
    private final BigDecimal stock_last;
    private final  BigDecimal stock_high;
    private final  BigDecimal stock_low;
    private final  BigDecimal stock_change;

    public Commodity(String stock_name, BigDecimal stock_last, BigDecimal stock_high, BigDecimal stock_low, BigDecimal stock_change) {
        this.stock_name = new SimpleStringProperty(stock_name);
        this.stock_last = new BigDecimal(String.valueOf((stock_last)));
        this.stock_high = new BigDecimal(String.valueOf(stock_high));
        this.stock_low = new BigDecimal(String.valueOf(stock_low));
        this.stock_change = new BigDecimal(String.valueOf(stock_change));
    }

    public String getStock_name() {
        return stock_name.get();
    }

    public BigDecimal getStock_last() {
        return stock_last;
    }

    public BigDecimal getStock_high() {

        return stock_high;
    }

    public BigDecimal getStock_low() {
        return stock_low;
    }

    public BigDecimal getStock_change() {

        return stock_change;
    }

}
