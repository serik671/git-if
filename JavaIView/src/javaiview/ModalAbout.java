package javaiview;

import java.io.FileInputStream;
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


public class ModalAbout extends Stage{
    private int width=600,height=300,frameWidth=5;
    private String title = "About";
    public ModalAbout(){
        Pane root = new Pane();
        Label about = new Label();
        
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
        
        about.getStyleClass().add("LabelText");
        about.setTranslateX(frameWidth);
        about.setTranslateY(frameWidth+up.getUpHeight());
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
        Scene scene = new Scene(root,width,height);  
        scene.getStylesheets().add("cssstyle/design.css");
        scene.setCursor(JavaIView.scene.getCursor());
        setScene(scene);
        initStyle(StageStyle.UNDECORATED);
        initModality(Modality.APPLICATION_MODAL);
        setTitle(title);
        showAndWait();
    }
}
