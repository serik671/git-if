package javafxmovescene;

import java.io.FileInputStream;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Scene1 extends Pane{
    
    protected Button nextScene;
    protected Image img;
    
    
    public Scene1(){        
        setTranslateY(30);
        Castom();
        
    }
    protected void Castom(){
        try{
            img = new Image(new FileInputStream("BackScene.png"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ImageView BImage = new ImageView(img);
        getChildren().add(BImage);
        getStyleClass().add("scene1");
        nextScene = new Button("Next");
        nextScene.setOnMouseClicked(event->Control.ReScene(this,new Scene2()));
        nextScene.getStyleClass().add("BN");
        getChildren().addAll(nextScene);
    }
}
