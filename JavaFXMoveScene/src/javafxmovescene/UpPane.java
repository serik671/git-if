package javafxmovescene;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UpPane extends Pane{
    private Button close;
    public double Ox,Oy;    
    public UpPane(){
        close = new Button();
        close.getStyleClass().add("BClose");
        close.setOnMouseClicked(event->System.exit(0));
        getStyleClass().add("up_pane");
        getChildren().add(close);
    }
    public void setMove(Stage stage){
        setOnMousePressed(event->{
            Ox = event.getSceneX();
            Oy = event.getSceneY();
        });
        setOnMouseDragged(event->{
            stage.setX(event.getScreenX()-Ox);
            stage.setY(event.getScreenY()-Oy);
        });
    }
}
