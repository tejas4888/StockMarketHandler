import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tejas on 16-10-2017.
 */
public class Handler implements Initializable {

    @FXML private TableView<Commodity> table;
    @FXML private TableColumn<Commodity, String> stock_name;
    @FXML private TableColumn<Commodity, Double> stock_last;
    @FXML private TableColumn<Commodity, Double> stock_high;
    @FXML private TableColumn<Commodity, Double > stock_low;
    @FXML private TableColumn<Commodity, Double > stock_change;
    @FXML private Label l_name;

    //Populate the table
    public ObservableList<Commodity> list = FXCollections.observableArrayList(
            new Commodity("CISCO",33.49,33.61,33.49,33.49),
            new Commodity("INTEL",33.49,33.61,33.49,33.49),
            new Commodity("Oracle",33.49,33.61,33.49,33.49),
            new Commodity("HP",33.49,33.61,33.49,33.49)
    );


    @Override
    //Initialize the table -> Adding values to each column for a particular row
    public void initialize(URL location, ResourceBundle resources) {
        stock_name.setCellValueFactory(new PropertyValueFactory<Commodity, String>("stock_name"));
        stock_last.setCellValueFactory(new PropertyValueFactory<Commodity, Double>("stock_last"));
        stock_high.setCellValueFactory(new PropertyValueFactory<Commodity, Double>("stock_high"));
        stock_low.setCellValueFactory(new PropertyValueFactory<Commodity, Double>("stock_low"));
        stock_change.setCellValueFactory(new PropertyValueFactory<Commodity, Double>("stock_change"));

        table.setItems(list);


    }


    //Open Commodity Window. Right now Justs print the row values
    public void mouseClick(javafx.scene.input.MouseEvent mouseEvent) throws Exception{
        if (mouseEvent.getClickCount() == 2) //Checking double click

        {
            /*System.out.println(table.getSelectionModel().getSelectedItem().getStock_name());
            System.out.println(table.getSelectionModel().getSelectedItem().getStock_last());
            System.out.println(table.getSelectionModel().getSelectedItem().getStock_high());
            System.out.println(table.getSelectionModel().getSelectedItem().getStock_low());
            System.out.println(table.getSelectionModel().getSelectedItem().getStock_change());
            */
            //Opens a new window to display individual stock details
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("stock_a.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(table.getSelectionModel().getSelectedItem().getStock_name());
            primaryStage.show();

        }


    }
}
