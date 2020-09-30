package javafxmovescene;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
public class JavaFXMoveScene extends Application{
    public static Pane root;
    public static Scene scene;
    public static final double width = 600;
    public static final double height = 600;
    private UpPane up;

    @Override public void start(Stage stage){
        
        root = new Pane();        
        root.setMinHeight(631);
        root.setMinWidth(750);
        scene= new Scene(root);
        scene.getStylesheets().add("stylecss/design.css");
        scene.setFill(Color.TRANSPARENT); 

        
        up = new UpPane();        
        up.setMove(stage);
        
        root.getChildren().addAll(up,new Scene1());
        try{
        stage.getIcons().add(new Image(new FileInputStream("Icon.png")));
        }catch(Exception e){System.out.println(e.getMessage());}
        stage.setScene(scene);        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
