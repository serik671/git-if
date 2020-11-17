package javaiview;

import java.io.FileInputStream;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author serik
 */
public class ModalHelp extends Stage {
    public ModalHelp(){
        Pane root = new Pane();
        Label help = new Label();
        help.getStyleClass().add("LabelText");
        help.setFont(Font.font(20));
        try{
            FileInputStream file = new FileInputStream("Help.txt");
            byte[] A= new byte [file.available()];
            file.read(A);
            help.setText(new String(A));
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not file \"Help.txt\"");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        root.getChildren().add(help);
        Scene scene = new Scene(root,800,800);
        scene.getStylesheets().add("cssstyle/design.css");
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Help");
        showAndWait();
    }
}
