package javaiview;

import java.io.FileInputStream;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ModalAbout extends Stage{
    public ModalAbout(){
        Pane root = new Pane();
        Label about = new Label();
        about.getStyleClass().add("LabelText");
        try{
            FileInputStream file = new FileInputStream("About.txt");
            byte[] str = new byte[file.available()];            
            file.read(str);
            about.setFont(Font.font(25));
            about.setText(new String(str));            
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Not file \"About.txt\"");
            alert.showAndWait();            
        }
        root.getChildren().add(about);
        Scene scene = new Scene(root,600,300);  
        scene.getStylesheets().add("cssstyle/design.css");
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("About");
        showAndWait();
    }
}
