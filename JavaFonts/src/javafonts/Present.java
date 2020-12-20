package javafonts;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author serik
 */
public class Present extends Stage{
    private final Pane pane = new Pane();
    private final Scene scene = new Scene(pane);
    private double x=0,y=0;
    public Present(String text, String color, String back, int size, boolean isAlways){        
        Label label = new Label(text);
        scene.getStylesheets().add("design/style.css");
        label.getStyleClass().add("present");
        label.setStyle(
                "-fx-background-color:"+back+";\n"+
                "-fx-text-fill:"+color+";\n"+
                "-fx-font-size:"+size+";\n"                
                );
        pane.getChildren().add(label);
        pane.setStyle("-fx-background-color:transparent");
        scene.setFill(Color.TRANSPARENT);
        
        scene.setOnMousePressed(event->{
            x=event.getSceneX();
            y=event.getSceneY();
        });
        scene.setOnMouseDragged(event->{
            setX(event.getScreenX()-x);
            setY(event.getScreenY()-y);
        });
        
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT); //AlwaysOnTop после initStyle!!!
        if(isAlways)setAlwaysOnTop(true);
        initModality(Modality.WINDOW_MODAL);       
        show();
    }
}
