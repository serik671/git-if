package javacomeback;

import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UpPane extends Pane{
    private HBox main;
    private Button BClose;
    private Button BMin;
    private double width = 100;
    private double height = 30;
    private double Ox,Oy;
    public UpPane(Stage stage){
        main = new HBox(150);       
        BMin = new Button();
        BClose = new Button();
        main.setMinSize(width,height);
        main.getStyleClass().add("main_up");
        
        BMin.getStyleClass().add("BMin");
        BClose.getStyleClass().add("BClose");
        BMin.setOnMouseClicked(event->stage.setIconified(true));
        BClose.setOnMouseClicked(event-> System.exit(0));
        
        main.setOnMousePressed(event->{
            JavaComeBack.root.setOpacity(0.5);
            Ox = event.getSceneX();
            Oy = event.getSceneY();
        });
        main.setOnMouseReleased(event-> JavaComeBack.root.setOpacity(1));
        main.setOnMouseDragged(event->{
            stage.setX(event.getScreenX()-Ox);
            stage.setY(event.getScreenY()-Oy);
        });
        
        main.getChildren().addAll(BMin,BClose);
        getChildren().add(main);
    }
    
    public void setWidth(double d){
        main.setMinWidth(d);
    }
    public void setPush(double d){
        BClose.setTranslateX(d);
        BMin.setTranslateX(d);
    }
}
