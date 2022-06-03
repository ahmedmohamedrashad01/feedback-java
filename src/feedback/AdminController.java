
package feedback;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AdminController implements Initializable {
    
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Display> table;

    @FXML
    private TableColumn<Display, Integer> colID;

    @FXML
    private TableColumn<Display, String> colName;

    @FXML
    private TableColumn<Display, String> colMobile;

    @FXML
    private TableColumn<Display, String> colProblem;

    @FXML
    private TableColumn<Display, String> colDate;

    @FXML
    private JFXButton btnBack;

    @FXML
    void btnBackAction(ActionEvent event) {
        try{
            
             Stage stage;
    Parent root;
    stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
    root = FXMLLoader.load(getClass().getResource("Main_Page.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setFullScreen(true);
    stage.show();
            
            
            
//            Parent p = FXMLLoader.load(getClass().getResource("Main_Page.fxml"));
//            root.getChildren().setAll(p);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
   final ObservableList<Display> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       colID.setReorderable(false);
        colName.setReorderable(false);

        colMobile.setReorderable(false);
        colProblem.setReorderable(false);
        colDate.setReorderable(false);
        displayProblem();
        
    }    
    
     public void displayProblem() {
        try {
            DB d = new DB();
            PreparedStatement pst = d.con.prepareStatement("select * from complaint");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
               data.add(new Display(r.getInt("id"), r.getString("name"), r.getString("mobile"),r.getString("problem"), r.getString("date")));

                }

                colID.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colProblem.setCellValueFactory(new PropertyValueFactory<>("problem"));
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
               
            table.setItems(data);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
