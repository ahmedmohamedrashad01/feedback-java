package feedback;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main_PageController implements Initializable {

    String status = "";
    String date = "";
    @FXML
    private BorderPane root;

    @FXML
    private JFXButton btnSend;

    @FXML
    private JFXButton btnAdmin;

    @FXML
    void btnAdminAction(ActionEvent event) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Login");
        dialog.setHeaderText("please enter administrator password");

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Password : "), 0, 1);
        grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> password.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                if (password.getText().trim().equalsIgnoreCase("123456")) {
                    try {
//                        Parent p = FXMLLoader.load(getClass().getResource("Admin.fxml"));
//                        root.getChildren().setAll(p);

                        Stage stage;
                        Parent root;
                        stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
                        root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setFullScreen(true);
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                } else {
                    new Alert(Alert.AlertType.ERROR, "Password is incorrect!!!").showAndWait();

                }

            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println(", Password=" + password.getText());
        });

    }

    @FXML
    void btnSendAction(ActionEvent event) {
        if (status.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "please select an image !!!").showAndWait();
        } else if (status.equalsIgnoreCase("normal") || status.equalsIgnoreCase("good") || status.equalsIgnoreCase("very good")) {
            try {
                //System.out.println(status);
                DB d = new DB();
                d.query = "insert into normal(status,date) values ('" + status + "','" + date + "')";
                d.stmt.executeUpdate(d.query);
                new Alert(Alert.AlertType.INFORMATION, "Done").show();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            try {
                CustomerController.status = status;
                 Stage stage;
                    Parent root;
                    stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                
//                Parent p = FXMLLoader.load(getClass().getResource("Customer.fxml"));
//                root.getChildren().setAll(p);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private ImageView imgAngry;
    @FXML
    private ImageView imgSad;
    @FXML
    private ImageView imgNormal;
    @FXML
    private ImageView imgGood;
    @FXML
    private ImageView imgVeryGood;

    @FXML
    void imgAngryAction(MouseEvent event) {
        status = "angry";
        RotateTransition r = new RotateTransition(Duration.millis(1500), imgAngry);
        r.setByAngle(360);
        r.play();

    }

    @FXML
    void imgSadAction(MouseEvent event) {
        status = "sad";
        RotateTransition r = new RotateTransition(Duration.millis(1500), imgSad);
        r.setByAngle(360);
        r.play();
    }

    @FXML
    void imgNormalAction(MouseEvent event) {
        status = "normal";
        RotateTransition r = new RotateTransition(Duration.millis(1500), imgNormal);
        r.setByAngle(360);
        r.play();
    }

    @FXML
    void imgGoodAction(MouseEvent event) {
        status = "good";
        RotateTransition r = new RotateTransition(Duration.millis(1500), imgGood);
        r.setByAngle(360);
        r.play();
    }

    @FXML
    void imgVeryGoodAction(MouseEvent event) {
        status = "very good";
        RotateTransition r = new RotateTransition(Duration.millis(1500), imgVeryGood);
        r.setByAngle(360);
        r.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        getDa();
    }

    public void getDa() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
    }

}
