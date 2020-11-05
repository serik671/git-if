package javaapplicationmwv;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.StageStyle;
import javafx.util.Duration;
/**
 *
 * @author serik
 */
public class JavaApplicationMWV extends Application {
    
    public Pane root;
    public static Image icon;
    public static Rectangle rec;
    public static FillTransition ftRec;
    public static RotateTransition rtRec;
    public static ScaleTransition stRec;
    
    @Override public void start(Stage stage){
        root = new Pane();
        root.getStyleClass().add("root1");
        //Buttons
        Button btn1 = new Button("Modal option of rectangle");
        btn1.setOnAction(event->new ModalWindow());
        btn1.getStyleClass().add("btn");
        
        Button btn2 = new Button("Exit");
        btn2.setTranslateX(458);
        btn2.setOnAction(event->stage.close());
        btn2.getStyleClass().add("btn");
        //Rectangle
        rec = new Rectangle(200,200);
        rec.setTranslateY(200);
        rec.setTranslateX(150);
        //FadeTransition
        ftRec = new FillTransition(Duration.millis(1000),rec);
        ftRec.setFromValue(Color.BLACK);
        ftRec.setToValue(Color.WHITE);
        ftRec.setAutoReverse(true);
        ftRec.setInterpolator(Interpolator.LINEAR);
        ftRec.setCycleCount(Animation.INDEFINITE);
        ftRec.play();
        //RotateTransition
        rtRec = new RotateTransition(Duration.millis(1500),rec);
        rtRec.setByAngle(360);
        rtRec.setInterpolator(Interpolator.LINEAR);
        rtRec.setCycleCount(Animation.INDEFINITE);
        rtRec.play();
        //Scaletransition
        stRec = new ScaleTransition(Duration.millis(2000),rec);
        stRec.setByX(1.5);
        stRec.setByY(1.5);
        stRec.setAutoReverse(true);
        stRec.setInterpolator(Interpolator.LINEAR);
        stRec.setCycleCount(Animation.INDEFINITE);
        stRec.play();
        
        root.getChildren().addAll(btn1,btn2,rec);
        Scene scene = new Scene(root,500,600);
        scene.getStylesheets().add("javaapplicationmwv/design/style.css");
        stage.setScene(scene);
        stage.setTitle("Happy rectangle");
        try{
        icon = new Image(getClass().getResourceAsStream("Icon(V).png"));
        stage.getIcons().add(icon);
        }catch(Exception e){
            System.out.println("Error image");
            //MessageBox
            Alert alert = new Alert(Alert.AlertType.WARNING);            
            alert.setContentText("Not load image for application icon");
            alert.getButtonTypes().add(ButtonType.CLOSE);
            alert.showAndWait();            
            if(alert.getResult() == ButtonType.CLOSE)System.exit(0);
        }
        //stage.setAlwaysOnTop(true);
        stage.setOnCloseRequest(event->{
            Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
            exit.setContentText("Вы действительно хотите выйти?");
            exit.getButtonTypes().clear();
            exit.getButtonTypes().addAll(ButtonType.NO,ButtonType.YES);
            exit.showAndWait();
            if(exit.getResult() == ButtonType.NO)event.consume();
        });
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
