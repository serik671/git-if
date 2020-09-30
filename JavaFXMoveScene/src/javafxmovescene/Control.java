package javafxmovescene;

import javafx.scene.layout.Pane;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Control {
        public static void ReScene(Pane p1, Pane p2){
            TranslateTransition tt1 = new TranslateTransition(Duration.millis(200),p1);
            TranslateTransition tt2 = new TranslateTransition(Duration.millis(200),p2);
            JavaFXMoveScene.root.getChildren().add(p2);
            tt1.setFromX(0);
            tt1.setToX(-600);
            tt1.play();
            tt2.setFromX(600);
            tt2.setToX(0);
            tt2.play();
            tt2.setOnFinished(event->{
                JavaFXMoveScene.root.getChildren().remove(p1);
                p1.setTranslateX(0);
                p2.setTranslateX(0);                
            }); 
           
        }
}
