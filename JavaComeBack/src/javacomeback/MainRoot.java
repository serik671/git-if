package javacomeback;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainRoot extends Pane {
    public MainRoot(Stage stage){
        DropShadow is = new DropShadow();
        is.setColor(Color.AQUA);
        is.setRadius(50);
        Circle c1 = new Circle(250);
        c1.getStyleClass().add("circle");
        c1.setEffect(is);
        getChildren().add(c1);
        
        UpPane up = new UpPane(stage);
        up.setTranslateX(170);
        up.setWidth(200);
        //up.setPush(124);
        getChildren().add(up);
        getStyleClass().add("main_root");
    }
    
}
