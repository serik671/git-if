package javaiview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 *
 * @author serik
 */
public class PaneChooser extends Pane{
    public FileChooser fc;
    public Button button1;
    public TextField textfield1;
    private Label label1;
    public PaneChooser(double width, double height){
        setMinWidth(width);
        setMinHeight(height);
        fc= new FileChooser();
        fc.setTitle("Open image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Image",new String []{"*.png","*.jpg","*.jpeg","*.gif"}),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif")
        );
        button1 = new Button("Open");
        button1.setMinSize(64,32);
        button1.setMaxSize(64,32);
        button1.getStyleClass().add("anyB");        
        textfield1 = new TextField();
        textfield1.setMinWidth(215);
        textfield1.getStyleClass().add("textField");
        label1 = new Label("File: ");
        label1.getStyleClass().add("labelC");
        HBox hb = new HBox(10,label1,textfield1,button1);
        hb.setTranslateY(height/3);
        hb.setAlignment(Pos.CENTER);
        getChildren().add(hb);
        getStyleClass().add("paneChooser");
    }    
    
}
