package javafxmovescene;

import javafx.scene.control.Button;

/**
 *
 * @author serik
 */
public class Scene2 extends Scene1 {
    @Override public void Castom(){        
        setMinSize(JavaFXMoveScene.width,JavaFXMoveScene.height);
        getStyleClass().add("scene2");
        nextScene = new Button("Next");
        nextScene.setOnMouseClicked(event->Control.ReScene(this,new Scene1()));
        nextScene.getStyleClass().add("BN");
        getChildren().addAll(nextScene);
    }
}
