package javaiview;

import java.io.FileInputStream;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author serik
 */
public class ModalHelp extends Stage {
    private int width=800,height=800,frameWidth=5;
    private String title="Help";
    public ModalHelp(){
        Pane root = new Pane();
        Label help = new Label();
        
        //Uppane
        UpPane up = new UpPane(this,width,title);        
        up.setMinimaze(false);
        up.setMoved(root);
        
        //About Frame
        Rectangle frame = new Rectangle();
        frame.getStyleClass().add("Frame");
        frame.setStrokeWidth(frameWidth);
        frame.setWidth(width);
        frame.setHeight(height-up.getUpHeight()-frameWidth);
        frame.setTranslateY(up.getUpHeight());
        frame.setFill(Color.TRANSPARENT);
        root.getChildren().addAll(frame,up);
        
        help.setTranslateX(frameWidth);
        help.setTranslateY(frameWidth+up.getUpHeight());
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
        Scene scene = new Scene(root,width,height);
        scene.setCursor(JavaIView.scene.getCursor());
        scene.getStylesheets().add("cssstyle/design.css");
        setScene(scene);
        initStyle(StageStyle.UNDECORATED);
        initModality(Modality.APPLICATION_MODAL);
        setTitle(title);
        showAndWait();
    }
}
