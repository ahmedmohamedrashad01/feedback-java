package feedback;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CustomerController implements Initializable {

    public static String status = "";
    String date = "";
    @FXML
    private BorderPane root;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    private JFXTextArea txtComplaint;

    @FXML
    private JFXButton btnSendData;

    @FXML
    void btnSendDataAction(ActionEvent event) {
        if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "please enter your name").show();
        } else if (txtName.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "customer name must be characters only !").show();

        } else if (txtMobile.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "please enter your mobile number").show();

        } else if (txtMobile.getText().matches("[a-zA-Z]+")) {
            new Alert(Alert.AlertType.ERROR, "please enter your mobile number digits only!").show();

        } else if (txtComplaint.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "please enter your complaint").show();
        } else if (txtComplaint.getText().matches("[0-9]+")) {
            new Alert(Alert.AlertType.ERROR, "please enter your complaint characters only!").show();

        } else {
            try {
                DB d = new DB();
                d.query = "insert into complaint (name,mobile,problem,date) values ('" + txtName.getText().trim() + "','" + txtMobile.getText().trim() + "','" + txtComplaint.getText().trim() + "','" + date + "')";
                d.stmt.executeUpdate(d.query);
                new Alert(Alert.AlertType.INFORMATION, "feedback has been sent").showAndWait();
                try {
//                    Parent p = FXMLLoader.load(getClass().getResource("Main_Page.fxml"));
//                    root.getChildren().setAll(p);

                    Stage stage;
                    Parent root;
                    stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("Main_Page.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(status);
        getDa();
    }

    public void getDa() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
    }

}
