import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Tejas on 16-10-2017.
 */
public class Handler {

    //This class will be used to call all the action listeners
    static int count=0;
    @FXML
    Label countLabel;

    public void increment(ActionEvent actionEvent) {
        count++;
        countLabel.setText(String.valueOf(count));
        System.out.println(actionEvent);
    }
}
