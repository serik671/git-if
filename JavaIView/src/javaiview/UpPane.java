package javaiview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        private Label ltitle;
        private HBox box;
        public double x,y;
        private double height=31;
        private Stage stage;
    public UpPane(Stage stage,double width, String title){
        this.stage = stage;
        min = new Button();        
        close = new Button();        
        ltitle = new Label(title);
        ltitle.setTranslateX(10);
        ltitle.getStyleClass().add("labelC");
        min.getStyleClass().add("minB");
        close.getStyleClass().add("closeB");        
        
        box = new HBox();
        box.getChildren().addAll(min,close); 
        
        FlowPane pane = new FlowPane(box);
        pane.setMinWidth(width);

        
        pane.setAlignment(Pos.CENTER_RIGHT);
        
        setMinHeight(height);    
        
        min.setOnMouseClicked(event-> stage.setIconified(true));
        close.setOnMouseClicked(event-> stage.close());
        getChildren().addAll(pane,ltitle);
        getStyleClass().add("upPane");
    }
    
    public void setMinimaze(boolean f){
        box.getChildren().remove(min);
        if(f)box.getChildren().add(min);         
    }    
    public double getUpHeight(){
        return height;
    }
    public void setMoved(Pane root){        
        root.setOnMousePressed(event->{
            x = event.getSceneX();
            y = event.getSceneY();
        });
        setOnMouseDragged(event->{
            stage.setX(event.getScreenX()-x);
            stage.setY(event.getScreenY()-y);
        });
    }
}
