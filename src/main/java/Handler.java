import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.math.BigDecimal;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;


/**
 * Created by Tejas on 16-10-2017.
 */


public class Handler implements Initializable {

    @FXML public TableView<Commodity> table;
    @FXML public TableColumn<Commodity, String> stock_name;
    @FXML public TableColumn<Commodity, BigDecimal> stock_last;
    @FXML public TableColumn<Commodity, BigDecimal> stock_high;
    @FXML public TableColumn<Commodity, BigDecimal> stock_low;
    @FXML public TableColumn<Commodity, BigDecimal > stock_change;
    @FXML public TableColumn<Commodity, Integer> stock_quantity;
    @FXML public Label l_name;
    @FXML LineChart<String,BigDecimal> lineChart;
    @FXML DatePicker dp1;
    @FXML DatePicker dp2;
    @FXML public ComboBox<String> comboBox;
    @FXML public TextField textField;


    //Use these labels for my account
    @FXML public Label stock_no;
    @FXML public Label profit;
    @FXML public Label top3;
    @FXML public Label bottom3;



    //Populate the table






    // @hussain this list also contains quantity and price_bought...create column and populate that too
    public ObservableList<Commodity> list = Commodity.boughtCommodity();
    //Populate combolist
    ObservableList<String> combolist = FXCollections.observableArrayList("MONTHLY","WEEKLY","DAILY");


    // @hussain need to add this combolist for selling stocks
    ObservableList<String> combolist2 = Commodity.boughtCommodity("stock_name");

    @Override
    //Initialize the table -> Adding values to each column for a particular row
    public void initialize(URL location, ResourceBundle resources) {
        stock_name.setCellValueFactory(new PropertyValueFactory<Commodity, String>("stock_name"));
        stock_last.setCellValueFactory(new PropertyValueFactory<Commodity, BigDecimal>("stock_last"));
        stock_high.setCellValueFactory(new PropertyValueFactory<Commodity, BigDecimal>("stock_high"));
        stock_low.setCellValueFactory(new PropertyValueFactory<Commodity, BigDecimal>("stock_low"));
        stock_change.setCellValueFactory(new PropertyValueFactory<Commodity, BigDecimal>("stock_change"));
        stock_quantity.setCellValueFactory(new PropertyValueFactory<Commodity, Integer>("stock_quantity"));
        table.setItems(list);
        comboBox.setItems(combolist);

    }

    //Open Commodity Window. Right now Justs print the row values
    public void mouseClick(javafx.scene.input.MouseEvent mouseEvent) throws Exception {
        if (mouseEvent.getClickCount() == 2) { //Checking double click

            //Opens a new window to display individual stock details
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("stock_a.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(table.getSelectionModel().getSelectedItem().getStock_name());
            primaryStage.show();
        }
        ;
    }
    public void labelchange(ActionEvent actionEvent) throws Exception{
        stock_no.setText("Hello");
    }



    //for calendar and charts , exception needs to be removed
    public void btn(ActionEvent event) throws Exception
    {
        lineChart.getData().clear();
        LocalDate date1 = dp1.getValue();
        LocalDate date2 = dp2.getValue();

        Calendar from=Calendar.getInstance();
        //Select the same day of the month  for both calendar for "Monthly"
        from.set(date1.getYear(),date1.getMonthValue(),date1.getDayOfMonth());

        Calendar to=Calendar.getInstance();
        to.set(date2.getYear(),date2.getMonthValue(),date2.getDayOfMonth());

        //Inteval can be "DAILY","WEEKLY","MONTHLY" only
        ArrayList<HistoricalInformation> arrayList=APICalls.getRangedIntervalHistoricalQuote(textField.getText(),from,to,comboBox.getValue());

        XYChart.Series<String,BigDecimal> series = new XYChart.Series<String, BigDecimal>();
        for (HistoricalInformation information:arrayList)
        {
            series.getData().add(new XYChart.Data<String, BigDecimal>(information.date,information.closingPrice));
            System.out.println("Date: "+information.date+" Price: "+information.closingPrice);
        }
        lineChart.getData().addAll(series);
    }



    public void btn1(ActionEvent event) throws Exception
    {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("buy_stock.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        //primaryStage.setTitle(table.getSelectionModel().getSelectedItem().getStock_name());



        primaryStage.show();
    }
    public void btn2(ActionEvent event) throws Exception
    {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sell_stock.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        //primaryStage.setTitle(table.getSelectionModel().getSelectedItem().getStock_name());
        primaryStage.show();
    }


}