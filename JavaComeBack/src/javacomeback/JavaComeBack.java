package javacomeback;
import java.io.File;
import java.util.Date;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.animation.FadeTransition;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class JavaComeBack extends Application{
    public Scene scene;
    static Pane root;
    static public UpPane upPane;
    private double width=580,height=580;
    private String [] I = {"*.png","*.jpg","*.jpeg","*.gif"}; 
    @Override public void start(Stage stage){
        root = new MainRoot(stage);          
                
        Date date = new Date();
        String datestr = date.toString();
        String [] datadate = datestr.split(" ");
        String [] datetime = datadate[3].split(":");
        int timeang = (Integer.parseInt(datetime[0])-3)*30+90;
        System.out.println(datetime[1]+"\n"+timeang);
        
        scene = new Scene(root,width,height);
        
        LineFX l2 = new LineFX(290,145,290,350,290,290,5,2.77777,1);        
        l2.setGradient(Color.RED, Color.ORANGE,0,1);
        LineFX l1 = new LineFX(290,290,100,290,290,290,50,5000,6);
        l1.setGradient(Color.web("#6633ff"), Color.RED,1,0);        
        l1.getTransforms().add(new Rotate(timeang,290,290));
        LineFX l3 = new LineFX(290,290,290,390,290,290,10,13.88888,1);
        
        Label err = new Label("!!!Open image!!!");
        err.getStyleClass().add("label_err");
        
        
                
        Circle circle = new Circle(25);
        circle.getStyleClass().add("circle1");
        circle.setOnMouseClicked(event->{
            FileChooser chooser = new FileChooser();
            try{
            chooser.setInitialDirectory(new File(System.getProperty("user.home")));
            }catch(Exception e){}
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Image",I),
                    new FileChooser.ExtensionFilter("PNG","*.png"),
                    new FileChooser.ExtensionFilter("JPG","*.jpg")
            );
            try{
            chooser.setTitle("Open Image");
            Image img = new Image(chooser.showOpenDialog(stage).toURI().toString());
            circle.setFill(new ImagePattern(img));
            }catch(Exception e){
                 FadeTransition ft = new FadeTransition(Duration.millis(200),err);
                 ft.setFromValue(1);
                 ft.setToValue(0);
                 ft.setAutoReverse(true);
                 ft.setCycleCount(5);                 
                 ft.play();
            }
        });
        root.getChildren().addAll(circle,err);
        
        
        try{
        stage.getIcons().add(new Image(new FileInputStream("/home/serik/NetBeansProjects/JavaComeBack/src/Icon.png")));
        }catch(Exception e){System.out.println("Not Image");}
        scene.getStylesheets().add("cssstyle/Style.css");
//      
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
//
        stage.setResizable(false);       
        stage.setTitle("ComeBackStyle");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
        
    }
}
