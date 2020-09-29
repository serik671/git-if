package javaiview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author serik
 */
public class UpPane extends Pane{
        private Button min;
        private Button close;
    public UpPane(Stage stage,double width){
        min = new Button();        
        close = new Button();        
        
        min.getStyleClass().add("minB");
        close.getStyleClass().add("closeB");        
        
        HBox box = new HBox();
        box.getChildren().addAll(min,close); 
        
        FlowPane pane = new FlowPane(box);
        pane.setMinWidth(width);
        pane.setMaxWidth(20);
        
        pane.setAlignment(Pos.CENTER_RIGHT);
        
        setMinHeight(31);
                
        min.setOnMouseClicked(event-> stage.setIconified(true));
        close.setOnMouseClicked(event-> System.exit(0));
        getChildren().add(pane);
        getStyleClass().add("upPane");
    }
    
}
