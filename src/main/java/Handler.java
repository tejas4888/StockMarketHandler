import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.math.BigDecimal;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



/**
 * Created by Tejas on 16-10-2017.
 */
public class Handler implements Initializable {

    @FXML private TableView<Commodity> table;
    @FXML private TableColumn<Commodity, String> stock_name;
    @FXML private TableColumn<Commodity, BigDecimal> stock_last;
    @FXML private TableColumn<Commodity, BigDecimal> stock_high;
    @FXML private TableColumn<Commodity, BigDecimal> stock_low;
    @FXML private TableColumn<Commodity, BigDecimal > stock_change;
    @FXML private Label l_name;


    StockInformation stockInformation=APICalls.GetPrice("CSCO");
    StockInformation stockInformation1=APICalls.GetPrice("INTC");
    StockInformation stockInformation2=APICalls.GetPrice("ORCL");
    StockInformation stockInformation3=APICalls.GetPrice("HPQ");


//test comment


    //Populate the table
    public ObservableList<Commodity> list = FXCollections.observableArrayList(
            new Commodity("CISCO", stockInformation.currentPrice, stockInformation.dayHigh,stockInformation.dayLow,stockInformation.change),
            new Commodity("INTEL", stockInformation1.currentPrice, stockInformation1.dayHigh, stockInformation1.dayLow, stockInformation1.change),
            new Commodity("Oracle", stockInformation2.currentPrice, stockInformation2.dayHigh, stockInformation2.dayLow, stockInformation2.change),
            new Commodity("HP", stockInformation3.currentPrice, stockInformation3.dayHigh, stockInformation3.dayLow ,stockInformation3.change)
    );


    @Override
    //Initialize the table -> Adding values to each column for a particular row
    public void initialize(URL location, ResourceBundle resources) {
        stock_name.setCellValueFactory(new PropertyValueFactory<Commodity, String>("stock_name"));
        stock_last.setCellValueFactory(new PropertyValueFactory<Commodity, BigDecimal>("stock_last"));
        stock_high.setCellValueFactory(new PropertyValueFactory<Commodity, BigDecimal>("stock_high"));
        stock_low.setCellValueFactory(new PropertyValueFactory<Commodity, BigDecimal>("stock_low"));
        stock_change.setCellValueFactory(new PropertyValueFactory<Commodity, BigDecimal>("stock_change"));

        table.setItems(list);


    }


    //Open Commodity Window. Right now Justs print the row values
    public void mouseClick(javafx.scene.input.MouseEvent mouseEvent) throws Exception{
        if (mouseEvent.getClickCount() == 2) //Checking double click

        {
            //Opens a new window to display individual stock details
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("stock_a.fxml"));

            AreaChart StockChart=(AreaChart) root.lookup("#StockChart");
            StockChart.setTitle("Stock History");

            String stockID="CSCO";
            String currentSelection=table.getSelectionModel().getSelectedItem().getStock_name();

            if(currentSelection.equals("INTEL")) {
                stockID="INTC";
            }else if(currentSelection.equals("Oracle")){
                stockID="ORCL";
            }else if (currentSelection.equals("HP")){
                stockID="HPQ";
            }

            ArrayList<HistoricalInformation> arrayList=APICalls.getNormalHistoricalQuote(stockID);

            XYChart.Series series = new XYChart.Series();
            series.setName("Stock Price");

            for (HistoricalInformation information:arrayList)
            {
                System.out.println("Date: "+information.date+" Price: "+information.closingPrice);
                series.getData().add(new XYChart.Data(information.date,Float.parseFloat(information.closingPrice)));
            }

            StockChart.getData().add(series);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(table.getSelectionModel().getSelectedItem().getStock_name());
            primaryStage.show();

        }


    }
}
