/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaiview;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

/**
 *
 * @author serik
 */
public class JavaIView extends Application{

    private boolean isStart = false;
    public static final int width = 1920, height = 1080;
    Scene scene;
    Pane root;
    StarSpace ss;
    UpPane up;
    
    @Override public void start(Stage stage){        
        
        root =  new Pane();
        scene = new Scene(root,width,height);
        scene.getStylesheets().add("cssstyle/design.css");
                
        
         ss = new StarSpace(width, height, 400);
        
         up = new UpPane(stage,width);

        scene.setOnMouseClicked(event->{
               if(!isStart){
                root.getChildren().remove(up);
                root.getChildren().add(ss);
                isStart = true;
               }
        });
        scene.setOnKeyPressed(event-> {
            if(event.getCode() == KeyCode.ESCAPE && isStart){
                ss.setStop();
                root.getChildren().remove(ss);
                root.getChildren().add(up);
                isStart = false;
            }
        });
        
        root.getChildren().add(up);
        
        stage.setScene(scene);
        stage.setMaximized(true);        
        //stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        try{
        stage.getIcons().add(new Image(new FileInputStream("Icon.png")));
        }catch(Exception e){
            System.out.println("Not image");
        }
        stage.setTitle("JavaIView");
        if(stage.isAlwaysOnTop())stage.setTitle(stage.getTitle()+"(AlwaysOnTop)");
        stage.show();
        }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
