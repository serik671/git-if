package javanewyear;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
/**
 *
 * @author serik
 */
public class JavaNewYear extends Application{
    public static BorderPane root = new BorderPane();
    public static Scene scene = new Scene(root);
    public Atom atom;
    public final int width=1300, height=900, minWidth=1280, minHeight=850;
    public static List<SnowFlake> list = new ArrayList<SnowFlake>();
    private final int count = 50;
    public static TextField word;
    public static Label label;

    @Override public void start(Stage stage){
        scene.getStylesheets().add("style/design.css");
        atom = new Atom();
        root.setTop(atom);
        
        label = new Label();
        label.setWrapText(true);
        root.setCenter(label);
        
        word = new TextField();
        word.setOnKeyPressed(event->{
            if(event.getCode()==KeyCode.UP)showTextField(true);
            if(event.getCode()==KeyCode.DOWN)showTextField(false);
        });
        word.setPromptText("Enter your happy string");
        word.setMinHeight(50);
        HBox.setHgrow(word, Priority.ALWAYS);
        word.setTranslateY(50);
        word.getStyleClass().add("tb");
        word.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED,event->{event.consume();});
        word.setOnMouseClicked(event->{
            if(event.getButton()==MouseButton.SECONDARY)word.selectAll();
        });
        word.setOnAction(event->{
            label.setText(word.getText());
        });
        root.setBottom(word);
        
        stage.getIcons().add(new Image("javanewyear/images/snowflake.png"));
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setResizable(true);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();  
        loadSound();
        for(int i=0;i<count;i++){
            SnowFlake flake = new SnowFlake();
            flake.rndY();
            list.add(flake);
            root.getChildren().add(flake);
        }
    }

    private void showTextField(boolean f){
        TranslateTransition tt = new TranslateTransition(Duration.millis(1000),word);
        if(f) tt.setToY(0);
        else tt.setToY(50);
        tt.play();
    }
    private void loadSound(){
        try{
            Media media = new Media(new File("src/javanewyear/sounds/We_Wish_You_A_Merry_Christmas.mp3").toURI().toString());
            System.out.println(media.getSource());
            MediaPlayer player = new MediaPlayer(media);
            player.setCycleCount(Animation.INDEFINITE);
            player.play();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
