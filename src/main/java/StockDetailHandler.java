import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tejas on 22-10-2017.
 */
public class StockDetailHandler  implements Initializable {

    @FXML public TextField textField = new TextField();
    @FXML public TextField textField1 = new TextField();
    @FXML public TextField textField2 = new TextField();

    public StockDetailHandler() throws IOException {
        super();
    }


    public void btn2(ActionEvent event) throws Exception
    {
//make it dynamic //
       // StockInformation stockInformation=APICalls.GetPrice(textField.getText());
        System.out.println(textField.getText());
        Commodity buy = new Commodity(textField.getText());
        buy.buyCommodity(Integer.parseInt(textField1.getText()));


    }

    public void btn3(ActionEvent actionEvent) throws Exception{
        Commodity sell = new Commodity(textField2.getText());
        sell.sellCommodity();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

//For the dropdown menu
}
