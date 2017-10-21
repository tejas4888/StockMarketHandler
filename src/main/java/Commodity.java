import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Tejas on 21-10-2017.
 */
public class Commodity {

    private final SimpleStringProperty stock_name;
    private final SimpleDoubleProperty stock_last;
    private final  SimpleDoubleProperty stock_high;
    private final  SimpleDoubleProperty stock_low;
    private final  SimpleDoubleProperty stock_change;

    public Commodity(String stock_name, Double stock_last, Double stock_high, Double stock_low, Double stock_change) {
        this.stock_name = new SimpleStringProperty(stock_name);
        this.stock_last = new SimpleDoubleProperty(stock_last);
        this.stock_high = new SimpleDoubleProperty(stock_high);
        this.stock_low = new SimpleDoubleProperty(stock_low);
        this.stock_change = new SimpleDoubleProperty(stock_change);
    }

    public String getStock_name() {
        return stock_name.get();
    }

    public Double getStock_last() {
        return stock_last.get();
    }

    public Double getStock_high() {
        return stock_high.get();
    }

    public Double getStock_low() {
        return stock_low.get();
    }

    public Double getStock_change() {
        return stock_change.get();
    }

}
