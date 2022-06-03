
package feedback;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Feedback extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       try{
            Parent p = FXMLLoader.load(getClass().getResource("Main_Page.fxml"));
        
        Scene scene = new Scene(p);
        
        primaryStage.setTitle("Feedback App");
        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
